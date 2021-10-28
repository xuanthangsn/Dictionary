package Controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class MyListView {
    private ListView<String> myListView;
    private MyListView referenceListView;
    private ArrayList<Word> wordList;
    public static final int RECENT_LIST_SIZE = 15;

    public MyListView(ListView<String> listView, TextField searchField, TextField wordFoundField, TextArea definnitionField) {
        referenceListView = null;
        this.myListView = listView;
        wordList = new ArrayList<>();
        myListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                int index = myListView.getSelectionModel().getSelectedIndex();
                if (index != -1) {
                    if (referenceListView != null) {
                        referenceListView.addFirst(wordList.get(index));
                    }
                    if (definnitionField != null) {
                        definnitionField.setText(wordList.get(index).getDefinition());
                    }
                    if (searchField != null) {
                        searchField.setText(wordList.get(index).getName());
                    }
                    if (wordFoundField != null) {
                        wordFoundField.setText(wordList.get(index).getName());
                    }
                    Word.currentWord = wordList.get(index);
                }
            }
        });
    }

    public void setReferenceListView(MyListView referenceListView) {
        this.referenceListView = referenceListView;
    }

    public void populateData(ArrayList<Word> wordList) {
        // replace the current word list with a new one.
        this.wordList.clear();
        this.wordList.addAll(wordList);
        // replace the current item in the listview with a new one.
        this.myListView.getItems().clear();
        for(Word element : this.wordList) {
            this.myListView.getItems().add(element.getName());
        }
    }

    public void addFirst(Word word) {
        if (wordList.contains(word)) {
            wordList.remove(word);
            wordList.add(0,word);
            myListView.getItems().remove(word.getName());
            myListView.getItems().add(0, word.getName());
        } else {
            if (wordList.size() >= RECENT_LIST_SIZE) {
                wordList.remove(wordList.size()-1);
                wordList.add(0, word);
                myListView.getItems().remove(myListView.getItems().size()-1);
                myListView.getItems().add(0, word.getName());
            } else {
                wordList.add(0, word);
                myListView.getItems().add(0, word.getName());
            }
        }
    }

    public void clearSelection() {
        myListView.getSelectionModel().clearSelection();
    }

}

