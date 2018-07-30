/**
 * Extract child Haplotype information
 * This is the third layer of the class above OrganizeBySample, 
 * OrganizeFamilyBySample and OrganizeTrioBySample
 * This is the most complicated class
 * 
 * 
 */
package workshop.haplotype.family.organize;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import workshop.haplotype.family.ngs.IdenticalHLACountCheck;
import workshop.haplotype.family.ngs.ReviewNoMatch;
import workshop.haplotype.family.ngs.SortHapAllele;
import workshop.haplotype.gene.MakeAmbiguityString;
import workshop.haplotype.organize.file.ChooseElement;

/**
 * @author Kazutoyo Osoegawa
 * @version November 15 2017
 *
 */
public class OrganizeChildHapType extends OrganizeTrioBySample {
	// child as a key
	// gene as a key for inner Map
	protected Map<String, Map<String, List<String>>>childHapTypeMap;
	protected Map<String, List<String>> childDRB345TypeList;	// added later
	protected Map<String, Map<String, Boolean>> childValid;


	/**
	 * @param ce
	 */
	public OrganizeChildHapType(ChooseElement ce) {
		super(ce);
		// TODO Auto-generated constructor stub		
		childHapTypeMap = new HashMap<String, Map<String, List<String>>>();
		childDRB345TypeList = new HashMap<String, List<String>>();	//
		childValid = new HashMap<String, Map<String, Boolean>>();
		
		for (String child : childList) {	// go through child sample ID, see OrganizeTrioBySample
			Map<String, List<String>> cmapList = new HashMap<String, List<String>>();
			List<String> drb345TypeList = new ArrayList<String>();	//
			Map<String, Boolean> validMap = new HashMap<String, Boolean>();
			
			for (String gene : this.geneListDRB345Combined.getList()) {	// go through gene			
				List<String> typeList1 = new LinkedList<String>();	// accumulate & order parent1 type
				List<String> typeList2 = new LinkedList<String>();	// accumulate & order parent2 type	
				
				if (trioMap.containsKey(child)) {
					if (trioMap.get(child).get(child).containsKey(gene)) {// go through a child type							
						for (String childType : trioMap.get(child).get(child).get(gene)) {	// go through two alleles						
							// obtain child allele that is derived from parent1
							enterList(child, parentList.get(0), gene, childType, typeList1);							
							// obtain child allele that is derived from parent2
							enterList(child, parentList.get(1), gene, childType, typeList2);
							// these two steps allow to capture one reported DRB345 allele as two
							// alleles when both parents have the same							
						}
						// "" were added twice when there are two DRB345 alleles
						if (gene.equals("HLA-DRB345")) {	// if DRB345							
							if (typeList1.size() > 1) {		// DRB345 cannot be more than 1
								if (typeList1.contains("")) {
									typeList1.remove("");
								}
							}
							if (typeList2.size() > 1) {
								if (typeList2.contains("")) {
									typeList2.remove("");
								}
							}
						}					
						
						List<String> tmpList = new LinkedList<String>();	// use LinkedList
						// build haplotype for child, if there is no perfect match, 
						// HLA-DRB1*15:01:01:01 vs HLA-DRB1*15:01:01:03 report as NoMatch
						IdenticalHLACountCheck check =  new IdenticalHLACountCheck(typeList1, typeList2);
						// deal with NoMatch				
						tmpList.addAll(check.getList());	// enter allele1 & allele2		
				
						List<String> linkedList1 = new LinkedList<String>();						
						if (tmpList.get(0).contains("Match")) {	// no match in allele1, use Match instead of NoMatch
							String str = tmpList.remove(0);		// extract allele1
							if (trioMap.get(child).get(parentList.get(0)).containsKey(gene)) {
								ReviewNoMatch review = new ReviewNoMatch(str,
									trioMap.get(child).get(child).get(gene), 
									trioMap.get(child).get(parentList.get(0)).get(gene));
								if (!review.getValid()) {	// false
									validMap.put(gene, review.getValid());
								}
								
								linkedList1.addAll(review.getLinkedList());	// this line was important for FAM13							
								// if No Two Filed Match found
								if (review.getReviewedType().contains("NoMatch")) {
									if (!gene.contains("DRB345")) {
										tmpList.add(0, gene + "*" + review.getReviewedType());
									}
									else {	// DRB345
										tmpList.add(0, "");
									}
								}
								else {
									tmpList.add(0, review.getReviewedType());
								}									
							}																					
						}	
		
						List<String> linkedList2 = new LinkedList<String>();
						if (tmpList.get(1).contains("Match")) {	// no match in allele2
							String str = tmpList.remove(1);	
//							System.out.println(str);
							if (trioMap.get(child).get(parentList.get(1)).containsKey(gene)) {
								ReviewNoMatch review = new ReviewNoMatch(str,
										trioMap.get(child).get(child).get(gene), 
										trioMap.get(child).get(parentList.get(1)).get(gene));
								linkedList2.addAll(review.getLinkedList());
								
								if ((!review.getValid()) && (validMap.isEmpty())) {
									validMap.put(gene, review.getValid());
								}
								// remove elements that overlaps with linkedList1
								for (String type : linkedList1) {
									if (linkedList2.contains(type)) {
										linkedList2.remove(type);
									}
								}
								
								if (review.getReviewedType().contains("NoMatch")) {
									if (!gene.contains("DRB345")) {
										tmpList.add(1, gene + "*" + review.getReviewedType());
									}									
									else {	// DRB345
										tmpList.add(1, "");
									}
								}
								else {
									MakeAmbiguityString ambiguity = new MakeAmbiguityString(linkedList2);
									tmpList.add(1, ambiguity.getAmbiguity());										
								}

							}
						}		

						cmapList.put(gene, tmpList);
						if (gene.contains("HLA-DRB345")) {	// DRB345
							drb345TypeList.addAll(tmpList);
						}			//			
					}		
				}				
				if (!validMap.containsKey(gene)) {
					validMap.put(gene, true);
				}
			}
			childHapTypeMap.put(child, cmapList);	
			if (!drb345TypeList.isEmpty()) {
				childDRB345TypeList.put(child, drb345TypeList);	//
			}

			if (!validMap.isEmpty()) {
				childValid.put(child, validMap);
			}
			
		}		
	}
	
	public void enterList (String child, String parent, String gene, String childType, List<String> typeList) {
		if (trioMap.get(child).get(parent).containsKey(gene)) {

			SortHapAllele sha = new SortHapAllele(this, gene, parent, childType);
			if (sha.getFirstAllele().length() > 0) {
				typeList.add(sha.getFirstAllele());	// populate first allele
			}
			
		}
		else {	// no match
			typeList.add("");
		}
	}
	
	public Map<String, Map<String, List<String>>> getChildHapTypeMap() {
		return childHapTypeMap;
	}
	
	public Map<String, List<String>> getChildDRB345TypeList() {
		return childDRB345TypeList;
	}	
	
	public Map<String, Map<String, Boolean>> getChildValid() {
		return childValid;
	}

}
