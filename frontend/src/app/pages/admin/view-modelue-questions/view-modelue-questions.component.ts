import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { QuestionService } from 'src/app/services/question.service';
import { QuestionsService } from 'src/app/services/questions.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-view-modelue-questions',
  templateUrl: './view-modelue-questions.component.html',
  styleUrls: ['./view-modelue-questions.component.css']
})
export class ViewModelueQuestionsComponent implements OnInit {
   

  qId: any;
  qTitle:any;
  questions:any;
 
  constructor(private _router:ActivatedRoute,private _quetions:QuestionsService,private _snak:MatSnackBar){
    
  }
  ngOnInit(): void {
    this.qId= this._router.snapshot.params['qid'];
    this.qTitle=this._router.snapshot.params['title'];
    this._quetions.getQuestionsOfMOdeule(this.qId).subscribe(
      (data:any)=>{
        console.log(data);
        this.questions=data;
        
      },(error)=>{console.log(error);
      }
    )
     
  }
  //delete question
  deleteQuestion(qid:any){
     Swal.fire({
      icon:'info',
     showCancelButton:true,
     confirmButtonText:'Delete',
     title:'Are you sure, want to delete'

     }).then((result)=>{
      if(result.isConfirmed){
        this._quetions.deleteQuestion(qid).subscribe(
          (data)=>{
            this._snak.open('Question Deleted','',{
              duration:3000,
              verticalPosition:'top'
            });
            this.questions=this.questions.filter((q: any)=>q.questionId!=qid)
            
          },
          (error)=>{
            this._snak.open('Error in delete Question','',{
              duration:3000,
            });
            console.log(error);
            
          }
        )
      }
     })
    
  }
}
