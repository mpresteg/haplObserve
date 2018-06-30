/**
 * Extract
 * Family
 * Sample
 * Relation
 * Genotype
 * used
 */
package workshop.haplotype.collective;

import java.util.ArrayList;
import java.util.List;

import workshop.haplotype.collective.CombineGLFile;
import workshop.haplotype.gene.HLAgene;
import workshop.haplotype.gene.IsLocusDRB345;
import workshop.haplotype.organize.NonRedundantList;

/**
 * @author kazu
 * @version June 12 2018
 *
 */
public class FamSamRelationGenotype {
	private List<List<String>> famSamRelationGenotype;

	/**
	 * @param combined
	 */
	public FamSamRelationGenotype( CombineGLFile combined ) {
		// TODO Auto-generated constructor stub
		famSamRelationGenotype = new ArrayList<List<String>>();
		HLAgene hlaGene = new HLAgene();
		
		for (String line : combined.getCombined()) {	// go through each line
			List<String> removedLabcode = new ArrayList<String>();
			String [] elements = line.split(",");
			for (int index = 1; index < 4; index++) {	// family, sample, relation				
				removedLabcode.add(elements[index]);
			}
			String [] genotype = elements[4].split("\\^");	// GL String
			
			for (String gene : hlaGene.getGeneList()) {
				IsLocusDRB345 drb345 = new IsLocusDRB345(gene);
				int count = 0;
				for (String type : genotype) {
					if (type.contains(gene)) {
						if (type.equals("HLA-A*02:01:01:01+HLA-A*02:01:01:01+HLA-A*02:01:01:02L+HLA-A*02:01:01:02L")) {
							type = "HLA-A*02:01:01:01+HLA-A*02:01:01:01|HLA-A*02:01:01:01+HLA-A*02:01:01:02L|HLA-A*02:01:01:02L+HLA-A*02:01:01:02L";
						}
						
						if (!drb345.getTestResult()) {
							removedLabcode.add(type);
						}
						else {	// DRB345
							if (type.contains("+")) {
								NonRedundantList non = new NonRedundantList();
								String [] types = type.split("\\+");
								for (String str : types) {
									non.addNonRedundantList(str);
								}
								if (non.getList().size() == 1) {
									type = non.getList().get(0);
								}								
							}
							removedLabcode.add(type);	// HERE							
						}						
						count++;
					}
				}
				if (count == 0) {
					removedLabcode.add("");
				}
			}
			famSamRelationGenotype.add(removedLabcode);
		}
	}
	
	public List<List<String>> getFamSamRelationGenotype() {
		return famSamRelationGenotype;
	}


}
