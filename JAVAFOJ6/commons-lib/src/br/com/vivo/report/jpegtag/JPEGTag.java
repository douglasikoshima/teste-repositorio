package br.com.vivo.report.jpegtag; 

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.BarRenderer;
import org.jfree.data.DefaultCategoryDataset;

import br.com.vivo.report.bean.BarChartBean;

public class JPEGTag extends BodyTagSupport
{

	private static final long serialVersionUID = 7722157751211480405L;
	private BarChartBean struct; 
    
  public void setStructure(BarChartBean bcs){
    this.struct=bcs;
  }  
    
   public BarChartBean getStructure(){
     return this.struct;
   } 

    public JPEGTag()
    {
        g = null;
        image = null;
    }

    public int doAfterBody()
        throws JspException
    {
        return 0;
    }

    public int doEndTag()
        throws JspException
    {
        javax.servlet.ServletOutputStream servletoutputstream = null;
        //Object obj = null;
        try
        {
            super.pageContext.getOut().clear();
            super.pageContext.getOut().clearBuffer();
            HttpServletResponse httpservletresponse = (HttpServletResponse)super.pageContext.getResponse();
            httpservletresponse.setContentType("image/jpeg");
            servletoutputstream = httpservletresponse.getOutputStream();
        }
        catch(Exception _ex) { }
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        JPEGImageEncoder jpegimageencoder = JPEGCodec.createJPEGEncoder(bytearrayoutputstream);
        //com.sun.image.codec.jpeg.JPEGEncodeParam jpegencodeparam = JPEGCodec.getDefaultJPEGEncodeParam(image);
        try
        {
            jpegimageencoder.encode(image);
            bytearrayoutputstream.flush();
            byte abyte0[] = bytearrayoutputstream.toByteArray();
            servletoutputstream.write(abyte0, 0, abyte0.length);
            servletoutputstream.flush();
        }
        catch(Exception _ex)
        {
            throw new JspException("Could not encode image");
        }
        return 5;
    }

    public void doInitBody()
        throws JspException
    {
        image = new BufferedImage(width, height, 1);
        //image = getBarGraph(struct);
        g = image.createGraphics();
        super.pageContext.setAttribute("graphics", g, 1);
    }

    public int getHeight()
    {
        return height;
    }

    public int getWidth()
    {
        return width;
    }

    public void release()
    {
        g = null;
        image = null;
    }

    public void setHeight(int i)
    {
        height = i;
    }

    public void setWidth(int i)
    {
        width = i;
    }
    
    public BufferedImage getBarGraph(BarChartBean lbcb){
   JFreeChart chart; 
   try{
   DefaultCategoryDataset dcdataset = new DefaultCategoryDataset();  
   
                for(int n=0; n<lbcb.getValues().length; n++){
                        dcdataset.setValue(lbcb.getValues()[n], "",lbcb.getIndentifiers()[n]);
                }

        
  chart = 
    //ChartFactory.createLineXYChart("Sesiones en Adictos al Trabajo",  "Meses", "Sesiones", juegoDatos,PlotOrientation.VERTICAL,true,true,true);
     ChartFactory.createBarChart3D(lbcb.getTitle(),  lbcb.getXAxis(), lbcb.getYAxis(), dcdataset,PlotOrientation.VERTICAL,false,true,true) ;       
    
     CategoryPlot plot = chart.getCategoryPlot();
                plot.setBackgroundPaint(Color.white);
                plot.setForegroundAlpha(0.8f);
                plot.configureRangeAxes();
                plot.configureDomainAxes();
                plot.setRangeGridlinePaint(Color.black);
                
                NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
                rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

                // disable bar outlines...
                BarRenderer renderer = (BarRenderer) plot.getRenderer();
                renderer.setDrawBarOutline(true);//
                renderer.setBaseItemLabelsVisible(false);
                renderer.setItemLabelsVisible(true);
       image = chart.createBufferedImage(width,height);         
  
   }catch(Exception e){e.printStackTrace(); return null;}
    return image;
    }

    private int width;
    private int height;
    private Graphics g;
    private BufferedImage image;
} 
