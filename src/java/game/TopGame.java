package game;

import java.util.Date;
import java.io.Serializable;

public class TopGame implements Serializable {
    
    /* ATTRIBUTES */
    private String playerName;
    private int money;
    private int showNumber;
    private Date datePlayed;
    
    /* CONSTRUCTORS */
    public TopGame() {
        this.playerName = "Player";
        this.money = 0;
        this.showNumber = 0;
        this.datePlayed = new Date();
    } //END default constructor
    
    public TopGame(String playerName, int money, int showNumber, Date datePlayed) {
        this.playerName = playerName;
        this.money = money;
        this.showNumber = showNumber;
        this.datePlayed = datePlayed;
    } //END args constructor
    
    public String getPlayerName() {
        return this.playerName;
    } //END method getPlayerName()
    
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    } //END method setPlayerName()
    
    public int getMoney() {
        return this.money;
    } //END method getMoney()
    
    public void setMoney(int money) {
        this.money = money;
    } //END method setMoney()
    
    public int getShowNumber() {
        return this.showNumber;
    } //END method getShowNumber()
    
    public void setShowNumber(int showNumber) {
        this.showNumber = showNumber;
    } //END method setShowNumber
    
    public Date getDatePlayed() {
        return this.datePlayed;
    } //END method getDatePlayed
    
    public void setDatePlayed(Date datePlayed) {
        this.datePlayed = datePlayed;
    } //END method setDatePlayed
    
    @Override
    public String toString() {
        return "Top Game: " + this.playerName + " - $" + this.money
             + " (game " + this.showNumber + ", played " + this.datePlayed + ")";
    } //END override method toString
    
} //END TopGame bean