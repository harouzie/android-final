package tdtu.fit.hrz.flashcards.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import tdtu.fit.hrz.flashcards.R;
import tdtu.fit.hrz.flashcards.controllers.DeckAdapter;
import tdtu.fit.hrz.flashcards.objects.Card;
import tdtu.fit.hrz.flashcards.objects.Deck;

public class DeckLibraryFragment extends Fragment {

    RecyclerView deckRCV;
    public DeckAdapter mRCVAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_deck_library, container, false);


        deckRCV = view.findViewById(R.id.deckRCV);
        mRCVAdapter = new DeckAdapter(getContext(), loadDecks());
        deckRCV.setAdapter(mRCVAdapter);
        deckRCV.setLayoutManager(new LinearLayoutManager(getActivity()));

        registerForContextMenu(deckRCV);
        return view;
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_review_deck){
            Toast.makeText(requireActivity(), "Review deck", Toast.LENGTH_SHORT).show();
        } else if(id == R.id.action_edit_deck){
            Toast.makeText(requireActivity(), "Edit deck", Toast.LENGTH_SHORT).show();
        } else if(id == R.id.action_delete_deck){
            Toast.makeText(requireActivity(), "Delete deck", Toast.LENGTH_SHORT).show();
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

        fcDog.setCoverImage(getResources().getDrawable(R.drawable.tdtu_logo, null));
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
        for (int i = 0; i < 2; i++) {
            f.add(new Card(
                    "question%d" + (i+1),
                    "answer" + (i+1))
            );
        }
        return f;
    }
}