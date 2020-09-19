package fi.berg.bookstore1;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.berg.bookstore1.domain.Book;
import fi.berg.bookstore1.domain.BookRepository;
import fi.berg.bookstore1.domain.Category;
import fi.berg.bookstore1.domain.CategoryRepository;

@SpringBootApplication
public class Bookstore1Application {

	public static void main(String[] args) {
		SpringApplication.run(Bookstore1Application.class, args);
	}

	@Bean
	public CommandLineRunner bookstore1 (BookRepository brepository, CategoryRepository crepository) {
		return (args) -> {
			System.out.println("put books to database, in this case H2");
			
			crepository.save(new Category("Dekkari"));
			crepository.save(new Category("Elämäntaito"));
			crepository.save(new Category("Harrastukset"));
			
			brepository.save(new Book("Kaikki nauraa", "Maija Mikkonen", crepository.findByName("Elämäntaito").get(0)));
			brepository.save(new Book("Kaikki itkee", "Miika Mikkonen", crepository.findByName("Dekkari").get(0)));
			
			System.out.println("list all books");
			for (Book book : brepository.findAll()) {
				System.out.println(book.toString());
			}
		};
	}
}
