#!/bin/bash -ex

mvn -q clean compile verify
mvn -e -q exec:exec -Dexec.mainClass=cs1302uga.api/${1-cs1302.api.ApiDriver}
