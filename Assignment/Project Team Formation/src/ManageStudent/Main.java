package ManageStudent;
import java.util.ArrayList;
import java.util.HashMap;

import TeamFormation.Student;
import TeamFormation.TeamMember;
import javafx.application.Application;
import javafx.stage.Stage;
import model.TeamList;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {			
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("TeamView.fxml"));
			Scene scene = new Scene(root,1000,1000);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}




