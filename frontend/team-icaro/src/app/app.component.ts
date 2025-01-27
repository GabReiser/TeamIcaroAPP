import { Component, Renderer2 } from '@angular/core';
import { IonApp, IonRouterOutlet } from '@ionic/angular/standalone';
import { NavbarComponent } from './navbar/navbar.component';

@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html',
  imports: [IonApp, IonRouterOutlet, NavbarComponent],
})
export class AppComponent {
  constructor(private renderer: Renderer2) {}

  onActivate() {
    const outlet = document.querySelector('router-outlet');
    if (outlet) {
      this.renderer.addClass(outlet, 'page-enter');
      setTimeout(() => this.renderer.removeClass(outlet, 'page-enter'), 500);
    }
  }

  onDeactivate() {
    const outlet = document.querySelector('router-outlet');
    if (outlet) {
      this.renderer.addClass(outlet, 'page-leave');
      setTimeout(() => this.renderer.removeClass(outlet, 'page-leave'), 500);
    }
  }
}
