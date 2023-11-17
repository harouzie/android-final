package tdtu.fit.hrz.flashcards.controllers;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import tdtu.fit.hrz.flashcards.objects.Card;
public class CardRCVAdapter extends
        RecyclerView.Adapter <CardRCVAdapter.FlashcardViewHolder>{
    private Context context;
    private int resourceId;
    private ArrayList<Card> flashcards;
    private LayoutInflater mInflater;
    private onFlashcardClickListener flashcardClickListener;

    public CardRCVAdapter(Context context, int resourceId, ArrayList<Card> flashcards) {
        this.context = context;
        this.resourceId = resourceId;
        this.flashcards = flashcards;
        this.mInflater = LayoutInflater.from(context);
        this.flashcardClickListener = new onFlashcardClickListener() {
            @Override
            public void onClick(FlashcardViewHolder view, int position) {
                view.flipCard();
            }
        };
    }

    @NonNull
    @Override
    public CardRCVAdapter.FlashcardViewHolder
        onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(this.resourceId, parent, false);
        return new FlashcardViewHolder(view);
    }

    @Override
    public void
        onBindViewHolder(@NonNull CardRCVAdapter.FlashcardViewHolder holder, int position) {
        Card flashcard = flashcards.get(position);
        holder.update(flashcard);
    }

    @Override
    public int getItemCount() {
        return flashcards.size();
    }

    public onFlashcardClickListener getFlashcardClickListener() {
        return flashcardClickListener;
    }

    private interface onFlashcardClickListener{
        void onClick(FlashcardViewHolder view, int position);
    }
    public class FlashcardViewHolder extends RecyclerView.ViewHolder {

        private Card flashcard;

        public FlashcardViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(view -> {
                flashcardClickListener.onClick(this, getAdapterPosition());
            });
        }

        public void update(Card flashcard){
            this.flashcard = flashcard;
            // TODO: bind data on QA sides
        }

        // Method to flip the card with animation
        private void flipCard() {
            ObjectAnimator flip = ObjectAnimator.ofFloat(
                    itemView, "rotationY", 0f, 360f);
            flip.setDuration(500);
            flip.start();

            // Toggle the flipped state
            assert  flashcard != null /* get the corresponding Flashcard object */;
            flashcard.flip();

            // Notify the adapter that the data has changed
            notifyItemChanged(getAdapterPosition());
        }

        public Card getFlashcard() {
            return flashcard;
        }

        public void setFlashcard(Card flashcard) {
            this.flashcard = flashcard;
        }
    }
}
