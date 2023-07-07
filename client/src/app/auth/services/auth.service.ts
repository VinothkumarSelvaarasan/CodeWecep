import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { environment } from "src/environments/environment";
import { map, Observable } from "rxjs";
import { User } from "../types/user";

@Injectable({
  providedIn: "root",
})
export class AuthService {
  private loginUrl = `${environment.apiUrl}`;

  constructor(private http: HttpClient) {}

  login(user: User): Observable<{ [key: string]: string }> {
    return this.http.post<{ token: string }>(
      `${this.loginUrl}/authenticate`,
      user
    );
  }

  getToken() {
    return localStorage.getItem("token");
  }

  getUsers(): Observable<User[]> {
    return this.http.get<User[]>(`${this.loginUrl}/fetchusers`);
  }

  createUser(user: User): Observable<User> {
    return this.http.post<User>(`${this.loginUrl}/user`, user);
  }
}
