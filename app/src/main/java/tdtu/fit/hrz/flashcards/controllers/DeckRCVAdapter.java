package tdtu.fit.hrz.flashcards.controllers;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import tdtu.fit.hrz.flashcards.activities.CardPreviewActivity;
import tdtu.fit.hrz.flashcards.objects.Deck;

public class DeckRCVAdapter extends
        RecyclerView.Adapter <DeckRCVAdapter.FlashcardCollectionViewHolder>{
    private Context context;
    private int resourceId;
    private ArrayList<Deck> flashcardCollections;
    private final LayoutInflater mInflater;
    private onCollectionClickListener collectionClickListener;
    public DeckRCVAdapter(Context context, int resourceId, ArrayList<Deck> flashcards) {
        this.context = context;
        this.resourceId = resourceId;
        this.flashcardCollections = flashcards;
        this.mInflater = LayoutInflater.from(context);
        setCollectionClickListener(new onCollectionClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(context, CardPreviewActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public DeckRCVAdapter.FlashcardCollectionViewHolder
    onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(this.resourceId, parent, false);
        return new FlashcardCollectionViewHolder(view);
    }

    @Override
    public void
    onBindViewHolder(@NonNull DeckRCVAdapter.FlashcardCollectionViewHolder holder, int position) {
        Deck fc = flashcardCollections.get(position);
        holder.update(fc);
    }

    @Override
    public int getItemCount() {
        return flashcardCollections.size();
    }

    public Context getContext() {
        return context;
    }

    public onCollectionClickListener getCollectionClickListener() {
        return collectionClickListener;
    }

    public void setCollectionClickListener(onCollectionClickListener collectionClickListener) {
        this.collectionClickListener = collectionClickListener;
    }

    public interface onCollectionClickListener {
        void onClick(View view, int position);
    }
    public class FlashcardCollectionViewHolder extends RecyclerView.ViewHolder {
        public FlashcardCollectionViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(view -> {
                collectionClickListener.onClick(view, getAdapterPosition());
            });
        }

        public void update(Deck fc){

        }
    }
}
