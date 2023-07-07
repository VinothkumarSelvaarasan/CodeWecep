import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import { BankRoutingModule } from "./bank-routing.module";
import { TransactionComponent } from "./components/transaction/transaction.component";
import { ReactiveFormsModule } from "@angular/forms";
import { HttpClientModule } from "@angular/common/http";
import { AuthModule } from "../auth/auth.module";
import { LogoutComponent } from "../auth/components/logout/logout.component";

@NgModule({
  declarations: [TransactionComponent],
  imports: [
    CommonModule,
    BankRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    AuthModule
  ],
})
export class BankModule {}
