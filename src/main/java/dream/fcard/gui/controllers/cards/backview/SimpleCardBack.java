package dream.fcard.gui.controllers.cards.backview;

import java.io.IOException;
import java.util.function.Consumer;

import dream.fcard.gui.controllers.windows.MainWindow;
import dream.fcard.logic.exam.Exam;
import dream.fcard.logic.exam.ExamRunner;
import dream.fcard.logic.respond.Consumers;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * The rear view of a front-back card.
 */
public class SimpleCardBack extends AnchorPane {
    @FXML
    private Button seeFrontButton;
    @FXML
    private Button correctButton;
    @FXML
    private Button wrongButton;
    @FXML
    private Label answerLabel;

    public SimpleCardBack(String backOfCard) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/Cards/"
                    + "Back/SimpleCardBack.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
            answerLabel.setText(backOfCard);
            seeFrontButton.setOnAction(e -> seeFront());
            correctButton.setOnAction(e -> onCorrect());
            wrongButton.setOnAction((e -> onWrong()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void seeFront() {
        Exam exam = ExamRunner.getCurrentExam();
        AnchorPane currCardFront = exam.getCardDisplayFront();
        Consumers.doTask("SWAP_CARD_DISPLAY", currCardFront);
    }

    private void onCorrect() {
        Consumers.doTask("GET_SCORE", true);
        Exam exam = ExamRunner.getCurrentExam();
        exam.upIndex();
        AnchorPane nextCardFront = exam.getCardDisplayFront();
        Consumers.doTask("SWAP_CARD_DISPLAY", nextCardFront);
    }

    private void onWrong() {
        Consumers.doTask("GET_SCORE", false);
        Exam exam = ExamRunner.getCurrentExam();
        exam.upIndex();
        AnchorPane nextCardFront = exam.getCardDisplayFront();
        Consumers.doTask("SWAP_CARD_DISPLAY", nextCardFront);
    }
}
