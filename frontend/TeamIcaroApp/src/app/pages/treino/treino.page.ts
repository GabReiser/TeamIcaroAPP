import { Component, OnInit } from '@angular/core';
import { IonicModule, NavController, ToastController } from '@ionic/angular'; // ✅ Importando o ToastController
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { TreinoService } from '../../services/treino.service';

interface ExerciseSet {
  series: string;
  instructions: string;
  weight?: number;
}

interface Exercise {
  name: string;
  instructions: string;
  videoUrl?: string;
  sets: ExerciseSet[];
  completed: boolean;
}

@Component({
  selector: 'app-treino',
  templateUrl: './treino.page.html',
  styleUrls: ['./treino.page.scss'],
  standalone: true,
  imports: [IonicModule, CommonModule, FormsModule],
})
export class TreinoPage implements OnInit {
  exercises: Exercise[] = [];
  isLoading = false; // ✅ Para bloquear duplo clique

  constructor(
    private treinoService: TreinoService,
    private router: Router,
    private navCtrl: NavController,
    private toastController: ToastController // ✅ Adicionando ToastController
  ) {}

  ngOnInit() {
    this.loadTreinos();
  }

  loadTreinos() {
    this.treinoService.getTreinosDoDia().subscribe(
      (data) => {
        this.exercises = data.map(ex => ({ ...ex, completed: false }));
      },
      (error) => {
        console.error('Erro ao carregar os treinos do dia:', error);
      }
    );
  }

  toggleCompleted(index: number) {
    this.exercises[index].completed = !this.exercises[index].completed;
  }

  async finalizarTreino() {
    if (this.isLoading) return; // ✅ Evita múltiplos cliques
    this.isLoading = true;

    const allCompleted = this.exercises.every(ex => ex.completed);

    if (!allCompleted) {
      this.isLoading = false;
      await this.showErrorToast();
      return;
    }

    // ✅ Simula o envio para o Backend
    this.treinoService.finalizarTreino().subscribe(
      async (response) => {
        if (response.status === 200) {
          await this.showSuccessToast();
          this.navCtrl.navigateForward('/resumo', {
            animated: true,
            animationDirection: 'forward',
          });
        }
      },
      async () => {
        this.isLoading = false;
        await this.showErrorToast();
      }
    );
  }

  async showSuccessToast() {
    const toast = await this.toastController.create({
      message: 'Treino finalizado com sucesso!',
      duration: 3000,
      color: 'success', // Verde
      position: 'bottom',
      buttons: [{ text: 'Fechar', role: 'cancel' }],
    });

    await toast.present();
  }

  // ✅ Função para exibir o toast vermelho
  async showErrorToast() {
    const toast = await this.toastController.create({
      message: 'Você precisa concluir todos os exercícios antes de finalizar o treino.',
      duration: 3000, // 3 segundos
      color: 'danger', // Vermelho
      position: 'top', // No topo da tela
      buttons: [
        {
          text: 'Fechar',
          role: 'cancel',
        },
      ],
    });

    await toast.present();
  }
}
