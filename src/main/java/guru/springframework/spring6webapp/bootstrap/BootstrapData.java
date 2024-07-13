package guru.springframework.spring6webapp.bootstrap;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.domain.Publisher;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;
import guru.springframework.spring6webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author eric = new Author();
        eric.setFirstName("Erik");
        eric.setLastName("Evens");

        Author rod = new Author();
        rod.setFirstName("Rod");
        rod.setLastName("Johnson");

        Book ddd = new Book();
        ddd.setTitle("Domain Driven Design");
        ddd.setSbn("122456");

        Author savedEric = authorRepository.save(eric);
        Book savedBook = bookRepository.save(ddd);

        Book noEJB = new Book();
        noEJB.setTitle("J2EE Development without J8B");
        noEJB.setSbn("7891011");

        Author savedRod = authorRepository.save(rod);
        Book savednoEJB = bookRepository.save(noEJB);

        savedEric.getBooks().add(savedBook);
        savedRod.getBooks().add(savednoEJB);
        savedBook.getAuthors().add(savedEric);
        savednoEJB.getAuthors().add(savedRod);

        //persist authors
        authorRepository.save(savedEric);
        authorRepository.save(savedRod);

        Publisher publisher = new Publisher();
        publisher.setPublisherName("Hanser Verlag") ;
        publisher.setAddress("Strasse 10");
        publisher.setCity("Melbourne");
        publisher.setZip(21147);

        Publisher savedPublisher = publisherRepository.save(publisher);
        savedBook.setPublisher(savedPublisher);
        savednoEJB.setPublisher(savedPublisher);

        bookRepository.save(savedBook);
        bookRepository.save(savednoEJB);

        System.out.println("In Bootstrap");
        System.out.println("Author count: " + authorRepository.count());
        System.out.println("Book count: " + bookRepository.count());
        System.out.println("PublisherRepository count: " + publisherRepository.count());

    }
}
