package tdtu.fit.hrz.flashcards.fragments;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
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
import tdtu.fit.hrz.flashcards.viewmodels.CardReviewViewModel;

public class CardQuestionFragment extends Fragment {

    private Button flipButton;
    private TextView cardQuestion;
    private CardReviewViewModel model;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_card_question, container, false);

        flipButton = view.findViewById(R.id.flip_btn);
        cardQuestion = view.findViewById(R.id.cardSideText);

        model = new ViewModelProvider(requireActivity()).get(CardReviewViewModel.class);

        cardQuestion.setText(model.getCards().get(model.getCurrentCard()).getQuestion());
        Log.i("00000", "onCreate Review: " + model.getCards().get(model.getCurrentCard()).getQuestion() + " algo: " + " | " + model.getCards().get(model.getCurrentCard()).getInterval());
        if (model.getCurrentCard() != 0) {
            Log.i("0000pre", "onCreate Review: " + model.getCards().get(model.getCurrentCard()-1).getQuestion() + " algo: " +
                    model.getCards().get(model.getCurrentCard()-1).getNextPractice().toString() + " | " + model.getCards().get(model.getCurrentCard()-1).getInterval());
        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        flipButton.setOnClickListener(v -> {
            CardReviewActivity activity = (CardReviewActivity) getActivity();
            activity.switchFragment(0);
        });
    }
}