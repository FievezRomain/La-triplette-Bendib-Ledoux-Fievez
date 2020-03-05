package ReaderWriter;

import org.apache.log4j.Logger;

public class WriterFileFactory {
	private static Logger logger = Logger.getLogger(WriterFileFactory.class);
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
				logger.warn("Le type de fichier n'est pas connu");
				return null;
		}
	}
}
