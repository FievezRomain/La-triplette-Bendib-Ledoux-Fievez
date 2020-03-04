package Functionalities;

import java.util.Map;

import Metadata.Column;
import Metadata.Table;
import Rules.RuleVerify;
import Rules.VerifyRulesFactory;

public class FunctionalityVerify extends Functionality {
	/**
	 * Gère l'exécution de la fonctionnalité
	 * @throws Exception 
	 */
	@Override
	public Table start (Table datasInput) throws Exception {
		checkTypes(datasInput);
		checkRules(datasInput);
		return datasInput;
	}
	
	/**
	 * Pour chaque lignes, on vérifie les règles des colonnes sur les données
	 * On supprime la ligne de la table si une donnée de la ligne ne répond pas aux règles de sa colonne
	 * @param datasInput
	 * @return la table avec les données vérifiées
	 * @throws Exception 
	 */
	private Table checkRules(Table datasInput) throws Exception {
		for(int i = 0; i < datasInput.getLines().size(); i++) {
			boolean respect = false;
			boolean check = false;
			for(Map.Entry<String, Column> elem : datasInput.getColumns().entrySet()) {
				for(String rule : elem.getValue().getRules()) {
					check = true;
					RuleVerify r = VerifyRulesFactory.getInstanceRule(rule);
					respect = r.checkRule(datasInput.getLines().get(i).getValueFromKey(elem.getKey()).getValue());
					if(!respect) {
						datasInput.remove(i, elem.getKey());
						i--;
						break;
					}
				}
				if(!respect && check) {
					break;
				}
			}
		}
		return datasInput;
	}
	
	/**
	 * Pour chaque ligne, on vérifie que chaque donnée de la ligne est du même type que celle de sa colonne
	 * Si ce n'est pas le cas, on supprime la ligne de la table
	 * @param datasInput
	 * @return la table avec les données dont le type de colonne est respecté
	 */
	private Table checkTypes(Table datasInput){
		for(int i = 0; i < datasInput.getLines().size(); i++) {
			boolean respect = false;
			for(Map.Entry<String, Column> elem : datasInput.getColumns().entrySet()) {
				switch(elem.getValue().getType()) {
					case "int":
						respect = isNumeric(datasInput.getLines().get(i).getValueFromKey(elem.getKey()).getValue());
						break;
					case "double":
						respect = isNumeric(datasInput.getLines().get(i).getValueFromKey(elem.getKey()).getValue());
						break;
					case "string":
						respect = true;
						break;
					default:
						//Log
						break;
				}
				if(!respect) {
					datasInput.remove(i, elem.getKey());
					i--;
					break;
				}
			}
			
		}
		return datasInput;
		
		
	}
	
	/**
	 * Vérifie si une chaine de caractères comporte un nombre
	 * @param str
	 * @return boolean
	 */
	private boolean isNumeric(String str) {

        // null or empty
        if (str == null || str.length() == 0) {
            return false;
        }

        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }

        return true;

    }
}