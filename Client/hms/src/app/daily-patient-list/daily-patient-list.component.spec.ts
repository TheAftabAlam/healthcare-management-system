import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DailyPatientListComponent } from './daily-patient-list.component';

describe('DailyPatientListComponent', () => {
  let component: DailyPatientListComponent;
  let fixture: ComponentFixture<DailyPatientListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DailyPatientListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DailyPatientListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
