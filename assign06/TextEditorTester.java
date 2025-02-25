package assign06;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

/**
 * Tests for the TextEditor class.
 *
 * @author CS 2420 course staff and Quang Khai Huynh
 * @version ***FILL IN DATE***
 */
class TextEditorTester {
	private Edit[] mediumEdits;
	private TextEditor empty, small, medium;

	@BeforeEach
	void setUp() throws Exception {
		empty = new TextEditor();
		small = new TextEditor();
		medium = new TextEditor();

		// Insert characters into a TextEditor
		small.insert('e', 0);
		small.insert('l', 1);
		small.insert('l', 2);
		small.insert('h', 0); // <- characters can be inserted at any valid index
		small.insert('o', 4);
		// Now small should contain "hello"

		// Edit objects for testing the history method
		mediumEdits = new Edit[29];
		for (int i = 0; i < 26; i++) {
			mediumEdits[i] = new Edit((char) ('a' + i), i);
			medium.insert((char) ('a' + i), i);
		}
		mediumEdits[26] = new Edit('3', 3);
		mediumEdits[27] = new Edit('3', 3);
		mediumEdits[28] = new Edit('3', 3);
		medium.insert('3', 3);
		medium.insert('3', 3);
		medium.insert('3', 3);
	}

	@Test
	void testEmptyToString() {
		assertEquals("", empty.toString());
	}

	@Test
	void testEmptyInsert() {
		empty.insert('Y', 0);
		assertEquals("Y", empty.toString());
	}

	@Test
	void testEmptyInsertUndo() {
		empty.insert('Z', 0);
		empty.undo();
		assertEquals("", empty.toString());
	}

	@Test
	void testEmptyInsertUndoRedo() {
		empty.insert('Z', 0);
		empty.undo();
		empty.redo();
		assertEquals("Z", empty.toString());
	}

	@Test
	void testSmallToString() {
		assertEquals("hello", small.toString());
	}

	@Test
	void testMediumHistorySize() {
		SinglyLinkedList<Edit> hist = medium.history();
		assertEquals(mediumEdits.length, hist.size());
	}

	@Test
	public void testPopFromEmptyStack() {
		LinkedListStack<String> stack = new LinkedListStack<>();
		assertThrows(NoSuchElementException.class, stack::pop);
	}

	@Test
	public void testClearStack() {
		LinkedListStack<String> stack = new LinkedListStack<>();
		stack.push("tuilathanh");
		stack.clear();
		assertTrue(stack.isEmpty());
	}

	@Test
	public void testPushToStack() {
		LinkedListStack<String> stack = new LinkedListStack<>();
		String element = "cmm";
		stack.push(element);
		assertEquals(element, stack.peek());
	}

	@Test
	public void testUndo() {
		TextEditor editor = new TextEditor();
		editor.insert('A', 0);
		editor.insert('B', 1);
		editor.undo();
		assertEquals("A", editor.toString());
		editor.undo();
		assertEquals("", editor.toString());
	}

	@Test
	public void testHistory_EmptyEditor() {
		TextEditor editor = new TextEditor();
		assertTrue(editor.history().isEmpty());
	}

	@Test
	public void testHistory_TracksAllInsertions() {
		TextEditor editor = new TextEditor();
		editor.insert('X', 0);
		editor.insert('Y', 1);
		editor.insert('Z', 2);
		SinglyLinkedList<Edit> history = editor.history();
		assertEquals(3, history.size());
		assertEquals(new Edit('X', 0).toString(), history.get(0).toString());
		assertEquals(new Edit('Y', 1).toString(), history.get(1).toString());
		assertEquals(new Edit('Z', 2).toString(), history.get(2).toString());
	}

	@Test
	public void testHistory_DoesNotIncludeUndoneEdits() {
		TextEditor editor = new TextEditor();
		editor.insert('M', 0);
		editor.insert('N', 1);
		editor.insert('O', 2);
		editor.undo();
		editor.undo();
		SinglyLinkedList<Edit> history = editor.history();
		assertEquals(1, history.size());
		assertEquals(new Edit('M', 0).toString(), history.get(0).toString());
	}

	@Test
	public void testHistory_UpdatesAfterRedo() {
		TextEditor editor = new TextEditor();
		editor.insert('P', 0);
		editor.insert('Q', 1);
		editor.undo();
		editor.redo();
		SinglyLinkedList<Edit> history = editor.history();
		assertEquals(2, history.size());
		assertEquals(new Edit('P', 0).toString(), history.get(0).toString());
		assertEquals(new Edit('Q', 1).toString(), history.get(1).toString());
	}

	@Test
	public void testHistory_MaintainsOrderOfInsertions() {
		TextEditor editor = new TextEditor();
		editor.insert('A', 0);
		editor.insert('B', 1);
		editor.insert('C', 2);
		SinglyLinkedList<Edit> history = editor.history();
		assertEquals(new Edit('A', 0).toString(), history.get(0).toString());
		assertEquals(new Edit('B', 1).toString(), history.get(1).toString());
		assertEquals(new Edit('C', 2).toString(), history.get(2).toString());
	}
}
