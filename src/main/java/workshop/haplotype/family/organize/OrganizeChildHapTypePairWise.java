/**
 * Two locus pairwise correction
 */
package workshop.haplotype.family.organize;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import workshop.haplotype.organize.ChooseChildPairWiseList;
import workshop.haplotype.organize.file.ChooseElement;

/**
 * @author Kazutoyo Osoegawa
 * @version November 21 2017
 *
 */
public class OrganizeChildHapTypePairWise extends OrganizeChildHapTypeDRBlinkage {
// mistake should use childHapTypeDRLinkageMap
	/**
	 * @param ce
	 */
	public OrganizeChildHapTypePairWise(ChooseElement ce) {
		super(ce);
		// TODO Auto-generated constructor stub
		String [] dp = {"HLA-DPA1", "HLA-DPB1"};
		pairWise(dp);
		
		String [] cb = {"HLA-B", "HLA-C"};
		pairWise(cb);
		
		String [] ba = {"HLA-C", "HLA-A"};
		pairWise(ba);
	}
	
	void pairWise(String [] loci) {
		boolean proceed = true;
		for (String sample : sampleList) {	// go through sample
			for (int index = 0; index < loci.length ; index ++) {
				if (hlaTypeBySample.get(sample).containsKey(loci[index])) {
					for (String nt : hlaTypeBySample.get(sample).get(loci[index])) {
						if (nt.contains("NT")) {
							proceed = false;
						}
					}					
				}
			}
		}
		if (proceed) {
			ChooseChildPairWiseList cpw = new ChooseChildPairWiseList(loci[0], loci[1], this);
			if (cpw.getPairWiseTest()) {	// pair wise test true, false lease as it is
				for (String child : childList) {	// go through child list
					if ((childHapTypeDRLinkageMap.get(child).containsKey(loci[0])) &&
							(childHapTypeDRLinkageMap.get(child).containsKey(loci[1]))) {	// both dpa1 and dpb1 genes are tested
						for (List<String> list : cpw.getChildPairWiseList().get(child)) {	// compare list one by one						
							if (childHapTypeDRLinkageMap.get(child).get(loci[0]).get(0)
									.equals(list.get(0))) {		// dpa1 allele is same
								if (!(childHapTypeDRLinkageMap.get(child).get(loci[1]).get(0).equals(list.get(1)))) {
									// dpq1 is same, but dpb1 is different => change dpb1
									
									if (!(childHapTypeDRLinkageMap.get(child).get(loci[0]).get(0)	// dpa1 not homozygous
											.equals(childHapTypeDRLinkageMap.get(child).get(loci[0]).get(1)))) {
										// swap
										List<String> tmp = new ArrayList<String>();
										tmp.add(childHapTypeDRLinkageMap.get(child).get(loci[1]).get(1));
										tmp.add(childHapTypeDRLinkageMap.get(child).get(loci[1]).get(0));
										Map<String, List<String>> mapList = new HashMap<String, List<String>>();
										mapList.putAll(childHapTypeDRLinkageMap.get(child));
										mapList.put(loci[1], tmp);	// replace
										childHapTypeDRLinkageMap.put(child, mapList);
										
									}																
								}
//								else { }	//do nothing	
									
							}	// second dpa1 allele is same as the first pair wise combination
							else if (childHapTypeDRLinkageMap.get(child).get(loci[0]).get(1)
									.equals(list.get(0))) {
								if (!(childHapTypeDRLinkageMap.get(child).get(loci[1]).get(1).equals(list.get(1)))) {
									// dpq1 is same, but dpb1 is different => change dpb1
									if (!(childHapTypeDRLinkageMap.get(child).get(loci[0]).get(0)	// dpa1 not homozygous
											.equals(childHapTypeDRLinkageMap.get(child).get(loci[0]).get(1)))) {
										// swap
										List<String> tmp = new ArrayList<String>();
										tmp.add(childHapTypeDRLinkageMap.get(child).get(loci[1]).get(1));
										tmp.add(childHapTypeDRLinkageMap.get(child).get(loci[1]).get(0));
										Map<String, List<String>> mapList = new HashMap<String, List<String>>();
										mapList.putAll(childHapTypeDRLinkageMap.get(child));
										mapList.put(loci[1], tmp);	// replace
										childHapTypeDRLinkageMap.put(child, mapList);
									}									
								}
//								else {	}	// dpb1 is same => good, do nothing	
							}							
						}										
						
					}				
					else {	// gene is not tested, then skip
						break;
					}				
				}
			}
		}
		
	}

}
