package tdtu.fit.hrz.flashcards.viewmodels;

import androidx.lifecycle.ViewModel;

import java.util.List;

import tdtu.fit.hrz.flashcards.objects.Card;

public class CardReviewViewModel extends ViewModel {
    private List<Card> cards;
    private int currentCard = 0;

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public int getCurrentCard() {
        return currentCard;
    }

    public void setCurrentCard(int currentCard) {
        this.currentCard = currentCard;
    }

    public boolean isOver() {
        if (currentCard >= cards.size()) {
            return true;
        }
        return false;
    }
}
