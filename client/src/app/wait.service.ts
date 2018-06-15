import {BehaviorSubject} from "rxjs/internal/BehaviorSubject";

export class WaitService {

  private readonly isWaiting = new BehaviorSubject(false);
  public readonly wait$ = this.isWaiting.asObservable();

  constructor() {
  }

  public toggle() {
    this.isWaiting.next(!this.isWaiting.value);
  }
}

