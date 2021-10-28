package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class OxfordAPIPageController {
    @FXML
    private TextArea wordTextArea;
    @FXML
    private TextArea definitionTextArea;
    @FXML
    private TextArea messageTextArea;

    public void goHomeButtonClicked() {
        App.sceneManager.changeScene("Home Page", "/fxml/MainPage.fxml");
    }

    public void searchButtonClicked() {
        if (!wordTextArea.getText().isBlank()) {
            String definition = OxfordAPI.retrieveData(OxfordAPI.searchWord(wordTextArea.getText()));
            if (definition == null) {
                messageTextArea.setText("Not found!!!");
                definitionTextArea.setText("");
            } else {
                messageTextArea.setText("word found!!");
                definitionTextArea.setText(definition);
            }
        } else {
            messageTextArea.setText("Not found!!!");
        }
    }
}
