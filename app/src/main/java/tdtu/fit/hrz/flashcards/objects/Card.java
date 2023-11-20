package tdtu.fit.hrz.flashcards.objects;

import java.io.Serializable;

/**
 * a Flashcard has 2 sides, head and tail
 * head is for question
 * tail is for answer
 */
public class Card implements Serializable {

    private String question;
    private String answer;
    private boolean flipped = false;

    public Card(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public Card(){
        question = "";
        answer = "";
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void flip(){
        this.flipped = !this.flipped;
    }
    public boolean isFlipped() {
        return flipped;
    }

    public void setFlipped(boolean flipped) {
        this.flipped = flipped;
    }
}
