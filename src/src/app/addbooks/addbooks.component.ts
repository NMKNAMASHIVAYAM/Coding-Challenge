import { Component } from '@angular/core';
import { Books } from '../books';
import { UserserviceService } from '../userservice.service';
import { AuthServiceService } from '../auth-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-addbooks',
  templateUrl: './addbooks.component.html',
  styleUrl: './addbooks.component.css'
})
export class AddbooksComponent {

  newBook: Books = {
    isbn: 0,
    title: '',
    author: '',
    publication_year: 0
  };

  constructor(private authService: AuthServiceService,private _router: Router) { }

  addBook(): void {
    const token = localStorage.getItem("jwt"); // Assuming you're using JWT for authorization

    if (token) {
      this.authService.addBooks(this.newBook, token).subscribe(
        (data: Books) => {
          console.log('Book added successfully', data);
          this._router.navigate(['/admindash']);
          // Reset the newBook object for the next entry
          // this.newBook = {
          //   isbn: 0,
          //   title: '',
          //   author: '',
          //   publication_year: 0
          // }; 
        },
        error => {
          console.error('Error adding book', error);
        }
      );
    }
  }
}
