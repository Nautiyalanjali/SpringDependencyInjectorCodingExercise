package org.classes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Printer {

	@Autowired // used for dependency injection
	CsvReader csvReader;
	public void print(String filename) {
		

		List<Book> books = csvReader.readBooksFromCSV(filename); 
		for (Book b : books)

		{

			System.out.print(b.getAuthor());
			System.out.print(b.getTitle());
			System.out.print(b.getPrice());
			System.out.println();

		}

	}
}
