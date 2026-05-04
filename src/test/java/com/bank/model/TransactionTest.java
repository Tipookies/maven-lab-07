package com.bank.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Kiểm thử lớp Transaction.
 */
public class TransactionTest {

  /**
   * Kiểm thử chuỗi mô tả loại giao dịch.
   */
  @Test
  public void testGetTypeString() {
    assertEquals(
        "Nạp tiền vãng lai",
        Transaction.getTypeString(Transaction.TYPE_DEPOSIT_CHECKING));
    assertEquals(
        "Rút tiền vãng lai",
        Transaction.getTypeString(Transaction.TYPE_WITHDRAW_CHECKING));
    assertEquals(
        "Nạp tiền tiết kiệm",
        Transaction.getTypeString(Transaction.TYPE_DEPOSIT_SAVINGS));
    assertEquals(
        "Rút tiền tiết kiệm",
        Transaction.getTypeString(Transaction.TYPE_WITHDRAW_SAVINGS));
    assertEquals("Không rõ", Transaction.getTypeString(999));
  }

  /**
   * Kiểm thử tóm tắt giao dịch.
   */
  @Test
  public void testGetTransactionSummary() {
    Transaction transaction = new Transaction(
        Transaction.TYPE_DEPOSIT_CHECKING,
        500,
        1000,
        1500);

    String summary = transaction.getTransactionSummary();

    assertTrue(summary.contains("Nạp tiền vãng lai"));
    assertTrue(summary.contains("$1000.00"));
    assertTrue(summary.contains("$500.00"));
    assertTrue(summary.contains("$1500.00"));
  }
}