import { Routes } from '@angular/router';
import { provideHttpClient } from '@angular/common/http';

export const routes: Routes = [
  {
    path: 'home',
    loadComponent: () => import('./pages/home/home.page').then(m => m.HomePage),
    providers: [provideHttpClient()]
  },
  {
    path: 'treino',
    loadComponent: () => import('./pages/treino/treino.page').then(m => m.TreinoPage),
    providers: [provideHttpClient()]
  },
  {
    path: 'dieta',
    loadComponent: () => import('./pages/dieta/dieta.page').then(m => m.DietaPage)
  },
  {
    path: 'perfil',
    loadComponent: () => import('./pages/perfil/perfil.page').then(m => m.PerfilPage)
  },
  {
    path: 'login',
    loadComponent: () => import('./pages/login/login.page').then(m => m.LoginPage)
  },
  {
    path: 'resumo',
    loadComponent: () => import('./pages/resumo/resumo.page').then(m => m.ResumoPage)
  }

];
