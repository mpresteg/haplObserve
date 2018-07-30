/**
 * Trio is defined by a child as a key
 * This is the second layer of the class above OrganizeBySample and 
 * OrganizeFamilyBySample
 */
package workshop.haplotype.family.organize;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import workshop.haplotype.organize.file.ChooseElement;

/**
 * @author Kazutoyo Osoegawa
 * @version April 29 2016
 *
 */
public class OrganizeTrioBySample extends  OrganizeFamilyBySamplePhaseResolved{	// OrganizeFamilyBySample, 
	// child as key for outer Map
	// sample ID as key for trio Internal Map
	protected Map<String, Map<String, Map<String, List<String>>>> trioMap;


	/**
	 * @param ce
	 */
	public OrganizeTrioBySample(ChooseElement ce) {
		super(ce);
		// TODO Auto-generated constructor stub
		trioMap = new HashMap<String, Map<String, Map<String, List<String>>>>();
		
		for (String child : childList) {
			Map<String, Map<String, List<String>>> map = new HashMap<String, Map<String, List<String>>>();
			map.put(child, this.hlaTypeBySample.get(child));
			for (String parent : parentList) {
				map.put(parent, this.hlaTypeBySample.get(parent));
			}
			trioMap.put(child, map);
		}
		
		if (parentList.size() != 2) {	// check both parents present
			System.err.println("The number of parents has to be 2!!!");
			System.err.println("Please check your list!");
			System.exit(1);
		}
		
	}
	
	public Map<String, Map<String, Map<String, List<String>>>> getTrioMap() {
		return trioMap;
	}
	

}
