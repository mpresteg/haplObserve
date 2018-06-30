/**
 * This class generates reports for multiple family haplotypes
 */
package workshop.haplotype.write;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import workshop.haplotype.collective.CombineGLFile;
import workshop.haplotype.collective.FamSamRelationCountry;

/**
 * @author kazu
 * @version June 22 2018
 *
 */
public class GenerateFamilyHaplotype {
	protected String collective;
	protected String haplotype;
	protected String today;
	protected FamSamRelationCountry fsrc;

	/**
	 * 
	 */
	public GenerateFamilyHaplotype(String global) {
		// TODO Auto-generated constructor stub
		collective = global + "collective/"; 
		CombineGLFile combined = new CombineGLFile(collective);
		String famcsv = collective+ "FAMCSV/";
		haplotype = collective + "haplotype/";
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();		
		today = dateFormat.format(date);	
		new RunHaplObserveForMultipleFamilies(collective, combined, famcsv, haplotype, today);
		
		fsrc = new FamSamRelationCountry(combined);		
		
		// generate table
		new GenerateFamSampleRelationHapValidCountryTable(haplotype, "Validation", fsrc,
				"_Haplotype_Summary_Table_" + today + ".csv");				
		new GenerateHaplotypeSummary(haplotype, "Validation", fsrc, today);
		
		new GenerateHaplotypeGLStringSummary(haplotype, collective, "Validation", fsrc, today);
		new GenerateHaplotypeGLStringSummary(haplotype, haplotype, "SingleAlleleHapType", fsrc, today);
		new GenerateHaplotypeGLStringSummary(haplotype, haplotype, "TwoFieldAlleleHapType", fsrc, today);
		
	}

}
