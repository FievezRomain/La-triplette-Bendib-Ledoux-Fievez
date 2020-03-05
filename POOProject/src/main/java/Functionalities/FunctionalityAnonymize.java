package Functionalities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import Metadata.Column;
import Metadata.Table;
import Rules.AnonymizeRuleFactory;
import Rules.RuleAnonymize;

public class FunctionalityAnonymize extends Functionality {
	private static Logger logger = Logger.getLogger(FunctionalityAnonymize.class);
	/**
	 * Gère l'exécution de la fonctionnalité
	 * @throws Exception 
	 */
	@Override
	public Table start (Table datasInput) throws Exception {
		applyRule(datasInput);
		logger.info("Rules apply on datas");
		return datasInput;
	}
	
	/**
	 * Applique la règle d'anonymisation sur chaque valeurs des colonnes qui possèdent une règle
	 * Trie ensuite la table pour enlever les colonnes et leurs valeurs qui n'ont pas de règles
	 * @param datasInput
	 * @return la table avec les données anonymisées
	 * @throws Exception 
	 */
	private Table applyRule(Table datasInput) throws Exception {
		logger.info("Start apply rules on datas");
		try {
			List<String> columnsToDelete = new ArrayList<>();
			for(int i = 0; i<datasInput.getLines().size();i++) {
				for(Map.Entry<String, Column> elem : datasInput.getColumns().entrySet()) {
					if(elem.getValue().getRuleAnonymize() != null) {
						String rule = elem.getValue().getRuleAnonymize();
						RuleAnonymize r = AnonymizeRuleFactory.getInstance(rule);
						datasInput.getLines().get(i).getValueFromKey(elem.getKey())
							.setValue(r.applyRule(datasInput.getLines()
								.get(i).getValueFromKey(elem.getKey()).getValue()));
					} else {
						datasInput.getLines().get(i).getLines().remove(elem.getKey());
						columnsToDelete.add(elem.getKey());
					}
				}
			}
			for(String s : columnsToDelete) {
				datasInput.getColumns().remove(s);
			}
		}catch(Exception e) {
			logger.fatal("Erreur lors de l'application d'une règle");
		}
		return datasInput;
	}
}
