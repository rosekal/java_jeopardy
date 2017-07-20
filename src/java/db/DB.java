package db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.Serializable;
import javax.servlet.ServletException;
import game.*;

public class DB implements Serializable {
    
    /* ATTRIBUTES */
    private Statement stmt;
    private Connection con;
    private ResultSet rs;
    
    
    /* CONSTRUCTOR */
    public DB() throws ClassNotFoundException, SQLException {
        this.connect();
    } //END constructor
    
    //Connects to the database
    public void connect() throws ClassNotFoundException, SQLException {
        try {
            String userName = "";
            String password = "";
            String url = "jdbc:ucanaccess://";

            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver").newInstance();
            con = DriverManager.getConnection(url + "C://Users//Kalen//Documents//Jeopardy_Content.mdb", userName, password);
            stmt = con.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            System.out.println("Database connection established");
            rs = stmt.executeQuery("SELECT * FROM users");
           

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {

            System.err.println("Cannot connect to database server");

        }

    }
        
    
    public void open() throws SQLException {
        try {
            if (this.con != null)
                this.stmt.executeQuery("");
            
        } catch(SQLException e) {
            System.out.println("Unable to connect");
            System.out.println("SQL Error: " + e);
            
        } //END trycatch
        
    } //END method open()
    
    public void open(String table) throws SQLException {
        try {
            if (this.con != null)
                this.stmt.executeQuery("SELECT * FROM " + table);
            
        } catch(SQLException e) {
            System.out.println("Unable to connect");
            System.out.println("SQL Error: " + e);
            
        } //END trycatch
        
    } //END method open()
    
    public void close() throws SQLException {
        try {
            if (this.rs != null)
                this.rs.close();
            if (this.stmt != null)
                this.stmt.close();
            if (this.con != null)
                this.con.close();
            
        } catch(SQLException e) {
            System.out.println("Unable to connect");
            System.out.println("SQL Error: " + e);
            
        } //END trycatch
        
    } //END method close()

    public String executeQuery(String query) throws SQLException {
        try {
            if (this.con != null) {
                this.rs = this.stmt.executeQuery(query);
                this.rs.next();
            }

        } catch(SQLException e) {
            System.out.println("Unable to connect");
            System.out.println("SQL Error: " + e);
            
        } //END trycatch
        
        return this.rs.getString(1);
        
    } //END method executeQuery()
    
    
    
    public Question[] getQuestions(String query) throws SQLException {
        return this.getQuestions(query, 1);
    }
    
    public Question[] getQuestions(String query, int multiplier) throws SQLException {
        Question[] questions = new Question[5];
        try {
            this.rs = this.stmt.executeQuery(query);
            
            int price = 0;

            for (int count = 0; count < 5; count++) {
                try{
                    this.rs.next();
                    questions[count] = new Question(Integer.parseInt(this.rs.getString(1)),
                                                    this.rs.getString(2),
                                                    this.rs.getString(3),
                                                    this.rs.getString(4),
                                                    (price + (200 * multiplier)));
                } catch (Exception e) {
                }
            }
            
        } catch(SQLException e) {
            System.out.println("Unable to connect");
            System.out.println("SQL Error: " + e);
            
        } //END trycatch
        
        return questions;
        
    } //END method getQuestions()
    
    public void openUpdatable() throws SQLException {
        try {
            if (this.con != null) {
                if (this.stmt != null)
                    this.stmt.close();
                this.stmt = this.con.createStatement(
                            ResultSet.TYPE_SCROLL_SENSITIVE,
                            ResultSet.CONCUR_UPDATABLE);
            }
            
        } catch(SQLException e) {
            System.out.println("Unable to connect");
            System.out.println("SQL Error: " + e);
            
        } //END trycatch
        
    } //END method openUpdatable()
    
    public void openReadOnly() throws SQLException {
        try {
            if (this.con != null) {
                if (this.stmt != null)
                    this.stmt.close();
                this.stmt = this.con.createStatement(
                            ResultSet.TYPE_SCROLL_SENSITIVE,
                            ResultSet.CONCUR_READ_ONLY);
            }
            
        } catch(SQLException e) {
            System.out.println("Unable to connect");
            System.out.println("SQL Error: " + e);
            
        } //END trycatch
        
    } //END method openReadOnly()
    
    public boolean addQuestion(Question question) throws SQLException {
        try {
            this.openUpdatable();
            this.stmt.executeUpdate("INSERT INTO Question(Round, "
                                                       + "Category, "
                                                       + "Question, "
                                                       + "Answer) "
                    + "VALUES('Jeopardy!', "
                    + question.getCategory() + ", "
                    + question.getText() + ", "
                    + question.getAnswer() + ")");
            this.openReadOnly();
            return true;
            
        } catch(SQLException e) {
            System.out.println("Unable to connect");
            System.out.println("SQL Error: " + e);
            return false;
            
        } //END trycatch
        
    }
    
    public boolean addCategory(String category) throws SQLException {
        try {
            this.openUpdatable();
            this.stmt.executeUpdate("INSERT INTO Category(Category_Name) "
                    + "VALUES(" + category + ")");
            this.openReadOnly();
            return true;
            
        } catch(SQLException e) {
            System.out.println("Unable to connect");
            System.out.println("SQL Error: " + e);
            return false;
            
        } //END trycatch
        
    }
    
    public boolean addUser(String user, String pswd) throws SQLException {
        try {
            this.openUpdatable();
            this.stmt.executeUpdate("INSERT INTO Users(Username, "
                                                    + "Password) "
                    + "VALUES(" + user + ", " + pswd + ")");
            this.openReadOnly();
            return true;
            
        } catch(SQLException e) {
            System.out.println("Unable to connect");
            System.out.println("SQL Error: " + e);
            return false;
            
        } //END trycatch
        
    }
    
    public boolean updateQuestion(Question oldQ, Question newQ) throws SQLException {
        try {
            this.openUpdatable();
            this.stmt.executeUpdate("UPDATE Question SET "
                    + "Category=" + newQ.getCategory() + ", "
                    + "Question=" + newQ.getText() + ", "
                    + "Answer=" + newQ.getAnswer() + " "
                    + "WHERE Q_ID=" + oldQ.getQID());
            this.openReadOnly();
            return true;
            
        } catch(SQLException e) {
            System.out.println("Unable to connect");
            System.out.println("SQL Error: " + e);
            return false;
            
        } //END trycatch
        
    }
    
    public boolean updateCategory(String oldC, String newC) throws SQLException {
        try {
            this.openUpdatable();
            this.stmt.executeUpdate("UPDATE Category SET "
                    + "Category_Name=" + newC + " "
                    + "WHERE Category_Name=" + oldC);
            this.openReadOnly();
            return true;
            
        } catch(SQLException e) {
            System.out.println("Unable to connect");
            System.out.println("SQL Error: " + e);
            return false;
            
        } //END trycatch
        
    }
    
    public boolean updateUser(String oldUser, String oldPswd, String newUser, String newPswd) throws SQLException {
        try {
            this.openUpdatable();
            this.stmt.executeUpdate("UPDATE Users SET "
                    + "Username=" + oldUser + ", "
                    + "Password=" + oldPswd + " "
                    + "WHERE Username=" + oldUser
                     + " AND Password=" + oldPswd);
            this.openReadOnly();
            this.openReadOnly();
            return true;
            
        } catch(SQLException e) {
            System.out.println("Unable to connect");
            System.out.println("SQL Error: " + e);
            return false;
            
        } //END trycatch
        
    }
    
    public boolean deleteQuestion(Question question) throws SQLException {
        try {
            this.openUpdatable();
            this.stmt.executeUpdate("DELETE FROM Question WHERE Q_ID=" + question.getQID());
            this.openReadOnly();
            return true;
            
        } catch(SQLException e) {
            System.out.println("Unable to connect");
            System.out.println("SQL Error: " + e);
            return false;
            
        } //END trycatch
        
    }
    
    public boolean deleteCategory(String category) throws SQLException {
        try {
            this.openUpdatable();
            this.stmt.executeUpdate("DELETE FROM Category WHERE Category_Name=" + category);
            this.openReadOnly();
            return true;
            
        } catch(SQLException e) {
            System.out.println("Unable to connect");
            System.out.println("SQL Error: " + e);
            return false;
            
        } //END trycatch
        
    }
    
    public boolean deleteUser(String user, String pswd) throws SQLException {
        try {
            this.openUpdatable();
            this.stmt.executeUpdate("DELETE FROM Users "
                    + "WHERE Username=" + user + " AND Password=" + pswd);
            this.openReadOnly();
            return true;
            
        } catch(SQLException e) {
            System.out.println("Unable to connect");
            System.out.println("SQL Error: " + e);
            return false;
            
        } //END trycatch   
    }
    
    public ResultSet results() throws SQLException {
        return rs;
    }
    
    public int signUp(String username, String password) throws SQLException {
        int signUpWorking = stmt.executeUpdate("INSERT INTO users (username, password) VALUES ('" + username + "', '"+ password + "')");
        return signUpWorking;
    }
    
    public void getUserInfo() throws SQLException{
        rs = stmt.executeQuery("SELECT * FROM Users");
    }
    
    public String getColumn(int col) throws SQLException {
        return rs.getString(col);
    }
    
    public void updateTopScore(int newScore, String userName) throws SQLException{
       this.stmt.executeUpdate("UPDATE Users SET TopScore = '" + newScore + "' WHERE Username = '" + userName + "'");
    }

    public void getUserTopScore(String user) throws SQLException{
        rs = this.stmt.executeQuery("SELECT Username, TopScore FROM Users WHERE Username= '" + user + "'");
    }
    
    public void getAllUsersTopScore() throws SQLException{
        rs = this.stmt.executeQuery("SELECT Username, TopScore FROM Users ORDER BY TopScore DESC");
    }
} //END DB bean