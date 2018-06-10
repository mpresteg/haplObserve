/**
 * This is the third layer of the class above OrganizeBySample
 * 
 * Known ambiguities were added
 * 
 * Phase ambiguities had been manually removed in the past
 * Capture phase ambiguity and resolve,check DPB1*13:01:01/DPB1*107:01
 * 
 */
package workshop.haplotype.family.organize;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import workshop.haplotype.ambiguity.AmbiguityList;
import workshop.haplotype.ambiguity.FixAmbiguityNumericalOrder;
import workshop.haplotype.family.ngs.NormalizePhaseAmbiguity;
import workshop.haplotype.gene.IsLocusDRB345;
import workshop.haplotype.organize.file.ChooseElement;


/**
 * @author Kazutoyo Osoegawa
 * @version November 13 2017
 *
 */
public class OrganizeFamilyBySample extends OrganizeFamily {	
	// sample
	// gene
	// list of allele
	protected Map<String, Map<String, List<String>>> sampleGeneGLType; // to be inherited
	protected AmbiguityList amList;	// to be inherited	

	/**
	 * @param ce
	 */
	public OrganizeFamilyBySample(ChooseElement ce) {
		super(ce);
		// TODO Auto-generated constructor stub
		sampleGeneGLType = new HashMap<String, Map<String, List<String>>>();
		amList = new AmbiguityList();	// June 29 2016
		for (String sample : sampleList) {	// go through sample
			List<String> drb345List = new ArrayList<String>();	// to put all DRB345 together in the same list	
			Map<String, List<String>> mapList = new HashMap<String, List<String>>();
			Map<String, List<String>> allMapList = new HashMap<String, List<String>>();
			
			for (String gene : hlaGene.getGeneList()) {	// go through gene
				IsLocusDRB345 drb345 = new IsLocusDRB345(gene);	// check DRB345
				String type = "";
				List<String> typeList = new ArrayList<String>();
				List<String> allTypeList = new ArrayList<String>();	// container for everything
				if (sampleGeneOrgGLType.get(sample).containsKey(gene)) {	// check presence of the gene
									
					type = sampleGeneOrgGLType.get(sample).get(gene);
					allTypeList.add(type);	// add everything
					// deal with phase ambiguity => convert genotype ambiguity to allele ambiguity
					// normalize phase ambiguity
					NormalizePhaseAmbiguity convert = new NormalizePhaseAmbiguity(type);	// check here
					if (!convert.getResolved())	 {	// phase ambiguity
						// capture gene that STILL contains phase ambiguity
						pahseAmbiguityGeneList.addNonRedundantList(gene);	
					}
					else {	// NO phase ambiguity
						String [] list = convert.getGlString().split("\\+");	// separate, use convert, not type						
						if (drb345.getTestResult()) {	// if DRB345
							drb345List.addAll(addList(list));			// addList function below					
						}
						else {		// non-DRB345		
							typeList.addAll(addList(list));	// add ambiguity here
						}
					}
				}
				if (!drb345.getTestResult()) {	// populate nonDRB345 loci or genes
					geneListDRB345Combined.addNonRedundantList(gene);
				}
				// typeList was added only when no phase ambiguity
				if (!typeList.isEmpty()) {	// if nonDRB345 list is not empty
					mapList.put(gene, typeList);	
				}		
				
				if (!allTypeList.isEmpty()) {	// this contains everything
					allMapList.put(gene, allTypeList);
				}				
			}
			if (!drb345List.isEmpty()) {
				mapList.put("HLA-DRB345", drb345List);	// key, HLA-DRB345, value DRB345 list
			}				
			geneListDRB345Combined.addNonRedundantList("HLA-DRB345");	// add HLA-DRB345 to the end
			if (!mapList.isEmpty()) {	// non-phase ambiguity
				hlaTypeBySample.put(sample, mapList);
			}	
			if (!allMapList.isEmpty()) {	// everything
				sampleGeneGLType.put(sample, allMapList);
			}			
		}
	}
	
	// addList function
	public List<String> addList(String[] list) {	//October 18 2016
		List<String> returnList = new ArrayList<String>();
		for (String str : list) {
			if (str.contains("/")) {	// contains ambiguity
				// fix the order of alleles if not ordered by numerically
				FixAmbiguityNumericalOrder fano = new FixAmbiguityNumericalOrder(str);
				amList.convertToAmbiguityString(fano.getFixedGl());
			}
			else {
				amList.convertToAmbiguityString(str);
			}			
			returnList.add(amList.getConverted());								
		}		
		return returnList;
	}
	
	public Map<String, Map<String, List<String>>> getSampleGeneGLType() {
		return sampleGeneGLType;
	}

}
