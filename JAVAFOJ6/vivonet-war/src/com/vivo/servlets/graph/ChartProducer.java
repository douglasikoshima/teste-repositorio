package com.vivo.servlets.graph; 

import java.util.Map;
import org.jfree.chart.JFreeChart;

public interface ChartProducer { 

    @SuppressWarnings("rawtypes")
	public JFreeChart createChart(Map params) throws Exception;
} 
