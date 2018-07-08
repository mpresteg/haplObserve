[![Build Status](https://api.travis-ci.org/mpresteg/haplObserve.svg?branch=master)](https://travis-ci.org/mpresteg/haplObserve)

Installation and Execution:
 - Install Git (https://git-scm.com/downloads)
 - Clone the repository
 - Install Java (written for 1.8)
 - Install Maven (configured for 3.3.3)
 - Run ‘mvn clean package’ from the root of the haplObserve cloned (local) repository
 
 
Prerequisite:
  - HaplObserve requires the following:
  - “global/collective/” directory structure. The “global” directory name can be anything, such as resource, but “collective” directory name must be used.

  - “gl_strings_XXX.csv” files should be stored under “global/collective/gl_strings_XXX.csv. The software looks for “gl_strings” to identify files to be used. Multiple families can be included in a single file. If multiple files exist, the software combines them.

  - The “gl_strings” file contains the following information: Labcode, Family ID, Sample ID, Relation, Gl String, Ethnicity/Country. 

 - The category should be included in the first line as a header.

 - Six example of gl_strings files are included.
 
 - if HaplObserve builds incorrect haplotypes after initial trial, manually edited haplotypes can be saved in global/collective/update. The manually edited files is used as final results.
 
 - newly generated directories should be deleted or moved to other place.
 
 
 Scripts:
 - driverForHaplotype.sh &lt;inputFile> &lt;sampleHapOutFile>
 - Takes spreadsheet format input file.
 - This is convenient to build haplotype from a single family
 
 
 - driverForGenerateFamilyHaplotype.sh &lt;workingDirectory>
 - Generates haplotypes from multiple families
 - This does NOT calculate haplotype frequencies
  
 
 - driverForGenerateFullHaplotypeFrequencyTable.sh &lt;workingDirectory>
 - Generates haplotypes from multiple families (same as option1)
 - Separate haplotypes by ethnicity/country
 - Calculates haplotype frequencies for 11 HLA loci (HLA-A, HLA-C, HLA-B, HLA-DRB3/4/5, HLA-DRB1, HLA-DQA1, HLA-DQB1, HLA-DPA1 and HLA-DPB1)
 - Generate summary table that contains haplotype frequencies from all ethnicity/country in a single spreadsheet
 - Generates haplotype frequency table that can be used as reference table for HLAHapV  
  
  
 - driverForGenerateSixLociHaplotypeTable.sh &lt;workingDirectory>
 - use this when genotypes for HLA-DRB3/4/5, HLA-DQA1 and HLA-DPA1 are not available. 
 - Generates haplotypes from multiple families (same as option1&2)
 - Separate haplotypes by ethnicity/country (same as option2)
 - Calculates haplotype frequencies for 6 HLA loci (HLA-A, HLA-C, HLA-B, HLA-DRB1, HLA-DQB1, HLA-DPB1)
 - Generate summary table that contains haplotype frequencies from all ethnicity/country in a single spreadsheet
 - Generates haplotype frequency table that can be used as reference table for HLAHapV
 




 