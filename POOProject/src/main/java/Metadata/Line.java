package Metadata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Line {
private Map<String, Value> lines;
	
	public Map<String, Value> getLines(){
		return lines;
	}
	public Line() {
		lines = new HashMap<>();
	}
	
	/**
	 * Permet d'ajouter une clé et une valeur dans lines
	 * @param key
	 * @param v
	 */
	public void addPair(String key, Value v) {
		lines.put(key, v);
	}
	
	/**
	 * Permet de récupérer la valeur associée à la clé
	 * @param key
	 * @return
	 */
	public Value getValueFromKey(String key) {
		return lines.get(key);
	}
	
	/**
	 * Permet de regrouper toutes les valeurs dans une liste
	 * @return une liste contenant toutes les valeurs de la Map
	 */
	public List<String> valuesToString(){
		List<String> list = new ArrayList<>();
		for(Value v : this.lines.values()) {
			list.add(v.getValue());
		}
		return list;
	}
}
