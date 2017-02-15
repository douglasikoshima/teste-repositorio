/*
 * Arquivo gerado automaticamente - não edite!!
 */
package br.com.indrasistemas.vivoservices.listapup.facade.interfaces;

/**
 * Classe utilitária para ListaPUPFacade.
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
public class ListaPUPFacadeUtil
{
   /** Interface Home guardada. Usa carregamento adiado para obter seu valor (carregado pelos métodos getHome()). */
   private static br.com.indrasistemas.vivoservices.listapup.facade.interfaces.ListaPUPFacadeHome cachedRemoteHome = null;
   /** Interface Home Local guardada. Usa carregamento adiado para obter seu valor (carregado pelos métodos getLocalHome()). */
   private static br.com.indrasistemas.vivoservices.listapup.facade.interfaces.ListaPUPFacadeLocalHome cachedLocalHome = null;

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

   // Método de busca da interface Home.

   /**
    * Obtém a interface remota a partir do contexto inicial padrão
    * @return Interface Home para ListaPUPFacade. Busca usando COMP_NAME
    */
   public static br.com.indrasistemas.vivoservices.listapup.facade.interfaces.ListaPUPFacadeHome getHome() throws javax.naming.NamingException
   {
      if (cachedRemoteHome == null) {
            cachedRemoteHome = (br.com.indrasistemas.vivoservices.listapup.facade.interfaces.ListaPUPFacadeHome) lookupHome(null, br.com.indrasistemas.vivoservices.listapup.facade.interfaces.ListaPUPFacadeHome.COMP_NAME, br.com.indrasistemas.vivoservices.listapup.facade.interfaces.ListaPUPFacadeHome.class);
      }
      return cachedRemoteHome;
   }

   /**
    * Obtém a interface remota a partir do contexto inicial parametrizado.
    * @param environment Parâmetros a serem usados para criar o contexto inicial.
    * @return Interface Home para ListaPUPFacade. Busca usando COMP_NAME
    */
   public static br.com.indrasistemas.vivoservices.listapup.facade.interfaces.ListaPUPFacadeHome getHome( java.util.Hashtable environment ) throws javax.naming.NamingException
   {
       return (br.com.indrasistemas.vivoservices.listapup.facade.interfaces.ListaPUPFacadeHome) lookupHome(environment, br.com.indrasistemas.vivoservices.listapup.facade.interfaces.ListaPUPFacadeHome.COMP_NAME, br.com.indrasistemas.vivoservices.listapup.facade.interfaces.ListaPUPFacadeHome.class);
   }

   /**
    * Obtém a interface home local a partir do contexto inicial padrão
    * @return Interface Home Local para ListaPUPFacade. Busca usando COMP_NAME
    */
   public static br.com.indrasistemas.vivoservices.listapup.facade.interfaces.ListaPUPFacadeLocalHome getLocalHome() throws javax.naming.NamingException
   {
      if (cachedLocalHome == null) {
            cachedLocalHome = (br.com.indrasistemas.vivoservices.listapup.facade.interfaces.ListaPUPFacadeLocalHome) lookupHome(null, br.com.indrasistemas.vivoservices.listapup.facade.interfaces.ListaPUPFacadeLocalHome.COMP_NAME, br.com.indrasistemas.vivoservices.listapup.facade.interfaces.ListaPUPFacadeLocalHome.class);
      }
      return cachedLocalHome;
   }

   /** Guardada por IP do servidor da JVM. */
   private static String hexServerIP = null;

   // inicializa a instância de SecureRandom.
   private static final java.security.SecureRandom seeder = new java.security.SecureRandom();

   /**
    * Um gerador de GUID (identificador único) de 32 bytes. Estas chaves artificiais <strong>NÃO</strong> DEVEM ser vistas pelo usuário,
    * nem modificadas pelo DBA exceto em raras exceções, apenas manipulada pelo banco do dados e pelos programas.
    *
    * Uso: adicione um campo id (tipo java.lang.String) no ser EJB, e adicione setId(XXXUtil.generateGUID(this)); no método ejbCreate.
    */
   public static final String generateGUID(Object o) {
       StringBuffer tmpBuffer = new StringBuffer(16);
       if (hexServerIP == null) {
           java.net.InetAddress localInetAddress = null;
           try {
               // obtém o endereço de internet

               localInetAddress = java.net.InetAddress.getLocalHost();
           }
           catch (java.net.UnknownHostException uhe) {
               System.err.println("ListaPUPFacadeUtil: Não foi possíbel obter o endereço IP local usando InetAddress.getLocalHost()!");
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

