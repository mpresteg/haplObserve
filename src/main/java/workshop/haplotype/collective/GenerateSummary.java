/**
 * 
 */
package workshop.haplotype.collective;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * @author kazu
 * @version June 25 2018
 *
 */
public class GenerateSummary {

	/**
	 * 
	 */
	public GenerateSummary(AddFamRelationCountry arc, String file) {
		// TODO Auto-generated constructor stub
		try {	// generate csv file for HaplObserve
			BufferedWriter out = 
				new BufferedWriter(new FileWriter(file));
			
			for (List<String> list : arc.getFamSamRelationHapCountry()) {
				int count = 0;
				for (String element : list) {
					out.write(element);
					if (count < list.size() -1) {
						out.write(",");
					}
					count++;
				}
				out.write("\n");
			}
			
			out.close();					
		} catch (IOException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println(file);
		}	
		
	}

}
