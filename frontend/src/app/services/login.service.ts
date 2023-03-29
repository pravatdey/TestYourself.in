import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import baseUrl from './helper';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
   public loginStatuSubject=new Subject<boolean>();
  constructor(private http:HttpClient) { }
  //get current user
  public getCurrentUser(){
    return this.http.get(`${baseUrl}/current-user`)
  }
  //generate token
  public generateToken(LoginData:any){
    return this.http.post(`${baseUrl}/generate-token`,LoginData)
  }
  
  //login user:set token in localStorage
  public loginUser(token: string){
    localStorage.setItem("token",token);
    
    return true;
  }

    //isLogin:user is login or not
    public isLoggedIn(){
      let tokenStr=localStorage.getItem("token");
      if(tokenStr==undefined || tokenStr==''||tokenStr==null){
        return false;
      }else{
        return true;
      }
    }
    //logout: remove token from local storage
    public logout(){
      localStorage.removeItem("token");
      return true;
    }
    //function get token
    public getToken(){
      return localStorage.getItem("token");
    }
    //set userDetail
    public setUser(user: any){
      localStorage.setItem("user",JSON.stringify(user));
    }
    //get user
    public getUser(){
      let userStr=localStorage.getItem("user");
      if(userStr!=null){
        return JSON.parse(userStr);
      }else{
        this.logout();
        return null;
      }
    }
    //get user roll 
    public getUserRole(){
      let user=this.getUser()
      return user.authorities[0].authority;//single work after complete project for double
    } 
    
  }

  