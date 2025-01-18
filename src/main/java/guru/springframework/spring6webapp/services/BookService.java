package guru.springframework.spring6webapp.services;

import guru.springframework.spring6webapp.dto.Book;

public interface BookService {
    Iterable<Book> findAll(); //devuelve un array de libros que podemos iterar
}
