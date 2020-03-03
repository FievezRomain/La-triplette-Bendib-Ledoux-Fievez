package Rules;

public interface RuleAnonymize extends Rule {
	/**
	 * Applique une règle d'anonymisation sur une donnée
	 * @param data
	 * @return
	 */
	public String applyRule(String data);
}
