//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.6-01/24/2006 06:08 PM(kohsuke)-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.09.03 at 11:56:26 AM BRT 
//


package br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.saida.impl;

public class MsgHdrTypeImpl implements br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.saida.MsgHdrType, com.sun.xml.bind.JAXBObject, br.com.indrasistemas.jaxb.impl.runtime.UnmarshallableObject, br.com.indrasistemas.jaxb.impl.runtime.XMLSerializable, br.com.indrasistemas.jaxb.impl.runtime.ValidatableObject
{

    protected java.lang.String _StatusCode;
    protected java.lang.String _User;
    protected boolean has_ServerElapsedTime;
    protected int _ServerElapsedTime;
    protected java.lang.String _Service;
    protected java.lang.String _SubSystem;
    protected java.lang.String _StatusText;
    public final static java.lang.Class version = (br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.saida.impl.JAXBVersion.class);
    private static com.sun.msv.grammar.Grammar schemaFragment;

    private final static java.lang.Class PRIMARY_INTERFACE_CLASS() {
        return (br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.saida.MsgHdrType.class);
    }

    public java.lang.String getStatusCode() {
        return _StatusCode;
    }

    public void setStatusCode(java.lang.String value) {
        _StatusCode = value;
    }

    public java.lang.String getUser() {
        return _User;
    }

    public void setUser(java.lang.String value) {
        _User = value;
    }

    public int getServerElapsedTime() {
        return _ServerElapsedTime;
    }

    public void setServerElapsedTime(int value) {
        _ServerElapsedTime = value;
        has_ServerElapsedTime = true;
    }

    public java.lang.String getService() {
        return _Service;
    }

    public void setService(java.lang.String value) {
        _Service = value;
    }

    public java.lang.String getSubSystem() {
        return _SubSystem;
    }

    public void setSubSystem(java.lang.String value) {
        _SubSystem = value;
    }

    public java.lang.String getStatusText() {
        return _StatusText;
    }

    public void setStatusText(java.lang.String value) {
        _StatusText = value;
    }

    public br.com.indrasistemas.jaxb.impl.runtime.UnmarshallingEventHandler createUnmarshaller(br.com.indrasistemas.jaxb.impl.runtime.UnmarshallingContext context) {
        return new br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.saida.impl.MsgHdrTypeImpl.Unmarshaller(context);
    }

