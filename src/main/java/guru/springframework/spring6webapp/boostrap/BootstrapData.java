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

        Book book2 = new Book();
        book2.setTitle("Title 1");
        book2.setIsbn("123456");

        Publisher pub1 = new Publisher();
        pub1.setAddress("Adress");
        pub1.setCity("City");
        pub1.setState("State");
        pub1.setZipCode("CP01");
        pub1.setPublisherName("Name");

        //necesitamos guardar en un objeto nuevo porque devuelve un id
        Author aSaved = authorRepository.save(auth1);
        Book bSaved = bookRepository.save(book1);
        Book b2Saved = bookRepository.save(book2);
        Publisher pSaved = publisherRepository.save(pub1);

        //IMPORTANTE!! Antes de añadir en el set los objetos dependientes hay que hacer un save,
        //De lo contrario lanza una excepcion de transaccion no guardada
        auth1.getBooks().add(book1);
        auth1.getBooks().add(book2);
        book1.getAuthors().add(auth1);
        book2.getAuthors().add(auth1);
        book1.setPublisher(pub1);
        book2.setPublisher(pub1);
        pub1.getBooks().add(book1);
        pub1.getBooks().add(book2);


        bookRepository.save(bSaved);
        bookRepository.save(b2Saved);
        authorRepository.save(aSaved);
        publisherRepository.save(pSaved);

        System.out.println("Book count" + bookRepository.count());
        System.out.println("Publishers: " + publisherRepository.count());


    }
}
