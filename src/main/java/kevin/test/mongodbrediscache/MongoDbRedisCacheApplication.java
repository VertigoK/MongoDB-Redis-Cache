package kevin.test.mongodbrediscache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MongoDbRedisCacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(MongoDbRedisCacheApplication.class, args);
    }

}
