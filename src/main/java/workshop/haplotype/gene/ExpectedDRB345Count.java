/**
 * This program generates expected number of DRB345 counts
 * based on linkage specified in DRBgenes
 */
package workshop.haplotype.gene;

import java.util.ArrayList;
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

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<String>();
		list.add("HLA-DRB1*07:01:01:01");
		list.add("HLA-DRB1*04:04:01");
		ExpectedDRB345Count exp = new ExpectedDRB345Count(list);
		System.out.println(exp.getDrb345Count());
		for (String drb1Type : list) {
			System.out.println(drb1Type);
			System.out.println(exp.getExpectedCombination().get(drb1Type));
			System.out.println();
		}
		System.out.println();
		
		List<String> list1 = new ArrayList<String>();	
		list1.add("HLA-DRB1*04:04:01");
		list1.add("HLA-DRB1*08:02:01");
		ExpectedDRB345Count exp1 = new ExpectedDRB345Count(list1);
		System.out.println(exp1.getDrb345Count());
		for (String drb1Type : list1) {
			System.out.println(drb1Type);
			System.out.println(exp1.getExpectedCombination().get(drb1Type));
			System.out.println();
		}

	}

}
