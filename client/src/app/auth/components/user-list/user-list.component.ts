import { Component, OnInit } from "@angular/core";
import { AuthService } from "../../services/auth.service";
import { User } from "../../types/user";
import { Observable } from "rxjs";

@Component({
  selector: "app-user-list",
  templateUrl: "./user-list.component.html",
  styleUrls: ["./user-list.component.scss"],
})
export class UserListComponent implements OnInit {
  users$: Observable<User[]>;
  constructor(private authService: AuthService) {}

  ngOnInit(): void {
    this.users$ = this.authService.getUsers();
  }
}
