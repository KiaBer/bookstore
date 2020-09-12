package fi.berg.bookstore1;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.berg.bookstore1.domain.Book;
import fi.berg.bookstore1.domain.BookRepository;

@SpringBootApplication
public class Bookstore1Application {

	public static void main(String[] args) {
		SpringApplication.run(Bookstore1Application.class, args);
	}

	@Bean
	public CommandLineRunner bookstore1 (BookRepository repository) {
		return (args) -> {
			System.out.println("put books to database, in this case H2");
			repository.save(new Book("Kaikki nauraa", "Maija Mikkonen"));
			repository.save(new Book("Kaikki itkee", "Miika Mikkonen"));
			
			System.out.println("list all books");
			for (Book book : repository.findAll()) {
				System.out.println(book.toString());
			}
		};
	}
}
