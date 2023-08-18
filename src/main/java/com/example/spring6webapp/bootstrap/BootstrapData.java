package com.example.spring6webapp.bootstrap;

import com.example.spring6webapp.domain.Author;
import com.example.spring6webapp.domain.Book;
import com.example.spring6webapp.domain.Publisher;
import com.example.spring6webapp.repositories.AuthorRepository;
import com.example.spring6webapp.repositories.BookRepository;
import com.example.spring6webapp.repositories.PublisherRepository;
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
        eric.setFirstName("Eric");
        eric.setLastName("Evans");

        Book ericbk = new Book();
        ericbk.setTitle("Domain Driven Design");
        ericbk.setIsbn("123456");

        Publisher pubs = new Publisher();
        pubs.setPublisherName("Ronak Patel");
        pubs.setAddress("upper sherman ave, Hamilton, Ontario");
        pubs.setState("Ontario");
        pubs.setZip("L1W 2R3");

        Author ericSaved = authorRepository.save(eric);
        Book ericbkSaved = bookRepository.save(ericbk);
        Publisher pubsSaved = publisherRepository.save(pubs);

        Author rod = new Author();
        rod.setLastName("Tukeja");
        rod.setFirstName("Manav");

        Book noEJB = new Book();
        noEJB.setTitle("A monk who sold his ferrari");
        noEJB.setIsbn("123412");

        Author rodSaved  = authorRepository.save(rod);
        Book noEJBSaved = bookRepository.save(noEJB);

        ericSaved.getBook().add(ericbkSaved);
        rodSaved.getBook().add(noEJBSaved);



        authorRepository.save(ericSaved);
        authorRepository.save(rodSaved);

        System.out.println("In Bootstrap");
        System.out.println("Author Count: " + authorRepository.count());
        System.out.println("Book Count: " + bookRepository.count());
        System.out.println("Publisher Count: " + publisherRepository.count());


    }
}
