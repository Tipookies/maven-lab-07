package com.bank.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

/**
 * Kiểm thử bổ sung cho các lớp tài khoản.
 */
public class AccountExtraTest {

  /**
   * Kiểm thử lịch sử giao dịch của tài khoản vãng lai.
   */
  @Test
  public void testCheckingTransactionHistory() {
    CheckingAccount account = new CheckingAccount(1001L, 1000);

    account.deposit(500);
    account.withdraw(300);

    String history = account.getTransactionHistory();

    assertTrue(history.contains("Lịch sử giao dịch của tài khoản 1001"));
    assertTrue(history.contains("Nạp tiền vãng lai"));
    assertTrue(history.contains("Rút tiền vãng lai"));
  }

  /**
   * Kiểm thử nạp tiền không hợp lệ.
   */
  @Test
  public void testCheckingInvalidDeposit() {
    CheckingAccount account = new CheckingAccount(1001L, 1000);

    account.deposit(-100);

    assertEquals(1000, account.getBalance(), 0.001);
    assertEquals(0, account.getTransactionList().size());
  }

  /**
   * Kiểm thử rút tiền không hợp lệ.
   */
  @Test
  public void testCheckingInvalidWithdraw() {
    CheckingAccount account = new CheckingAccount(1001L, 1000);

    account.withdraw(-100);

    assertEquals(1000, account.getBalance(), 0.001);
    assertEquals(0, account.getTransactionList().size());
  }

  /**
   * Kiểm thử thiết lập danh sách giao dịch null.
   */
  @Test
  public void testSetTransactionListWithNull() {
    CheckingAccount account = new CheckingAccount(1001L, 1000);

    account.setTransactionList(null);

    assertTrue(account.getTransactionList().isEmpty());
  }

  /**
   * Kiểm thử thiết lập danh sách giao dịch khác null.
   */
  @Test
  public void testSetTransactionList() {
    CheckingAccount account = new CheckingAccount(1001L, 1000);
    ArrayList<Transaction> transactions = new ArrayList<>();
    transactions.add(new Transaction(
        Transaction.TYPE_DEPOSIT_CHECKING,
        100,
        1000,
        1100));

    account.setTransactionList(transactions);

    assertEquals(1, account.getTransactionList().size());
  }

  /**
   * Kiểm thử equals và hashCode.
   */
  @Test
  public void testEqualsAndHashCode() {
    CheckingAccount first = new CheckingAccount(1001L, 1000);
    CheckingAccount second = new CheckingAccount(1001L, 2000);
    CheckingAccount third = new CheckingAccount(1002L, 1000);

    assertTrue(first.equals(second));
    assertEquals(first.hashCode(), second.hashCode());
    assertTrue(!first.equals(third));
    assertTrue(!first.equals(null));
  }
}