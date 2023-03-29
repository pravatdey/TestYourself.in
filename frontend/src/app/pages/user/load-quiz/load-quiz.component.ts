import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { QuestionService } from 'src/app/services/question.service';

@Component({
  selector: 'app-load-quiz',
  templateUrl: './load-quiz.component.html',
  styleUrls: ['./load-quiz.component.css']
})
export class LoadQuizComponent implements OnInit {
  catId:any;
  questions:any;
  constructor(private _router:ActivatedRoute,private _question:QuestionService){}
  ngOnInit(): void {

   this._router.params.subscribe((params)=>{
   this.catId=params['catId'];
   if(this.catId==0){
    console.log("Load all the quiz");
    this._question.getActiveQuizzes().subscribe((data:any)=>{
      this.questions=data;
      console.log(this.questions);
    },
    (error)=>{
      console.log(error);
      alert("Error load questions Module")
      
    })
   }else{
    console.log("Load specific quiz");
    this._question.getActiveQuizzesOfCategory(this.catId).subscribe((data:any)=>{
      this.questions=data;
    },
    (error)=>{
      alert("Error in loading quiz data")
    })
   }
    
   })
   
   
  }

}
