package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class DeletePageController {
    @FXML
    private Button goHomeButton;
    @FXML
    private Button deleteButton;
    @FXML
    private TextArea wordDeleteTextArea;
    @FXML
    private TextArea messageTextArea;

    public void deleteButtonClicked() {
        if (wordDeleteTextArea.getText().isBlank()) {
            showMessage("Please enter the word you want to delete!!!");
        } else {
            if (DataConnection.Contain(wordDeleteTextArea.getText(), "second_dict")) {
                if (DataConnection.Delete(wordDeleteTextArea.getText(), "second_dict")) {
                    showMessage("Successfuly delete word!!!");
                } else {
                    showMessage("Cannot delete word!!!");
                }
            } else {
                showMessage("Your dictionary has no such word!!!");
            }
        }
    }

    public void goHomeButtonClicked() {
        App.sceneManager.changeScene("Home Page", "/fxml/MainPage.fxml");
    }

    private void showMessage(String message) {
        messageTextArea.setText(message);
    }
}
