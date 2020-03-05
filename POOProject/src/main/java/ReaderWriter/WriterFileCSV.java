package ReaderWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;

import IHM.JavaFX;
import Metadata.Column;
import Metadata.Line;
import Metadata.Table;

public class WriterFileCSV implements WriterFile {
	private static Logger logger = Logger.getLogger(WriterFileCSV.class);
	/**
	 * Permet l'écriture du résultat de l'application en format CSV
	 * Ecriture des colonnes puis des valeurs
	 * @throws Exception 
	 */
	@Override
	public boolean write(Separator separator, Table table, String path) throws Exception {
		logger.info("Début de l'écriture");
		FileWriter csvWriter = null;
		try {
			csvWriter = new FileWriter(path);
			for(Map.Entry<String, Column> elem : table.getColumns().entrySet()) {
				csvWriter.append(elem.getKey());
				csvWriter.append(separator.getSeprator());
			}
			for(Line line : table.getLines()){
				csvWriter.append("\n");
				csvWriter.append(String.join(separator.getSeprator(), line.valuesToString()));
			}
			csvWriter.flush();
			csvWriter.close();
			logger.info("Ecriture terminée, fichier : " + new File(path).getAbsolutePath());
			return true;
		} catch (IOException e) {
			logger.fatal(e.getMessage());
			throw new Exception(e);
		} finally{
			try {
				csvWriter.close();
			} catch (IOException e) {
				logger.fatal(e.getMessage());
				throw new Exception(e);
			}
		}
	}
}
