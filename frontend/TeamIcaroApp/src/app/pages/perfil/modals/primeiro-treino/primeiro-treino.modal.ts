import { Component } from '@angular/core';
import { ModalController } from '@ionic/angular';
import { IonicModule } from '@ionic/angular';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-primeiro-treino-modal',
  templateUrl: './primeiro-treino.modal.html',
  styleUrls: ['./primeiro-treino.modal.scss'],
  standalone: true,
  imports: [IonicModule, CommonModule] // <-- Aqui está a correção
})
export class PrimeiroTreinoModal {
  constructor(private modalCtrl: ModalController) {}

  fecharModal() {
    this.modalCtrl.dismiss();
  }
}