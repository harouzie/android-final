package tdtu.fit.hrz.flashcards.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import tdtu.fit.hrz.flashcards.R;
import tdtu.fit.hrz.flashcards.activities.CardReviewActivity;
import tdtu.fit.hrz.flashcards.controllers.DeckAdapter;
import tdtu.fit.hrz.flashcards.objects.Card;
import tdtu.fit.hrz.flashcards.objects.Deck;

public class CardAnswerFragment extends Fragment {

    private Button again;
    private Button hard;
    private Button good;
    private Button easy;

    private TextView cardAnswer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_card_answer, container, false);

        again = view.findViewById(R.id.again);
        hard = view.findViewById(R.id.hard);
        good = view.findViewById(R.id.good);
        easy = view.findViewById(R.id.easy);
        cardAnswer = view.findViewById(R.id.cardAnswer);

        Bundle bundle = getArguments();
        if (bundle != null) {
            int p = bundle.getInt("deck_pos");
            Deck deck = DeckAdapter.deckList.get(p);
            List<Card> cards = deck.getCards();
            cardAnswer.setText(cards.get(0).getAnswer());
        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        again.setOnClickListener(v -> {
            CardReviewActivity activity = (CardReviewActivity) getActivity();
            activity.switchFragment();
        });
    }
}