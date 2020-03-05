package ReaderWriter;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import IHM.JavaFX;
import Metadata.Line;
import Metadata.Table;
import Metadata.Value;

public class ReaderFileJSON implements ReaderFile {
	private static Logger logger = Logger.getLogger(ReaderFileJSON.class);
	/**
	 * Classe interne qui permet de pouvoir lire les éléments d'un fichier JSON
	 * @author Ledoux
	 *
	 */
	class element {
		String name;
		String dataType;
		String value;
		String changeTo;
		List<String> should;
	}
	private String mode;
	private Table datasInput;
	private int i;
	private List<element> staff;
	private List<Line> lines;
	
	/**
	 * Permet la lecture d'un fichier JSON
	 * Le traitement des données dépend selon le fichier en lecture grâce à l'utilisation de la méthode 
	 * actionMaker()
	 * @param separator
	 * @param path
	 * @return
	 * @throws Exception 
	 */
	public Table read(Separator separator, String path) throws Exception {
		logger.info("Début de la lecture");
        Gson gson = new Gson();
        try (Reader reader = new FileReader(path)) {

            // Convert JSON File to Java Object
        	Type listType = new TypeToken<ArrayList<element>>() {}.getType();
            staff = gson.fromJson(reader, listType);
            lines = new ArrayList<>(); 
            for(i = 0; i < staff.size(); i++) {
        		actionMaker();
            }
			
			// print staff 
            return this.datasInput;

        } catch (IOException e) {
        	logger.fatal(e.getMessage());
            throw new Exception(e); 
        }
	}
	
	/**
	 * Méthode concernant la lecture du fichier contenant les données principales
	 * (Plus d'infos dans l'interface ReaderFile)
	 * @throws Exception 
	 */
	@Override
	public Table readInput(Separator separator, String path) throws Exception {
		logger.info("Lecture des datas en input");
		this.mode = "input";
		return read(separator, path);
	}
	
	/**
	 * Méthode concernant la lecture du fichier avec les règles à appliquées 
	 * (Plus d'infos dans l'interface ReaderFile)
	 * @throws Exception 
	 */
	@Override
	public Table readRules(Separator separator, String path, Table datasInput) throws Exception {
		logger.info("Lecture des règles");
		this.mode = "rules";
		this.datasInput = datasInput;
		read(separator, path);
		return datasInput;
	}
	
	/**
	 * Méthode concernant la lecture du fichier comportant les types de colonnes
	 * (Plus d'infos dans l'interface ReaderFile)
	 * @throws Exception 
	 */
	@Override
	public Table readTypes(Separator separator, String path, Table datasInput) throws Exception {
		logger.info("Lecture des descriptions");
		this.mode = "types";
		this.datasInput = datasInput;
		read(separator, path);
		return datasInput;
	}
	
	/**
	 * Selon le fichier en lecture, on applique une action à la donnée qui est actuellement en lecture
	 */
	private void actionMaker() {
		switch(this.mode) {
			case "input":
				inputAction();
				break;
			case "rules":
				rulesAction();
				break;
			case "types":
				typesAction();
				break;
			default:
				logger.warn("Nouvelle lecture dans l'application ? Celle-ci n'est pas implémentée");
				break;
		}
	}
	
	/**
	 * Gère l'action à effectuer si le fichier en lecture est le fichier contenant les valeurs principales
	 * Dans ce cas, on ajoute la valeur en cours de lecture dans la table
	 */
	private void inputAction() {
		datasInput.getLines().add(new Line());
		datasInput.add(new Value(staff.get(i).value.toLowerCase()), staff.get(i).name.toLowerCase());
	}
	
	/**
	 * Gère l'action à effectuer si le fichier en lecture est le fichier contenant les règles
	 * Dans ce cas, les règles sont ajoutées dans la colonnes correspondante
	 */
	private void rulesAction() {
		if(staff.get(i).changeTo != null) {
			datasInput.setColumnRuleAnonymize(staff.get(i).name.toLowerCase(), staff.get(i).changeTo);
		} else {
			datasInput.setColumnRules(staff.get(i).name.toLowerCase(), staff.get(i).should);
		}
		
	}
	
	/**
	 * Gère l'action à effectuer si le fichier en lecture est le fichier détaillant le type des colonnes.
	 * Dans ce cas, le type est appliqué sur la colonne correspondante
	 */
	private void typesAction() {
		datasInput.setColumnType(staff.get(i).dataType.toLowerCase(), staff.get(i).name.toLowerCase());
	}
}
