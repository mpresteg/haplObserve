/**
 * This class is to obtain linkage information of
 * DRB1 - DRB345
 * only the first field information is used
 * added NT option
 */
package workshop.haplotype.gene;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Kazutoyo Osoegawa
 * @version October 26 2017
 *
 */
public class DRBgenes extends HLAgene {
	protected List<String> drb0345List;
	protected Map<String, List<String>> drb1MapList;
	

	/**
	 * 
	 */
	public DRBgenes() {
		// TODO Auto-generated constructor stub
		drb0345List = new ArrayList<String>();
		String dr50 = "None";
		drb0345List.add(dr50);
		String dr51 = "HLA-DRB5";	// DR51 antigen
		drb0345List.add(dr51);
		String dr52 = "HLA-DRB3";	// DR52 antigen
		drb0345List.add(dr52);
		String dr53 = "HLA-DRB4";	// DR53 antigen
		drb0345List.add(dr53);
		
		drb1MapList = new TreeMap<String, List<String>>();
		List<String> drb0List = new ArrayList<String>();
		String [] drb0 = {"HLA-DRB1*01", "HLA-DRB1*08", "HLA-DRB1*10", "HLA-DRB1*NT"};
		addList(drb0List, drb0);
		drb1MapList.put(dr50, drb0List);
		
		List<String> drb3List = new ArrayList<String>();
		String [] drb3 = {"HLA-DRB1*03", "HLA-DRB1*05", "HLA-DRB1*06", 
				"HLA-DRB1*11", "HLA-DRB1*12", "HLA-DRB1*13", "HLA-DRB1*14", "HLA-DRB1*17", "HLA-DRB1*18"};
		addList(drb3List, drb3);
		drb1MapList.put(dr52, drb3List);
		
		List<String> drb4List = new ArrayList<String>();
		String [] drb4 = {"HLA-DRB1*04", "HLA-DRB1*07", "HLA-DRB1*09"};
		addList(drb4List, drb4);
		drb1MapList.put(dr53, drb4List);
		
		List<String> drb5List = new ArrayList<String>();
		String [] drb5 = {"HLA-DRB1*02", "HLA-DRB1*15", "HLA-DRB1*16"};
		addList(drb5List, drb5);
		drb1MapList.put(dr51, drb5List);

	}
	
	public void addList(List<String> list1, String [] list2) {
		for (String str : list2) {
			list1.add(str);
		}
	}
	
	public List<String> getDrb0345List() {
		return drb0345List;
	}
	
	public Map<String, List<String>> getDrb1MapList() {
		return drb1MapList;
	}

}
