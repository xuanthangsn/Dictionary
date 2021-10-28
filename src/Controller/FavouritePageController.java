package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FavouritePageController {
    @FXML
    private TextField wordFoundTextField;
    @FXML
    private TextArea definitionTextArea;
    @FXML
    private ListView<String> favouriteListView;

    private MyListView myFavouriteListView;

    public void initialize() {
        myFavouriteListView = new MyListView(favouriteListView, null, wordFoundTextField, definitionTextArea);
        myFavouriteListView.populateData(DataConnection.ShowAll("favourite", false));
    }

    public void speakButtonClicked() {
        if (Word.currentWord != null) {
            MyVoiceManager.speak("kevin16", Word.currentWord.getName(), 30);
        }
    }

    public void goHomeButtonClicked() {
        App.sceneManager.changeScene("Main Page", "/fxml/MainPage.fxml");
    }
}
