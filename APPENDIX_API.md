# Appendix: Working with RESTful JSON APIs

## Checking for Rate Limits

Some RESTful JSON APIs impose limits on how often users can make API requests. Some APIs with rate limits set the status code of a response to [429 (Too Many Requests)](https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/429) when a user exceeds their rate limit and include a [Retry-After](https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Retry-After) header (or various [X-RateLimit-](https://www.ietf.org/archive/id/draft-polli-ratelimit-headers-02.html#name-header-specifications) headers) in the response to explicitly let us know how long we should wait before sending another request. 

Information about an API's "rate limits" can usually be found in the documentation provided by the API developers or provider. If you are still unsure whether rate limits are imposed after consulting the API's documentation, then you can usually figure it out yourself using `curl`, a Unix command that lets you send an HTTP request in the terminal and see its associated HTTP response. For example, the following `curl` example would tell us that we need to wait at least 300 seconds (i.e., 5 minutes) before sending another request (see "`man curl`" for information about `curl`'s options):

```
$ curl -IL -X GET 'REQUEST-URI'
```

```
HTTP/1.2 429 Too Many Requests
Retry-After: 300
```

You can access the headers associated with an `HttpResponse<T>` obect in in your Java code by calling the object's `headers()` method. After that, you can call `getFirstValue(name)` on the `HttpHeaders` object to access the value of a specific header entry where `name` is the lowercase version of the desired header's name. Here is a simple example (without error handling) that attempts to get, parse, and use the value of the`"Retry-After"` header associated with an `HttpResponse<String>` object:

```java
Optional<String> retryAfter = response.headers()
    .getFirstValue("retry-after"); 

if (rettryAfter.isPresent()) {
    String retryAfterValue = retryAfter.get(); // "300"
    int seconds = Integer.parseInt(retryAfterValue); // 300
    ...
} // if
```

See the documentation for [the `headers()` method](https://docs.oracle.com/en/java/javase/17/docs/api/java.net.http/java/net/http/HttpResponse.html#headers())  in `HttpResponse<T>` for more information.

## API Example: Open Library Search API

[The Open Library API](https://openlibrary.org/developers/api) is nice
because it does not require you to register with the API provider to
get an API key. According to the API documentation, the endpoint URL
is:

    "https://openlibrary.org/api/search.json"

Basic usage includes what looks like three mutually exclusive
parameters, `q`, `title`, and `author`. The table below provides an
example for each parameter.

| Parameter | Example Value           | Query String[1]                |
|-----------|-------------------------|--------------------------------|
| `q`       | `the lord of the rings` | `?q=the+lord+of+the+rings`     |
| `title`   | `the lord of the rings` | `?title=the+lord+of+the+rings` |
| `author`  | `tolkien`               | `?author=tolkien`              |

Remember, the `value` in `param=value` contained in a query string
needs to be url-encoded by calling
`URLEncoder.encode(value, StandardCharsets.UTF_8)`.

The API documentation says the JSON response should look something like
this:

```json
{
    "start": 0,
    "num_found": 629,
    "docs": [
        {...},
        {...},
        {...},
        {...}
    ]
}
```

The starter code includes an example that uses this API in
[OpenLibrarySearchApi.java](https://github.com/cs1302uga/cs1302-api-app/blob/main/src/main/java/cs1302/api/OpenLibrarySearchApi.java).
It models the JSON-formatted response string using two Java classes,
gets the string using an HTTP client, and parses the string using Gson.
The example does little to no error checking.

## API Example: TheDogApi

[TheDogApi](https://thedogapi.com/) requires you to register with the
API provider to get an API, and that API key is required in order to
make HTTP requests to the API's endpoint URL. See the API's
[signup page](https://thedogapi.com/signup) for information on how to
register for a key.

Once you have your API key, you will want to store it in a `.properties`
file so that it's not hard-coded in your program. For example, you might
store the API key in `resources/config.properties` like this:

    thedogapi.apikey=YOUR-API-KEY

An example of how to read the values from `resources/config.properties`
is provided in the starter code
[here](https://github.com/cs1302uga/cs1302-api-app/blob/main/src/main/java/cs1302/api/PropertiesExample.java).
We will assume that your code retrieves your API key and stores it in a
string using some code similar to this:

``` java
final String apiKey = config.getProperty("thedogapi.apikey");
```

According to the API documentation, the endpoint for TheDogApi is this
URL:

    "https://api.thedogapi.com/v1"

This API provides different "methods" that are accessed via different
paths relative to the endpoint:

    "https://api.thedogapi.com/v1/method/name"

The table below provides an example for some of he methods; you should
consult the API documentation for information about other potential
methods:

| Method           | Description       | Query String            |
|------------------|-------------------|-------------------------|
| `/breeds`        | List the Breeds   | `?api_key=KEY`          |
| `/breeds/search` | Search for Breeds | `?api_key=KEY&q=golden` |

Remember, the `value` in `param=value` contained in a query string
needs to be url-encoded by calling
`URLEncoder.encode(value, StandardCharsets.UTF_8)`.

Below is an example of what the JSON response for the `/breeds` method
looks like. Notice how the outer-most element refers to a JSON array and
not a JSON object. This particular response contains an array of
objects:

```json
[
  {...},
  {
    ...
    "id": 1,
    "name": "Affenpinscher",
    "bred_for": "Small rodent hunting, lapdog",
    "breed_group": "Toy",
    "life_span": "10 - 12 years",
    "temperament": "Stubborn, Curious, Playful, Adventurous, Active, Fun-loving",
    "origin": "Germany, France",
    "reference_image_id": "BJa4kxc4X",
    "image": {
      "id": "BJa4kxc4X",
      "width": 1600,
      "height": 1199,
      "url": "https://cdn2.thedogapi.com/images/BJa4kxc4X.jpg"
    }
  },
  {...},
  ...
  {...},
]
```

To use this API, model the JSON-formatted response body string using
Java classes, get the string using an HTTP client, then parse the string
using Gson.

### Aditional Notes

Since the outermost portion of the response is an array of objects,
you will need to use `ClassName[].class` instead of
`ClassName.class` when using Gson's `fromJson` method. Assuming
`Breed` is the name of the class used to model each object in the
array:

```java
public class BreedImage {
    ...
    String url;
} // BreedImage
```

```java
public class Breed {
    ...
    String name;
    ...
    String origin;
    ...
    BreedImage image;
} // Breed
```

```java
// ELSEWHERE
Breed[] breeds = GSON.fromJson(responseBody, Breed[].class);
```

The variable `life_span` does not conform to the class code style
guidelines. To model a response with such a variable, make use of
Gson's `@SerializedName` annotation as follows:

```java
@SerializedName("life_span")
String lifeSpan;
```

This will tell Gson that the variable is named `life_span` in the
JSON response and `lifeSpan` in the Java object, the latter of which
conforms to the class code style guidelines. To use the
`@SerializedName` annotation, import
`com.google.gson.annotations.SerializedName`.

## Example: Objects with Variable Names that Vary

Consider the following JSON responses:

```json
{
  "releases": {
    "na": "...",
    "eu": "..."
  }
}

{
  "releases": {
    "jp": "...",
    "eu": "..."
  }
}
```

Since the `releases` refers to an object with variable names that vary,
a `Map<K, V>` is needed. A `Map<K, V>` is a mapping from "keys" (i.e.,
variable names) to "values" (i.e., the values of the keys / variables).
To model this in a class that can be used by Gson, declare `releases` as
a `Map<String, String>` variable as follows:

```java
Map<String, String> releases;
```

To use `Map<K, V>`, import `java.util.Map`. To access the data in the
`releases` variable, interact with it using the methods available in the
`Map` interface:

```java
public class ExampleResponse {
    Map<String, String> releases;
} // ExampleResponse
```

```java
// ELSEWHERE
ExampleResponse obj = GSON.fromJson(responseBody, ExampleResponse.class);
Map<String, String> releases = obj.releases;
if (releases.contains("na")) {
    // use releases.get("na") to get its value
} // if
```

### Additional Notes

In the example above, `K` repreents the "key" type and is replaced
with `String`. The keys in this mapping are the variable names that
vary (e.g., `"na"`, `"eu"`, `"jp"`, etc.). Likewise, `V` represent
the "value" type and is also replaced with `String`. If the values
in your response are a different type, then replace `V` with
something else.

A `TypeToken` object is needed when a `Map<K, V>` is required to
model the outer-most object instead of some inner object. Consider
the following JSON responses:

```json
{
  "CSCI 1301": { ... },
  "CSCI 1302": { ... }
}
```

```json
{
  "CSCI 1302": { ... },
  "CSCI 2720": { ... }
}
```

Now suppose you have a class called `ValueType` that models the
`{ ... }` values. You may be tempted to try the following, which
will NOT work:

```java
Map<String, ValueType> map = GSON.fromJson(responseBody, Map<String, ValueType>.class);
```

To get this to work, a `TypeToken` object is neede. A `TypeToken` is
a Gson-specific object that can help Gson remember the type
information in a scenario like this. A small example is provided
below -- be sure to replace `ValueType` with the name of class you
want to use to model the values:

```java
TypeToken<Map<String, ValueType>> mapType  = new TypeToken<Map<String, ValueType>() {};
Map<String, ValueType> map = GSON.fromJson(responseBody, mapType);
```

The FQN for `TypeToken` is `com.google.gson.reflect.TypeToken`.

[![license\_image](https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg)](http://creativecommons.org/licenses/by-nc-nd/4.0/)

Copyright © Michael E. Cotterell, Bradley J. Barnes, and the University
of Georgia. This work is licensed under a CC BY-NC-ND 4.0\_ license to
students and the public. The content and opinions expressed on this Web
page do not necessarily reflect the views of nor are they endorsed by
the University of Georgia or the University System of Georgia.
