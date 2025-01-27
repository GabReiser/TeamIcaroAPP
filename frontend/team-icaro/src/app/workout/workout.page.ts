import { Component, OnInit, AfterViewInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import Swiper from 'swiper';
import { ModalController, IonicModule } from '@ionic/angular';
import {
  trigger,
  style,
  animate,
  transition,
  query,
  stagger,
} from '@angular/animations';

@Component({
  selector: 'app-workout',
  templateUrl: './workout.page.html',
  styleUrls: ['./workout.page.scss'],
  standalone: true,
  imports: [CommonModule, FormsModule, IonicModule],
  animations: [
    trigger('listAnimation', [
      transition('* => *', [
        query(
          ':enter',
          [
            style({ opacity: 0, transform: 'translateY(10px)' }),
            stagger(100, [
              animate('0.5s ease-out', style({ opacity: 1, transform: 'translateY(0)' })),
            ]),
          ],
          { optional: true }
        ),
        query(
          ':leave',
          [
            animate('0.3s ease-in', style({ opacity: 0, transform: 'translateY(-10px)' })),
          ],
          { optional: true }
        ),
      ]),
    ]),
  ],
})
export class WorkoutPage implements OnInit, AfterViewInit {
  calendarDates: Date[] = [];
  selectedDate: string = '';
  selectedWorkouts: any[] = [];
  workouts: any = {};
  selectedExercise: any | null = null;

  constructor() {}

  ngOnInit() {
    this.generateCalendar();
    this.loadMockData();
  }

  ngAfterViewInit() {
    // Inicializar o Swiper após a visualização estar pronta
    new Swiper('.swiper-container', {
      slidesPerView: 5,
      spaceBetween: 10,
      navigation: true,
      pagination: {
        el: '.swiper-pagination',
        clickable: true,
      },
    });
  }

  generateCalendar() {
    const today = new Date();
    for (let i = -3; i <= 3; i++) {
      const date = new Date();
      date.setDate(today.getDate() + i);
      this.calendarDates.push(date);
    }
    // Seleciona a data inicial
    this.selectedDate = this.calendarDates[3].toISOString().split('T')[0];
  }

  loadMockData() {
    this.workouts = {
      '2025-01-14': [
        {
          name: 'Supino Reto',
          description: 'Fortaleça seu peitoral.',
          reps: '3x12',
          videoUrl: 'https://example.com/supino.mp4',
        },
        {
          name: 'Crucifixo Deitado',
          description: 'Melhore a amplitude dos ombros.',
          reps: '3x10',
          videoUrl: 'https://example.com/crucifixo.mp4',
        },
      ],
    };
    this.selectDate(new Date()); // Carrega os treinos para a data atual
  }

  selectDate(date: Date) {
    this.selectedDate = date.toISOString().split('T')[0];
    this.selectedWorkouts = [...(this.workouts[this.selectedDate] || [])];
  }

  openExerciseDetails(workout: any) {
    this.selectedExercise = workout;
  }

  generateMoreDates(direction: 'next' | 'prev') {
    const lastDate =
      direction === 'next'
        ? this.calendarDates[this.calendarDates.length - 1]
        : this.calendarDates[0];

    for (let i = 1; i <= 7; i++) {
      const newDate = new Date(lastDate);
      newDate.setDate(
        lastDate.getDate() + (direction === 'next' ? i : -i)
      );

      if (direction === 'next') {
        this.calendarDates.push(newDate);
      } else {
        this.calendarDates.unshift(newDate);
      }
    }
  }

  closeExerciseDetails() {
    this.selectedExercise = null;
  }

  startWorkout() {
    console.log('Treino iniciado!');
  }

  contactSupport() {
    console.log('Entrando em contato com o suporte...');
  }
  
}
