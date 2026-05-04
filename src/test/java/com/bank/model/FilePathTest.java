package com.bank.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;

/**
 * Kiểm thử xử lý đường dẫn tệp tin trên nhiều hệ điều hành.
 */
public class FilePathTest {

  /**
   * Kiểm thử tạo đường dẫn tệp tin bằng Path API.
   */
  @Test
  public void testCrossPlatformPath() {
    Path path = Paths.get("data", "customers.txt");

    assertEquals(Paths.get("data", "customers.txt"), path);
  }
}