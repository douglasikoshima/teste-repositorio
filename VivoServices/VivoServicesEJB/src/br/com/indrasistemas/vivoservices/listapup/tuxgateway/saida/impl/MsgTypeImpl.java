//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.6-01/24/2006 06:08 PM(kohsuke)-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2008.01.15 at 05:15:24 PM BRST 
//


package br.com.indrasistemas.vivoservices.listapup.tuxgateway.saida.impl;

public class MsgTypeImpl implements br.com.indrasistemas.vivoservices.listapup.tuxgateway.saida.MsgType, com.sun.xml.bind.JAXBObject, br.com.indrasistemas.jaxb.impl.runtime.UnmarshallableObject, br.com.indrasistemas.jaxb.impl.runtime.XMLSerializable, br.com.indrasistemas.jaxb.impl.runtime.ValidatableObject
{

    protected br.com.indrasistemas.vivoservices.listapup.tuxgateway.saida.MsgBodyType _MsgBody;
    protected br.com.indrasistemas.vivoservices.listapup.tuxgateway.saida.MsgHdrType _MsgHdr;
    public final static java.lang.Class version = (br.com.indrasistemas.vivoservices.listapup.tuxgateway.saida.impl.JAXBVersion.class);
    private static com.sun.msv.grammar.Grammar schemaFragment;

    private final static java.lang.Class PRIMARY_INTERFACE_CLASS() {
        return (br.com.indrasistemas.vivoservices.listapup.tuxgateway.saida.MsgType.class);
    }

    public br.com.indrasistemas.vivoservices.listapup.tuxgateway.saida.MsgBodyType getMsgBody() {
        return _MsgBody;
    }

    public void setMsgBody(br.com.indrasistemas.vivoservices.listapup.tuxgateway.saida.MsgBodyType value) {
        _MsgBody = value;
    }

    public br.com.indrasistemas.vivoservices.listapup.tuxgateway.saida.MsgHdrType getMsgHdr() {
        return _MsgHdr;
    }

    public void setMsgHdr(br.com.indrasistemas.vivoservices.listapup.tuxgateway.saida.MsgHdrType value) {
        _MsgHdr = value;
    }

    public br.com.indrasistemas.jaxb.impl.runtime.UnmarshallingEventHandler createUnmarshaller(br.com.indrasistemas.jaxb.impl.runtime.UnmarshallingContext context) {
        return new br.com.indrasistemas.vivoservices.listapup.tuxgateway.saida.impl.MsgTypeImpl.Unmarshaller(context);
    }

    public void serializeBody(br.com.indrasistemas.jaxb.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        context.startElement("", "msgHdr");
        context.childAsURIs(((com.sun.xml.bind.JAXBObject) _MsgHdr), "MsgHdr");
        context.endNamespaceDecls();
        context.childAsAttributes(((com.sun.xml.bind.JAXBObject) _MsgHdr), "MsgHdr");
        context.endAttributes();
        context.childAsBody(((com.sun.xml.bind.JAXBObject) _MsgHdr), "MsgHdr");
        context.endElement();
        context.startElement("", "msgBody");
        context.childAsURIs(((com.sun.xml.bind.JAXBObject) _MsgBody), "MsgBody");
        context.endNamespaceDecls();
        context.childAsAttributes(((com.sun.xml.bind.JAXBObject) _MsgBody), "MsgBody");
        context.endAttributes();
        context.childAsBody(((com.sun.xml.bind.JAXBObject) _MsgBody), "MsgBody");
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
        return (br.com.indrasistemas.vivoservices.listapup.tuxgateway.saida.MsgType.class);
    }

