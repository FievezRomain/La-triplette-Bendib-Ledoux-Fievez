package Rules;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.Test;

import Rules.AnonymizeRuleFactory;
import Rules.RuleAnonymize;
import Rules.RuleVerify;
import Rules.VerifyRulesFactory;

public class RulesTest {
	private static Logger logger = Logger.getLogger(RulesTest.class);

	@Test
	public void testRuleAge() {
		try {
			RuleVerify rule = VerifyRulesFactory.getInstanceRule("BE_AN_AGE");
			assertTrue(rule.checkRule("22"));
		}catch(Exception e) {
			logger.fatal(e.getMessage());
		}
	}
	
	@Test
	public void testErrorRuleAge() {
		try {
			RuleVerify rule = VerifyRulesFactory.getInstanceRule("BE_AN_AGE");
			assertFalse(rule.checkRule("Pas d'âge"));
		}catch(Exception e) {
			logger.fatal(e.getMessage());
		}
	}
	
	@Test
	public void testRuleEmail() {
		try {
			RuleVerify rule = VerifyRulesFactory.getInstanceRule("BE_AN_EMAIL");
			assertTrue(rule.checkRule("test@gmail.com"));
		}catch(Exception e) {
			logger.fatal(e.getMessage());
		}
	}
	
	@Test
	public void testErrorRuleEmail() {
		try {
			RuleVerify rule = VerifyRulesFactory.getInstanceRule("BE_AN_EMAIL");
			assertFalse(rule.checkRule("testgmailcom"));
		}catch(Exception e) {
			logger.fatal(e.getMessage());
		}
	}
	
	@Test
	public void testRuleEmailDauphine() {
		try {
			RuleVerify rule = VerifyRulesFactory.getInstanceRule("BE_AN_DAUPHINE_EMAIL");
			assertTrue(rule.checkRule("test@dauphine.psl.eu"));
		}catch(Exception e) {
			logger.fatal(e.getMessage());
		}
	}
	
	@Test
	public void testErrorRuleEmailDauphine() {
		try {
			RuleVerify rule = VerifyRulesFactory.getInstanceRule("BE_AN_DAUPHINE_EMAIL");
			assertFalse(rule.checkRule("test@gmail.com"));
		}catch(Exception e) {
			logger.fatal(e.getMessage());
		}
	}
	
	@Test
	public void testRandomLetter() {
		try {
			RuleAnonymize rule = AnonymizeRuleFactory.getInstance("RANDOM_LETTER");
			String result = rule.applyRule("test");
			String result2 = rule.applyRule("test");
			assertEquals(result, result2);
		}catch(Exception e) {
			logger.fatal(e.getMessage());
		}
	}
	
	@Test
	public void testRandomLetterForLocalPart() {
		try {
			RuleAnonymize rule = AnonymizeRuleFactory.getInstance("RANDOM_LETTER_FOR_LOCAL_PART");
			String result = rule.applyRule("test@gmail.com");
			String result2 = rule.applyRule("test@gmail.com");
			assertEquals(result, result2);
		}catch(Exception e) {
			logger.fatal(e.getMessage());
		}
	}
	
	@Test
	public void testUndefinedRuleVerify() {
		try {
			RuleVerify rule = VerifyRulesFactory.getInstanceRule("BE_AN_AGEe");
			logger.fatal("Fail testUndefinedRuleVerify");
			fail();
		}catch(Exception e) {
			assertEquals("Message correct", "La règle n'est pas implémentée", e.getMessage());
		}
	}
	
	@Test
	public void testUndefinedRuleAnonymize() {
		try {
			RuleAnonymize rule = AnonymizeRuleFactory.getInstance("BE_AN_AGEe");
			logger.fatal("Fail testUndefinedRuleAnonymize");
			fail();
		}catch(Exception e) {
			assertEquals("Message correct", "La règle n'est pas implémentée", e.getMessage());
		}
	}

}
