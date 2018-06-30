/**
 * This is to generate Haplotype frequency table for HLAHapV reference
 * EUR,glstring,freq,rank
 */
package workshop.haplotype.frequency.write;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import workshop.haplotype.frequency.ranking.OrderbyRanking;
import workshop.haplotype.frequency.ranking.SampleHapCount;

/**
 * @author kazu
 * @version April 6 2018
 *
 */
public class GenerateHapFrequencyTableForHLAHapV {

	/**
	 * 
	 */
	public GenerateHapFrequencyTableForHLAHapV( String global, String targetLoci, String targetName, String today) {
		// TODO Auto-generated constructor stub
		File dir = new File(global);
		String hapV = "HLAHapV/";
		new File(dir.getAbsolutePath() + "/" + hapV).mkdir();	// make FAMCSV dir
		try {
			BufferedWriter out = 
				new BufferedWriter(new FileWriter(dir.getAbsolutePath() + "/" + hapV + targetName + "_" + today + ".csv"));				
			
			for (File country : dir.listFiles()) {
				if ((!country.getName().equals("collective")) && (!country.getName().equals("summary"))
						&& (!country.getName().equals("HLAHapV"))) {
					if (country.isDirectory()) {
						for (File famFile : country.listFiles()) {
							if (famFile.getName().contains("FAM")) {
								SampleHapCount globalshc = new SampleHapCount( famFile.getAbsolutePath(), 
										targetLoci );		// target file for a population
								OrderbyRanking obr = new OrderbyRanking(globalshc);	// generate ranked haplotype list	
								for (String hap : obr.getRankedHapList()) {		// use ordered hap list
									out.write(country.getName() + "," + hap + "," + 
											globalshc.getFrequency().get(hap) + ",");
									out.write(globalshc.getHapCountRank().get(hap).get(1) + "\n");	// rank
								}
							}
						}												
					}					
				}
			}			
			out.close();					
		} catch (IOException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("Cannot write in " + dir.getAbsolutePath() + hapV + targetName + "_" + today + ".csv");
		}	
	}

}
