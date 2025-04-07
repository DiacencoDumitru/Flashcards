package command;

import flashcards.FlashcardsManager;

public class ImportCommand implements Command {
    private final FlashcardsManager app;

    public ImportCommand(FlashcardsManager app) {
        this.app = app;
    }

    @Override
    public boolean execute() {
        app.outputMsg("File name:");
        app.importCards(app.getUserInputString());
        return true;
    }
}
