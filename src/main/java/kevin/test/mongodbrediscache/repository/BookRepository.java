package kevin.test.mongodbrediscache.repository;

import kevin.test.mongodbrediscache.domain.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, String> {

    Book findByTitle(String title);
    void deleteByTitle(String title);
}
