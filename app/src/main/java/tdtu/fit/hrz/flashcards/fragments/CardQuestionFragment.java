package tdtu.fit.hrz.flashcards.fragments;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
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

public class CardQuestionFragment extends Fragment {

    private Button flipButton;
    private TextView cardQuestion;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_card_question, container, false);

        flipButton = view.findViewById(R.id.flip_btn);
        cardQuestion = view.findViewById(R.id.cardSideText);

        Bundle bundle = getArguments();
        if (bundle != null) {
            int p = bundle.getInt("deck_pos");
            Deck deck = DeckAdapter.deckList.get(p);
            List<Card> cards = deck.getCards();
            cardQuestion.setText(cards.get(0).getQuestion());
        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        flipButton.setOnClickListener(v -> {
            CardReviewActivity activity = (CardReviewActivity) getActivity();
            activity.switchFragment();
        });
    }
}