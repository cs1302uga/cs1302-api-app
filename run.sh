#!/bin/bash -ex

mvn -q clean
mvn -q compile
mvn -q exec:exec
