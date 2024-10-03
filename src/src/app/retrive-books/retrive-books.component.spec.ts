import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RetriveBooksComponent } from './retrive-books.component';

describe('RetriveBooksComponent', () => {
  let component: RetriveBooksComponent;
  let fixture: ComponentFixture<RetriveBooksComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [RetriveBooksComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RetriveBooksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
