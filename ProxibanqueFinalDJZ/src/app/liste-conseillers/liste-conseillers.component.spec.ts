import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListeConseillersComponent } from './liste-conseillers.component';

describe('ListeConseillersComponent', () => {
  let component: ListeConseillersComponent;
  let fixture: ComponentFixture<ListeConseillersComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListeConseillersComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListeConseillersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
