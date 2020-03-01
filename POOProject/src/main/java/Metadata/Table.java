package Metadata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Structure de données permettant de stocker les colonnes et les lignes d'un fichier
 * Les colonnes possèdent une liste de valeur et les lignes aussi
 * Des méthodes sont donc présentes pour garder cette structure de données cohérente
 * @author FIEVEZ
 *
 */
public class Table {
	private List<Line> lines;
	private Map<String, Column> columns;
	
	public Table() {
		lines = new ArrayList<>();
		columns = new HashMap<>();
	}
	
	public List<Line> getLines(){
		return this.lines;
	}
	public Map<String, Column> getColumns(){
		return this.columns;
	}
	public void remove (int id, String columnName) {
		lines.remove(id);
		columns.get(columnName).getValues().remove(id);
	}
	public void add(Value v, String columnName) {
		lines.get(lines.size()-1).addPair(columnName, v);
		if(!columns.containsKey(columnName)) {
			columns.put(columnName, new Column(columnName));
		}
		columns.get(columnName).getValues().add(v);
	}
	public void setColumnType(String type, String columnName) {
		columns.get(columnName).setType(type);
	}
	public void setColumnRules(String columnName, List<String> rules) {
		columns.get(columnName).setRules(rules);
	}
	public void setColumnRuleAnonymize(String columnName, String ruleAnonymize) {
		columns.get(columnName).setRuleAnonymize(ruleAnonymize);
	}
	public boolean isEmpty() {
		return this.lines.isEmpty();
	}
}
