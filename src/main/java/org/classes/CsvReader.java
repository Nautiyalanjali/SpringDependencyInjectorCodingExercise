package org.classes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

@Component
public class CsvReader {

	
	
	
	public List<Book> readBooksFromCSV(String fileName) {

		List<Book> books = new ArrayList<>();
		Path pathToFile = Paths.get(fileName);
		// create an instance of BufferedReader
		// using try with resource, Java 7 feature to close resources

		try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {

			// read the first line from the text file

			String line = br.readLine();

			// loop until all lines are read

			while (line != null) {

				// use string.split to load a string array with the values from each line of the
				// file, using a comma as the delimiter

				String[] attributes = line.split(",");

				Book book = createBook(attributes); // adding book into ArrayList
				books.add(book); // read next line before looping
				// if end of file reached, line would be null
				line = br.readLine();
			}

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return books;
	}

	private static Book createBook(String[] metadata) {
		String title = metadata[0];
		BigDecimal price = BigDecimal.valueOf(Long.valueOf(metadata[1]));
		String author = metadata[2]; // create and return book of this metadata
		return new Book(title, author, price);
	}
}
