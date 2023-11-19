package tdtu.fit.hrz.flashcards.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import tdtu.fit.hrz.flashcards.R;
import tdtu.fit.hrz.flashcards.controllers.DeckRCVAdapter;
import tdtu.fit.hrz.flashcards.objects.Card;
import tdtu.fit.hrz.flashcards.objects.Deck;

public class DeckLibraryFragment extends Fragment {

    RecyclerView flashcardCollectionRCV;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_deck_library, container, false);

        flashcardCollectionRCV = view.findViewById(R.id.deckRCV);
        DeckRCVAdapter mRCVAdapter = new DeckRCVAdapter(
            getActivity(), R.layout.rcv_deck, loadFlashcardCollection());
        flashcardCollectionRCV.setAdapter(mRCVAdapter);
        flashcardCollectionRCV.setLayoutManager(new LinearLayoutManager(getActivity()));

        getActivity().setTitle("Collections");

       return view;
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