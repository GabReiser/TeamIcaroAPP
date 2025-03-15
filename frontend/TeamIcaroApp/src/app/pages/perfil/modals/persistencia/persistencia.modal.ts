import { Component } from '@angular/core';
import { ModalController } from '@ionic/angular';
import { IonicModule } from '@ionic/angular';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-persistencia-modal',
  templateUrl: './persistencia.modal.html',
  styleUrls: ['./persistencia.modal.scss'],
  standalone: true,
  imports: [IonicModule, CommonModule] // <-- Aqui está a correção
})
export class PersistenciaModal {
  diasSeguidos = 7; // Simulando dados de persistência

  constructor(private modalCtrl: ModalController) {}

  fecharModal() {
    this.modalCtrl.dismiss();
  }
}
