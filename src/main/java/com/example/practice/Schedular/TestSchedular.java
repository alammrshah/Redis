package com.example.practice.Schedular;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * TestSchedular - A comprehensive example of Spring Boot Scheduling
 *
 * <p>This class demonstrates various scheduling patterns and configurations available in Spring
 * Boot using @Scheduled annotation.
 *
 * <p>Prerequisites: 1. Enable scheduling in your main application class with @EnableScheduling 2.
 * Add @Slf4j for logging (Lombok dependency) 3. Configure thread pool for async execution if needed
 *
 * @author Your Name
 */
@Slf4j
@Component // Changed from @Service to @Component for better semantic meaning
public class TestSchedular {

  // Date formatter for consistent timestamp formatting
  private static final DateTimeFormatter FORMATTER =
      DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

  /**
   * FIXED RATE SCHEDULING
   *
   * <p>Executes at fixed intervals regardless of the previous execution's completion time. If the
   * previous execution takes longer than the interval, the next execution will start immediately
   * after the previous one completes.
   *
   * <p>fixedRate = 5000 means execute every 5 seconds initialDelay = 2000 means wait 2 seconds
   * before the first execution
   */
  // @Scheduled(fixedRate = 5000, initialDelay = 2000)
  public void fixedRateScheduler() {
    log.info(
        "[FIXED RATE] Task executed at: {} - Thread: {}",
        getCurrentTime(),
        Thread.currentThread().getName());

    // Simulate some work
    try {
      Thread.sleep(1000); // Sleep for 1 second
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      log.error("Thread interrupted during fixed rate task", e);
    }
  }

  /**
   * FIXED DELAY SCHEDULING
   *
   * <p>Executes with a fixed delay between the completion of the last execution and the start of
   * the next execution.
   *
   * <p>fixedDelay = 3000 means wait 3 seconds after the previous execution completes
   */
  // @Scheduled(fixedDelay = 3000)
  public void fixedDelayScheduler() {
    log.info(
        "[FIXED DELAY] Task started at: {} - Thread: {}",
        getCurrentTime(),
        Thread.currentThread().getName());

    // Simulate variable processing time
    try {
      Thread.sleep(2000); // Sleep for 2 seconds
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      log.error("Thread interrupted during fixed delay task", e);
    }

    log.info("[FIXED DELAY] Task completed at: {}", getCurrentTime());
  }

  /**
   * CRON EXPRESSION SCHEDULING
   *
   * <p>Uses cron expressions for more complex scheduling patterns.
   *
   * <p>Cron format: "second minute hour day month weekday" "0 0 12 * * ?" means execute at 12:00 PM
   * every day "0 2 * * * ?" means execute every 2 minutes "0 0 9 * * MON-FRI" means execute at 9:00
   * AM on weekdays
   */
  // @Scheduled(cron = "0 */1 * * * ?") // Every minute
  public void cronScheduler() {
    log.info(
        "[CRON] Cron job executed at: {} - Thread: {}",
        getCurrentTime(),
        Thread.currentThread().getName());

    // Example: Database cleanup, report generation, etc.
    performDatabaseCleanup();
  }

  /**
   * CRON EXPRESSION WITH TIMEZONE
   *
   * <p>Execute at specific times considering timezone "0 0 6 * * ?" means execute at 6:00 AM every
   * day in specified timezone
   */
  // @Scheduled(cron = "0 0 6 * * ?", zone = "Asia/Kolkata")
  public void cronWithTimezone() {
    log.info(
        "[CRON-TIMEZONE] Morning task executed at: {} (IST) - Thread: {}",
        getCurrentTime(),
        Thread.currentThread().getName());

    // Example: Send daily reports, morning notifications
    sendDailyReports();
  }

  /**
   * ASYNC SCHEDULING
   *
   * <p>Execute scheduled tasks asynchronously to avoid blocking other scheduled tasks.
   * Requires @EnableAsync in configuration and proper thread pool configuration.
   */
  @Async
  // @Scheduled(fixedRate = 10000) // Every 10 seconds
  public void asyncScheduler() {
    log.info(
        "[ASYNC] Async task started at: {} - Thread: {}",
        getCurrentTime(),
        Thread.currentThread().getName());

    // Simulate long-running task
    try {
      Thread.sleep(15000); // Sleep for 15 seconds (longer than the interval)
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      log.error("Thread interrupted during async task", e);
    }

    log.info("[ASYNC] Async task completed at: {}", getCurrentTime());
  }

