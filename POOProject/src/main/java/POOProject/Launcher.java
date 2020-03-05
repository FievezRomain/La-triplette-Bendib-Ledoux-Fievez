package POOProject;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import IHM.IHM;
import IHM.JavaFX;

public class Launcher {
	private static Logger logger = Logger.getLogger(Launcher.class);
	/*
	 * Méthode de base de l'application qui sert à lancer l'IHM que nous voulons
	 */
	public static void main(String[] args) {
		BasicConfigurator.configure();
		IHM ihm = new JavaFX();
		logger.info("L'ihm est lancée");
		ihm.Lancer(args);
	}

}
