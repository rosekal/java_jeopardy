package game;

import java.io.Serializable;

public class Question implements Serializable {
    
    /* ATTRIBUTES */
    private int qID;
    private String category;
    private String text;
    private String answer;
    private int moneyValue;
    
    /* CONSTRUCTORS */
    public Question() {
        this.qID = 0;
        this.category = "";
        this.text = "";
        this.answer = "";
        this.moneyValue = 0;
    } //END default constructor
    
    public Question(int qID, String category, String text, String answer, int moneyValue) {
        this.qID = qID;
        this.category = category;
        this.text = text;
        this.answer = answer;
        this.moneyValue = moneyValue;
    } //END args constructor
    
    /* METHODS */
    public void setQuestionToVoid() {
        this.text = "";
    } //END method setQuestionToVoid()
    
    public int getQID() {
        return this.qID;
    } //END method getQID
    
    public String getCategory() {
        return this.category;
    } //END method getCategory()
    
    public String getText() {
        return this.text;
    } //END method getText()
    
    public boolean guessAnswer(String userAnswer) {
        String[] answerArray = this.answer.split(" "),
                 userArray = userAnswer.split(" ");
        
        if(this.answer.equalsIgnoreCase(userAnswer))
            return true;
        
        for(String answerString : answerArray) {
            for(String userString : userArray) {
                if(answerString.equalsIgnoreCase(userString) && (answerString.length() > 3 || answerArray.length <= 2))
                    return true;
            }
        }
        
        return false;
        
    } //END method guessAnswer()
    
    public String getAnswer() {
        return this.answer;
    } //END method getAnswer()
    
    public int getMoneyValue() {
        return this.moneyValue;
    } //END method getMoneyValue()
    
    public void setMoneyValue(int moneyValue) {
        this.moneyValue = moneyValue;
    } //END method setMoneyValue()
    
    public void wageMoney(int money) {
        this.moneyValue += money;
    } //END method wageMoney()
    
    public void flagQuestion() {
        
    } //END method flagQuestion()
    
    @Override
    public String toString() {
        return this.text + " - A: " + this.answer + ", for $" + this.moneyValue;
    } //END override method toString()
    
} //END Question bean
