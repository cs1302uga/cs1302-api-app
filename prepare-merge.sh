#!/bin/bash -ex

git checkout main
echo "* $(date)" >> README.md
git commit -am "preparing to merge $*"
