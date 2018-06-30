/**
 * This abstract class do nothing
 * The object is to calculate TDT
 */
package workshop.haplotype.frequency.ms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import workshop.haplotype.organize.NonRedundantList;

/**
 * @author kazu
 * @version February 13 2018
 *
 */
public abstract class SampleHapAbstract {
	protected Map<String, String> sampleFam;	// sample family
	protected Map<String, String> sampleRelation;	// sample relationship
	protected Map<String, List<String>> sampleHap;	// sample haplotype
	protected NonRedundantList haplotypes;	// store unique haplotypes including recombinant haplotypes
	protected List<String> sampleList;
	protected List<String> fatherList;
	protected List<String> motherList;
	protected List<String> childList;
	protected int locusCount;		// number of loci to analyze
	protected NonRedundantList familyList;	
	protected String [] loci;

	/**
	 * 
	 */
	public SampleHapAbstract(String filePath, String targetLoci) {
		// TODO Auto-generated constructor stub
		sampleHap = new HashMap<String, List<String>>();
		haplotypes = new NonRedundantList();
		sampleList = new ArrayList<String>();
		fatherList = new ArrayList<String>();
		motherList = new ArrayList<String>();
		childList = new ArrayList<String>();
		sampleFam = new HashMap<String, String>();	// stores sample family
		sampleRelation = new HashMap<String, String>();	// stores sample relation	
		familyList = new NonRedundantList();
		
		locusCount = targetLoci.split(",").length;
		String target = targetLoci.replace("DRB[345]", "HLA-DRB3,HLA-DRB4,HLA-DRB5");	// handle DRB345
		loci = target.split(",");		// separate by locus
	}
	
	public Map<String, String> getSampleFam() {
		return sampleFam;
	}
	
	public Map<String, String> getSampleRelation() {
		return sampleRelation;
	}
	
	public Map<String, List<String>> getSampleHap() {
		return sampleHap;
	}
	
	public NonRedundantList getHaplotypes() {
		return haplotypes;
	}
	
	public List<String> getSampleList() {
		return sampleList;
	}
	
	public List<String> getFatherList() {
		return fatherList;
	}
	
	public List<String> getMotherList() {
		return motherList;
	}
	
	public List<String> getChildList() {
		return childList;
	}
	
	public int getLocusCount() {
		return locusCount;
	}
	
	public NonRedundantList getFamilyList() {
		return familyList;
	}
	
	public String [] getLoci() {
		return loci;
	}

}
