/**
 * combine files containing GL Strings
 */
package workshop.haplotype.collective;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import workshop.haplotype.organize.file.removeLine.RemoveFirstLine;
import workshop.haplotype.organize.file.removeLine.RemoveLine;

/**
 * @author kazu
 * @version June 12 2018
 *
 */
public class CombineGLFile {
	private List<String> combined;	// no header
	private String header;

	/**
	 * 
	 */
	public CombineGLFile() {
		// TODO Auto-generated constructor stub
		String collective = "src/test/resources/collective/";		//		"global/collective/";
		combine(collective);	
	}
	
	public CombineGLFile(String collective) {	// constructor with argument
		combine(collective);	
	}
	
	public void combine(String collective) {
		combined = new LinkedList<String>();
		
		File glFile = new File(collective);
		File [] listGlFile = glFile.listFiles();
		header = "";
		for (File file : listGlFile) {
			if ((file.getName().contains("gl_strings")) && (!file.getName().contains("combined"))) {
//				System.out.println(file.getName());
				RemoveLine rl = new RemoveFirstLine(collective + file.getName());
				
				header = rl.getRemovedList().get(0);
//				System.out.println(header);
				combined.addAll(rl.getRevisedList());	
			}
		
		}
	}
	
	public List<String> getCombined() {
		return combined;
	}
	
	public String getHeader() {
		return header;
	}

}
