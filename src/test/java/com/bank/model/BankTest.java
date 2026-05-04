package com.bank.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Kiểm thử lớp Bank.
 */
public class BankTest {

  @Test
  public void testReadCustomerList() {
    String data = ""
        + "Nguyen Van A 123456789\n"
        + "1001 CHECKING 1000.0\n"
        + "1002 SAVINGS 6000.0\n"
        + "Tran Van B 987654321\n"
        + "2001 CHECKING 2000.0\n";

    Bank bank = new Bank();
    bank.readCustomerList(
        new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8)));

    List<Customer> customers = bank.getCustomerList();

    assertEquals(2, customers.size());
    assertEquals("Nguyen Van A", customers.get(0).getFullName());
    assertEquals(2, customers.get(0).getAccountList().size());
    assertEquals("Tran Van B", customers.get(1).getFullName());
    assertEquals(1, customers.get(1).getAccountList().size());
  }

  @Test
  public void testReadCustomerListWithNullInput() {
    Bank bank = new Bank();

    bank.readCustomerList(null);

    assertTrue(bank.getCustomerList().isEmpty());
  }

  @Test
  public void testGetCustomersInfoByIdOrder() {
    Bank bank = new Bank();
    List<Customer> customers = new ArrayList<>();
    customers.add(new Customer(987654321L, "Tran Van B"));
    customers.add(new Customer(123456789L, "Nguyen Van A"));
    bank.setCustomerList(customers);

    String result = bank.getCustomersInfoByIdOrder();

    assertTrue(result.indexOf("Nguyen Van A") < result.indexOf("Tran Van B"));
  }

  @Test
  public void testGetCustomersInfoByNameOrder() {
    Bank bank = new Bank();
    List<Customer> customers = new ArrayList<>();
    customers.add(new Customer(2L, "Tran Van B"));
    customers.add(new Customer(1L, "Nguyen Van A"));
    bank.setCustomerList(customers);

    String result = bank.getCustomersInfoByNameOrder();

    assertTrue(result.indexOf("Nguyen Van A") < result.indexOf("Tran Van B"));
  }

  @Test
  public void testSetCustomerListWithNull() {
    Bank bank = new Bank();

    bank.setCustomerList(null);

    assertTrue(bank.getCustomerList().isEmpty());
  }
}