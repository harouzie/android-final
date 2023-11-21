package tdtu.fit.hrz.flashcards.objects;

import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Deck implements Serializable {
    private String deckName;
    private boolean isDownloaded;
    private boolean isPrivate;
    private Date createDate;
    private String creator;
    private Date lastModifiedDate;
    private int size;
    private Drawable coverImage;
    private ArrayList<Card> cards;

    public Deck(@NonNull ArrayList<Card> cards) {
        this.cards = cards;
        this.size = cards.size();
        int i = 1;
        for(Card c: cards){
            c.setIndex(i);
            i++;
        }
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public int getSize() {
        size = cards.size();
        return size;
    }

    public Drawable getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(Drawable coverImage) {
        this.coverImage = coverImage;
    }

    public String getDeckName() {
        return deckName;
    }

    public void setDeckName(String deckName) {
        this.deckName = deckName;
    }

    public boolean isDownloaded() {
        return isDownloaded;
    }

    public void setDownloaded(boolean downloaded) {
        isDownloaded = downloaded;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }


    // these 2 functions currently useless,
    // cuz it return a list of card objects that didn't linked to the real cards in data
    public ArrayList<Card> getDueDateCard() {
        ArrayList<Card> dueDateCards = new ArrayList<>();
        for (int i = 0; i <= cards.size()-1; i++) {
            if (cards.get(i).isDueDate()) dueDateCards.add(cards.get(i));
        }
        return dueDateCards;
    }

    public static ArrayList<Card> getDueDateCardFromAllDeck(List<Deck> decks) {
        ArrayList<Card> dueDateCards = new ArrayList<>();

        for (int i = 0; i <= decks.size()-1; i++) {
            for (int j = 0; j <= decks.get(i).getSize()-1; j++) {
                Card card = decks.get(i).getCards().get(j);
                if (card.isDueDate()) {
                    dueDateCards.add(card);
                }
            }
        }

        return dueDateCards;
    }
}
