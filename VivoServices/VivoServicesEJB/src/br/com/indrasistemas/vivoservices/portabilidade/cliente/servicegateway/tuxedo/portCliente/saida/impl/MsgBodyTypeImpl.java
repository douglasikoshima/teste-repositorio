//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.6-01/24/2006 06:08 PM(kohsuke)-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2008.03.31 at 06:44:46 PM GMT 
//


package br.com.indrasistemas.vivoservices.portabilidade.cliente.servicegateway.tuxedo.portCliente.saida.impl;

public class MsgBodyTypeImpl implements br.com.indrasistemas.vivoservices.portabilidade.cliente.servicegateway.tuxedo.portCliente.saida.MsgBodyType, com.sun.xml.bind.JAXBObject, br.com.indrasistemas.jaxb.impl.runtime.UnmarshallableObject, br.com.indrasistemas.jaxb.impl.runtime.XMLSerializable, br.com.indrasistemas.jaxb.impl.runtime.ValidatableObject
{

    protected br.com.indrasistemas.vivoservices.portabilidade.cliente.servicegateway.tuxedo.portCliente.saida.MsgBodyType.PortabilidadeOutTOType _PortabilidadeOutTO;
    public final static java.lang.Class version = (br.com.indrasistemas.vivoservices.portabilidade.cliente.servicegateway.tuxedo.portCliente.saida.impl.JAXBVersion.class);
    private static com.sun.msv.grammar.Grammar schemaFragment;

    private final static java.lang.Class PRIMARY_INTERFACE_CLASS() {
        return (br.com.indrasistemas.vivoservices.portabilidade.cliente.servicegateway.tuxedo.portCliente.saida.MsgBodyType.class);
    }

    public br.com.indrasistemas.vivoservices.portabilidade.cliente.servicegateway.tuxedo.portCliente.saida.MsgBodyType.PortabilidadeOutTOType getPortabilidadeOutTO() {
        return _PortabilidadeOutTO;
    }

    public void setPortabilidadeOutTO(br.com.indrasistemas.vivoservices.portabilidade.cliente.servicegateway.tuxedo.portCliente.saida.MsgBodyType.PortabilidadeOutTOType value) {
        _PortabilidadeOutTO = value;
    }

    public br.com.indrasistemas.jaxb.impl.runtime.UnmarshallingEventHandler createUnmarshaller(br.com.indrasistemas.jaxb.impl.runtime.UnmarshallingContext context) {
        return new br.com.indrasistemas.vivoservices.portabilidade.cliente.servicegateway.tuxedo.portCliente.saida.impl.MsgBodyTypeImpl.Unmarshaller(context);
    }

