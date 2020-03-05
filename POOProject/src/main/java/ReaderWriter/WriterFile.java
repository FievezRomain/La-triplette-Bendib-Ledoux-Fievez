package ReaderWriter;

import Metadata.Table;

public interface WriterFile {
	/**
	 * Méthode permettant l'écriture du résultat de l'application
	 * @param separator
	 * @param table
	 * @param pathFile
	 * @return
	 */
	public boolean write(Separator separator, Table table, String pathFile) throws Exception;
}
