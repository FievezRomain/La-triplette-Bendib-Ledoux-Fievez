package ReaderWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import Metadata.Column;
import Metadata.Line;
import Metadata.Table;

public class WriterFileCSV implements WriterFile {
	/**
	 * Permet l'écriture du résultat de l'application en format CSV
	 * Ecriture des colonnes puis des valeurs
	 */
	@Override
	public boolean write(Separator separator, Table table, String path) {
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
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				csvWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return false;
	}
}
