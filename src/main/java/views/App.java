package views;

import controllers.IngredientController;
import controllers.RepasController;
import controllers.SupplementController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import views.tables.RepasTable;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        /*PatientController patientController = new PatientController(new PatientMetierImpl());
        PatientTable patientTable = new PatientTable(patientController);

        TabPane tabPane = new TabPane();
        tabPane.getTabs().addAll(
                new Tab("Patients", patientTable)
        );*/

        RepasController repasController = new RepasController();
        IngredientController ingredientController = new IngredientController();
        SupplementController supplementController = new SupplementController();

        RepasTable repasTable = new RepasTable(repasController, ingredientController, supplementController);

        TabPane tabPane = new TabPane();
        tabPane.getTabs().addAll(
                new Tab("Repas", repasTable)
        );


        Scene scene = new Scene(tabPane, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Management System");
        primaryStage.show();
    }
}
