package com.bank.model;

import com.bank.exception.BankException;
import com.bank.exception.InsufficientFundsException;
import com.bank.exception.InvalidFundingAmountException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Lớp trừu tượng đại diện cho một tài khoản ngân hàng.
 */
public abstract class Account {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(Account.class);

  public static final String CHECKING_TYPE = "CHECKING";
  public static final String SAVINGS_TYPE = "SAVINGS";

  private long accountNumber;
  private double balance;
  private List<Transaction> transactions;

  /**
   * Khởi tạo tài khoản với số tài khoản và số dư ban đầu.
   *
   * @param accountNumber số tài khoản
   * @param balance số dư ban đầu
   */
  public Account(long accountNumber, double balance) {
    this.accountNumber = accountNumber;
    this.balance = balance;
    this.transactions = new ArrayList<>();
  }

  /**
   * Lấy số tài khoản.
   *
   * @return số tài khoản
   */
  public long getAccountNumber() {
    return accountNumber;
  }

  /**
   * Thiết lập số tài khoản.
   *
   * @param accountNumber số tài khoản
   */
  public void setAccountNumber(long accountNumber) {
    this.accountNumber = accountNumber;
  }

  /**
   * Lấy số dư tài khoản.
   *
   * @return số dư
   */
  public double getBalance() {
    return balance;
  }

  /**
   * Thiết lập số dư tài khoản.
   *
   * @param balance số dư
   */
  protected void setBalance(double balance) {
    this.balance = balance;
  }

  /**
   * Lấy danh sách giao dịch.
   *
   * @return danh sách giao dịch
   */
  public List<Transaction> getTransactionList() {
    return transactions;
  }

  /**
   * Thiết lập danh sách giao dịch.
   *
   * @param transactionList danh sách giao dịch
   */
  public void setTransactionList(List<Transaction> transactionList) {
    if (transactionList == null) {
      this.transactions = new ArrayList<>();
    } else {
      this.transactions = transactionList;
    }
  }

  /**
   * Nạp tiền vào tài khoản.
   *
   * @param amount số tiền nạp
   */
  public abstract void deposit(double amount);

  /**
   * Rút tiền khỏi tài khoản.
   *
   * @param amount số tiền rút
   */
  public abstract void withdraw(double amount);

  /**
   * Thực hiện nạp tiền sau khi kiểm tra hợp lệ.
   *
   * @param amount số tiền
   * @throws InvalidFundingAmountException nếu số tiền không hợp lệ
   */
  protected void doDepositing(double amount)
      throws InvalidFundingAmountException {
    if (amount <= 0) {
      LOGGER.warn("Số tiền nạp không hợp lệ: {}", amount);
      throw new InvalidFundingAmountException(amount);
    }
    balance += amount;
    LOGGER.info("Nạp tiền thành công: tài khoản {}, số tiền {}",
        accountNumber, amount);
  }

  /**
   * Thực hiện rút tiền sau khi kiểm tra hợp lệ.
   *
   * @param amount số tiền
   * @throws BankException nếu lỗi xảy ra
   */
  protected void doWithdrawing(double amount) throws BankException {
    if (amount <= 0) {
      LOGGER.warn("Số tiền rút không hợp lệ: {}", amount);
      throw new InvalidFundingAmountException(amount);
    }
    if (amount > balance) {
      LOGGER.error("Không đủ số dư: tài khoản {}, số tiền rút {}",
          accountNumber, amount);
      throw new InsufficientFundsException(amount);
    }
    balance -= amount;
    LOGGER.info("Rút tiền thành công: tài khoản {}, số tiền {}",
        accountNumber, amount);
  }

  /**
   * Thêm một giao dịch vào danh sách.
   *
   * @param transaction giao dịch
   */
  public void addTransaction(Transaction transaction) {
    if (transaction != null) {
      transactions.add(transaction);
    }
  }

  /**
   * Lấy lịch sử giao dịch của tài khoản.
   *
   * @return chuỗi lịch sử giao dịch
   */
  public String getTransactionHistory() {
    StringBuilder builder = new StringBuilder();
    builder.append("Lịch sử giao dịch của tài khoản ")
        .append(accountNumber)
        .append(":\n");

    for (int i = 0; i < transactions.size(); i++) {
      builder.append(transactions.get(i).getTransactionSummary());
      if (i < transactions.size() - 1) {
        builder.append("\n");
      }
    }

    LOGGER.info("Đã lấy lịch sử giao dịch của tài khoản {}",
        accountNumber);

    return builder.toString();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Account)) {
      return false;
    }
    Account other = (Account) obj;
    return this.accountNumber == other.accountNumber;
  }

  @Override
  public int hashCode() {
    return (int) (accountNumber ^ (accountNumber >>> 32));
  }
}