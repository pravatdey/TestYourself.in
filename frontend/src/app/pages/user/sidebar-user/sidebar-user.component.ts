import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CategoryService } from 'src/app/services/category.service';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-sidebar-user',
  templateUrl: './sidebar-user.component.html',
  styleUrls: ['./sidebar-user.component.css']
})
export class SidebarUserComponent implements OnInit {
  categories:any;
  constructor(private _cat:CategoryService,private _snack:MatSnackBar,public login:LoginService){}
  ngOnInit(): void {
    this._cat.categories().subscribe((data:any)=>{
      this.categories=data;
    },(error)=>{
      this._snack.open('Error in loading categories form server','',{
        duration:3000,
      })
    })
  }
  public logout(){
    this.login.logout();
    window.location.reload();
     
  }
}