    public com.sun.msv.verifier.DocumentDeclaration createRawValidator() {
        if (schemaFragment == null) {
            schemaFragment = com.sun.xml.bind.validator.SchemaDeserializer.deserialize((
 "\u00ac\u00ed\u0000\u0005sr\u0000\u001fcom.sun.msv.grammar.SequenceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001dcom.su"
+"n.msv.grammar.BinaryExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0004exp1t\u0000 Lcom/sun/msv/gra"
+"mmar/Expression;L\u0000\u0004exp2q\u0000~\u0000\u0002xr\u0000\u001ecom.sun.msv.grammar.Expressi"
+"on\u00f8\u0018\u0082\u00e8N5~O\u0002\u0000\u0002L\u0000\u0013epsilonReducibilityt\u0000\u0013Ljava/lang/Boolean;L\u0000\u000b"
+"expandedExpq\u0000~\u0000\u0002xpppsr\u0000\'com.sun.msv.grammar.trex.ElementPatt"
+"ern\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\tnameClasst\u0000\u001fLcom/sun/msv/grammar/NameClass;"
+"xr\u0000\u001ecom.sun.msv.grammar.ElementExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002Z\u0000\u001aignoreUndecl"
+"aredAttributesL\u0000\fcontentModelq\u0000~\u0000\u0002xq\u0000~\u0000\u0003pp\u0000sq\u0000~\u0000\u0000ppsq\u0000~\u0000\u0006pp\u0000"
+"sr\u0000\u001dcom.sun.msv.grammar.ChoiceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0001ppsr\u0000 com."
+"sun.msv.grammar.OneOrMoreExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001ccom.sun.msv.gramm"
+"ar.UnaryExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\u0003expq\u0000~\u0000\u0002xq\u0000~\u0000\u0003sr\u0000\u0011java.lang.Boolean"
+"\u00cd r\u0080\u00d5\u009c\u00fa\u00ee\u0002\u0000\u0001Z\u0000\u0005valuexp\u0000psr\u0000 com.sun.msv.grammar.AttributeExp\u0000"
+"\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0003expq\u0000~\u0000\u0002L\u0000\tnameClassq\u0000~\u0000\u0007xq\u0000~\u0000\u0003q\u0000~\u0000\u0012psr\u00002com.su"
+"n.msv.grammar.Expression$AnyStringExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000"
+"\u0003sq\u0000~\u0000\u0011\u0001psr\u0000 com.sun.msv.grammar.AnyNameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001d"
+"com.sun.msv.grammar.NameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xpsr\u00000com.sun.msv.gr"
+"ammar.Expression$EpsilonExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003q\u0000~\u0000\u0017psr\u0000"
+"#com.sun.msv.grammar.SimpleNameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\tlocalNamet"
+"\u0000\u0012Ljava/lang/String;L\u0000\fnamespaceURIq\u0000~\u0000\u001exq\u0000~\u0000\u0019t\u0000Fbr.com.indr"
+"asistemas.vivoservices.listapup.tuxgateway.saida.MsgHdrTypet"
+"\u0000+http://java.sun.com/jaxb/xjc/dummy-elementssq\u0000~\u0000\fppsq\u0000~\u0000\u0013q"
+"\u0000~\u0000\u0012psr\u0000\u001bcom.sun.msv.grammar.DataExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0003L\u0000\u0002dtt\u0000\u001fLorg/"
+"relaxng/datatype/Datatype;L\u0000\u0006exceptq\u0000~\u0000\u0002L\u0000\u0004namet\u0000\u001dLcom/sun/m"
+"sv/util/StringPair;xq\u0000~\u0000\u0003ppsr\u0000\"com.sun.msv.datatype.xsd.Qnam"
+"eType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000*com.sun.msv.datatype.xsd.BuiltinAtomicTy"
+"pe\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000%com.sun.msv.datatype.xsd.ConcreteType\u0000\u0000\u0000\u0000\u0000\u0000"
+"\u0000\u0001\u0002\u0000\u0000xr\u0000\'com.sun.msv.datatype.xsd.XSDatatypeImpl\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0003L"
+"\u0000\fnamespaceUriq\u0000~\u0000\u001eL\u0000\btypeNameq\u0000~\u0000\u001eL\u0000\nwhiteSpacet\u0000.Lcom/sun/"
+"msv/datatype/xsd/WhiteSpaceProcessor;xpt\u0000 http://www.w3.org/"
+"2001/XMLSchemat\u0000\u0005QNamesr\u00005com.sun.msv.datatype.xsd.WhiteSpac"
+"eProcessor$Collapse\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000,com.sun.msv.datatype.xsd.W"
+"hiteSpaceProcessor\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xpsr\u00000com.sun.msv.grammar.Expre"
+"ssion$NullSetExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003ppsr\u0000\u001bcom.sun.msv.ut"
+"il.StringPair\u00d0t\u001ejB\u008f\u008d\u00a0\u0002\u0000\u0002L\u0000\tlocalNameq\u0000~\u0000\u001eL\u0000\fnamespaceURIq\u0000~\u0000"
+"\u001expq\u0000~\u0000/q\u0000~\u0000.sq\u0000~\u0000\u001dt\u0000\u0004typet\u0000)http://www.w3.org/2001/XMLSchem"
+"a-instanceq\u0000~\u0000\u001csq\u0000~\u0000\u001dt\u0000\u0006msgHdrt\u0000\u0000sq\u0000~\u0000\u0006pp\u0000sq\u0000~\u0000\u0000ppsq\u0000~\u0000\u0006pp\u0000s"
+"q\u0000~\u0000\fppsq\u0000~\u0000\u000eq\u0000~\u0000\u0012psq\u0000~\u0000\u0013q\u0000~\u0000\u0012pq\u0000~\u0000\u0016q\u0000~\u0000\u001aq\u0000~\u0000\u001csq\u0000~\u0000\u001dt\u0000Gbr.co"
+"m.indrasistemas.vivoservices.listapup.tuxgateway.saida.MsgBo"
+"dyTypeq\u0000~\u0000!sq\u0000~\u0000\fppsq\u0000~\u0000\u0013q\u0000~\u0000\u0012pq\u0000~\u0000\'q\u0000~\u00007q\u0000~\u0000\u001csq\u0000~\u0000\u001dt\u0000\u0007msgBo"
+"dyq\u0000~\u0000<sr\u0000\"com.sun.msv.grammar.ExpressionPool\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\be"
+"xpTablet\u0000/Lcom/sun/msv/grammar/ExpressionPool$ClosedHash;xps"
+"r\u0000-com.sun.msv.grammar.ExpressionPool$ClosedHash\u00d7j\u00d0N\u00ef\u00e8\u00ed\u001c\u0003\u0000\u0003I"
+"\u0000\u0005countB\u0000\rstreamVersionL\u0000\u0006parentt\u0000$Lcom/sun/msv/grammar/Expr"
+"essionPool;xp\u0000\u0000\u0000\t\u0001pq\u0000~\u0000\u0005q\u0000~\u0000\"q\u0000~\u0000Eq\u0000~\u0000\u0010q\u0000~\u0000Aq\u0000~\u0000\rq\u0000~\u0000@q\u0000~\u0000\nq"
+"\u0000~\u0000>x"));
        }
        return new com.sun.msv.verifier.regexp.REDocumentDeclaration(schemaFragment);
    }

