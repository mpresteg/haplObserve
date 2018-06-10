/**
 * This class generates haplotype report
 */
package workshop.haplotype.write;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

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
	public GenerateSampleHaplotype(String inputFile, String outputFile) {
		// TODO Auto-generated constructor stub
		ChooseElement ce = new ChooseElementHapType(inputFile);
		OrganizeValidatedHaplotype oh = new OrganizeValidatedHaplotype(ce);
		try {
			BufferedWriter out = 
				new BufferedWriter(new FileWriter(outputFile));	
			
			for (String child : oh.getChildList()) {
				out.write(child + "\t" + oh.getChildValidation() + "\t");	// added on September 8 2016
				int count = 0;
				for (String hap : oh.getHaplotypeMapList().get(child).get(child)) {
					if (count > 0) {
						out.write("+");
					}
					out.write(hap);
					count++;
				}
				out.write("\n");
				
				for (String parent : oh.getParentList()) {
					out.write(parent + "\t" + oh.getParentValidation().get(parent)+ "\t");// added on September 8 2016
					int index = 0;
					for (String hap : oh.getHaplotypeMapList().get(child).get(parent)) {
						if (index > 0) {
							out.write("+");
						}
						out.write(hap);
						index++;
					}
					out.write("\n");
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
		new GenerateSampleHaplotype(file, "SupplementalData.txt");

	}

}
