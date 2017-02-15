package br.vivo.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class BatchFormatter  extends Formatter
{   
    @Override
    public String format(LogRecord record) 
    {  
        StringBuffer sb = new StringBuffer();
        
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        
        sb.append(sdf.format(now));
        sb.append(" -- ");
        sb.append(formatMessage(record));
        sb.append("\r\n");
        
        return sb.toString();
    }
    
}