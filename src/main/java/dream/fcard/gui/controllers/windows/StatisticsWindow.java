package dream.fcard.gui.controllers.windows;

import java.io.IOException;

import dream.fcard.logic.stats.Session;
import dream.fcard.logic.stats.UserStats;
import dream.fcard.logic.stats.UserStatsHolder;
import dream.fcard.model.Deck;
import dream.fcard.model.StateHolder;
import dream.fcard.util.StatsDisplayUtil;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;

/**
 * Window to display user's statistics.
 */
public class StatisticsWindow extends ScrollPane {
    @FXML
    private Label totalSessions;
    @FXML
    private Label totalDuration;
    @FXML
    private Label sessionsThisWeek;
    @FXML
    private TableView<Session> sessionsTableView;
    @FXML
    private TableView<Deck> deckTableView;

    private UserStats userStats = UserStatsHolder.getUserStats();

    /** Creates a new instance of StatisticsWindow. */
    public StatisticsWindow() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class
                .getResource("/view/Windows/StatisticsWindow.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            //TODO: replace with logger
            e.printStackTrace();
        }

        //ArrayList<Deck> decks = StateHolder.getState().getDecks(); TODO

        displaySummaryStats();
        this.sessionsTableView = StatsDisplayUtil.getSessionsTableView(userStats.getSessionList());
        this.deckTableView = StatsDisplayUtil.getDeckTableView(StateHolder.getState());
    }

    /** Retrieves and displays numerical stats, like the total number of login sessions. */
    private void displaySummaryStats() {
        int numSessions = userStats.getNumberOfSessions();
        this.totalSessions.setText("Total login sessions: " + numSessions
            + (numSessions == 1 ? " session" : " sessions"));

        String duration = userStats.getTotalDurationOfSessionsAsString();
        this.totalDuration.setText("Total login duration: " + duration);
    }

}
