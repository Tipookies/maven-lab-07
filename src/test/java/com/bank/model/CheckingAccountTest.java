package com.bank.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Kiểm thử lớp CheckingAccount.
 */
public class CheckingAccountTest {

  /**
   * Kiểm thử nạp tiền tài khoản vãng lai.
   */
  @Test
  public void testDeposit() {
    CheckingAccount account = new CheckingAccount(1001L, 1000);

    account.deposit(500);

    assertEquals(1500, account.getBalance(), 0.001);
    assertEquals(1, account.getTransactionList().size());
  }

  /**
   * Kiểm thử rút tiền tài khoản vãng lai.
   */
  @Test
  public void testWithdraw() {
    CheckingAccount account = new CheckingAccount(1001L, 1000);

    account.withdraw(300);

    assertEquals(700, account.getBalance(), 0.001);
    assertEquals(1, account.getTransactionList().size());
  }

  /**
   * Kiểm thử rút tiền quá số dư.
   */
  @Test
  public void testWithdrawInsufficientFunds() {
    CheckingAccount account = new CheckingAccount(1001L, 1000);

    account.withdraw(2000);

    assertEquals(1000, account.getBalance(), 0.001);
    assertEquals(0, account.getTransactionList().size());
  }
}