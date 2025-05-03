import {Component, inject} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {FormBuilder, ReactiveFormsModule, Validators} from '@angular/forms';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInput} from '@angular/material/input';
import {MatButton} from '@angular/material/button';
import {AuthService} from '../_auth/auth.service';
import {NgIf} from '@angular/common';

@Component({
  standalone: true,
  templateUrl: 'login.component.html',
  imports: [ReactiveFormsModule, MatFormFieldModule, MatInput, MatButton, NgIf],
  styleUrl: 'login.component.css'
})
export class LoginComponent {
  private authService = inject(AuthService);
  private fb = inject(FormBuilder);
  private route = inject(ActivatedRoute);
  private router = inject(Router);

  constructor() {
    if (this.authService.isLoggedIn()) {
      this.router.navigate(['/']).catch(err => console.log(err));
    }
    this.loginForm.valueChanges.subscribe(() => this.setError(false))
  }

  loginForm = this.fb.group({
    username: ['', Validators.required],
    password: ['', Validators.required]
  });

  error = false;

  setError(error: boolean) {
    this.error = error;
    if(error) {
      this.loginForm.get('username')?.setErrors({wrong: true});
      this.loginForm.get('password')?.setErrors({wrong: true});
    } else {
      this.loginForm.get('username')?.setErrors(null);
      this.loginForm.get('password')?.setErrors(null);
    }
  }

  submit() {
    if (this.loginForm.valid) {
      this.authService.login({
        username: this.loginForm.value.username!,
        password: this.loginForm.value.password!
      }).subscribe({
        next: data => {
          this.router.navigate([this.route.snapshot.queryParams['returnUrl'] || '/'])
            .catch(err => console.log(err));
        },
        error: error => this.setError(true)
      });
    }
  }
}
