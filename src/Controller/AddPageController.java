package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;


public class AddPageController {
    @FXML
    private TextArea wordAddTextArea;
    @FXML
    private TextArea definitionAddTextArea;
    @FXML
    private TextArea messageTextArea;
    @FXML
    private Button addButton;
    @FXML
    private Button goHomeButton;

    public void addButtonClicked() {
        if (wordAddTextArea.getText().isBlank() || definitionAddTextArea.getText().isBlank()) {
            showMessage("Please Enter all information!!");
        } else {
            if (DataConnection.BothContain(wordAddTextArea.getText())) {
                showMessage("Your word has already existed!!");
            } else {
                // try to add word to the second_dict table.
                if (DataConnection.Add(new Word(wordAddTextArea.getText(), definitionAddTextArea.getText(), false), "second_dict")) {
                    showMessage("successfuly add word !!");
                } else {
                    showMessage("cannot add word!!");
                }
            }
        }
    }

    public void goHomeButtonClicked() {
        App.sceneManager.changeScene("Home Window", "/fxml/MainPage.fxml");
    }

    private void showMessage(String message) {
        messageTextArea.setText(message);
    }
}
