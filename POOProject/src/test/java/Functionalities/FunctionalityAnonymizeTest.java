package Functionalities;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.Test;

import Metadata.Line;
import Metadata.Table;
import Metadata.Value;

public class FunctionalityAnonymizeTest {
	private static Logger logger = Logger.getLogger(FunctionalityAnonymizeTest.class);
	@Test
	public void testSuccessful() {
		Functionality func = Functionality.getFunctionality("anonymize");
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
			map.setColumnRuleAnonymize("email_perso", "RANDOM_LETTER_FOR_LOCAL_PART");
			map.setColumnRuleAnonymize("nom", "RANDOM_LETTER");
			func.start(map);
			assertFalse(map.isEmpty());
		} catch (Exception e) {
			logger.fatal(e.getMessage());
		}
	}

}
