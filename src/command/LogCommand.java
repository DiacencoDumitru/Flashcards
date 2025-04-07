package command;

import flashcards.FlashcardsManager;

public class LogCommand implements Command {
    private final FlashcardsManager app;

    public LogCommand(FlashcardsManager app) {
        this.app = app;
    }

    @Override
    public boolean execute() {
        app.outputMsg("File name:");
        String fileName = app.getUserInputString();
        app.saveLog(fileName);
        return true;
    }
}
