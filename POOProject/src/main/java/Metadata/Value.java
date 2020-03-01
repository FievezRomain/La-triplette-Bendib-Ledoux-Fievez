package Metadata;

/**
 * Classe qui représente une donnée d'une colonne / une donnée d'une ligne
 * @author FIEVEZ
 *
 */
public class Value {
	
	private String value;
	
	public Value(String value) {
		this.value = value;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
