import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { delay } from 'rxjs/operators';

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
}

@Injectable({
  providedIn: 'root',
})
export class TreinoService {
  private mockUrl = 'assets/mocks/treinos.json';

  constructor(private http: HttpClient) {}

  getTreinosDoDia(): Observable<Exercise[]> {
    return this.http.get<Exercise[]>(this.mockUrl);
  }

  finalizarTreino(): Observable<{ status: number; message: string }> {
    return of({ status: 200, message: 'Treino finalizado com sucesso!' }).pipe(delay(1000)); // Simula 1s de resposta
  }
}
