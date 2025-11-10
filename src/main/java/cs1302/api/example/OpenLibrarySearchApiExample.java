package cs1302.api.example;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.http.HttpRequest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
            System.out.printf("numFound = %d\n", result.numFound);
            for (OpenLibraryDoc doc: result.docs) {
                System.out.println(doc.title);
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
            URI uri = new URI(OpenLibrarySearchApiExample.ENDPOINT, "?q=", q);
            String json = OpenLibrarySearchApiExample.fetch(uri);
            return GSON.fromJson(json, OpenLibraryResult.class);
        } catch (IllegalArgumentException | IOException | InterruptedException e) {
            return null;
        } catch (URISyntaxException e) {
            // URL is malformed
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
