package Metadata;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import Metadata.Line;
import Metadata.Table;
import Metadata.Value;

public class MetadataTest {
	@Test
	public void testAdd() {
		Table table = new Table();
		table.getLines().add(new Line());
		table.add(new Value("romain"), "nom");
		assertFalse(table.isEmpty());
	}
	
	@Test
	public void testRemove() {
		Table table = new Table();
		table.getLines().add(new Line());
		table.add(new Value("romain"), "nom");
		table.remove(0, "nom");
		assertTrue(table.isEmpty());
	}
	
	@Test
	public void testTypeColumn() {
		Table table = new Table();
		table.getLines().add(new Line());
		table.add(new Value("21"), "age");
		table.setColumnType("int", "age");
		assertEquals("int", table.getColumns().get("age").getType());
	}
	
	@Test
	public void testRulesVerifyColumn() {
		Table table = new Table();
		table.getLines().add(new Line());
		table.add(new Value("22"), "age");
		table.setColumnRules("age", Arrays.asList("BE_AN_AGE"));
		assertTrue(table.getColumns().get("age").getRules().contains("BE_AN_AGE"));
	}
	@Test
	public void testRuleAnonymizeColumn() {
		Table table = new Table();
		table.getLines().add(new Line());
		table.add(new Value("romain"), "nom");
		table.setColumnRuleAnonymize("nom", "RANDOM_LETTER");
		assertEquals("RANDOM_LETTER", table.getColumns().get("nom").getRuleAnonymize());
	}
}
