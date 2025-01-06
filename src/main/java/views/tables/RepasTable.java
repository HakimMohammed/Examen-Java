package views.tables;

import controllers.IngredientController;
import controllers.RepasController;
import controllers.SupplementController;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import models.Ingredient;
import models.Repas;
import models.Supplement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RepasTable extends BorderPane {
    public final RepasController controller;
    private final IngredientController ingredientController;
    private final SupplementController supplementController;
    private TableView<Repas> table;

    public RepasTable(RepasController repasController, IngredientController ingredientController, SupplementController supplementController) {
        this.controller = repasController;
        this.ingredientController = ingredientController;
        this.supplementController = supplementController;
        this.setCenter(createRepasTable());
    }

    private BorderPane createRepasTable() {
        String EntityName = "Repas";
        BorderPane root = new BorderPane();

        table = new TableView<>();

        TableColumn<Repas, Integer> idCol = new TableColumn<>("id");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        idCol.setVisible(false);

        TableColumn<Repas, String> mainDishCol = new TableColumn<>("Main Dish");
        mainDishCol.setCellValueFactory(new PropertyValueFactory<>("mainDish"));

        TableColumn<Repas, String> ingredientsCol = new TableColumn<>("Ingredients");
        ingredientsCol.setCellValueFactory(cellData -> {
            List<Ingredient> ingredients = cellData.getValue().getIngredients();
            String ingredientsString = ingredients.stream()
                    .map(Ingredient::getNom)
                    .collect(Collectors.joining(", "));
            return new javafx.beans.property.SimpleStringProperty(ingredientsString);
        });

        TableColumn<Repas, String> supplementsCol = new TableColumn<>("Supplements");
        supplementsCol.setCellValueFactory(cellData -> {
            List<Supplement> supplements = cellData.getValue().getSupplements();
            String supplementsString = supplements.stream()
                    .map(Supplement::getNom)
                    .collect(Collectors.joining(", "));
            return new javafx.beans.property.SimpleStringProperty(supplementsString);
        });

        TableColumn<Repas, Double> totalPriceCol = new TableColumn<>("Total Price");
        totalPriceCol.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

        table.getColumns().addAll(idCol, mainDishCol, ingredientsCol, supplementsCol, totalPriceCol);
        table.setItems(FXCollections.observableArrayList(controller.afficherTousLesRepas()));

        VBox buttonsBox = new VBox(10);
        buttonsBox.setPadding(new Insets(10));

        Button choisirIngredientsButton = new Button("Choisir Ingredients");
        choisirIngredientsButton.setOnAction(e -> {
            Repas selectedRepas = table.getSelectionModel().getSelectedItem();
            if (selectedRepas != null) {
                afficherSelectionIngredients(selectedRepas.getId());
                refreshTable();
            } else {
                showAlert("Selection Error", "Please select a meal to add ingredients.");
            }
        });

        Button choisirSupplementsButton = new Button("Choisir Supplements");
        choisirSupplementsButton.setOnAction(e -> {
            Repas selectedRepas = table.getSelectionModel().getSelectedItem();
            if (selectedRepas != null) {
                afficherSelectionSupplements(selectedRepas.getId());
                refreshTable();
            } else {
                showAlert("Selection Error", "Please select a meal to add supplements.");
            }
        });

        buttonsBox.getChildren().addAll(choisirIngredientsButton, choisirSupplementsButton);

        root.setCenter(table);
        root.setRight(buttonsBox);
        return root;
    }

    private void afficherSelectionIngredients(int repasId) {
        Stage stage = new Stage();
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));

        List<Ingredient> allIngredients = ingredientController.afficherTousLesIngredients();
        List<CheckBox> checkboxes = new ArrayList<>();

        for (Ingredient ingredient : allIngredients) {
            CheckBox checkBox = new CheckBox(ingredient.getNom());
            checkBox.setUserData(ingredient.getId()); // Store the ingredient ID
            checkboxes.add(checkBox);
            root.getChildren().add(checkBox);
        }

        Button confirmButton = new Button("Confirmer");
        confirmButton.setOnAction(e -> {
            List<Integer> selectedIngredientIds = new ArrayList<>();
            for (CheckBox checkBox : checkboxes) {
                if (checkBox.isSelected()) {
                    selectedIngredientIds.add((Integer) checkBox.getUserData());
                }
            }
            for (int i = 0; i < selectedIngredientIds.size(); i++) {
                controller.ajouterIngredient(repasId, selectedIngredientIds.get(i));
            }
            stage.close();
        });

        root.getChildren().add(confirmButton);

        stage.setScene(new javafx.scene.Scene(root));
        stage.setTitle("Choisir Ingredients");
        stage.show();
    }

    private void afficherSelectionSupplements(int repasId) {
        Stage stage = new Stage();
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));

        List<Supplement> allSupplements = supplementController.afficherTousLesSupplements();
        List<CheckBox> checkboxes = new ArrayList<>();

        for (Supplement supplement : allSupplements) {
            CheckBox checkBox = new CheckBox(supplement.getNom());
            checkBox.setUserData(supplement.getId()); // Store the supplement ID
            checkboxes.add(checkBox);
            root.getChildren().add(checkBox);
        }

        Button confirmButton = new Button("Confirmer");
        confirmButton.setOnAction(e -> {
            List<Integer> selectedSupplementIds = new ArrayList<>();
            for (CheckBox checkBox : checkboxes) {
                if (checkBox.isSelected()) {
                    selectedSupplementIds.add((Integer) checkBox.getUserData());
                }
            }
            for(int i = 0; i < selectedSupplementIds.size(); i++){
                controller.ajouterSupplement(repasId, selectedSupplementIds.get(i));
            }
            stage.close();
        });

        root.getChildren().add(confirmButton);

        stage.setScene(new javafx.scene.Scene(root));
        stage.setTitle("Choisir Supplements");
        stage.show();
    }

    private void refreshTable() {
        table.setItems(FXCollections.observableArrayList(controller.afficherTousLesRepas()));
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
