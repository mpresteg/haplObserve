/**
 * Update manually modified files
 * This works when existing ambiguity strings are extended, but does not work to make it shorter
 */
package workshop.haplotype.update;

import java.io.PrintWriter;
import workshop.haplotype.ambiguity.AmbiguityList;
import workshop.haplotype.organize.file.ReadFileOrganizeInList;
import workshop.haplotype.utilities.FileUtilities;

/**
 * @author kazu
 * @version August 23 2018
 *
 */
public class UpdateHaplotypes {

	/**
	 * 
	 */
	public UpdateHaplotypes(String input, String output) {
		// TODO Auto-generated constructor stub
		
		ReadFileOrganizeInList ro = new ReadFileOrganizeInList(FileUtilities.readFile(input));
		AmbiguityList amList = new AmbiguityList();
		
		PrintWriter writer = FileUtilities.writeFile(output);	
		for (String line : ro.getOriginalList()) {
			String [] elements = line.split("\t");
			String [] haplotypes = elements[2].split("\\+");
			String haplotype = "";
			int count = 0;
			for (String hap : haplotypes) {
				String [] alleles = hap.split("~");
				String tmphap = "";
				for (String allele : alleles) {
					amList.convertToAmbiguityString(allele);
					tmphap += amList.getConverted() + "~";
				}
				tmphap = tmphap.substring(0, tmphap.length() - 1);
				haplotype += tmphap;
				if (count == 0) {
					haplotype += "+";
				}
				count++;
			}
			writer.write(elements[0] + "\t" + elements[1] + "\t" + haplotype + "\n");		
		}
		writer.close();			
	}

}
