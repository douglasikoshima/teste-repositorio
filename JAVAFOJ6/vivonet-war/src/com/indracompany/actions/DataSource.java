package com.indracompany.actions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.log4j.Category;

@SuppressWarnings("deprecation")
public abstract class DataSource implements Cloneable {

	private static Category cat = Category.getInstance(DataSource.class.getName());

    protected String url = null;

    /**
     * Key correspondente ao nome da DataSource, na HashMap enviada ao Command
     */
    protected static final String NAME_PARAM = "name";
    
    /**
     * Key correspondente ao status de disponibilidade, na HashMap enviada ao Command
     */
    protected static final String RESULT_PARAM = "softItemStatus";
    
    /**
     * Key correspondente ao tipo de DataSource, na HashMap enviada ao Command
     */
    protected final static String TYPE_PARAM = "type";
    
    /**
     * Nome do parâmetro de inicialização
     *	que deve conter o nome do arquivo XML com os dados dos DataSources.
     */
    protected static final String FILE_NAME_PARAM = "fileName";
    
    /**
     * Nome do parâmetro de inicialização
     *	que deve indica se a aplicacao fara o teste de verificacao
     * da disponibilidade do DataSource ou nao.
     */
    protected static final String TESTA_PARAM = "testar";
    
    //nome do arquivo XML
    protected java.lang.String xmlFileName;

    /* Flag que determina se a aplicação deve fazer o teste de disponibilidade
     *	do DataSource ou não
     */
    protected boolean checkSttDataSource;

    /*
     * Função que altera o flag que indica se a verificação no arquivo XML
     *	deve ser feita ou não.
     */
    public void setVerificationStt(boolean newValue) {
        this.checkSttDataSource = newValue;
    }

    /*
     * Função que retorna o flag que indica se a verificação no arquivo XML
     *	deve ser feita ou não.
     */
    public boolean getVerificationStt() {
        return this.checkSttDataSource;
    }

    /*
     * Função que altera o nome do arquivo XML com os dados sobre os DataSources
     *
     */
    public void setXMLFile(String fileName) {
        this.xmlFileName = fileName;
    }

    /*
     * Função que retorna o nome do arquivo XML com os dados sobre os DataSources
     *
     */
    public String getXMLFile() {
        return this.xmlFileName;
    }

    /**
     *  Função que retorna uma nova instância do DataSource
     */
    public DataSource getClone() {
        Object obj = null;
        try {
            super.clone();
            obj = this.clone();
        } catch (CloneNotSupportedException e) {
        }
        return (DataSource) obj;
    }

    protected InitialContext getInitialContext() throws NamingException {
        InitialContext ic = null;
        if (url == null) {
            ic = new javax.naming.InitialContext();
        } else {
            Hashtable<String, String> env = new Hashtable<String, String>();
            env.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
            env.put(Context.PROVIDER_URL, url);
            ic = new InitialContext(env);
        }
        return ic;
    }

    protected String executeURL(String url) throws IOException {
        return executeURL(url, null);
    }

    protected String executeURL(String url, String params) throws SocketTimeoutException, IOException {

        //String timeoutStr = AppConfig.getInstance().getParameter("TIME_OUT_EXECUTE_URL");
        String timeoutStr = null;

        if (timeoutStr == null) {
            timeoutStr = "45000";//Timeout Default
        }

        int timeout = Integer.parseInt(timeoutStr);

        if (params != null) {
            cat.debug("URL...:: " + url + "\nPOST...:: " + params);
        } else {
            cat.debug("URL...:: " + url);
        }

        weblogic.net.http.HttpURLConnection urlConnection = null;

        java.io.PrintStream outputStream;
        try {
            urlConnection = new weblogic.net.http.HttpURLConnection(new URL(url));
            urlConnection.setAllowUserInteraction(false);
            urlConnection.setUseCaches(false);
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);

            urlConnection.setTimeout(timeout);
            urlConnection.connect();

            outputStream = new java.io.PrintStream(urlConnection.getOutputStream());

            if (params != null) {
                outputStream.print(params);
            }

            outputStream.flush();
            outputStream.close();

            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            StringBuffer buffer = new StringBuffer();

            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }

            return buffer.toString();

        } catch (SocketTimeoutException e) {
            if (params != null) {
                cat.error("ERRO NA EXECUÇÃO DA URL: " + url + "?" + params);
            } else {
                cat.error("ERRO NA EXECUÇÃO DA URL: " + url);
            }

            throw new SocketTimeoutException("TIME OUT SUPERIOR A " + timeout + " ms NA EXECUÇÃO DA URL :" + url + "?" + params);

        } catch (IOException e) {
            cat.error("ERRO NA EXECUÇÃO DA URL: " + url + "?" + params);
            throw e;
        }
    }
}
