package flashcards;

import command.*;

import java.io.*;
import java.util.*;

public class FlashcardsManager {
    private final Map<String, String> cards = new LinkedHashMap<>();
    private final Set<String> definitions = new LinkedHashSet<>();
    private final Map<String, Integer> mistakes = new HashMap<>();
    private final List<String> log = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);
    private final String[] args;
    private final Map<String, Command> commands = new HashMap<>();

    public FlashcardsManager(String[] args) {
        this.args = args;
        initializeCommands();
        executeImportArgs();
    }

    private void initializeCommands() {
        commands.put("add", new AddCommand(this));
        commands.put("remove", new RemoveCommand(this));
        commands.put("import", new ImportCommand(this));
        commands.put("export", new ExportCommand(this));
        commands.put("ask", new AskCommand(this));
        commands.put("exit", new ExitCommand(this));
        commands.put("log", new LogCommand(this));
        commands.put("hardest card", new HardestCardCommand(this));
        commands.put("reset stats", new ResetStatsCommand(this));
    }

    private void executeImportArgs() {
        for (int i = 0; i < args.length; i += 2) {
            if (args[i].equals("-import")) {
                importCards(args[i + 1]);
            }
        }
    }

    public void executeExportArgs() {
        for (int i = 0; i < args.length; i += 2) {
            if (args[i].equals("-export")) exportCards(args[i + 1]);
        }
    }

    public void run() {
        boolean askFirstTime = true;
        boolean isExit = false;

        while (!isExit) {
            if (askFirstTime) { // implement method
                outputMsg("Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):");
                askFirstTime = false;
            } else {
                outputMsg("");
                outputMsg("Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):");
            }

            String action = getUserInputString();
            Command command = commands.get(action);

            if (command == null) {
                outputMsg("Unknown command.");
                continue;
            }

            if (!command.execute()) {
                isExit = true;
            }
        }
    }

    public boolean cardExists(String card) {
        return cards.containsKey(card);
    }

    public boolean definitionExists(String definition) {
        return definitions.contains(definition);
    }

    public boolean removeCard(String card) {
        if (cardExists(card)) {
            cards.remove(card);
            return true;
        }
        return false;
    }

    public void putCard(String card, String definition) {
        cards.put(card, definition);
        definitions.add(definition);
    }

    public void clearMistakes() {
        mistakes.clear();
    }

    public void outputMsg(String msg) {
        System.out.println(msg);
        log.add(msg);
    }

    public String getUserInputString() {
        String input = scanner.nextLine();
        log.add(input);
        return input;
    }

    public int getUserInputInt() {
        int input = scanner.nextInt();
        scanner.nextLine();
        log.add(String.valueOf(input));
        return input;
    }

    public void saveLog(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String str : log) { // implement method
                writer.write(str);
                writer.newLine();
            }
        } catch (IOException e) {
            outputMsg("Error while saving the log: " + e.getMessage());
        } catch (Exception e) {
            outputMsg("An unexpected error occurred while saving the log: " + e.getMessage());
        }
        outputMsg("The log has been saved.");
    }

    public void outputHardestCard() {
        if (mistakes.isEmpty()) {
            outputMsg("There are no cards with errors.");
            return;
        }

        int maxErrors = Collections.max(mistakes.values());
        List<String> hardestCards = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : mistakes.entrySet()) {
            if (entry.getValue() == maxErrors) {
                hardestCards.add(entry.getKey());
            }
        }

        if (maxErrors == 0) {
            outputMsg("There are no cards with errors.");
        } else if (hardestCards.size() == 1) {
            outputMsg("The hardest card is \"" + hardestCards.getFirst() + "\". You have " + maxErrors + " errors answering it.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < hardestCards.size(); i++) {
                if (i != hardestCards.size() - 1) {
                    sb.append("\"").append(hardestCards.get(i)).append("\", ");
                } else {
                    sb.append("\"").append(hardestCards.get(i)).append("\"");
                }
            }
            outputMsg("The hardest cards are " + sb + ". You have " + maxErrors + " errors answering them.");
        }
    }

    public Map.Entry<String, String> getRandomCard() {
        List<Map.Entry<String, String>> entries = new ArrayList<>(cards.entrySet());
        if (entries.isEmpty()) return null;
        return entries.get(new Random().nextInt(entries.size()));
    }

    public void checkAnswer(String card, String answer) {
        String correct = cards.get(card);
        if (correct.equals(answer)) {
            outputMsg("Correct!");
        } else {
            mistakes.put(card, mistakes.getOrDefault(card, 0) + 1);
            Optional<String> otherCard = cards.entrySet().stream()
                    .filter(e -> e.getValue().equals(answer))
                    .map(Map.Entry::getKey)
                    .findFirst();
            if (otherCard.isPresent()) {
                outputMsg("Wrong. The right answer is \"" + correct + "\", but your definition is correct for \"" + otherCard.get() + "\".");
            } else {
                outputMsg("Wrong. The right answer is \"" + correct + "\".");
            }
        }
    }

    public void importCards(String fileName) {
        int countLines = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] partsOfLine = line.split(":");
                String card = partsOfLine[0];
                String definition = partsOfLine[1];
                String errors = partsOfLine[2];

                if (!cards.containsKey(card) && !definitions.contains(definition)) {
                    definitions.add(definition);
                    cards.put(card, definition);
                } else if (cards.containsKey(card)) {
                    definitions.remove(cards.get(card));
                    definitions.add(definition);
                    cards.put(card, definition);
                }
                if (Integer.parseInt(errors) > 0)
                    mistakes.put(card, mistakes.getOrDefault(card, Integer.parseInt(errors)));
                countLines++;
            }
        } catch (FileNotFoundException e) {
            outputMsg("File not found.");
        } catch (IOException e) {
            outputMsg("Error reading the file.");
        } catch (NumberFormatException e) {
            outputMsg("Error parsing numbers.");
        } catch (Exception e) {
            outputMsg("An unexpected error occurred: " + e.getMessage());
        } finally {
            outputMsg(countLines + " cards have been loaded.");
        }
        outputMsg(countLines + " cards have been loaded.");
    }

    public void exportCards(String fileName) {
        int countLines = 0;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Map.Entry<String, String> entry : cards.entrySet()) {
                if (mistakes.containsKey(entry.getKey())) {
                    writer.write(entry.getKey() + ":" + entry.getValue() + ":" + mistakes.get(entry.getKey()));
                    writer.newLine();
                } else {
                    writer.write(entry.getKey() + ":" + entry.getValue() + ":" + "0");
                    writer.newLine();
                }
                countLines++;
            }
        } catch (IOException e) {
            outputMsg("Error while saving cards: " + e.getMessage());
        } catch (Exception e) {
            outputMsg("An unexpected error occurred while saving cards: " + e.getMessage());
        }
        outputMsg(countLines + " cards have been saved.");
    }
}