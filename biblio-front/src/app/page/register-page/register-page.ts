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
  selector: 'app-register-page',
  imports: [ReactiveFormsModule, RouterLink],
  templateUrl: './register-page.html',
  styleUrl: '../login-page.css',
})
export class RegisterPage implements OnInit {
  protected formAuth!: FormGroup;
  protected formCtrlUsername!: FormControl;
  protected formCtrlPassword!: FormControl;
  protected registerError = signal(false);

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

  public register() {
    this.authService.register(this.formAuth.getRawValue()).subscribe({
      next: () => this.router.navigate(['/login']),
      error: () => this.registerError.set(true),
    });
  }
}
