.. |openlib_api| replace:: The Open Library API
.. _openlib_api: https://openlibrary.org/

.. |the_dog_api| replace:: TheDogApi
.. _the_dog_api: https://thedogapi.com/

Appendix: Working with RESTful JSON APIs
========================================

.. contents::

API Example: Open Library Search API
************************************

The |openlib_api|_ is nice because it does not require you to
register with the API provider to get an API key -- an API key is not required. According to
the API documentation, the endpoint is this URL::

  "https://openlibrary.org/api/search.json"

Basic usage includes what looks like three mutually exclusive parameters, ``q``, ``title``,
and ``author``. The table below provides an example for each parameter as well as a link
to an interactive example of a potential response (via Code Beautify):

.. |openlib_ex1| replace:: Example 1
.. _openlib_ex1: https://codebeautify.org/jsonviewer?url=https://openlibrary.org/search.json?q=the+lord+of+the+rings

.. |openlib_ex2| replace:: Example 2
.. _openlib_ex2: https://codebeautify.org/jsonviewer?url=https://openlibrary.org/search.json?title=the+lord+of+the+rings

.. |openlib_ex3| replace:: Example 3
.. _openlib_ex3: https://codebeautify.org/jsonviewer?url=https://openlibrary.org/search.json?author=tolkien

==========  =========================  ================================  ================
Parameter   Example Value              Query String [1]_                 Example Response
==========  =========================  ================================  ================
``q``       ``the lord of the rings``  ``?q=the+lord+of+the+rings``      |openlib_ex1|_
``title``   ``the lord of the rings``  ``?title=the+lord+of+the+rings``  |openlib_ex2|_
``author``  ``tolkien``                ``?author=tolkien``               |openlib_ex3|_
==========  =========================  ================================  ================

.. [1] Remember, the ``value`` in ``param=value`` contained in a query string needs
   to be url-encoded by calling ``URLEncoder.encode(value, StandardCharsets.UTF_8)``.

The API documentation says the JSON response should look something like this::

  {
      "start": 0,
      "num_found": 629,
      "docs": [
          {...},
          {...},
          {...},
          ...
          {...}
      ]
  }

The starter code proved an example that uses this API in the
|open_library_search_api|_ class -- it models the JSON-formatted
response string using two Java classes, gets the string using an
HTTP client, and parses the string using Gson. The example does little
to no error checking.

.. |open_library_search_api| replace:: ``OpenLibrarySearchApi``
.. _open_library_search_api: https://github.com/cs1302uga/cs1302-api-app/blob/main/src/main/java/cs1302/api/OpenLibrarySearchApi.java

API Example: TheDogApi
**********************

This API requires you to register with the API provider to
get an API key -- an API key is required; see the API's |the_dog_api_auth|_
page for information on how to register for a key.

.. |the_dog_api_auth| replace:: Authentication
.. _the_dog_api_auth: :https://docs.thedogapi.com/authentication

Once you have your API key, you will want to store it in a ``.properties`` file
so that it's not hard-coded in your program. For example, you might store the
API key in ``resources/config.properties`` like this::

  thedogapi.apikey=YOUR-API-KEY

An example of how to read the values from ``resources/config.properties``
is provided in the starter code
`here <https://github.com/cs1302uga/cs1302-api-app/blob/main/src/main/java/cs1302/api/PropertiesExample.java>`__.
We will assume that your code retrieves your API key and stores it in a string
using some code similar to this:

.. code:: java

   final String apiKey = config.getProperty("thedogapi.apikey");

According to the API documentation, the endpoint for TheDogApi is this URL::

  "https://api.thedogapi.com/v1"

This API provides different "methods" that are accessed via different paths
relative to the endpoint::

  "https://api.thedogapi.com/v1/method/name"

The table below provides an example for some of he methods; you should consult
the API documentation for information about other potential methods:

==================  =================  =========================  ================
Method              Description        Query String               Example Response
==================  =================  =========================  ================
``/breeds``         List the Breeds    ``?api_key=KEY``           |dogapi_ex1|_
``/breeds/search``  Search for Breeds  ``?api_key=KEY&q=golden``  |dogapi_ex2|_
==================  =================  =========================  ================

.. |dogapi_ex1| replace:: Example 1
.. _dogapi_ex1: https://codebeautify.org/jsonviewer/cbba90d7

.. |dogapi_ex2| replace:: Example 2
.. _dogapi_ex2: https://codebeautify.org/jsonviewer/cb771263

