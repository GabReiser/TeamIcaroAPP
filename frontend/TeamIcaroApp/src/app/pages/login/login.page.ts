import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { IonicModule } from '@ionic/angular';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss'],
    standalone: true,
    imports: [
      CommonModule,
      FormsModule,
      IonicModule  // ✅ Apenas uma única importação do IonicModule
    ],
})

export class LoginPage {
  email: string = '';
  senha: string = '';

  constructor(private router: Router, private http: HttpClient) {}

  login() {
    const payload = { email: this.email, senha: this.senha };

    this.http.post('https://seu-backend.com/api/login', payload).subscribe({
      next: (res: any) => {
        localStorage.setItem('token', res.token); // Armazena o token JWT
        this.router.navigate(['/home']); // Redireciona para a home
      },
      error: (err) => {
        console.error('Erro no login:', err);
      }
    });
  }

  loginComGoogle() {
    window.location.href = 'https://seu-backend.com/api/oauth2/google';
  }

  recuperarSenha() {
    console.log('Recuperação de senha ainda não implementada');
  }
  
  ionViewWillEnter() {
    const tabs = document.querySelector('ion-tab-bar');
    if (tabs) {
      tabs.style.display = 'none';
    }
  }

  ionViewWillLeave() {
    const tabs = document.querySelector('ion-tab-bar');
    if (tabs) {
      tabs.style.display = 'flex';
    }
  }
}
