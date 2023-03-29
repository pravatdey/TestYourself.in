import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { QuestionsService } from 'src/app/services/questions.service';
import Swal from 'sweetalert2';
 
// import {AngularEditorConfig} from '@kolkov/angular-editor';
@Component({
  
  selector: 'app-add-module-questions',
  templateUrl: './add-module-questions.component.html',
  styleUrls: ['./add-module-questions.component.css']
})
export class AddModuleQuestionsComponent implements OnInit {
  // config: AngularEditorConfig = {
  //   editable: true,
  //   spellcheck: true,
  //   height: '15rem',
  //   minHeight: '5rem',
  //   placeholder: 'Enter text here...',
  //   translate: 'no',
  //   defaultParagraphSeparator: 'p',
  //   defaultFontName: 'Arial',
   
  // };
  
   
  qId: any;
  qTitle:any;
  questions={
    quiz: {
      qid:'',
    }, 
    content:'',
    option1:'',
    option2:'',
    option3:'',
    option4:'',
    answer:'',
  };

  constructor(private _router:ActivatedRoute,private _question:QuestionsService){}
  ngOnInit(): void {
    this.qId=this._router.snapshot.params['qid'];
    this.qTitle=this._router.snapshot.params['title'];
     this.questions.quiz['qid']=this.qId;
  }
  formSubmit1(){
     if(this.questions.content.trim()=='' || this.questions.content==null){
      return;
     }
     if(this.questions.option1.trim()=='' || this.questions.option1==null){
      return;
     }
     if(this.questions.option2.trim()=='' || this.questions.option2==null){
      return;
     }
     if(this.questions.option3.trim()=='' || this.questions.option3==null){
      return;
     }
     if(this.questions.option4.trim()=='' || this.questions.option4==null){
      return;
     }

     //form submit
     this._question.addQuestionsInmodule(this.questions).subscribe(
      (data)=>{
        Swal.fire("Success","Question Added and Add Another one","success")
        this.questions.content='';
        this.questions.option1='';
        this.questions.option2='';
        this.questions.option3='';
        this.questions.option4='';
        this.questions.answer='';
      },
      (error)=>{
        Swal.fire("Error","Something went wrong to add question","error")
      }
     )
  }

}
