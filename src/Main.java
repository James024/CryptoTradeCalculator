import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        HomeScene scene = new HomeScene(primaryStage);
    }

        public static void main (String[]args) throws IOException {

            launch(args);

        }
    }