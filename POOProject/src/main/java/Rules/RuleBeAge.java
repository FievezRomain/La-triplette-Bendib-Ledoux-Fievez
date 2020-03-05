package Rules;

import org.apache.log4j.Logger;

public class RuleBeAge implements RuleVerify {
	private static Logger logger = Logger.getLogger(RuleBeAge.class);
	/**
	 * Vérifie si la valeur est bien un âge compris entre 0 et 120 ans
	 */
	@Override
	public boolean checkRule(String data) {
		logger.info("Début de la vérification de l'âge");
		int age = Integer.parseInt(data);
		return age >= 0 && age <= 120;
	}
}
