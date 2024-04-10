# CSCI 1302 - Api App v2024.sp

![Approved for](https://img.shields.io/badge/Approved%20for-Spring%202024-blue)
![Team Size](https://img.shields.io/badge/Team%20Size-1-informational)

![Image from page 400 of "The Palm of Alpha Tau Api" (1880)](https://github.com/cs1302uga/cs1302-api-app/raw/main/resources/readme-banner.png)

This document contains the description for the **cs1302-api-app**
project assigned to the students in the Spring 2024 CSCI 1302 classes at
the University of Georgia.

**Please read the entirety of this file before beginning your project.**

## Deadlines

This project has **three deadline options**. Students who perform their
final submission via the submit command before the date/times listed
below automatically receive the associated Submission-Based (SB) extra
credit.

| \# | Bonus | Deadline Option                       |
|----|-------|---------------------------------------|
| 1  | +10   | SAT 2024-05-04 (MAY 04) @ 5:00 PM EST |
| 2  | +5    | SUN 2024-05-05 (MAY 05) @ 5:00 PM EST |
| 3  | +0    | MON 2024-05-06 (May 06) @ 5:00 PM EST |


For both logistical and policy-related reasons, the usual late-work
policy will not apply for this project, and no late submissions will be
accepted after the last deadline option mentioned above. **To be clear,
final project submissions received after the last deadline option will
NOT be graded.**

## Academic Honesty

You agree to the Academic Honesty policy as outlined in the course
syllabus and the University honesty website. Furthermore, you must
adhere to the copyright notice and licensing information at the bottom
of this document.

## Updates

If any updates to this project are needed after it is released, then
they will be announced on Piazza. Updates related to correcting typos
will probably not be announced unless they change the meaning of some
requirement.

## Project Description

Your goal is to implement, from scratch, an application in Java 17 using
JavaFX 17 that incorporates a preponderance of the topics introduced in
this course in a way that demonstrates that you have met the learning
outcomes related to those topics.

Your application must integrate two or more external RESTful JSON APIs
so that your users don't need to utilize multiple services themselves to
get the information or content that they want. Your app needs to do more
than just download and display responses from the external APIs, it
needs to combine the responses in some meaningful way. Combining
responses means that the response from one API should be used (at least
in part) to query the second API (see below for more details). 
Your application must automate the process of connecting two different APIs for a single purpose.

-   Many services provide **free access** to their RESTful JSON APIs --
    a RESTful JSON API is one that you can access with an HTTP client
    (like `java.net.http.HttpClient`) and parse with a JSON library like
    Gson. For this project, you may only use RESTful JSON APIs and no
    other kinds of APIs.

    You can find hundreds of APIs on the web; however, for this
	project, you must pick your APIs from this list:

    * https://github.com/public-apis-dev/public-apis

	Please note that the list above is a public resource and that
	not every API listed has been tested by the instructional staff
	nor guaranteed to be safe for work. All of the APIs list should,
	however, offer full, free access or at least a free access tier
	and do not depend on the purchase of a device/service before a
	request can be made.

	Please make sure you only use APIs that are safe for work and
	adhere to the *Non-Discrimination and Anti-Harassment Policy* mentioned
	later in the project's requirements. 

-   You **must** choose your APIs according to the following guidelines.
    Failure to do so may have a negative impact on your project grade:

    1.  **You are required to integrate two or more RESTful JSON APIs
        from the "public-apis" list above in your final project submission.**

        For each API, you need to be able to answer **yes** to the
        following questions -- they are all questions that can be
        self-determined based on the project description, its
        appendices, the related readings, and what you learned in class:

        -   Does the API method that you are trying to call have a URI
            associated with it that can be adjusted (e.g., by changing
            parts of the URI or by setting an HTTP header) to make a
            request?
        -   Does the API method that you are trying to call return a
            JSON-formatted response?

		**NOTE:** The APIs that you pick must NOT require "OAuth"
		for authentication (for each API on the "public-apis" site, there is a column
                indicating if OAuth is required). It is okay if an API key is required,
		and we even include an example that demonstrates how to
		make a request involving an API key in the appendix. If you
		use an API that requires an API key, then it is your responsibility
		to make sure you familiarize yourself with any restrictions
		associated with the API key. **We also strongly encourage you
		to NOT pick an API that requires you to pay for an API key.**

    3.  **Your app must integrate the two APIs in a meaningful way.**

        For each API, you need to be able to answer **yes** to the
        following questions -- they are all questions that can be
        self-determined based on the project description, its
        appendices, the related readings, and what you learned in class:

        -   Is user input used to adjust your first API request?
        -   Is some part (or all) of the JSON response to your first API
            request used to adjust your second API request? The response
            can be modified by your application before being sent to the
            second API.
        -   Does the JSON response to the second API request provide you
            with one or more pieces of information that are NOT included
            in the response from your first API request?

        The last question implies that both APIs must play an important
        role in the overall process that is automated by your app --
        **it should NOT be possible to implement the functionality of
        your app using only one of the APIs that you specifically
        selected**.

        **Additional Notes:** There are some additional things that you
        should consider that are not explicitly stated in the project
        description:

        1.  **The users of your app must not be required to provide
            their own credentials to any service in order for it to
            function (e.g., a username, password, or API key).** Your
            app may incorporate *your* API keys, if needed, as described
            in the appendix of the project description.

        2.  **You are responsible for dealing with rate limits imposed by
            the APIs that you use.** Many APIs have a limit on the number of
            API calls that can be made by an app (e.g., number of calls per
            second, per day, or in total). Please take precautions when
            using such APIs in your project so that we can grade it. There
            is no way for us to specify the exact number of times that we
            will run your app when grading it. If your app exceeds any rate
            limits while grading, then we will be unable to evaluate it.

        **SUGGESTION:** To reduce the risk that you will exceed a rate
        limit, you are encouraged to write code to let your app keep
        track of the number of API requests that it has made over
        different periods of time and use that information to
        programmatically delay subsequent calls. For example, if your
        app includes a button that triggers multiple API requests, then
        should consider disabling the button while those requests are
        being made AND for some sensible extra amount of time after the
        completion of those requests in order to reduce the rate of
        request made by your app -- the exact amount of extra time will
        depend on the number of API requests your app makes as well as
        the rate limits imposed by the APIs. If your app needs to make
        more calls than it is allowed to make due to an API rate limit,
        then you may be able to introduce delays between requests to
        lower your app's request rate. Any extra waiting or intentional
        delays introduced by your app to deal with rate limits must be
        communicated to the user (e.g., you can adjust a label to say
        something like "intentionally waiting to deal with rate limits"
        whenever your program does either of these two things).

-   Some of these API services do require you to register with them to
    gain access to an "API key" -- an API key is usually just a special
    string that is unique to you that must be incorporated into how you
    request the JSON response. For example, suppose you have an API key
    for the dog API stored in `API_KEY`, then you might use the
    following URL when requesting the JSON for a list of breeds:

    ```
    "https://api.thedogapi.com/v1/breeds?apikey=" + API_KEY
    ```

    If the API key needs to be set using an HTTP header, then refer
    to the example involving the GitHub API in the HTTP reading
    posted earlier this semester.

-   You should read the "Working with RESTful JSON APIs" appendix
    section before you write any code. A link to the appendix can
    be found later in this project description.

You have a lot of flexibility with regard to the functionality and
visuals of your app. So long as your app functions and you meet the
other requirements, you are free to make the app look and feel however
you want (keep it appropriate).

Remember, part of software development is being given a goal but not
necessarily being given instruction on all of the details needed to
accomplish that goal. For example, even though working with things like
API keys have not been explicitly covered in class, you are going to
need to look up how to do these things to complete this project.

## Learning Outcomes

Here are some of the learning outcomes for this project:

-   Plan, design, implement, test, debug, and deploy a complete
    object-oriented software solution in a Linux/Unix environment
    (1302-LO1).
-   Utilize inheritance and polymorphism in a software project
    (1302-LO3-LO4).
-   Develop a GUI for a software project (1302-LO7).
-   Implement exception handling in a software project (1302-LO8).
-   Understand and apply language basics using an OOP language
    (1302-LO11).

## Functional Requirements

A functional requirement is *added* to your point total if satisfied.
This assignment is worth 100 points.

### Primary Functions (90 points)

Your app must integrate two or more external RESTful JSON APIs based on
user input and combine the responses in some meaningful/interesting
way. Combining responses means that the response from one API should be
used (at least in part) to query the second API. Your application must
automate the process of connecting two different APIs for a single
purpose. Failure to meet this requirement will result in a grade of 0
for this category. If you have questions about whether or not your idea
is sufficient, please discuss it with an instructor.

### Multiple Uses per Execution (10 points)

After the application is started, your application should allow the user
to query the API(s) an arbitrary number of times without requiring them
to exit and rerun the application. By arbitrary, we mean that there is
no limit to how many times the user may do this.

## Non-Functional Requirements

A non-functional requirement is *subtracted* from your point total if
not satisfied. To emphasize the importance of these requirements,
non-compliance results in the full point amount being subtracted from
your point total. That is, they are all or nothing.

### Development Environment (100 points)

This project *must compile and run* correctly on Odin using the specific
version of Java that is enabled by the **CSCI 1302 shell profile**. For
this requirement, the term *compile* should be interpreted as *compile
with no errors or warnings*.

### API Access (100 points)

You must directly access any APIs used in your project. You are not
allowed to use third-party services such as Rapid API (or similar) for
access.

### User-Friendly Experience (10 points)

The windows of your application should not exceed a pixel dimension of
`1280` (width) by `720` (height). Additionally, except for reasonable
delays resulting from X forwarding, your application should not
hang/freeze or crash during execution.

**NOTE:** If a grader encounters lag, then they will try to run your application
locally after first checking that it compiles on Odin.

### Private GitHub-hosted Git Repository (20 points)

Each student is required to setup a private GitHub-hosted Git repository
for their project. Failure to make your repository private will result
in a 20-point penalty. If you are unsure whether your repository is
private, ask an instructor or TA to visit the main URL of your project.

**Reminder:** Hosting any CSCI 1302 assignment in a public repository
may lead to an academic honesty violation.

### Local Assets / Resources (10 points)

All assets (e.g., images), except for assets discovered using an
external API, need to be pre-downloaded and placed either in the
`resources` (not `src/main/resources`) or a directory under `resources`.
**This will help make your app faster.** Here are some examples that
illustrate the relationship between the path for a resource and the
`file:` URL that you need to use in your code:

| Resource                | URL                            |
|-------------------------|--------------------------------|
| `resources/icon.png`    | `"file:resources/icon.png"`    |
| `resources/foo/img.png` | `"file:resources/foo/img.png"` |

### Code Style (20)

Every `.java` file that you include as part of your submission for this
project must be in valid style as defined in the [CS1302 Code Style
Guide](https://github.com/cs1302uga/cs1302-styleguide). All of the
individual code style guidelines listed in the style guide document are
considered for this requirement.

If `check1302` on Odin reports any style violations for your submission,
then 5 points will be subtracted from your earned point total **for each
violation**, up to a maximum deduction of 20 points.

### Attribution (10 points)

Proper attribution should be given for **all assets** (e.g., art, sound,
music, etc.) that you include in your project, especially assets that
you did not personally author. All such attributions need to be placed
in the `meta/ATTRIBUTION.md` file.

For each asset that you authored, please provide the following
information:

```
* Asset Name
  - `resources/path/to/file`
  - Your Name. Year.
```

For each asset that you did not personally author, please provide the
following information:

```
* Asset Name
  - `resources/path/to/file`
  - Author. Year.
  - URL
  - License
```

**NOTE:** Don't forget to stage and commit your `meta/ATTRIBUTION.md` file after
you update it!

## Final Project Policies

### No use of `JsonArray`, `JsonElement`, `JsonObject`, and `JsonParser`

You may NOT use or mention the following classes provided by Gson:

-   `com.google.gson.JsonArray`
-   `com.google.gson.JsonElement`
-   `com.google.gson.JsonObject`
-   `com.google.gson.JsonParser`

To parse a JSON-formatted string, use a `Gson` object's `fromJson` method to parse
the string directly into an instance of a class that you created to represent the
object described by that JSON-formatted string. Classes for an iTunes Search response
and result are provided with the starter code. Instructions for parsing JSON-formatted
strings using `fromJson` are described in the
[JSON reading](https://github.com/cs1302uga/cs1302-tutorials/blob/alsi/web/json.md).

### No use of the `openStream()` method in `URL`

You may NOT use or mention the `openStream()` method provided by the
`java.net.URL` class. If you need to access web content, then use an
HTTP client as described in the
[HTTP reading](https://github.com/cs1302uga/cs1302-tutorials/blob/alsi/web/http.md).

### Final Project == Final Exam

Per university policy, each student must be provided the opportunity to
stand for a final examination as part of the completion of a full
instructional term, and instructors have the authority to design and
administer the final examination for a course in whatever manner is
appropriate. In CSCI 1302 this semester, **the final project described
by this document will be treated as the final examination** since the
grade and feedback that a student receives for this assignment is a
summative evaluation of the entire term's work.

### Final Submission Deadline

Please take care to note the date/time for the final submission
deadline, **Deadline Option 3**. In particular, the deadline time
is earlier in the day compared to previous projects.

### Amended Late Work Policy

For both logistical and policy-related reasons, the usual late-work
policy will not apply for this project, and no late submissions will be
accepted after **Deadline Option 3**. **Final submissions received after
Deadline Option 3 will NOT be graded.**

### Non-Discrimination and Anti-Harassment Policy

Since this project affords you more flexibility with respect to the
content of your app, you are reminded that, as a UGA student, you must
conduct yourself in accordance with the
[UGA Code of Conduct](https://conduct.uga.edu/code-of-conduct/) and
the [UGA Non-Discrimination and Anti-Harassment Policy](https://eoo.uga.edu/civil_rights_NDAH/ndah-policy/).

### Private GitHub-hosted Git Repository

Each student is required to set up a private GitHub-hosted Git
repository for their project. **Instructions are provided later in this
document.**

### Working on a Local Machine

If you decide to work on part or all of the project on your local
machine, then it's your responsibility to ensure that your environment
is compatible with the versions of software on Odin. No technical
assistance will be provided by the instructional staff to accommodate
this beyond the information provided in this policy statement. Remember,
**your code still needs to compile and run on Odin** per the
"Development Environment" absolute requirement. That is, if your
submission does not compile on Odin, then that will result in an
immediate zero for the assignment. A list of the relevant software
versions currently in use on Odin (at the time of this writing) is
provided below for convenience.

* **Apache Maven 3.9.6**
  (https://maven.apache.org/)

* **Java 17.0.10** (vendor: Oracle Corporation; **not OpenJDK**)
  (https://www.oracle.com/java/technologies/downloads/)

All other dependencies are handled via Maven.

## How to Download the Project

**Downloading the starter code for this project requires more steps
compared to earlier projects.** These instructions assume that you have
completed the steps in
["Setting up SSH Keys"](https://github.com/cs1302uga/cs1302-tutorials/blob/master/github-setup.md)
to set up your public and private key pair on Odin and GitHub.

1.  If you have not done so already, you should create a free
    GitHub-hosted private Git repository for your project under your
    GitHub account called `cs1302-api-app` and note its SSH URL. Here is
    an example:

    ![image](https://github.com/cs1302uga/cs1302-api-app/raw/main/resources/readme-newrepo.png)

    Remember to note the SSH URL!

    **NOTE:**
    In the remaining instructions, `REPO_SSH` refers to the SSH URL for
    the private repository you created on GitHub.

2.  Clone your empty private repository to your Odin account.

    ```
    $ git clone REPO_SSH cs1302-api-app
    ```

    You should now have a directory called `cs1302-api-app` in your
    present working directory.

	**NOTE:**
    If you get an authentication error, then that means that you did not
    set up your public and private key pair on Odin and GitHub prior to
    following these instructions. Instructions for this are provided in
    the ["Setting up SSH Keys"](https://github.com/cs1302uga/cs1302-tutorials/blob/master/github-setup.md)
	reading.

3.  Set up a remote link to the repository containing the starter code.
    A sequence of commands is provided below. You should make every
    effort to understand what each command is doing *before* you execute
    the command:

        $ cd cs1302-api-app
        $ git branch -M main
        $ git remote add starter https://github.com/cs1302uga/cs1302-api-app.git
        $ git pull starter main --rebase

    If you followed these instructions correctly, then your present
    working directory (you should still be inside `cs1302-api-app`) now
    contains the starter code and a `.git` directory.

4.  You should think of the `cs1302-api-app` directory on Odin as your
    local copy of the project. As you add, stage, commit, branch, etc.,
    those changes are only local to that copy of the project -- they do
    not automatically appear on the GitHub page for your repository. To
    send changes to GitHub, follow these steps:

    1.  Use `git status` to ensure that you are on the `main` branch and
        fully committed. If you're not, then take the necessary steps to
        make sure that you are.

    2.  Push changes to GitHub:

            $ git push origin main

        In your browser, revisit your GitHub-hosted private Git
        repository. Instead of an empty repository, you should now see
        the starter code.

    You can follow the steps above any time you want to send your local
    changes to GitHub.

    NOTE
    If you have trouble getting any of this to work, then try asking on
    Piazza or see someone during office hours.

5.  Clean, compile, and run the starter code using the provided `run.sh`
    script:

        $ ./run.sh

    Here is the expected output, which also shows the related Maven
    commands, should you wish to type them out manually:

        + mvn -q -e clean
        + mvn -q -e compile
        + mvn -q -e exec:exec

    By default, the project is setup to automatically run the
    `cs1302.api.ApiDriver` class. If you wish to run another driver
    class, then you can provide the simple class name of a class with a
    `main` method in the `cs1302.api` package after the script name:

        $ ./run.sh PropertiesExample

    Any other command-line options that you add after the script name
    will be added to the end of the `mvn` command that executes
    `exec:exec`.

## Submission Instructions

You will be submitting your project via Odin before the last deadline
option indicated near the top of this document. Make sure your project
files are on `odin.cs.uga.edu`.

1.  Update your project's `meta/DEADLINE.md` Specific instructions for
    what to include in the update are contained in the file itself.

2.  Merge all of your work in progress into to the `main` branch, then
    tag your `main` branch for this deadline as described below.

    1.  Ensure that whatever branch you are on is **fully committed**
        (i.e., `git status` says there is nothing to commit).

    2.  Checkout the `main` branch.

        ```
        $ git checkout main
        ```

    3.  If needed, merge changes into `main` from the branch you were
        working on following the instructions provided in the "Git
        Feature Branch Workflow" appendix section.

    4.  Tag your `main` branch by executing the commands below:

        ```
        $ git tag -am "deadline" deadline
        $ git push origin --all
        $ git push origin --tags
        ```

        **NOTE:**
        Take special care to ensure that your fully-committed `main`
        branch reflects the project you wish to submit. Compare your log
        to the log on GitHub. If your GitHub repository does not have
        the most recent version of your project, then you may need to do
        a `git push origin main` while on your `main` branch.

        **NOTE:**
        If you need to make more commits and retag, then use an `a`,
        `b`, `c`, ... suffix in the tag names (e.g., `deadline-a`,
        `deadline-b`, etc.).

	    **NOTE:**
        Evidence of branching and merging **is encouraged** for this
        deadline. When inspecting your Git log, the graders would like
        to see that you made proper use of `branch`, `checkout`, and
        `merge` to work on portions of your project prior to including
        those changes in your `main` branch. More detailed instructions
        are provided in the "Git Feature Branch Workflow" appendix
        section.

3.  **CRITICAL:** For this deadline, you also need to submit on Odin!
    Use the `submit` command to submit your project on Odin for this
    deadline:

    1.  Check for style guide violations:

        ```
        $ check1302 cs1302-api-app/src/main/java
        ```

        **NOTE:**
        If there are style guide violations, then checkout a new branch,
        fix your code, commit, test your program, potentially fix some
        more, commit, then checkout `main` and merge in the beautiful
        code from the branch you were just in. You should also retag and
        push your `main` branch as described elsewhere. Once you have no
        style guide violations, you can proceed to the next step.

    2.  Perform your final submission:

        ```
        $ submit cs1302-api-app csci-1302
        ```

        **NOTE:**
        If you have any problems submitting your project, then please
        contact the CSCI 1302 Support Team by sending a private post to
        "Instructors" via the course Piazza as soon as possible.

## Appendix

### JavaFX

* [JavaFX 17 API Documentation](https://openjfx.io/javadoc/17/)
* [CSCI 1302 JavaFX Bookmarks](https://github.com/cs1302uga/cs1302-tutorials/blob/alsi/javafx/javafx-bookmarks.md)
* [CSCI 1302 JavaFX Tutorial](https://github.com/cs1302uga/cs1302-tutorials/blob/alsi/javafx/javafx.md)

### Git

* [Git Feature Branch Workflow](APPENDIX_GIT.md)

### RESTful JSON APIs

* [Working with RESTful JSON APIs](APPENDIX_API.md)

## FAQ

Below are some frequently asked questions related to this project. You
may also find the cs1302-gallery FAQ\_ a useful resource as well.

1.  **May I use an API not mentioned in the project description?**

	* **RESTful JSON API**

      If you're asking about a RESTful JSON API that's not mentioned in
      the project description, then probably yes! Here are the
      requirements:

      -   the API and your use of the API must not violate the UGA \_; and
      -   the API must provide a JSON response based on a request to a URL
          that is pragmatically generated by your program.

      If you're not sure about an API, then ask on Piazza.

	* **Java API**

      If you're asking about a third-party Java API that is not included
      with Java 17, JavaFX, Gson, or the starter code, then
      the answer is NO.

2.  **How do I add sound?**

    While JavaFX does support audio playback of various formats, this
    feature is not currently available over X11 forwarding from Odin.
    We're sorry to say this, but **you should not attempt to add audio
    to your application** for this project.

[![license\_image](https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg)](http://creativecommons.org/licenses/by-nc-nd/4.0/)

Copyright © Michael E. Cotterell, Bradley J. Barnes, and the University
of Georgia. This work is licensed under a CC BY-NC-ND 4.0\_ license to
students and the public. The content and opinions expressed on this Web
page do not necessarily reflect the views of nor are they endorsed by
the University of Georgia or the University System of Georgia.

<hr>

**Feature Preparation Timestamps:**
