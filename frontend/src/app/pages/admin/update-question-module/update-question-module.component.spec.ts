import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateQuestionModuleComponent } from './update-question-module.component';

describe('UpdateQuestionModuleComponent', () => {
  let component: UpdateQuestionModuleComponent;
  let fixture: ComponentFixture<UpdateQuestionModuleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateQuestionModuleComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UpdateQuestionModuleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
