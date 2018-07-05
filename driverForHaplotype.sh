#!/bin/bash

if [ "$#" -ne 2 ]; then
  echo "Illegal number of parameters"
else
  mvn exec:java -Dexec.mainClass="workshop.haplotype.driver.DriverForHaplotype" -Dexec.args="$*"
fi

exit 0
