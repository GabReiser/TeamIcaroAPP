import { animate, style, transition, trigger } from '@angular/animations';

export const fadeAnimation = trigger('routeAnimations', [
  transition('* <=> *', [
    style({ opacity: 0, transform: 'scale(0.98)' }),
    animate('300ms ease-in-out', style({ opacity: 1, transform: 'scale(1)' }))
  ])
]);
