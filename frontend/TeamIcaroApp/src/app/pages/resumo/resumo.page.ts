import { Component, AfterViewInit } from '@angular/core';
import { IonicModule } from '@ionic/angular'; // ✅ Importando o IonicModule
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import JSConfetti from 'js-confetti'; // Biblioteca de confetes

@Component({
  selector: 'app-resumo',
  templateUrl: './resumo.page.html',
  styleUrls: ['./resumo.page.scss'],
  standalone: true,
  imports: [IonicModule, CommonModule, FormsModule], // ✅ Agora o IonicModule está aqui
})
export class ResumoPage implements AfterViewInit {
  private jsConfetti!: JSConfetti;

  ngAfterViewInit() {
    this.jsConfetti = new JSConfetti();
    this.triggerConfetti();
  }

  triggerConfetti() {
    this.jsConfetti.addConfetti({
      confettiColors: ['#7f5af0', '#2cb67d', '#ffffff'],
      confettiRadius: 5,
      confettiNumber: 200,
    });
  }
}
