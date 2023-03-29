import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { CategoryService } from 'src/app/services/category.service';
import { QuestionService } from 'src/app/services/question.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-update-question-module',
  templateUrl: './update-question-module.component.html',
  styleUrls: ['./update-question-module.component.css']
})
export class UpdateQuestionModuleComponent implements OnInit {
 
  constructor(private _route:ActivatedRoute,private _question:QuestionService,private _cat:CategoryService,
    private _snack:MatSnackBar,private _router:Router){}
  qid=0;
  question: any;
  categories:any
  quiz:any;
   
  ngOnInit(): void {
    this.qid= this._route.snapshot.params['qid']
    this._question.getQuestion(this.qid).subscribe(
      (data:any)=>{
        this.question=data;
        console.log(this.question);
        
      },
      (error)=>{
        console.log(error);
        
      }
    );
    this._cat.categories().subscribe((data:any)=>{
      this.categories=data;
    },
    (error)=>{
      alert('error in loading categories');
    })
  }
  //update submit
  public updateData(){
     
     this._question.updateQuestion(this.question).subscribe(
      (data)=>{
        Swal.fire('Success !!','question updated','success').then((e)=>{
          this._router.navigate(['/admin/questions']);
        })
      },
      (error)=>{
        Swal.fire('Error','error in updating quesiton','error')
      }
     )
  }
 
}
