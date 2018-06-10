/**
 * This class was added on November 13 2017
 * To prepare resolving phase ambiguity
 * this class deals with loci that contain phase ambiguity
 */
package workshop.haplotype.family.organize;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import workshop.haplotype.ambiguity.PrioritizePhaseAmbiguity;
import workshop.haplotype.family.ngs.NormalizePhaseAmbiguity;
import workshop.haplotype.organize.file.ChooseElement;

/**
 * @author Kazutoyo Osoegawa
 * @version November 13 2017
 *
 */
public class OrganizeFamilyBySampleUnresolvedPhaseAmbiguity extends
		OrganizeFamilyBySample {
	// sample
	// gene
	// type list list
	protected Map<String, Map<String, List<List<String>>>> sampleGeneListList;

	/**
	 * @param ce
	 */
	public OrganizeFamilyBySampleUnresolvedPhaseAmbiguity(ChooseElement ce) {
		super(ce);
		// TODO Auto-generated constructor stub
		sampleGeneListList = new HashMap<String, Map<String, List<List<String>>>>();		
		for (String sample : sampleRelation.keySet()) {	// go through sample

			Map<String, List<List<String>>> mapSetOfList = new HashMap<String, List<List<String>>>();
			// go through genes that contains phase ambiguity
			for (String gene : pahseAmbiguityGeneList.getList()) {	
				if (sampleGeneGLType.get(sample).containsKey(gene)) {
					List<List<String>> listOfList = new ArrayList<List<String>>();
					for (String str : sampleGeneGLType.get(sample).get(gene)) {	// go through everything
//						System.out.println(str);
						String converted = "";
						NormalizePhaseAmbiguity convert = new NormalizePhaseAmbiguity(str);
						if (convert.getResolved()) {	// resolved
							converted = convert.getGlString();
						}
						else {	// not resolved
							PrioritizePhaseAmbiguity ppa = new PrioritizePhaseAmbiguity(convert.getGlString());
							converted = ppa.getOrderedAmbiguity();
//							System.out.println(converted);
						}
						
						if (converted.contains("|")) {
							String [] list = converted.split("\\|");	// back slash was required
							for (String gl : list) {	
								String [] alleleList = gl.split("\\+");	// separate	
								List<String> typeList = new ArrayList<String>();
								for (String allele : alleleList) {
									amList.convertToAmbiguityString(allele);
	//								System.out.println(amList.getConverted());
									typeList.add(amList.getConverted());	// nonDRB345 list	
								}
								listOfList.add(typeList);
	//							System.out.println(typeList);
							}
						}
					}
					
					if (listOfList.isEmpty()) {		// empty => no phase ambiguity
						listOfList.add(hlaTypeBySample.get(sample).get(gene));
					}	// listOfList is not empty => phase ambiguity
					mapSetOfList.put(gene, listOfList);					
				}
			}
			
			if (!mapSetOfList.isEmpty()) {
				sampleGeneListList.put(sample, mapSetOfList);
			}			
		}
	}
	
	public Map<String, Map<String, List<List<String>>>> getSampleGeneListList() {
		return sampleGeneListList;
	}

}
