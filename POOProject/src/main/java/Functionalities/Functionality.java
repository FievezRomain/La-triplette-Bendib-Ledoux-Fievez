package Functionalities;

import org.apache.log4j.Logger;

import Metadata.Table;
import ReaderWriter.ReaderFile;
import ReaderWriter.ReaderFileFactory;
import ReaderWriter.Separator;
import ReaderWriter.WriterFile;
import ReaderWriter.WriterFileFactory;

public abstract class Functionality {
	private Table datasInput;
	private static Logger logger = Logger.getLogger(Functionality.class);
	
	/**
	 * Méthode centrale qui permet la lecture du fichier avec l'appel à la méthode before, son traitement
	 * et l'écriture du résultat avec l'appel à la méthode after.
	 * @param functionalityType
	 * @param pathFileInput
	 * @param pathFileDescription
	 * @param pathFileDataToCheck
	 * @param pathFileOutput
	 * @return une table avec les données finales
	 * @throws Exception 
	 */
	public Table launch(Functionality functionalityType, String pathFileInput, String pathFileDescription,
			String pathFileDataToCheck, String pathFileOutput) throws Exception {
		before(pathFileInput, pathFileDescription, pathFileDataToCheck);
		logger.info("Read files finished");
		Table datasResult = functionalityType.start(datasInput);
		logger.info("Functionality finished");
		after(datasResult, pathFileOutput);
		logger.info("Write result finished");
		return datasResult;
	}
	
	/**
	 * Pour chaque fichier, il va déterminer le lecture qu'il faut grâce à l'appel à getReader
	 * Ensuite, chaque fichier est envoyé en lecture
	 * La lecture du fichier de description et du fichier contenant les règles ne retourne pas une table
	 * avec les données de ces fichiers.
	 * Ces méthodes retournent la table contenant les données du fichier de base (fichier avec les données)
	 * mais mis à jour grâce à la structure de données qui le permet.
	 * @param pathFileInput
	 * @param pathFileDescription
	 * @param pathFileDataToCheck
	 */
	private void before(String pathFileInput, String pathFileDescription,
			String pathFileDataToCheck) {
		try {
			ReaderFile rf;
			rf = getReader(pathFileInput);
			Separator sep = new Separator(";");
			datasInput = rf.readInput(sep, pathFileInput);
			logger.info("Read datas input finished");
			rf = getReader(pathFileDescription);
			datasInput = rf.readTypes(sep, pathFileDescription, datasInput);
			logger.info("Read description datas finished");
			rf = getReader(pathFileDataToCheck);
			datasInput = rf.readRules(sep, pathFileDataToCheck, datasInput);
			logger.info("Read rules finished");
		}catch(Exception e) {
			logger.fatal(e.getMessage());
			
		}
		
	}
	
	/**
	 * L'appel à getInstance permet de récupérer l'instance de la classe qu'il faut pour écrire le bon
	 * type de fichier selon l'extension du chemin de fichier de sortie
	 * @param datasResult
	 * @param pathFile
	 */
	private void after(Table datasResult, String pathFile) {
		try {
			logger.info("Write result process");
			String[] alt = pathFile.split("\\.");
			String extension = alt[alt.length-1];
			WriterFile wf = WriterFileFactory.getInstance(extension.toUpperCase());
			wf.write(new Separator(";"), datasResult, pathFile);
		}catch(Exception e) {
			logger.fatal(e.getMessage());
		}
	}
	
	/**
	 * Selon le type de fonctionnalité que l'utilisateur veut, une instance est créée
	 * @param type
	 * @return l'instance de la fonctionnalité demandée en paramètre
	 */
	public static Functionality getFunctionality(String type) {
		switch(type) {
			case "verify":
				return new FunctionalityVerify();
			case "anonymize":
				return new FunctionalityAnonymize();
			default:
				logger.fatal("Functionality not defined !");
				return null;
		}
	}

	/**
	 * Permet de récupérer le bon lecture selon l'extension d'un fichier
	 * @param path
	 * @return
	 */
	private ReaderFile getReader(String path) {
		try {
			String[] alt = path.split("\\.");
			String extension = alt[alt.length - 1].toLowerCase();
			ReaderFile rf = ReaderFileFactory.createInstance(extension);
			return rf;
		}catch(Exception e){
			logger.fatal(e.getMessage());
		}
		return null;
		
	}
	
	/**
	 * Méthode start qui est implémentée par les classes filles afin de pouvoir lancer la fonctionnalité
	 * dans la méthode launch()
	 * @param datasInput
	 * @return
	 */
	protected Table start(Table datasInput) throws Exception{
		return null;
	}
}
