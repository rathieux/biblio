import { Component, signal } from '@angular/core';
import { Router, RouterLink, RouterOutlet } from '@angular/router';
import { AuthService } from './service/auth-service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-root',
  imports: [RouterLink, RouterOutlet, CommonModule],
  templateUrl: './app.html',
  styleUrl: './app.css',
})
export class App {
  protected readonly title = signal('biblio-front');

  constructor(
    protected authService: AuthService,
    private router: Router
  ) {}

  logOut() {
    this.authService.disconnect();
    this.router.navigate(['/login']);
  }
}
