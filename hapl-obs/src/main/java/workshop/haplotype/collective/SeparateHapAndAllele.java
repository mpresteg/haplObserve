/**
 * 
 */
package workshop.haplotype.collective;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import workshop.haplotype.gene.IsLocusDRB345;
import workshop.haplotype.gene.OrderedHLAgene;

/**
 * @author kazu
 *
 */
public class SeparateHapAndAllele extends AddFamRelationCountry {
	private Map<String, List<List<String>>> sampleAllele;

	/**
	 * @param filePath
	 * @param fsrc
	 */
	public SeparateHapAndAllele(String filePath, FamSamRelationCountry fsrc) {
		super(filePath, fsrc);
		// TODO Auto-generated constructor stub
		sampleAllele = new HashMap<String, List<List<String>>>();
		OrderedHLAgene hla = new OrderedHLAgene();
		for (String sample : sampleType.keySet()) {
			String [] haplotype = sampleType.get(sample).split(",");
			String [] hap = haplotype[0].split("\\+");
			List<List<String>> listList = new ArrayList<List<String>>();
			for (String hapType : hap) {
				String [] alleles = hapType.split("~");
				List<String> list = new ArrayList<String>();
				for (String gene : hla.getOrderedGeneList()) {	// go through ordered gene
					int count = 0;
					for (String allele : alleles) {
						IsLocusDRB345 test = new IsLocusDRB345(allele);
						if (!test.getTestResult()) {
							if (allele.contains(gene)) {	// NOT DRB345
								list.add(allele);
								count++;
								break;
							}	
						}							
						else {	// DRB345
							if (gene.contains("DRB345")) {
								list.add(allele);
								count++;
								break;
							}
						}
					}
					if (count == 0) {
						list.add("");
					}

				}				
				listList.add(list);
			}
			sampleAllele.put(sample, listList);
		}
		
	}
	
	public Map<String, List<List<String>>> getSampleAllele() {
		return sampleAllele;
	}

}
