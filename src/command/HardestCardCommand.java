package command;

import flashcards.FlashcardsManager;


public class HardestCardCommand implements Command {
    private final FlashcardsManager app;

    public HardestCardCommand(FlashcardsManager app) {
        this.app = app;
    }

    @Override
    public boolean execute() {
        app.outputHardestCard();
        return true;
    }
}
