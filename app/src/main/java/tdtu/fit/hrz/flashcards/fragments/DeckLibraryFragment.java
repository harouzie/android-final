package tdtu.fit.hrz.flashcards.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import tdtu.fit.hrz.flashcards.R;
import tdtu.fit.hrz.flashcards.controllers.DeckRCVAdapter;
import tdtu.fit.hrz.flashcards.objects.Card;
import tdtu.fit.hrz.flashcards.objects.Deck;

public class DeckLibraryFragment extends Fragment {

    RecyclerView deckRCV;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_deck_library, container, false);

        deckRCV = view.findViewById(R.id.deckRCV);
        DeckRCVAdapter mRCVAdapter = new DeckRCVAdapter(
            getActivity(), R.layout.rcv_deck, loadFlashcardCollection());
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

    private ArrayList<Deck> loadFlashcardCollection(){
        ArrayList<Deck> flashcardCollections = new ArrayList<>();
        Deck fcBird = new Deck(loadFlashcard());
        Deck fcCat = new Deck(loadFlashcard());
        Deck fcDog = new Deck(loadFlashcard());

//        fcBird.setCoverImage(Drawable.createFromPath("drawable/huhcat.png"));
        flashcardCollections.add(fcBird);
        flashcardCollections.add(fcCat);
        flashcardCollections.add(fcDog);

        return flashcardCollections;
    }
    private ArrayList<Card> loadFlashcard(){
        ArrayList<Card> f = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            f.add(new Card());
        }
        return f;
    }
}