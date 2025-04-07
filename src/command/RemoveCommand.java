package command;

import flashcards.FlashcardsManager;

public class RemoveCommand implements Command {
    private final FlashcardsManager app;

    public RemoveCommand(FlashcardsManager app) {
        this.app = app;
    }


    @Override
    public boolean execute() {
        app.outputMsg("Which card?");
        String card = app.getUserInputString();
        if (app.removeCard(card)) {
            app.outputMsg("The card has been removed.");
        } else {
            app.outputMsg("Can't remove \"" + card + "\": there is no such card.");
        }
        return true;
    }
}
