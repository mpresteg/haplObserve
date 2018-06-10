/**
 * 
 */
package workshop.haplotype.write;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import workshop.haplotype.family.organize.OrganizeValidatedHaplotype;
import workshop.haplotype.gene.OrderedHLAgene;
import workshop.haplotype.organize.file.ChooseElement;
import workshop.haplotype.organize.file.ChooseElementHapType;

/**
 * @author kazu
 *
 */
public class GenerateHaplotypeLog {

	/**
	 * 
	 */
	public GenerateHaplotypeLog(String inputFile, String log) {
		// TODO Auto-generated constructor stub
		ChooseElement ce = new ChooseElementHapType(inputFile);
		OrganizeValidatedHaplotype oh = new OrganizeValidatedHaplotype(ce);
		OrderedHLAgene orderedGene = new OrderedHLAgene();
		
		try {
			BufferedWriter out = 
				new BufferedWriter(new FileWriter(log));	
			
			out.write("Child ID,");
			int count = 0;
			for (String gene : orderedGene.getOrderedGeneList()) {	// 
				if (count != 0) {
					out.write(",");
				}
				out.write(gene);
				count++;
			}
			out.write("\n");
			for (String child : oh.getChildList()) {
				out.write(child + ",");
				for (String gene : orderedGene.getOrderedGeneList()) {	// 
					if (oh.getChildHapTypeDRLinkageMap().get(child).containsKey(gene)) {
						String test = "";
						if (oh.getChildValid().get(child).get(gene)) {
							test = "true";
						}
						else {
							test = "false";
						}

						out.write(test + ",");
					}
					else {
						out.write(",");
					}
				}
				out.write("\n");
			}
			
			out.close();					
		} catch (IOException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("Cannot write in " + log);
		}		
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub	
		String file = "SupplementalTable1.csv";
		new GenerateHaplotypeLog(file, "SupplementalData.csv");

	}

}
