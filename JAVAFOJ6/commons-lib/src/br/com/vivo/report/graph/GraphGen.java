package br.com.vivo.report.graph;

import java.awt.Color;
import java.io.File;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.BarRenderer;
import org.jfree.data.DefaultCategoryDataset;
import br.com.vivo.report.bean.BarChartBean;
import java.io.FileOutputStream;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;
import weblogic.logging.NonCatalogLogger;

@SuppressWarnings("deprecation")
public class GraphGen extends TagSupport {

    private static final long serialVersionUID = 5654497182211267514L;
    private BarChartBean struct;
    private String createdFile;
    private static transient NonCatalogLogger log = new NonCatalogLogger(GraphGen.class.getName());

    public void setStructure(BarChartBean bcs) {
        this.struct = bcs;
    }

    public BarChartBean getStructure() {
        return this.struct;
    }

    public int doStartTag() throws JspException {
        try {

            log.warning("GraphGen - doStartTag()");
            createChart(this.struct);

            StringBuffer sb = new StringBuffer();

            log.warning("GraphGen - doStartTag() cria sb");

            sb.append(((HttpServletRequest) pageContext.getRequest()).getContextPath()).append('/');

            log.warning("GraphGen - doStartTag() sppend");
            //sb.append(this.struct.getDir()).append('/');
            sb.append(createdFile);

            log.warning("GraphGen - doStartTag() append 2");
            ///FrontOfficeWeb/.//FRG23063relatorioAcessosHoraTAV.png
            System.out.println("string--> " + sb.toString());

            JspWriter out = pageContext.getOut();

            log.warning("GraphGen - doStartTag() out - JspWriter");

            out.println("<img src=\"" + sb.toString() + "\"/>");

            log.warning("GraphGen - doStartTag() END");
        } catch (Exception e) {

            log.error("GraphGen - doStartTag() -> Exception -> " + e.getMessage(), e);
            throw new JspException(e);
        }
        //return (SKIP_BODY);
        return (TagSupport.EVAL_BODY_INCLUDE);
    }

    private void createChart(BarChartBean bcb) throws Exception {
        try {

            log.warning("GraphGen - createChart(BarChartBean bcb) STARTED");

            byte[] imgBytes;
            JFreeChart chart = null;

            DefaultCategoryDataset dcdataset = new DefaultCategoryDataset();
            for (int n = 0; n < bcb.getValues().length; n++) {
                log.warning("GraphGen - createChart() FOR i =" + n + " bcb.getValues()[n]= " + bcb.getValues()[n] + "| bcb.getIndentifiers()[n] =" + bcb.getIndentifiers()[n]);
                dcdataset.setValue(bcb.getValues()[n], "", bcb.getIndentifiers()[n]);
            }
            log.warning("GraphGen - createChart() APOS FOR");

            log.warning("GraphGen - createChart() Param 1 - title " + bcb.getTitle());
            log.warning("GraphGen - createChart() Param 2 - X " + bcb.getXAxis());
            log.warning("GraphGen - createChart() Param 3 - Y " + bcb.getYAxis());
            log.warning("GraphGen - createChart() Param 4 - dcdataset " + dcdataset);
            log.warning("GraphGen - createChart() Param 5 - PlotOrientation.VERTICAL " + PlotOrientation.VERTICAL);

            try {

                chart = ChartFactory.createBarChart3D(bcb.getTitle(), bcb.getXAxis(), bcb.getYAxis(), dcdataset, PlotOrientation.VERTICAL, false, true, true);

            } catch (Exception t) {
                log.error("GraphGen - createChart() -> Exception interno e-> " + t.getMessage(), t);
                log.warning("GraphGen - createChart() -> Exception interno e-> " + t.getMessage(), t);
                System.out.println("---> " + t.getMessage());

                t.printStackTrace();
            }

            log.warning("GraphGen - createChart() ChartFactory.createBarChart3D()");

            CategoryPlot plot = chart.getCategoryPlot();
            plot.setBackgroundPaint(Color.white);
            plot.setForegroundAlpha(0.8f);
            plot.configureRangeAxes();
            plot.configureDomainAxes();
            plot.setRangeGridlinePaint(Color.black);

            log.warning("GraphGen - createChart() passagem 1");

            NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
            rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

            // disable bar outlines...
            BarRenderer renderer = (BarRenderer) plot.getRenderer();
            renderer.setDrawBarOutline(true);//
            renderer.setBaseItemLabelsVisible(false);
            renderer.setItemLabelsVisible(true);

            log.warning("GraphGen - ChartUtilities.encodeAsPNG() ANTES");

            imgBytes = ChartUtilities.encodeAsPNG(chart.createBufferedImage(this.struct.getWidth(), this.struct.getLong()));
            bcb.setContent(imgBytes);

            log.warning("GraphGen - ChartUtilities.encodeAsPNG() APOS");

            // String realPath = pageContext.getRequest().getRealPath(this.struct.getDir());
            
            URL about = pageContext.getServletContext().getResource("/WEB-INF/about.xml");
            String realPath = about.getPath().substring(0, about.getPath().indexOf("/WEB-INF/about.xml"));

            log.warning("GraphGen - createChart() realPath --> " + realPath);

            File dir = new File(realPath);
            dir.mkdirs();

            File retf = File.createTempFile("FRG", bcb.getFile(), dir);
            //String tmp = retf.getAbsolutePath();
            createdFile = retf.getName();

            log.warning("GraphGen - createChart()createdFile -> " + createdFile);

            FileOutputStream fos = new FileOutputStream(retf);
            ChartUtilities.writeChartAsJPEG(fos, chart, struct.getWidth(), struct.getLong());

            fos.flush();
            fos.close();

            log.warning("GraphGen - createChart() END");

        } catch (Exception e) {
            log.error("GraphGen - createChart() -> Exception e-> " + e.getMessage(), e);
            log.warning("GraphGen - createChart() -> Exception e-> " + e.getMessage(), e);
            e.printStackTrace();

            throw e;
        }
    }
}
