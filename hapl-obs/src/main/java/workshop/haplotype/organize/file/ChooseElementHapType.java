/**
 * file containing HLA typing include a line of header
 */
package workshop.haplotype.organize.file;

import java.io.BufferedReader;

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
	public ChooseElementHapType(BufferedReader reader) {
		super();
		// TODO Auto-generated constructor stub
		RemoveLine rl = new RemoveFirstLine(reader);
		OrganizeElement orel = new OrganizeElement(rl, ",");	// csv file
		chooseElement(orel);
	}

}
