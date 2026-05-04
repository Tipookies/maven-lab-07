package com.bank.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Kiểm thử lớp SavingsAccount.
 */
public class SavingsAccountTest {

  /**
   * Kiểm thử nạp tiền tài khoản tiết kiệm.
   */
  @Test
  public void testDeposit() {
    SavingsAccount account = new SavingsAccount(2001L, 6000);

    account.deposit(500);

    assertEquals(6500, account.getBalance(), 0.001);
    assertEquals(1, account.getTransactionList().size());
  }

  /**
   * Kiểm thử rút tiền hợp lệ.
   */
  @Test
  public void testWithdraw() {
    SavingsAccount account = new SavingsAccount(2001L, 6000);

    account.withdraw(500);

    assertEquals(5500, account.getBalance(), 0.001);
    assertEquals(1, account.getTransactionList().size());
  }

  /**
   * Kiểm thử rút tiền vượt giới hạn.
   */
  @Test
  public void testWithdrawOverLimit() {
    SavingsAccount account = new SavingsAccount(2001L, 6000);

    account.withdraw(1500);

    assertEquals(6000, account.getBalance(), 0.001);
    assertEquals(0, account.getTransactionList().size());
  }
}