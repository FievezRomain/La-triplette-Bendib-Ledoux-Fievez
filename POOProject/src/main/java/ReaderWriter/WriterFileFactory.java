package ReaderWriter;


public class WriterFileFactory {
	/**
	 * Permet la création d'une classe permettant l'écriture du résultat de l'application selon
	 * l'extension que l'utilisateur veut en sortie
	 * @param type
	 * @return
	 */
	public static WriterFile getInstance(String type) {
		switch(type) {
			case "CSV":
				return new WriterFileCSV();
			default:
				return null; //Log classe non implémentée
		}
	}
}
