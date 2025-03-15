import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class DietaService {
  private apiUrl = 'https://seu-backend.com/api/dieta'; // 🔥 Substituir pela API real
  private mockUrl = 'assets/mocks/dieta.json';

  constructor(private http: HttpClient) {}

  getDieta(): Observable<any> {
    return this.http.get<any>(this.mockUrl);
  }
}
