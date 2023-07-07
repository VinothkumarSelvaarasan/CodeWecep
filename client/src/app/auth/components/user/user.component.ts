import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Observable, of } from "rxjs";
import { User } from "../../types/user";
import { AuthService } from "../../services/auth.service";

@Component({
  selector: "app-user",
  templateUrl: "./user.component.html",
  styleUrls: ["./user.component.scss"],
})
export class UserComponent implements OnInit {
  userForm: FormGroup;
  userError$: Observable<string>;
  userSuccess$: Observable<string>;
  isFormSubmitted: boolean = false;

  constructor(
    private formBuilder: FormBuilder,
    private authServie: AuthService
  ) {}

  ngOnInit(): void {
    this.userForm = this.formBuilder.group({
      userId: ["", [Validators.required]],
      password: ["", [Validators.required]],
      role: ["", [Validators.required]],
    });
  }

  onSubmit() {
    this.isFormSubmitted = true;
    this.userSuccess$ = of('');
    this.userError$ = of('');
    if (this.userForm.invalid) {
      return;
    } else {
      const { userId, password, role } = this.userForm.value;
      const user: User = {
        userId,
        password,
        role,
      };
      this.authServie.createUser(user).subscribe(
        (res: any) => {
          this.userSuccess$ = of(res.message);
        },
        (error) => {
          this.userError$ = of("Unable to create user");
        }
      );
    }
  }
}
