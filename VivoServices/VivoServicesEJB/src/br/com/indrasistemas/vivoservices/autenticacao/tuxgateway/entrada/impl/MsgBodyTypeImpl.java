//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.6-01/24/2006 06:08 PM(kohsuke)-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.09.02 at 04:06:14 PM BRT 
//


package br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.entrada.impl;

public class MsgBodyTypeImpl implements br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.entrada.MsgBodyType, com.sun.xml.bind.JAXBObject, br.com.indrasistemas.jaxb.impl.runtime.UnmarshallableObject, br.com.indrasistemas.jaxb.impl.runtime.XMLSerializable, br.com.indrasistemas.jaxb.impl.runtime.ValidatableObject
{

    protected br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.entrada.ValidaSenhaVOType _ValidaSenhaVO;
    public final static java.lang.Class version = (br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.entrada.impl.JAXBVersion.class);
    private static com.sun.msv.grammar.Grammar schemaFragment;

    private final static java.lang.Class PRIMARY_INTERFACE_CLASS() {
        return (br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.entrada.MsgBodyType.class);
    }

    public br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.entrada.ValidaSenhaVOType getValidaSenhaVO() {
        return _ValidaSenhaVO;
    }

    public void setValidaSenhaVO(br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.entrada.ValidaSenhaVOType value) {
        _ValidaSenhaVO = value;
    }

    public br.com.indrasistemas.jaxb.impl.runtime.UnmarshallingEventHandler createUnmarshaller(br.com.indrasistemas.jaxb.impl.runtime.UnmarshallingContext context) {
        return new br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.entrada.impl.MsgBodyTypeImpl.Unmarshaller(context);
    }

    public void serializeBody(br.com.indrasistemas.jaxb.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        context.startElement("", "ValidaSenhaVO");
        context.childAsURIs(((com.sun.xml.bind.JAXBObject) _ValidaSenhaVO), "ValidaSenhaVO");
        context.endNamespaceDecls();
        context.childAsAttributes(((com.sun.xml.bind.JAXBObject) _ValidaSenhaVO), "ValidaSenhaVO");
        context.endAttributes();
        context.childAsBody(((com.sun.xml.bind.JAXBObject) _ValidaSenhaVO), "ValidaSenhaVO");
        context.endElement();
    }

    public void serializeAttributes(br.com.indrasistemas.jaxb.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
    }

    public void serializeURIs(br.com.indrasistemas.jaxb.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
    }

    public java.lang.Class getPrimaryInterface() {
        return (br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.entrada.MsgBodyType.class);
    }

