package fi.berg.bookstore1.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fi.berg.bookstore1.domain.Book;
import fi.berg.bookstore1.domain.BookRepository;
import fi.berg.bookstore1.domain.CategoryRepository;

@Controller
public class BookstoreController {
	
	@Autowired
	private BookRepository brepository;
	
	@Autowired
	private CategoryRepository crepository;
	
	@RequestMapping("/index")
	public String giveIndexPage() {
		return "Index!";
	}
	
	@RequestMapping("/booklist")
	public String returnAllBooks(Model model) {
		model.addAttribute("books", brepository.findAll());
		return "booklist";
	}
	
	@RequestMapping("/add")
	public String addNewBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categories", crepository.findAll());
		return "addBook";
	}
	
	@PostMapping("/save")
	public String save(Book book) {
		brepository.save(book);
		return "redirect:booklist";
	}
	@PostMapping("/edit/save")
	public String saveEdit(Book book) {
		brepository.save(book);
		return "redirect:../booklist";
	}
	
	@GetMapping(value = "/delete/{id}")
	public String deleteBook(@PathVariable("id") Long bookId, Model model) {
		brepository.deleteById(bookId);
		return "redirect:../booklist";
	}
	
	@GetMapping(value = "/edit/{id}")
	public String editBook(@PathVariable("id") Long bookId, Model model) {
		model.addAttribute("book", brepository.findById(bookId));
		model.addAttribute("categories", crepository.findAll());
		return "editBook";
	}

}
