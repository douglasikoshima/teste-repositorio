package br.com.vivo.fo.commons.utils;

import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;

public final class BrowserDetect implements Serializable {

	private static final long serialVersionUID = -3392821178551285909L;
	private HttpServletRequest request = null;
	private String useragent = null;
	private boolean netEnabled = false;
	private boolean ie = false;
	private boolean ns6 = false;
	private boolean ns7 = false;
	private boolean op = false;
	private boolean moz = false;
	private boolean ns4 = false;
	private int version = 0;

	public void setRequest(HttpServletRequest req) {
		request = req;
		useragent = request.getHeader("User-Agent");
		String user = useragent.toLowerCase();
		String[] arrayData = user.substring(user.indexOf("(") + 1, user.length() - 1).split(";");
		version = Integer.parseInt(arrayData[1].trim().substring(0, arrayData[1].trim().length() - 1)
				.replaceAll("[^0-9]", ""));
		if (user.indexOf("msie") != -1) {
			ie = true;
		} else if (user.indexOf("netscape6") != -1) {
			ns6 = true;
		} else if (user.indexOf("netscape/7") != -1) {
			ns7 = true;
		} else if (user.indexOf("opera") != -1) {
			op = true;
		} else if (user.indexOf("gecko/2004") != -1) {
			moz = true;
		} else if (user.indexOf("mozilla/4.7") != -1) {
			ns4 = true;
		}

		if (user.indexOf(".net clr") != -1)
			netEnabled = true;
	}

	public String getUseragent() {
		return useragent;
	}

	public boolean isNetEnabled() {
		return netEnabled;
	}

	public boolean isIE() {
		return ie;
	}

	public boolean isNS7() {
		return ns7;
	}

	public boolean isNS6() {
		return ns6;
	}

	public boolean isOP() {
		return op;
	}

	public boolean isMOZ() {
		return moz;
	}

	public boolean isNS4() {
		return ns4;
	}

	public int getVersion() {
		return version;
	}

}