import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;

//TODO:need to refactor
public class LibraryTest {

    private ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void should_contain_welcome_when_library_receive_user() throws Exception {
        User user = new User();
        TouchPad touchPad = new TouchPad(new Scanner(System.in), new PrintStream(outputStream));
        Library library = new Library(touchPad, user);

        library.greet();
        assertThat(outputStream.toString(), containsString("Welcome to the library."));
    }

    @Test
    public void should_contain_menus_when_library_receive_user() throws Exception {
        User user = new User();
        TouchPad touchPad = new TouchPad(new Scanner(System.in), new PrintStream(outputStream));
        Library library = new Library(touchPad, user);

        library.greet();
        assertThat(outputStream.toString(), containsString("1.Books\n" +
                "2.MyCollection\n" +
                "3.Library number\n" +
                "0.Exit\n"));
    }

    @Test
    public void should_return_valid_message_when_user_select_a_valid_option() throws Exception {
        User user = new User();
        InputStream inputStream = new ByteArrayInputStream("4".getBytes());
        TouchPad touchPad = new TouchPad(new Scanner(inputStream), new PrintStream(outputStream));
        Library library = new Library(touchPad, user);
        library.processCommand();
        assertThat(outputStream.toString(),is("Select a valid option!!\n"));
    }

    @Test
    public void should_return_all_books_when_user_enter_books_shelf() throws Exception {
        User user = new User();
        InputStream inputStream = new ByteArrayInputStream("1".getBytes());
        TouchPad touchPad = new TouchPad(new Scanner(inputStream), new PrintStream(outputStream));
        Library library = new Library(touchPad, user);
        library.processCommand();
        assertThat(outputStream.toString(),is("1.Harry Poter\n" +
                "2.Clean Code\n" +
                "0.Exit\n"));
    }

    @Test
    public void should_reserve_a_book_from_library() throws Exception {
        User user = new User();
        user.setPlace(Place.SHELF);
        InputStream inputStream = new ByteArrayInputStream("1".getBytes());
        TouchPad touchPad = new TouchPad(new Scanner(inputStream), new PrintStream(outputStream));
        Library library = new Library(touchPad, user);
        library.processCommand();
        
        assertThat(user.getCollection().get(0),is("Harry Poter"));
        assertThat(outputStream.toString(),is("Thank You! Enjoy the book.\n"));
    }

    @Test
    public void should_return_no_such_book_message_when_no_this_book_in_library() throws Exception {
        User user = new User();
        user.setPlace(Place.SHELF);
        InputStream inputStream = new ByteArrayInputStream("4".getBytes());
        TouchPad touchPad = new TouchPad(new Scanner(inputStream), new PrintStream(outputStream));
        Library library = new Library(touchPad, user);
        library.processCommand();

        assertThat(outputStream.toString(),is("Sorry we don't have that book yet.\n"));
    }

    @Test
    public void should_talk_to_librarian_when_check_library_number() throws Exception {
        User user = new User();
        InputStream inputStream = new ByteArrayInputStream("3".getBytes());
        TouchPad touchPad = new TouchPad(new Scanner(inputStream), new PrintStream(outputStream));
        Library library = new Library(touchPad, user);
        library.processCommand();

        assertThat(outputStream.toString(),is("Please talk to Librarian. Thank you.\n"));
    }
}
