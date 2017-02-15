package br.com.vivo.report.jpegtag;

import javax.servlet.jsp.tagext.TagData;
import javax.servlet.jsp.tagext.TagExtraInfo;
import javax.servlet.jsp.tagext.VariableInfo;

public class graphicsVar extends TagExtraInfo {

	public graphicsVar() {
	}

	public VariableInfo[] getVariableInfo(TagData tagdata) {
		VariableInfo variableinfo = new VariableInfo("graphics", "java.awt.Graphics", true, VariableInfo.NESTED);
		VariableInfo avariableinfo[] = { variableinfo };
		return avariableinfo;
	}
}
