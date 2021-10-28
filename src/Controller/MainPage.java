package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class MainPage {
    @FXML
    private ListView<String> suggestionListView;
    @FXML
    private ListView<String> recentSearchedListView;
    @FXML
    private TextField searchTextField;
    @FXML
    private TextField wordFoundTextField;
    @FXML
    private TextArea definitionTextArea;

    private MyListView mySuggestionListView;
    private MyListView myRecentSearchedListView;

    public void initialize() {
        mySuggestionListView = new MyListView(suggestionListView, searchTextField, wordFoundTextField, definitionTextArea);
        myRecentSearchedListView = new MyListView(recentSearchedListView, searchTextField, wordFoundTextField, definitionTextArea);
        mySuggestionListView.setReferenceListView(myRecentSearchedListView);
    }

    public void searchButtonClicked() {
        String target = searchTextField.getText().trim();
        if (target.isBlank()) {
            mySuggestionListView.populateData(new ArrayList<Word>());
        } else {
            mySuggestionListView.populateData(DataConnection.MatchSuggestion(target));
        }
    }

    public void addToFavouriteButtonClicked() {
        if (Word.currentWord != null) {
            if (DataConnection.Contain(Word.currentWord.getName(), "favourite")) {
                System.out.println("this word has alreay added to favourite list!!!");
            } else {
                if (DataConnection.Add(Word.currentWord, "favourite")) {
                    System.out.println("successfuly add word to favourit list!!");
                } else {
                    System.out.println("cannot add word to favourite list!!");
                }
            }
        }
    }

    public void speakButtonClicked() {
        if (Word.currentWord != null) {
            MyVoiceManager.speak("kevin16", Word.currentWord.getName(), 30);
        }
    }

    public void goToFavouriteButtonClicked() {
        App.sceneManager.changeScene("Favourite Page", "/fxml/FavouritePage.fxml");
    }

    public void goToDeleteClicked() {
        App.sceneManager.changeScene("Delete Page", "/fxml/DeletePage.fxml");
    }

    public void goToAddClicked() {
        App.sceneManager.changeScene("Add Page", "/fxml/AddPage.fxml");
    }

    public void goToAPIButtonClicked() {
        App.sceneManager.changeScene("Oxford Dictionary", "/fxml/OxfordAPIPage.fxml" );
    }

}
