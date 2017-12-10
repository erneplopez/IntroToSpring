package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    private void initData(){

        Author author01=new Author("Eric","Evans");
        Author author02=new Author("Rod","Jonhson");
        authorRepository.save(author01);
        authorRepository.save(author02);

        Publisher publisher01=new Publisher("HeartBooks.com","3630 SW 5 ST");
        Publisher publisher02=new Publisher("ElcSys","4268 NW 5 ST");
        publisherRepository.save(publisher01);
        publisherRepository.save(publisher02);

        Book book01=new Book("J2EE Development without EJB","23444",publisher01);
        Book book02= new Book("Domain Driven Design","1234",publisher02);


        book01.getAuthors().add(author01);
        book02.getAuthors().add(author02);
        bookRepository.save(book01);
        bookRepository.save(book02);



    }
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }
}
