package Rules;

import java.util.regex.Pattern;

public class RuleBeEmail implements RuleVerify{
	/**
	 * Vérifie si l'adresse e-mail est bien une adresse e-mail conforme
	 */
	@Override
	public boolean checkRule(String data) {
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
