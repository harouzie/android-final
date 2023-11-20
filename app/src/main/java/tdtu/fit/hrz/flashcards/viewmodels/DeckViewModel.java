package tdtu.fit.hrz.flashcards.viewmodels;

import androidx.lifecycle.ViewModel;

public class DeckViewModel extends ViewModel {

    private String deckTitle;
    public String getDeckTitle() {return deckTitle;}
    public void setDeckTitle(String deckTitle) {this.deckTitle = deckTitle;}
}
