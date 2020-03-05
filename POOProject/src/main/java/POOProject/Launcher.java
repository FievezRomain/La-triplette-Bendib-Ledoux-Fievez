package POOProject;

import org.apache.log4j.Logger;

import IHM.IHM;
import IHM.JavaFX;

public class Launcher {
	private static Logger logger = Logger.getLogger(Launcher.class);
	/*
	 * Méthode de base de l'application qui sert à lancer l'IHM que nous voulons
	 */
	public static void main(String[] args) {
		IHM ihm = new JavaFX();
		ihm.Lancer(args);
		logger.info("L'ihm est lancée");
	}

}
