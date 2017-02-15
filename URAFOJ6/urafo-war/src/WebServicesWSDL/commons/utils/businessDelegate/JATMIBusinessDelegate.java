package WebServicesWSDL.commons.utils.businessDelegate;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;

import weblogic.wtc.jatmi.TPException;
import weblogic.wtc.jatmi.TPReplyException;
import br.com.vivo.fo.atmi.JATMI;

public class JATMIBusinessDelegate implements Serializable{

	private static final long serialVersionUID = -1265315819557363438L;

	private JATMI jatmiR = null;

    public JATMIBusinessDelegate(){
        try{
    		Hashtable<String, String> ht = new Hashtable<String, String>();
    		ht.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
    		InitialContext ctx = new InitialContext();
    		jatmiR = (JATMI) ctx.lookup("java:comp/env/ejb/JATMI");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public String executeCommnad(String method,String input) throws TPException, TPReplyException,RemoteException{
        return jatmiR.executeCommnad(method,input);
    }

}
