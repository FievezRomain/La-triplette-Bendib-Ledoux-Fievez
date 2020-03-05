package ReaderWriter;

import org.apache.log4j.Logger;

public class ReaderFileFactory {
	private static Logger logger = Logger.getLogger(ReaderFileFactory.class);
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
				logger.warn("Le type de fichier n'est pas connu");
				reader = null;
		}
		return reader;
	}
}