    public com.sun.msv.verifier.DocumentDeclaration createRawValidator() {
        if (schemaFragment == null) {
            schemaFragment = com.sun.xml.bind.validator.SchemaDeserializer.deserialize((
 "\u00ac\u00ed\u0000\u0005sr\u0000\'com.sun.msv.grammar.trex.ElementPattern\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000"
+"\tnameClasst\u0000\u001fLcom/sun/msv/grammar/NameClass;xr\u0000\u001ecom.sun.msv."
+"grammar.ElementExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002Z\u0000\u001aignoreUndeclaredAttributesL\u0000"
+"\fcontentModelt\u0000 Lcom/sun/msv/grammar/Expression;xr\u0000\u001ecom.sun."
+"msv.grammar.Expression\u00f8\u0018\u0082\u00e8N5~O\u0002\u0000\u0002L\u0000\u0013epsilonReducibilityt\u0000\u0013Lj"
+"ava/lang/Boolean;L\u0000\u000bexpandedExpq\u0000~\u0000\u0003xppp\u0000sr\u0000\u001fcom.sun.msv.gra"
+"mmar.SequenceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001dcom.sun.msv.grammar.BinaryExp"
+"\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0004exp1q\u0000~\u0000\u0003L\u0000\u0004exp2q\u0000~\u0000\u0003xq\u0000~\u0000\u0004ppsq\u0000~\u0000\u0000pp\u0000sr\u0000\u001dcom."
+"sun.msv.grammar.ChoiceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\bppsr\u0000 com.sun.msv."
+"grammar.OneOrMoreExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001ccom.sun.msv.grammar.Unary"
+"Exp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\u0003expq\u0000~\u0000\u0003xq\u0000~\u0000\u0004sr\u0000\u0011java.lang.Boolean\u00cd r\u0080\u00d5\u009c\u00fa\u00ee"
+"\u0002\u0000\u0001Z\u0000\u0005valuexp\u0000psr\u0000 com.sun.msv.grammar.AttributeExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002"
+"\u0000\u0002L\u0000\u0003expq\u0000~\u0000\u0003L\u0000\tnameClassq\u0000~\u0000\u0001xq\u0000~\u0000\u0004q\u0000~\u0000\u0011psr\u00002com.sun.msv.gr"
+"ammar.Expression$AnyStringExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0004sq\u0000~\u0000\u0010\u0001"
+"psr\u0000 com.sun.msv.grammar.AnyNameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001dcom.sun."
+"msv.grammar.NameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xpsr\u00000com.sun.msv.grammar.Ex"
+"pression$EpsilonExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0004q\u0000~\u0000\u0016psr\u0000#com.sun"
+".msv.grammar.SimpleNameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\tlocalNamet\u0000\u0012Ljava/"
+"lang/String;L\u0000\fnamespaceURIq\u0000~\u0000\u001dxq\u0000~\u0000\u0018t\u0000Sbr.com.indrasistema"
+"s.vivoservices.autenticacao.tuxgateway.entrada.ValidaSenhaVO"
+"Typet\u0000+http://java.sun.com/jaxb/xjc/dummy-elementssq\u0000~\u0000\u000bppsq"
+"\u0000~\u0000\u0012q\u0000~\u0000\u0011psr\u0000\u001bcom.sun.msv.grammar.DataExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0003L\u0000\u0002dtt\u0000\u001f"
+"Lorg/relaxng/datatype/Datatype;L\u0000\u0006exceptq\u0000~\u0000\u0003L\u0000\u0004namet\u0000\u001dLcom/"
+"sun/msv/util/StringPair;xq\u0000~\u0000\u0004ppsr\u0000\"com.sun.msv.datatype.xsd"
+".QnameType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000*com.sun.msv.datatype.xsd.BuiltinAto"
+"micType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000%com.sun.msv.datatype.xsd.ConcreteType\u0000"
+"\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\'com.sun.msv.datatype.xsd.XSDatatypeImpl\u0000\u0000\u0000\u0000\u0000\u0000\u0000"
+"\u0001\u0002\u0000\u0003L\u0000\fnamespaceUriq\u0000~\u0000\u001dL\u0000\btypeNameq\u0000~\u0000\u001dL\u0000\nwhiteSpacet\u0000.Lcom"
+"/sun/msv/datatype/xsd/WhiteSpaceProcessor;xpt\u0000 http://www.w3"
+".org/2001/XMLSchemat\u0000\u0005QNamesr\u00005com.sun.msv.datatype.xsd.Whit"
+"eSpaceProcessor$Collapse\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000,com.sun.msv.datatype."
+"xsd.WhiteSpaceProcessor\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xpsr\u00000com.sun.msv.grammar."
+"Expression$NullSetExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0004ppsr\u0000\u001bcom.sun.m"
+"sv.util.StringPair\u00d0t\u001ejB\u008f\u008d\u00a0\u0002\u0000\u0002L\u0000\tlocalNameq\u0000~\u0000\u001dL\u0000\fnamespaceUR"
+"Iq\u0000~\u0000\u001dxpq\u0000~\u0000.q\u0000~\u0000-sq\u0000~\u0000\u001ct\u0000\u0004typet\u0000)http://www.w3.org/2001/XML"
+"Schema-instanceq\u0000~\u0000\u001bsq\u0000~\u0000\u001ct\u0000\rValidaSenhaVOt\u0000\u0000sr\u0000\"com.sun.msv"
+".grammar.ExpressionPool\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\bexpTablet\u0000/Lcom/sun/msv"
+"/grammar/ExpressionPool$ClosedHash;xpsr\u0000-com.sun.msv.grammar"
+".ExpressionPool$ClosedHash\u00d7j\u00d0N\u00ef\u00e8\u00ed\u001c\u0003\u0000\u0003I\u0000\u0005countB\u0000\rstreamVersio"
+"nL\u0000\u0006parentt\u0000$Lcom/sun/msv/grammar/ExpressionPool;xp\u0000\u0000\u0000\u0004\u0001pq\u0000~"
+"\u0000\u000fq\u0000~\u0000\fq\u0000~\u0000\tq\u0000~\u0000!x"));
        }
        return new com.sun.msv.verifier.regexp.REDocumentDeclaration(schemaFragment);
    }

