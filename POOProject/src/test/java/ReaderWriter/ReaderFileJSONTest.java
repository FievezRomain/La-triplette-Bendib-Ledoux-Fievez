package ReaderWriter;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.junit.Test;

import Functionalities.FunctionalityAnonymizeTest;
import Metadata.Table;
import ReaderWriter.ReaderFile;
import ReaderWriter.ReaderFileFactory;
import ReaderWriter.Separator;

public class ReaderFileJSONTest {
	private static Logger logger = Logger.getLogger(ReaderFileJSONTest.class);
	@Test
	public void testRead() {
		String path = new File("src/test/java/testFiles/testProjetPOOM1MIAGE.csv").getAbsolutePath();
		String[] alt = path.split("\\.");
		String extension = alt[alt.length - 1].toLowerCase();
		Table fileContent = null;
		Separator sep = new Separator(";");
		ReaderFile rf = ReaderFileFactory.createInstance(extension);
		try {
			fileContent = rf.readInput(sep, path);
			path = new File("src/test/java/testFiles/testProjetPOOM1MIAGE.json").getAbsolutePath();
			alt = path.split("\\.");
			extension = alt[alt.length - 1].toLowerCase();
			sep = new Separator(";");
			rf = ReaderFileFactory.createInstance(extension);
			fileContent = rf.readRules(sep, path, fileContent); // Corriger la lecture JSON
		} catch (IOException e) {
			logger.fatal(e.getMessage());
		} catch (Exception e) {
			logger.fatal(e.getMessage());
		}
		assertFalse(fileContent.isEmpty());
	}

}
