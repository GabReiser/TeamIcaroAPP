import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full', // Redireciona para "home" caso o caminho seja vazio
  },
  {
    path: 'home',
    loadComponent: () => import('./home/home.page').then((m) => m.HomePage), // Carrega o componente Home
  },
  {
    path: 'workout',
    loadComponent: () =>
      import('./workout/workout.page').then((m) => m.WorkoutPage), // Carrega o componente Workout
  },/*
  {
    path: 'diet',
    loadComponent: () =>
      import('./diet/diet.page').then((m) => m.DietPage), // Carrega o componente Diet
  },
  {
    path: 'profile',
    loadComponent: () =>
      import('./profile/profile.page').then((m) => m.ProfilePage), // Carrega o componente Profile
  },*/
];
