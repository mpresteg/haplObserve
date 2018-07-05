/**
 * For Family Haplotype frequency
 * This program is to organize directory and input files for 
 * GenerateGlobalGroupsHapCountTable
 */
package workshop.haplotype.organize.file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kazu & Matt
 * @version July 5 2018
 *
 */
public class CreateDirectoryList {
	private List<String> dirList;	// directory list => population
	private List<String> inputFileNameList;	// input file list: AFA/FAM_Haplotype_
	private String globalFam;	// global input file: haplotype/FAM_Haplotype_

	/**
	 * @param globalDir => full file path to global directory
	 */
	public CreateDirectoryList( String globalDir ) {	// globalDir needs to contain / at the end
		// TODO Auto-generated constructor stub
		dirList = new ArrayList<String>();
		inputFileNameList = new ArrayList<String>();
		globalFam = "";
		File directory = new File( globalDir );	// create File object in global dir
		File [] fileList = directory.listFiles();	// generate file list
		
		for (File file : fileList) {	// go through directories
			if ( file.isDirectory() ) {	// select directory
				// exclude collective and summary directory
				if ((!file.getName().equals("collective")) && (!file.getName().equals("summary")) &&
						(!file.getName().equals("HLAHapV")) && (!file.getName().equals("haplotype"))
						&& (!file.getName().equals("FAMCSV"))) {
					dirList.add(file.getName());	// capture all directory, but these three
				}		
			}
		}
		
		for (String dir : dirList) {	// go through country directory
//			System.out.println(dirName + dir);
			File inside = new File( globalDir + dir );	// full file path
			File [] inputList = inside.listFiles();
			for (File input : inputList) {	// go through file names
				if (input.isFile()) {	// select ONLY file
					if (input.getName().contains("FAM_Haplotype_")) {	// select specific file name
						inputFileNameList.add( globalDir + dir + "/" + input.getName());
					}					
				}
				
			}
		}
		
		// TODO:  double check - changing to haplotype
		//File inside = new File( globalDir + "collective");	// deal with collective directory
//		File inside = new File(globalDir + "collective/haplotype"); // deal with collective/haplotype directory
		File inside = new File(globalDir + "haplotype"); // deal with haplotype directory
		File [] inputList = inside.listFiles();
		for (File input : inputList) {
			if (input.isFile()) {
				if (input.getName().contains("FAM_Haplotype_")) {
					// TODO:  double check - changing to haplotype
					//global = globalDir + "collective/" + input.getName();
					globalFam = globalDir + "haplotype/" + input.getName();
				}					
			}
		}
	}
	
	public List<String> getDirList() {
		return dirList;
	}
	
	public List<String> getInputFileNameList() {
		return inputFileNameList;
	}
	
	public String getGlobalFam() {
		return globalFam;
	}

}
