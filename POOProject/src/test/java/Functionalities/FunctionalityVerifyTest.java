package Functionalities;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.junit.Test;

import Metadata.Line;
import Metadata.Table;
import Metadata.Value;

public class FunctionalityVerifyTest {
	private static Logger logger = Logger.getLogger(FunctionalityVerifyTest.class);

	@Test
	public void testSuccessful() {
		Functionality func = Functionality.getFunctionality("verify");
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
			map.setColumnRules("email_perso", Arrays.asList("BE_AN_EMAIL"));
			map = func.start(map);
			assertFalse(map.isEmpty());
		} catch (Exception e) {
			logger.fatal(e.getMessage());
		}
	}
	
	@Test
	public void testErrorType() {
		Functionality func = Functionality.getFunctionality("verify");
		try {
			Table map = new Table();
			map.getLines().add(new Line());
			map.add(new Value("romain"), "nom");
			map.setColumnType("string", "nom");
			map.add(new Value("romain@dauphine.psl.eu"), "email_perso");
			map.setColumnType("string", "email_perso");
			map.add(new Value("Pas d'âge"), "age");
			map.setColumnType("int", "age");
			map.getLines().add(new Line());
			map.add(new Value("perrine"), "nom");
			map.add(new Value("perrine@dauphine.psl.eu"), "email_perso");
			map.add(new Value("Pas d'âge"), "age");
			map = func.start(map);
			assertTrue(map.isEmpty());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testErrorRule() {
		Functionality func = Functionality.getFunctionality("verify");
		try {
			Table map = new Table();
			map.getLines().add(new Line());
			map.add(new Value("romain"), "nom");
			map.setColumnType("string", "nom");
			map.add(new Value("romain@dauphine.psl.eu"), "email_perso");
			map.setColumnType("string", "email_perso");
			map.add(new Value("121"), "age");
			map.setColumnType("int", "age");
			map.getLines().add(new Line());
			map.add(new Value("perrine"), "nom");
			map.add(new Value("perrine@dauphine.psl.eu"), "email_perso");
			map.add(new Value("122"), "age");
			map.setColumnRules("age", Arrays.asList("BE_AN_AGE"));
			map = func.start(map);
			assertTrue(map.isEmpty());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
