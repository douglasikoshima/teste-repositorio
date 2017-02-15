/*
 * Arquivo gerado automaticamente - n�o edite!!
 */
package br.com.indrasistemas.vivoservices.vole.contato.facade.interfaces;

/**
 * Classe utilit�ria para PesquisaPontoContatoFacade.
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
public class PesquisaPontoContatoFacadeUtil
{
   /** Interface Home guardada. Usa carregamento adiado para obter seu valor (carregado pelos m�todos getHome()). */
   private static br.com.indrasistemas.vivoservices.vole.contato.facade.interfaces.PesquisaPontoContatoFacadeHome cachedRemoteHome = null;
   /** Interface Home Local guardada. Usa carregamento adiado para obter seu valor (carregado pelos m�todos getLocalHome()). */
   private static br.com.indrasistemas.vivoservices.vole.contato.facade.interfaces.PesquisaPontoContatoFacadeLocalHome cachedLocalHome = null;

   private static Object lookupHome(java.util.Hashtable environment, String jndiName, Class narrowTo) throws javax.naming.NamingException {
      // Obtain initial context
      javax.naming.InitialContext initialContext = new javax.naming.InitialContext(environment);
      try {
         Object objRef = initialContext.lookup(jndiName);
         // only narrow if necessary
         if (java.rmi.Remote.class.isAssignableFrom(narrowTo))
            return javax.rmi.PortableRemoteObject.narrow(objRef, narrowTo);
         else
            return objRef;
      } finally {
         initialContext.close();
      }
   }

   // M�todo de busca da interface Home.

   /**
    * Obt�m a interface remota a partir do contexto inicial padr�o
    * @return Interface Home para PesquisaPontoContatoFacade. Busca usando COMP_NAME
    */
   public static br.com.indrasistemas.vivoservices.vole.contato.facade.interfaces.PesquisaPontoContatoFacadeHome getHome() throws javax.naming.NamingException
   {
      if (cachedRemoteHome == null) {
            cachedRemoteHome = (br.com.indrasistemas.vivoservices.vole.contato.facade.interfaces.PesquisaPontoContatoFacadeHome) lookupHome(null, br.com.indrasistemas.vivoservices.vole.contato.facade.interfaces.PesquisaPontoContatoFacadeHome.COMP_NAME, br.com.indrasistemas.vivoservices.vole.contato.facade.interfaces.PesquisaPontoContatoFacadeHome.class);
      }
      return cachedRemoteHome;
   }

   /**
    * Obt�m a interface remota a partir do contexto inicial parametrizado.
    * @param environment Par�metros a serem usados para criar o contexto inicial.
    * @return Interface Home para PesquisaPontoContatoFacade. Busca usando COMP_NAME
    */
   public static br.com.indrasistemas.vivoservices.vole.contato.facade.interfaces.PesquisaPontoContatoFacadeHome getHome( java.util.Hashtable environment ) throws javax.naming.NamingException
   {
       return (br.com.indrasistemas.vivoservices.vole.contato.facade.interfaces.PesquisaPontoContatoFacadeHome) lookupHome(environment, br.com.indrasistemas.vivoservices.vole.contato.facade.interfaces.PesquisaPontoContatoFacadeHome.COMP_NAME, br.com.indrasistemas.vivoservices.vole.contato.facade.interfaces.PesquisaPontoContatoFacadeHome.class);
   }

   /**
    * Obt�m a interface home local a partir do contexto inicial padr�o
    * @return Interface Home Local para PesquisaPontoContatoFacade. Busca usando COMP_NAME
    */
   public static br.com.indrasistemas.vivoservices.vole.contato.facade.interfaces.PesquisaPontoContatoFacadeLocalHome getLocalHome() throws javax.naming.NamingException
   {
      if (cachedLocalHome == null) {
            cachedLocalHome = (br.com.indrasistemas.vivoservices.vole.contato.facade.interfaces.PesquisaPontoContatoFacadeLocalHome) lookupHome(null, br.com.indrasistemas.vivoservices.vole.contato.facade.interfaces.PesquisaPontoContatoFacadeLocalHome.COMP_NAME, br.com.indrasistemas.vivoservices.vole.contato.facade.interfaces.PesquisaPontoContatoFacadeLocalHome.class);
      }
      return cachedLocalHome;
   }

   /** Guardada por IP do servidor da JVM. */
   private static String hexServerIP = null;

   // inicializa a inst�ncia de SecureRandom.
   private static final java.security.SecureRandom seeder = new java.security.SecureRandom();

   /**
    * Um gerador de GUID (identificador �nico) de 32 bytes. Estas chaves artificiais <strong>N�O</strong> DEVEM ser vistas pelo usu�rio,
    * nem modificadas pelo DBA exceto em raras exce��es, apenas manipulada pelo banco do dados e pelos programas.
    *
    * Uso: adicione um campo id (tipo java.lang.String) no ser EJB, e adicione setId(XXXUtil.generateGUID(this)); no m�todo ejbCreate.
    */
   public static final String generateGUID(Object o) {
       StringBuffer tmpBuffer = new StringBuffer(16);
       if (hexServerIP == null) {
           java.net.InetAddress localInetAddress = null;
           try {
               // obt�m o endere�o de internet

               localInetAddress = java.net.InetAddress.getLocalHost();
           }
           catch (java.net.UnknownHostException uhe) {
               System.err.println("PesquisaPontoContatoFacadeUtil: N�o foi poss�bel obter o endere�o IP local usando InetAddress.getLocalHost()!");
               // todo: find better way to get around this...
               uhe.printStackTrace();
               return null;
           }
           byte serverIP[] = localInetAddress.getAddress();
           hexServerIP = hexFormat(getInt(serverIP), 8);
       }

       String hashcode = hexFormat(System.identityHashCode(o), 8);
       tmpBuffer.append(hexServerIP);
       tmpBuffer.append(hashcode);

       long timeNow      = System.currentTimeMillis();
       int timeLow       = (int)timeNow & 0xFFFFFFFF;
       int node          = seeder.nextInt();

       StringBuffer guid = new StringBuffer(32);
       guid.append(hexFormat(timeLow, 8));
       guid.append(tmpBuffer.toString());
       guid.append(hexFormat(node, 8));
       return guid.toString();
   }

   private static int getInt(byte bytes[]) {
       int i = 0;
       int j = 24;
       for (int k = 0; j >= 0; k++) {
           int l = bytes[k] & 0xff;
           i += l << j;
           j -= 8;
       }
       return i;
   }

   private static String hexFormat(int i, int j) {
       String s = Integer.toHexString(i);
       return padHex(s, j) + s;
   }

   private static String padHex(String s, int i) {
       StringBuffer tmpBuffer = new StringBuffer();
       if (s.length() < i) {
           for (int j = 0; j < i - s.length(); j++) {
               tmpBuffer.append('0');
           }
       }
       return tmpBuffer.toString();
   }

}

