/**
 * This program is developed to order haplotypes based on the ranking
 */
package workshop.haplotype.frequency.ranking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author kazu
 * @version April 5 2018
 *
 */
public class OrderbyRanking {
	private List<String> rankedHapList;	// ranked haplotype list

	/**
	 * 
	 */
	public OrderbyRanking(SampleHapCount shc) {
		// TODO Auto-generated constructor stub
		List<Integer> intList = new ArrayList<Integer>();
		Set<Integer> tmp = new HashSet<Integer>();	// for non redundant number
		for (String hap : shc.getHapCountRank().keySet()) {
			if (!tmp.contains(shc.getHapCountRank().get(hap).get(1))) {	// ranking => index of 1
				intList.add(shc.getHapCountRank().get(hap).get(1));	// capture only ranking
				tmp.add(shc.getHapCountRank().get(hap).get(1));
			}			
		}
		Collections.sort(intList);	// sore ascending order
				
		rankedHapList = new ArrayList<String>();
		List<String> hapList = new LinkedList<String>();
		for (String hap : shc.getHapCountRank().keySet()) {
			hapList.add(hap);		
		}
		
		for (Integer num : intList) {	// go through rank			
			for (String hap : hapList) {	
				// IMPORTANT
				// NEVER use == operator to compare Integer
				// need to use equals() function
				if (shc.getHapCountRank().get(hap).get(1).equals(num)) {
					rankedHapList.add(hap);
				}				
			}
			hapList.removeAll(rankedHapList);	// remove as it goes
			
		}		
	}
	
	public List<String> getRankedHapList() {
		return rankedHapList;
	}
	

}
