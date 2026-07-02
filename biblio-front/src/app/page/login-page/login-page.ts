import { Component, OnInit, signal } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../service/auth-service';

@Component({
  selector: 'app-login-page',
  imports: [ReactiveFormsModule, RouterLink],
  templateUrl: './login-page.html',
  styleUrl: '../login-page.css',
})
export class LoginPage implements OnInit {
  protected newAccount = false;

  protected formAuth!: FormGroup;
  protected formCtrlUsername!: FormControl;
  protected formCtrlPassword!: FormControl;
  protected loginError = signal(false);

  constructor(
    private authService: AuthService,
    private router: Router,
    private formBuilder: FormBuilder,
  ) {}

  ngOnInit(): void {
    this.formCtrlUsername = this.formBuilder.control('', Validators.required);
    this.formCtrlPassword = this.formBuilder.control('', [Validators.required]);

    this.formAuth = this.formBuilder.group({
      username: this.formCtrlUsername,
      password: this.formCtrlPassword,
    });
  }

  public auth() {
    this.authService.auth(this.formAuth.getRawValue()).subscribe({
      next: () => this.router.navigate(['collection']),
      error: () => this.loginError.set(true),
    });
  }

  public register() {
    this.authService.register(this.formAuth.getRawValue()).subscribe({
      next: () => this.newAccount = false,
      error: () => this.loginError.set(true),
    });
  }
}