  /**
   * CONDITIONAL SCHEDULING
   *
   * <p>Use property placeholders to make scheduling configurable. Define properties in
   * application.properties or application.yml
   *
   * <p>Example in application.properties: scheduler.health-check.enabled=true
   * scheduler.health-check.fixed-rate=30000
   */
  // @Scheduled(fixedRateString = "${scheduler.health-check.fixed-rate:30000}")
  public void conditionalScheduler() {
    log.info(
        "[CONDITIONAL] Health check executed at: {} - Thread: {}",
        getCurrentTime(),
        Thread.currentThread().getName());

    // Example: Check application health, system resources
    checkApplicationHealth();
  }

  /**
   * BUSINESS HOURS SCHEDULING
   *
   * <p>Execute only during business hours (9 AM to 5 PM, Monday to Friday) Cron: "0 0 9-17 * *
   * MON-FRI" (every hour from 9 AM to 5 PM on weekdays)
   */
  // @Scheduled(cron = "0 0 9-17 * * MON-FRI")
  public void businessHoursScheduler() {
    log.info(
        "[BUSINESS HOURS] Task executed during business hours at: {} - Thread: {}",
        getCurrentTime(),
        Thread.currentThread().getName());

    // Example: Process business transactions, send notifications to staff
    processBusinessTransactions();
  }

  /**
   * MONTHLY CLEANUP SCHEDULER
   *
   * <p>Execute on the first day of every month at 2 AM Cron: "0 0 2 1 * ?" (at 2:00 AM on the 1st
   * day of every month)
   */
  // @Scheduled(cron = "0 0 2 1 * ?")
  public void monthlyCleanupScheduler() {
    log.info(
        "[MONTHLY CLEANUP] Monthly cleanup task executed at: {} - Thread: {}",
        getCurrentTime(),
        Thread.currentThread().getName());

    // Example: Archive old data, generate monthly reports
    performMonthlyCleanup();
  }

  // ========== HELPER METHODS ==========

  /** Get current time formatted as string */
  private String getCurrentTime() {
    return LocalDateTime.now().format(FORMATTER);
  }

  /** Simulate database cleanup operation */
  private void performDatabaseCleanup() {
    log.debug("Performing database cleanup operations...");
    // Add your database cleanup logic here
    // Example: Delete old logs, remove expired tokens, etc.
  }

  /** Simulate sending daily reports */
  private void sendDailyReports() {
    log.debug("Sending daily reports...");
    // Add your report generation and sending logic here
    // Example: Generate PDF reports, send emails, etc.
  }

  /** Simulate application health check */
  private void checkApplicationHealth() {
    log.debug("Checking application health...");
    // Add your health check logic here
    // Example: Check database connectivity, external API availability, etc.
  }

  /** Simulate business transaction processing */
  private void processBusinessTransactions() {
    log.debug("Processing business transactions...");
    // Add your business logic here
    // Example: Process pending orders, update inventory, etc.
  }

  /** Simulate monthly cleanup operations */
  private void performMonthlyCleanup() {
    log.debug("Performing monthly cleanup...");
    // Add your monthly cleanup logic here
    // Example: Archive old data, generate monthly reports, cleanup temp files
  }

  // ========== SCHEDULER MONITORING ==========

  /** Monitor scheduler execution statistics Execute every 5 minutes to log scheduler statistics */
  // @Scheduled(cron = "0 */5 * * * ?")
  public void schedulerMonitoring() {
    log.info("[MONITORING] Scheduler monitoring executed at: {}", getCurrentTime());
    log.info("Active threads: {}", Thread.activeCount());
    log.info("Available processors: {}", Runtime.getRuntime().availableProcessors());
    log.info("Free memory: {} MB", Runtime.getRuntime().freeMemory() / (1024 * 1024));
  }
}
