/**
 * This class generates haplotype report
 */
package workshop.haplotype.write;

import java.io.BufferedReader;
import java.io.PrintWriter;

import workshop.haplotype.family.organize.OrganizeValidatedHaplotype;
import workshop.haplotype.organize.file.ChooseElement;
import workshop.haplotype.organize.file.ChooseElementHapType;

/**
 * @author kazu
 * @version October 2 2017
 *
 */
public class GenerateSampleHaplotype {

	/**
	 * 
	 */
	public GenerateSampleHaplotype(BufferedReader reader, PrintWriter writer) {
		// TODO Auto-generated constructor stub
		ChooseElement ce = new ChooseElementHapType(reader);
		OrganizeValidatedHaplotype oh = new OrganizeValidatedHaplotype(ce);
		
		for (String child : oh.getChildList()) {
			writer.write(child + "\t" + oh.getChildValidation() + "\t");	// added on September 8 2016
			int count = 0;
			for (String hap : oh.getHaplotypeMapList().get(child).get(child)) {
				if (count > 0) {
					writer.write("+");
				}
				writer.write(hap);
				count++;
			}
			writer.write("\n");
			
			for (String parent : oh.getParentList()) {
				writer.write(parent + "\t" + oh.getParentValidation().get(parent)+ "\t");// added on September 8 2016
				int index = 0;
				for (String hap : oh.getHaplotypeMapList().get(child).get(parent)) {
					if (index > 0) {
						writer.write("+");
					}
					writer.write(hap);
					index++;
				}
				writer.write("\n");
			}				
		}					
		writer.close();						
	}

}
