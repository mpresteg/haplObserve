[![Build Status](https://api.travis-ci.org/mpresteg/haplObserve.svg?branch=master)](https://travis-ci.org/mpresteg/haplObserve)

# Using the software:

The ability to download the software package and make use of command line tools is available.

From the Releases section of GitHub you may grab the snapshot of the latest release.  E.g:  hapl-obs-tools-0.0.1-SNAPSHOT-bin.zip from a given release at [Releases](https://github.com/ihiw/haplObserve/releases)

After un-zipping the software, you may run either:
 - ./hapl-obs-tools-0.0.1-SNAPSHOT/bin/haplotype-driver -h OR
 - ./hapl-obs-tools-0.0.1-SNAPSHOT/bin/haplotype-table-driver -h
for instructions on how to run the software.
 
# Prerequisite:
  # HaplObserve requires the following:
  - “&lt;baseDirectory>/collective/” directory structure. The &lt;baseDirectory> name can be anything, such as resource, but “collective” directory name must be used.

  - “gl_strings_XXX.csv” files should be stored under “&lt;baseDir>/collective/gl_strings_XXX.csv. The software looks for “gl_strings” to identify files to be used. Multiple families can be included in a single file. If multiple files exist, the software combines them.

  - The “gl_strings” file contains the following information: Labcode, Family ID, Sample ID, Relation, Gl String, Ethnicity/Country. 

 - The category should be included in the first line as a header.

 - Six example of gl_strings files are included.
 
 - if HaplObserve builds incorrect haplotypes after initial trial, manually edited haplotypes can be saved in &lt;baseDirectory>/collective/update. The manually edited files is used as final results.
 
 - newly generated directories should be deleted or moved to other place.
 
 
 # haplotype-driver -i &lt;inputFile> -o &lt;outputFile>
 - Takes spreadsheet format input file.
 - This is convenient to build haplotype from a single family
 
 # haplotype-table-driver -b &lt;baseDirectory> -fam
 - Generates haplotypes from multiple families
 - This does NOT calculate haplotype frequencies
  
 # haplotype-table-driver -b &lt;baseDirectory> -full
 - Generates haplotypes from multiple families (same as option1)
 - Separate haplotypes by ethnicity/country
 - Calculates haplotype frequencies for 11 HLA loci (HLA-A, HLA-C, HLA-B, HLA-DRB3/4/5, HLA-DRB1, HLA-DQA1, HLA-DQB1, HLA-DPA1 and HLA-DPB1)
 - Generate summary table that contains haplotype frequencies from all ethnicity/country in a single spreadsheet
 - Generates haplotype frequency table that can be used as reference table for HLAHapV  
  
  # haplotype-table-driver -b &lt;baseDirectory> -six
 - use this when genotypes for HLA-DRB3/4/5, HLA-DQA1 and HLA-DPA1 are not available. 
 - Generates haplotypes from multiple families (same as option1&2)
 - Separate haplotypes by ethnicity/country (same as option2)
 - Calculates haplotype frequencies for 6 HLA loci (HLA-A, HLA-C, HLA-B, HLA-DRB1, HLA-DQB1, HLA-DPB1)
 - Generate summary table that contains haplotype frequencies from all ethnicity/country in a single spreadsheet
 - Generates haplotype frequency table that can be used as reference table for HLAHapV
 

# (Alternative) Installation and Execution:
 - Install JDK (written for 1.8)
 - Download Maven (https://maven.apache.org/download.cgi)
 - Install Maven (https://maven.apache.org/install.html, or useful instruction for Windows: https://www.mkyong.com/maven/how-to-install-maven-in-windows/) 
 - Confirm correct installation by: mvn -version
 - It is important to set JAVA_HOME and PATH environments correctly.
 
 - Install Git (https://git-scm.com/downloads)
 - Open terminal (use Git Bash terminal for Windows)
 - Change directory where haplObserve should be cloned, e.g., "cd" will change to your home directory
 - Clone the repository: git clone https://github.com/IHIW/haplObserve.git
 - When haploObserve is cloned in home (~): cd haplObserve (go inside of haplObserve directory)
 - From the root of the haplObserve cloned (local) repository: mvn clean install 