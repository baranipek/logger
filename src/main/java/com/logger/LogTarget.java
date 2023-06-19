package com.logger;

import com.logger.enumeration.LogLevel;

public interface LogTarget {
  void writeLog(LogLevel logLevel, String message);
}