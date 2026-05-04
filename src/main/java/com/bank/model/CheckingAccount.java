package com.bank.model;

import com.bank.exception.BankException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Lớp đại diện cho tài khoản vãng lai (Checking Account).
 */
public class CheckingAccount extends Account {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(CheckingAccount.class);

  /**
   * Khởi tạo tài khoản vãng lai.
   *
   * @param accountNumber số tài khoản
   * @param balance số dư ban đầu
   */
  public CheckingAccount(long accountNumber, double balance) {
    super(accountNumber, balance);
  }

  /**
   * Nạp tiền vào tài khoản.
   *
   * @param amount số tiền nạp
   */
  @Override
  public void deposit(double amount) {
    double initialBalance = getBalance();

    try {
      doDepositing(amount);
      double finalBalance = getBalance();

      Transaction transaction = new Transaction(
          Transaction.TYPE_DEPOSIT_CHECKING,
          amount,
          initialBalance,
          finalBalance);

      addTransaction(transaction);

      LOGGER.debug("Nạp tiền thành công: tài khoản {}, số tiền {}",
          getAccountNumber(), amount);

    } catch (BankException e) {
      LOGGER.debug("Lỗi khi nạp tiền: {}", e.getMessage());
    }
  }

  /**
   * Rút tiền khỏi tài khoản.
   *
   * @param amount số tiền rút
   */
  @Override
  public void withdraw(double amount) {
    double initialBalance = getBalance();

    try {
      doWithdrawing(amount);
      double finalBalance = getBalance();

      Transaction transaction = new Transaction(
          Transaction.TYPE_WITHDRAW_CHECKING,
          amount,
          initialBalance,
          finalBalance);

      addTransaction(transaction);

      LOGGER.debug("Rút tiền thành công: tài khoản {}, số tiền {}",
          getAccountNumber(), amount);

    } catch (BankException e) {
      LOGGER.debug("Lỗi khi rút tiền: {}", e.getMessage());
    }
  }
}