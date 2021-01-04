import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;

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


            Scene scene = new Scene(root, 700, 400);


            // long-running operation runs on different thread
            Thread thread = new Thread(new Runnable() {

                @Override
                public void run() {
                    
                    Runnable updater = () -> {

                        //is run after 30 second wait
                        controller.updateExchangeRates();
                    };

                    while (true) {
                        try {
                            Thread.sleep(30000);
                        } catch (InterruptedException ignored){
                        }

                        // UI update is on the Application thread
                        Platform.runLater(updater);
                    }
                }
            });

            // dont let thread prevent JVM shutdown
            thread.setDaemon(true);
            thread.start();


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

    public homeController getController() {
        return controller;
    }
}
