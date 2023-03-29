import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddCategoryComponent } from './pages/admin/add-category/add-category.component';
import { AddModuleQuestionsComponent } from './pages/admin/add-module-questions/add-module-questions.component';
import { AddQuestionComponent } from './pages/admin/add-question/add-question.component';
import { DashboardComponent } from './pages/admin/dashboard/dashboard.component';
import { UpdateQuestionModuleComponent } from './pages/admin/update-question-module/update-question-module.component';
import { ViewCategoriesComponent } from './pages/admin/view-categories/view-categories.component';
import { ViewModelueQuestionsComponent } from './pages/admin/view-modelue-questions/view-modelue-questions.component';
import { ViewQuestionsComponent } from './pages/admin/view-questions/view-questions.component';
import { WelcomeComponent } from './pages/admin/welcome/welcome.component';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { ProfileComponent } from './pages/profile/profile.component';
import { RegisterComponent } from './pages/register/register.component';
import { InstructionsComponent } from './pages/user/instructions/instructions.component';
import { LoadQuizComponent } from './pages/user/load-quiz/load-quiz.component';
import { StartTestComponent } from './pages/user/start-test/start-test.component';
import { UserDashboardComponent } from './pages/user/user-dashboard/user-dashboard.component';
import { AdminGuard } from './services/admin.guard';
import { NormalGuard } from './services/normal.guard';
 
const routes: Routes = [
  {
    path:'',
    component:HomeComponent,
    pathMatch:'full'
  },
  {
    path:'register',
    component:RegisterComponent,
    pathMatch:'full'
  },
  {
    path:'login',
    component:LoginComponent,
    pathMatch:'full'
  },
  {
    path:'admin',
    component:DashboardComponent,
   
    canActivate: [AdminGuard],
    children:[
      {
        path:'',
        component:WelcomeComponent
      },
      {
        path:'profile',
        component:ProfileComponent
      },
      {
        path:'categories',
        component:ViewCategoriesComponent
      },
      {
        path:'add-category',
        component:AddCategoryComponent
      },
      {
        path:'questions',
        component:ViewQuestionsComponent
      },
      {
        path:'add-question',
         component:AddQuestionComponent
      },
      {
        path:'question/:qid',
        component:UpdateQuestionModuleComponent
      },
      {
        path:'view-questions/:qid/:title',
        component:ViewModelueQuestionsComponent
      },
      {
        path:'add-module-questions/:qid/:title',
        component:AddModuleQuestionsComponent
      }
       
      
    ],
     
  },
  {
    path:'user-dashboard',
    component:UserDashboardComponent,
    canActivate:[NormalGuard],
    children:[
      {
        path:'profile',
        component:ProfileComponent
      },
     
      {
        path:':catId',
        component:LoadQuizComponent
      },
      {
        path:'instructions/:qid',
        component:InstructionsComponent
      },
     
    ],
    
  },
  {
    path:'start_test/:qid',
    component:StartTestComponent,
    canActivate:[NormalGuard]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
