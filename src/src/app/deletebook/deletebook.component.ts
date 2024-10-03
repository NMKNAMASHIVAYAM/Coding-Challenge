import { Component } from '@angular/core';
import { AuthServiceService } from '../auth-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-deletebook',
  templateUrl: './deletebook.component.html',
  styleUrl: './deletebook.component.css'
})
export class DeletebookComponent {
  // isbnToDelete: number; // Variable to hold the ISBN of the book to delete

  // constructor(private authService: AuthServiceService,private _router: Router) { }

  // // Method to delete the book
  // deleteBook(): void {
  //   const token = localStorage.getItem("jwt"); // Retrieve JWT from local storage
  //   if (this.isbnToDelete && token) {
  //     this.authService.deleteBook(this.isbnToDelete, token).subscribe(
  //       response => {
  //         console.log('Book deleted successfully', response);
          
  //         this.isbnToDelete = null;
  //         this._router.navigate(['/admindash']);
  //       },
  //       error => {
  //         console.error('Error deleting book', error);
  //         this._router.navigate(['/admindash']);
  //       }
  //     );
  //   } else {
  //     console.error('Please provide a valid ISBN and ensure you are logged in');
  //   }
  // }

  isbn: number; // Variable to hold ISBN input

  constructor(private authService: AuthServiceService) {}

  deleteBook(): void {
    const token = localStorage.getItem("jwt"); // Retrieve JWT from local storage

    if (this.isbn && token) {
      this.authService.deleteBook(this.isbn, token).subscribe(
        response => {
          console.log('Book deleted successfully', response);
          this.isbn = null; // Clear the ISBN input after deletion
        },
        error => {
          console.error('Error deleting book', error);
        }
      );
    } else {
      console.error('Please provide a valid ISBN and ensure you are logged in.');
    }
  }
}