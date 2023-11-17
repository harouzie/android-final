package tdtu.fit.hrz.flashcards.objects;

import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Date;

public class Deck {
    private Date createDate;
    private String creator;
    private Date lastModifiedDate;
    private int size;
    private Drawable coverImage;
    private ArrayList<Flashcard> flashcards;


    public Deck(@NonNull ArrayList<Flashcard> flashcards) {
        this.flashcards = flashcards;
        this.size = flashcards.size();
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
        size = flashcards.size();
        return size;
    }

    public Drawable getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(Drawable coverImage) {
        this.coverImage = coverImage;
    }
}
