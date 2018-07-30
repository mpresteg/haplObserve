/**
 * generate human view friendly haplotype summary table
 * This is similar to GenerateFamSampleRelationHapValidCountryTable
 * GenerateFamSampleRelationHapValidCountryTable generate table per family
 * GenerateHaplotypeSummary generates combined single table for all families
 * 
 */
package workshop.haplotype.write;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import workshop.haplotype.collective.FamSamRelationCountry;
import workshop.haplotype.collective.SeparateHapAndAllele;
import workshop.haplotype.gene.OrderedHLAgene;

/**
 * @author kazu
 * @version June 18 2018
 *
 */
public class GenerateHaplotypeSummary {

	/**
	 * @param hapPath haplotype directory created during RunHaplObserveForMultipleFamilies
	 * @param category: Validation 
	 */
	public GenerateHaplotypeSummary(String hapPath, String category, 
			FamSamRelationCountry fsrc, String today) {
		// TODO Auto-generated constructor stub
		File hap = new File(hapPath);

		try {	
			BufferedWriter out = 
				new BufferedWriter(new FileWriter(hapPath + "/Family_Haplotype_Summary_Table_" + today + ".csv"));
			out.write("Family_ID,Sample_ID,Relation,");
			OrderedHLAgene hla = new OrderedHLAgene();
			for (String gene : hla.getOrderedGeneList()) {	// go through ordered gene
				out.write(gene + ",");
			}
			out.write("Validation,Ethnicity/Country\n");
			
			for (File dir : hap.listFiles()) {
				if (dir.isDirectory()) {
					String fam = dir.getAbsolutePath();
					File glFile = new File(fam);
					for (File file : glFile.listFiles()) {
						if (file.getName().contains(category)) {
							SeparateHapAndAllele saa = new SeparateHapAndAllele(file.getAbsolutePath(), fsrc);
							for (List<String> list : saa.getFamSamRelationHapCountry()) {																
								for (int count = 0; count < 2; count++) {	// family, sample, relation
									for (int index = 0; index < 3; index++) {
										out.write(list.get(index) + ",");										
									}
									for (String type : saa.getSampleAllele().get(list.get(1)).get(count)) {
										out.write(type + ",");
									}
									out.write(list.get(4) + ",");	// validation
									out.write(list.get(5) + "\n");	// count
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
			System.err.println("Cannot write in " + hapPath + "/Family_Haplotype_Summary_" + today + ".csv");
		}
		
	}

}
