package game;


import db.DB;
import java.sql.SQLException;
import java.io.Serializable;

public class Category implements Serializable {
    
    /* ATTRIBUTES */
    private int cID;
    private String categoryName;
    private Question[] questions;
    private DB db;
    
    /* CONSTRUCTORS */
    public Category(DB db) throws SQLException {
        try {
            this.cID = (int) (Math.random() * 1357) + 1;
            this.db = db;
            this.setCategory();
            this.setQuestions();
            
        } catch(SQLException e) {
            System.out.println("Unable to connect");
            System.out.println("SQL Error: " + e);
            
        } //END trycatch
        
    } //END constructor
    
    /* METHODS */
    public void setCategory() throws SQLException {
        setCategory(this.cID);
    } //END overload method setCategory()
    
    public void setCategory(int cID) throws SQLException {
        try {
            this.categoryName = this.db.executeQuery("SELECT Category_Name FROM Category WHERE C_ID = " + cID);
        
        } catch(SQLException e) {
            System.out.println("Unable to connect");
            System.out.println("SQL Error: " + e);
            
        } //END trycatch
        
    } //END method setCategory()
    
    public String getCategory() {
            return this.categoryName;
    } //END method getCategory()
    
    public void setQuestions() throws SQLException {
        setQuestions(this.categoryName);
    } //END overload method setQuestions()
    
    public void setQuestions(String categoryName) throws SQLException {
        try {
            this.questions = this.db.getQuestions("SELECT Q_ID, Category, Question, Answer FROM Question WHERE Category = \'" + categoryName.replaceAll("'", "''") + "\'");
            
        } catch(SQLException e) {
            System.out.println("Unable to connect");
            System.out.println("SQL Error: " + e);
            
        } //END trycatch
        
    } //END method setQuestions()
    
    public Question[] loadQuestions() {
        return this.questions;
    } //END method loadQuestions()
    
    @Override
    public String toString() {
        String output = "\t" + this.categoryName + ":\n";
        for (Question q : this.questions) {
            output = output + "\t\t" + q.toString() + "\n";
        }
        return output;
    } //END override method toString()
    
} //END Category bean