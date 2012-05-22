package fancyworld;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//TODO:need to refactor
//TODO:single test can pass,but run all test will be failed.?_?
public class LibraryTest {

    private ByteArrayOutputStream outputStream;

    @Before
    public void setUp() throws Exception {
        outputStream = new ByteArrayOutputStream();
    }

    @Test
    public void should_contain_welcome_when_library_receive_user() throws Exception {
        User user = mock(User.class);
        DisplayScreen displayScreen = new DisplayScreen(new PrintStream(outputStream));
        Library library = new Library(displayScreen, user);

        library.greet();
        assertThat(outputStream.toString(), is("Welcome to the library.\n"));
    }

    @Test
    public void should_contain_menus_when_library_receive_user() throws Exception {
        User user = mock(User.class);
        when(user.getUserPosition()).thenReturn(UserPosition.MAIN_MENU);
        when(user.sendCommand()).thenReturn(0);

        DisplayScreen displayScreen = new DisplayScreen(new PrintStream(outputStream));
        Library library = new Library(displayScreen, user);
        
        library.processCommand();
        assertThat(outputStream.toString(), containsString("1.Books\n" +
                "2.MyCollection\n" +
                "3.Library number\n" +
                "0.Exit\n"));
    }

    @Test
    public void should_return_valid_message_when_user_select_a_valid_option() throws Exception {
        int InvalidOption = 8;
        User user = mock(User.class);
        when(user.getUserPosition()).thenReturn(UserPosition.MAIN_MENU);
        when(user.sendCommand()).thenReturn(InvalidOption);
        DisplayScreen displayScreen = new DisplayScreen(new PrintStream(outputStream));
        Library library = new Library(displayScreen, user);

        library.processCommand();

        assertThat(outputStream.toString(),containsString("Select a valid option!!\n"));
    }

    @Test
    public void should_return_all_books_when_user_enter_books_menu() throws Exception {
        User user = mock(User.class);
        when(user.getUserPosition()).thenReturn(UserPosition.BOOKS_MENU);
        when(user.sendCommand()).thenReturn(0);
        DisplayScreen displayScreen = new DisplayScreen(new PrintStream(outputStream));
        Library library = new Library(displayScreen, user);

        library.processCommand();

        assertThat(outputStream.toString(),is("1.Harry Poter\n" +
                "2.Clean Code\n" +
                "0.Exit\n"));
    }

    @Test
    public void should_reserve_a_book_from_library() throws Exception {
        List<String> collection = new ArrayList<String>();
        User user = mock(User.class);
        when(user.getUserPosition()).thenReturn(UserPosition.BOOKS_MENU);
        when(user.sendCommand()).thenReturn(1);
        when(user.getCollection()).thenReturn(collection);
        DisplayScreen displayScreen = new DisplayScreen(new PrintStream(outputStream));
        Library library = new Library(displayScreen, user);

        library.processCommand();

        assertThat(user.getCollection().get(0),is("Harry Poter"));
        assertThat(outputStream.toString(),containsString("Thank You! Enjoy the book.\n"));
    }

    @Test
    public void should_return_no_such_book_message_when_no_this_book_in_library() throws Exception {
        int noBookIndex = 4;
        User user = mock(User.class);
        when(user.getUserPosition()).thenReturn(UserPosition.BOOKS_MENU);
        when(user.sendCommand()).thenReturn(noBookIndex);
        DisplayScreen displayScreen = new DisplayScreen(new PrintStream(outputStream));
        Library library = new Library(displayScreen, user);

        library.processCommand();

        assertThat(outputStream.toString(),containsString("Sorry we don't have that book yet.\n"));
    }

    @Test
    public void should_talk_to_librarian_when_check_library_number() throws Exception {
        User user = mock(User.class);
        when(user.getUserPosition()).thenReturn(UserPosition.MAIN_MENU);
        when(user.sendCommand()).thenReturn(3);
        DisplayScreen displayScreen = new DisplayScreen(new PrintStream(outputStream));
        Library library = new Library(displayScreen, user);

        library.processCommand();

        assertThat(outputStream.toString(),containsString("Please talk to Librarian. Thank you.\n"));
    }
}
