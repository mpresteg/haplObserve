/**
 * creates family list
 * capture sample family information
 * used
 */
package workshop.haplotype.collective;

import java.util.HashMap;
import java.util.Map;

import workshop.haplotype.organize.NonRedundantList;

/**
 * @author kazu
 * @version June 18 2018
 *
 */
public class ExtractFamily {
	private NonRedundantList familyList;
	private Map<String, String> sampleFamily;

	/**
	 * @param
	 * combined gl string file
	 */
	public ExtractFamily(CombineGLFile combined) {	// header is removed by CombinedGLDile
		// TODO Auto-generated constructor stub
		familyList = new NonRedundantList();
		sampleFamily = new HashMap<String, String>();
		for (String line : combined.getCombined()) {	// go through line
			String [] elements = line.split(",");
			familyList.addNonRedundantList(elements[1]);
			sampleFamily.put(elements[1], elements[0]);
		}
	}
	
	public NonRedundantList getFamilyList() {
		return familyList;
	}
	
	public Map<String, String> getSampleFamily() {
		return sampleFamily;
	}

}
