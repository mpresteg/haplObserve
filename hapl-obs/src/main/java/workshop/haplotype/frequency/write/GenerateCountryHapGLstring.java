/**
 * 
 */
package workshop.haplotype.frequency.write;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import workshop.haplotype.collective.FamSamRelationCountry;
import workshop.haplotype.organize.file.removeLine.RemoveFirstLine;
import workshop.haplotype.organize.file.removeLine.RemoveLine;
import workshop.haplotype.utilities.FileUtilities;

/**
 * @author kazu
 * @version June 22 2018
 *
 */
public class GenerateCountryHapGLstring {

	/**
	 * 
	 */
	public GenerateCountryHapGLstring(String globalDir, String file, 
			FamSamRelationCountry fsrc, String category, String today) {
		// TODO Auto-generated constructor stub

		for (String country : fsrc.getCountryList().getList()) {
			new File(globalDir+country).mkdir();	// make country dir			
			RemoveLine rl = new RemoveFirstLine(FileUtilities.readFile(file));
			
			try {
				BufferedWriter out = 
					new BufferedWriter(new FileWriter(globalDir + country + "/" + 
				category + "_Haplotype_" + country + "_" +today + ".csv"));	
				for (String line : rl.getRemovedList()) {
					out.write(line + "\n");
				}
								
				for (String line : rl.getRevisedList()) {
					String [] separated = line.split(",");
					if (separated[5].equals(country)) {	// bug fixed 
						out.write(line + "\n");						
					}
				}
				
				out.close();					
			} catch (IOException e) {
					// TODO Auto-generated catch block
				e.printStackTrace();
				System.err.println("Cannot write in " + globalDir + country + "/" + 
						category + "_Haplotype_" + country + "_" +today + ".csv");
			}	
						
		}
				
	}

}
