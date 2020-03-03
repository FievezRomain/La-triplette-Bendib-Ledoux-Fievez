package Rules;

import java.util.Random;

public class RuleRandomLetterForLocalPart implements RuleAnonymize {
	/**
	 * Permet d'anonymiser une données jusqu'au caractère '@' avec une génération de caractères aléatoires
	 * La génération de caractères est cohérente
	 * Exemple: si dans cette exécution la lettre anonymisée est a qui devient @ alors cela restera le cas
	 * à la prochaine exécution
	 */
	@Override
	public String applyRule(String data) {
		Random rand = new Random(2_000_000_000);
		String[] alt = data.split("@");
		String result = "";
		for(int i = 0; i < alt.length;i++) {
			result += (char) (rand.nextInt() + ((int)data.charAt(i)));
		}
		if(alt.length > 0) {
			result += "@"+alt[alt.length-1];
		}
		return result;
	}
}
