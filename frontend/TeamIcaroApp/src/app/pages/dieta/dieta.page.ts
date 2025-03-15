import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { IonContent, IonHeader, IonTitle, IonToolbar } from '@ionic/angular/standalone';
import { IonicModule } from '@ionic/angular';
import { trigger, state, style, transition, animate } from '@angular/animations';
import { DietaService } from 'src/app/services/dieta.service';

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
export class DietaPage implements OnInit {
  refeicoes: any[] = [];
  objetivos: any[] = [];

  constructor(private cd: ChangeDetectorRef, private dietaService: DietaService){}

  ngOnInit() {
    this.dietaService.getDieta().subscribe((data) => {
      this.refeicoes = data.refeicoes;
      this.objetivos = data.objetivos;
    });
  }

  toggleRefeicao(refeicao: any) {
    refeicao.expanded = !refeicao.expanded;
    setTimeout(() => this.cd.detectChanges(), 50);
  }
}