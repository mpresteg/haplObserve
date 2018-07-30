package workshop.haplotype.utilities;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class FileUtilities {
	public static BufferedReader readFile(String filename) {
		InputStream stream = FileUtilities.class.getClassLoader()
				.getResourceAsStream(filename);
		if (stream == null) {
			try {
				stream = new FileInputStream(filename);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		
		return reader;
	}
	
	public static PrintWriter writeFile(String filename) {
		PrintWriter writer = null;
		try {
			FileOutputStream fos = new FileOutputStream(filename);
			writer = new PrintWriter(fos);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return writer;
	}
}
