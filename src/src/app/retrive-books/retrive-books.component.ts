import { Component } from '@angular/core';
import { Books } from '../books';
import { UserserviceService } from '../userservice.service';

@Component({
  selector: 'app-retrive-books',
  templateUrl: './retrive-books.component.html',
  styleUrls: ['./retrive-books.component.css'], // Fixed typo from 'styleUrl' to 'styleUrls'
})
export class RetriveBooksComponent {
  libraries: Books[] = []; // Array to store the list of libraries
  filteredLibraries: Books[] = []; // Array to store filtered books
  isbnFilter: string = ''; // Variable to hold the ISBN filter input

  constructor(private userrService: UserserviceService) {}

  ngOnInit(): void {
    this.loadLibraries(); // Call the method when the component initializes
  }

  // Method to call the service and load the libraries
  loadLibraries(): void {
    this.userrService.retrivebooks().subscribe(
      (data: Books[]) => {
        this.libraries = data;
        this.filteredLibraries = data; // Initialize filteredLibraries with all data
        console.log('Libraries loaded: ', this.libraries);
      },
      (error) => {
        console.error('Error loading libraries: ', error);
      }
    );
  }

  // Method to filter books by ISBN
  filterByISBN(): void {
    if (this.isbnFilter) {
      this.filteredLibraries = this.libraries.filter(book =>
        book.isbn.toString().includes(this.isbnFilter)
      );
    } else {
      // Reset to original list if filter input is empty
      this.filteredLibraries = this.libraries;
    }
  }
}
