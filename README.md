### 1. Flashcard Project

This is the first stage of a flashcard-style application.  
The goal is to create a basic structure for storing terms and their definitions.
---------

- Read two inputs from the user:

1. A **term**
2. Its **definition**

- Display the inputs in the following format:

```
Example 1:
------------
Card:
> purchase
purchase
Definition:
> buy
buy
------------
Example 2:
Card:
> cos'(x)
cos'(x)
Definition:
> -sin(x)
-sin(x)
```

### 2. Basic Flashcards Creation and Guessing Mechanism

- Read two lines of input: a term and its definition.
- Ask the user to input a definition for the given term.
- Compare the user's answer with the correct definition.
- Print whether the user's answer is correct or wrong.

```
Example:
-------------
> print()
> outputs text
> outputs text

Output:
-------------
Your answer is right!
```

### 3. Multiple Flashcards Game

- Allow users to create multiple flashcards.
- Ask the user how many flashcards they want to create.
- For each flashcard, input a term and its definition.
- The program will then quiz the user by asking for the definition of each term.

------------------

- Ask for the number of cards.
- For each card, ask for the term and its definition.
- Quiz the user with each term and check their answers.

```
Example:
> 2
--------
Card #1:
> print()
The definition for card #1:
> outputs text
---------
Card #2:
> str()
The definition for card #2:
> converts to a string
---------
Print the definition of "print()":
> outputs text
Correct!
---------
Print the definition of "str()":
> outputs text
Wrong. The right answer is "converts to a string".
```

### 4. Handle Duplicate Terms and Definitions

- Prevent users from entering duplicate terms or definitions.
- If the user tries to input a term or definition that already exists, prompt them to try again.
- If the user enters a wrong definition for the requested term but the definition is correct for another term, provide
  feedback with the correct term.

------------

- Ask for a term and its definition.
- If the term or definition already exists, show an appropriate error message.
- If the definition matches another term, inform the user of the correct answer and the term that matches their
  definition.

```
Example:
> 2
-------
Card #1:
> print()
The definition for card #1:
> outputs text
-------
Card #2:
> print()
The term "print()" already exists. Try again:
> str()
-------
The definition for card #2:
> outputs text
The definition "outputs text" already exists. Try again:
> converts to a string
------
Print the definition of "print()":
> outputs text
Correct!
```

### 5. Flashcard Menu and File Operations

- Add a menu that lets users perform various actions:
- add: Add a new flashcard.
- remove: Remove an existing flashcard.
- import: Import flashcards from a file.
- export: Export flashcards to a file.
- ask: Quiz the user on all the flashcards.
- exit: Exit the program.

---------------

- Display the available actions to the user.
- Based on the user's action, either add, remove, import, export flashcards, or quiz the user.

```
Example:
--------
Input the action (add, remove, import, export, ask, exit):
> add
Card #1:
> print()
The definition for card #1:
> outputs text
The pair ("print()":"outputs text") has been added.
-------------
Input the action (add, remove, import, export, ask, exit):
> ask
Print the definition of "print()":
> outputs text
Correct!
```

### 6. File Operations (Import/Export)

- Enable the user to save flashcards to a file and load them back into the program.
- When importing, overwrite existing cards if the term is already present.
- When exporting, save the cards to a file in a simple format (.txt)

-----------

- Use the import action to load flashcards from a file.
- Use the export action to save flashcards to a file.

```
Example:
-------
Input the action (add, remove, import, export, ask, exit):
> export
File name:
> flashcards.txt
The cards have been saved.
--------
Input the action (add, remove, import, export, ask, exit):
> import
File name:
> flashcards.txt
2 cards have been loaded.
```

### 7. IMPORTant

Files are used to save progress and restore it the next time the user runs the program. It's tedious to print the
actions manually. Sometimes you can just forget to do it! Let's add run arguments that define which file to read at the
start and which file to save at the exit.

1. If `-import IMPORT` is passed, where IMPORT is the file name, read the initial card set from the external file and
   print
   the message n cards have been loaded. as the first line of the output, where n is the number of cards loaded from the
   external file. If such an argument is not provided, the set of cards should initially be empty and no message about
   card
   loading should be output.
2. If `-export EXPORT` is passed, where EXPORT is the file name, write all cards that are in the program memory into
   this
   file after the user has entered exit, and the last line of the output should be n cards have been saved., where n is
   the
   number of flashcards in the set.