package br.com.vivo.report.bean; 

import java.io.Serializable;
import javax.servlet.http.HttpServlet;

public class BarChartBean implements Serializable
{ 

	private static final long serialVersionUID = 5790704934432145818L;

private String name; 
   
   private String dir;
   
   private String file; 
    
   private String title;
   
   private String XAxis;
   
   private String YAxis;
   
   private double[] values;
   
   private String[] indentifiers;
   
   private int l;
   private int width;
   private byte[] content;
   
   private HttpServlet mServlet = null;
   
   public HttpServlet getServlet(){
   return this.mServlet;
   }
   
   public void setServlet(HttpServlet serv){
   this.mServlet = serv;
   }
   
   public byte[] getContent(){
   return this.content;
   }
   
   public void setContent(byte[] in){
    this.content = new byte[in.length];
   this.content=in;
   }
   
   public int getLong(){
   return this.l;
   }
   
   public void setLong(int ll){
     this.l=ll;   
   }
   
   
   public int getWidth(){
   return this.width;
   }
   
   public void setWidth(int ll){
     this.width=ll;   
   }
   
   
  
    public String getName(){
    return this.name;
   }
   
   
   
   public void setName(String at){
     this.name = at;
   }
  
  
    public String getDir(){
    if ((this.dir != null) && (this.dir.trim().length() > 0)) {
        return this.dir;
    } else {
        return "./";
    }
   }
   
   
   
   public void setDir(String dir){
    this.dir = dir;
   }
  
   
    public String getFile(){
    return this.file;
   }
   
   
   
   public void setFile(String at){
     this.file = at;
   }
  
   
   public String getTitle(){
    return this.title;
   }
   
   
   
   public void setTitle(String at){
     this.title = at;
   }
   
   
   public String getXAxis(){
    return this.XAxis;
   }
   
   public void setXAxis(String at){
     this.XAxis = at;
   }
   
   
   public String getYAxis(){
    return this.YAxis;
   }
   
   public void setYAxis(String at1){
     this.YAxis = at1;
   }
   
   
   
  public String[] getIndentifiers(){
   return this.indentifiers;
  } 
      
  public void setIndentifiers(String[] ids){
   this.indentifiers=ids;
  }    
  
  public double[] getValues(){
   return this.values;
  }
  
  public void setValues(double[] val){
    this.values=val;
  }
      
} 
