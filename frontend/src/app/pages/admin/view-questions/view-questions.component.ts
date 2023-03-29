import { Component, OnInit } from '@angular/core';
import { QuestionService } from 'src/app/services/question.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-view-questions',
  templateUrl: './view-questions.component.html',
  styleUrls: ['./view-questions.component.css']
})
export class ViewQuestionsComponent implements OnInit {
  question: any;
  // result:any;
  constructor(private _quiz: QuestionService) { }
  ngOnInit(): void {
    this._quiz.questions().subscribe(
      (data: any) => {
        this.question = data;
        console.log(this.question);
      },
      (error) => {
        console.log(error);
        Swal.fire('Error !!', 'Error in Loding data !', 'error');

      }
    )
  }
  deleteQuestion(qid: any) {
    Swal.fire({
      icon: 'info',  
      'title': 'Are you sure?',
      confirmButtonText: 'Delete',
      showCancelButton: true,
    }).then((result: any) => {
      if (result.isConfirmed) {
        this._quiz.deleteQuestion(qid).subscribe(
          (data)=>{
            this.question=this.question.filter((question: any)=>question.qid !=qid)
            Swal.fire('Success','Question deleted','success');
          },
          (error)=>{
            Swal.fire('Error','Error in deleting Question','error');
          }
          )
      }
    })


  }

}



