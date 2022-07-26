import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RefereesComponent } from './referee.component';

describe('RefereeComponent', () => {
  let component: RefereesComponent;
  let fixture: ComponentFixture<RefereesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RefereesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RefereesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
