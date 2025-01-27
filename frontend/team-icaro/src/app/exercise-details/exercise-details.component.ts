import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-exercise-details',
  template: `
  <div class="exercise-details">
    <h2>{{ workout.name }}</h2>
    <video controls [src]="workout.videoUrl"></video>
    <p>Repetições: {{ workout.reps }}</p>
    <button (click)="contactSupport()">Entrar em Contato</button>
    <ion-button (click)="closeModal()">Fechar</ion-button>
  </div>
  `,
  styleUrls: ['./exercise-details.component.scss'],
})
export class ExerciseDetailsComponent {
  
  @Input() workout: any;

  contactSupport() {
    console.log('Entrando em contato com o suporte...');
  }

  closeModal() {
    document.querySelector('ion-modal')?.dismiss();
  }
}
