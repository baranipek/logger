package com.logger.impl;

import com.logger.LogTarget;
import com.logger.enumeration.LogLevel;

public class ConsoleLogTarget implements LogTarget {
  @Override
  public void writeLog(LogLevel logLevel, String message) {
    System.out.println("[Console] " + logLevel + ": " + message);
  }
}
