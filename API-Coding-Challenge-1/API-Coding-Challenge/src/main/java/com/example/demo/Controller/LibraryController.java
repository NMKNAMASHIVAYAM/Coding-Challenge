package com.example.demo.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Model.Library;
import com.example.demo.Service.LibraryService;



@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/books")
public class LibraryController {

	@Autowired
	private LibraryService service;
	
	
	
	
	@GetMapping(value="/RetriveBooks")
	
	public List<Library> showAll() {
		return service.showAll();
	}
	
	
	
	@GetMapping(value="/searchBookByISBN/{isbn}")
	public Library searchBookByISBN(@PathVariable int isbn) {
		return service.findById(isbn);
	}
	
	
	@PostMapping(value="/addBooks")
	public void addBooks(@RequestBody Library library) {
		service.addBooks(library);
	}
	
	
	
	@PutMapping("/updateBook/{isbn}")
	public ResponseEntity<String> updateBook(@PathVariable int isbn, @RequestBody Library updatelibrary) {
	    String resultMessage = service.updateBook(isbn, updatelibrary);

	    if (resultMessage.contains("successfully")) {
	        return ResponseEntity.status(HttpStatus.OK).body(resultMessage);
	    } else {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultMessage);
	    }
	}
	
	@DeleteMapping("/deleteBook/{isbn}")
	public ResponseEntity<?> deleteBookByIsbn(@PathVariable int isbn){
		  try {
	            boolean isDeleted = service.deleteBook(isbn);

	            if (isDeleted) {
	                return ResponseEntity.status(HttpStatus.OK).body("Book with ID " + isbn + " has been deleted successfully.");
	            } else {
	                
	                throw new ResourceNotFoundException("Book with ID " + isbn + " not found.");
	            }
	        } catch (ResourceNotFoundException e) {
	           
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	        } 
	}
}
