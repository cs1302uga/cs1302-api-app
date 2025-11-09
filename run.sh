#!/bin/bash -ex

mvn -e -q clean compile verify
mvn -e -q exec:exec -Dexec.mainClass=cs1302uga.api/cs1302.api.${1-ApiDriver}
