//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.6-01/24/2006 06:08 PM(kohsuke)-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2009.01.23 at 10:00:22 AM BRST 
//


package br.com.indrasistemas.vivoservices.atendimento.retencao.tuxgateway.saida.finalret.impl;

public class RetornoVOTypeImpl implements br.com.indrasistemas.vivoservices.atendimento.retencao.tuxgateway.saida.finalret.RetornoVOType, com.sun.xml.bind.JAXBObject, br.com.indrasistemas.jaxb.impl.runtime.UnmarshallableObject, br.com.indrasistemas.jaxb.impl.runtime.XMLSerializable, br.com.indrasistemas.jaxb.impl.runtime.ValidatableObject
{

    protected java.lang.String _Valor;
    protected java.lang.String _Descricao;
    protected java.lang.String _MsgRetorno;
    protected java.lang.String _NrProtocolo;
    protected java.lang.String _CdRetorno;
    public final static java.lang.Class version = (br.com.indrasistemas.vivoservices.atendimento.retencao.tuxgateway.saida.finalret.impl.JAXBVersion.class);
    private static com.sun.msv.grammar.Grammar schemaFragment;

    private final static java.lang.Class PRIMARY_INTERFACE_CLASS() {
        return (br.com.indrasistemas.vivoservices.atendimento.retencao.tuxgateway.saida.finalret.RetornoVOType.class);
    }

    public java.lang.String getValor() {
        return _Valor;
    }

    public void setValor(java.lang.String value) {
        _Valor = value;
    }

    public java.lang.String getDescricao() {
        return _Descricao;
    }

    public void setDescricao(java.lang.String value) {
        _Descricao = value;
    }

    public java.lang.String getMsgRetorno() {
        return _MsgRetorno;
    }

    public void setMsgRetorno(java.lang.String value) {
        _MsgRetorno = value;
    }

    public java.lang.String getNrProtocolo() {
        return _NrProtocolo;
    }

    public void setNrProtocolo(java.lang.String value) {
        _NrProtocolo = value;
    }

    public java.lang.String getCdRetorno() {
        return _CdRetorno;
    }

    public void setCdRetorno(java.lang.String value) {
        _CdRetorno = value;
    }

    public br.com.indrasistemas.jaxb.impl.runtime.UnmarshallingEventHandler createUnmarshaller(br.com.indrasistemas.jaxb.impl.runtime.UnmarshallingContext context) {
        return new br.com.indrasistemas.vivoservices.atendimento.retencao.tuxgateway.saida.finalret.impl.RetornoVOTypeImpl.Unmarshaller(context);
    }

    public void serializeBody(br.com.indrasistemas.jaxb.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        if (_CdRetorno!= null) {
            context.startElement("", "cdRetorno");
            context.endNamespaceDecls();
            context.endAttributes();
            try {
                context.text(((java.lang.String) _CdRetorno), "CdRetorno");
            } catch (java.lang.Exception e) {
                br.com.indrasistemas.jaxb.impl.runtime.Util.handlePrintConversionException(this, e, context);
            }
            context.endElement();
        }
        if (_MsgRetorno!= null) {
            context.startElement("", "msgRetorno");
            context.endNamespaceDecls();
            context.endAttributes();
            try {
                context.text(((java.lang.String) _MsgRetorno), "MsgRetorno");
            } catch (java.lang.Exception e) {
                br.com.indrasistemas.jaxb.impl.runtime.Util.handlePrintConversionException(this, e, context);
            }
            context.endElement();
        }
        context.startElement("", "descricao");
        context.endNamespaceDecls();
        context.endAttributes();
        try {
            context.text(((java.lang.String) _Descricao), "Descricao");
        } catch (java.lang.Exception e) {
            br.com.indrasistemas.jaxb.impl.runtime.Util.handlePrintConversionException(this, e, context);
        }
        context.endElement();
        context.startElement("", "valor");
        context.endNamespaceDecls();
        context.endAttributes();
        try {
            context.text(((java.lang.String) _Valor), "Valor");
        } catch (java.lang.Exception e) {
            br.com.indrasistemas.jaxb.impl.runtime.Util.handlePrintConversionException(this, e, context);
        }
        context.endElement();
        if (_NrProtocolo!= null) {
            context.startElement("", "nrProtocolo");
            context.endNamespaceDecls();
            context.endAttributes();
            try {
                context.text(((java.lang.String) _NrProtocolo), "NrProtocolo");
            } catch (java.lang.Exception e) {
                br.com.indrasistemas.jaxb.impl.runtime.Util.handlePrintConversionException(this, e, context);
            }
            context.endElement();
        }
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
        return (br.com.indrasistemas.vivoservices.atendimento.retencao.tuxgateway.saida.finalret.RetornoVOType.class);
    }

