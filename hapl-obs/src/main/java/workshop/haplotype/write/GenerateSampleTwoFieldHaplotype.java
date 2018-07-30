/**
 * 
 */
package workshop.haplotype.write;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import workshop.haplotype.family.organize.OrganizeValidatedHaplotype;
import workshop.haplotype.organize.file.ChooseElement;
import workshop.haplotype.organize.file.ChooseElementHapType;
import workshop.haplotype.utilities.FileUtilities;

/**
 * @author kazu
 *
 */
public class GenerateSampleTwoFieldHaplotype {

	/**
	 * 
	 */
	public GenerateSampleTwoFieldHaplotype(String inputFile, String outputFile) {
		// TODO Auto-generated constructor stub
		ChooseElement ce = new ChooseElementHapType(FileUtilities.readFile(inputFile));
		OrganizeValidatedHaplotype oh = new OrganizeValidatedHaplotype(ce);
		try {
			BufferedWriter out = 
					new BufferedWriter(new FileWriter(outputFile));	
			
			for (String child : oh.getChildList()) {
				out.write(child + "\t" + oh.getChildValidation() + "\t");	// added on September 8 2016
				int count = 0;
				for (String hap : oh.getTwoFieldHaplotypeMapList().get(child).get(child)) {
					if (count > 0) {
						out.write("+");
					}
					out.write(hap);
					count++;
				}
				out.write("\n");
				
				for (String parent : oh.getParentList()) {
					// do not print if there is only one haplotype present
					// this happens when one parents haplotype was imputed from a single child, trio family
					// this created a problem when LD was calculated using Steve's script
					if (oh.getTwoFieldHaplotypeMapList().get(child).get(parent).size() == 2) {
						out.write(parent + "\t" + oh.getParentValidation().get(parent)+ "\t");// added on September 8 2016
						int index = 0;
						
						for (String hap : oh.getTwoFieldHaplotypeMapList().get(child).get(parent)) {
							if (index > 0) {
								out.write("+");
							}
							out.write(hap);
							index++;
						}
						out.write("\n");
					}
					
				}				
			}					
			out.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("Cannot write in " + outputFile);
		}	
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String file = "SupplementalTable1.csv";
		new GenerateSampleTwoFieldHaplotype(file, "SupplementalData.txt");

	}

}
