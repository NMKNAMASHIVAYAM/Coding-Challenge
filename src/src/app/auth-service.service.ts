import { Injectable } from '@angular/core';
import { AuthRequest } from './auth-request';
import { Observable, tap } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Books } from './books';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {
  constructor(private _http : HttpClient) { }
  generateTokenNew(authRequest : AuthRequest) : Observable<any> {
    return this._http.post("http://localhost:3333/auth/generateToken",authRequest, {responseType:'text'}).pipe(
      tap((resp) => {
        alert(resp);
      })
    )
  }
  getUserByName(name:String):Observable<any>{
    return this._http.get<any>("http://localhost:3333/auth/searchByUser/"+name)
  }
  addBooks(book: Books, token: string): Observable<any> {
    const headers_object = new HttpHeaders({
      "Content-Type": "application/json",
      "Authorization": "Bearer " + token
    });
    const httpOptions = {
      headers: headers_object
    };

    
    return this._http.post("http://localhost:3333/books/addBooks",book,httpOptions);
  }
  updateBooks(isbn: number, updatedBook: Books, token: string): Observable<string> {
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    return this._http.put<string>("http://localhost:3333/books/updateBook/" +isbn, updatedBook, { headers });
  }
  deleteBook(isbn: number, token: string): Observable<any> {
    const headers_object = new HttpHeaders({
      "Content-Type": "application/json",
      "Authorization": "Bearer " + token
    });

    const httpOptions = {
      headers: headers_object
    };

    return this._http.delete("http://localhost:3333/books/deleteBook/"+isbn, httpOptions);
  }
  
}