    public com.sun.msv.verifier.DocumentDeclaration createRawValidator() {
        if (schemaFragment == null) {
            schemaFragment = com.sun.xml.bind.validator.SchemaDeserializer.deserialize((
 "\u00ac\u00ed\u0000\u0005sr\u0000\u001fcom.sun.msv.grammar.SequenceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001dcom.su"
+"n.msv.grammar.BinaryExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0004exp1t\u0000 Lcom/sun/msv/gra"
+"mmar/Expression;L\u0000\u0004exp2q\u0000~\u0000\u0002xr\u0000\u001ecom.sun.msv.grammar.Expressi"
+"on\u00f8\u0018\u0082\u00e8N5~O\u0002\u0000\u0002L\u0000\u0013epsilonReducibilityt\u0000\u0013Ljava/lang/Boolean;L\u0000\u000b"
+"expandedExpq\u0000~\u0000\u0002xpppsq\u0000~\u0000\u0000ppsq\u0000~\u0000\u0000ppsq\u0000~\u0000\u0000ppsr\u0000\u001dcom.sun.msv."
+"grammar.ChoiceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0001ppsr\u0000\'com.sun.msv.grammar."
+"trex.ElementPattern\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\tnameClasst\u0000\u001fLcom/sun/msv/gr"
+"ammar/NameClass;xr\u0000\u001ecom.sun.msv.grammar.ElementExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000"
+"\u0002Z\u0000\u001aignoreUndeclaredAttributesL\u0000\fcontentModelq\u0000~\u0000\u0002xq\u0000~\u0000\u0003sr\u0000\u0011"
+"java.lang.Boolean\u00cd r\u0080\u00d5\u009c\u00fa\u00ee\u0002\u0000\u0001Z\u0000\u0005valuexp\u0000p\u0000sq\u0000~\u0000\u0000ppsr\u0000\u001bcom.sun"
+".msv.grammar.DataExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0003L\u0000\u0002dtt\u0000\u001fLorg/relaxng/datatype"
+"/Datatype;L\u0000\u0006exceptq\u0000~\u0000\u0002L\u0000\u0004namet\u0000\u001dLcom/sun/msv/util/StringPa"
+"ir;xq\u0000~\u0000\u0003ppsr\u0000#com.sun.msv.datatype.xsd.StringType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000"
+"\u0001Z\u0000\risAlwaysValidxr\u0000*com.sun.msv.datatype.xsd.BuiltinAtomicT"
+"ype\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000%com.sun.msv.datatype.xsd.ConcreteType\u0000\u0000\u0000\u0000\u0000"
+"\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\'com.sun.msv.datatype.xsd.XSDatatypeImpl\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0003"
+"L\u0000\fnamespaceUrit\u0000\u0012Ljava/lang/String;L\u0000\btypeNameq\u0000~\u0000\u001aL\u0000\nwhite"
+"Spacet\u0000.Lcom/sun/msv/datatype/xsd/WhiteSpaceProcessor;xpt\u0000 h"
+"ttp://www.w3.org/2001/XMLSchemat\u0000\u0006stringsr\u00005com.sun.msv.data"
+"type.xsd.WhiteSpaceProcessor$Preserve\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000,com.sun."
+"msv.datatype.xsd.WhiteSpaceProcessor\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xp\u0001sr\u00000com.su"
+"n.msv.grammar.Expression$NullSetExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003p"
+"psr\u0000\u001bcom.sun.msv.util.StringPair\u00d0t\u001ejB\u008f\u008d\u00a0\u0002\u0000\u0002L\u0000\tlocalNameq\u0000~\u0000\u001a"
+"L\u0000\fnamespaceURIq\u0000~\u0000\u001axpq\u0000~\u0000\u001eq\u0000~\u0000\u001dsq\u0000~\u0000\tppsr\u0000 com.sun.msv.gram"
+"mar.AttributeExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0003expq\u0000~\u0000\u0002L\u0000\tnameClassq\u0000~\u0000\fxq\u0000~\u0000"
+"\u0003q\u0000~\u0000\u0010psq\u0000~\u0000\u0012ppsr\u0000\"com.sun.msv.datatype.xsd.QnameType\u0000\u0000\u0000\u0000\u0000\u0000\u0000"
+"\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0017q\u0000~\u0000\u001dt\u0000\u0005QNamesr\u00005com.sun.msv.datatype.xsd.WhiteSpa"
+"ceProcessor$Collapse\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000 q\u0000~\u0000#sq\u0000~\u0000$q\u0000~\u0000,q\u0000~\u0000\u001dsr"
+"\u0000#com.sun.msv.grammar.SimpleNameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\tlocalName"
+"q\u0000~\u0000\u001aL\u0000\fnamespaceURIq\u0000~\u0000\u001axr\u0000\u001dcom.sun.msv.grammar.NameClass\u0000\u0000"
+"\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xpt\u0000\u0004typet\u0000)http://www.w3.org/2001/XMLSchema-instan"
+"cesr\u00000com.sun.msv.grammar.Expression$EpsilonExpression\u0000\u0000\u0000\u0000\u0000\u0000"
+"\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003sq\u0000~\u0000\u000f\u0001psq\u0000~\u00000t\u0000\tcdRetornot\u0000\u0000q\u0000~\u00006sq\u0000~\u0000\tppsq\u0000~\u0000\u000bq"
+"\u0000~\u0000\u0010p\u0000sq\u0000~\u0000\u0000ppq\u0000~\u0000\u0015sq\u0000~\u0000\tppsq\u0000~\u0000\'q\u0000~\u0000\u0010pq\u0000~\u0000)q\u0000~\u00002q\u0000~\u00006sq\u0000~\u00000"
+"t\u0000\nmsgRetornoq\u0000~\u0000:q\u0000~\u00006sq\u0000~\u0000\u000bpp\u0000sq\u0000~\u0000\u0000ppq\u0000~\u0000\u0015sq\u0000~\u0000\tppsq\u0000~\u0000\'q"
+"\u0000~\u0000\u0010pq\u0000~\u0000)q\u0000~\u00002q\u0000~\u00006sq\u0000~\u00000t\u0000\tdescricaoq\u0000~\u0000:sq\u0000~\u0000\u000bpp\u0000sq\u0000~\u0000\u0000pp"
+"q\u0000~\u0000\u0015sq\u0000~\u0000\tppsq\u0000~\u0000\'q\u0000~\u0000\u0010pq\u0000~\u0000)q\u0000~\u00002q\u0000~\u00006sq\u0000~\u00000t\u0000\u0005valorq\u0000~\u0000:s"
+"q\u0000~\u0000\tppsq\u0000~\u0000\u000bq\u0000~\u0000\u0010p\u0000sq\u0000~\u0000\u0000ppq\u0000~\u0000\u0015sq\u0000~\u0000\tppsq\u0000~\u0000\'q\u0000~\u0000\u0010pq\u0000~\u0000)q\u0000"
+"~\u00002q\u0000~\u00006sq\u0000~\u00000t\u0000\u000bnrProtocoloq\u0000~\u0000:q\u0000~\u00006sr\u0000\"com.sun.msv.gramma"
+"r.ExpressionPool\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\bexpTablet\u0000/Lcom/sun/msv/gramma"
+"r/ExpressionPool$ClosedHash;xpsr\u0000-com.sun.msv.grammar.Expres"
+"sionPool$ClosedHash\u00d7j\u00d0N\u00ef\u00e8\u00ed\u001c\u0003\u0000\u0003I\u0000\u0005countB\u0000\rstreamVersionL\u0000\u0006par"
+"entt\u0000$Lcom/sun/msv/grammar/ExpressionPool;xp\u0000\u0000\u0000\u0011\u0001pq\u0000~\u0000\u0005q\u0000~\u0000\b"
+"q\u0000~\u0000\u0007q\u0000~\u0000&q\u0000~\u0000>q\u0000~\u0000\u0011q\u0000~\u0000=q\u0000~\u0000Dq\u0000~\u0000Cq\u0000~\u0000Jq\u0000~\u0000Iq\u0000~\u0000Qq\u0000~\u0000Pq\u0000~\u0000\u0006"
+"q\u0000~\u0000\nq\u0000~\u0000;q\u0000~\u0000Nx"));
        }
        return new com.sun.msv.verifier.regexp.REDocumentDeclaration(schemaFragment);
    }

