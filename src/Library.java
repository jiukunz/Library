import java.util.LinkedHashMap;
import java.util.Map;

public class Library {
    private final String NO_THIS_BOOK = "Sorry we don't have that book yet.";
    private final String ENJOY_BOOK = "Thank You! Enjoy the book.";
    private TouchPad touchPad = new TouchPad();
    private final String WELCOME = "Welcome to the library.";
    private final String BYT_BYE = "Bye Bye";
    private final String SAY_TO_Librarian="Please talk to Librarian. Thank you.";
    private final String VALID_OPTION = "Select a valid option!!";
    private User user;
    private static Map<Integer, String> menus = new LinkedHashMap<Integer, String>();
    private static Map<Integer, String> books = new LinkedHashMap<Integer, String>();

    public Library(TouchPad touchPad, User user) {
        this.touchPad = touchPad;
        this.user = user;
    }
    static {
        menus.put(1, "Books");
        menus.put(2, "MyCollection");
        menus.put(3, "Library number");

        books.put(1, "Harry Poter");
        books.put(2, "Clean Code");
    }

    public void greet(){
        welcome();
        showMenu();
    }

    public void receiveCommandFromUser(){
        while (true){
            processCommand();
        }
    }

    public void processCommand() {
        int command = touchPad.waitForTouch();

        if(user.getPlace() == Place.SHELF){
            if (processCommandInShelf(command)) return;
        }

        if(user.getPlace() == Place.GATE){
            processCommandInGate(command);
        }
    }

    private void processCommandInGate(int command) {
        switch (command){
            case 0:
                touchPad.show(BYT_BYE);
                System.exit(1);
                break;
            case 1:
                user.setPlace(Place.SHELF);
                touchPad.show(books);
                break;
            case 2:
                touchPad.show(user.getCollection());
                break;
            case 3:
                touchPad.show(SAY_TO_Librarian);
                break;
            default:
                touchPad.show(VALID_OPTION);
                break;
        }
    }

    private boolean processCommandInShelf(int command) {
        if(command == 0){
            user.setPlace(Place.GATE);
            showMenu();
            return true;
        }
        int bookId = command;
        String book = getBookFrom(bookId);
        if(book == null){
            touchPad.show(NO_THIS_BOOK);
            return true;
        }
        user.getCollection().add(book);
        touchPad.show(ENJOY_BOOK);
        return false;
    }

    private void showMenu() {
        touchPad.show(menus);
    }

    private void welcome() {
        touchPad.show(WELCOME);
    }

    private String getBookFrom(int bookId) {
        return books.get(bookId);
    }

}
