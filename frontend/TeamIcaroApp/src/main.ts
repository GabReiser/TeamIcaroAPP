import { bootstrapApplication } from '@angular/platform-browser';
import { RouteReuseStrategy, provideRouter, withPreloading, PreloadAllModules } from '@angular/router';
import { IonicRouteStrategy, provideIonicAngular } from '@ionic/angular/standalone';
import { provideAnimations } from '@angular/platform-browser/animations'; 
import { IonicModule } from '@ionic/angular'
import { addIcons } from 'ionicons';
import { home, barbell, nutrition, person, restaurant, chevronDown, chevronUp } from 'ionicons/icons';


import { routes } from './app/app.routes';
import { AppComponent } from './app/app.component';
import { provideHttpClient } from '@angular/common/http';
import { importProvidersFrom } from '@angular/core';

addIcons({
  home,
  barbell,
  nutrition,
  person,
  restaurant,
  'chevron-down': chevronDown,
  'chevron-up': chevronUp
});


bootstrapApplication(AppComponent, {
  providers: [
    { provide: RouteReuseStrategy, useClass: IonicRouteStrategy },
    provideIonicAngular(),
    provideRouter(routes, withPreloading(PreloadAllModules)),
    provideHttpClient(),
    provideAnimations(),
    importProvidersFrom(IonicModule.forRoot({})),
    
  ],
});
