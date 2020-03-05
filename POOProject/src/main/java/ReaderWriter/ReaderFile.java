package ReaderWriter;

import java.io.IOException;

import Metadata.Table;

public interface ReaderFile {
	/**
	 * Lecture du fichier contenant les valeurs principales
	 * @param separator
	 * @param path
	 * @return table avec les données du fichier qui comporte les données principales
	 */
	public Table readInput(Separator separator, String path) throws Exception;
	
	/**
	 * Lecture du fichier contenant les règles à appliquées
	 * Retourne la table contenant les valeurs principales en ajoutant des caractéristiques dans 
	 * la structure de données
	 * @param separator
	 * @param path
	 * @param datasInput
	 * @return 
	 */
	public Table readRules(Separator separator, String path, Table datasInput) throws Exception;
	
	/**
	 * Lecture du fhicher contenant la description des valeurs principales
	 * Retourne la table contenant les valeurs principales en ajoutant des caractéristiques dans
	 * la structure de données
	 * @param separator
	 * @param path
	 * @param datasInput
	 * @return
	 */
	public Table readTypes(Separator separator, String path, Table datasInput) throws Exception;
}
