package ua.com.kneu.contollers;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BaseController<T> {

    ResponseEntity<T> save(T obj);

    ResponseEntity<T> update(Long id, T obj);

    ResponseEntity<Void> deleteById(Long id);

    ResponseEntity<Void> deleteAll();

    ResponseEntity<List<T>> findAll();

    ResponseEntity<T> findById(Long id);
}
