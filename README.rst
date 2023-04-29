
.. project information
.. |title| replace:: Api
.. |slug| replace:: **cs1302-api**
.. |semester| replace:: Spring 2023
.. |version| replace:: v2023.sp
.. |team_size| replace:: 1
.. |banner| image:: https://github.com/cs1302uga/cs1302-api-app/raw/main/resources/readme-banner.png
   :alt: Image from page 400 of "The Palm of Alpha Tau Api" (1880)
.. |compile_points| replace:: 100
.. |style_points_each| replace:: 5
.. |style_points_max| replace:: 20
.. |server| replace:: Odin

.. deadlines
.. |deadline1| replace:: SAT 2023-05-06 (MAY 06) @ 1:00 PM EST
.. |deadline2| replace:: SUN 2023-05-07 (MAY 07) @ 1:00 PM EST
.. |deadline3| replace:: MON 2023-05-08 (MAY 08) @ 1:00 PM EST

.. deadline section links
.. _deadline1: #deadline-option-1-sat-2023-05-06-may-06--100-pm-est
.. _deadline2: #deadline-option-2-sun-2023-05-07-may-07--100-pm-est
.. _deadline3: #deadline-option-3-mon-2023-05-08-may-08--100-pm-est

.. notices (need to manually update the urls)
.. |team_size_notice| image:: https://img.shields.io/badge/Team%20Size-1-informational
   :alt: Team Size |team_size|
.. |approval_notice| image:: https://img.shields.io/badge/Approved%20for-Spring%202023-magenta
   :alt: Approved for: |version|

CSCI 1302 - |title| |version|
#############################

|approval_notice| |team_size_notice|

|banner|

This document contains the description for the |slug| project assigned to the
students in the |semester| CSCI 1302 classes at the University of Georgia.

**Please read the entirety of this file before beginning your project.**

----

.. contents::

----

Deadlines
*********

This project has **three deadlines options**. Students who perform their final
submission via the submit command before the date/times listed below automatically
receive the associated Submission-Based (SB) extra credit. The amended late penalty
that is described in the "|final_pols|_" section does not start applying until
after the final date and time listed.

=====  ===============
Bonus  Deadline Option
=====  ===============
 +10   |deadline1|_
  +5   |deadline2|_
  +0   |deadline3|_
=====  ===============

Academic Honesty
****************

You agree to the Academic Honesty policy as outlined in the course syllabus and the
University honesty website. Furthermore, you must adhere to the copyright notice and
licensing information at the bottom of this document.

Updates
*******

If any updates to this project are needed after it is released, then they will
be announced on Piazza. Updates related to correcting typos will probably
not be announced unless they change the meaning of some requirement.

:2023-04-27:
   Added the missing ``meta/DEADLINE.md`` file.

Project Description
*******************

Your goal is to implement, from scratch, an application in Java 17 using JavaFX 17
that incorporates a preponderance of the topics introduced in this course in a way that
demonstrates that you have met the learning outcomes related to those topics.

Your application must integrate two or more external RESTful JSON APIs so that your users don't need
to utilize multiple services themselves to get the information or content that
they want. Your app needs to do more than just download and display responses
from the external APIs, it needs to combine the responses in some meaningful
way. Combining responses means that the response from one API should be used
(at least in part) to query the second API. Your application must automate the process of
connecting two different APIs for a single purpose.

- Many services provide **free access** to their RESTful JSON APIs -- a RESTful JSON API is
  one that you can access with an HTTP client (like ``java.net.http.HttpClient``) and parse
  with a JSON library like Gson. For this project, you may only use RESTful JSON APIs and no
  other kinds of APIs.

  You can find hundreds of APIs on the web. As a starting point, you may want to take a look
  at this list: https://github.com/public-apis/public-apis. Please note that this is a public
  resource. Not everything has been tested and not everything is safe for work. Please make sure
  you only use APIs that are safe for work and meet the |uga_ndah|_. You must access the APIs
  directly. You are not allowed to use third-party services such as Rapid API (or similar) for
  access.

- Some of these API services do require you to register with them to gain access to
  an "API key" -- an API key is usually just a special string that is unique to you
  that must be incorporated into how you request the JSON response. For example,
  suppose you have an API key for the dog api stored in ``API_KEY``, then you
  might use the following URL when requesting the JSON for a list of breeds
  (see |the_dog_api_breeds|_):

  .. code::

     "https://api.thedogapi.com/v1/breeds?apikey=" + API_KEY

  .. |the_dog_api_breeds| replace:: ``/breeds``
  .. _the_dog_api_breeds: https://docs.thedogapi.com/api-reference/breeds/breeds-list

