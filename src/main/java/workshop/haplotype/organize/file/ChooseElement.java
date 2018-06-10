/**
 * This is originally called "ChooseElementAbstract"
 * This abstract program chooses elements that are required for typing report
 * 
 */
package workshop.haplotype.organize.file;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Kazutoyo Osoegawa
 * @version August 13 2016
 *
 */
public abstract class ChooseElement {
	protected List<List<String>> chosenElement;
	
	public ChooseElement(String filePath) {
		chosenElement = new ArrayList<List<String>>();
	}
	
	
	public List<List<String>> getChosenElement() {
		return chosenElement;
	}
	
	public void chooseElement(OrganizeElement orel) {		// this method has to be overridden
		for (List<String> tmpList : orel.getListElement()) {
			List<String> selected = new ArrayList<String>();
			for (int index = 0; index < tmpList.size(); index++) {
				if (!tmpList.get(index).isEmpty()) {	// this line was added on August 13 2016
					selected.add(tmpList.get(index));
				}
			}
			
			chosenElement.add(selected);
		}
	}

}
