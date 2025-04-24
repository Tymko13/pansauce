import {Component, inject} from '@angular/core';
import {MatIconModule, MatIconRegistry} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {DomSanitizer} from '@angular/platform-browser';
import {NgIf} from '@angular/common';
import {Router} from '@angular/router';
import {ConfirmDialogComponent} from '../../confirm-dialog/confirm-dialog.component';
import {MatDialog} from '@angular/material/dialog';
import {AuthService} from '../../_auth/auth.service';

@Component({
  selector: 'app-login-logout-btn',
  imports: [MatIconModule, MatButtonModule, NgIf],
  styles: `
    mat-icon {
      color: var(--text-color);
      font-size: 2.5rem;
      width: 2.5rem;
      height: 2.5rem;
      line-height: 2.5rem;
      transition-duration: 0.25s;
    }
    mat-icon:hover {
      color: var(--accent-color);
    }
    button {
      height: 2.5rem;
      width: 2.5rem;
      padding: 0;
      display: inline-flex;
      align-items: center;
      justify-content: center;
      margin: 1rem;
    }
  `,
  template: `
    <button mat-icon-button  (click)="act()">
      <mat-icon *ngIf="!authService.isLoggedIn()">login</mat-icon>
      <mat-icon *ngIf="authService.isLoggedIn()">logout</mat-icon>
    </button>
  `,
  standalone: true
})
export class LoginLogoutBtnComponent {
  private iconRegistry = inject(MatIconRegistry);
  private sanitizer = inject(DomSanitizer);

  protected authService = inject(AuthService);
  private dialog = inject(MatDialog);
  private router = inject(Router);

  constructor() {
    this.iconRegistry.addSvgIcon('login',
      this.sanitizer.bypassSecurityTrustResourceUrl('assets/icons/login.svg'));
    this.iconRegistry.addSvgIcon('logout',
      this.sanitizer.bypassSecurityTrustResourceUrl('assets/icons/logout.svg'));
  }


  act() {
    if(!this.authService.isLoggedIn()) {
      this.router.navigate(['/login']).catch(err => console.log(err));
    }
    else {
      const confirmation = this.dialog.open(ConfirmDialogComponent, {
        data: {message: "Are you sure you want to log out?"}
      });
      confirmation.afterClosed().subscribe(res => {
        if(res) this.authService.logout();
      });
    }
  }
}
