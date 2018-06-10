/**
 * file containing HLA typing include a line of header
 */
package workshop.haplotype.organize.file;

import workshop.haplotype.organize.file.ChooseElement;
import workshop.haplotype.organize.file.OrganizeElement;
import workshop.haplotype.organize.file.removeLine.RemoveFirstLine;
import workshop.haplotype.organize.file.removeLine.RemoveLine;

/**
 * @author Kazutoyo Osoegawa
 * @version April 20 2016
 *
 */
public class ChooseElementHapType extends ChooseElement {

	/**
	 * @param filePath
	 */
	public ChooseElementHapType(String filePath) {
		super(filePath);
		// TODO Auto-generated constructor stub
		RemoveLine rl = new RemoveFirstLine(filePath);
		OrganizeElement orel = new OrganizeElement(rl, ",");	// csv file
		chooseElement(orel);
	}

}
