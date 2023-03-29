import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewModelueQuestionsComponent } from './view-modelue-questions.component';

describe('ViewModelueQuestionsComponent', () => {
  let component: ViewModelueQuestionsComponent;
  let fixture: ComponentFixture<ViewModelueQuestionsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewModelueQuestionsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ViewModelueQuestionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
