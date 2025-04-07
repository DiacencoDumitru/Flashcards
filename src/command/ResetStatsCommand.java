package command;

import flashcards.FlashcardsManager;

public class ResetStatsCommand implements Command {
    private final FlashcardsManager app;

    public ResetStatsCommand(FlashcardsManager app) {
        this.app = app;
    }

    @Override
    public boolean execute() {
        app.clearMistakes();
        app.outputMsg("Card statistics have been reset.");
        return true;
    }
}
