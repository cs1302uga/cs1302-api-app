Appendix: Git Feature Branch Workflow
=====================================

.. contents::

Resources
*********

=================  ======================================================
Resource           Description
=================  ======================================================
|git_scm_docs|_    alternative to the manual pages for `git`
|pro_git|_ (book)  contains a lot of good examples
|sink|_ (book)     excellent introduction to version control by Eric Sink
=================  ======================================================

Introduction to the Workflow
****************************

The basic idea behind the **Feature Branch Workflow** is that all feature
development should take place in a dedicated branch instead of the ``main``
branch. Not only does this workflow make it easier to list what features
you've added by inspecting the log, it also means the ``main`` branch never
contains broken code! It also makes it easier for you to stop working on
a feature if you don't think it's going to work out.

:IMPORTANT:
   Before reading the rest of this appendix section, please refer to your
   notes from the |gitscm_branching|_ reading in *Pro Git*.

.. |gitscm_branching| replace:: 3.1 Git Branching - Branches in a Nutshell
.. _gitscm_branching: https://git-scm.com/book/en/v2/Git-Branching-Branches-in-a-Nutshell

Rule of Thumb
*************

Before you checkout a branch or pull changes from a remote repository,
you should always make sure that whatever branch you're in is fully
committed. You can always ``git status`` to check.

Start Working on a Feature
**************************

You should use a new, separate branch for each new feature or bug you
work on. Give your branch a short name. In the command below, we chose
``short-feature-name``, but you should choose something that makes more
sense for the feature you intend to work on.

.. code::

   $ git checkout main
   $ git checkout -b short-feature-name

Work on a Feature
*****************

While in your feature branch, update, add, commit, and push changes
as you make progress. Try to write `good log messages <https://chris.beams.io/posts/git-commit/>`__
that are also short and sweet. Most features involve multiple commits;
let the log tell the story of your feature development.

Merge the Feature Branch into main
**********************************

Once your feature works, it's time to merge the branch back into ``main``.
We recommend that you follow the "Rule of Thumb" mentioned earlier, then
use the following commands directly inside your ``cs1302-omega`` directory:

.. code::

   $ git checkout main
   $ ,/prepare-merge.sh short-feature-name
   $ git merge short-feature-name
   $ git pull origin main
   $ git push origin --all

:NOTE:
   The ``prepare-merge.sh`` script appends a timestamp to your ``README.rst`` in
   the ``main`` branch, then stages and commits that change. This ensures
   that ``main`` and your feature branch have divergent histories, which makes
   the merge look a little nicer in your log.

.. #############################################################################

.. resources
.. |git_scm_docs| replace:: Git Reference Manual
.. _git_scm_docs: https://git-scm.com/docs
.. |pro_git| replace:: Pro Git
.. _pro_git: https://git-scm.com/book/en/v2
.. |sink| replace:: Version Control by Example
.. _sink: https://ericsink.com/vcbe/index.html

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
