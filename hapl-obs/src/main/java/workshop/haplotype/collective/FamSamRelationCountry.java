/**
 * Extract
 * Family
 * Sample
 * Relation
 * Ethnicity/Country
 * used
 */
package workshop.haplotype.collective;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import workshop.haplotype.organize.NonRedundantList;

/**
 * @author kazu
 * @version June 18 2018
 *
 */
public class FamSamRelationCountry {
	private List<List<String>> famSamRelationCountry;
	private Map<String, String> sampleFamily;
	private Map<String, String> sampleRelation;
	private Map<String, String> sampleCountry;
	private NonRedundantList countryList;

	/**
	 * 
	 */
	public FamSamRelationCountry(CombineGLFile combined) {
		// TODO Auto-generated constructor stub
		famSamRelationCountry = new ArrayList<List<String>>();
		sampleFamily = new HashMap<String, String>();
		sampleRelation = new HashMap<String, String>();
		sampleCountry = new HashMap<String, String>();
		countryList = new NonRedundantList();
		
		for (String line : combined.getCombined()) {
			List<String> tmp = new ArrayList<String>();
			String [] elements = line.split(",");
			tmp.add(elements[1]);		// family
			tmp.add(elements[2]);		// sample
			tmp.add(elements[3]);		// relation
			tmp.add(elements[5]);		// ethnicity/country
			famSamRelationCountry.add(tmp);
			sampleFamily.put(elements[2], elements[1]);
			sampleRelation.put(elements[2], elements[3]);
			sampleCountry.put(elements[2], elements[5]);
			countryList.addNonRedundantList(elements[5]);
		}
	}
	
	public List<List<String>> getFamSamCountry() {
		return famSamRelationCountry;
	}
	
	public Map<String, String> getSampleFamily() {
		return sampleFamily;
	}
	
	public Map<String, String> getSampleRelation() {
		return sampleRelation;
	}
	
	public Map<String, String> getSampleCountry() {
		return sampleCountry;
	}
	
	public NonRedundantList getCountryList () {
		return countryList;
	}

}
