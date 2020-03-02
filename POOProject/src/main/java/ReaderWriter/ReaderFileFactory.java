package ReaderWriter;

public class ReaderFileFactory {
	/**
	 * Permet la création d'un lecture selon l'extension d'un fichier
	 * @param extension
	 * @return
	 */
	public static ReaderFile createInstance(String extension) {
		ReaderFile reader;
		switch(extension) {
			case "csv":
				reader = new ReaderFileCSV();
				break;
			case "json":
				reader = new ReaderFileJSON();
				break;
			default:
				reader = null; //Log type de fichier à implémenter
				break;
		}
		return reader;
	}
}
