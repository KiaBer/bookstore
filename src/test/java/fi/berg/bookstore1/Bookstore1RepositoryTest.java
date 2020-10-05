package fi.berg.bookstore1;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.berg.bookstore1.domain.Book;
import fi.berg.bookstore1.domain.BookRepository;
import fi.berg.bookstore1.domain.Category;



@RunWith(SpringRunner.class)
@DataJpaTest
public class Bookstore1RepositoryTest {
	
	@Autowired
    private BookRepository repository;

    @Test
    public void findByAuthorShouldReturnBook() {
        List<Book> books = repository.findByAuthor("Maija Mikkonen");
        
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getTitle()).isEqualTo("Kaikki nauraa");
    }
    
    @Test
    public void createNewBook() {
    	Book book = new Book("Kauniit maisemat", "Kirsi Kimalainen", new Category("Huumori"));
    	repository.save(book);
    	assertThat(book.getId()).isNotNull();
    }

}
