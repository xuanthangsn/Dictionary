package Controller;

public class Word {
    private String name;
    private String definition;

    // current word being displayed in the screen.
    public static Word currentWord = null;

    /** set filter to difinition of  word in order to add word to database. */
    public static Word filter(Word word) {
        String filterDefinition = "";
        String definition = word.getDefinition();
        String[] list = definition.split("'");
        for (int i = 0; i < list.length; i++) {
            if (i != list.length - 1) {
                filterDefinition += list[i] + "\\'";
            } else {
                filterDefinition += list[i];
            }
        }
        return new Word(word.getName(), filterDefinition, false);
    }

    public Word(String name, String pureDefinition, boolean parse) {
        this.name = name;
        if (parse == true) {
            this.definition = parsePureDefinition(pureDefinition);
        }
        else {
            this.definition = pureDefinition;
        }
    }

    private String parsePureDefinition(String pureDefinition) {
        String result = "";
        String[] list = pureDefinition.split("<br />|<C><F><I><N><Q>|</Q></N></I></F></C>");
        for (String element : list) {
            if (!element.isBlank()) {
                result += element + ".\n";
            }
        }
        return result;
    }

    public String getName() {
        return name;
    }

    public String getDefinition() {
        return definition;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }
}
