package fancyworld;

public class MenuFactory {
    private static MainMenu mainMenu = null;
    private static BooksMenu booksMenu = null;

    public static ReceiveCommand createMenu(UserPosition userPosition, DisplayScreen displayScreen) {
        if (userPosition == UserPosition.MAIN_MENU) {
            if (mainMenu == null) {
                mainMenu = new MainMenu(displayScreen);
            }
            return mainMenu;
        }

        if (userPosition == UserPosition.BOOKS_MENU) {
            if (booksMenu == null) {
                booksMenu = new BooksMenu(displayScreen);
            }
            return booksMenu;
        }
        return null;
    }
}


