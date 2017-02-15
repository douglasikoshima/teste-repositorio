package br.com.vivo.report.graph; 

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;
import javax.servlet.jsp.PageContext;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.BarRenderer;
import org.jfree.data.DefaultCategoryDataset;

import br.com.vivo.report.bean.BarChartBean;

public class GraphGenUtil implements Serializable 
{ 
	private static final long serialVersionUID = -5269098364198857952L;
private BarChartBean struct; 
  JFreeChart chart;  
    
  public void setStructure(BarChartBean bcs){
    this.struct=bcs;
  }  
    
   public BarChartBean getStructure(){
     return this.struct;
   } 

   public GraphGenUtil (BarChartBean bc){
   this.struct = bc;
   }

   public  Graphics sendImage(PageContext pc){
 
 

    try{
 DefaultCategoryDataset dcdataset = new DefaultCategoryDataset(); 
                for(int n=0; n<struct.getValues().length; n++){
                        dcdataset.setValue(struct.getValues()[n], "",struct.getIndentifiers()[n]);
                }

        
  chart = 
     ChartFactory.createBarChart3D(struct.getTitle(),  struct.getXAxis(), struct.getYAxis(), dcdataset,PlotOrientation.VERTICAL,false,true,true) ;       
    
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
                
     //ChartUtilities.writeBufferedImageAsPNG(new BufferedOutputStream(renderer.g),)           
     
    //byte[] imgBytes = ChartUtilities.encodeAsPNG(chart.createBufferedImage(this.struct.getWidth(),this.struct.getLong()));           
     
     /*
     File retf = File.createTempFile("FRG",bcb.getFile());
     ret = retf.getAbsolutePath(); 
     
     System.err.println(" Procurando em :"+ret);
     bcb.setFile(ret);
              
     ChartUtilities.saveChartAsPNG(retf,chart,bcb.getLong(),bcb.getWidth());           
       */
     //ChartUtilities.saveChartAsJPEG(new File("c:\\lolo.jpeg"),chart,struct.getLong(),struct.getWidth());
       //HttpServletResponse response;
     
     //response = (HttpServletResponse)pc.getResponse();
     //ServletOutputStream out = response.getOutputStream();
     
     java.awt.image.BufferedImage image = chart.createBufferedImage(this.struct.getWidth(),this.struct.getLong());
    Graphics   g   = image.getGraphics();
/*
      OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
     ChartUtilities.writeChartAsJPEG(outputStream,chart,struct.getWidth(),struct.getLong());
     outputStream.flush();
     outputStream.close();
  */  
     //JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
     //encoder.encode(image);
     //out.flush();
     //out.close(); 
    return g;
    
               
    }catch(Exception e){e.printStackTrace(); return null;}
    
  
}



} 
