package util;

import org.apache.log4j.Logger;

public class JTLogger {

	static final Logger logger = Logger.getLogger(JTLogger.class);
	private static JTLogger _instance;

	private JTLogger() {
	}

	public synchronized static JTLogger getInstance() {
		if (_instance == null)
			_instance = new JTLogger();

		return _instance;
	}

	public void setDebug(String message) {
		logger.debug(message);
	}

	public void setWarn(String message) {
		logger.warn(message);

	}

	public void setInfo(String message) {
		logger.info(message);
	}

	public void setError(String message) {

		logger.error(message);
	}

	public void setFatal(String message) {

		logger.fatal(message);
	}

}