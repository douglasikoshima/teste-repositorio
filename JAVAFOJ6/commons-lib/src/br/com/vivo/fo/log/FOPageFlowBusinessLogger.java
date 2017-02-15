package br.com.vivo.fo.log;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@SuppressWarnings({"rawtypes","unchecked"})
public class FOPageFlowBusinessLogger implements Serializable {

	private static final long serialVersionUID = -7121008447547696768L;

	public FOPageFlowBusinessLogger() {
		this.mapLogger = new LinkedHashMap();
	}

	private Map mapLogger;
	private SimpleDateFormat formatter = new SimpleDateFormat("EEE dd-MMM-yyyy HH:mm:ss");

	public Map getMapLogger() {
		if (this.mapLogger == null) {
			this.mapLogger = new LinkedHashMap();
		}
		return this.mapLogger;
	}

	public void addNewLoggerItem(String key, String value) {
		getMapLogger().put(key, "[" + formatter.format(new Date()) + "]" + value);
	}

}