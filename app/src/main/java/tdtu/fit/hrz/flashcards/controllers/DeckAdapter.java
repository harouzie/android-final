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

import tdtu.fit.hrz.flashcards.R;
import tdtu.fit.hrz.flashcards.activities.CardReviewActivity;
import tdtu.fit.hrz.flashcards.objects.Deck;

public class DeckAdapter extends RecyclerView.Adapter<DeckAdapter.ViewHolder> {
    public static List<Deck> deckList;
    public static int selectedPos;
    private Context context;
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final ShapeableImageView image;
        private final TextView title;
        private final TextView username;
        private final TextView date;
        private final TextView cardAmount;
        private final ImageView delete;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            view.setOnClickListener(this);

            image = view.findViewById(R.id.image);
            title = view.findViewById(R.id.title);
            username = view.findViewById(R.id.user);
            date = view.findViewById(R.id.date);
            cardAmount = view.findViewById(R.id.amount);
            delete = view.findViewById(R.id.action_delete_deck);

            delete.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            selectedPos = position;
            if (position == RecyclerView.NO_POSITION) return;
            if (v.getId() == R.id.action_delete_deck) {
                Toast.makeText(v.getContext(), "Card delete, popup are you fucking sure?", Toast.LENGTH_LONG).show();
            } else {
                Intent intent = new Intent(v.getContext(), CardReviewActivity.class);
                intent.putExtra("deck_pos", selectedPos);
                v.getContext().startActivity(intent);
            }
        }
    }

    public DeckAdapter(Context context, List<Deck> deckList) {
        this.deckList = deckList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rcv_deck, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(deckList.get(position).getDeckName());
        holder.image.setImageDrawable(deckList.get(position).getCoverImage());
        holder.username.setText(deckList.get(position).getCreator());

        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        holder.date.setText(formatter.format(deckList.get(position).getLastModifiedDate()));

        holder.cardAmount.setText(Integer.toString(deckList.get(position).getSize()));

        holder.itemView.setOnCreateContextMenuListener((ContextMenu contextMenu, View view,
                                                        ContextMenu.ContextMenuInfo contextMenuInfo) -> {
            MenuInflater menuInflater = new MenuInflater(holder.itemView.getContext());
            menuInflater.inflate(R.menu.deck_context_menu, contextMenu);
        });
    }

    @Override
    public int getItemCount() {
        return deckList.size();
    }

}
