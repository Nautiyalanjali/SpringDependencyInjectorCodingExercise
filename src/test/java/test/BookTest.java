package test;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.lenient;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.classes.Book;
import org.classes.CsvReader;
import org.classes.Printer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BookTest {

	@Captor
	ArgumentCaptor<String> captor;
	@InjectMocks // printer mock object injected
	Printer printer;
	@Mock
	CsvReader csvreader; // csv reader mock object injected inside the printer object created in line no
							// 28

	@InjectMocks
	CsvReader csvReader;

	@Test
	public void testCsvReader() {

		List<Book> books = csvReader.readBooksFromCSV("./src/main/resources/Books.csv");
		assertEquals("Book1", books.get(0).getTitle());
		assertEquals("James", books.get(0).getAuthor());
		assertEquals(new BigDecimal(20), books.get(0).getPrice());
		assertEquals("Book2", books.get(1).getTitle());
		assertEquals("Peter", books.get(1).getAuthor());
		assertEquals(new BigDecimal(30), books.get(1).getPrice());
		assertEquals("Book3", books.get(2).getTitle());
		assertEquals("James", books.get(2).getAuthor());
		assertEquals(new BigDecimal(30), books.get(2).getPrice());

	}

	// Test case to verify the dependency injection to inject the CSV reader
	// component into the printer component
	@Test
	public void testPrinter() {

		printer.print("Testing");

		Mockito.verify(csvreader).readBooksFromCSV(captor.capture());
		assertEquals("Testing", captor.getValue());
	}

	// Test case to verify the list of books returns which are written by same
	// author
	@Test
	public void testBooksBySameAuthor() {

		String author = "James";
		List<Book> expectedBooks = new ArrayList<>();
		expectedBooks.add(new Book("LMN", "James", new BigDecimal(30)));
		expectedBooks.add(new Book("ABC", "James", new BigDecimal(20)));

		when(csvreader.readBooksFromCSV(anyString())).thenReturn(expectedBooks);

		List<Book> actualBooks = printer.getBooksByAuthor(author);

		assertEquals(expectedBooks.size(), actualBooks.size());
		for (int i = 0; i < expectedBooks.size(); i++) {
			assertEquals(expectedBooks.get(i).getTitle(), actualBooks.get(i).getTitle());
		}
	}

	// Test case to verify if csv file is blank : Negative scenario

	@Test
	public void emptyCsvReturnsErrorMessage() {
		// String filePath = "Empty.csv";
		List<Book> emptyList = new ArrayList<>();

		lenient().when(csvreader.readBooksFromCSV(anyString())).thenReturn(emptyList);

		List<Book> books = csvReader.readBooksFromCSV("./src/main/resources/Empty.csv");

		assertEquals(0, books.size());
	}
}
