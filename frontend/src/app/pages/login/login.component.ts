import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{
  LoginData={
    username:'',
    password:'',
  }
    constructor(private snack:MatSnackBar,private login:LoginService, private router:Router){}

    ngOnInit(): void {
        
    }
    formSubmit(){
      
        console.log("login btn clicked");
        if(this.LoginData.username.trim()=='' ||this.LoginData.username==null){
          this.snack.open("User name is required !!",'', {duration:3000, 
            verticalPosition:'top'});
          return;
        }
        if(this.LoginData.password.trim()=='' ||this.LoginData.password==null){
          this.snack.open("Password is required !!",'', {duration:3000, 
            verticalPosition:'top'});
          return;
        }
        //Request to sever to generate noken
        this.login.generateToken(this.LoginData).subscribe(
          (data:any)=>{
            console.log('success');
            console.log(data);

              //login...
              this.login.loginUser(data.token);
              this.login.getCurrentUser().subscribe(
                (user:any)=>{
                  this.login.setUser(user);
                  console.log(user);
                  //redirect... Admin:admin dashboard
                  //redirect... Normal:Normal dasboard
                  if(this.login.getUserRole()=='Admin'){  

                    window.location.href='/admin';
                    this.login.loginStatuSubject.next(true)
                    // this.router.navigate(['Admin']);
                  }else if(this.login.getUserRole()=='NORMAL'){
                    window.location.href='/user-dashboard/0';
                    this.login.loginStatuSubject.next(true)
                    // this.router.navigate(['user-dashboard']);
                  }else{
                    this.login.logout();
                  }
                  
                }
              );


          },
          (error)=>{
            console.log('Error !');
            console.log(error);
            this.snack.open("Invalid Details ! Try again",'',{
              duration:3000,
              verticalPosition:'top'
            })
          }
        );
    
      }
    }