Below is an example of what the JSON response for the ``/breeds`` method looks like.
Notice how the outer-most element refers to a JSON array and not a JSON object. This
particular response contains an array of objects::

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

To use this API, model the JSON-formatted response body string using Java classes,
get the string using an HTTP client, then parse the string using Gson.

**Aditional Notes:**

* Since the outermost portion of the response is an array of objects, you will need to
  use ``ClassName[].class`` instead of ``ClassName.class`` when using Gson's ``fromJson``
  method. Assuming ``Breed`` is the name of the class used to model each
  object in the array::

    public class BreedImage {
        ...
        String url;
    } // BreedImage

    public class Breed {
        ...
        String name;
        ...
        String origin;
        ...
        BreedImage image;
    } // Breed

    // ELSEWHERE
    Breed[] breeds = GSON.fromJson(responseBody, Breed[].class);

* The variable ``life_span`` does not conform to the class code style guidelines. To
  model a response with such a variable, make use of Gson's ``@SerializedName`` annotation
  as follows::

    @SerializedName("life_span")
    String lifeSpan;

  This will tell Gson that the variable is named ``life_span`` in the JSON response
  and ``lifeSpan`` in the Java object, the latter of which conforms to the class
  code style guidelines. To use the ``@SerializedName`` annotation, import
  ``com.google.gson.annotations.SerializedName``.

Example: Objects with Variable Names that Vary
**********************************************

Consider the following JSON responses::

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

Since the ``releases`` refers to an object with variable names that vary, a ``Map<K, V>``
is needed. A ``Map<K, V>`` is a mapping from "keys" (i.e., variable names) to "values" (i.e., the
values of the keys / variables). To model this in a class that can be used by Gson, declare ``releases``
as a ``Map<String, String>`` variable as follows::

  Map<String, String> releases;

To use ``Map<K, V>``, import ``java.util.Map``. To access the data in the ``releases`` variable,
interact with it using the methods available in the ``Map`` interface::

  public class ExampleResponse {
      Map<String, String> releases;
  } // ExampleResponse

  // ELSEWHERE
  ExampleResponse obj = GSON.fromJson(responseBody, ExampleResponse.class);
  Map<String, String> releases = obj.releases;
  if (releases.contains("na")) {
      // use releases.get("na") to get its value
  } // if

**Additional Notes:**

* In the example above, ``K`` repreents the "key" type and is replaced with ``String``. The
  keys in this mapping are the variable names that vary (e.g., ``"na"``, ``"eu"``, ``"jp"``,
  etc.). Likewise, ``V`` represent the "value" type and is also replaced with ``String``.
  If the values in your response are a different type, then replace ``V`` with something
  else.

* A ``TypeToken`` object is needed when a ``Map<K, V>`` is required to model the outer-most object
  instead of some inner object. Consider the following JSON responses::

    {
      "CSCI 1301": { ... },
      "CSCI 1302": { ... }
    }

    {
      "CSCI 1302": { ... },
      "CSCI 2720": { ... }
    }

  Now suppose you have a class called ``ValueType`` that models the ``{ ... }`` values. You may be
  tempted to try the following, which will NOT work::

    Map<String, ValueType> map = GSON.fromJson(responseBody, Map<String, ValueType>.class);
  
  To get this to work, a ``TypeToken`` object is neede. A ``TypeToken`` is a Gson-specific object 
  that can help Gson remember the type information in a scenario like this. A small example is
  provided below -- be sure to replace ``ValueType`` with the name of class you want to use to model
  the values::

    TypeToken<Map<String, ValueType>> mapType  = new TypeToken<Map<String, ValueType>() {};  
    Map<String, ValueType> map = GSON.fromJson(responseBody, mapType);

  The FQN for ``TypeToken`` is ``com.google.gson.reflect.TypeToken``.
  
.. #############################################################################

.. copyright and license information
.. |copy| unicode:: U+000A9 .. COPYRIGHT SIGN
.. |copyright| replace:: Copyright |copy| Michael E. Cotterell, Bradley J. Barnes, and the University of Georgia.
.. |license| replace:: CC BY-NC-ND 4.0
.. _license: http://creativecommons.org/licenses/by-nc-nd/4.0/
.. |license_image| image:: https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg
                   :target: http://creativecommons.org/licenses/by-nc-nd/4.0/
.. standard footer
.. footer:: |license_image|

   |copyright| This work is licensed under a |license|_ license to students
   and the public. The content and opinions expressed on this Web page do not necessarily
   reflect the views of nor are they endorsed by the University of Georgia or the University
   System of Georgia.
