import {Component, inject } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import {FormBuilder, ReactiveFormsModule, Validators} from '@angular/forms';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInput} from '@angular/material/input';
import {MatButton} from '@angular/material/button';
import {AuthService} from '../_auth/auth.service';

@Component({
  standalone: true,
  templateUrl: 'login.component.html',
  imports: [ReactiveFormsModule, MatFormFieldModule, MatInput, MatButton],
  styleUrl: 'login.component.css'
})
export class LoginComponent{
  private authService = inject(AuthService);
  private fb = inject(FormBuilder);
  private route = inject(ActivatedRoute);
  private router = inject(Router);

  constructor() {
    if (this.authService.isLoggedIn()) {
      this.router.navigate(['/']).catch(err => console.log(err));
    }
  }

  loginForm = this.fb.group({
    username: ['', Validators.required],
    password: ['', Validators.required]
  });

  submit() {
    if(this.loginForm.valid) {
      this.authService.login({username: this.loginForm.value.username!, password: this.loginForm.value.password!}).subscribe(() => {
        this.router.navigate([this.route.snapshot.queryParams['returnUrl'] || '/'])
          .catch(err => console.log(err));
      });
    }
  }
}
