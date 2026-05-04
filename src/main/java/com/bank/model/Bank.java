package com.bank.model;

import com.bank.model.CheckingAccount;
import com.bank.model.Customer;
import com.bank.model.SavingsAccount;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Lớp quản lý danh sách khách hàng của ngân hàng.
 */
public class Bank {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(Bank.class);

  private List<Customer> customers;

  /**
   * Khởi tạo ngân hàng.
   */
  public Bank() {
    this.customers = new ArrayList<>();
  }

  /**
   * Lấy danh sách khách hàng.
   *
   * @return danh sách khách hàng
   */
  public List<Customer> getCustomerList() {
    return customers;
  }

  /**
   * Thiết lập danh sách khách hàng.
   *
   * @param customerList danh sách khách hàng
   */
  public void setCustomerList(List<Customer> customerList) {
    if (customerList == null) {
      this.customers = new ArrayList<>();
    } else {
      this.customers = customerList;
    }
  }

  /**
   * Đọc danh sách khách hàng từ InputStream.
   *
   * @param inputStream luồng dữ liệu đầu vào
   */
  public void readCustomerList(InputStream inputStream) {
    LOGGER.debug("Bắt đầu đọc dữ liệu khách hàng");

    if (inputStream == null) {
      LOGGER.warn("InputStream là null");
      return;
    }

    try (BufferedReader reader =
        new BufferedReader(new InputStreamReader(inputStream))) {

      String line;
      Customer currentCustomer = null;

      while ((line = reader.readLine()) != null) {
        line = line.trim();

        if (line.isEmpty()) {
          continue;
        }

        int lastSpace = line.lastIndexOf(' ');
        if (lastSpace <= 0) {
          continue;
        }

        String token = line.substring(lastSpace + 1).trim();

        // Nhận diện khách hàng (CMND 9 chữ số)
        if (token.matches("\\d{9}")) {
          String name = line.substring(0, lastSpace).trim();
          long id = Long.parseLong(token);

          currentCustomer = new Customer(id, name);
          customers.add(currentCustomer);

          LOGGER.debug("Thêm khách hàng: {}", name);
        } else {
          // Nhận diện tài khoản
          if (currentCustomer == null) {
            continue;
          }

          String[] parts = line.split("\\s+");
          if (parts.length < 3) {
            continue;
          }

          long accountNumber = Long.parseLong(parts[0]);
          String type = parts[1];
          double balance = Double.parseDouble(parts[2]);

          if ("CHECKING".equals(type)) {
            currentCustomer.addAccount(
                new CheckingAccount(accountNumber, balance));
          } else if ("SAVINGS".equals(type)) {
            currentCustomer.addAccount(
                new SavingsAccount(accountNumber, balance));
          } else {
            LOGGER.warn("Loại tài khoản không hợp lệ: {}", type);
          }
        }
      }

    } catch (IOException e) {
      LOGGER.error("Lỗi khi đọc dữ liệu: {}", e.getMessage());
    }
  }

  /**
   * Lấy thông tin khách hàng theo thứ tự CMND.
   *
   * @return chuỗi thông tin khách hàng
   */
  public String getCustomersInfoByIdOrder() {
    customers.sort(Comparator.comparingLong(Customer::getIdNumber));

    StringBuilder result = new StringBuilder();

    for (int i = 0; i < customers.size(); i++) {
      result.append(customers.get(i).getCustomerInfo());
      if (i < customers.size() - 1) {
        result.append("\n");
      }
    }

    return result.toString();
  }

  /**
   * Lấy thông tin khách hàng theo thứ tự tên.
   *
   * @return chuỗi thông tin khách hàng
   */
  public String getCustomersInfoByNameOrder() {
    List<Customer> sortedList = new ArrayList<>(customers);

    sortedList.sort((c1, c2) -> {
      int result = c1.getFullName().compareTo(c2.getFullName());
      if (result != 0) {
        return result;
      }
      return Long.compare(c1.getIdNumber(), c2.getIdNumber());
    });

    StringBuilder result = new StringBuilder();

    for (Customer customer : sortedList) {
      result.append(customer.getCustomerInfo()).append("\n");
    }

    return result.toString().trim();
  }

  public Path getDataFilePath() {
    return Paths.get("data", "customers.txt");
  }
}