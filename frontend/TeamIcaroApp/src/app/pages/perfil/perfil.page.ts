import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { IonicModule } from '@ionic/angular';
import { ModalController } from '@ionic/angular';
import { PrimeiroTreinoModal } from '../perfil/modals/primeiro-treino/primeiro-treino.modal';
import { MetasCumpridasModal } from '../perfil/modals/metas-cumpridas/metas-cumpridas.modal';
import { PersistenciaModal } from '../perfil/modals/persistencia/persistencia.modal';

@Component({
  selector: 'app-perfil',
  templateUrl: './perfil.page.html',
  styleUrls: ['./perfil.page.scss'],
  standalone: true,
  imports:[
    IonicModule, 
    FormsModule, 
    CommonModule
  ]
})
export class PerfilPage {
  usuario = {
    nome: 'Gabriel Oliveira',
    nivel: 5,
    xp: 350,
    proximoNivelXp: 500,
    conquistas: [
      { nome: 'Primeiro Treino', icone: 'barbell', tipo: 'primeiroTreino' },
      { nome: 'Metas Cumpridas', icone: 'checkmark-circle', tipo: 'metasCumpridas' },
      { nome: 'Persistência', icone: 'hourglass', tipo: 'persistencia' }
    ],
    treinosConcluidos: 4,
    metasConcluidas: 2
  };

  constructor(private modalCtrl: ModalController) {}

  aumentarXP(valor: number) {
    this.usuario.xp += valor;
    if (this.usuario.xp >= this.usuario.proximoNivelXp) {
      this.subirNivel();
    }
  }

  subirNivel() {
    this.usuario.nivel++;
    this.usuario.xp = 0;
    this.usuario.proximoNivelXp *= 1.2; // Aumenta a dificuldade para o próximo nível
  }

  async abrirModal(tipo: string) {
    let modal = null;
  
    switch (tipo) {
      case 'primeiroTreino':
        modal = await this.modalCtrl.create({ component: PrimeiroTreinoModal });
        break;
      case 'metasCumpridas':
        modal = await this.modalCtrl.create({ component: MetasCumpridasModal });
        break;
      case 'persistencia':
        modal = await this.modalCtrl.create({ component: PersistenciaModal });
        break;
      default:
        console.error("Tipo de modal inválido:", tipo);
        return;
    }
  
    if (modal) {
      return await modal.present();
    }
  }
}
