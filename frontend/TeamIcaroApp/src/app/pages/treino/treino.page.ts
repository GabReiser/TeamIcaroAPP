import { Component, OnInit } from '@angular/core';
import { IonicModule, NavController, ToastController } from '@ionic/angular'; // ✅ Importando o ToastController
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { TreinoService } from '../../services/treino.service';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';

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
    private toastController: ToastController, // ✅ Adicionando ToastController
    private sanitizer: DomSanitizer
  ) {}

  ngOnInit() {
    this.loadTreinos();
  }

  loadTreinos() {
  this.treinoService.getTreinosDoDia(this.selectedDay).subscribe(
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

  diasTreino = [
  { label: 'Day 1', value: 'day1' },
  { label: 'Day 2', value: 'day2' },
  { label: 'Day 3', value: 'day3' },
  { label: 'Day 4', value: 'day4' }
];

selectedDay = 'day1'; // valor padrão

onDayChange(event: any) {
  const day = event.detail.value;
  console.log('Dia selecionado:', day);
  this.loadTreinos(); // ou outra função para carregar os treinos desse dia
}

getEmbeddedVideoUrl(exercise: Exercise): SafeResourceUrl | null {
  if (!exercise?.videoUrl) return null;
  const videoId = this.extractYouTubeVideoId(exercise.videoUrl);
  if (!videoId) return null;
  const embedUrl = `https://www.youtube.com/embed/${videoId}`;
  return this.sanitizer.bypassSecurityTrustResourceUrl(embedUrl);
}

  private extractYouTubeVideoId(url: string): string | null {
    const regex = /[?&]v=([^&#]*)/;
    const match = url.match(regex);
    return match ? match[1] : null;
  }

}
