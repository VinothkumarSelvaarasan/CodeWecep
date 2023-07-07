import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Observable, map, of } from "rxjs";
import { AuthService } from "src/app/auth/services/auth.service";
import { User } from "src/app/auth/types/user";
import { TransactionService } from "../../services/transaction.service";
import { Transaction } from "../../types/transaction";
import { ActivatedRoute, Router } from '@angular/router';


@Component({
  selector: "app-transaction",
  templateUrl: "./transaction.component.html",
  styleUrls: ["./transaction.component.scss"],
})
export class TransactionComponent implements OnInit {
  transactionForm: FormGroup;

  transactionError$: Observable<string>;
  transactionSuccess$: Observable<string>;
  users$: Observable<User[]>;
  isFormSubmitted: boolean = false;
  userId: string;
  outstandingBalance$: Observable<number>;
  transactions$: Observable<Transaction[]>;
  outstandBalanceTiDisplay: number;

  errorMessages: { [key: string] : string} = {
    NOT_ENOUGH_BALANCE: 'Not enough balance to complete transaction'
  }

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private trnasactionService: TransactionService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.route.queryParams.subscribe(params => {
      if (params.userId) {
        this.userId = params.userId;
        this.outstandingBalance$ = this.trnasactionService.getOutstandingBalance(this.userId);
        this.getAllTransactions();
      }else {
        this.router.navigate(["/auth"]);
      }
    })

    this.outstandingBalance$.subscribe(res => {
      this.outstandBalanceTiDisplay = res;
    })
    
  
  }

  ngOnInit(): void {
    this.transactionForm = this.formBuilder.group({
      amount: ["", ""],
      type: ["", ""],
    });
    this.users$ = this.authService
      .getUsers()
      .pipe(map((users) => users.filter((u) => u.role === "CLIENT")));
  }

  onSubmit() {
    this.isFormSubmitted = true;
    this.transactionError$ = of('');
    this.transactionSuccess$ = of('');
    if (this.transactionForm.invalid) {
      return;
    } else {
      const {  amount, type } = this.transactionForm.value;
      const transaction: Transaction = {
        userId: this.userId,
        transactionAmount: amount,
        transactionType: type,
      };
      this.trnasactionService.performTransaction(transaction).subscribe(
        (res: any) => {
          this.transactionSuccess$ = of(res.message);
          this.trnasactionService.getOutstandingBalance(this.userId).subscribe(res => {
            this.outstandBalanceTiDisplay = res;
            this.getAllTransactions();
          });
         
        },
        ({error}) => {
          this.transactionError$ = of(this.errorMessages[error.message]);
        }
      );
    }
  }

  getAllTransactions() {
    this.transactions$ = this.trnasactionService.getAllTranactions(this.userId);

  }
}