- You should read the "|working_with_apis|_" appendix section before you write any code.

You have a lot of flexibility with regard to the functionality and
visuals of your app. So long as your app actually functions and you
meet the other requirements, you are free to make the app look and
feel however you want (keep it appropriate).

Remember, part of software development is being given a goal but not
necessarily being given instruction on all of the details needed to
accomplish that goal. For example, even though working with things
like API keys have not been explicitly covered in class, you are going
to need to look up how to do these things in order to complete this
project.

Learning Outcomes
*****************

Here are some of the learning outcomes for this project:

* Plan, design, implement, test, debug, and deploy a complete object-oriented software solution in Linux/Unix environment (1302-LO1).
* Utilize inheritance and polymorphism in a software project (1302-LO3-LO4).
* Develop a GUI for a software project (1302-LO7).
* Implement exception-handling in a software project (1302-LO8).
* Understand and apply language basics using an OOP language (1302-LO11).

.. |freqs| replace:: Functional Requirements
.. _freqs: #functional-requirements

|freqs|
*******

A functional requirement is *added* to your point total if satisfied.
This assignment is worth 100 points.

Primary Functions (90 points)
   Your app must integrate two or more external RESTful JSON APIs
   based on user input and combine the responses in some meaningful /
   interesting way. Combining responses means that the response from
   one API should be used (at least in part) to query the second
   API. Your application must automate the process of connecting two
   different APIs for a single purpose. Failure to meet this
   requirement will result in a grade of 0 for this category. If you
   have questions about whether or not your idea is sufficient, please
   discuss it with an instructor.

Multiple Uses per Execution (10 points)
   After the application is started,
   your application should allow the user to query the API(s) an arbitrary number of
   times without requiring them to exit and rerun the application. By arbitrary, we
   mean that there is no limit to how many times the user may do this.

Non-Functional Requirements
***************************

A non-functional requirement is *subtracted* from your point total if
not satisfied. In order to emphasize the importance of these requirements,
non-compliance results in the full point amount being subtracted from your
point total. That is, they are all or nothing.

Development Environment (100 points)
  This project must *must compile and run*
  correctly on Odin using the specific version of Java that is enabled
  by the **CSCI 1302 shell profile**. For this requirement, the term
  *compile* should be interpreted as *compile with no errors or warnings*.

API Access (100 points)
  You must directly access any APIs used in your project. You are not allowed to
  use third-party services such as Rapid API (or similar) for access.

User-Friendly Experience (10 points)
   The windows of your application
   should not exceed a pixel dimension of ``1280`` (width) by ``720`` (height).
   Additionally, except for reasonable delays resulting from X forwarding, your
   application should not hang/freeze or crash during execution.

   :NOTE:
      If a grader encounters lag, then they will try to run your application
      locally after first checking that it compiles on Odin.

**Private** GitHub-hosted Git Repository (20 points)
   Each student is required to setup a private GitHub-hosted Git repository
   for their project. Failure to make your repository private will result in
   a 20-point penalty. If you are unsure whether your repository is private,
   ask an instructor or TA to visit the main URL of your project.

   **Reminder:** Hosting any CSCI 1302 assignment in a public repository may
   lead to an academic honesty violation.

Local Assets / Resources (10 points)
   All assets (e.g., images), except
   for assets discovered using an external API, need to be pre-downloaded and
   placed either in the ``resources`` (not ``src/main/resouces``) or a directory
   under ``resources``. **This will help make your app faster.** Here are some
   examples that illustrate the relationship between the path for a resource
   and the ``file:`` URL that you need to use in your code:

   =========================  ================================
   Resource                   URL
   =========================  ================================
   ``resources/icon.png``     ``"file:resources/icon.png"``
   ``resources/foo/img.png``  ``"file:resources/foo/img.png"``
   =========================  ================================

Code Style (|style_points_max|)
   Every ``.java`` file that you include as part of your submission for this project must
   be in valid style as defined in the `CS1302 Code Style Guide <https://github.com/cs1302uga/cs1302-styleguide>`_.
   All of the individual code style guidelines listed in the style guide document are considered for
   this requirement.

   If ``check1302`` on |server| reports any style violations for your submission, then
   |style_points_each| points will be subtracted from your earned point total **for each
   violation**, up to a maximum deduction of |style_points_max| points.

