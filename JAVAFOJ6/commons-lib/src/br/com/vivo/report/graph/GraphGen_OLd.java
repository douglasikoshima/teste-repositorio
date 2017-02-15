package br.com.vivo.report.graph; 

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import org.jfree.chart.JFreeChart;

import br.com.vivo.report.bean.BarChartBean;

public class GraphGen_OLd extends TagSupport
{ 
	private static final long serialVersionUID = 6163680478013715972L;
	private BarChartBean struct; 
	JFreeChart chart;  
    
  public void setStructure(BarChartBean bcs){
    this.struct=bcs;
  }  
    
   public BarChartBean getStructure(){
     return this.struct;
   } 

    public int doStartTag() throws JspException {

     createChart(this.struct);
      
      return SKIP_BODY;
    }
    
    
   public void createChart(BarChartBean bcb)
 {
   setStructure(bcb); 
   this.pageContext.getSession().setAttribute(this.getStructure().getName(),this.getStructure());
 
 }
 



} 
