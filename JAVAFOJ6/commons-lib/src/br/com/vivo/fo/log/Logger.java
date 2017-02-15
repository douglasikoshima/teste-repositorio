package br.com.vivo.fo.log;

import java.io.Serializable;
import weblogic.logging.NonCatalogLogger;

public class Logger implements Serializable {

    private static final long serialVersionUID = -7114658447547696768L;

    private static weblogic.logging.NonCatalogLogger log = null;
    //private String jvmLog = "";

    public Logger(String logger){
        //if(logger==null){
            //log = org.apache.log4j.Logger.getRootLogger();
        log = new NonCatalogLogger("Vivonet");
        //}else{
        //    log = org.apache.log4j.Logger.getLogger(logger);
        //}
        //jvmLog = System.getProperty("weblogic.Name");
    }

    public void debug(Object message){
        //if(log.isDebugEnabled()){
            log.debug(message.toString());
        //}
    }

    public void debug(Object message, Throwable throwable){
        //if(log.isDebugEnabled()){
            log.debug(message.toString(), throwable);
        //}
    }

    public void info(Object message){
        //if(log.isInfoEnabled()){
            log.info(message.toString());
        //}
    }

    public void info(Object message, Throwable throwable){
        //if(log.isInfoEnabled()){
            log.info(message.toString(), throwable);
        //}
    }

    public void warn(Object message){
        //if(log.isEnabledFor(org.apache.log4j.Priority.WARN)){
            log.warning(message.toString());
        //}
    }

    public void warn(Object message, Throwable throwable){
        //if(log.isEnabledFor(org.apache.log4j.Priority.WARN)){
            log.warning(message.toString(), throwable);
        //}
    }

    public void warning(Object message){
        //if(log.isEnabledFor(org.apache.log4j.Priority.WARN)){
            log.warning(message.toString());
        //}
    }

    public void warning(Object message, Throwable throwable){
        //if(log.isEnabledFor(org.apache.log4j.Priority.WARN)){
            log.warning(message.toString(), throwable);
        //}
    }

    public void error(Object message){
        //if(log.isEnabledFor(org.apache.log4j.Priority.ERROR)){
            log.error(message.toString());
        //}
    }

    public void error(Object message, Throwable throwable){
        //if(log.isEnabledFor(org.apache.log4j.Priority.ERROR)){
            log.error(message.toString(), throwable);
        //}
    }

    public void fatal(Object message){
        //if(log.isEnabledFor(org.apache.log4j.Priority.FATAL)){
            //log.fatal(message);
        //}
        log.critical(message.toString());
    }

    public void fatal(Object message, Throwable throwable){
        //if(log.isEnabledFor(org.apache.log4j.Priority.FATAL)){
            //log.fatal(message.toString(), throwable);
        //}
        log.critical(message.toString(), throwable);
    }

}
