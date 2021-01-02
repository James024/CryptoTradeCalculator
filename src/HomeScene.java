import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class HomeScene {
    private Stage primaryStage;
    private homeController controller;

    public HomeScene(Stage stage){
        this.primaryStage = stage;

        try {
            FXMLLoader loader = new FXMLLoader();
            this.controller = new homeController();
            loader.setController(controller);
            Parent root = loader.load(getClass()
                    .getResource("homeView.fxml")
                    .openStream());


            Scene scene = new Scene(root, 600, 400);

            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            System.out.println(e);
            Alert error = new Alert(Alert.AlertType.ERROR,
                    "An error was encountered while attempting to load scene",
                    ButtonType.OK);
            error.showAndWait();

        }
    }
}
