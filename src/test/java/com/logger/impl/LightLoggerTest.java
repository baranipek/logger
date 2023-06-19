package com.logger.impl;

import com.logger.LogTarget;
import com.logger.enumeration.LogLevel;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class LightLoggerTest {

  private LightLogger logger;
  private LogTarget logTarget;

  @Before
  public void setUp() {
    logger = new LightLogger(LogLevel.INFO);
    logTarget = mock(LogTarget.class);
    logger.addLogTarget(logTarget);
  }

  @Test
  public void testLog_WithMinLogLevelEqualToLogLevel_ShouldWriteLogToTarget() {
    logger.log(LogLevel.INFO, "Test message");

    verify(logTarget, times(1)).writeLog(LogLevel.INFO, "Test message");
  }

  @Test
  public void testLog_WithMinLogLevelLowerThanLogLevel_ShouldNotWriteLogToTarget() {
    logger.log(LogLevel.DEBUG, "Test message");

    verify(logTarget, never()).writeLog(LogLevel.DEBUG, "Test message");
  }

  @Test
  public void testSetMinLogLevel_ShouldUpdateMinLogLevel() {
    logger.setMinLogLevel(LogLevel.WARNING);

    assertEquals(LogLevel.WARNING, logger.getMinLogLevel());
  }


  @Test
  public void testSetLogTargets_ShouldUpdateLogTargets() {
    List<LogTarget> newTargets = Collections.singletonList(mock(LogTarget.class));

    logger.setLogTargets(newTargets);

    assertEquals(newTargets, logger.getLogTargets());
  }

  @Test
  public void testClearLogTargets_ShouldRemoveAllLogTargets() {
    logger.clearLogTargets();

    assertEquals(0, logger.getLogTargets().size());
  }

  @Test
  public void testRemoveLogTarget_ShouldRemoveLogTarget() {
    LogTarget targetToRemove = mock(LogTarget.class);
    logger.addLogTarget(targetToRemove);

    logger.removeLogTarget(targetToRemove);

    assertFalse(logger.getLogTargets().contains(targetToRemove));
  }
}
