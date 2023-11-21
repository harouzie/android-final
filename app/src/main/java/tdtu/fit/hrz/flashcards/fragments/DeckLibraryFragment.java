package tdtu.fit.hrz.flashcards.fragments;

import static tdtu.fit.hrz.flashcards.objects.Deck.ACTION_CREATE_DECK;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

import tdtu.fit.hrz.flashcards.R;
import tdtu.fit.hrz.flashcards.activities.CardReviewActivity;
import tdtu.fit.hrz.flashcards.activities.DeckEditActivity;
import tdtu.fit.hrz.flashcards.controllers.DeckAdapter;
import tdtu.fit.hrz.flashcards.objects.Card;
import tdtu.fit.hrz.flashcards.objects.Deck;

public class DeckLibraryFragment extends Fragment {

    RecyclerView deckRCV;
    public DeckAdapter mRCVAdapter;
    private FloatingActionButton floatingActionButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_deck_library, container, false);


        deckRCV = view.findViewById(R.id.deckRCV);
        mRCVAdapter = new DeckAdapter(getContext(), loadDecks());
        deckRCV.setAdapter(mRCVAdapter);
        deckRCV.setLayoutManager(new LinearLayoutManager(getActivity()));
        // ========FAB =========================
        floatingActionButton = view.findViewById(R.id.libraryFAB);
        floatingActionButton.setOnClickListener(view1 -> {
            Intent intent = new Intent(requireActivity(), DeckEditActivity.class);
            intent.setAction(ACTION_CREATE_DECK);
            startActivity(intent);
        });

        return view;
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_review_deck){
            Intent intent = new Intent(getActivity(), CardReviewActivity.class);
            startActivity(intent);
        } else if(id == R.id.action_edit_deck){
            Intent intent = new Intent(getActivity(), DeckEditActivity.class);
            intent.setAction(Deck.ACTION_EDIT_DECK);
            startActivity(intent);
        } else if(id == R.id.action_delete_deck){
            AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
            builder.setNegativeButton("No", null)
                    .setPositiveButton("Yes", (dialogInterface, i) -> {
//                        mRCVAdapter.removeDeck(DeckAdapter.selectedPos); TODO remove
                    }).setTitle("Warning").setMessage("Are you sure to delete this deck?");
            builder.show();
        }
        return super.onContextItemSelected(item);
    }

    private ArrayList<Deck> loadDecks(){
        ArrayList<Deck> decks = new ArrayList<>();
        Deck fcBird = new Deck(loadFlashcard());
        Deck fcCat = new Deck(loadFlashcard());
        Deck fcDog = new Deck(loadFlashcard());

        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

        fcBird.setCoverImage(getResources().getDrawable(R.drawable.birb, null));
        fcBird.setDeckName("fucking birb");
        fcBird.setCreator("not me");

        fcCat.setCoverImage(getResources().getDrawable(R.drawable.huhcat, null));
        fcCat.setDeckName("fucking cat");
        fcCat.setCreator("me");

        fcDog.setCoverImage(getResources().getDrawable(android.R.drawable.sym_def_app_icon, null));
        fcDog.setDeckName("not fucking dog");
        fcDog.setCreator("you");

        try {
            Date specificDate = dateFormat.parse("2021/12/20");

            fcBird.setLastModifiedDate(specificDate);
            fcCat.setLastModifiedDate(specificDate);
            fcDog.setLastModifiedDate(specificDate);
        } catch (ParseException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }

        decks.add(fcBird);
        decks.add(fcCat);
        decks.add(fcDog);


        return decks;
    }
    private ArrayList<Card> loadFlashcard(){
        ArrayList<Card> f = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < random.nextInt(100); i++) {
            f.add(new Card(
                    "question " + (i+1),
                    "answer" + (i+1))
            );
        }
        return f;
    }
}