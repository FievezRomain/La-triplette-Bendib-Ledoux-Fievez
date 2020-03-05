package Rules;

import org.apache.log4j.Logger;

public abstract class AnonymizeRuleFactory {
	private static Logger logger = Logger.getLogger(AnonymizeRuleFactory.class);
	/**
	 * Permet de créer une instance d'une règle à partir de la valeur du fichier lu
	 * @param rule
	 * @return l'instance de la règle d'anonymisation demandée en paramètre
	 * @throws Exception 
	 */
	public static RuleAnonymize getInstance(String rule) throws Exception {
		switch(rule) {
			case "RANDOM_LETTER":
				return new RuleRandomLetter();
			case "RANDOM_LETTER_FOR_LOCAL_PART":
				return new RuleRandomLetterForLocalPart();
			default:
				logger.warn("La règle n'est pas implémentée");
				throw new Exception("La règle n'est pas implémentée");
		}
	}
}
