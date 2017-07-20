package game;

import java.io.Serializable;

public class Player implements Serializable {
    
    /* ATTRIBUTES */
    private String name;
    private int money;
    
    /* CONSTRUCTORS */
    public Player() {
        this.name = "Player";
        this.money = 0;
    } //END default constructor
    
    public Player(String name) {
        this.name = name;
        this.money = 0;
    } //END args constructor
    
    /* METHODS */
    public void setName(String name) {
        this.name = name;
    } //END method setName()
    
    public String getName() {
        return this.name;
    } //END method getName()
    
    public void addMoney(int amount) {
        this.money += amount;
    } //END method addMoney()
    
    public void subMoney(int amount) {
        this.money -= amount;
    } //END method subMoney()
    
    public int getMoney() {
        return this.money;
    } //END method getMoney()
    
    @Override
    public String toString() {
        return this.name + " - $" + this.money;
    } //END override method toString
    
} //END Player bean