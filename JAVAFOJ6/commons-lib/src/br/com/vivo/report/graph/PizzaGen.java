package br.com.vivo.report.graph; 

import java.awt.Color;
import java.io.File;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.DefaultPieDataset;
import br.com.vivo.report.bean.BarChartBean;
import java.io.FileOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.urls.StandardPieURLGenerator;

@SuppressWarnings("deprecation")
public class PizzaGen extends TagSupport { 

	private static final long serialVersionUID = 3344399574155138122L;
	private BarChartBean struct;
    private String createdFile;
    
    public void setStructure(BarChartBean bcs){
        this.struct=bcs;
    }  
    
    public BarChartBean getStructure(){
        return this.struct;
    } 
    
    public int doStartTag() throws JspException {
        try {
            createChart(this.struct);

            StringBuffer sb = new StringBuffer();
            sb.append(((HttpServletRequest)pageContext.getRequest()).getContextPath()).append('/');
            sb.append(this.struct.getDir()).append('/');
            sb.append(createdFile);

            JspWriter out = pageContext.getOut();
            out.println("<img src=\"" + sb.toString() + "\"/>" );
        } catch (Exception e) {
            throw new JspException(e);
        }
        //return (SKIP_BODY);
        return (TagSupport.EVAL_BODY_INCLUDE);
    }

    private void createChart(BarChartBean bcb) throws Exception {
    
        byte[] imgBytes;
        JFreeChart chart;  
  
        DefaultPieDataset data = new DefaultPieDataset();
        for(int n=0; n<bcb.getValues().length; n++){
            data.setValue(bcb.getIndentifiers()[n], bcb.getValues()[n]);
        }

        // create the chart...
        chart = ChartFactory.createPie3DChart(bcb.getTitle(), data, true, true, false);

        // set the background color for the chart...
        chart.setBackgroundPaint(Color.white);
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setNoDataMessage("No data available");
         
        // set drilldown capability...
        plot.setURLGenerator(new StandardPieURLGenerator("Bar3DDemo.jsp","section"));
        plot.setLabelGenerator(null);
    
        imgBytes = ChartUtilities.encodeAsPNG(chart.createBufferedImage(this.struct.getWidth(), this.struct.getLong()));           
        bcb.setContent(imgBytes);

        String realPath = pageContext.getRequest().getRealPath(this.struct.getDir());
        File dir = new File(realPath);
        dir.mkdirs();
        
        File retf = File.createTempFile("FRG", bcb.getFile(), dir);
        //String tmp = retf.getAbsolutePath();
        createdFile = retf.getName();

        FileOutputStream fos = new FileOutputStream(retf);
        ChartUtilities.writeChartAsJPEG(fos, chart, struct.getWidth(), struct.getLong());

        fos.flush();
        fos.close();
    }
} 
