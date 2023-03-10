package test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.classes.Book;
import org.classes.CsvReader;
import org.classes.Printer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TestBook {

	@Captor
	ArgumentCaptor<String> captor;
	@InjectMocks // printer mock object injected
	Printer printer;
	@Mock
	CsvReader csvreader; //csv reader mock object injected inside the printer object created in line no 28
	
	@InjectMocks
	CsvReader csvReader;
	
	@Test // junit annotation for test case
	public void testCsvReader() {

		//CsvReader csvReader = new CsvReader();
		List<Book> books = csvReader.readBooksFromCSV("./src/main/resources/Books.csv");
		assertEquals("ABC", books.get(0).getTitle());
		assertEquals("James", books.get(0).getAuthor());
		assertEquals(new BigDecimal(20), books.get(0).getPrice());
		assertEquals("DEF", books.get(1).getTitle());
		assertEquals("Peter", books.get(1).getAuthor());
		assertEquals(new BigDecimal(30), books.get(1).getPrice());

	}

	@Test
	public void testPrinter() {

		printer.print("./src/main/resources/Books.csv");

		Mockito.verify(csvreader).readBooksFromCSV(captor.capture());
		assertEquals("./src/main/resources/Books.csv", captor.getValue());
	}

//	@Test
//	
//	public void testPrinter2() {
//		
//		printer.print("./src/main/resources/Books.csv");
//		
//		
//		when(csvreader.readBooksFromCSV(Mockito.anyString())).thenReturn(new ArrayList <Book>());
//	}
}
