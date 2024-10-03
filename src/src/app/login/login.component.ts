import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { UserDetails } from '../user-details';
import { UserserviceService } from '../userservice.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  user: UserDetails;
  isValid: boolean;
  
  constructor(
    private _userService: UserserviceService,
    private _router: Router
  ) {
    this.user = new UserDetails();
    this.isValid = false;
  }
  
  isLoginFormVisible: boolean = false; 
  
  showLoginForm() {
    this.isLoginFormVisible = true;
  }
  
  login(loginForm: NgForm) {
    if (loginForm.invalid) {
      return;
    }
    this.isValid = true;

    // Call the login API with username and password
    this._userService.Login(this.user.name, this.user.password).subscribe(
      (response: any) => {
        console.log("Login response:", response);

        if (response === 1) { // If login is successful
          // Fetch user details after successful login
          this._userService.getUserByName(this.user.name).subscribe(
            (userDetails: any) => {
              console.log("User details fetched:", userDetails);
              
              // Assign role to this.user.role
              this.user.role = userDetails.roles;

              if (this.user.role && this.user.role.toUpperCase() === 'ROLE_USER') { 
                console.log("Login successful, navigating to user dashboard.");
                this._router.navigate(['/userdash']); 
              } else {
                alert("You are not authorized as a user.");
              }
            },
            (error) => {
              console.error("Error fetching user details:", error);
              alert("Failed to fetch user details.");
            }
          );
        } else {
          alert("Invalid credentials.");
        }
      },
      (error) => {
        console.error("Login error:", error);
        alert("Login failed.");
      }
    );
  }
}
