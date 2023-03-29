import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddModuleQuestionsComponent } from './add-module-questions.component';

describe('AddModuleQuestionsComponent', () => {
  let component: AddModuleQuestionsComponent;
  let fixture: ComponentFixture<AddModuleQuestionsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddModuleQuestionsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddModuleQuestionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
