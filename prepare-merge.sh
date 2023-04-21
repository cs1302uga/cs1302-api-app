#!/bin/bash -ex

git checkout main
echo "* $(date)" >> README.rst
git commit -am "preparing to merge $*"