Attribution (10 points)
   Proper attribution should be given for **all assets**
   (e.g., art, sound, music, etc.) that you include in your project, especially assets
   that you did not personally author. All such attributions needs to be placed in the
   ``meta/ATTRIBUTION.md`` file.

   For each asset that you authored, please provide the following information:

   .. code::

      * Asset Name
        - `resources/path/to/file`
        - Your Name. Year.

   For each asset that you did not personally author, please provide the following
   information:

   .. code::

      * Asset Name
        - `resources/path/to/file`
        - Author. Year.
        - URL
        - License

   :NOTE:
      Don't forget to stage and commit your ``meta/ATTRIBUTION.md`` file after you
      update it!

Final Project Policies
**********************

.. |final_pols| replace:: Final Project Policies
.. _final_pols: https://github.com/cs1302uga/cs1302-api#final-project-policies

No use of ``JsonArray``, ``JsonElement``, ``JsonObject``, and ``JsonParser``
   You may not use or mention the following classes provided by Gson:

   * ``com.google.gson.JsonArray``
   * ``com.google.gson.JsonElement``
   * ``com.google.gson.JsonObject``
   * ``com.google.gson.JsonParser``

   To parse a JSON-formatted string, use a ``Gson`` object's ``fromJson`` method to parse
   the string directly into instances of classes that represent the data. Classes for
   an iTunes Search response and result are provided with the starter code. Instructions
   for parsing JSON-formatted strings using ``fromJson`` is described in the
   `JSON reading <https://github.com/cs1302uga/cs1302-tutorials/blob/master/web/json.rst>`__.

No use of the ``openStream()`` method in ``URL``
  You may not use or mention the ``openStream()`` method provided by the ``java.net.URL`` class.
  If you need to access web content, then use an HTTP client as described in the
  `HTTP reading <https://github.com/cs1302uga/cs1302-tutorials/blob/master/web/http.rst>`__.

Final Project == Final Exam
   Per university policy, each student must be provided the opportunity to stand
   for a final examination as part of the completion of a full instructional term,
   and instructors have the authority to design and administer the final examination
   for a course in whatever manner is appropriate. In CSCI 1302 this semester,
   **the final project that described by this document will be treated as the final
   examination** since the grade and feedback that a student receives for this
   assignment is a summative evaluation of the entire term's work.

.. comment
   Final Project Grade Not Dropped
      Since this Final Project is your Final Exam, the grade that you earn for your
      final project submission does not qualify as a grade that can be dropped.

Final Submission Deadline
   Please take care to note the date/time for final submission deadline,
   **Deadline 3**. In particular, the deadline time is earlier
   in the day compared to previous projects.

Amended Late Work Policy
   For both logistical and policy-related reasons, the usual late work policy
   will not apply for this project, and no late submissions will be accepted after
   |deadline3|_.

   Final submissions received after |deadline3|_ will not be graded.

Non-Discrimination and Anti-Harassment Policy
   Since this project affords you more flexibility with respect to the content of your
   app, you are reminded that, as a UGA student, you must conduct yourself in accordance
   with the |uga_ndah|_.

   .. |uga_ndah| replace:: Non-Discrimination and Anti-Harassment Policy
   .. _uga_ndah: https://eoo.uga.edu/policies-resources/ndah-policy/

Private GitHub-hosted Git Repository
   Each student is required to setup a private GitHub-hosted Git repository
   for their project. **Instructions are provided later in this document.**

Working on a Local Machine
   If you decide to work on part or all of the project on your local machine,
   then it's your responsibility to ensure that your environment is compatible
   with the versions of software on Odin. No technical assistance will be provided
   by the instructional staff to accommodate this beyond the information provided
   in this policy statement. Remember, **your code still needs to compile and
   run on Odin** per the "Development Environment" absolute requirement. That is,
   if your submission does not compile on Odin, then that will result in an
   immediate zero for the assignment. A list of the relevant software versions
   currently in use on Odin (at the time of this writing) is provided below for
   convenience.

   * **Apache Maven 3.8.6**
        https://maven.apache.org/
   * **Java 17.0.5** (vendor: Oracle Corporation; **not OpenJDK**)
        https://www.oracle.com/java/technologies/downloads/

   All other dependencies are handled via Maven.

How to Download the Project
***************************

.. |ssh_keys| replace:: Setting up SSH Keys
.. _ssh_keys: https://git.io/fjLzB#setting-up-ssh-keys

