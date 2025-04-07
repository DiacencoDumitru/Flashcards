package command;

import flashcards.FlashcardsManager;

public class ExportCommand implements Command {
    private final FlashcardsManager app;

    public ExportCommand(FlashcardsManager app) {
        this.app = app;
    }

    @Override
    public boolean execute() {
        app.outputMsg("File name:");
        app.exportCards(app.getUserInputString());
        return true;
    }
}
