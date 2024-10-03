package com.example.demo.Service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Model.Library;
import com.example.demo.repository.LibraryRepository;

@Service
public class LibraryService {

	@Autowired
	private LibraryRepository repo;
	
	public List<Library> showAll(){
		return repo.findAll();
	}
	
	public Library findById(int isbn) {
	    return repo.findById(isbn)
	               .orElseThrow(() -> new ResourceNotFoundException("ISBN " + isbn + " not found"));
	}
	
	public void addBooks(Library library) {
		repo.save(library);
	}
	public String updateBook(Integer isbn, Library updatedBook) {
	    Optional<Library> optionalBook = repo.findById(isbn);

	    if (optionalBook.isPresent()) {
	        Library existingBook = optionalBook.get();

	        existingBook.setAuthor(updatedBook.getAuthor() != null ? updatedBook.getAuthor() : existingBook.getAuthor());
	        existingBook.setTitle(updatedBook.getTitle() != null ? updatedBook.getTitle() : existingBook.getTitle());
	        existingBook.setPublication_year(updatedBook.getPublication_year() != 0 ? updatedBook.getPublication_year() : existingBook.getPublication_year());

	        repo.save(existingBook);
	        return "Book updated successfully.";
	    }

	    return "Failed to update the book. Book with the given ISBN not found.";
	}

	public boolean deleteBook(Integer isbn) {
		  Optional<Library> optionalCar = repo.findById(isbn);

	        if (optionalCar.isPresent()) {
	            repo.deleteById(isbn);
	            return true; 
	        } else {
	            return false; 
	        }
	}
}
