import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserService } from 'src/app/services/user.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  constructor(private userService: UserService, private snack: MatSnackBar) { }
  public user = {
    username: '',
    password: '',
    firstName: '',
    lastName: '',
    email: '',
    phone: ''
  }
  ngOnInit(): void {
  }
  formSubmit() {
    console.log(this.user); 
    this.userService.adduser(this.user).subscribe(
      (data) => {
        console.log(data);
         Swal.fire('Successfully', 'Your  registered' ,'success')
      },
      (error) => {
        console.log(error);
        this.snack.open('User Already Registered!!', '', {
          verticalPosition:'top', 
          duration:3000
          
          
        })
        // alert("Something went wrong");
      }
    )

  }

}
