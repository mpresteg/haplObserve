[![Build Status](https://api.travis-ci.org/mpresteg/haplObserve.svg?branch=master)](https://travis-ci.org/mpresteg/haplObserve)

Installation and Execution:
 - Install Git
 - Clone the repository (git clone https://github.com/mpresteg/haplObserve.git)
 - Install Java (written for 1.8)
 - Install Maven (configured for 3.3.3)
 - Run ‘mvn clean package’ from the root of the haplObserve cloned (local) repository
 
 Scripts:
 - driverForHaplotype.sh &lt;inputFile> &lt;sampleHapOutFile> &lt;singleAlleleHapOutFile> &lt;logFile> &lt;sampleTwoFieldHapOutFile>
  - driverForGenerateFamilyHaplotype.sh &lt;workingDirectory>
  - driverForGenerateFullHaplotypeFrequencyTable.sh &lt;workingDirectory>
  - driverForGenerateSixLociHaplotypeTable.sh &lt;workingDirectory>
