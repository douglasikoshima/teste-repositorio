//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.6-01/24/2006 06:08 PM(kohsuke)-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2009.02.27 at 07:08:09 PM BRT 
//


package br.com.indrasistemas.vivoservices.atendimento.retencao.tuxgateway.entrada.getbonus;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the br.com.indrasistemas.vivoservices.atendimento.retencao.tuxgateway.entrada.getbonus package. 
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
    public final static br.com.indrasistemas.jaxb.impl.runtime.GrammarInfo grammarInfo = new br.com.indrasistemas.jaxb.impl.runtime.GrammarInfoImpl(rootTagMap, defaultImplementations, (br.com.indrasistemas.vivoservices.atendimento.retencao.tuxgateway.entrada.getbonus.ObjectFactory.class));
    public final static java.lang.Class version = (br.com.indrasistemas.vivoservices.atendimento.retencao.tuxgateway.entrada.getbonus.impl.JAXBVersion.class);

    static {
        defaultImplementations.put((br.com.indrasistemas.vivoservices.atendimento.retencao.tuxgateway.entrada.getbonus.Msg.class), "br.com.indrasistemas.vivoservices.atendimento.retencao.tuxgateway.entrada.getbonus.impl.MsgImpl");
        defaultImplementations.put((br.com.indrasistemas.vivoservices.atendimento.retencao.tuxgateway.entrada.getbonus.MsgType.class), "br.com.indrasistemas.vivoservices.atendimento.retencao.tuxgateway.entrada.getbonus.impl.MsgTypeImpl");
        defaultImplementations.put((br.com.indrasistemas.vivoservices.atendimento.retencao.tuxgateway.entrada.getbonus.BonusPesquisaVOType.class), "br.com.indrasistemas.vivoservices.atendimento.retencao.tuxgateway.entrada.getbonus.impl.BonusPesquisaVOTypeImpl");
        defaultImplementations.put((br.com.indrasistemas.vivoservices.atendimento.retencao.tuxgateway.entrada.getbonus.MsgBodyType.class), "br.com.indrasistemas.vivoservices.atendimento.retencao.tuxgateway.entrada.getbonus.impl.MsgBodyTypeImpl");
        defaultImplementations.put((br.com.indrasistemas.vivoservices.atendimento.retencao.tuxgateway.entrada.getbonus.MsgHdrType.class), "br.com.indrasistemas.vivoservices.atendimento.retencao.tuxgateway.entrada.getbonus.impl.MsgHdrTypeImpl");
        rootTagMap.put(new javax.xml.namespace.QName("", "msg"), (br.com.indrasistemas.vivoservices.atendimento.retencao.tuxgateway.entrada.getbonus.Msg.class));
    }

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: br.com.indrasistemas.vivoservices.atendimento.retencao.tuxgateway.entrada.getbonus
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
     * @param value
     *     the value of the property to be set
     * @param name
     *     the name of the property to retrieve
     * @throws PropertyException
     *     when there is an error processing the given property or value
     */
    public void setProperty(java.lang.String name, java.lang.Object value)
        throws javax.xml.bind.PropertyException
    {
        super.setProperty(name, value);
    }

    /**
     * Create an instance of Msg
     * 
     * @throws JAXBException
     *     if an error occurs
     */
    public br.com.indrasistemas.vivoservices.atendimento.retencao.tuxgateway.entrada.getbonus.Msg createMsg()
        throws javax.xml.bind.JAXBException
    {
        return new br.com.indrasistemas.vivoservices.atendimento.retencao.tuxgateway.entrada.getbonus.impl.MsgImpl();
    }

    /**
     * Create an instance of MsgType
     * 
     * @throws JAXBException
     *     if an error occurs
     */
    public br.com.indrasistemas.vivoservices.atendimento.retencao.tuxgateway.entrada.getbonus.MsgType createMsgType()
        throws javax.xml.bind.JAXBException
    {
        return new br.com.indrasistemas.vivoservices.atendimento.retencao.tuxgateway.entrada.getbonus.impl.MsgTypeImpl();
    }

    /**
     * Create an instance of BonusPesquisaVOType
     * 
     * @throws JAXBException
     *     if an error occurs
     */
    public br.com.indrasistemas.vivoservices.atendimento.retencao.tuxgateway.entrada.getbonus.BonusPesquisaVOType createBonusPesquisaVOType()
        throws javax.xml.bind.JAXBException
    {
        return new br.com.indrasistemas.vivoservices.atendimento.retencao.tuxgateway.entrada.getbonus.impl.BonusPesquisaVOTypeImpl();
    }

    /**
     * Create an instance of MsgBodyType
     * 
     * @throws JAXBException
     *     if an error occurs
     */
    public br.com.indrasistemas.vivoservices.atendimento.retencao.tuxgateway.entrada.getbonus.MsgBodyType createMsgBodyType()
        throws javax.xml.bind.JAXBException
    {
        return new br.com.indrasistemas.vivoservices.atendimento.retencao.tuxgateway.entrada.getbonus.impl.MsgBodyTypeImpl();
    }

    /**
     * Create an instance of MsgHdrType
     * 
     * @throws JAXBException
     *     if an error occurs
     */
    public br.com.indrasistemas.vivoservices.atendimento.retencao.tuxgateway.entrada.getbonus.MsgHdrType createMsgHdrType()
        throws javax.xml.bind.JAXBException
    {
        return new br.com.indrasistemas.vivoservices.atendimento.retencao.tuxgateway.entrada.getbonus.impl.MsgHdrTypeImpl();
    }

}
