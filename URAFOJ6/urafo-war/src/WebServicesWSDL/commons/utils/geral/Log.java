package WebServicesWSDL.commons.utils.geral;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Properties;

import weblogic.logging.NonCatalogLogger;

/**
 * Simples log no output padrão.
 * Para ativar altere o arquivo log.properties
 */
public class Log {

    private Properties properties = new Properties();

    private transient NonCatalogLogger log = new NonCatalogLogger(Log.class.getName());

    private boolean enablePropertie = false;

    public Log(){
         getClass().getResourceAsStream("log.properties");
         try{
            properties.load(getClass().getResourceAsStream("log.properties"));
         }catch(IOException ioe){
            // falha ao carregar log.
            System.out.println("Erro ao carregar arquivo de log.");
         }
        String enable = properties.getProperty("log.enable");
        System.out.println("[Log.Log]Log Enable: " + enable);
        Boolean b = new Boolean(enable);
        this.enablePropertie = b.booleanValue();
    }

    public Log(String nome){
        this();
        log = new NonCatalogLogger(nome);
    }

    /**
     *  carregar arquivo de properties para analisar se log está ativo.
     *  isEnable = true;
     */
    private boolean isEnable(){
        return this.enablePropertie;
    }

    @SuppressWarnings("unused")
	private String getTime(){
        Calendar c = new GregorianCalendar();
        StringBuffer data = new StringBuffer();

        String day = String.valueOf(c.get(Calendar.DATE));
        if (day.length() == 1) {
            day = "0" + day;
        }

        String month = String.valueOf(c.get(Calendar.MONTH) + 1);
        if (month.length() == 1) {
            month = "0" + month;
        }

        String year = String.valueOf(c.get(Calendar.YEAR));

        String hour = String.valueOf(c.get(Calendar.HOUR_OF_DAY));
        if(hour.length() == 1)
            hour = "0" + hour;

        String minute = String.valueOf(c.get(Calendar.MINUTE));
        if(minute.length() == 1)
            minute = "0" + minute;

        String second = String.valueOf(c.get(Calendar.SECOND));
        if(second.length() == 1)
            second = "0" + second;

        data.append(year);
        data.append(month);
        data.append(day);
        data.append(hour);
        data.append(minute);
        data.append(second);
        return  data.toString();
    }

    /**
     * Escrever o log quando log estiver ativado
     */
    public void log(String value,Exception exception){
        if(isEnable()){
            log.debug(value);
            exception.printStackTrace();
            /*
            StringBuffer buffer = new StringBuffer();
            buffer.append("[LOG] ");
            buffer.append(getTime());
            buffer.append(": ");
            buffer.append(value);
            System.err.println(buffer);
            exception.printStackTrace();
            */
        }
    }

    /**
     * Escrever o log quando log estiver ativado
     */
    public void log(String value){
        if(isEnable()){

            log.debug(value);
            /*
            StringBuffer buffer = new StringBuffer();
            buffer.append("[LOG] ");
            buffer.append(getTime());
            buffer.append(": ");
            buffer.append(value);
            System.err.println(buffer);
            */
        }
    }
    
    /**
     * Escrever o log quando log estiver ativado
     */
    public void logInfo(String value){
       log.info(value);
    }
    
    /**
     * Escrever o log quando log estiver ativado
     */
    public void logWarn(String value,Exception exception){
        if(isEnable()){
            log.warning(value, exception);
            exception.printStackTrace();
        }
    }


}
