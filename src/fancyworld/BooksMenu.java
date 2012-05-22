package fancyworld;

import java.util.LinkedHashMap;
import java.util.Map;

public class BooksMenu implements ReceiveCommand {

    private static final int EXIT = 0;
    private static final String NO_THIS_BOOK = "Sorry we don't have that book yet.";
    private static final String ENJOY_BOOK = "Thank You! Enjoy the book.";

    private DisplayScreen displayScreen;
    private Map<Integer, String> books;

    public BooksMenu(DisplayScreen displayScreen){
        this.displayScreen = displayScreen;
        books = new LinkedHashMap<Integer, String>();
        books.put(1, "Harry Poter");
        books.put(2, "Clean Code");
        books.put(0, "Exit");
    }
    @Override
    public void executeCommandFromUser(User user) {
        displayScreen.display(books);
        int command = user.sendCommand();
        if (command == EXIT) {
            user.setUserPosition(UserPosition.MAIN_MENU);
            return;
        }

        int bookId = command;
        String book = getBookFrom(bookId);
        if (book == null) {
            displayScreen.display(NO_THIS_BOOK);
            return;
        }

        user.getCollection().add(book);
        displayScreen.display(ENJOY_BOOK);
    }

    private String getBookFrom(int bookId) {
        return books.get(bookId);
    }
}
