package tdtu.fit.hrz.flashcards.objects;

/**
 * a Flashcard has 2 sides, head and tail
 * head is for question
 * tail is for answer
 */
public class Flashcard {

    private String question;
    private String answer;

    public Flashcard(){
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
}