    public class Unmarshaller
        extends br.com.indrasistemas.jaxb.impl.runtime.AbstractUnmarshallingEventHandlerImpl
    {


        public Unmarshaller(br.com.indrasistemas.jaxb.impl.runtime.UnmarshallingContext context) {
            super(context, "-------");
        }

        protected Unmarshaller(br.com.indrasistemas.jaxb.impl.runtime.UnmarshallingContext context, int startState) {
            this(context);
            state = startState;
        }

        public java.lang.Object owner() {
            return br.com.indrasistemas.vivoservices.listapup.tuxgateway.saida.impl.MsgTypeImpl.this;
        }

        public void enterElement(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname, org.xml.sax.Attributes __atts)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                switch (state) {
                    case  6 :
                        revertToParentFromEnterElement(___uri, ___local, ___qname, __atts);
                        return ;
                    case  3 :
                        if (("msgBody" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, false);
                            state = 4;
                            return ;
                        }
                        break;
                    case  4 :
                        if (("AtendimentoVO" == ___local)&&("" == ___uri)) {
                            _MsgBody = ((br.com.indrasistemas.vivoservices.listapup.tuxgateway.saida.impl.MsgBodyTypeImpl) spawnChildFromEnterElement((br.com.indrasistemas.vivoservices.listapup.tuxgateway.saida.impl.MsgBodyTypeImpl.class), 5, ___uri, ___local, ___qname, __atts));
                            return ;
                        }
                        _MsgBody = ((br.com.indrasistemas.vivoservices.listapup.tuxgateway.saida.impl.MsgBodyTypeImpl) spawnChildFromEnterElement((br.com.indrasistemas.vivoservices.listapup.tuxgateway.saida.impl.MsgBodyTypeImpl.class), 5, ___uri, ___local, ___qname, __atts));
                        return ;
                    case  1 :
                        if (("statusText" == ___local)&&("" == ___uri)) {
                            _MsgHdr = ((br.com.indrasistemas.vivoservices.listapup.tuxgateway.saida.impl.MsgHdrTypeImpl) spawnChildFromEnterElement((br.com.indrasistemas.vivoservices.listapup.tuxgateway.saida.impl.MsgHdrTypeImpl.class), 2, ___uri, ___local, ___qname, __atts));
                            return ;
                        }
                        if (("statusCode" == ___local)&&("" == ___uri)) {
                            _MsgHdr = ((br.com.indrasistemas.vivoservices.listapup.tuxgateway.saida.impl.MsgHdrTypeImpl) spawnChildFromEnterElement((br.com.indrasistemas.vivoservices.listapup.tuxgateway.saida.impl.MsgHdrTypeImpl.class), 2, ___uri, ___local, ___qname, __atts));
                            return ;
                        }
                        if (("serverElapsedTime" == ___local)&&("" == ___uri)) {
                            _MsgHdr = ((br.com.indrasistemas.vivoservices.listapup.tuxgateway.saida.impl.MsgHdrTypeImpl) spawnChildFromEnterElement((br.com.indrasistemas.vivoservices.listapup.tuxgateway.saida.impl.MsgHdrTypeImpl.class), 2, ___uri, ___local, ___qname, __atts));
                            return ;
                        }
                        if (("subSystem" == ___local)&&("" == ___uri)) {
                            _MsgHdr = ((br.com.indrasistemas.vivoservices.listapup.tuxgateway.saida.impl.MsgHdrTypeImpl) spawnChildFromEnterElement((br.com.indrasistemas.vivoservices.listapup.tuxgateway.saida.impl.MsgHdrTypeImpl.class), 2, ___uri, ___local, ___qname, __atts));
                            return ;
                        }
                        if (("service" == ___local)&&("" == ___uri)) {
                            _MsgHdr = ((br.com.indrasistemas.vivoservices.listapup.tuxgateway.saida.impl.MsgHdrTypeImpl) spawnChildFromEnterElement((br.com.indrasistemas.vivoservices.listapup.tuxgateway.saida.impl.MsgHdrTypeImpl.class), 2, ___uri, ___local, ___qname, __atts));
                            return ;
                        }
                        if (("user" == ___local)&&("" == ___uri)) {
                            _MsgHdr = ((br.com.indrasistemas.vivoservices.listapup.tuxgateway.saida.impl.MsgHdrTypeImpl) spawnChildFromEnterElement((br.com.indrasistemas.vivoservices.listapup.tuxgateway.saida.impl.MsgHdrTypeImpl.class), 2, ___uri, ___local, ___qname, __atts));
                            return ;
                        }
                        _MsgHdr = ((br.com.indrasistemas.vivoservices.listapup.tuxgateway.saida.impl.MsgHdrTypeImpl) spawnChildFromEnterElement((br.com.indrasistemas.vivoservices.listapup.tuxgateway.saida.impl.MsgHdrTypeImpl.class), 2, ___uri, ___local, ___qname, __atts));
                        return ;
                    case  0 :
                        if (("msgHdr" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, false);
                            state = 1;
                            return ;
                        }
                        break;
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
                    case  6 :
                        revertToParentFromLeaveElement(___uri, ___local, ___qname);
                        return ;
                    case  2 :
                        if (("msgHdr" == ___local)&&("" == ___uri)) {
                            context.popAttributes();
                            state = 3;
                            return ;
                        }
                        break;
                    case  4 :
                        _MsgBody = ((br.com.indrasistemas.vivoservices.listapup.tuxgateway.saida.impl.MsgBodyTypeImpl) spawnChildFromLeaveElement((br.com.indrasistemas.vivoservices.listapup.tuxgateway.saida.impl.MsgBodyTypeImpl.class), 5, ___uri, ___local, ___qname));
                        return ;
                    case  1 :
                        _MsgHdr = ((br.com.indrasistemas.vivoservices.listapup.tuxgateway.saida.impl.MsgHdrTypeImpl) spawnChildFromLeaveElement((br.com.indrasistemas.vivoservices.listapup.tuxgateway.saida.impl.MsgHdrTypeImpl.class), 2, ___uri, ___local, ___qname));
                        return ;
                    case  5 :
                        if (("msgBody" == ___local)&&("" == ___uri)) {
                            context.popAttributes();
                            state = 6;
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
                    case  6 :
                        revertToParentFromEnterAttribute(___uri, ___local, ___qname);
                        return ;
                    case  4 :
                        _MsgBody = ((br.com.indrasistemas.vivoservices.listapup.tuxgateway.saida.impl.MsgBodyTypeImpl) spawnChildFromEnterAttribute((br.com.indrasistemas.vivoservices.listapup.tuxgateway.saida.impl.MsgBodyTypeImpl.class), 5, ___uri, ___local, ___qname));
                        return ;
                    case  1 :
                        _MsgHdr = ((br.com.indrasistemas.vivoservices.listapup.tuxgateway.saida.impl.MsgHdrTypeImpl) spawnChildFromEnterAttribute((br.com.indrasistemas.vivoservices.listapup.tuxgateway.saida.impl.MsgHdrTypeImpl.class), 2, ___uri, ___local, ___qname));
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
                    case  6 :
                        revertToParentFromLeaveAttribute(___uri, ___local, ___qname);
                        return ;
                    case  4 :
                        _MsgBody = ((br.com.indrasistemas.vivoservices.listapup.tuxgateway.saida.impl.MsgBodyTypeImpl) spawnChildFromLeaveAttribute((br.com.indrasistemas.vivoservices.listapup.tuxgateway.saida.impl.MsgBodyTypeImpl.class), 5, ___uri, ___local, ___qname));
                        return ;
                    case  1 :
                        _MsgHdr = ((br.com.indrasistemas.vivoservices.listapup.tuxgateway.saida.impl.MsgHdrTypeImpl) spawnChildFromLeaveAttribute((br.com.indrasistemas.vivoservices.listapup.tuxgateway.saida.impl.MsgHdrTypeImpl.class), 2, ___uri, ___local, ___qname));
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
                        case  6 :
                            revertToParentFromText(value);
                            return ;
                        case  4 :
                            _MsgBody = ((br.com.indrasistemas.vivoservices.listapup.tuxgateway.saida.impl.MsgBodyTypeImpl) spawnChildFromText((br.com.indrasistemas.vivoservices.listapup.tuxgateway.saida.impl.MsgBodyTypeImpl.class), 5, value));
                            return ;
                        case  1 :
                            _MsgHdr = ((br.com.indrasistemas.vivoservices.listapup.tuxgateway.saida.impl.MsgHdrTypeImpl) spawnChildFromText((br.com.indrasistemas.vivoservices.listapup.tuxgateway.saida.impl.MsgHdrTypeImpl.class), 2, value));
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
