/**
 * generate possible pair wise combination
 * found a problem in f4996 (autfis) DPA1 & DPB1
 */
package workshop.haplotype.organize;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Kazutoyo Osoegawa
 * @version November 4 2017
 *
 */
public class CreatePairWiseCombination {
	// key number
	// value pairWiseList, but only possible combination
	private Map<Integer, List<List<String>>> pairWiseCombination; // possible combination

	/**
	 * @param gene1 (DPA1)
	 * @param gene2 (DPB1)
	 * key gene
	 * value list of alleles
	 * @param Map<String, List<String>> map
	 * 
	 */
	public CreatePairWiseCombination(String gene1, String gene2, Map<String, List<String>> map) {
		// TODO Auto-generated constructor stub
		pairWiseCombination = new HashMap<Integer, List<List<String>>>();	
		
		boolean g1homo = homozygousTest(map.get(gene1));
//		System.out.println(g1homo);
		boolean g2homo = homozygousTest(map.get(gene2));
//		System.out.println(g2homo);
		
		List<List<String>> pairWiseList = new LinkedList<List<String>>();	// list of all possible pairwise	
		
		if (g1homo && g2homo) { // both homozygous -> only one pairwise combination
			List<String> tmp = new ArrayList<String>();		// tmp list
			tmp.add(map.get(gene1).get(0));
			tmp.add(map.get(gene2).get(0));
			pairWiseList.add(tmp);
			pairWiseList.add(tmp);	// repeat for second allele
			pairWiseCombination.put(0, pairWiseList);
		}
		else if (g1homo && !g2homo) { // only gene1 homozygous, not gene2 -> two pairwise combination
			for (String allele2 : map.get(gene2)) {
				List<String> tmp = new ArrayList<String>();		// tmp list
				tmp.add(map.get(gene1).get(0));
				tmp.add(allele2);
				pairWiseList.add(tmp);
			}
			pairWiseCombination.put(0, pairWiseList);
			
		}
		else if (!g1homo && g2homo) { // only gene2 homozygous -> two pairwise combination
			for (String allele1 : map.get(gene1)) {
				List<String> tmp = new ArrayList<String>();		// tmp list
				tmp.add(allele1);
				tmp.add(map.get(gene2).get(0));
				pairWiseList.add(tmp);
			}
			pairWiseCombination.put(0, pairWiseList);			
		}
		else {	// both loci not homozygous
			// four pairwise comination
			for (String allele1 : map.get(gene1)) {		// go through list of alleles for gene1
				for (String allele2 : map.get(gene2)) {		// go through list of alleles for gene2
					List<String> tmp = new ArrayList<String>();		// tmp list
					// organize gene1 allele and gene2 allele in list in order
//					System.out.println(allele1);
//					System.out.println(allele2);
					tmp.add(allele1);	// add allele from gene1
					tmp.add(allele2);	// add allele from gene2		
					pairWiseList.add(tmp);
				}				
			}
			// create only possible combination
			// gene1-a1 ~ gene2-a1 -> [0]
			// gene1-a1 ~ gene2-a2 -> [1]
			// gene1-a2 ~ gene2-a1 -> [2]
			// gene1-a2 ~ gene2-a2 -> [3]		
			int [] numList1 = {0, 3};	// first possible combination 1 (see above)
			List<List<String>> listList1 = new ArrayList<List<String>>();
			for (int num : numList1) {
//				System.out.println(pairWiseList.get(num));
				listList1.add(pairWiseList.get(num));			
			}
			pairWiseCombination.put(0, listList1);
			
			int [] numList2 = {1, 2};	// second possible combination
			List<List<String>> listList2 = new ArrayList<List<String>>();
			for (int num : numList2) {
				listList2.add(pairWiseList.get(num));			
			}
			pairWiseCombination.put(1, listList2);			
		}		
		
	}
	
	public boolean homozygousTest(List<String> list) {
		boolean homo = false;
		Set<String> set1 = new HashSet<String>();
		for (String allele1 : list) {		// go through list of alleles for gene1
			set1.add(allele1);
		}
		if (set1.size() == 1) {
			homo = true;
		}
		return homo;
	}
	
	public Map<Integer, List<List<String>>> getPairWiseCombination() {
		return pairWiseCombination;
	}

}
