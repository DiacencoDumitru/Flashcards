package command;

import flashcards.FlashcardsManager;

import java.util.Map;

public class AskCommand implements Command {
    private final FlashcardsManager app;

    public AskCommand(FlashcardsManager app) {
        this.app = app;
    }

    @Override
    public boolean execute() {
        app.outputMsg("How many times to ask?");
        int times = app.getUserInputInt();

        for (int i = 0; i < times; i++) {
            Map.Entry<String, String> entry = app.getRandomCard();
            if (entry == null) {
                app.outputMsg("No cards available to ask.");
                return true;
            }

            String card = entry.getKey();

            app.outputMsg("Print the definition of \"" + card + "\":");
            String answer = app.getUserInputString();

            app.checkAnswer(card, answer);
        }
        return true;
    }
}