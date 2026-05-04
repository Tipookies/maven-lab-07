package com.bank.model;

import java.util.Locale;

/**
 * Lớp đại diện cho một giao dịch.
 */
public class Transaction {

  public static final int TYPE_DEPOSIT_CHECKING = 1;
  public static final int TYPE_WITHDRAW_CHECKING = 2;
  public static final int TYPE_DEPOSIT_SAVINGS = 3;
  public static final int TYPE_WITHDRAW_SAVINGS = 4;

  private int type;
  private double amount;
  private double initialBalance;
  private double finalBalance;

  /**
   * Khởi tạo giao dịch.
   *
   * @param type loại giao dịch
   * @param amount số tiền giao dịch
   * @param initialBalance số dư ban đầu
   * @param finalBalance số dư cuối
   */
  public Transaction(int type, double amount,
      double initialBalance, double finalBalance) {
    this.type = type;
    this.amount = amount;
    this.initialBalance = initialBalance;
    this.finalBalance = finalBalance;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public double getInitialBalance() {
    return initialBalance;
  }

  public void setInitialBalance(double initialBalance) {
    this.initialBalance = initialBalance;
  }

  public double getFinalBalance() {
    return finalBalance;
  }

  public void setFinalBalance(double finalBalance) {
    this.finalBalance = finalBalance;
  }

  /**
   * Trả về chuỗi mô tả loại giao dịch.
   *
   * @param type loại giao dịch
   * @return mô tả loại giao dịch
   */
  public static String getTypeString(int type) {
    switch (type) {
      case TYPE_DEPOSIT_CHECKING:
        return "Nạp tiền vãng lai";
      case TYPE_WITHDRAW_CHECKING:
        return "Rút tiền vãng lai";
      case TYPE_DEPOSIT_SAVINGS:
        return "Nạp tiền tiết kiệm";
      case TYPE_WITHDRAW_SAVINGS:
        return "Rút tiền tiết kiệm";
      default:
        return "Không rõ";
    }
  }

  /**
   * Trả về thông tin tóm tắt giao dịch.
   *
   * @return chuỗi mô tả giao dịch
   */
  public String getTransactionSummary() {
    StringBuilder builder = new StringBuilder();

    builder.append("- Kiểu giao dịch: ")
        .append(getTypeString(type))
        .append(". Số dư ban đầu: $")
        .append(String.format(Locale.US, "%.2f", initialBalance))
        .append(". Số tiền: $")
        .append(String.format(Locale.US, "%.2f", amount))
        .append(". Số dư cuối: $")
        .append(String.format(Locale.US, "%.2f", finalBalance))
        .append(".");

    return builder.toString();
  }
}