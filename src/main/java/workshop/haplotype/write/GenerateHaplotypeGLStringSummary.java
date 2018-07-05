/**
 * generate summary in GL String format
 */
package workshop.haplotype.write;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import workshop.haplotype.collective.AddFamRelationCountry;
import workshop.haplotype.collective.FamSamRelationCountry;

/**
 * @author kazu
 * @version June 26 2018
 *
 */
public class GenerateHaplotypeGLStringSummary {

	/**
	 * 
	 */
	public GenerateHaplotypeGLStringSummary(String hapPath, String outPath, String category, 
			FamSamRelationCountry fsrc, String today) {
		// TODO Auto-generated constructor stub
		File hap = new File(hapPath);
		
		String name = category;
		if (category.equals("Validation")) {
			name = "FAM";	// replace name
		}
		
		try {	
			BufferedWriter out = 
				new BufferedWriter(new FileWriter(outPath + name + "_Haplotype_Summary_GL_String_" + today + ".csv"));
			// add header
			// Requires header for pould
			// also see SampleHap
			// "Gl String" is very specific to pould, e.g., GL_String does not work
//			if (!name.equals("FAM")) {	// no header for this file
				out.write("Family_ID,Sample_ID,Relation,Gl String,Validation,Ethnicity/Country\n");
//			}		

			for (File dir : hap.listFiles()) {	// haplotype
				if (dir.isDirectory()) {
					String fam = dir.getAbsolutePath();	// family dir
					File glFile = new File(fam);
					for (File file : glFile.listFiles()) {	// go through file
						if (file.getName().contains(category)) {	// target file
							AddFamRelationCountry arc = new AddFamRelationCountry(file.getAbsolutePath(), fsrc);
							for (List<String> list : arc.getFamSamRelationHapCountry()) {
								int count = 0;
								for (String element : list) {
									out.write(element);
									if (count < list.size() -1) {
										out.write(",");
									}
									count++;
								}
								out.write("\n");							
							}	
						}
					}
				}
			}
			
			out.close();					
		} catch (IOException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("Cannot write in " + hapPath + category + "_Haplotype_Summary_" + today + ".csv");
		}
	}

}
