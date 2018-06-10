/**
 * Generate nonredundant list
 */
package workshop.haplotype.organize;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author kazutoyo
 * @version March 21 2016
 *
 */
public class NonRedundantList {
	private List<String> list;
	private Set<String> set;

	/**
	 * 
	 */
	public NonRedundantList() {
		// TODO Auto-generated constructor stub
		list = new ArrayList<String>();
		set = new HashSet<String>();
	}
	
	public void addNonRedundantList(String element) {
		if (!set.contains(element)) { // fixed bug here on January 29 2016
			list.add(element);
			set.add(element);	
		}			
	}
	
	public List<String> getList() {
		return list;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		NonRedundantList non2 = new NonRedundantList();
		String [] list2 = {"", "DRB1", "DRB1", "DRB3", "DRB4", "DRB4", "DRB5"};
		for (String str : list2) {
			non2.addNonRedundantList(str);
		}
		for (String str : non2.getList()) {
			System.out.println(str);
		}
		
	}

}
