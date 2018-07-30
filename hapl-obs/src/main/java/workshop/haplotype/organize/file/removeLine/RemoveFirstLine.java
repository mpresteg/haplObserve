/**
 * This is designed to remove the first line, header
 * inherited from RemoveLine
 */
package workshop.haplotype.organize.file.removeLine;

import java.io.BufferedReader;
import java.util.List;

import workshop.haplotype.organize.file.ReadFileOrganizeInList;


/**
 * @author kazu
 * @version March 26 2016
 * 
 *
 */
public class RemoveFirstLine extends RemoveLine {

	public RemoveFirstLine(BufferedReader reader) {
		// TODO Auto-generated constructor stub
		super(reader);
		
	}

	/* (non-Javadoc)
	 * @see workshop.haplotype.organize.file.removeLine.RemoveLine#remove(typeStream.ReadCsvFile, java.util.List)
	 */
	@Override
	public void remove(ReadFileOrganizeInList rfoil, List<String> revisedList, List<String> removedList) {
		// TODO Auto-generated method stub
		int count = 0;
		for (String str : rfoil.getOriginalList()) {
			if (count != 0) {		// remove the first line
				revisedList.add(str);
			}
			else {
				removedList.add(str);
			}
			count++;
		}

	}

}
