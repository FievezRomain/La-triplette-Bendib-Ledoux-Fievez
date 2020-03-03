package Rules;

public class RuleBeAge implements RuleVerify {
	/**
	 * Vérifie si la valeur est bien un âge compris entre 0 et 120 ans
	 */
	@Override
	public boolean checkRule(String data) {
		int age = Integer.parseInt(data);
		return age >= 0 && age <= 120;
	}
}
