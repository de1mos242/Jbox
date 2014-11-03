package net.de1mos.jbox.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogWrapper {
	private Logger logger;

	public LogWrapper(Logger logger) {
		this.logger = logger;
	}

	public static <T> LogWrapper get(Class<T> aClass) {
		return new LogWrapper(LoggerFactory.getLogger(aClass));
	}

	public void debug(String msg) {
		logger.debug(msg);
	}

	public void info(String msg) {
		logger.info(msg);
	}

	public void warn(String msg) {
		logger.warn(msg);
	}

	public void warn(String msg, Throwable t) {
		logger.warn(msg, t);
	}

	public void error(Exception e) {
		logger.error(e.getLocalizedMessage());
	}

	public void error(String msg) {
		logger.error(msg);
	}

	public void error(String msg, Throwable t) {
		logger.error(msg, t);
	}

	public boolean isDebug() {
		return logger.isDebugEnabled();
	}

	public void error(Throwable ex) {
		logger.error(ex.getLocalizedMessage());
	}
}