    public void serializeBody(br.com.indrasistemas.jaxb.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        if (_User!= null) {
            context.startElement("", "user");
            context.endNamespaceDecls();
            context.endAttributes();
            try {
                context.text(((java.lang.String) _User), "User");
            } catch (java.lang.Exception e) {
                br.com.indrasistemas.jaxb.impl.runtime.Util.handlePrintConversionException(this, e, context);
            }
            context.endElement();
        }
        if (_Service!= null) {
            context.startElement("", "service");
            context.endNamespaceDecls();
            context.endAttributes();
            try {
                context.text(((java.lang.String) _Service), "Service");
            } catch (java.lang.Exception e) {
                br.com.indrasistemas.jaxb.impl.runtime.Util.handlePrintConversionException(this, e, context);
            }
            context.endElement();
        }
        if (_SubSystem!= null) {
            context.startElement("", "subSystem");
            context.endNamespaceDecls();
            context.endAttributes();
            try {
                context.text(((java.lang.String) _SubSystem), "SubSystem");
            } catch (java.lang.Exception e) {
                br.com.indrasistemas.jaxb.impl.runtime.Util.handlePrintConversionException(this, e, context);
            }
            context.endElement();
        }
        if (has_ServerElapsedTime) {
            context.startElement("", "serverElapsedTime");
            context.endNamespaceDecls();
            context.endAttributes();
            try {
                context.text(javax.xml.bind.DatatypeConverter.printInt(((int) _ServerElapsedTime)), "ServerElapsedTime");
            } catch (java.lang.Exception e) {
                br.com.indrasistemas.jaxb.impl.runtime.Util.handlePrintConversionException(this, e, context);
            }
            context.endElement();
        }
        if (_StatusCode!= null) {
            context.startElement("", "statusCode");
            context.endNamespaceDecls();
            context.endAttributes();
            try {
                context.text(((java.lang.String) _StatusCode), "StatusCode");
            } catch (java.lang.Exception e) {
                br.com.indrasistemas.jaxb.impl.runtime.Util.handlePrintConversionException(this, e, context);
            }
            context.endElement();
        }
        if (_StatusText!= null) {
            context.startElement("", "statusText");
            context.endNamespaceDecls();
            context.endAttributes();
            try {
                context.text(((java.lang.String) _StatusText), "StatusText");
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
        return (br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.saida.MsgHdrType.class);
    }

    public com.sun.msv.verifier.DocumentDeclaration createRawValidator() {
        if (schemaFragment == null) {
            schemaFragment = com.sun.xml.bind.validator.SchemaDeserializer.deserialize((
 "\u00ac\u00ed\u0000\u0005sr\u0000!com.sun.msv.grammar.InterleaveExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001dcom."
+"sun.msv.grammar.BinaryExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0004exp1t\u0000 Lcom/sun/msv/g"
+"rammar/Expression;L\u0000\u0004exp2q\u0000~\u0000\u0002xr\u0000\u001ecom.sun.msv.grammar.Expres"
+"sion\u00f8\u0018\u0082\u00e8N5~O\u0002\u0000\u0002L\u0000\u0013epsilonReducibilityt\u0000\u0013Ljava/lang/Boolean;L"
+"\u0000\u000bexpandedExpq\u0000~\u0000\u0002xpppsq\u0000~\u0000\u0000ppsq\u0000~\u0000\u0000ppsq\u0000~\u0000\u0000ppsq\u0000~\u0000\u0000ppsr\u0000\u001dco"
+"m.sun.msv.grammar.ChoiceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0001ppsr\u0000\'com.sun.ms"
+"v.grammar.trex.ElementPattern\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\tnameClasst\u0000\u001fLcom/"
+"sun/msv/grammar/NameClass;xr\u0000\u001ecom.sun.msv.grammar.ElementExp"
+"\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002Z\u0000\u001aignoreUndeclaredAttributesL\u0000\fcontentModelq\u0000~\u0000\u0002"
+"xq\u0000~\u0000\u0003sr\u0000\u0011java.lang.Boolean\u00cd r\u0080\u00d5\u009c\u00fa\u00ee\u0002\u0000\u0001Z\u0000\u0005valuexp\u0000p\u0000sr\u0000\u001fcom.s"
+"un.msv.grammar.SequenceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0001ppsr\u0000\u001bcom.sun.msv"
+".grammar.DataExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0003L\u0000\u0002dtt\u0000\u001fLorg/relaxng/datatype/Dat"
+"atype;L\u0000\u0006exceptq\u0000~\u0000\u0002L\u0000\u0004namet\u0000\u001dLcom/sun/msv/util/StringPair;x"
+"q\u0000~\u0000\u0003ppsr\u0000#com.sun.msv.datatype.xsd.StringType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001Z\u0000\r"
+"isAlwaysValidxr\u0000*com.sun.msv.datatype.xsd.BuiltinAtomicType\u0000"
+"\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000%com.sun.msv.datatype.xsd.ConcreteType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002"
+"\u0000\u0000xr\u0000\'com.sun.msv.datatype.xsd.XSDatatypeImpl\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0003L\u0000\fn"
+"amespaceUrit\u0000\u0012Ljava/lang/String;L\u0000\btypeNameq\u0000~\u0000\u001cL\u0000\nwhiteSpac"
+"et\u0000.Lcom/sun/msv/datatype/xsd/WhiteSpaceProcessor;xpt\u0000 http:"
+"//www.w3.org/2001/XMLSchemat\u0000\u0006stringsr\u00005com.sun.msv.datatype"
+".xsd.WhiteSpaceProcessor$Preserve\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000,com.sun.msv."
+"datatype.xsd.WhiteSpaceProcessor\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xp\u0001sr\u00000com.sun.ms"
+"v.grammar.Expression$NullSetExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003ppsr\u0000"
+"\u001bcom.sun.msv.util.StringPair\u00d0t\u001ejB\u008f\u008d\u00a0\u0002\u0000\u0002L\u0000\tlocalNameq\u0000~\u0000\u001cL\u0000\fn"
+"amespaceURIq\u0000~\u0000\u001cxpq\u0000~\u0000 q\u0000~\u0000\u001fsq\u0000~\u0000\nppsr\u0000 com.sun.msv.grammar."
+"AttributeExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0003expq\u0000~\u0000\u0002L\u0000\tnameClassq\u0000~\u0000\rxq\u0000~\u0000\u0003q\u0000~"
+"\u0000\u0011psq\u0000~\u0000\u0014ppsr\u0000\"com.sun.msv.datatype.xsd.QnameType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000"
+"xq\u0000~\u0000\u0019q\u0000~\u0000\u001ft\u0000\u0005QNamesr\u00005com.sun.msv.datatype.xsd.WhiteSpacePr"
+"ocessor$Collapse\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\"q\u0000~\u0000%sq\u0000~\u0000&q\u0000~\u0000.q\u0000~\u0000\u001fsr\u0000#co"
+"m.sun.msv.grammar.SimpleNameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\tlocalNameq\u0000~\u0000"
+"\u001cL\u0000\fnamespaceURIq\u0000~\u0000\u001cxr\u0000\u001dcom.sun.msv.grammar.NameClass\u0000\u0000\u0000\u0000\u0000\u0000"
+"\u0000\u0001\u0002\u0000\u0000xpt\u0000\u0004typet\u0000)http://www.w3.org/2001/XMLSchema-instancesr"
+"\u00000com.sun.msv.grammar.Expression$EpsilonExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000"
+"\u0000xq\u0000~\u0000\u0003sq\u0000~\u0000\u0010\u0001psq\u0000~\u00002t\u0000\u0004usert\u0000\u0000q\u0000~\u00008sq\u0000~\u0000\nppsq\u0000~\u0000\fq\u0000~\u0000\u0011p\u0000sq\u0000"
+"~\u0000\u0012ppq\u0000~\u0000\u0017sq\u0000~\u0000\nppsq\u0000~\u0000)q\u0000~\u0000\u0011pq\u0000~\u0000+q\u0000~\u00004q\u0000~\u00008sq\u0000~\u00002t\u0000\u0007servic"
+"eq\u0000~\u0000<q\u0000~\u00008sq\u0000~\u0000\nppsq\u0000~\u0000\fq\u0000~\u0000\u0011p\u0000sq\u0000~\u0000\u0012ppq\u0000~\u0000\u0017sq\u0000~\u0000\nppsq\u0000~\u0000)q"
+"\u0000~\u0000\u0011pq\u0000~\u0000+q\u0000~\u00004q\u0000~\u00008sq\u0000~\u00002t\u0000\tsubSystemq\u0000~\u0000<q\u0000~\u00008sq\u0000~\u0000\nppsq\u0000~"
+"\u0000\fq\u0000~\u0000\u0011p\u0000sq\u0000~\u0000\u0012ppsq\u0000~\u0000\u0014ppsr\u0000 com.sun.msv.datatype.xsd.IntTyp"
+"e\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000+com.sun.msv.datatype.xsd.IntegerDerivedType\u0099"
+"\u00f1]\u0090&6k\u00be\u0002\u0000\u0001L\u0000\nbaseFacetst\u0000)Lcom/sun/msv/datatype/xsd/XSDataty"
+"peImpl;xq\u0000~\u0000\u0019q\u0000~\u0000\u001ft\u0000\u0003intq\u0000~\u00000sr\u0000*com.sun.msv.datatype.xsd.Ma"
+"xInclusiveFacet\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000#com.sun.msv.datatype.xsd.Range"
+"Facet\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\nlimitValuet\u0000\u0012Ljava/lang/Object;xr\u00009com.su"
+"n.msv.datatype.xsd.DataTypeWithValueConstraintFacet\"\u00a7Ro\u00ca\u00c7\u008aT\u0002"
+"\u0000\u0000xr\u0000*com.sun.msv.datatype.xsd.DataTypeWithFacet\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0005Z"
+"\u0000\fisFacetFixedZ\u0000\u0012needValueCheckFlagL\u0000\bbaseTypeq\u0000~\u0000QL\u0000\fconcre"
+"teTypet\u0000\'Lcom/sun/msv/datatype/xsd/ConcreteType;L\u0000\tfacetName"
+"q\u0000~\u0000\u001cxq\u0000~\u0000\u001bppq\u0000~\u00000\u0000\u0001sr\u0000*com.sun.msv.datatype.xsd.MinInclusiv"
+"eFacet\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000Uppq\u0000~\u00000\u0000\u0000sr\u0000!com.sun.msv.datatype.xsd"
+".LongType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000Pq\u0000~\u0000\u001ft\u0000\u0004longq\u0000~\u00000sq\u0000~\u0000Tppq\u0000~\u00000\u0000\u0001sq"
+"\u0000~\u0000[ppq\u0000~\u00000\u0000\u0000sr\u0000$com.sun.msv.datatype.xsd.IntegerType\u0000\u0000\u0000\u0000\u0000\u0000\u0000"
+"\u0001\u0002\u0000\u0000xq\u0000~\u0000Pq\u0000~\u0000\u001ft\u0000\u0007integerq\u0000~\u00000sr\u0000,com.sun.msv.datatype.xsd.F"
+"ractionDigitsFacet\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001I\u0000\u0005scalexr\u0000;com.sun.msv.datatyp"
+"e.xsd.DataTypeWithLexicalConstraintFacetT\u0090\u001c>\u001azb\u00ea\u0002\u0000\u0000xq\u0000~\u0000Xppq"
+"\u0000~\u00000\u0001\u0000sr\u0000#com.sun.msv.datatype.xsd.NumberType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~"
+"\u0000\u0019q\u0000~\u0000\u001ft\u0000\u0007decimalq\u0000~\u00000q\u0000~\u0000it\u0000\u000efractionDigits\u0000\u0000\u0000\u0000q\u0000~\u0000ct\u0000\fminI"
+"nclusivesr\u0000\u000ejava.lang.Long;\u008b\u00e4\u0090\u00cc\u008f#\u00df\u0002\u0000\u0001J\u0000\u0005valuexr\u0000\u0010java.lang.N"
+"umber\u0086\u00ac\u0095\u001d\u000b\u0094\u00e0\u008b\u0002\u0000\u0000xp\u0080\u0000\u0000\u0000\u0000\u0000\u0000\u0000q\u0000~\u0000ct\u0000\fmaxInclusivesq\u0000~\u0000m\u007f\u00ff\u00ff\u00ff\u00ff\u00ff\u00ff\u00ff"
+"q\u0000~\u0000^q\u0000~\u0000lsr\u0000\u0011java.lang.Integer\u0012\u00e2\u00a0\u00a4\u00f7\u0081\u00878\u0002\u0000\u0001I\u0000\u0005valuexq\u0000~\u0000n\u0080\u0000\u0000\u0000"
+"q\u0000~\u0000^q\u0000~\u0000psq\u0000~\u0000r\u007f\u00ff\u00ff\u00ffq\u0000~\u0000%sq\u0000~\u0000&q\u0000~\u0000Sq\u0000~\u0000\u001fsq\u0000~\u0000\nppsq\u0000~\u0000)q\u0000~\u0000\u0011"
+"pq\u0000~\u0000+q\u0000~\u00004q\u0000~\u00008sq\u0000~\u00002t\u0000\u0011serverElapsedTimeq\u0000~\u0000<q\u0000~\u00008sq\u0000~\u0000\npp"
+"sq\u0000~\u0000\fq\u0000~\u0000\u0011p\u0000sq\u0000~\u0000\u0012ppq\u0000~\u0000\u0017sq\u0000~\u0000\nppsq\u0000~\u0000)q\u0000~\u0000\u0011pq\u0000~\u0000+q\u0000~\u00004q\u0000~\u0000"
+"8sq\u0000~\u00002t\u0000\nstatusCodeq\u0000~\u0000<q\u0000~\u00008sq\u0000~\u0000\nppsq\u0000~\u0000\fq\u0000~\u0000\u0011p\u0000sq\u0000~\u0000\u0012ppq"
+"\u0000~\u0000\u0017sq\u0000~\u0000\nppsq\u0000~\u0000)q\u0000~\u0000\u0011pq\u0000~\u0000+q\u0000~\u00004q\u0000~\u00008sq\u0000~\u00002t\u0000\nstatusTextq\u0000"
+"~\u0000<q\u0000~\u00008sr\u0000\"com.sun.msv.grammar.ExpressionPool\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\b"
+"expTablet\u0000/Lcom/sun/msv/grammar/ExpressionPool$ClosedHash;xp"
+"sr\u0000-com.sun.msv.grammar.ExpressionPool$ClosedHash\u00d7j\u00d0N\u00ef\u00e8\u00ed\u001c\u0003\u0000\u0003"
+"I\u0000\u0005countB\u0000\rstreamVersionL\u0000\u0006parentt\u0000$Lcom/sun/msv/grammar/Exp"
+"ressionPool;xp\u0000\u0000\u0000\u0017\u0001pq\u0000~\u0000(q\u0000~\u0000@q\u0000~\u0000Gq\u0000~\u0000vq\u0000~\u0000}q\u0000~\u0000\u0084q\u0000~\u0000\u0013q\u0000~\u0000?"
+"q\u0000~\u0000Fq\u0000~\u0000|q\u0000~\u0000\u0083q\u0000~\u0000\bq\u0000~\u0000\u0007q\u0000~\u0000\u0006q\u0000~\u0000\u000bq\u0000~\u0000=q\u0000~\u0000Dq\u0000~\u0000zq\u0000~\u0000\u0081q\u0000~\u0000M"
+"q\u0000~\u0000Kq\u0000~\u0000\u0005q\u0000~\u0000\tx"));
        }
        return new com.sun.msv.verifier.regexp.REDocumentDeclaration(schemaFragment);
    }

    public class Unmarshaller
        extends br.com.indrasistemas.jaxb.impl.runtime.AbstractUnmarshallingEventHandlerImpl
    {


        public Unmarshaller(br.com.indrasistemas.jaxb.impl.runtime.UnmarshallingContext context) {
            super(context, "-------------");
        }

        protected Unmarshaller(br.com.indrasistemas.jaxb.impl.runtime.UnmarshallingContext context, int startState) {
            this(context);
            state = startState;
        }

        public java.lang.Object owner() {
            return br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.saida.impl.MsgHdrTypeImpl.this;
        }

        public void enterElement(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname, org.xml.sax.Attributes __atts)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                switch (state) {
                    case  0 :
                        if (("statusText" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, true);
                            state = 11;
                            return ;
                        }
                        if (("statusCode" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, true);
                            state = 9;
                            return ;
                        }
                        if (("serverElapsedTime" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, true);
                            state = 7;
                            return ;
                        }
                        if (("subSystem" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, true);
                            state = 1;
                            return ;
                        }
                        if (("service" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, true);
                            state = 5;
                            return ;
                        }
                        if (("user" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, true);
                            state = 3;
                            return ;
                        }
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
                    case  8 :
                        if (("serverElapsedTime" == ___local)&&("" == ___uri)) {
                            context.popAttributes();
                            state = 0;
                            return ;
                        }
                        break;
                    case  0 :
                        revertToParentFromLeaveElement(___uri, ___local, ___qname);
                        return ;
                    case  4 :
                        if (("user" == ___local)&&("" == ___uri)) {
                            context.popAttributes();
                            state = 0;
                            return ;
                        }
                        break;
                    case  10 :
                        if (("statusCode" == ___local)&&("" == ___uri)) {
                            context.popAttributes();
                            state = 0;
                            return ;
                        }
                        break;
                    case  12 :
                        if (("statusText" == ___local)&&("" == ___uri)) {
                            context.popAttributes();
                            state = 0;
                            return ;
                        }
                        break;
                    case  6 :
                        if (("service" == ___local)&&("" == ___uri)) {
                            context.popAttributes();
                            state = 0;
                            return ;
                        }
                        break;
                    case  2 :
                        if (("subSystem" == ___local)&&("" == ___uri)) {
                            context.popAttributes();
                            state = 0;
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
                    case  0 :
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
                    case  0 :
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
                        case  3 :
                            state = 4;
                            eatText1(value);
                            return ;
                        case  5 :
                            state = 6;
                            eatText2(value);
                            return ;
                        case  0 :
                            revertToParentFromText(value);
                            return ;
                        case  7 :
                            state = 8;
                            eatText3(value);
                            return ;
                        case  9 :
                            state = 10;
                            eatText4(value);
                            return ;
                        case  11 :
                            state = 12;
                            eatText5(value);
                            return ;
                        case  1 :
                            state = 2;
                            eatText6(value);
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
                _User = value;
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

        private void eatText2(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            try {
                _Service = value;
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

        private void eatText3(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            try {
                _ServerElapsedTime = javax.xml.bind.DatatypeConverter.parseInt(com.sun.xml.bind.WhiteSpaceProcessor.collapse(value));
                has_ServerElapsedTime = true;
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

        private void eatText4(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            try {
                _StatusCode = value;
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

        private void eatText5(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            try {
                _StatusText = value;
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

        private void eatText6(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            try {
                _SubSystem = value;
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

    }

}