    public void serializeBody(br.com.indrasistemas.jaxb.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        if (_PortabilidadeOutTO!= null) {
            context.startElement("", "PortabilidadeOutTO");
            context.childAsURIs(((com.sun.xml.bind.JAXBObject) _PortabilidadeOutTO), "PortabilidadeOutTO");
            context.endNamespaceDecls();
            context.childAsAttributes(((com.sun.xml.bind.JAXBObject) _PortabilidadeOutTO), "PortabilidadeOutTO");
            context.endAttributes();
            context.childAsBody(((com.sun.xml.bind.JAXBObject) _PortabilidadeOutTO), "PortabilidadeOutTO");
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
        return (br.com.indrasistemas.vivoservices.portabilidade.cliente.servicegateway.tuxedo.portCliente.saida.MsgBodyType.class);
    }

    public com.sun.msv.verifier.DocumentDeclaration createRawValidator() {
        if (schemaFragment == null) {
            schemaFragment = com.sun.xml.bind.validator.SchemaDeserializer.deserialize((
 "\u00ac\u00ed\u0000\u0005sr\u0000\u001dcom.sun.msv.grammar.ChoiceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001dcom.sun."
+"msv.grammar.BinaryExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0004exp1t\u0000 Lcom/sun/msv/gramm"
+"ar/Expression;L\u0000\u0004exp2q\u0000~\u0000\u0002xr\u0000\u001ecom.sun.msv.grammar.Expression"
+"\u00f8\u0018\u0082\u00e8N5~O\u0002\u0000\u0002L\u0000\u0013epsilonReducibilityt\u0000\u0013Ljava/lang/Boolean;L\u0000\u000bex"
+"pandedExpq\u0000~\u0000\u0002xpppsr\u0000\'com.sun.msv.grammar.trex.ElementPatter"
+"n\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\tnameClasst\u0000\u001fLcom/sun/msv/grammar/NameClass;xr"
+"\u0000\u001ecom.sun.msv.grammar.ElementExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002Z\u0000\u001aignoreUndeclar"
+"edAttributesL\u0000\fcontentModelq\u0000~\u0000\u0002xq\u0000~\u0000\u0003sr\u0000\u0011java.lang.Boolean\u00cd"
+" r\u0080\u00d5\u009c\u00fa\u00ee\u0002\u0000\u0001Z\u0000\u0005valuexp\u0000p\u0000sr\u0000\u001fcom.sun.msv.grammar.SequenceExp\u0000\u0000"
+"\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0001ppsq\u0000~\u0000\u0006pp\u0000sq\u0000~\u0000\u0000ppsr\u0000 com.sun.msv.grammar.On"
+"eOrMoreExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001ccom.sun.msv.grammar.UnaryExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000"
+"\u0001\u0002\u0000\u0001L\u0000\u0003expq\u0000~\u0000\u0002xq\u0000~\u0000\u0003q\u0000~\u0000\u000bpsr\u0000 com.sun.msv.grammar.Attribute"
+"Exp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0003expq\u0000~\u0000\u0002L\u0000\tnameClassq\u0000~\u0000\u0007xq\u0000~\u0000\u0003q\u0000~\u0000\u000bpsr\u00002co"
+"m.sun.msv.grammar.Expression$AnyStringExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000x"
+"q\u0000~\u0000\u0003sq\u0000~\u0000\n\u0001psr\u0000 com.sun.msv.grammar.AnyNameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000"
+"xr\u0000\u001dcom.sun.msv.grammar.NameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xpsr\u00000com.sun.ms"
+"v.grammar.Expression$EpsilonExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003q\u0000~\u0000\u0017"
+"psr\u0000#com.sun.msv.grammar.SimpleNameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\tlocalN"
+"amet\u0000\u0012Ljava/lang/String;L\u0000\fnamespaceURIq\u0000~\u0000\u001exq\u0000~\u0000\u0019t\u0000\u0082br.com."
+"indrasistemas.vivoservices.portabilidade.cliente.servicegate"
+"way.tuxedo.portCliente.saida.MsgBodyType.PortabilidadeOutTOT"
+"ypet\u0000+http://java.sun.com/jaxb/xjc/dummy-elementssq\u0000~\u0000\u0000ppsq\u0000"
+"~\u0000\u0013q\u0000~\u0000\u000bpsr\u0000\u001bcom.sun.msv.grammar.DataExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0003L\u0000\u0002dtt\u0000\u001fL"
+"org/relaxng/datatype/Datatype;L\u0000\u0006exceptq\u0000~\u0000\u0002L\u0000\u0004namet\u0000\u001dLcom/s"
+"un/msv/util/StringPair;xq\u0000~\u0000\u0003ppsr\u0000\"com.sun.msv.datatype.xsd."
+"QnameType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000*com.sun.msv.datatype.xsd.BuiltinAtom"
+"icType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000%com.sun.msv.datatype.xsd.ConcreteType\u0000\u0000"
+"\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\'com.sun.msv.datatype.xsd.XSDatatypeImpl\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001"
+"\u0002\u0000\u0003L\u0000\fnamespaceUriq\u0000~\u0000\u001eL\u0000\btypeNameq\u0000~\u0000\u001eL\u0000\nwhiteSpacet\u0000.Lcom/"
+"sun/msv/datatype/xsd/WhiteSpaceProcessor;xpt\u0000 http://www.w3."
+"org/2001/XMLSchemat\u0000\u0005QNamesr\u00005com.sun.msv.datatype.xsd.White"
+"SpaceProcessor$Collapse\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000,com.sun.msv.datatype.x"
+"sd.WhiteSpaceProcessor\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xpsr\u00000com.sun.msv.grammar.E"
+"xpression$NullSetExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003ppsr\u0000\u001bcom.sun.ms"
+"v.util.StringPair\u00d0t\u001ejB\u008f\u008d\u00a0\u0002\u0000\u0002L\u0000\tlocalNameq\u0000~\u0000\u001eL\u0000\fnamespaceURI"
+"q\u0000~\u0000\u001expq\u0000~\u0000/q\u0000~\u0000.sq\u0000~\u0000\u001dt\u0000\u0004typet\u0000)http://www.w3.org/2001/XMLS"
+"chema-instanceq\u0000~\u0000\u001csq\u0000~\u0000\u001dt\u0000\u0012PortabilidadeOutTOt\u0000\u0000q\u0000~\u0000\u001csr\u0000\"co"
+"m.sun.msv.grammar.ExpressionPool\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\bexpTablet\u0000/Lco"
+"m/sun/msv/grammar/ExpressionPool$ClosedHash;xpsr\u0000-com.sun.ms"
+"v.grammar.ExpressionPool$ClosedHash\u00d7j\u00d0N\u00ef\u00e8\u00ed\u001c\u0003\u0000\u0003I\u0000\u0005countB\u0000\rstr"
+"eamVersionL\u0000\u0006parentt\u0000$Lcom/sun/msv/grammar/ExpressionPool;xp"
+"\u0000\u0000\u0000\u0005\u0001pq\u0000~\u0000\rq\u0000~\u0000\u0012q\u0000~\u0000\u0005q\u0000~\u0000\"q\u0000~\u0000\u000fx"));
        }
        return new com.sun.msv.verifier.regexp.REDocumentDeclaration(schemaFragment);
    }

    public static class PortabilidadeOutTOTypeImpl implements br.com.indrasistemas.vivoservices.portabilidade.cliente.servicegateway.tuxedo.portCliente.saida.MsgBodyType.PortabilidadeOutTOType, com.sun.xml.bind.JAXBObject, br.com.indrasistemas.jaxb.impl.runtime.UnmarshallableObject, br.com.indrasistemas.jaxb.impl.runtime.XMLSerializable, br.com.indrasistemas.jaxb.impl.runtime.ValidatableObject
    {

        protected java.lang.String _MsgError;
        protected boolean has_CdError;
        protected int _CdError;
        public final static java.lang.Class version = (br.com.indrasistemas.vivoservices.portabilidade.cliente.servicegateway.tuxedo.portCliente.saida.impl.JAXBVersion.class);
        private static com.sun.msv.grammar.Grammar schemaFragment;

        private final static java.lang.Class PRIMARY_INTERFACE_CLASS() {
            return (br.com.indrasistemas.vivoservices.portabilidade.cliente.servicegateway.tuxedo.portCliente.saida.MsgBodyType.PortabilidadeOutTOType.class);
        }

        public java.lang.String getMsgError() {
            return _MsgError;
        }

        public void setMsgError(java.lang.String value) {
            _MsgError = value;
        }

        public int getCdError() {
            return _CdError;
        }

        public void setCdError(int value) {
            _CdError = value;
            has_CdError = true;
        }

        public br.com.indrasistemas.jaxb.impl.runtime.UnmarshallingEventHandler createUnmarshaller(br.com.indrasistemas.jaxb.impl.runtime.UnmarshallingContext context) {
            return new br.com.indrasistemas.vivoservices.portabilidade.cliente.servicegateway.tuxedo.portCliente.saida.impl.MsgBodyTypeImpl.PortabilidadeOutTOTypeImpl.Unmarshaller(context);
        }

        public void serializeBody(br.com.indrasistemas.jaxb.impl.runtime.XMLSerializer context)
            throws org.xml.sax.SAXException
        {
            if (has_CdError) {
                context.startElement("", "cdError");
                context.endNamespaceDecls();
                context.endAttributes();
                try {
                    context.text(javax.xml.bind.DatatypeConverter.printInt(((int) _CdError)), "CdError");
                } catch (java.lang.Exception e) {
                    br.com.indrasistemas.jaxb.impl.runtime.Util.handlePrintConversionException(this, e, context);
                }
                context.endElement();
            }
            if (_MsgError!= null) {
                context.startElement("", "msgError");
                context.endNamespaceDecls();
                context.endAttributes();
                try {
                    context.text(((java.lang.String) _MsgError), "MsgError");
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
            return (br.com.indrasistemas.vivoservices.portabilidade.cliente.servicegateway.tuxedo.portCliente.saida.MsgBodyType.PortabilidadeOutTOType.class);
        }

        public com.sun.msv.verifier.DocumentDeclaration createRawValidator() {
            if (schemaFragment == null) {
                schemaFragment = com.sun.xml.bind.validator.SchemaDeserializer.deserialize((
 "\u00ac\u00ed\u0000\u0005sr\u0000\u001fcom.sun.msv.grammar.SequenceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001dcom.su"
+"n.msv.grammar.BinaryExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0004exp1t\u0000 Lcom/sun/msv/gra"
+"mmar/Expression;L\u0000\u0004exp2q\u0000~\u0000\u0002xr\u0000\u001ecom.sun.msv.grammar.Expressi"
+"on\u00f8\u0018\u0082\u00e8N5~O\u0002\u0000\u0002L\u0000\u0013epsilonReducibilityt\u0000\u0013Ljava/lang/Boolean;L\u0000\u000b"
+"expandedExpq\u0000~\u0000\u0002xpppsr\u0000\u001dcom.sun.msv.grammar.ChoiceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000"
+"\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0001ppsr\u0000\'com.sun.msv.grammar.trex.ElementPattern\u0000\u0000\u0000\u0000\u0000"
+"\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\tnameClasst\u0000\u001fLcom/sun/msv/grammar/NameClass;xr\u0000\u001ecom."
+"sun.msv.grammar.ElementExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002Z\u0000\u001aignoreUndeclaredAttr"
+"ibutesL\u0000\fcontentModelq\u0000~\u0000\u0002xq\u0000~\u0000\u0003sr\u0000\u0011java.lang.Boolean\u00cd r\u0080\u00d5\u009c\u00fa"
+"\u00ee\u0002\u0000\u0001Z\u0000\u0005valuexp\u0000p\u0000sq\u0000~\u0000\u0000ppsr\u0000\u001bcom.sun.msv.grammar.DataExp\u0000\u0000\u0000\u0000"
+"\u0000\u0000\u0000\u0001\u0002\u0000\u0003L\u0000\u0002dtt\u0000\u001fLorg/relaxng/datatype/Datatype;L\u0000\u0006exceptq\u0000~\u0000\u0002"
+"L\u0000\u0004namet\u0000\u001dLcom/sun/msv/util/StringPair;xq\u0000~\u0000\u0003ppsr\u0000 com.sun.m"
+"sv.datatype.xsd.IntType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000+com.sun.msv.datatype.x"
+"sd.IntegerDerivedType\u0099\u00f1]\u0090&6k\u00be\u0002\u0000\u0001L\u0000\nbaseFacetst\u0000)Lcom/sun/msv"
+"/datatype/xsd/XSDatatypeImpl;xr\u0000*com.sun.msv.datatype.xsd.Bu"
+"iltinAtomicType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000%com.sun.msv.datatype.xsd.Concr"
+"eteType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\'com.sun.msv.datatype.xsd.XSDatatypeImp"
+"l\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0003L\u0000\fnamespaceUrit\u0000\u0012Ljava/lang/String;L\u0000\btypeNameq"
+"\u0000~\u0000\u0019L\u0000\nwhiteSpacet\u0000.Lcom/sun/msv/datatype/xsd/WhiteSpaceProc"
+"essor;xpt\u0000 http://www.w3.org/2001/XMLSchemat\u0000\u0003intsr\u00005com.sun"
+".msv.datatype.xsd.WhiteSpaceProcessor$Collapse\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000"
+",com.sun.msv.datatype.xsd.WhiteSpaceProcessor\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xpsr"
+"\u0000*com.sun.msv.datatype.xsd.MaxInclusiveFacet\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000#c"
+"om.sun.msv.datatype.xsd.RangeFacet\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\nlimitValuet\u0000"
+"\u0012Ljava/lang/Object;xr\u00009com.sun.msv.datatype.xsd.DataTypeWith"
+"ValueConstraintFacet\"\u00a7Ro\u00ca\u00c7\u008aT\u0002\u0000\u0000xr\u0000*com.sun.msv.datatype.xsd."
+"DataTypeWithFacet\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0005Z\u0000\fisFacetFixedZ\u0000\u0012needValueCheck"
+"FlagL\u0000\bbaseTypeq\u0000~\u0000\u0015L\u0000\fconcreteTypet\u0000\'Lcom/sun/msv/datatype/"
+"xsd/ConcreteType;L\u0000\tfacetNameq\u0000~\u0000\u0019xq\u0000~\u0000\u0018ppq\u0000~\u0000 \u0000\u0001sr\u0000*com.sun"
+".msv.datatype.xsd.MinInclusiveFacet\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\"ppq\u0000~\u0000 \u0000"
+"\u0000sr\u0000!com.sun.msv.datatype.xsd.LongType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0014q\u0000~\u0000\u001c"
+"t\u0000\u0004longq\u0000~\u0000 sq\u0000~\u0000!ppq\u0000~\u0000 \u0000\u0001sq\u0000~\u0000(ppq\u0000~\u0000 \u0000\u0000sr\u0000$com.sun.msv.da"
+"tatype.xsd.IntegerType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0014q\u0000~\u0000\u001ct\u0000\u0007integerq\u0000~\u0000 s"
+"r\u0000,com.sun.msv.datatype.xsd.FractionDigitsFacet\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001I\u0000"
+"\u0005scalexr\u0000;com.sun.msv.datatype.xsd.DataTypeWithLexicalConstr"
+"aintFacetT\u0090\u001c>\u001azb\u00ea\u0002\u0000\u0000xq\u0000~\u0000%ppq\u0000~\u0000 \u0001\u0000sr\u0000#com.sun.msv.datatype."
+"xsd.NumberType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0016q\u0000~\u0000\u001ct\u0000\u0007decimalq\u0000~\u0000 q\u0000~\u00006t\u0000\u000ef"
+"ractionDigits\u0000\u0000\u0000\u0000q\u0000~\u00000t\u0000\fminInclusivesr\u0000\u000ejava.lang.Long;\u008b\u00e4\u0090\u00cc"
+"\u008f#\u00df\u0002\u0000\u0001J\u0000\u0005valuexr\u0000\u0010java.lang.Number\u0086\u00ac\u0095\u001d\u000b\u0094\u00e0\u008b\u0002\u0000\u0000xp\u0080\u0000\u0000\u0000\u0000\u0000\u0000\u0000q\u0000~\u00000"
+"t\u0000\fmaxInclusivesq\u0000~\u0000:\u007f\u00ff\u00ff\u00ff\u00ff\u00ff\u00ff\u00ffq\u0000~\u0000+q\u0000~\u00009sr\u0000\u0011java.lang.Integer"
+"\u0012\u00e2\u00a0\u00a4\u00f7\u0081\u00878\u0002\u0000\u0001I\u0000\u0005valuexq\u0000~\u0000;\u0080\u0000\u0000\u0000q\u0000~\u0000+q\u0000~\u0000=sq\u0000~\u0000?\u007f\u00ff\u00ff\u00ffsr\u00000com.sun"
+".msv.grammar.Expression$NullSetExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003pp"
+"sr\u0000\u001bcom.sun.msv.util.StringPair\u00d0t\u001ejB\u008f\u008d\u00a0\u0002\u0000\u0002L\u0000\tlocalNameq\u0000~\u0000\u0019L"
+"\u0000\fnamespaceURIq\u0000~\u0000\u0019xpq\u0000~\u0000\u001dq\u0000~\u0000\u001csq\u0000~\u0000\u0006ppsr\u0000 com.sun.msv.gramm"
+"ar.AttributeExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0003expq\u0000~\u0000\u0002L\u0000\tnameClassq\u0000~\u0000\txq\u0000~\u0000\u0003"
+"q\u0000~\u0000\rpsq\u0000~\u0000\u000fppsr\u0000\"com.sun.msv.datatype.xsd.QnameType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001"
+"\u0002\u0000\u0000xq\u0000~\u0000\u0016q\u0000~\u0000\u001ct\u0000\u0005QNameq\u0000~\u0000 q\u0000~\u0000Csq\u0000~\u0000Dq\u0000~\u0000Lq\u0000~\u0000\u001csr\u0000#com.sun."
+"msv.grammar.SimpleNameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\tlocalNameq\u0000~\u0000\u0019L\u0000\fna"
+"mespaceURIq\u0000~\u0000\u0019xr\u0000\u001dcom.sun.msv.grammar.NameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000x"
+"pt\u0000\u0004typet\u0000)http://www.w3.org/2001/XMLSchema-instancesr\u00000com."
+"sun.msv.grammar.Expression$EpsilonExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000"
+"\u0003sq\u0000~\u0000\f\u0001psq\u0000~\u0000Nt\u0000\u0007cdErrort\u0000\u0000q\u0000~\u0000Tsq\u0000~\u0000\u0006ppsq\u0000~\u0000\bq\u0000~\u0000\rp\u0000sq\u0000~\u0000\u0000"
+"ppsq\u0000~\u0000\u000fppsr\u0000#com.sun.msv.datatype.xsd.StringType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001"
+"Z\u0000\risAlwaysValidxq\u0000~\u0000\u0016q\u0000~\u0000\u001ct\u0000\u0006stringsr\u00005com.sun.msv.datatype"
+".xsd.WhiteSpaceProcessor$Preserve\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u001f\u0001q\u0000~\u0000Csq\u0000~"
+"\u0000Dq\u0000~\u0000_q\u0000~\u0000\u001csq\u0000~\u0000\u0006ppsq\u0000~\u0000Gq\u0000~\u0000\rpq\u0000~\u0000Iq\u0000~\u0000Pq\u0000~\u0000Tsq\u0000~\u0000Nt\u0000\bmsgE"
+"rrorq\u0000~\u0000Xq\u0000~\u0000Tsr\u0000\"com.sun.msv.grammar.ExpressionPool\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001"
+"\u0002\u0000\u0001L\u0000\bexpTablet\u0000/Lcom/sun/msv/grammar/ExpressionPool$ClosedH"
+"ash;xpsr\u0000-com.sun.msv.grammar.ExpressionPool$ClosedHash\u00d7j\u00d0N\u00ef"
+"\u00e8\u00ed\u001c\u0003\u0000\u0003I\u0000\u0005countB\u0000\rstreamVersionL\u0000\u0006parentt\u0000$Lcom/sun/msv/gramm"
+"ar/ExpressionPool;xp\u0000\u0000\u0000\u0007\u0001pq\u0000~\u0000\u000eq\u0000~\u0000[q\u0000~\u0000\u0007q\u0000~\u0000Yq\u0000~\u0000\u0005q\u0000~\u0000Fq\u0000~\u0000"
+"cx"));
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
                return br.com.indrasistemas.vivoservices.portabilidade.cliente.servicegateway.tuxedo.portCliente.saida.impl.MsgBodyTypeImpl.PortabilidadeOutTOTypeImpl.this;
            }

            public void enterElement(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname, org.xml.sax.Attributes __atts)
                throws org.xml.sax.SAXException
            {
                int attIdx;
                outer:
                while (true) {
                    switch (state) {
                        case  0 :
                            if (("cdError" == ___local)&&("" == ___uri)) {
                                context.pushAttributes(__atts, true);
                                state = 1;
                                return ;
                            }
                            state = 3;
                            continue outer;
                        case  6 :
                            revertToParentFromEnterElement(___uri, ___local, ___qname, __atts);
                            return ;
                        case  3 :
                            if (("msgError" == ___local)&&("" == ___uri)) {
                                context.pushAttributes(__atts, true);
                                state = 4;
                                return ;
                            }
                            state = 6;
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
                        case  0 :
                            state = 3;
                            continue outer;
                        case  2 :
                            if (("cdError" == ___local)&&("" == ___uri)) {
                                context.popAttributes();
                                state = 3;
                                return ;
                            }
                            break;
                        case  6 :
                            revertToParentFromLeaveElement(___uri, ___local, ___qname);
                            return ;
                        case  3 :
                            state = 6;
                            continue outer;
                        case  5 :
                            if (("msgError" == ___local)&&("" == ___uri)) {
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
                        case  0 :
                            state = 3;
                            continue outer;
                        case  6 :
                            revertToParentFromEnterAttribute(___uri, ___local, ___qname);
                            return ;
                        case  3 :
                            state = 6;
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
                        case  0 :
                            state = 3;
                            continue outer;
                        case  6 :
                            revertToParentFromLeaveAttribute(___uri, ___local, ___qname);
                            return ;
                        case  3 :
                            state = 6;
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
                            case  0 :
                                state = 3;
                                continue outer;
                            case  1 :
                                state = 2;
                                eatText1(value);
                                return ;
                            case  6 :
                                revertToParentFromText(value);
                                return ;
                            case  3 :
                                state = 6;
                                continue outer;
                            case  4 :
                                state = 5;
                                eatText2(value);
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
                    _CdError = javax.xml.bind.DatatypeConverter.parseInt(com.sun.xml.bind.WhiteSpaceProcessor.collapse(value));
                    has_CdError = true;
                } catch (java.lang.Exception e) {
                    handleParseConversionException(e);
                }
            }

            private void eatText2(final java.lang.String value)
                throws org.xml.sax.SAXException
            {
                try {
                    _MsgError = value;
                } catch (java.lang.Exception e) {
                    handleParseConversionException(e);
                }
            }

        }

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
            return br.com.indrasistemas.vivoservices.portabilidade.cliente.servicegateway.tuxedo.portCliente.saida.impl.MsgBodyTypeImpl.this;
        }

        public void enterElement(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname, org.xml.sax.Attributes __atts)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                switch (state) {
                    case  3 :
                        revertToParentFromEnterElement(___uri, ___local, ___qname, __atts);
                        return ;
                    case  1 :
                        if (("cdError" == ___local)&&("" == ___uri)) {
                            _PortabilidadeOutTO = ((br.com.indrasistemas.vivoservices.portabilidade.cliente.servicegateway.tuxedo.portCliente.saida.impl.MsgBodyTypeImpl.PortabilidadeOutTOTypeImpl) spawnChildFromEnterElement((br.com.indrasistemas.vivoservices.portabilidade.cliente.servicegateway.tuxedo.portCliente.saida.impl.MsgBodyTypeImpl.PortabilidadeOutTOTypeImpl.class), 2, ___uri, ___local, ___qname, __atts));
                            return ;
                        }
                        if (("msgError" == ___local)&&("" == ___uri)) {
                            _PortabilidadeOutTO = ((br.com.indrasistemas.vivoservices.portabilidade.cliente.servicegateway.tuxedo.portCliente.saida.impl.MsgBodyTypeImpl.PortabilidadeOutTOTypeImpl) spawnChildFromEnterElement((br.com.indrasistemas.vivoservices.portabilidade.cliente.servicegateway.tuxedo.portCliente.saida.impl.MsgBodyTypeImpl.PortabilidadeOutTOTypeImpl.class), 2, ___uri, ___local, ___qname, __atts));
                            return ;
                        }
                        _PortabilidadeOutTO = ((br.com.indrasistemas.vivoservices.portabilidade.cliente.servicegateway.tuxedo.portCliente.saida.impl.MsgBodyTypeImpl.PortabilidadeOutTOTypeImpl) spawnChildFromEnterElement((br.com.indrasistemas.vivoservices.portabilidade.cliente.servicegateway.tuxedo.portCliente.saida.impl.MsgBodyTypeImpl.PortabilidadeOutTOTypeImpl.class), 2, ___uri, ___local, ___qname, __atts));
                        return ;
                    case  0 :
                        if (("PortabilidadeOutTO" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, false);
                            state = 1;
                            return ;
                        }
                        state = 3;
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
                    case  3 :
                        revertToParentFromLeaveElement(___uri, ___local, ___qname);
                        return ;
                    case  1 :
                        _PortabilidadeOutTO = ((br.com.indrasistemas.vivoservices.portabilidade.cliente.servicegateway.tuxedo.portCliente.saida.impl.MsgBodyTypeImpl.PortabilidadeOutTOTypeImpl) spawnChildFromLeaveElement((br.com.indrasistemas.vivoservices.portabilidade.cliente.servicegateway.tuxedo.portCliente.saida.impl.MsgBodyTypeImpl.PortabilidadeOutTOTypeImpl.class), 2, ___uri, ___local, ___qname));
                        return ;
                    case  0 :
                        state = 3;
                        continue outer;
                    case  2 :
                        if (("PortabilidadeOutTO" == ___local)&&("" == ___uri)) {
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
                    case  3 :
                        revertToParentFromEnterAttribute(___uri, ___local, ___qname);
                        return ;
                    case  1 :
                        _PortabilidadeOutTO = ((br.com.indrasistemas.vivoservices.portabilidade.cliente.servicegateway.tuxedo.portCliente.saida.impl.MsgBodyTypeImpl.PortabilidadeOutTOTypeImpl) spawnChildFromEnterAttribute((br.com.indrasistemas.vivoservices.portabilidade.cliente.servicegateway.tuxedo.portCliente.saida.impl.MsgBodyTypeImpl.PortabilidadeOutTOTypeImpl.class), 2, ___uri, ___local, ___qname));
                        return ;
                    case  0 :
                        state = 3;
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
                    case  3 :
                        revertToParentFromLeaveAttribute(___uri, ___local, ___qname);
                        return ;
                    case  1 :
                        _PortabilidadeOutTO = ((br.com.indrasistemas.vivoservices.portabilidade.cliente.servicegateway.tuxedo.portCliente.saida.impl.MsgBodyTypeImpl.PortabilidadeOutTOTypeImpl) spawnChildFromLeaveAttribute((br.com.indrasistemas.vivoservices.portabilidade.cliente.servicegateway.tuxedo.portCliente.saida.impl.MsgBodyTypeImpl.PortabilidadeOutTOTypeImpl.class), 2, ___uri, ___local, ___qname));
                        return ;
                    case  0 :
                        state = 3;
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
                        case  3 :
                            revertToParentFromText(value);
                            return ;
                        case  1 :
                            _PortabilidadeOutTO = ((br.com.indrasistemas.vivoservices.portabilidade.cliente.servicegateway.tuxedo.portCliente.saida.impl.MsgBodyTypeImpl.PortabilidadeOutTOTypeImpl) spawnChildFromText((br.com.indrasistemas.vivoservices.portabilidade.cliente.servicegateway.tuxedo.portCliente.saida.impl.MsgBodyTypeImpl.PortabilidadeOutTOTypeImpl.class), 2, value));
                            return ;
                        case  0 :
                            state = 3;
                            continue outer;
                    }
                } catch (java.lang.RuntimeException e) {
                    handleUnexpectedTextException(value, e);
                }
                break;
            }
        }

    }

}
