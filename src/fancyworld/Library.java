package fancyworld;

public class Library {
    private final String WELCOME = "Welcome to the library.";

    private DisplayScreen displayScreen;
    private User user = new User();

    public Library(DisplayScreen displayScreen) {
        this.displayScreen = displayScreen;
    }

    public Library(DisplayScreen displayScreen, User user) {
        this.displayScreen = displayScreen;
        this.user = user;
    }

    public void greet() {
        displayScreen.display(WELCOME);
    }

    public void processCommand() {
        if (UserPosition.OUT_OF_LIBRARY == user.getUserPosition()) {
            System.exit(1);
        }
        UserPosition userPosition = user.getUserPosition();
        ReceiveCommand menu = MenuFactory.createMenu(userPosition, displayScreen);
        menu.executeCommandFromUser(user);
    }
}
