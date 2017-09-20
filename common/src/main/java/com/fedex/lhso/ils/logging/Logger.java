package com.fedex.lhso.ils.logging;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;

public class Logger implements ILogger {
	ch.qos.logback.classic.Logger rootLogger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("application");

	private LogLevel rootLogLevel;

	public Logger() {
		rootLogger.info("Logger initialized");
	}

	@Override
	public void warn(Class<?> clazz, String messagePattern, Object[] arguments) {
		if (this.loggingEnabled(clazz, Level.WARN)) {
			doLogging(messagePattern, LogLevel.WARN, clazz, arguments);
		}
	}

	@Override
	public void debug(Class<?> clazz, String messagePattern, Object[] arguments) {
		if (this.loggingEnabled(clazz, Level.DEBUG)) {
			doLogging(messagePattern, LogLevel.DEBUG, clazz, arguments);
		}
	}

	@Override
	public void info(Class<?> clazz, String messagePattern, Object[] arguments) {
		if (this.loggingEnabled(clazz, Level.INFO)) {
			doLogging(messagePattern, LogLevel.INFO, clazz, arguments);
		}
	}

	@Override
	public void error(Class<?> clazz, String messagePattern, Object[] arguments) {
		if (this.loggingEnabled(clazz, Level.ERROR)) {
			doLogging(messagePattern, LogLevel.ERROR, clazz, arguments);
		}
	}

	@Override
	public void trace(Class<?> clazz, String messagePattern, Object[] arguments) {
		if (this.loggingEnabled(clazz, Level.TRACE)) {
			doLogging(messagePattern, LogLevel.TRACE, clazz, arguments);
		}
	}

	private void doLogging(String messagePattern, LogLevel logLevel, Class<?> clazz, Object... arguments) {
		org.slf4j.Logger logger = getLoggerObject();
		String message = createMessage("SYSTEM-PROCESS", "SYSTEM", logLevel, clazz, messagePattern);

		switch (logLevel) {
		case INFO:
			logger.info(message, arguments);
			break;
		case DEBUG:
			logger.debug(message, arguments);
			break;
		case WARN:
			logger.warn(message, arguments);
			break;
		case ERROR:
			Throwable t = null;
			if (arguments != null) {
				for (Object argument : arguments) {
					if (argument instanceof Throwable) {
						t = (Throwable) argument;
						break;
					}
				}
			}
			if (t != null) {
				logger.error(message, t);
			} else {
				logger.error(message);
			}
			break;
		default:
			break;
		}
	}

	private org.slf4j.Logger getLoggerObject() {
		return rootLogger;
	}

	private boolean loggingEnabled(Class<?> clazz, Level logLevel) {
		if (this.rootLogLevel == null) {
			this.setRootLoggerLevel();
		}
		LogLevel loggerLevel = this.rootLogLevel;

		boolean isLoggingEnabled = false;

		switch (loggerLevel) {
		case ERROR:
			isLoggingEnabled = true;
			break;
		case INFO:
			if (logLevel == Level.INFO || logLevel == Level.WARN || logLevel == Level.ERROR) {
				isLoggingEnabled = true;
			}
			break;
		case DEBUG:
			if (logLevel == Level.INFO || logLevel == Level.DEBUG || logLevel == Level.WARN
					|| logLevel == Level.ERROR) {
				isLoggingEnabled = true;
			}
			break;
		case WARN:
			if (logLevel == Level.WARN || logLevel == Level.ERROR) {
				isLoggingEnabled = true;
			}
			break;
		default:
			break;
		}
		return isLoggingEnabled;
	}

	private void setRootLoggerLevel() {
		this.rootLogLevel = LogLevel.valueOf(this.rootLogger.getLevel().toString());
	}

	private String createMessage(String sessionID, String userName, LogLevel logLevel, Class<?> clazz,
			String messagePattern) {
		StringBuilder builder = new StringBuilder(sessionID);
		builder.append(" ");
		builder.append(userName);
		builder.append(" ");
		builder.append(clazz.getCanonicalName());
		builder.append(" ");
		builder.append(logLevel.name());
		builder.append(" ");
		builder.append(messagePattern);
		return builder.toString();
	}

}