**Downloading the starter code for this project requires more steps compared
to earlier projects.** These instructions assume that you have completed the steps
in "|ssh_keys|_" to setup your public and private key pair on Odin and GitHub.

1. If you have not done so already, you should create a
   free GitHub-hosted private Git repository for your project under
   your GitHub account called ``cs1302-api`` and note its SSH URL.
   Here is an example:

   .. image:: https://github.com/cs1302uga/cs1302-api-app/raw/main/resources/readme-newrepo.png

   Remember to note the SSH URL!

   :NOTE:
      In the remaining instructions, ``REPO_SSH`` refers to the SSH URL for the
      private repository you created on GitHib.

2. Clone your empty private repository to your Odin account.

   .. code::

      $ git clone REPO_SSH cs1302-api

   You should now have a directory called ``cs1302-api`` in your present
   working directory.

   :NOTE:
      If you get an authentication error, then that means that you did not setup
      your public and private key pair on Odin and GitHub prior to following these
      instructions. Instructions for this are provided in the "|ssh_keys|_" reading.

3. Setup a remote link the repository containing the starter code.
   A sequence of commands is provided below. You should
   make every effort to understand what each command is doing
   *before* you execute the command::

     $ cd cs1302-api
     $ git branch -M main
     $ git remote add starter https://github.com/cs1302uga/cs1302-api-app.git
     $ git pull starter main --rebase

   If you followed these instructions correctly, then your present working
   directory (you should still be inside ``cs1302-api``) now contains the
   starter code and a ``.git`` directory.

4. You should think of the ``cs1302-api`` directory on Odin as your local
   copy of the project. As you add, stage, commit, branch, etc., those changes
   are only local to that copy of the project -- they do not automatically
   appear on the GitHub page for your repository. To send changes to GitHub,
   follow these steps:

   1. Use ``git status`` to ensure that you are on the ``main`` branch and
      fully committed. If you're not, then take the necessary steps to
      make sure that you are.

   3. Push changes to GitHub::

        $ git push origin main

      In your browser, revisit your GitHub-hosted private Git repository.
      Instead of an empty repository, you should now see the starter code.

   You can follow the steps above any time you want to send your local
   changes to GitHub.

   :NOTE:
      If you have trouble getting any of this to work, then try asking
      on Piazza or see someone during office hours.

5. Clean, compile, and run the starter code using the provided
   ``run.sh`` script::

     $ ./run.sh

   Here is the expected output, which also shows the related Maven
   commands, should you wish to type them out manually::

     + mvn -q -e clean
     + mvn -q -e compile
     + mvn -q -e exec:exec

   By default, the project is setup to automatically run the
   ``cs1302.api.ApiDriver`` class. If you wish to run another
   driver class, then you can provide the simple class name
   of a class with a ``main`` method in the ``cs1302.api``
   package after the script name::

     $ ./run.sh PropertiesExample

   Any other command-line options that you add after the
   script name will be added to the end of the ``mvn`` command
   that executes ``exec:exec``.

Submission Instructions
***********************

Deadline Option 1: |deadline1|
==============================

:NOTE:
   Same instructions as the |deadline3|_ deadline.

Deadline Option 2: |deadline2|
==============================

:NOTE:
   Same instructions as the |deadline3|_ deadline.

Deadline Option 3: |deadline3|
==============================

For this deadline, you're required to **include the your final project code**
and **update to your deadline file**:``meta/DEADLINE.md``.

1. Update your project's ``meta/DEADLINE.md`` Specific instructions
   for what to include in the update are contained in the file itself.

2. Merge all of your work in progress into to the ``main`` branch,
   then tag your ``main`` branch for this deadline as described below.

   1. Ensure that whatever branch you are on is **fully committed**
      (i.e., ``git status`` says there is nothing to commit).

   2. Checkout the ``main`` branch.

      .. code::

         $ git checkout main

   3. If needed, merge changes into ``main`` from the branch
      you were working on following the instructions provided
      in the "|git_feature_workflow|_" appendix section.

   4. Tag your ``main`` branch by executing the commands below:

      .. code::

         $ git tag -am "deadline" deadline
         $ git push origin --all
         $ git push origin --tags

      :NOTE:
         Take special care to ensure that your fully-committed ``main``
         branch reflects the project you wish to submit. Compare your
         log to the log on GitHub. If your GitHub repository does not
         have the most recent version of your project, then you may
         need to do a ``git push origin main`` while on your ``main``
         branch.

      :NOTE:
         If you need to make more commits and retag, then use an ``a``, ``b``,
         ``c``, ... suffix in the tag names (e.g., ``deadline-a``,
         ``deadline-b``, etc.).

      :NOTE:
         Evidence of branching and merging **is encouraged** for this deadline.
         When inspecting your Git log, the graders would like to see that
         you made proper use of ``branch``, ``checkout``, and ``merge`` to
         work on portions of your project prior to including those changes in
         your ``main`` branch. More detailed instructions are provided in
         the "|git_feature_workflow|_" appendix section.

