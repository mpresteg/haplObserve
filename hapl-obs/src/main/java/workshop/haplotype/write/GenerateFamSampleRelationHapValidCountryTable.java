/**
 * This generate human view friendly haplotype table
 * haplotypes are separated in two lines
 * allele are separated in columns
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
public class GenerateFamSampleRelationHapValidCountryTable {

	/**
	 * @param hapPath haplotype directory created during RunHaplObserveForMultipleFamilies
	 * @param category: Validation 
	 */
	public GenerateFamSampleRelationHapValidCountryTable(String hapPath, String category, 
			FamSamRelationCountry fsrc, String output) {
		// TODO Auto-generated constructor stub
		File hap = new File(hapPath);		// haplotype directory
		for (File dir : hap.listFiles()) {		// list files & directories in haplotype
			if (dir.isDirectory()) {	// if directory
				String fam = dir.getAbsolutePath();	// get absolute path to a family directory
				File glFile = new File(fam);	
				for (File file : glFile.listFiles()) {	// go through files in family directory
					if (file.getName().contains(category)) {
						SeparateHapAndAllele saa = new SeparateHapAndAllele(file.getAbsolutePath(), fsrc);
						try {	// generate table for each family
							BufferedWriter out = 
								new BufferedWriter(new FileWriter(fam + "/" +  dir.getName() + output));
							out.write("Family_ID,Sample_ID,Relation,");
							OrderedHLAgene hla = new OrderedHLAgene();
							for (String gene : hla.getOrderedGeneList()) {	// go through ordered gene
								out.write(gene + ",");
							}
							out.write("Validation,Ethnicity/Country\n");
							
							for (List<String> list : saa.getFamSamRelationHapCountry()) {																
								for (int count = 0; count < 2; count++) {	// two haplotypes
									for (int index = 0; index < 3; index++) {	// family, sample, relation
										out.write(list.get(index) + ",");										
									}
									for (String type : saa.getSampleAllele().get(list.get(1)).get(count)) {
										out.write(type + ",");
									}
									out.write(list.get(4) + ",");	// validation
									out.write(list.get(5) + "\n");	// count
								}							
							}								
							out.close();					
						} catch (IOException e) {
								// TODO Auto-generated catch block
							e.printStackTrace();
							System.err.println("Cannot write in " + output);
						}	
						
					}
				}
			}
		}
		
	}

}
