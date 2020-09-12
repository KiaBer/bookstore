package fi.berg.bookstore1.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.berg.bookstore1.domain.Book;
import fi.berg.bookstore1.domain.BookRepository;

@Controller
public class BookstoreController {
	
	@Autowired
	BookRepository bookRepository;
	
	@GetMapping("/index")
	@ResponseBody
	public String giveIndexPage() {
		return "Index!";
	}
	
	@GetMapping("/booklist")
	public String returnAllBooks(Model model) {
		model.addAttribute("books", bookRepository.findAll());
		return "booklist";
	}
	
	@GetMapping("/add")
	public String addNewBook(Model model) {
		model.addAttribute("book", new Book());
		return "addBook";
	}
	
	@PostMapping("/save")
	public String saveBook(Book book) {
		bookRepository.save(book);
		return "redirect:booklist";
	}
	
	@GetMapping(value = "/delete/{id}")
	public String deleteBook(@PathVariable("id") Long bookId) {
		bookRepository.deleteById(bookId);
		return "redirect:../booklist";
	}
	
	@GetMapping(value = "/edit/{id}")
	public String editBook(@PathVariable("id") Long bookId, Model model) {
		model.addAttribute("book", bookRepository.findById(bookId));
		return "editBook";
	}

}
