package command;

import flashcards.FlashcardsManager;

public class ExitCommand implements Command {
    private final FlashcardsManager app;

    public ExitCommand(FlashcardsManager app) {
        this.app = app;
    }

    @Override
    public boolean execute() {
        app.outputMsg("Bye bye!");
        app.executeExportArgs();
        return false;
    }
}
