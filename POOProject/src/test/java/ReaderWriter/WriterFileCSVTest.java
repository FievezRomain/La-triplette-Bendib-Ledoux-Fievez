package ReaderWriter;

import static org.junit.Assert.*;

import java.io.File;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.junit.Test;

import Functionalities.FunctionalityAnonymizeTest;
import Metadata.Line;
import Metadata.Table;
import Metadata.Value;
import ReaderWriter.Separator;
import ReaderWriter.WriterFile;
import ReaderWriter.WriterFileFactory;

public class WriterFileCSVTest {
	private static Logger logger = Logger.getLogger(WriterFileCSVTest.class);

	@Test
	public void testWrite() {
		BasicConfigurator.configure();
		try {
			Table map = new Table();
			map.getLines().add(new Line());
			map.add(new Value("romain"), "nom");
			map.setColumnType("string", "nom");
			map.add(new Value("romain@dauphine.psl.eu"), "email_perso");
			map.setColumnType("string", "email_perso");
			map.getLines().add(new Line());
			map.add(new Value("perrine"), "nom");
			map.add(new Value("perrine@dauphine.psl.eu"), "email_perso");
			WriterFile wf = WriterFileFactory.getInstance("CSV");
			assertTrue(wf.write(new Separator(";"), map, new File("src/test/java/testFiles/testFileResult.csv").getAbsolutePath()));
		} catch (Exception e) {
			logger.fatal(e.getMessage());
		}
	}

}