    public class Unmarshaller
        extends br.com.indrasistemas.jaxb.impl.runtime.AbstractUnmarshallingEventHandlerImpl
    {


        public Unmarshaller(br.com.indrasistemas.jaxb.impl.runtime.UnmarshallingContext context) {
            super(context, "----------------");
        }

        protected Unmarshaller(br.com.indrasistemas.jaxb.impl.runtime.UnmarshallingContext context, int startState) {
            this(context);
            state = startState;
        }

        public java.lang.Object owner() {
            return br.com.indrasistemas.vivoservices.atendimento.retencao.tuxgateway.saida.finalret.impl.RetornoVOTypeImpl.this;
        }

        public void enterElement(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname, org.xml.sax.Attributes __atts)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                switch (state) {
                    case  15 :
                        revertToParentFromEnterElement(___uri, ___local, ___qname, __atts);
                        return ;
                    case  3 :
                        if (("msgRetorno" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, true);
                            state = 4;
                            return ;
                        }
                        state = 6;
                        continue outer;
                    case  9 :
                        if (("valor" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, true);
                            state = 10;
                            return ;
                        }
                        break;
                    case  0 :
                        if (("cdRetorno" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, true);
                            state = 1;
                            return ;
                        }
                        state = 3;
                        continue outer;
                    case  6 :
                        if (("descricao" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, true);
                            state = 7;
                            return ;
                        }
                        break;
                    case  12 :
                        if (("nrProtocolo" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, true);
                            state = 13;
                            return ;
                        }
                        state = 15;
                        continue outer;
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
                    case  14 :
                        if (("nrProtocolo" == ___local)&&("" == ___uri)) {
                            context.popAttributes();
                            state = 15;
                            return ;
                        }
                        break;
                    case  8 :
                        if (("descricao" == ___local)&&("" == ___uri)) {
                            context.popAttributes();
                            state = 9;
                            return ;
                        }
                        break;
                    case  15 :
                        revertToParentFromLeaveElement(___uri, ___local, ___qname);
                        return ;
                    case  3 :
                        state = 6;
                        continue outer;
                    case  0 :
                        state = 3;
                        continue outer;
                    case  11 :
                        if (("valor" == ___local)&&("" == ___uri)) {
                            context.popAttributes();
                            state = 12;
                            return ;
                        }
                        break;
                    case  12 :
                        state = 15;
                        continue outer;
                    case  2 :
                        if (("cdRetorno" == ___local)&&("" == ___uri)) {
                            context.popAttributes();
                            state = 3;
                            return ;
                        }
                        break;
                    case  5 :
                        if (("msgRetorno" == ___local)&&("" == ___uri)) {
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
                    case  15 :
                        revertToParentFromEnterAttribute(___uri, ___local, ___qname);
                        return ;
                    case  3 :
                        state = 6;
                        continue outer;
                    case  0 :
                        state = 3;
                        continue outer;
                    case  12 :
                        state = 15;
                        continue outer;
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
                    case  15 :
                        revertToParentFromLeaveAttribute(___uri, ___local, ___qname);
                        return ;
                    case  3 :
                        state = 6;
                        continue outer;
                    case  0 :
                        state = 3;
                        continue outer;
                    case  12 :
                        state = 15;
                        continue outer;
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
                        case  10 :
                            state = 11;
                            eatText1(value);
                            return ;
                        case  15 :
                            revertToParentFromText(value);
                            return ;
                        case  3 :
                            state = 6;
                            continue outer;
                        case  4 :
                            state = 5;
                            eatText2(value);
                            return ;
                        case  0 :
                            state = 3;
                            continue outer;
                        case  13 :
                            state = 14;
                            eatText3(value);
                            return ;
                        case  1 :
                            state = 2;
                            eatText4(value);
                            return ;
                        case  12 :
                            state = 15;
                            continue outer;
                        case  7 :
                            state = 8;
                            eatText5(value);
                            return ;
                    }
                } catch (java.lang.RuntimeException e) {
                    handleUnexpectedTextException(value, e);
                }
                break;
            }
        }

        private void eatText1(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            try {
                _Valor = value;
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

        private void eatText2(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            try {
                _MsgRetorno = value;
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

        private void eatText3(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            try {
                _NrProtocolo = value;
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

        private void eatText4(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            try {
                _CdRetorno = value;
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

        private void eatText5(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            try {
                _Descricao = value;
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

    }

}
