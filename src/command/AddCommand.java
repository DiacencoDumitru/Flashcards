package command;

import flashcards.FlashcardsManager;

public class AddCommand implements Command {
    private final FlashcardsManager app;

    public AddCommand(FlashcardsManager app) {
        this.app = app;
    }

    @Override
    public boolean execute() {
        app.outputMsg("The card:");
        String card = app.getUserInputString();
        if (app.cardExists(card)) {
            app.outputMsg("The card \"" + card + "\" already exists.");
            return true;
        }

        app.outputMsg("The definition of the card:");
        String definition = app.getUserInputString();
        if (app.definitionExists(definition)) {
            app.outputMsg("The definition \"" + definition + "\" already exists.");
            return true;
        }

        app.putCard(card, definition);
        app.outputMsg("The pair (\"" + card + "\":\"" + definition + "\") has been added.");

        return true;
    }
}
