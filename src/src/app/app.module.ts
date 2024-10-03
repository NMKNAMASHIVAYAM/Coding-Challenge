import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule, Routes } from '@angular/router';
import { GenerateTokenComponent } from './generate-token/generate-token.component';
import { MenuComponentComponent } from './menu-component/menu-component.component';
import { RetriveBooksComponent } from './retrive-books/retrive-books.component';
import { LoginComponent } from './login/login.component';
import { UserdashboardComponent } from './userdashboard/userdashboard.component';
import { AddbooksComponent } from './addbooks/addbooks.component';
import { AdminComponent } from './admin/admin.component';
import { UpdatebookComponent } from './updatebook/updatebook.component';
import { DeletebookComponent } from './deletebook/deletebook.component';



const routes : Routes = 
[
  { path: 'user', component: LoginComponent },
  {path:'',component:MenuComponentComponent},
  {path:'generateToken',component:GenerateTokenComponent},
  {
    path: 'admindash',
    component: AdminComponent,
    children: [
      { path: 'addbooks', component: AddbooksComponent, outlet: 'cac' },
      { path: 'updatebooks', component: UpdatebookComponent, outlet: 'cac' },
      { path: 'deletebooks', component: DeletebookComponent, outlet: 'cac' }
      

     
    ]
  },
  {
    path: 'userdash',
    component: UserdashboardComponent,
    children: [
      { path: 'allbooks', component: RetriveBooksComponent, outlet: 'hexa' },
      

     
    ]
  }
]
@NgModule({
  declarations: [
    AppComponent,
    GenerateTokenComponent,
    MenuComponentComponent,
    RetriveBooksComponent,
    LoginComponent,
    UserdashboardComponent,
    AddbooksComponent,
    AdminComponent,
    UpdatebookComponent,
    DeletebookComponent,
  
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot(routes),
    AppRoutingModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
