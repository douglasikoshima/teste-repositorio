//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.6-01/24/2006 06:08 PM(kohsuke)-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.09.02 at 04:06:14 PM BRT 
//


package br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.entrada;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.entrada package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
public class ObjectFactory
    extends br.com.indrasistemas.jaxb.impl.runtime.DefaultJAXBContextImpl
{

    private static java.util.HashMap defaultImplementations = new java.util.HashMap(16, 0.75F);
    private static java.util.HashMap rootTagMap = new java.util.HashMap();
    public final static br.com.indrasistemas.jaxb.impl.runtime.GrammarInfo grammarInfo = new br.com.indrasistemas.jaxb.impl.runtime.GrammarInfoImpl(rootTagMap, defaultImplementations, (br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.entrada.ObjectFactory.class));
    public final static java.lang.Class version = (br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.entrada.impl.JAXBVersion.class);

    static {
        defaultImplementations.put((br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.entrada.MsgBodyType.class), "br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.entrada.impl.MsgBodyTypeImpl");
        defaultImplementations.put((br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.entrada.MsgHdrType.class), "br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.entrada.impl.MsgHdrTypeImpl");
        defaultImplementations.put((br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.entrada.MsgType.class), "br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.entrada.impl.MsgTypeImpl");
        defaultImplementations.put((br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.entrada.Msg.class), "br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.entrada.impl.MsgImpl");
        defaultImplementations.put((br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.entrada.ValidaSenhaVOType.class), "br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.entrada.impl.ValidaSenhaVOTypeImpl");
        rootTagMap.put(new javax.xml.namespace.QName("", "msg"), (br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.entrada.Msg.class));
    }

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.entrada
     * 
     */
    public ObjectFactory() {
        super(grammarInfo);
    }

    /**
     * Create an instance of the specified Java content interface.
     * 
     * @param javaContentInterface
     *     the Class object of the javacontent interface to instantiate
     * @return
     *     a new instance
     * @throws JAXBException
     *     if an error occurs
     */
    public java.lang.Object newInstance(java.lang.Class javaContentInterface)
        throws javax.xml.bind.JAXBException
    {
        return super.newInstance(javaContentInterface);
    }

    /**
     * Get the specified property. This method can only be
     * used to get provider specific properties.
     * Attempting to get an undefined property will result
     * in a PropertyException being thrown.
     * 
     * @param name
     *     the name of the property to retrieve
     * @return
     *     the value of the requested property
     * @throws PropertyException
     *     when there is an error retrieving the given property or value
     */
    public java.lang.Object getProperty(java.lang.String name)
        throws javax.xml.bind.PropertyException
    {
        return super.getProperty(name);
    }

    /**
     * Set the specified property. This method can only be
     * used to set provider specific properties.
     * Attempting to set an undefined property will result
     * in a PropertyException being thrown.
     * 
     * @param name
     *     the name of the property to retrieve
     * @param value
     *     the value of the property to be set
     * @throws PropertyException
     *     when there is an error processing the given property or value
     */
    public void setProperty(java.lang.String name, java.lang.Object value)
        throws javax.xml.bind.PropertyException
    {
        super.setProperty(name, value);
    }

    /**
     * Create an instance of MsgBodyType
     * 
     * @throws JAXBException
     *     if an error occurs
     */
    public br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.entrada.MsgBodyType createMsgBodyType()
        throws javax.xml.bind.JAXBException
    {
        return new br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.entrada.impl.MsgBodyTypeImpl();
    }

    /**
     * Create an instance of MsgHdrType
     * 
     * @throws JAXBException
     *     if an error occurs
     */
    public br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.entrada.MsgHdrType createMsgHdrType()
        throws javax.xml.bind.JAXBException
    {
        return new br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.entrada.impl.MsgHdrTypeImpl();
    }

    /**
     * Create an instance of MsgType
     * 
     * @throws JAXBException
     *     if an error occurs
     */
    public br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.entrada.MsgType createMsgType()
        throws javax.xml.bind.JAXBException
    {
        return new br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.entrada.impl.MsgTypeImpl();
    }

    /**
     * Create an instance of Msg
     * 
     * @throws JAXBException
     *     if an error occurs
     */
    public br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.entrada.Msg createMsg()
        throws javax.xml.bind.JAXBException
    {
        return new br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.entrada.impl.MsgImpl();
    }

    /**
     * Create an instance of ValidaSenhaVOType
     * 
     * @throws JAXBException
     *     if an error occurs
     */
    public br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.entrada.ValidaSenhaVOType createValidaSenhaVOType()
        throws javax.xml.bind.JAXBException
    {
        return new br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.entrada.impl.ValidaSenhaVOTypeImpl();
    }

}
