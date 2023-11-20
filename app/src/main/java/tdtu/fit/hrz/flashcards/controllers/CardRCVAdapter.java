package tdtu.fit.hrz.flashcards.controllers;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import tdtu.fit.hrz.flashcards.activities.CardEditActivity;
import tdtu.fit.hrz.flashcards.objects.Card;
public class  CardRCVAdapter extends
        RecyclerView.Adapter <CardRCVAdapter.CardViewHolder>{
    private Context context;
    private int resourceId;
    private ArrayList<Card> flashcards;
    private LayoutInflater mInflater;
    private onCardClickListener onCardClickListener;
    private onCardLongClickListener onCardLongClickListener;

    public CardRCVAdapter(Context context, int resourceId, ArrayList<Card> flashcards) {
        this.context = context;
        this.resourceId = resourceId;
        this.flashcards = flashcards;
        this.mInflater = LayoutInflater.from(context);
        this.onCardClickListener = (view, position) -> {
            Intent intent = new Intent(context, CardEditActivity.class);
            context.startActivity(intent);
        };
    }

    @NonNull
    @Override
    public CardViewHolder
        onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(this.resourceId, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void
        onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        Card flashcard = flashcards.get(position);
        holder.update(flashcard);
    }

    @Override
    public int getItemCount() {
        return flashcards.size();
    }

    public onCardClickListener getOnCardClickListener() {
        return onCardClickListener;
    }

    private interface onCardClickListener {
        void onClick(CardViewHolder view, int position);
    }

    private interface onCardLongClickListener {
        void onClick(CardViewHolder view, int position);
    }
    public class CardViewHolder extends RecyclerView.ViewHolder {

        private Card flashcard;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(view -> {
                onCardClickListener.onClick(this, getAdapterPosition());
            });
            itemView.setOnLongClickListener(view -> {
                onCardLongClickListener.onClick(this, getAdapterPosition());
                return true;
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
