package com.example.demo.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.example.demo.Model.Library;

class LibraryTest {

    @Test
    void testConstructor() {
        
        Library library = new Library(1, "Test Book Title", "Test Author", 2023);

        assertEquals(1, library.getIsbn());
        assertEquals("Test Book Title", library.getTitle());
        assertEquals("Test Author", library.getAuthor());
        assertEquals(2023, library.getPublication_year());
    }

    @Test
    void testSettersAndGetters() {

        Library library = new Library();

        library.setIsbn(2);
        library.setTitle("Another Test Book");
        library.setAuthor("Another Test Author");
        library.setPublication_year(2021);

        assertEquals(2, library.getIsbn());
        assertEquals("Another Test Book", library.getTitle());
        assertEquals("Another Test Author", library.getAuthor());
        assertEquals(2021, library.getPublication_year());
    }

    @Test
    void testToString() {
        
        Library library = new Library(3, "Test ToString Title", "Test ToString Author", 2020);

        String expectedString = "Library [isbn=3, title=Test ToString Title, author=Test ToString Author, publication_year=2020]";

        assertEquals(expectedString, library.toString());
    }
}
   