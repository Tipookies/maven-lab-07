package com.bank.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Kiểm thử lớp Customer.
 */
public class CustomerTest {

  /**
   * Kiểm thử thông tin khách hàng.
   */
  @Test
  public void testGetCustomerInfo() {
    Customer customer = new Customer(123456789L, "Nguyen Van A");

    assertEquals(
        "Số CMND: 123456789. Họ tên: Nguyen Van A.",
        customer.getCustomerInfo());
  }

  /**
   * Kiểm thử thêm tài khoản cho khách hàng.
   */
  @Test
  public void testAddAccount() {
    Customer customer = new Customer(123456789L, "Nguyen Van A");
    Account account = new CheckingAccount(1001L, 1000);

    customer.addAccount(account);

    assertEquals(1, customer.getAccountList().size());
    assertTrue(customer.getAccountList().contains(account));
  }

  /**
   * Kiểm thử xóa tài khoản khỏi khách hàng.
   */
  @Test
  public void testRemoveAccount() {
    Customer customer = new Customer(123456789L, "Nguyen Van A");
    Account account = new CheckingAccount(1001L, 1000);

    customer.addAccount(account);
    customer.removeAccount(account);

    assertEquals(0, customer.getAccountList().size());
  }
}