import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Store } from "@ngrx/store";
import { Observable, catchError, of, switchMap, tap, throwError } from "rxjs";
import { User } from "../../types/user";
import { Router } from "@angular/router";
import { AuthService } from "../../services/auth.service";

@Component({
  selector: "app-login",
  templateUrl: "./login.component.html",
  styleUrls: ["./login.component.css"],
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  loginError$: Observable<{ [key: string]: string }>;

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      userId: ["", [Validators.required]],
      password: ["", Validators.required],
      role: ["", Validators.required],
    });
  }

  onSubmit(): void {
    if (this.loginForm.invalid) {
      this.loginError$ = of({
        message: "Please make sure you have filled all the required fields",
      });
      return;
    } else {
      const { userId, password, role } = this.loginForm.value;
      this.loginError$ = this.authService
        .login({ username: userId, password, role })
        .pipe(
          tap((response) => {
            localStorage.setItem("token", response.token);
            if (role === "ADMIN") {
              this.router.navigate(["/auth/users"]);
            } else {
              this.router.navigate(["/bank"], { queryParams: { userId }});
            }
          }),
          catchError((error) => {
            console.error("Login error:", error);
            return of({ message: "Invalid Credentials" });
          })
        );
    }
  }
}
