package tdtu.fit.hrz.flashcards.controllers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import tdtu.fit.hrz.flashcards.R;
import tdtu.fit.hrz.flashcards.activities.CardReviewActivity;
import tdtu.fit.hrz.flashcards.objects.Deck;

public class DeckAdapter extends RecyclerView.Adapter<DeckAdapter.DeckViewHolder> {
    private static List<Deck> deckList;
    public static int selectedDeckIdx;
    private Context context;
    public class DeckViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final ShapeableImageView image;
        private final TextView title;
        private final TextView username;
        private final TextView date;
        private final TextView cardAmount;
        private final ImageView delete;
        private Deck deck;

        public DeckViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            view.setOnClickListener(this);

            image = view.findViewById(R.id.image);
            title = view.findViewById(R.id.edtDeckName);
            username = view.findViewById(R.id.user);
            date = view.findViewById(R.id.date);
            cardAmount = view.findViewById(R.id.amount);
            delete = view.findViewById(R.id.action_delete_deck);

            delete.setOnClickListener(this);
        }

        public void update(Deck deck){
            this.deck = deck;
            title.setText(deck.getDeckName());
            image.setImageDrawable(deck.getCoverImage());
            username.setText(deck.getCreator());

            @SuppressLint("SimpleDateFormat")
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            date.setText(formatter.format(deck.getLastModifiedDate()));

            cardAmount.setText(String.format(Locale.ENGLISH,"%03d",deck.getSize()));

            itemView.setOnCreateContextMenuListener((ContextMenu contextMenu, View view,
                                                            ContextMenu.ContextMenuInfo contextMenuInfo) -> {
                MenuInflater menuInflater = new MenuInflater(context);
                menuInflater.inflate(R.menu.deck_context_menu, contextMenu);
                selectedDeckIdx = getAdapterPosition();
            });
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            selectedDeckIdx = position;
            if (position == RecyclerView.NO_POSITION) return;
            if (v.getId() == R.id.action_delete_deck) {
                Toast.makeText(v.getContext(), "Card delete, popup are you fucking sure?", Toast.LENGTH_LONG).show();
            } else {
                Intent intent = new Intent(v.getContext(), CardReviewActivity.class);
                intent.putExtra("deck_pos", selectedDeckIdx);
                v.getContext().startActivity(intent);
            }
        }
    }

    public DeckAdapter(Context context, List<Deck> deckList) {
        this.deckList = deckList;
        this.context = context;
    }

//================================================
    public void removeDeck(int selectedPos) {
        if (selectedPos > (this.getItemCount() - 1) ) {
            return;
        }
        deckList.remove(selectedPos);
        notifyItemChanged(selectedPos);
    }
//================================================
    @NonNull
    @Override
    public DeckViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rcv_deck, parent, false);
        return new DeckViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeckViewHolder holder, int position) {
        holder.update(deckList.get(position));
    }

    @Override
    public int getItemCount() {
        return deckList.size();
    }

}
