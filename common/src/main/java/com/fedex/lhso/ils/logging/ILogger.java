package com.fedex.lhso.ils.logging;

public interface ILogger {

	public void warn(Class<?> clazz, String messagePattern, Object[] arguments);

	public void debug(Class<?> clazz, String messagePattern, Object[] arguments);

	public void info(Class<?> clazz, String messagePattern, Object[] arguments);

	public void error(Class<?> clazz, String messagePattern, Object[] arguments);

	public void trace(Class<?> clazz, String messagePattern, Object[] arguments);

}
