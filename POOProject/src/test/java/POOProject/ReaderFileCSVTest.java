package POOProject;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import Metadata.Table;
import ReaderWriter.ReaderFile;
import ReaderWriter.ReaderFileFactory;
import ReaderWriter.Separator;

public class ReaderFileCSVTest {

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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertFalse(fileContent.isEmpty());
	}

}
