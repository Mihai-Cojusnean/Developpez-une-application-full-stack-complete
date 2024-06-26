import {Component, OnDestroy, OnInit} from '@angular/core';
import {SessionService} from "./services/session.service";
import {User} from "./interfaces/user.interface";
import {Observable, Subject, takeUntil} from "rxjs";
import {Router} from "@angular/router";
import {BreakpointObserver, Breakpoints} from "@angular/cdk/layout";
import {UserService} from "./services/user.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit, OnDestroy {
  public isSmallScreen: boolean = false;
  private destroy$: Subject<void> = new Subject<void>();

  constructor(
    private sessionService: SessionService,
    private userService: UserService,
    private router: Router,
    private breakpointObserver: BreakpointObserver) {
  }

  public ngOnInit(): void {
    this.breakpointObserver.observe([Breakpoints.XSmall])
      .pipe(takeUntil(this.destroy$))
      .subscribe(result => {
        this.isSmallScreen = result.matches;
      });
    this.autoLog();
  }

  public autoLog(): void {
    this.userService.getUser().subscribe({
        next: (user: User) => {
          this.sessionService.logIn(user);
        },
        error: (_) => {
          this.sessionService.logOut();
        }
      }
    )
  }

  public isHomePage(): boolean {
    return this.router.url == '/';
  }

  public $isLogged(): Observable<boolean> {
    return this.sessionService.$isLogged();
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }
}
