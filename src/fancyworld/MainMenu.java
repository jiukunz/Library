package fancyworld;

import java.util.LinkedHashMap;
import java.util.Map;

public class MainMenu implements ReceiveCommand {
    private static final int EXIT = 0;
    private static final int ENTER_BOOKS_MENU = 1;
    private static final int GET_COLLECTION = 2;
    private static final int CHECK_LIBRARY_NUMBER = 3;

    private static final String BYE_BYE = "Bye Bye";
    private static final String VALID_OPTION = "Select a valid option!!";
    private static final String SAY_TO_LIBRARIAN ="Please talk to Librarian. Thank you.";

    private Map<Integer, String> menus;
    private DisplayScreen displayScreen;

    public MainMenu(DisplayScreen displayScreen) {
        this.displayScreen = displayScreen;
        this.menus = new LinkedHashMap<Integer, String>();
        menus.put(1, "Books");
        menus.put(2, "MyCollection");
        menus.put(3, "Library number");
        menus.put(0, "Exit");
    }

    @Override
    public void executeCommandFromUser(User user){
        displayScreen.display(this.menus);
        int command = user.sendCommand();
        switch (command){
            case EXIT:
                displayScreen.display(BYE_BYE);
                user.setUserPosition(UserPosition.OUT_OF_LIBRARY);
                break;
            case ENTER_BOOKS_MENU:
                user.setUserPosition(UserPosition.BOOKS_MENU);
                break;
            case GET_COLLECTION:
                displayScreen.display(user.getCollection());
                break;
            case CHECK_LIBRARY_NUMBER:
                displayScreen.display(SAY_TO_LIBRARIAN);
                break;
            default:
                displayScreen.display(VALID_OPTION);
                break;
        }
    }
}
