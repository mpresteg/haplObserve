/**
 * This program generates expected number of DRB345 counts
 * based on linkage specified in DRBgenes
 */
package workshop.haplotype.gene;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Kazutoyo Osoegawa
 * @version June 15 2016
 *
 */
public class ExpectedDRB345Count {
	private int drb345Count;
	private Map<String, String> expectedCombination;

	/**
	 * 
	 */
	public ExpectedDRB345Count(List<String> drb1List) {
		// TODO Auto-generated constructor stub
		drb345Count = 0;
		expectedCombination = new HashMap<String, String>();
		DRBgenes drb = new DRBgenes();
		for (String drb1Type : drb1List) {
			for (String drb345 : drb.getDrb0345List()) {
				for (String drb1 : drb.getDrb1MapList().get(drb345)) {
					if (drb1Type.contains(drb1)) {
						if (!drb345.equals("None")) {
							drb345Count++;
						}						
						expectedCombination.put(drb1Type, drb345);
					}
				}
			}
		}
	}
	
	public int getDrb345Count() {
		return drb345Count;
	}
	
	public Map<String, String> getExpectedCombination() {
		return expectedCombination;
	}


}
