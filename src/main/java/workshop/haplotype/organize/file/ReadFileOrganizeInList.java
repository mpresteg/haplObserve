/**
 * This programs read a file and organize each line of string
 * into ArrayList<String>
 */
package workshop.haplotype.organize.file;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kazutoyo Osoegawa
 * @version September 25 2017
 *
 */
public class ReadFileOrganizeInList {
	protected List<String> originalList;
	
	public ReadFileOrganizeInList (String filePath) {
		originalList = new ArrayList<String>();
		BufferedReader fin;
		String thisLine;
		
		try {
			InputStream stream = ReadFileOrganizeInList.class.getClassLoader()
					.getResourceAsStream(filePath);
			if (stream == null) {
				stream = new FileInputStream(filePath);
			}
			
			fin = new BufferedReader(new InputStreamReader(stream));
			
			// read bytes until EOF is encountered
			do {
				thisLine = fin.readLine();		// read from the file
				if (thisLine != null) {
//					System.out.println(thisLine);
					// trim "\r\n"
					originalList.add(thisLine.trim());
				}
			} while (thisLine != null);	// when i equals -1, the end of the file has been reached.
			fin.close();	// close the file
		} catch (FileNotFoundException exc) {
			System.out.println("File Not Found!");
		} catch(IOException exc) {
			System.out.println("Error reading file.");
		}
	}
	
	public List<String> getOriginalList() {
		return originalList;
	}

}
