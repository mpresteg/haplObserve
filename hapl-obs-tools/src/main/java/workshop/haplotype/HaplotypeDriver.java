/*

    Copyright (c) 2014-2015 National Marrow Donor Program (NMDP)

    This library is free software; you can redistribute it and/or modify it
    under the terms of the GNU Lesser General Public License as published
    by the Free Software Foundation; either version 3 of the License, or (at
    your option) any later version.

    This library is distributed in the hope that it will be useful, but WITHOUT
    ANY WARRANTY; with out even the implied warranty of MERCHANTABILITY or
    FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public
    License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with this library;  if not, write to the Free Software Foundation,
    Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307  USA.

    > http://www.gnu.org/licenses/lgpl.html

*/
package workshop.haplotype;

import static org.dishevelled.compress.Readers.reader;
import static org.dishevelled.compress.Writers.writer;

import java.io.BufferedReader;
import java.io.File;
import java.io.PrintWriter;
import java.util.concurrent.Callable;

import org.dishevelled.commandline.ArgumentList;
import org.dishevelled.commandline.CommandLine;
import org.dishevelled.commandline.CommandLineParseException;
import org.dishevelled.commandline.CommandLineParser;
import org.dishevelled.commandline.Switch;
import org.dishevelled.commandline.Usage;
import org.dishevelled.commandline.argument.FileArgument;

import workshop.haplotype.write.GenerateSampleHaplotype;

/**
 * AnalyzeGLStrings
 *
 */
public class HaplotypeDriver implements Callable<Integer> {
	
    private final File inputFile;
    private final File outputFile;

    private static final String USAGE = "haplotype-driver [args]";


    /**
     * Analyze gl string using linkage disequilibrium frequencies
     *
     * @param inputFile input file, if any
     * @param outputFile output interpretation file, if any
     */
    public HaplotypeDriver(File inputFile, File outputFile) {
        this.inputFile = inputFile;
        this.outputFile   = outputFile;
    }
    
    @Override
    public Integer call() throws Exception {    
    	BufferedReader reader = reader(inputFile);
    	PrintWriter writer = writer(outputFile);

    	new GenerateSampleHaplotype(reader, writer);
    	//new GenerateSampleHaplotype(inputFile.getAbsolutePath(), outputFile.getAbsolutePath());
    	return 0;
    }

    /**
     * Main.
     *
     * @param args command line args
     */
    public static void main(final String[] args) {
        Switch about = new Switch("a", "about", "display about message");
        Switch help  = new Switch("h", "help", "display help message");
        FileArgument inputFile = new FileArgument("i", "input", "input file, default stdin", false);
        FileArgument outputFile   = new FileArgument("o", "output", "output file, default stdout", false);

        ArgumentList arguments  = new ArgumentList(about, help, inputFile, outputFile);
        CommandLine commandLine = new CommandLine(args);

        HaplotypeDriver haplotypeDriver = null;
        
        try
        {
            CommandLineParser.parse(commandLine, arguments);
            haplotypeDriver = new HaplotypeDriver(inputFile.getValue(), outputFile.getValue());

            if (about.wasFound()) {
                haplotypeDriver.new AboutHaplotypeDriver().about(System.out);
                System.exit(0);
            }
            if (help.wasFound()) {
                Usage.usage(USAGE, null, commandLine, arguments, System.out);
                System.exit(0);
            }
                                    
        }
        catch (CommandLineParseException | IllegalArgumentException e) {
            Usage.usage(USAGE, e, commandLine, arguments, System.err);
            System.exit(-1);
        }
        try {
            System.exit(haplotypeDriver.call());
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    public class AboutHaplotypeDriver extends About {
        @Override
        public String addText() {
            StringBuilder sb = new StringBuilder();

            sb.append("Takes spreadsheet format input file.\n"); 
            sb.append(" - This is convenient to build haplotype from a single family.\n");
            return sb.toString();
        }
    }
    
}
