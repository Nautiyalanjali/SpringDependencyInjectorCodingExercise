package org.classes;

/* printer component that prints the title, author, and price of each book in the list */

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Printer {

	@Autowired // used for dependency injection
	CsvReader csvReader;

	public void print(String filename) {
		List<Book> books = csvReader.readBooksFromCSV(filename);
		for (Book b : books) {
			System.out.print(b.getAuthor() + ",");
			System.out.print(b.getTitle() + ",");
			System.out.print(b.getPrice());
			System.out.println();

		}

	}

	public List<Book> getBooksByAuthor(String author) {
		List<Book> books = csvReader.readBooksFromCSV("Books.csv");
		List<Book> filteredBooks = new ArrayList<>();
		for (Book book : books) {
			if (book.getAuthor().equals(author)) {
				filteredBooks.add(book);
			}
		}
		return filteredBooks;
	}

}
