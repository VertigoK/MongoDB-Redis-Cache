package kevin.test.mongodbrediscache.controller;

import kevin.test.mongodbrediscache.domain.Book;
import kevin.test.mongodbrediscache.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WebServiceController {

    private final BookRepository bookRepository;

    @Autowired
    public WebServiceController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/book")
    public List<Book> findBooks() {
        return bookRepository.findAll();
    }

    @PostMapping("/book")
    public Book saveBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    //@Cacheable을 사용해 해당 key 값을 가지는 데이터를 Cache에 저장
    @GetMapping("/book/{title}")
    @Cacheable(value = "book", key = "#title")
    public Book findBook(@PathVariable String title) {
        return bookRepository.findByTitle(title);
    }

    //Delete: MongoDB의 데이터를 삭제하고 Cache에 저장된(있다면) 데이터도 삭제해야 함
    //따라서 @CacheEvict을 사용해 Cache에서 해당 key 값을 가지는 데이터를 삭제
    @DeleteMapping("/book/{title}")
    @CacheEvict(value = "book", key = "#title")
    public void deleteBook(@PathVariable String title) {
        bookRepository.deleteByTitle(title);
    }

    //Update: MongoDB에 저장된 데이터는 바뀌지만 Cache에 저장된(있다면) 데이터는 바뀌지 않음
    //따라서 @CacheEvict을 사용해 Cache에서 해당 key 값을 가지는 데이터를 삭제
    @PutMapping("/book/{title}")
    @CacheEvict(value = "book", key = "#title")
    public Book updateBook(@PathVariable String title, @RequestBody Book updatedBook) {
        Book book = bookRepository.findByTitle(title);
        book.setTitle(updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor());
        book.setDescription(updatedBook.getDescription());
        return bookRepository.save(book);   //save()의 경우 _id가 존재하면 insert, _id가 존재하지 않으면 update 수행
    }
}