    public class Unmarshaller
        extends br.com.indrasistemas.jaxb.impl.runtime.AbstractUnmarshallingEventHandlerImpl
    {


        public Unmarshaller(br.com.indrasistemas.jaxb.impl.runtime.UnmarshallingContext context) {
            super(context, "----");
        }

        protected Unmarshaller(br.com.indrasistemas.jaxb.impl.runtime.UnmarshallingContext context, int startState) {
            this(context);
            state = startState;
        }

        public java.lang.Object owner() {
            return br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.entrada.impl.MsgBodyTypeImpl.this;
        }

        public void enterElement(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname, org.xml.sax.Attributes __atts)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                switch (state) {
                    case  0 :
                        if (("ValidaSenhaVO" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, false);
                            state = 1;
                            return ;
                        }
                        break;
                    case  1 :
                        if (("idCanal" == ___local)&&("" == ___uri)) {
                            _ValidaSenhaVO = ((br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.entrada.impl.ValidaSenhaVOTypeImpl) spawnChildFromEnterElement((br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.entrada.impl.ValidaSenhaVOTypeImpl.class), 2, ___uri, ___local, ___qname, __atts));
                            return ;
                        }
                        if (("idTerminal" == ___local)&&("" == ___uri)) {
                            _ValidaSenhaVO = ((br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.entrada.impl.ValidaSenhaVOTypeImpl) spawnChildFromEnterElement((br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.entrada.impl.ValidaSenhaVOTypeImpl.class), 2, ___uri, ___local, ___qname, __atts));
                            return ;
                        }
                        if (("telefone" == ___local)&&("" == ___uri)) {
                            _ValidaSenhaVO = ((br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.entrada.impl.ValidaSenhaVOTypeImpl) spawnChildFromEnterElement((br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.entrada.impl.ValidaSenhaVOTypeImpl.class), 2, ___uri, ___local, ___qname, __atts));
                            return ;
                        }
                        if (("cdSenha" == ___local)&&("" == ___uri)) {
                            _ValidaSenhaVO = ((br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.entrada.impl.ValidaSenhaVOTypeImpl) spawnChildFromEnterElement((br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.entrada.impl.ValidaSenhaVOTypeImpl.class), 2, ___uri, ___local, ___qname, __atts));
                            return ;
                        }
                        if (("cdDDD" == ___local)&&("" == ___uri)) {
                            _ValidaSenhaVO = ((br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.entrada.impl.ValidaSenhaVOTypeImpl) spawnChildFromEnterElement((br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.entrada.impl.ValidaSenhaVOTypeImpl.class), 2, ___uri, ___local, ___qname, __atts));
                            return ;
                        }
                        if (("cdNumTelefone" == ___local)&&("" == ___uri)) {
                            _ValidaSenhaVO = ((br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.entrada.impl.ValidaSenhaVOTypeImpl) spawnChildFromEnterElement((br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.entrada.impl.ValidaSenhaVOTypeImpl.class), 2, ___uri, ___local, ___qname, __atts));
                            return ;
                        }
                        if (("validarSenhaService" == ___local)&&("" == ___uri)) {
                            _ValidaSenhaVO = ((br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.entrada.impl.ValidaSenhaVOTypeImpl) spawnChildFromEnterElement((br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.entrada.impl.ValidaSenhaVOTypeImpl.class), 2, ___uri, ___local, ___qname, __atts));
                            return ;
                        }
                        _ValidaSenhaVO = ((br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.entrada.impl.ValidaSenhaVOTypeImpl) spawnChildFromEnterElement((br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.entrada.impl.ValidaSenhaVOTypeImpl.class), 2, ___uri, ___local, ___qname, __atts));
                        return ;
                    case  3 :
                        revertToParentFromEnterElement(___uri, ___local, ___qname, __atts);
                        return ;
                }
                super.enterElement(___uri, ___local, ___qname, __atts);
                break;
            }
        }

        public void leaveElement(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                switch (state) {
                    case  1 :
                        _ValidaSenhaVO = ((br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.entrada.impl.ValidaSenhaVOTypeImpl) spawnChildFromLeaveElement((br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.entrada.impl.ValidaSenhaVOTypeImpl.class), 2, ___uri, ___local, ___qname));
                        return ;
                    case  3 :
                        revertToParentFromLeaveElement(___uri, ___local, ___qname);
                        return ;
                    case  2 :
                        if (("ValidaSenhaVO" == ___local)&&("" == ___uri)) {
                            context.popAttributes();
                            state = 3;
                            return ;
                        }
                        break;
                }
                super.leaveElement(___uri, ___local, ___qname);
                break;
            }
        }

        public void enterAttribute(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                switch (state) {
                    case  1 :
                        _ValidaSenhaVO = ((br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.entrada.impl.ValidaSenhaVOTypeImpl) spawnChildFromEnterAttribute((br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.entrada.impl.ValidaSenhaVOTypeImpl.class), 2, ___uri, ___local, ___qname));
                        return ;
                    case  3 :
                        revertToParentFromEnterAttribute(___uri, ___local, ___qname);
                        return ;
                }
                super.enterAttribute(___uri, ___local, ___qname);
                break;
            }
        }

        public void leaveAttribute(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                switch (state) {
                    case  1 :
                        _ValidaSenhaVO = ((br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.entrada.impl.ValidaSenhaVOTypeImpl) spawnChildFromLeaveAttribute((br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.entrada.impl.ValidaSenhaVOTypeImpl.class), 2, ___uri, ___local, ___qname));
                        return ;
                    case  3 :
                        revertToParentFromLeaveAttribute(___uri, ___local, ___qname);
                        return ;
                }
                super.leaveAttribute(___uri, ___local, ___qname);
                break;
            }
        }

        public void handleText(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                try {
                    switch (state) {
                        case  1 :
                            _ValidaSenhaVO = ((br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.entrada.impl.ValidaSenhaVOTypeImpl) spawnChildFromText((br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.entrada.impl.ValidaSenhaVOTypeImpl.class), 2, value));
                            return ;
                        case  3 :
                            revertToParentFromText(value);
                            return ;
                    }
                } catch (java.lang.RuntimeException e) {
                    handleUnexpectedTextException(value, e);
                }
                break;
            }
        }

    }

}
