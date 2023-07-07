export interface Transaction {
  userId: string;
  transactionAmount: number;
  transactionType: "CREDIT" | "DEBIT";
}
