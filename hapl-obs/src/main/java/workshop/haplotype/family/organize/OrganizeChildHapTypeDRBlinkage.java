/**
 * This is to re-organize child haplotype DRB345
 * based on DRB1 - DRB345 linkages
 * nonDRB345 genes stay the same 
 */
package workshop.haplotype.family.organize;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import workshop.haplotype.organize.EnterGeneMapForChild;
import workshop.haplotype.organize.file.ChooseElement;

/**
 * @author Kazutoyo Osoegawa
 * @version June 17 2016
 *
 */
public class OrganizeChildHapTypeDRBlinkage extends OrganizeChildHapType {
	// child as a key
	// gene as a key for inner Map
	protected Map<String, Map<String, List<String>>> childHapTypeDRLinkageMap;

	/**
	 * @param ce
	 */
	public OrganizeChildHapTypeDRBlinkage(ChooseElement ce) {
		super(ce);
		// TODO Auto-generated constructor stub
		childHapTypeDRLinkageMap = new HashMap<String, Map<String, List<String>>>();
		
		for (String child : childList) {	// go through child list
			List<String> drb1List = new ArrayList<String>();		
			Map<String, List<String>> geneMap = new HashMap<String, List<String>>();
			for (String gene : this.geneListDRB345Combined.getList()) {		// go through gene
				List<String> nonDRB345 = new ArrayList<String>();
				if (childHapTypeMap.get(child).containsKey(gene)) {
					for (String type : childHapTypeMap.get(child).get(gene)) {
						if (!gene.contains("HLA-DRB345")) {
							nonDRB345.add(type);
						}
						if (gene.equals("HLA-DRB1")) {
							drb1List.add(type);	// populate DRB1 types
						}
						// during this cycle, ignore DRB345
					}
					if (!nonDRB345.isEmpty()) {
						geneMap.put(gene, nonDRB345);
					}					
				}				
			}			
			EnterGeneMapForChild egmc = new EnterGeneMapForChild(drb1List, child, this);
			Map<String, Boolean> validMap = new HashMap<String, Boolean>();
			if (childValid.containsKey(child)) {
				for (String gene : childValid.get(child).keySet()) {
					if (!gene.contains("HLA-DRB345")) {
						validMap.put(gene, childValid.get(child).get(gene));
					}
					else {	// DRB345
						validMap.put(gene, egmc.getValid());
					}
				}
			}
				
			if (!validMap.isEmpty()) {
				childValid.get(child).putAll(validMap);
				childValid.put(child, validMap);
			}			
			geneMap.putAll(egmc.getGeneMap());
							
			childHapTypeDRLinkageMap.put(child, geneMap);
		}

	}
	
	public Map<String, Map<String, List<String>>> getChildHapTypeDRLinkageMap() {
		return childHapTypeDRLinkageMap;
	}

}
