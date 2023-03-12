package org.classes;

/*CSV reader component that reads the CSV file and returns a list of Book objects.*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class CsvReader {

	public List<Book> readBooksFromCSV(String fileName) {

		List<Book> books = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			// read the first line from the text file
			String line = br.readLine();

			// loop until all lines are read
			if (!line.equalsIgnoreCase("")) {
				while (line != null) {

					String[] attributes = line.split(",");

					Book book = createBook(attributes); // adding book into ArrayList
					books.add(book); // read next line before looping
					// if end of file reached, line would be null
					line = br.readLine();
				}

			}

			else {

				return new ArrayList<Book>();
			}
		}

		catch (IOException ioe) {

			ioe.printStackTrace();
		}
		return books;
	}

	private static Book createBook(String[] metadata) {
		String title = metadata[0];
		String author = metadata[1]; // create and return book of this metadata
		BigDecimal price = new BigDecimal(metadata[2]);

		return new Book(title, author, price);
	}
}
