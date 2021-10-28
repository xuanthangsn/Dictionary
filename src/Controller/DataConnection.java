package Controller;


import java.sql.*;
import java.util.ArrayList;

public class DataConnection {
    public static final String main_url = "jdbc:mysql://localhost:3306/mydictionary";
    public static final String user_name = "root";
    public static final String password = "#Xuanthang123";
    public static final int SUGGESTION_LIMIT = 15;

    private Connection connection;
    private PreparedStatement preparedStatement;

    /**
     * constructor.
     */
    public DataConnection() {
        try {
            connection = DriverManager.getConnection(main_url, user_name, password);
            preparedStatement = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * close connection.
     */
    public void closeConnect() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<Word> matcSuggestion(String target) {
        ArrayList<Word> result = new ArrayList<>();
        try {
            String select1 = "select * from second_dict where word like '" + target + "'";
            String select2 = "select * from tbl_edict where word like '" + target +"%'";
            int count = 0;
            // select all from the second_dict;
            preparedStatement = connection.prepareStatement(select1);
            ResultSet resultSet1 = preparedStatement.executeQuery();
            while (count < 15 && resultSet1.next()) {
                String word = resultSet1.getString("word");
                String meaning = resultSet1.getString("detail");
                Word newWord = new Word(word, meaning, false);
                result.add(newWord);
                count++;
            }
            // after select from second_dict, select from tbl_edict.
            if (count < 15) {
                preparedStatement = connection.prepareStatement(select2);
                ResultSet resultSet2 = preparedStatement.executeQuery();
                while (count < 15 && resultSet2.next()) {
                    String word = resultSet2.getString("word");
                    String meaning = resultSet2.getString("detail");
                    Word newWord = new Word(word, meaning, true);
                    result.add(newWord);
                    count++;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("something went wrong when try to match suggestion from database");
        } finally {
            closeConnect();
            return result;
        }
    }

    /** this method answer if the specific table contains the given word or not. */
    public boolean contain(String word, String table) {
        boolean result = true;
        try {
            String select = "select word from " + table + " where word like '" + word + "'";
            preparedStatement = connection.prepareStatement(select);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                result = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("something went wrong when try to check whether database contains a word or not!");
        } finally {
            closeConnect();
            return result;
        }
    }

    /**
     * add new word to datase, the new word can only be added to the second dictionary.
     * add a  new word to the specific table.
     * */
    public boolean add(Word word, String table) {
        boolean result = false;
        try {
            Word filterWord = Word.filter(word);
            String order = "insert into " + table + " value('" + filterWord.getName() + "','" + filterWord.getDefinition() + "')";
            preparedStatement = connection.prepareStatement(order);
            preparedStatement.execute();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("something went wrong when try to add word to the " + table + " dictionary database");
        } finally {
            closeConnect();
            return result;
        }
    }

    public boolean delete(String word, String table) {
        boolean result = false;
        try {
            String order = "delete from " + table + " where word='" + word + "'";
            preparedStatement = connection.prepareStatement(order);
            preparedStatement.execute();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("something went wrong when try to delete word from " + table + "!!!");
        } finally {
            closeConnect();
            return result;
        }
    }

    public ArrayList<Word> showAll(String table, boolean parse) {
        ArrayList<Word> result = new ArrayList<>();
        try {
            String order = "select * from " + table ;
            preparedStatement = connection.prepareStatement(order);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("word");
                String definition = resultSet.getString("detail");
                result.add(new Word(name, definition, parse));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("something went wrong when try to select all data from " + table);
        } finally {
            closeConnect();
            return result;
        }
    }

    public static ArrayList<Word> MatchSuggestion(String target) {
        DataConnection dataConnection = new DataConnection();
        return dataConnection.matcSuggestion(target);
    }

    public static boolean Contain(String word, String table) {
        DataConnection dataConnection = new DataConnection();
        return dataConnection.contain(word, table);
    }

    public static boolean BothContain(String word) {
        DataConnection check1 = new DataConnection();
        DataConnection check2 = new DataConnection();
        if (check1.contain(word, "tbl_edict") == false && check2.contain(word, "second_dict") == false) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean Add(Word word, String table) {
        DataConnection dataConnection = new DataConnection();
        return dataConnection.add(word, table);
    }

    public static boolean Delete(String word, String table) {
        DataConnection dataConnection = new DataConnection();
        return dataConnection.delete(word, table);
    }

    public static ArrayList<Word> ShowAll(String table, boolean parse) {
        DataConnection dataConnection = new DataConnection();
        return dataConnection.showAll(table, parse);
    }
}