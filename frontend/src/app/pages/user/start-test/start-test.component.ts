import { LocationStrategy } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { QuestionsService } from 'src/app/services/questions.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-start-test',
  templateUrl: './start-test.component.html',
  styleUrls: ['./start-test.component.css']
})
export class StartTestComponent implements OnInit {
  qid:any;
  questions:any;

  markGot=0;
  correctAnswer=0;
  attempted=0;

  isSubmit=false;
  timer:any;
  constructor(private locatiost:LocationStrategy,private _route:ActivatedRoute,private _question:QuestionsService){}
  ngOnInit(): void {
     this.BackButton();
     this.qid= this._route.snapshot.params['qid']
     this.loadQuestions();
  }
  loadQuestions(){
    this._question.getQuestionsOfMOdeuleForTest(this.qid).subscribe((data:any)=>{
     this.questions=data
     this.timer=this.questions.length*2*60
      
      console.log(data);
      this.stateTimer()
    },
    (error)=>{
      Swal.fire("Error","Error in loading questions of module","error")
    })
  }
  BackButton(){
    history.pushState(null, 'null' ,location.href)
    this.locatiost.onPopState(()=>{
      history.pushState(null,'null',location.href)
    })
  }
  submitTest(){
    Swal.fire({
      title: 'Do you want to submit the test?',
      showCancelButton: true,
      confirmButtonText: 'Submit',
    }).then((e)=>{
      if(e.isConfirmed){
        this.evalModule()
      // console.log("correct Answer"+ this.correctAnswer);
      // console.log("marks Got"+ this.markGot);
      // console.log("attemted" + this.attempted);
      // console.log(this.questions);
      }
    })
  }
  stateTimer(){
   let t= window.setInterval(()=>{
      if(this.timer<=0){
        this.evalModule()
        clearInterval(t)
      }else{
        this.timer--
      }
    },1000)
  }
  getFormattedTimer(){
    let mins=Math.floor(this.timer/60)
    let secs=this.timer-mins*60
    return `${mins} min : ${secs} sec`
  }
  evalModule(){
    //call to server to check questions

     this._question.evalModule(this.questions).subscribe((data:any)=>{
        console.log(data);  
        this.markGot=parseFloat  (Number(data.markGot).toFixed(2))
        this.correctAnswer=data.correctAnswer
        this.attempted=data.attempted
        this.isSubmit=true
        
     },(error)=>{
      console.log(error);
      
     }
     )
    // this.isSubmit=true
    // this.questions.forEach((q:any)=>{
    //   if(q.givenAnswer==q.answer){
    //   this.correctAnswer++
    //   let marksSingle=this.questions[0].quiz.maxMarks/this.questions.length
    //   this.markGot +=  marksSingle
    //   }
    //   if(q.givenAnswer.trim()!=''){
    //     this.attempted++;
    //   }
    // })
  }
  printPage(){
    window.print();
  }
}

