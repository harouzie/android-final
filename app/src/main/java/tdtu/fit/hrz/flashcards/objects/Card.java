package tdtu.fit.hrz.flashcards.objects;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * a Flashcard has 2 sides, head and tail
 * head is for question
 * tail is for answer
 */
public class Card implements Serializable {

    private int index = 0;
    private String question;
    private String answer;
    private boolean flipped = false;

    // Card Review algorithm, if a user download a new deck, these field in those newly downloaded deck
    // should be the default value or not have these field in the json at all
    private int repetitions = 0;
    private int interval = 1;
    private float ease = 2.5f;
    private LocalDate nextPractice;

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

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    //Spaced repetition SM-2, anki based algorithm

    public void updateNextPractice(int rating) {
        if (rating < 1 || rating > 4) {
            throw new IllegalArgumentException("Rating should be between 1 and 4");
        }

        this.ease = Math.max(1.3f, this.ease + (0.1f - (5 - rating) * (0.08f + (5 - rating) * 0.02f)));

        if (rating == 1) {
            this.repetitions = 0;
            this.interval = 1;
        } else if (this.repetitions <= 1) {
            this.repetitions = this.repetitions + 1;
            this.interval = rating == 2 ? 1 : 3;
        } else {
            this.repetitions = this.repetitions + 1;
            this.interval = (int) (this.interval * this.ease);
        }

        this.nextPractice = LocalDate.now().plusDays(this.interval);
    }

    public boolean isDueDate() {
        if (nextPractice == null) return true;
        if (nextPractice.isAfter(LocalDate.now()) || nextPractice.isEqual(LocalDate.now())) {
            return true;
        }
        return false;
    }
}
