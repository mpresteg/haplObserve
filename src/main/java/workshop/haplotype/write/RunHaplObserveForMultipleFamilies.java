/**
 * execute HaplObserve family by family
 */
package workshop.haplotype.write;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import workshop.haplotype.collective.ExtractFamily;
import workshop.haplotype.collective.FamSamRelationGenotype;
import workshop.haplotype.gene.HLAgene;
import workshop.haplotype.write.GenerateHaplotypeLog;
import workshop.haplotype.write.GenerateSampleHaplotype;
import workshop.haplotype.write.GenerateSampleSingleAlleleHaplotype;
import workshop.haplotype.write.GenerateSampleTwoFieldHaplotype;
import workshop.haplotype.collective.CombineGLFile;

/**
 * @author kazu
 * @version June 18 2018
 *
 */
public class RunHaplObserveForMultipleFamilies {

	/**
	 * @param collective dir
	 * @param combined
	 * 
	 */
	public RunHaplObserveForMultipleFamilies(String collective, CombineGLFile combined , 
			String famcsv, String haplotype, String today) {
		// TODO Auto-generated constructor stub
		ExtractFamily ef = new ExtractFamily(combined);
		FamSamRelationGenotype fsrg = new FamSamRelationGenotype(combined);
		new File(famcsv).mkdir();	// make FAMCSV dir
		new File(haplotype).mkdir();	// make haplotype dir
		
		HLAgene hlaGene = new HLAgene();		
		for (String family : ef.getFamilyList().getList()) {	// go through family
			
			try {	// generate input csv file for HaplObserve
				BufferedWriter out = 
					new BufferedWriter(new FileWriter(famcsv + family + ".csv"));
				// write header
				out.write("Sample,Relationship,");				
				int count = 0;
				for (String gene : hlaGene.getGeneList()) {
					out.write(gene);
					if (count < hlaGene.getGeneList().size() - 1) {
						out.write(",");
					}
					count++;					
				}
				out.write("\n");
				
				for (List<String> line : fsrg.getFamSamRelationGenotype()) {
					if (line.get(0).equals(family)) {
						for (int index = 1; index < line.size(); index++) {
							if (index < line.size() -1) {
								out.write(line.get(index) + ",");
							}
							else {
								out.write(line.get(index));
							}							
						}
						out.write("\n");
					}
				}				
				out.close();					
			} catch (IOException e) {
					// TODO Auto-generated catch block
				e.printStackTrace();
				System.err.println(famcsv + family + ".csv");
			}			
			new File(haplotype + family).mkdir();	// make family directory
			
			String input = famcsv + family + ".csv";			
			// check for manually edited haplotype file in update directory
			File validation = new File(collective + "update/" + family + "_Validation.txt");
			if (validation.exists()) {		// copy manually edited file		
				Path source = FileSystems.getDefault().getPath(collective, "update", 
						family + "_Validation.txt");
				Path target = FileSystems.getDefault().getPath(collective, "haplotype", 
						family, family + "_Validation.txt");
				try {
					Files.copy(source, target);					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
				new GenerateSampleHaplotype(input,
						haplotype + family + "/" + family + "_Validation_" + today + ".txt");
			}			
			
			new GenerateHaplotypeLog(input, haplotype + family + "/" + family + "_Log_" + today + ".log");
			new GenerateSampleSingleAlleleHaplotype(input, 
					haplotype + family + "/" + family + "_SingleAlleleHapType_" + today + ".txt");

			new GenerateSampleTwoFieldHaplotype(input, 
					haplotype + family + "/" + family + "_TwoFieldAlleleHapType_" + today + ".txt");				
		}
	}

}
