/**
 * This program organizes sample HLA type information as follows:
 * Map<String, Map<String, List<String>>> hlaTypeBySample
 * sample ID Outer Map
 * locus: Inner Map
 * Type: List<String>
 */
package workshop.haplotype.family.organize;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import workshop.haplotype.gene.HLAgene;
import workshop.haplotype.organize.NonRedundantList;
import workshop.haplotype.organize.file.ChooseElement;

/**
 * @author Kazutoyo Osoegawa
 * @version May 31 2018
 *
 */
public abstract class OrganizeBySample {
	protected List<String> sampleList;	// sample list in original order
	// sample ID Outer Map
	// locus: Inner Map
	// Type: List<String>
	protected Map<String, Map<String, List<String>>> hlaTypeBySample;
	protected HLAgene hlaGene;
	
	protected Map<String, String> sampleRelation;//sample relationship is organized in Map
	// sample
	// gene
	// original glstring
	protected Map<String, Map<String, String>> sampleGeneOrgGLType;
	protected NonRedundantList pahseAmbiguityGeneList;	// to be inherited for next
	// HLA-DRB3, HLA-DRB4, HLA-DRB5 => HLA-DRB345
	protected NonRedundantList geneListDRB345Combined;	// replace hlaGene, used in next step
	protected List<String> childList;
	protected List<String> parentList;		// father, mother in this order


	/**
	 * @param ce
	 */
	public OrganizeBySample(ChooseElement ce) {
		// TODO Auto-generated constructor stub
		pahseAmbiguityGeneList = new NonRedundantList();	
		geneListDRB345Combined = new NonRedundantList();
		
		sampleRelation = new HashMap<String, String>();	// required
		sampleGeneOrgGLType = new HashMap<String, Map<String, String>>();	// required
		childList = new ArrayList<String>();	// required
		parentList = new ArrayList<String>();	// required
		
		// collect sample ID		
		sampleList = new ArrayList<String>();	// use List	August 18 2015		
		hlaTypeBySample = new TreeMap<String, Map<String, List<String>>>();		
		hlaGene = new HLAgene();	
	}
	

	
	public List<String> getSampleList() {
		return sampleList;
	}
	
	public Map<String, Map<String, List<String>>> getHlaTypeBySample() {
		return hlaTypeBySample;
	}
	
	public Map<String, String> getSampleRelation() {
		return sampleRelation;
	}
	
	public NonRedundantList getGeneListDRB345Combined() {
		return geneListDRB345Combined;
	}
	
	public List<String> getChildList() {
		return childList;
	}
	
	public List<String> getParentList() {
		return parentList;
	}
	
	public NonRedundantList getPahseAmbiguityGeneList() {
		return pahseAmbiguityGeneList;
	}

}
