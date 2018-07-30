/**
 * This program breaks up each line into element,
 * organize in List<List<String>>
 */
package workshop.haplotype.organize.file;

import java.util.ArrayList;
import java.util.List;

import workshop.haplotype.organize.file.removeLine.RemoveLine;

/**
 * @author Kazutoyo Osoegawa
 * @version April 11 2016
 *
 */
public class OrganizeElement {
	protected List<List<String>> listElement;
	
	public OrganizeElement(RemoveLine rel, String delimiter) {	//RemoveExtraLine
		listElement = new ArrayList<List<String>>();
		addElement(rel, delimiter);
				
	}
	
	public void addElement(RemoveLine rel, String delimiter) {
		for (String str : rel.getRevisedList()) {
			if (str.contains("\"")) {	// added here on April 11 2016 to remove quotation mark
				str = str.replaceAll("\"", "");
			}

			String[] tmp;
			tmp = str.split(delimiter);
			List<String> tmpList = new ArrayList<String>();
			for (int index = 0; index < tmp.length; index++) {
				if (tmp[index].equals("-")) {
					tmpList.add("");
				}
				else {
					tmpList.add(tmp[index]);
				}
			}
			listElement.add(tmpList);
		}
	}
	
	public List<List<String>> getListElement() {
		return listElement;
	}
		

}
