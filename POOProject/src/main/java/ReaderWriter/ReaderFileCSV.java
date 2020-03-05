package ReaderWriter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import IHM.JavaFX;
import Metadata.Column;
import Metadata.Line;
import Metadata.Table;
import Metadata.Value;

public class ReaderFileCSV implements ReaderFile {
	private static Logger logger = Logger.getLogger(ReaderFileCSV.class);
	private String mode;
	private Table datasInput;
	private Table table;
	private List<Line> lines;
	private List<String> columns;
	
	/**
	 * Méthode concernant la lecture du fichier contenant les données principales
	 * (Plus d'infos dans l'interface ReaderFile)
	 * @throws IOException 
	 */
	@Override
	public Table readInput(Separator separator, String path) throws IOException{
		logger.info("Lecture des datas en input");
		mode = "input";
		return read(separator, path);
	}
	
	/**
	 * Méthode concernant la lecture du fichier avec les règles à appliquées 
	 * (Plus d'infos dans l'interface ReaderFile)
	 * @throws IOException 
	 */
	@Override
	public Table readRules(Separator separator, String path, Table datasInput) throws IOException{
		logger.info("Lecture des règles");
		mode = "rules";
		this.datasInput = datasInput;
		read (separator, path);
		return this.datasInput;
	}
	
	/**
	 * Méthode concernant la lecture du fichier comportant les types de colonnes
	 * (Plus d'infos dans l'interface ReaderFile)
	 * @throws IOException 
	 */
	@Override
	public Table readTypes(Separator separator, String path, Table datasInput) throws IOException{
		logger.info("Lecture des descriptions");
		mode = "types";
		this.datasInput = datasInput;
		read(separator, path);
		return this.datasInput;
	}
	
	/**
	 * Permet la lecture d'un fichier CSV, le traitement d'une ligne est selon le fichier.
	 * Cela est fait grâce à la méthode actionMaker()
	 * Lors de la lecture de la première ligne du fichier, les valeurs sont ajoutées comme colonnes
	 * @param separator
	 * @param path
	 * @return table
	 * @throws IOException 
	 */
	public Table read(Separator separator, String path) throws IOException {
		logger.info("Début de la lecture");
		String csvFile = path;
        String line = "";
        String cvsSplitBy = separator.getSeprator();
        String splitBy = ",";
        table = new Table();
        lines = table.getLines();
        columns = new ArrayList<>();
        int i = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {
            	
                	line = replaceAll(separator.getSeprator(), splitBy, line);
                    List<String> items = Arrays.asList(line.split(splitBy));
                    if(i != 0) {
                    	table.getLines().add(new Line());
                    }
                    for(int y = 0; y < items.size(); y++) {
                    	if(i == 0) {
                    		table.getColumns().put(items.get(y), new Column(items.get(y)));
                    		columns.add(items.get(y));
                    	} else {
                    		actionMaker(i, y, items);
                    	}
                    } 
                    i++;
            }

        } catch (IOException e) {
        	logger.fatal(e.getMessage());
            throw new IOException(e);
        }
        logger.info("Fin de la lecture");
		return table;
	}
	
	/**
	 * Permet de pouvoir gérer quelconque séparateur, il remplace le séparateur spécial par un simple.
	 * @param separatorBefore
	 * @param separatorAfter
	 * @param line
	 * @return String
	 */
	private String replaceAll(String separatorBefore, String separatorAfter, String line) {
		return line.replaceAll(separatorBefore, separatorAfter);
	}
	
	/**
	 * Selon le fichier en lecture, on applique une action à la donnée qui est actuellement en lecture
	 * @param i
	 * @param y
	 * @param items
	 */
	private void actionMaker(int i, int y, List<String> items) {
		switch(mode) {
			case "input":
				actionInput(i, y,items);
				break;
			case "rules":
				actionRules();
				break;
			case "types":
				actionTypes(i, y, items);
				break;
			default:
				logger.warn("Nouvelle lecture dans l'application ? Celle-ci n'est pas implémentée");
				break;
		}
	}
	/**
	 * Gère l'action à effectuer si le fichier en lecture est le fichier contenant les valeurs principales
	 * Dans ce cas, on ajoute la valeur en cours de lecture dans la table
	 * @param i
	 * @param y
	 * @param items
	 */
	private void actionInput(int i, int y, List<String> items) {
		table.add(new Value(items.get(y)), columns.get(y));
		//lines.get(i-1).addPair(columns.get(y), new Value(items.get(y)));
	}
	
	/**
	 * Gère l'action à effectuer si le fichier en lecture est le fichier contenant les règles
	 * Dans ce cas, les règles sont ajoutées dans la colonnes correspondante
	 */
	private void actionRules() {
		//to do
	}
	
	/**
	 * Gère l'action à effectuer si le fichier en lecture est le fichier détaillant le type des colonnes.
	 * Dans ce cas, le type est appliqué sur la colonne correspondante
	 * @param i
	 * @param y
	 * @param items
	 */
	private void actionTypes(int i, int y, List<String> items) {
		table.setColumnType(items.get(y), columns.get(y));
		//datasInput.getLines().get(i-1).getLines().get(columns.get(y)).setType(items.get(y));
	}
}
