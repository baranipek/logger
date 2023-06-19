package com.logger.impl;

import com.logger.LogTarget;
import com.logger.enumeration.LogLevel;

public class EmailLogTarget implements LogTarget {
  @Override
  public void writeLog(LogLevel logLevel, String message) {
    System.out.println("[Email] " + logLevel + ": " + message);
  }
}