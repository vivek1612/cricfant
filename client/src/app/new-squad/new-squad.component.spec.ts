import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewSquadComponent } from './new-squad.component';

describe('NewSquadComponent', () => {
  let component: NewSquadComponent;
  let fixture: ComponentFixture<NewSquadComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewSquadComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewSquadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
