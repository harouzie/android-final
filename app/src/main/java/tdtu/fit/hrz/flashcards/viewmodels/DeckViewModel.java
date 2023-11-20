package tdtu.fit.hrz.flashcards.viewmodels;

import androidx.lifecycle.ViewModel;

import tdtu.fit.hrz.flashcards.objects.Card;

public class DeckViewModel extends ViewModel {

    //Deck Edit Activity
    private String deckTitle;
    public String getDeckTitle() {return deckTitle;}
    public void setDeckTitle(String deckTitle) {this.deckTitle = deckTitle;}

    //Card Edit Activity
    private String question;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
