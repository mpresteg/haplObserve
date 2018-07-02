#!/bin/bash

if [ "$#" -ne 1 ]; then
  echo "Illegal number of parameters"
else
  mvn exec:java -Dexec.mainClass="workshop.haplotype.driver.DriverForGenerateFullHaplotypeFrequencyTable" -Dexec.args="$*"
fi

exit 0
