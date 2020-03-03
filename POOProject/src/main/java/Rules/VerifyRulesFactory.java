package Rules;

public abstract class VerifyRulesFactory {
	/**
	 * Permet de retourner une instance de RuleVerify selon la règle en paramètre
	 * @param rule
	 * @return RuleVerify
	 * @throws Exception 
	 */
	public static RuleVerify getInstanceRule(String rule) throws Exception {
		switch(rule) {
			case "BE_AN_AGE":
				return new RuleBeAge();
			case "BE_AN_EMAIL":
				return new RuleBeEmail();
			case "BE_AN_DAUPHINE_EMAIL":
				return new RuleBeDauphineEmail();
			default:
				throw new Exception("La règle n'est pas implémentée");
		}
	}
}
