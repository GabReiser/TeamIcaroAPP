import { Component } from '@angular/core';
import { IonicModule, NavController } from '@ionic/angular';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
import { addIcons } from 'ionicons';
import { homeOutline, barbellOutline, fitnessOutline, personOutline } from 'ionicons/icons';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss'],
  standalone: true,
  imports: [IonicModule, CommonModule, RouterModule],
})
export class NavbarComponent {

  tabs = [
    { name: 'Home', icon: 'home-outline', route: '/home', active: false },
    { name: 'Treino', icon: 'barbell-outline', route: '/workout', active: false },
    { name: 'Dieta', icon: 'fitness-outline', route: '/diet', active: false },
    { name: 'Perfil', icon: 'person-outline', route: '/profile', active: false },
  ];
  
  constructor(private navCtrl: NavController, private router: Router) {
    addIcons({
      'home-outline': homeOutline,
      'barbell-outline': barbellOutline,
      'fitness-outline': fitnessOutline,
      'person-outline': personOutline,
    });
  }

  navigateToHome() {
    this.navCtrl.navigateForward('/home', {
      animated: true,
      animationDirection: 'forward',
    });
  }

  navigateToWorkout() {
    this.navCtrl.navigateForward('/workout', {
      animated: true,
      animationDirection: 'forward',
    });
  }

  navigateToDiet() {
    this.navCtrl.navigateForward('/diet', {
      animated: true,
      animationDirection: 'forward',
    });
  }

  navigateToProfile() {
    this.navCtrl.navigateForward('/profile', {
      animated: true,
      animationDirection: 'forward',
    });
  }

  navigateTo(route: string) {
    this.router.navigate([route], { state: { animate: true } }); // Estado adicional para controle, se necessário
  }
}
