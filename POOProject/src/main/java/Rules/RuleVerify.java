package Rules;

public interface RuleVerify extends Rule {
	/**
	 * Applique une règle de vérification sur une donnée
	 * @param data
	 * @return
	 */
	public boolean checkRule(String data);
}
