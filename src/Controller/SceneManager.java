package Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager {
    private Stage stage;

    public SceneManager() {
        stage = new Stage();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void changeScene(String sceneName, String fxmlFile) {
        try {
            stage.setTitle(sceneName);
            stage.setResizable(false);
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            Word.currentWord = null;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("something went wrong when try to change scene");
        }
    }
}
