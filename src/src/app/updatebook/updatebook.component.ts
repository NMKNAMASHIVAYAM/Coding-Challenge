import { Component } from '@angular/core';
import { Books } from '../books';
import { FormBuilder, FormGroup } from '@angular/forms';
import { AuthServiceService } from '../auth-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-updatebook',
  templateUrl: './updatebook.component.html',
  styleUrl: './updatebook.component.css'
})
export class UpdatebookComponent {
  newBook: Books = {
    isbn: 0,
    title: '',
    author: '',
    publication_year: 0
  };

  // Assuming you have a form for updating
  updateBookForm: FormGroup;

  constructor(private authService: AuthServiceService,private _router: Router) {
    // this.updateBookForm = this.fb.group({
    //   isbn: [''],
    //   title: [''],
    //   author: [''],
    //   publication_year: ['']
    // });
  }

  updateBook(): void {
    const token = localStorage.getItem("jwt");
    const isbn = this.updateBookForm.value.isbn;
    const updatedBook: Books = {
      isbn: this.updateBookForm.value.isbn,
      title: this.updateBookForm.value.title,
      author: this.updateBookForm.value.author,
      publication_year: this.updateBookForm.value.publication_year
    };
  
    if (token) {
      this.authService.updateBooks(isbn, updatedBook, token).subscribe(
        response => {
          console.log('Book updated successfully', response);
          this._router.navigate(['/admindash']);
        },
        error => {
          console.error('Error updating book', error);
          this._router.navigate(['/admindash']);
        }
      );
    }
  }
  
}
