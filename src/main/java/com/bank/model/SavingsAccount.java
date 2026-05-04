package com.bank.model;

import com.bank.exception.BankException;
import com.bank.exception.InsufficientFundsException;
import com.bank.exception.InvalidFundingAmountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Tài khoản tiết kiệm - thực thi các quy định về nạp và rút tiền.
 */
public class SavingsAccount extends Account {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(SavingsAccount.class);

  private static final double MAX_WITHDRAW = 1000.0;
  private static final double MIN_BALANCE = 5000.0;

  /**
   * Khởi tạo tài khoản tiết kiệm.
   *
   * @param accountNumber số tài khoản
   * @param balance số dư ban đầu
   */
  public SavingsAccount(long accountNumber, double balance) {
    super(accountNumber, balance);
  }

  /**
   * Nạp tiền vào tài khoản tiết kiệm.
   *
   * @param amount số tiền nạp
   */
  @Override
  public void deposit(double amount) {
    LOGGER.debug("Bắt đầu nạp tiền: tài khoản {}, số tiền {}",
        getAccountNumber(), amount);

    double initialBalance = getBalance();

    try {
      doDepositing(amount);
      double finalBalance = getBalance();

      Transaction transaction = new Transaction(
          Transaction.TYPE_DEPOSIT_SAVINGS,
          amount,
          initialBalance,
          finalBalance);

      addTransaction(transaction);

      LOGGER.debug("Nạp tiền thành công: tài khoản {}, số tiền {}",
          getAccountNumber(), amount);

    } catch (BankException e) {
      LOGGER.error("Lỗi khi nạp tiền", e);
    }
  }

  /**
   * Rút tiền từ tài khoản tiết kiệm.
   *
   * @param amount số tiền rút
   */
  @Override
  public void withdraw(double amount) {
    LOGGER.debug("Bắt đầu rút tiền: tài khoản {}, số tiền {}",
        getAccountNumber(), amount);

    double initialBalance = getBalance();

    try {
      if (amount > MAX_WITHDRAW) {
        throw new InvalidFundingAmountException(amount);
      }

      if (initialBalance - amount < MIN_BALANCE) {
        throw new InsufficientFundsException(amount);
      }

      doWithdrawing(amount);
      double finalBalance = getBalance();

      Transaction transaction = new Transaction(
          Transaction.TYPE_WITHDRAW_SAVINGS,
          amount,
          initialBalance,
          finalBalance);

      addTransaction(transaction);

      LOGGER.debug("Rút tiền thành công: tài khoản {}, số tiền {}",
          getAccountNumber(), amount);

    } catch (BankException e) {
      LOGGER.error("Lỗi khi rút tiền", e);
    }
  }
}