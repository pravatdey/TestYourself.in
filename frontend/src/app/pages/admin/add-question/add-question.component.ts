import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CategoryService } from 'src/app/services/category.service';
import { QuestionService } from 'src/app/services/question.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-question',
  templateUrl: './add-question.component.html',
  styleUrls: ['./add-question.component.css']
})
export class AddQuestionComponent implements OnInit {
     
   categories:any

   questionData={
    title:'',
    description:'',
    maxMarks:'',
    numberOfQuestions:'',
    active:true,
    category:{
      cid:'',
    }
   }

  constructor(private _category:CategoryService,private _snack:MatSnackBar,private _quiz:QuestionService){}
  ngOnInit(): void {
     this._category.categories().subscribe(
      (data:any)=>{
        //category load 
        this.categories=data;
        console.log(this.categories);
        
      },
      (error)=>{
        console.log(error);
        Swal.fire('Error!!','error in loading data from server','error')
        
      }
     )
  }
  //  add Question

  addQuestion(){
     if(this.questionData.title.trim()==''||this.questionData.title==null){
      this._snack.open('Title Required !!', '',{
        verticalPosition:'top', 
        duration:3000
      });
      return;
     }
     if(this.questionData.description.trim()==''||this.questionData.description==null){
      this._snack.open('Description Required !!', '',{
        verticalPosition:'top', 
        duration:3000
      });
      return;
     }
     if(this.questionData.maxMarks.trim()==''||this.questionData.maxMarks==null){
      this._snack.open('Marks Required !!', '',{
        verticalPosition:'top', 
        duration:3000
      });
      return;
     }
     if(this.questionData.numberOfQuestions.trim()==''||this.questionData.numberOfQuestions==null){
      this._snack.open('Number of question Required !!', '',{
        verticalPosition:'top', 
        duration:3000
      });
      return;
     }
     if( this.questionData.category==null){
      this._snack.open('Category are Required !!', '',{
        verticalPosition:'top', 
        duration:3000
      });
      return;
     }
     this._quiz.addQuestion(this.questionData).subscribe(
      (data)=>{
        Swal.fire('Success','Question is added','success')
       this.questionData={
          title:'',
          description:'',
          maxMarks:'',
          numberOfQuestions:'',
          active:true,
          category:{
            cid:'',
          },
      };
    },
    (error)=>{
      Swal.fire('Error !!','Error while adding question','error')
    }
     )
    
  }
}
