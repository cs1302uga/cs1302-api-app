package cs1302.api.example;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.http.HttpRequest;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

/**
 * Example using Open Library Search API.
 *
 * <p>
 * To run this example on Odin, use the following command:
 *
 * <pre>
 * $ ./run.sh cs1302.api.example.OpenLibrarySearchApiExample
 * </pre>
 *
 * or both of these commands:
 *
 * <pre>
 * $ mvn -e clean compile verify
 * $ mvn -e exec:exec -Dexec.mainClass=cs1302uga.api/cs1302.api.example.OpenLibrarySearchApiExample
 * </pre>
 *
 * @see <a href="https://github.com/cs1302uga/cs1302-api-app/blob/main/APPENDIX_API.md#api-example-open-library-search-api">API Example: Open Library Search API</a>
 * @see cs1302.api.example.OpenLibraryResult
 * @see cs1302.api.example.OpenLibraryDoc
 */
public class OpenLibrarySearchApiExample {

    /** HTTP client. */
    public static final HttpClient HTTP_CLIENT = HttpClient.newBuilder()
        .version(HttpClient.Version.HTTP_2)           // uses HTTP protocol version 2 where possible
        .followRedirects(HttpClient.Redirect.NORMAL)  // always redirects, except from HTTPS to HTTP
        .build();                                     // builds and returns a HttpClient object

    /** Google {@code Gson} object for parsing JSON-formatted strings. */
    public static Gson GSON = new GsonBuilder()
        .setPrettyPrinting()                          // enable nice output when printing
        .create();                                    // builds and returns a Gson object

    /** Endpoint URI for the Open Library Search API. */
    private static final String ENDPOINT = "https://openlibrary.org/search.json";

    /**
     * Main entry-point for the application.
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        String q = "the lord of the rings";
        OpenLibraryResult result = OpenLibrarySearchApiExample.search(q);
        if (result == null) {
            System.err.printf("error fetching result for: %s\n", q);
        } else {
            System.out.printf("numFound = %d\n", result.numFound());
            for (OpenLibraryDoc doc: result.docs()) {
                System.out.println(doc.title());
            } // for
        } // if
    } // main

    /**
     * Return an {@code Optional} describing the root element of the JSON
     * response for a "search" query.
     * @param q query string
     * @return an {@code Optional} describing the root element of the response
     */
    public static OpenLibraryResult search(String q) {
        System.out.printf("Searching for: %s\n", q);
        System.out.println("This may take some time to download...");
        try {
            String url = ENDPOINT + "?q=" + URLEncoder.encode(q, StandardCharsets.UTF_8);
            URI uri = new URI(url);
            String json = OpenLibrarySearchApiExample.fetch(uri);
            return GSON.fromJson(json, OpenLibraryResult.class);
        } catch (IllegalArgumentException | IOException | InterruptedException e) {
            System.err.printf("search() error: %s\n", e.getMessage());
            return null;
        } catch (URISyntaxException e) {
            // URL is malformed
            System.err.printf("URISyntaxException: %s\n", e.getMessage());
            throw new RuntimeException(e);
        } // catch
    } // search

    /**
     * Returns the response body string data from a URI.
     * @param uri location of desired content
     * @return response body string
     * @throws IOException if an I/O error occurs when sending or receiving
     * @throws InterruptedException if the HTTP client's {@code send} method is
     *    interrupted
     */
    private static String fetch(URI uri) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(uri)
            .build();
        HttpResponse<String> response = HTTP_CLIENT
            .send(request, BodyHandlers.ofString());
        int statusCode = response.statusCode();
        if (statusCode != 200) {
            throw new IOException("response status code not 200:" + statusCode);
        } // if
        return response.body().trim();
    } // fetchString

} // OpenLibrarySearchApiExample

/**
 * Represents an Open Library Search API result
 *
 * @param numFound
 * @param docs
 *
 * <p>
 *
 * An immutable data class (Java 14+). The record keyword auto-generates
 * boilerplate code for immutable data carriers:
 *  - Constructor: OpenLibraryResult(int numFound, OpenLibraryDoc[] docs)
 *  - Getters: result.numFound(), result.docs()
 *  - toString()
 *  - equals() and hashCode(): compares all fields
 *  - implements Serializable hooks (used for converting an object to a Java specific byte-stream encoding)
 */
record OpenLibraryResult(
        @SerializedName("num_found") // Used for gson.fromJson() decoding when the name of the member variable does not match the JSON field
        int numFound,
        OpenLibraryDoc[] docs
) {
    /*
     * Example of overriding an auto-generated function with the `record` class.
     * Not necessarily needed, but this shows that you can have custom returns,
     * which may be useful for getter methods.
     */
    @Override
    public String toString() {
        return String.format("numFound = %d, docs = %s", numFound, Arrays.toString(docs));
    }
}

/**
 * Represents an Open Library Search API document
 *
 * @param type
 * @param title
 */
record OpenLibraryDoc(String type, String title) {}
