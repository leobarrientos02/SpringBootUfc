import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FightsComponent } from './fights.component';

describe('FightComponent', () => {
  let component: FightsComponent;
  let fixture: ComponentFixture<FightsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FightsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FightsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
