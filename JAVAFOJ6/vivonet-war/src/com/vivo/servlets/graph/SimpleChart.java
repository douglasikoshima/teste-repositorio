package com.vivo.servlets.graph;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;

import br.com.vivo.fo.constantes.ConstantesCRM;

public class SimpleChart extends HttpServlet {

	private static final long serialVersionUID = -5003733774722941644L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	@SuppressWarnings("rawtypes")
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Map<String, String> params = new HashMap<String, String>();

		Enumeration enumeration = request.getAttributeNames();
		String next = ConstantesCRM.SVAZIO;

		while (enumeration.hasMoreElements()) {
			next = (String) enumeration.nextElement();
			params.put(next, request.getParameter(next));
		}

		params.remove("Random");

		String className = (String) request.getParameter("class");
		if (className == null || className.trim().length() == 0) {
			throw new ServletException("class must be defined");
		} else {
			params.remove("class");
		}

		String format = (String) request.getParameter("format");
		if (format == null || format.trim().length() == 0) {
			throw new ServletException("format must be defined");
		} else {
			params.remove("format");
		}
		format = format.toLowerCase();

		String width = (String) request.getParameter("width");
		if (format == null || format.trim().length() == 0) {
			throw new ServletException("width must be defined");
		} else {
			params.remove("width");
		}

		String height = (String) request.getParameter("height");
		if (format == null || format.trim().length() == 0) {
			throw new ServletException("height must be defined");
		} else {
			params.remove("height");
		}

		Class klass = null;
		ChartProducer producer = null;
		JFreeChart chart = null;

		try {
			klass = Class.forName(className);
			producer = (ChartProducer) klass.newInstance();
			// call method from ChartProducer implementation class
			chart = producer.createChart(params);

		} catch (Exception e) {
			throw new ServletException(e);
		}

		int w = Integer.parseInt(width);
		int h = Integer.parseInt(height);

		if (format.equals("jpeg")) {
			response.setContentType("image/jpeg");
			ChartUtilities.writeChartAsJPEG(response.getOutputStream(), chart, w, h);

		} else if (format.equals("png")) {
			response.setContentType("image/png");
			ChartUtilities.writeChartAsPNG(response.getOutputStream(), chart, w, h);

		} else {
			throw new ServletException("unsupported format");
		}

	}

}
