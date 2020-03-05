package POOProject;

import IHM.IHM;
import IHM.JavaFX;

public class Launcher {

	/*
	 * Méthode de base de l'application qui sert à lancer l'IHM que nous voulons
	 */
	public static void main(String[] args) {
		IHM ihm = new JavaFX();
		ihm.Lancer(args);
	}

}
