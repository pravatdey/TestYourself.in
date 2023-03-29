import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { QuestionService } from 'src/app/services/question.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-instructions',
  templateUrl: './instructions.component.html',
  styleUrls: ['./instructions.component.css']
})
export class InstructionsComponent implements OnInit{

  qid:any;
  question:any;
   constructor(private _route:ActivatedRoute,private _question:QuestionService,private _router:Router){}
  ngOnInit(): void {
     this.qid=this._route.snapshot.params['qid']
   
     this._question.getQuestion(this.qid).subscribe((data:any)=>{
      // console.log(data);
      this.question=data;
     },
     (error)=>{
      // console.log(error);
      alert("Error in loding quiestion data")
      
     })
  }
  start_test(){
    Swal.fire({
      title: 'Do you want to start the test?',
      showCancelButton: true,
      confirmButtonText: 'State',
    }).then((result) => {
      /* Read more about isConfirmed, isDenied below */
      if (result.isConfirmed) {
        this._router.navigate(['/start_test/' +this.qid]);
      } else if (result.isDenied) {
        Swal.fire('Changes are not saved', '', 'info')
      }
    })
  }

}
