package Rules;

import org.apache.log4j.Logger;

public class RuleBeDauphineEmail implements RuleVerify {
	private static Logger logger = Logger.getLogger(RuleBeDauphineEmail.class);
	/**
	 * Vérifie si l'adresse e-mail est bien une adresse de Dauphine
	 */
	@Override
	public boolean checkRule(String data) {
		logger.info("Début vérification email Dauphine");
		RuleVerify r = new RuleBeEmail(); 
		boolean test = r.checkRule(data);
		if(test) {
			String[] split = data.split("@");
			String alt = split[split.length -1];
			if(alt.equals("dauphine.eu") || alt.equals("dauphine.psl.eu") || alt.equals("lamsade.dauphine.fr")) {
				return true;
			}
		}
		return false;
	}
}
