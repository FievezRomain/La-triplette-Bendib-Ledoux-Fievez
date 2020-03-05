package IHM;

import java.io.File;

import Functionalities.Functionality;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class JavaFX extends Application implements IHM{
	private String file1, file2, file3;
	
	@Override
	public void Lancer(String[] args) {
		this.launch(JavaFX.class, args);
	}

	/**
	 * Méthode qui permet de mettre en forme l'interface de l'application
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		FlowPane root = new FlowPane();
        root.setPadding(new Insets(10));
        root.setHgap(10);
        root.setVgap(10);
		Label label = new Label("POOProject");
        label.setAlignment(Pos.CENTER);
        Button buttonAno = new Button("Anonymiser les données");
        buttonAno.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                startAnonymizeStage(primaryStage);
            }
        });
        Button buttonVerif = new Button("Vérifier les données");
        buttonVerif.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                startVerifyStage(primaryStage);
            }
        });
        BackgroundFill background_fill = new BackgroundFill(Color.STEELBLUE,  
                CornerRadii.EMPTY, Insets.EMPTY); 
        Background background = new Background(background_fill); 
        root.setAlignment(Pos.CENTER);
        root.setBackground(background);
        root.getChildren().addAll(buttonAno, buttonVerif);
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("POOProject");
        primaryStage.show();
	}
	
	/**
	 * Méthode qui affiche l'interface pour la fonctionnalité Verify
	 */
	public void startVerifyStage(Stage primaryStage) {
		final TextField comment = new TextField();
		comment.setPrefColumnCount(15);
		comment.setPromptText("Enter output file name (with extension like .csv)");
		Button buttonVerif = new Button("Verify");
		buttonVerif.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	if(file1 != null && file2 != null && file3 != null) {
            		launchFunctionality("verify", comment.getText());
            	}
            	
            }
        });
		Button buttonDatas = new Button("Choose data input file");
		buttonDatas.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	FileChooser fcDatas = new FileChooser();
            	fcDatas.setTitle("Choose data input file");
            	File selectedFile = fcDatas.showOpenDialog(null);
            	if (selectedFile != null) {
            		file1 = selectedFile.getAbsolutePath();
            	}
            	else {
            		file1 = null;
            	}
            }
        });
		Button buttonDesc = new Button("Choose data description file");
		buttonDesc.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	FileChooser fcDesc = new FileChooser();
            	fcDesc.setTitle("Choose data description file");
            	File selectedFile = fcDesc.showOpenDialog(null);
            	if (selectedFile != null) {
            		file2 = selectedFile.getAbsolutePath();
            	}
            	else {
            		file2 = null;
            	}
            }
        });
		Button buttonDescFunc = new Button("Choose description of functionnality file");
		buttonDescFunc.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	FileChooser fcDescFunc = new FileChooser();
            	fcDescFunc.setTitle("Choose description of functionnality file");
            	File selectedFile = fcDescFunc.showOpenDialog(null);
            	if (selectedFile != null) {
            		file3 = selectedFile.getAbsolutePath();
            	}
            	else {
            		file3 = null;
            	}
            }
        });
		VBox root = new VBox(5);
		BackgroundFill background_fill = new BackgroundFill(Color.STEELBLUE,  
                CornerRadii.EMPTY, Insets.EMPTY); 
        Background background = new Background(background_fill); 
        root.setAlignment(Pos.CENTER);
        root.setBackground(background);
        root.getChildren().addAll(buttonDatas, buttonDesc, buttonDescFunc, comment, buttonVerif);
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
	}
	
	/**
	 * Méthode qui affiche l'interface pour la fonctionnalité Anonymize
	 */
	public void startAnonymizeStage(Stage primaryStage) {
		final TextField comment = new TextField();
		comment.setPrefColumnCount(15);
		comment.setPromptText("Enter output file name (with extension like .csv)");
		Button buttonAno = new Button("Anonymize");
		buttonAno.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	if(file1 != null && file2 != null && file3 != null) {
            		launchFunctionality("anonymize", comment.getText());
            	}
            	
            }
        });
		Button buttonDatas = new Button("Choose data input file");
		buttonDatas.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	FileChooser fcDatas = new FileChooser();
            	fcDatas.setTitle("Choose data input file");
            	File selectedFile = fcDatas.showOpenDialog(null);
            	if (selectedFile != null) {
            		file1 = selectedFile.getAbsolutePath();
            	}
            	else {
            		file1 = null;
            	}
            }
        });
		Button buttonDesc = new Button("Choose data description file");
		buttonDesc.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	FileChooser fcDesc = new FileChooser();
            	fcDesc.setTitle("Choose data description file");
            	File selectedFile = fcDesc.showOpenDialog(null);
            	if (selectedFile != null) {
            		file2 = selectedFile.getAbsolutePath();
            	}
            	else {
            		file2 = null;
            	}
            }
        });
		Button buttonDescFunc = new Button("Choose description of functionnality file");
		buttonDescFunc.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	FileChooser fcDescFunc = new FileChooser();
            	fcDescFunc.setTitle("Choose description of functionnality file");
            	File selectedFile = fcDescFunc.showOpenDialog(null);
            	if (selectedFile != null) {
            		file3 = selectedFile.getAbsolutePath();
            	}
            	else {
            		file3 = null;
            	}
            }
        });
		VBox root = new VBox(5);
		BackgroundFill background_fill = new BackgroundFill(Color.STEELBLUE,  
                CornerRadii.EMPTY, Insets.EMPTY); 
        Background background = new Background(background_fill); 
        root.setAlignment(Pos.CENTER);
        root.setBackground(background);
        root.getChildren().addAll(buttonDatas, buttonDesc, buttonDescFunc, comment, buttonAno);
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
	}
	
	private void launchFunctionality(String functionality, String outputFileName) {
		Functionality func = Functionality.getFunctionality(functionality);
		try {
			func.launch(func, file1, file2, file3, outputFileName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); //Log
		}
	}

}
