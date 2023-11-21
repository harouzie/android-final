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
import tdtu.fit.hrz.flashcards.controllers.DeckAdapter;
import tdtu.fit.hrz.flashcards.objects.Deck;

public class DeckDownloadFragment extends Fragment {

    RecyclerView deckRCV;
    public DeckAdapter mRCVAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_deck_download, container, false);

        deckRCV = view.findViewById(R.id.deckRCV);
        mRCVAdapter = new DeckAdapter(getContext(), this, loadDecks());
        deckRCV.setAdapter(mRCVAdapter);
        deckRCV.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    private ArrayList<Deck> loadDecks() {
        ArrayList<Deck> downloadDecks = new ArrayList<>();
        //fetch data from firebase??
        return downloadDecks;
    }
}