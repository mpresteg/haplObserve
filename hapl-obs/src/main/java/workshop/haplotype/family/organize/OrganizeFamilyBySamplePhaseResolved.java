/**
 * Resolve phase ambiguity
 */
package workshop.haplotype.family.organize;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import workshop.haplotype.family.ngs.PhaseAmbiguityResolver;
import workshop.haplotype.organize.file.ChooseElement;


/**
 * @author Kazutoyo Osoegawa
 * @version November 13 2017
 *
 */
public class OrganizeFamilyBySamplePhaseResolved extends OrganizeFamilyBySampleUnresolvedPhaseAmbiguity {
	

	/**
	 * @param ce
	 */
	public OrganizeFamilyBySamplePhaseResolved(ChooseElement ce) {
		super(ce);
		// TODO Auto-generated constructor stub	

		for (String gene : pahseAmbiguityGeneList.getList()) {
			// resolve phase ambiguity
			PhaseAmbiguityResolver rpat = new PhaseAmbiguityResolver(childList, 
					parentList, sampleGeneListList, gene);
			
			for (String child : childList) {
				Map<String, List<String>> cmapList = new HashMap<String, List<String>>();
				for (List<String> cList : rpat.getChildParentList().get(child).keySet()) {	//
					cmapList.putAll(hlaTypeBySample.get(child));	
					cmapList.put(gene, cList);	// replace
					hlaTypeBySample.put(child, cmapList);
					
					for (String parent : rpat.getChildParentList().get(child).get(cList).keySet()) {
						Map<String, List<String>> pmapList = new HashMap<String, List<String>>();
						pmapList.putAll(hlaTypeBySample.get(parent));	
						pmapList.put(gene, rpat.getChildParentList().get(child).get(cList).get(parent));
						hlaTypeBySample.put(parent, pmapList);						
					}
										
				}
								
			}
			
		}		
	}


}
