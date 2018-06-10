/**
 * DRB345 may be reported only once as hemizygous
 * For example family 23
 * This class fixes DRB345 alleles based on DRB1 linkage
 * HLA-DRB1*04:02:01+HLA-DRB1*04:05:01^HLA-DRB4*01:03:01:01 =>
 * HLA-DRB1*04:02:01+HLA-DRB1*04:05:01^HLA-DRB4*01:03:01:01+HLA-DRB4*01:03:01:01
 */
package workshop.haplotype.family.organize;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import workshop.haplotype.organize.EnterGeneMapForParent;
import workshop.haplotype.organize.file.ChooseElement;

/**
 * @author kazu
 * @version June 8 2016
 *
 */
public class OrganizeParentHapTypeDRBlinkage extends OrganizeParentHapType {
	// child as key for outer Map
	// parent as key for trio Internal Map
	protected Map<String, Map<String, Map<String, List<String>>>> parentHapTypeMapDRLinkageMap;

	/**
	 * @param ce
	 */
	public OrganizeParentHapTypeDRBlinkage(ChooseElement ce) {
		super(ce);
		// TODO Auto-generated constructor stub
		parentHapTypeMapDRLinkageMap = new HashMap<String, Map<String, Map<String, List<String>>>>();
		
		for (String child : childList) {
			Map<String, Map<String, List<String>>> parentMap= new HashMap<String, Map<String, List<String>>>();
			
			for (String parent : parentList) {				
				 List<String> drb1List = new ArrayList<String>();
				 Map<String, List<String>> geneMap = new HashMap<String, List<String>>();
				 for (String gene : geneListDRB345Combined.getList()) {
					 List<String> nonDRB345 = new ArrayList<String>();
					 if (parentHapTypeMap.get(child).get(parent).containsKey(gene)) {
						 for (String type : parentHapTypeMap.get(child).get(parent).get(gene)) {
							 if (!gene.contains("HLA-DRB345")) {
								 nonDRB345.add(type);
							 }
							 if (gene.equals("HLA-DRB1")) {
								 drb1List.add(type);	// populate DRB1 types
							 }
						 }
						 if (!nonDRB345.isEmpty()) {
							geneMap.put(gene, nonDRB345);
						 }	
						 						 
					 }								 
				 }	
				 
				 EnterGeneMapForParent egmp = new EnterGeneMapForParent(drb1List, child, parent, this);
				 if (egmp.getGeneMap().containsKey("HLA-DRB345")) {
					 geneMap.put("HLA-DRB345", egmp.getGeneMap().get("HLA-DRB345"));
				 }				
				 parentMap.put(parent, geneMap);
			 }				
			parentHapTypeMapDRLinkageMap.put(child, parentMap);				
		}		 
	}
	
	public Map<String, Map<String, Map<String, List<String>>>> getParentHapTypeMapDRLinkageMap() {
		return parentHapTypeMapDRLinkageMap;
	}

}
