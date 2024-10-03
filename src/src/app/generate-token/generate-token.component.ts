import { Component } from '@angular/core';
import { AuthRequest } from '../auth-request';
import { AuthServiceService } from '../auth-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-generate-token',
  templateUrl: './generate-token.component.html',
  styleUrl: './generate-token.component.css'
})
export class GenerateTokenComponent {
  userName : string;
  passWord : string;
  result : any;
  authRequest : AuthRequest;
  jwt : string;
  userDetails: any;
  generate() {
    this._authService.getUserByName(this.userName).subscribe(userDetails => {
      this.userDetails = userDetails;
      
      
      if (this.userDetails && this.userDetails.roles && this.userDetails.roles === 'ROLE_ADMIN') {
        
       
        this.authRequest.username = this.userName;
        this.authRequest.password = this.passWord;

        this._authService.generateTokenNew(this.authRequest).subscribe(x => {
          localStorage.setItem('jwt', x);
          this.jwt = x;
          this._router.navigate(['/admindash']);
         
        });
      } else {
        
        console.error('Access denied: Only admins can generate tokens.');
      }
    });
  }
  constructor(private _authService : AuthServiceService,private _router: Router) {
    this.authRequest = new AuthRequest();
 
 
  }

}
