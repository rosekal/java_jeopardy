package game;

import db.DB;
import java.sql.SQLException;
import java.io.Serializable;

public class Game implements Serializable {
    
    /* ATTRIBUTES */
    private Category[]  round1,
                        round2;
    private Category    round3;
    
    /* CONSTRUCTOR */
    public Game() throws SQLException, ClassNotFoundException {
        try {
            DB db = new DB();
            this.round1 = new Category[6];
            this.round2 = new Category[6];

            for(int i = 0; i < round1.length; i++){
                round1[i] = new Category(db);
            }

            for(int i = 0; i < round2.length; i++){
                round2[i] = new Category(db);
            }

            this.round3 = new Category(db);
            
        } catch(ClassNotFoundException e) {
            System.out.println("Failed to load JDBC/ODBC Driver");
            System.out.println("Class Error: " + e);
            
        } catch(SQLException e) {
            System.out.println("Unable to connect");
            System.out.println("SQL Error: " + e);
            
        } //END trycatch
        
    } //END constructor
    
    /* METHODS */
    
    /**
     * This method is used to return an array of categories for either round 1 or 2
     * @param round
     * @return Category
     */
    public Category[] loadRound(int round) {
        return round == 1 ? round1 : round2;
    }
    
    /**
     * This overloaded method is used to return a category for the final round
     * @return Category
     */
    public Category loadRound() {
        return round3;
    }
    
    @Override
    public String toString() {
        String r1 = "  Round 1:\n";
        for (Category c : round1) {
            r1 = r1 + c.toString();
        }
        String r2 = "  Round 2:\n";
        for (Category c : round2) {
            r2 = r2 + c.toString();
        }
        String r3 = "  Round 3:\n" + round3.toString();
        return "Game:\n" + r1 + r2 + r3;
    } //END override method toString()
    
} //END Game bean