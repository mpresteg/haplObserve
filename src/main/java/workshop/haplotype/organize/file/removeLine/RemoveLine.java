/**
 * This is an abstract class which is designed to remove extra lines
 * 
 */
package workshop.haplotype.organize.file.removeLine;

import java.util.ArrayList;
import java.util.List;
import workshop.haplotype.organize.file.ReadFileOrganizeInList;

/**
 * @author kazu
 * @version October 28 2015
 *
 */
public abstract class RemoveLine {
	protected List<String> revisedList;
	protected List<String> removedList;		// added on October 28 2015
	
	public RemoveLine(String filePath) {
		ReadFileOrganizeInList rcsvf = new ReadFileOrganizeInList(filePath);
		revisedList = new ArrayList<String>();
		removedList  = new ArrayList<String>();
		this.remove(rcsvf, revisedList, removedList);
	}
	
	public List<String> getRevisedList() {
		return revisedList;
	}
	
	public List<String> getRemovedList() {	// This is added to check NMDP code file
		return removedList;
	}
	
	public abstract void remove(ReadFileOrganizeInList rfoil, List<String> revisedList, 
			List<String> removedList);
}
