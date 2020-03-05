package Rules;

import java.util.Random;

import org.apache.log4j.Logger;

public class RuleRandomLetter implements RuleAnonymize {
	private static Logger logger = Logger.getLogger(RuleRandomLetter.class);
	/**
	 * Permet d'anonymiser une données avec une génération de caractères aléatoires
	 * La génération de caractères est cohérente
	 * Exemple: si dans cette exécution la lettre anonymisée est a qui devient @ alors cela restera le cas
	 * à la prochaine exécution
	 */
	@Override
	public String applyRule(String data) {
		logger.info("Début anonymisation");
		Random rand = new Random(2_000_000_000);
		String result = "";
		for(int i = 0; i < data.length(); i++) {
			result += (char) (rand.nextInt() + ((int)data.charAt(i)));
		}
		return result;
	}
}
