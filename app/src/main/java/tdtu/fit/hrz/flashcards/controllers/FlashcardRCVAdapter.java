package tdtu.fit.hrz.flashcards.controllers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import tdtu.fit.hrz.flashcards.objects.Flashcard;
public class FlashcardRCVAdapter extends
        RecyclerView.Adapter <FlashcardRCVAdapter.FlashcardViewHolder>{
    private Context context;
    private int resourceId;
    private ArrayList<Flashcard> flashcards;
    private LayoutInflater mInflater;

    public FlashcardRCVAdapter(Context context, int resourceId, ArrayList<Flashcard> flashcards) {
        this.context = context;
        this.resourceId = resourceId;
        this.flashcards = flashcards;
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public FlashcardRCVAdapter.FlashcardViewHolder
        onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(this.resourceId, parent, false);
        return new FlashcardViewHolder(view);
    }

    @Override
    public void
        onBindViewHolder(@NonNull FlashcardRCVAdapter.FlashcardViewHolder holder, int position) {
        Flashcard flashcard = flashcards.get(position);
        holder.update(flashcard);
    }

    @Override
    public int getItemCount() {
        return flashcards.size();
    }

    public static class FlashcardViewHolder extends RecyclerView.ViewHolder {
        public FlashcardViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void update(Flashcard flashcard){

        }
    }
}
