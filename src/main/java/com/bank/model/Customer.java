package com.bank.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Lớp đại diện cho một khách hàng của ngân hàng.
 */
public class Customer {

  private long idNumber;
  private String fullName;
  private List<Account> accountList;

  /**
   * Khởi tạo khách hàng rỗng.
   */
  public Customer() {
    this(0L, "");
  }

  /**
   * Khởi tạo khách hàng với số căn cước và họ tên.
   *
   * @param idNumber số căn cước
   * @param fullName họ tên khách hàng
   */
  public Customer(long idNumber, String fullName) {
    this.idNumber = idNumber;
    this.fullName = fullName;
    this.accountList = new ArrayList<>();
  }

  /**
   * Lấy số căn cước của khách hàng.
   *
   * @return số căn cước
   */
  public long getIdNumber() {
    return idNumber;
  }

  /**
   * Thiết lập số căn cước của khách hàng.
   *
   * @param idNumber số căn cước
   */
  public void setIdNumber(long idNumber) {
    this.idNumber = idNumber;
  }

  /**
   * Lấy họ tên khách hàng.
   *
   * @return họ tên khách hàng
   */
  public String getFullName() {
    return fullName;
  }

  /**
   * Thiết lập họ tên khách hàng.
   *
   * @param fullName họ tên khách hàng
   */
  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  /**
   * Lấy danh sách tài khoản của khách hàng.
   *
   * @return danh sách tài khoản
   */
  public List<Account> getAccountList() {
    return accountList;
  }

  /**
   * Thiết lập danh sách tài khoản của khách hàng.
   *
   * @param accountList danh sách tài khoản
   */
  public void setAccountList(List<Account> accountList) {
    if (accountList == null) {
      this.accountList = new ArrayList<>();
    } else {
      this.accountList = accountList;
    }
  }

  /**
   * Thêm tài khoản cho khách hàng.
   *
   * @param account tài khoản cần thêm
   */
  public void addAccount(Account account) {
    if (account == null) {
      return;
    }
    if (!accountList.contains(account)) {
      accountList.add(account);
    }
  }

  /**
   * Xóa tài khoản khỏi khách hàng.
   *
   * @param account tài khoản cần xóa
   */
  public void removeAccount(Account account) {
    if (account == null) {
      return;
    }
    accountList.remove(account);
  }

  /**
   * Lấy thông tin khách hàng dạng văn bản.
   *
   * @return thông tin khách hàng
   */
  public String getCustomerInfo() {
    return "Số CMND: " + idNumber + ". Họ tên: " + fullName + ".";
  }
}