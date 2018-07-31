/**
 * This driver is to run HaplObser for single family
 * Input file is table format 
 */
package workshop.haplotype.driver;

import workshop.haplotype.utilities.FileUtilities;
import workshop.haplotype.write.GenerateSampleHaplotype;

/**
 * @author kazu
 * @version October 2 2017
 *
 */
public class DriverForHaplotype {


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new GenerateSampleHaplotype(FileUtilities.readFile(args[0]), 
				FileUtilities.writeFile(args[1]));

	}

}
