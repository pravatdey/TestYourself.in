import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from './helper';

@Injectable({
  providedIn: 'root'
})
export class QuestionsService {

  constructor(private _http:HttpClient) { }
  public getQuestionsOfMOdeule(qid:any){
    return this._http.get(`${baseUrl}/question/quiz/${qid}`)
  }
  public getQuestionsOfMOdeuleForTest(qid:any){
    return this._http.get(`${baseUrl}/question/quiz/${qid}`)
  }
  //add question
  public addQuestionsInmodule(question: any){
    return this._http.post(`${baseUrl}/question/`,question)
  }
   //delete question
   public deleteQuestion(questionId: any){
    return this._http.delete(`${baseUrl}/question/${questionId}`)
   }
   //eval Module
   public evalModule(question:any){
    return this._http.post(`${baseUrl}/question/evel-module`,question)
   }

}
