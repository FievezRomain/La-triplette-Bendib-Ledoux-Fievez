package Rules;

import java.util.regex.Pattern;

import org.apache.log4j.Logger;

public class RuleBeEmail implements RuleVerify{
	private static Logger logger = Logger.getLogger(RuleBeEmail.class);
	/**
	 * Vérifie si l'adresse e-mail est bien une adresse e-mail conforme
	 */
	@Override
	public boolean checkRule(String data) {
		logger.info("Début de la vérification email");
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                "[a-zA-Z0-9_+&*-]+)*@" + 
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                "A-Z]{2,7}$"; 
                  
		Pattern pat = Pattern.compile(emailRegex); 
		if (data == null) 
            return false; 
        return pat.matcher(data).matches();
	}
}