4. **CRITICAL:** For this deadline, you also need to submit on Odin!
   Use the ``submit`` command to submit your project on Odin for this
   deadline:

   1. Check for style guide violations:

      .. code::

         $ find cs1302-api-app/src/main/java -type f -name "*.java" | xargs check1302

      :NOTE:
         If there are style guide violations, then checkout a new branch,
         fix your code, commit, test your program, potentially fix some
         more, commit, then checkout ``main`` and merge in the beautiful
         code from the branch you were just in. You should also retag and
         push your ``main`` branch as described elsewhere. Once you have no
         style guide violations, you can proceed to the next step.

   2. Perform your final submission:

      .. code::

         $ submit cs1302-api csci-1302

      :NOTE:
         If you have any problems submitting your project, then please
         contact the CSCI 1302 Support Team by sending a private post
         to "Instructors" via the course Piazza as soon as possible.

Appendix
********

.. rubric:: **JavaFX**

* `JavaFX 17 API Documentation <https://openjfx.io/javadoc/17/>`__
* `CSCI 1302 JavaFX Bookmarks <https://github.com/cs1302uga/cs1302-tutorials/blob/master/javafx/javafx-bookmarks.md>`__
* `CSCI 1302 JavaFX Tutorial <https://github.com/cs1302uga/cs1302-tutorials/blob/master/javafx/javafx.md>`__

.. rubric:: **Git**

.. |git_feature_workflow| replace:: Git Feature Branch Workflow
.. _git_feature_workflow: https://github.com/cs1302uga/cs1302-api-app/blob/main/APPENDIX_GIT.rst

* |git_feature_workflow|_

.. rubric:: **RESTful JSON APIs**

.. |working_with_apis| replace:: Working with RESTful JSON APIs
.. _working_with_apis: https://github.com/cs1302uga/cs1302-api-app/blob/main/APPENDIX_API.rst

* |working_with_apis|_

FAQ
***

.. |cs1302_gallery| replace:: cs1302-gallery FAQ
.. _cs1302_gallery: https://github.com/cs1302uga/cs1302-gallery#appendix---faq

Below are some frequently asked questions related to this project.
You may also find the |cs1302_gallery|_ a useful resource as well.

1. **May I use an API not mentioned in the project description?**

   .. rubric:: **RESTful JSON API**

   If you're asking about a RESTful JSON API that's not mentioned in the project
   description, then probably yes! Here are the requirements:

   * the API and your use of the API does must not violate the UGA |uga_ndah|_; and
   * the API must provide a JSON response based on a request to a URL that
     is pragmatically generated by your program.

   If you're not sure about an API, then ask on Piazza.

   .. rubric:: **Java API**

   If you're asking about a third-party Java API that is not included with
   Java 17, JavaFX 17.0.2, Gson 2.9.0, or the starter code, then the answer is no.

2. **How do I add sound?**

   While JavaFX does support audio playback of various formats, this feature is not
   currently available over X11 forwarding from Odin. We're sorry to say this, but
   **you should not attempt to add audio to your application** for this project.

3. **How can I generate my Javadoc using Maven?**

   For this project, a ``site.sh`` script is provided that will deploy
   a Maven site, including Javadoc, to your ``~/public_html/cs1302-api``
   directory when executed on Odin. The script will display the URL of the
   deployed site near the end of its execution. Here is the command::

     $ ./site.sh

.. #############################################################################

.. readings
.. |reading_github_setup| replace:: Setting up your own GitHub Account
.. _reading_github_setup: https://github.com/cs1302uga/cs1302-tutorials/blob/master/github-setup.md

.. instructor github profiles
.. |mepcotterell| replace:: ``mepcotterell``
.. _mepcotterell: https://github.com/mepcotterell
.. |bjb211| replace:: ``bjb211``
.. _bjb211: https://github.com/bjb211

.. util
.. |Y| unicode:: U+2713
.. |N| unicode:: U+2717

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

----

.. rubric:: **Feature Preparation Timestamps:**
