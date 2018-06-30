/**
 * replacing perl script
 * This is to add ranking for haplotype
 * haplotypes list contains all unique haplotypes including recombinants
 * keys for hapCountRank contains only parents
 * These haplotype list may slightly be different when recombinant haplotype
 * are observed
 */
package workshop.haplotype.frequency.ranking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import workshop.haplotype.frequency.ms.SampleHap;
import workshop.haplotype.organize.NonRedundantList;

/**
 * @author kazu
 * @version April 4 2018
 *
 */
public class SampleHapCount {
	private SampleHap sh;
	// key: haplotype
	// parent count => index of 0
	// ranking => index of 1
	// family & sample count => index of 2,3
	private Map<String, List<Integer>> hapCountRank;
	private int parentHapCount;	// total parent hap count
	private Map<String, Double> frequency;	// haplotype frequency is calculated separately
	private int nextLastRank;	// to add ranking to 0 count haplotype

	/**
	 * @param filePath
	 * @param target
	 */
	public SampleHapCount( String filePath, String target ) {
		// TODO Auto-generated constructor stub
		sh = new SampleHap( filePath, target );	// re-sue this code from MS
		frequency = new HashMap<String, Double>();
		parentHapCount = 0;
		nextLastRank = 0;		// in case we need to add the last rank number
				
		List<Integer> intList = new ArrayList<Integer>();	// hold parent count				
		Map<String, Integer> hapCount = new HashMap<String, Integer>();	
		
		// capture only parent hap count
		Map<String, List<Integer>> hapCountList = new HashMap<String, List<Integer>>();
		
		for (String hap : sh.getHaplotypes().getList()) {	// go through unique haplotype
			if (!hap.contains("NT")) {
				int parentCount = 0;	// how many times haplotype appeared, homozygous => 2
				int childCount = 0;
				// hold list parentCount, familyCount, sampleCount
				List<Integer> countList = new ArrayList<Integer>();
				NonRedundantList family = new NonRedundantList();
				for (String sample : sh.getSampleList()) {
					if (!sh.getSampleRelation().get(sample).equals("child")) {	// parents
						for (String str : sh.getSampleHap().get(sample)) {	// go through list to count homozygous
							if (str.equals(hap)) {
								parentCount++;	// parent haplotype count
								parentHapCount++;
							}
						}
						
						if (sh.getSampleHap().get(sample).contains(hap)) {														
							family.addNonRedundantList(sh.getSampleFam().get(sample));	// add family					
						}
					}
					else {	// child
						if (sh.getSampleHap().get(sample).contains(hap)) {							
							for (String str : sh.getSampleHap().get(sample)) {	// go through list to count homozygous
								if (str.equals(hap)) {
									childCount++;
								}
							}																				
							family.addNonRedundantList(sh.getSampleFam().get(sample));	// add family
						}
					}
				}				
				if (parentCount != 0) {
					intList.add( parentCount );	// parental hap count
					
					countList.add( family.getList().size() );	// family count
					countList.add( parentCount + childCount );	// sample count
					hapCount.put( hap, parentCount );
					hapCountList.put( hap, countList );	// not including parent here
				}				
			}			
		}
		// ranking largest count to the lowest count
		Collections.sort(intList, Collections.reverseOrder());	// sore descending order
		nextLastRank = intList.size();	// to add rank for 0 incidence of small group
				
		hapCountRank = new HashMap<String, List<Integer>>();
		for (String hap : hapCountList.keySet()) {	// go through haplotype
			List<Integer> tmpList = new ArrayList<Integer>();	// list to hold values
			tmpList.add(hapCount.get(hap));	// parent count => index of 0
			tmpList.add(intList.indexOf(hapCount.get(hap)) + 1);	// ranking => index of 1
			tmpList.addAll(hapCountList.get(hap));	// family & sample count => index of 2,3
			
			hapCountRank.put(hap, tmpList);		
			double count = (double)hapCount.get(hap);
			double total = (double)parentHapCount;
			double freq = count / total;		// no conversion to %
			frequency.put(hap, freq);	// haplotype frequency
		}		
	}
	
	public SampleHap getSampleHap() {
		return sh;
	}
	
	public Map<String, List<Integer>> getHapCountRank() {
		return hapCountRank;
	}
	
	public Map<String, Double> getFrequency() {
		return frequency;
	}
	
	public int getParentHapCount() {
		return parentHapCount;
	}
	
	public int getNextLastRank() {
		return nextLastRank;
	}

}
