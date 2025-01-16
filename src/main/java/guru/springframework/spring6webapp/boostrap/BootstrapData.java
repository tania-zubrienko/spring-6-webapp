package guru.springframework.spring6webapp.boostrap;

import guru.springframework.spring6webapp.dto.Author;
import guru.springframework.spring6webapp.dto.Book;
import guru.springframework.spring6webapp.dto.Publisher;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;
import guru.springframework.spring6webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//anotacion necesaria
@Component
public class BootstrapData implements CommandLineRunner {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(BookRepository bookRepository, AuthorRepository authorRepository, PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author auth1 = new Author();
        auth1.setName("Autor 1");
        auth1.setLastName("Lastname 1");

        Book book1 = new Book();
        book1.setTitle("Title 1");
        book1.setIsbn("123456");

        auth1.getBooks().add(book1);

        //necesitamos guardar en un objeto nuevo porque devuelve un id
        Author aSaved = authorRepository.save(auth1);
        Book bSaved = bookRepository.save(book1);

        bookRepository.save(bSaved);
        authorRepository.save(aSaved);

        System.out.println("Book count" + bookRepository.count());


        Publisher pub1 = new Publisher();
        pub1.setAddress("Adress");
        pub1.setCity("City");
        pub1.setState("State");
        pub1.setZipCode("CP01");
        pub1.setPublisherName("Name");

        publisherRepository.save(pub1);
        System.out.println("Publishers: " + publisherRepository.count());


    }
}
