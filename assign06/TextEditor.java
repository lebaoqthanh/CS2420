package assign06;

/**
 * Simulates the edit, undo, and redo functionality of a text editor. Edit will
 * update the current String and the stack of undo actions. Undo will reverse
 * the most recent edit and update the redo stack. Redo will reapply the last
 * undone edit and update the undo stack.
 *
 * @author CS 2420 course staff and ***FILL IN STUDENT NAME***
 * @version ***FILL IN DATE***
 */
public class TextEditor {
	private StringBuilder text;
	private Stack<Edit> undoStack, redoStack;

	public TextEditor() {
		text = new StringBuilder();
		undoStack = new LinkedListStack<Edit>();
		redoStack = new LinkedListStack<Edit>();
	}

	public void insert(char character, int position) {
		Edit change = new Edit(character, position);
		change.apply(text);

		undoStack.push(change);
		redoStack.clear(); // Clear redo stack when a new edit is made
	}

	public void undo() {
		if (undoStack.isEmpty()) {
			throw new IllegalStateException("No action to undo.");
		}

		Edit lastEdit = undoStack.pop();
		lastEdit.revert(text);  // Revert the last edit

		// Push the undone edit to redoStack for potential redo
		redoStack.push(lastEdit);
	}

	public void redo() {
		if (redoStack.isEmpty()) {
			throw new IllegalStateException("No action to redo.");
		}

		Edit lastEdit = redoStack.pop();

		// Ensure that this is an undone edit before applying it again
		lastEdit.apply(text);  // Apply the undone edit

		undoStack.push(lastEdit);  // Store the re-applied edit back in the undo stack
	}

	public SinglyLinkedList<Edit> history() {
		SinglyLinkedList<Edit> historyList = new SinglyLinkedList<>();
		LinkedListStack<Edit> tempStack = new LinkedListStack<>();

		// Transfer undoStack elements to tempStack to maintain order
		while (!undoStack.isEmpty()) {
			Edit edit = undoStack.pop();
			tempStack.push(edit);
		}

		// Restore undoStack and add elements to historyList in the correct order
		while (!tempStack.isEmpty()) {
			Edit edit = tempStack.pop();
			historyList.insert(historyList.size(), edit);  // Insert at the end to maintain chronological order
			undoStack.push(edit);
		}

		return historyList;
	}

	public String toString() {
		return text.toString();
	}
}
