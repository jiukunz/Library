package fancyworld;

public class Main {
    public static void main(String[] args) {
        DisplayScreen displayScreen = new DisplayScreen();
        User user = new User();
        Library library = new Library(displayScreen,user);
        library.greet();
        while (true){
            library.processCommand();
        }
    }
}
