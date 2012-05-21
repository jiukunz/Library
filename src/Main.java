public class Main {
    public static void main(String[] args) {
        TouchPad touchPad = new TouchPad();
        User user = new User();
        Library library = new Library(touchPad,user);

        library.greet();
        library.receiveCommandFromUser();
    }
}
