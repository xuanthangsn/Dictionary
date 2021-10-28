package Controller;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    public static SceneManager sceneManager;

    public App() {
        sceneManager = new SceneManager();
    }
    public static void main(String args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        sceneManager.setStage(stage);
        sceneManager.changeScene("Home page", "/fxml/MainPage.fxml");
    }
}
