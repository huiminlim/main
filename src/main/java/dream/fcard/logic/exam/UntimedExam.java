package dream.fcard.logic.exam;

import dream.fcard.model.Deck;
import dream.fcard.model.cards.FlashCard;
import dream.fcard.model.exceptions.IndexNotFoundException;

import java.util.ArrayList;
import java.util.Scanner;

public class UntimedExam implements Exam {

    private final ArrayList<FlashCard> testDeck;
    private Result result;

    public UntimedExam(ArrayList<FlashCard> deck) {
        this.testDeck = deck;
        this.result = new Result(testDeck.size());
    }

    @Override
    public FlashCard nextCard(int index) {
        FlashCard card = testDeck.get(index);
        card.getFront();
        return card;
    }


}
