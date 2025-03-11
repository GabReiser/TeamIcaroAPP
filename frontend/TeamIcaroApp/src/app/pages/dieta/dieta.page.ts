import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { IonContent, IonHeader, IonTitle, IonToolbar } from '@ionic/angular/standalone';
import { IonicModule } from '@ionic/angular';
import { trigger, state, style, transition, animate } from '@angular/animations';

@Component({
  selector: 'app-dieta',
  templateUrl: './dieta.page.html',
  styleUrls: ['./dieta.page.scss'],
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    IonicModule  // ✅ Apenas uma única importação do IonicModule
  ],
  animations: [
    trigger('expandAnimation', [
      state('collapsed', style({
        height: '0px',
        opacity: 0,
        padding: '0px',
        overflow: 'hidden'
      })),
      state('expanded', style({
        height: '*',
        opacity: 1,
        padding: '10px'
      })),
      transition('collapsed <=> expanded', animate('300ms ease-in-out'))
    ])
  ]
})
export class DietaPage {
  constructor(private cd: ChangeDetectorRef){}
  refeicoes = [
    {
      horario: '07:00',
      titulo: 'Refeição 1',
      expanded: false, // Estado de expansão
      alimentos: [
        { nome: 'Pão de forma tradicional', quantidade: '2 fatias ou 50g' },
        { nome: 'Requeijão', quantidade: '20g' },
        { nome: 'Ovo', quantidade: '2 unidades médias ou 100g' },
      ],
    },
    {
      horario: '12:00',
      titulo: 'Refeição 2',
      expanded: false, // Estado de expansão
      alimentos: [
        { nome: 'Arroz branco', quantidade: '180g' },
        { nome: 'Feijão', quantidade: '80g' },
        { nome: 'Carne bovina magra', quantidade: '120g' },
      ],
    },
  ];

  objetivos = [
    { descricao: 'Beber 3,6L de água por dia', concluido: false },
    { descricao: 'Evitar açúcar e farinha branca', concluido: false },
    { descricao: 'Comer mais proteínas', concluido: false },
  ];

  toggleRefeicao(refeicao: any) {
    refeicao.expanded = !refeicao.expanded;
    setTimeout(() => this.cd.detectChanges(), 50); // 🔥 Pequeno delay para Angular atualizar
  }
}