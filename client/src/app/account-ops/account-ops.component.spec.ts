import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AccountOpsComponent } from './account-ops.component';

describe('AccountOpsComponent', () => {
  let component: AccountOpsComponent;
  let fixture: ComponentFixture<AccountOpsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AccountOpsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AccountOpsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
