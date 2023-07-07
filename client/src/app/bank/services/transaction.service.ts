import { Injectable } from "@angular/core";
import { environment } from "src/environments/environment";
import { HttpClient } from "@angular/common/http";
import { Transaction } from "../types/transaction";
import { Observable } from "rxjs";

@Injectable({
  providedIn: "root",
})
export class TransactionService {
  private baseUrl = `${environment.apiUrl}`;

  constructor(private http: HttpClient) {}

  performTransaction(transaction: Transaction): Observable<Transaction> {
    return this.http.post<Transaction>(
      `${this.baseUrl}/transaction`,
      transaction
    );
  }

  getOutstandingBalance(userId: string): Observable<number> {
    return this.http.get<number>(
      `${this.baseUrl}/out-standing?userId=${userId}`
    );

  }

  getAllTranactions(userId: string): Observable<Transaction[]> {
    return this.http.get<Transaction[]>(
      `${this.baseUrl}/all-transactions?userId=${userId}`
    );

  }


}
