import { Component } from '@angular/core';
import { ModalController } from '@ionic/angular';
import { IonicModule } from '@ionic/angular';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-metas-cumpridas-modal',
  templateUrl: './metas-cumpridas.modal.html',
  styleUrls: ['./metas-cumpridas.modal.scss'],
  standalone: true,
  imports: [IonicModule, CommonModule] // <-- Aqui está a correção
})
export class MetasCumpridasModal {
  metas = [
    { descricao: 'Beber 3,6L de água por dia', concluido: true },
    { descricao: 'Evitar açúcar e farinha branca', concluido: false },
    { descricao: 'Comer mais proteínas', concluido: true },
  ];

  constructor(private modalCtrl: ModalController) {}

  fecharModal() {
    this.modalCtrl.dismiss();
  }
}
