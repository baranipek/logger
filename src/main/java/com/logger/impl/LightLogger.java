package com.logger.impl;

import com.logger.LogTarget;
import com.logger.enumeration.LogLevel;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class LightLogger {

  private volatile LogLevel minLogLevel;
  private List<LogTarget> logTargets;
  private Map<LogTarget, LogLevel> targetLogLevels;

  public LightLogger(LogLevel minLogLevel) {
    this.minLogLevel = minLogLevel;
    this.logTargets = new CopyOnWriteArrayList<>();
    this.targetLogLevels = new ConcurrentHashMap<>();
  }

  public void addLogTarget(LogTarget logTarget) {
    logTargets.add(logTarget);
  }

  public void log(LogLevel logLevel, String message) {
    if (logLevel.ordinal() >= minLogLevel.ordinal()) {
      for (LogTarget logTarget : logTargets) {
        LogLevel targetLogLevel = targetLogLevels.getOrDefault(logTarget, minLogLevel);
        if (logLevel.ordinal() >= targetLogLevel.ordinal()) {
          logTarget.writeLog(logLevel, message);
        }
      }
    }
  }

  public void setMinLogLevel(LogLevel minLogLevel) {
    this.minLogLevel = minLogLevel;
  }

  public void setLogLevelForTarget(LogTarget logTarget, LogLevel logLevel) {
    targetLogLevels.put(logTarget, logLevel);
  }

  public void setLogTargets(List<LogTarget> logTargets) {
    this.logTargets = new CopyOnWriteArrayList<>(logTargets);
  }

  public void clearLogTargets() {
    logTargets.clear();
    targetLogLevels.clear();
  }

  public void removeLogTarget(LogTarget logTarget) {
    logTargets.remove(logTarget);
    targetLogLevels.remove(logTarget);
  }

  public List<LogTarget> getLogTargets() {
    return Collections.unmodifiableList(logTargets);
  }

  public LogLevel getMinLogLevel() {
    return minLogLevel;
  }
}
