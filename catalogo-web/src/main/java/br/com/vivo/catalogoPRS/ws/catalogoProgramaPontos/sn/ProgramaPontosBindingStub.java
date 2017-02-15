/**
 * ProgramaPontosBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn;

import br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.CabecalhoVivoHeader;
import br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.SecurityHeader;

public class ProgramaPontosBindingStub extends org.apache.axis.client.Stub implements br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.ProgramaPontosPortType {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();
    
    private String user = new String();
    private String password = new String();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[4];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("buscarListaPrecoProdutoPP");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", "buscarListaPrecoProdutoPPRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", ">buscarListaPrecoProdutoPPRequest"), br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.BuscarListaPrecoProdutoPPRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", ">buscarListaPrecoProdutoPPResponse"));
        oper.setReturnClass(br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.BuscarListaPrecoProdutoPPResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", "buscarListaPrecoProdutoPPResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "erroInfo"),
                      "br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.mc.geral.ErroInfo",
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "ErroInfo"), 
                      true
                     ));
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("alterarDispProdutoPP");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", "alterarDispProdutoPPRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", ">alterarDispProdutoPPRequest"), br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.AlterarDispProdutoPPRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", ">alterarDispProdutoPPResponse"));
        oper.setReturnClass(br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.AlterarDispProdutoPPResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", "alterarDispProdutoPPResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "erroInfo"),
                      "br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.mc.geral.ErroInfo",
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "ErroInfo"), 
                      true
                     ));
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("buscarConfigProdutoPP");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", "buscarConfigProdutoPPRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", ">buscarConfigProdutoPPRequest"), br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.BuscarConfigProdutoPPRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", ">buscarConfigProdutoPPResponse"));
        oper.setReturnClass(br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.BuscarConfigProdutoPPResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", "buscarConfigProdutoPPResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "erroInfo"),
                      "br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.mc.geral.ErroInfo",
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "ErroInfo"), 
                      true
                     ));
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("carregarDispProdutoPP");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", "carregarDispProdutoPPRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", ">carregarDispProdutoPPRequest"), br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.CarregarDispProdutoPPRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", ">carregarDispProdutoPPResponse"));
        oper.setReturnClass(br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.CarregarDispProdutoPPResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", "carregarDispProdutoPPResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "erroInfo"),
                      "br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.mc.geral.ErroInfo",
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "ErroInfo"), 
                      true
                     ));
        _operations[3] = oper;

    }

    public ProgramaPontosBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public ProgramaPontosBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public ProgramaPontosBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.2");
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", ">CabecalhoVivo>canalAtendimento");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", ">CabecalhoVivo>codigoFuncionalidade");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", ">CabecalhoVivo>codigoSessao");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", ">CabecalhoVivo>dataTransacao");
            cachedSerQNames.add(qName);
            cls = java.util.Calendar.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", ">CabecalhoVivo>enderecoIP");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", ">CabecalhoVivo>loginUsuario");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", ">CabecalhoVivo>nomeAplicacao");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.mc.geral.CabecalhoVivoNomeAplicacao.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", ">CabecalhoVivo>nomeServico");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", ">CabecalhoVivo>token");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", ">CabecalhoVivo>versao");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", ">ErroInfo>codigo");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", ">ErroInfo>codigoClassificacao");
            cachedSerQNames.add(qName);
            cls = java.math.BigInteger.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", ">ErroInfo>dataTransacao");
            cachedSerQNames.add(qName);
            cls = java.util.Calendar.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", ">ErroInfo>descClassificacao");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", ">ErroInfo>descricao");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "CabecalhoVivo");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.mc.geral.CabecalhoVivo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "ErroInfo");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.mc.geral.ErroInfo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "ErrosInfo");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.mc.geral.ErroInfo[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "ErroInfo");
            qName2 = new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "erroInfo");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", ">>>ResultBuscarPrecoProdutoPP>ListaProduto>Produto");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.ResultBuscarPrecoProdutoPPListaProdutoProduto.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", ">>ParamAlterarDispProdutoPP>inDisponivel");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.ParamAlterarDispProdutoPPInDisponivel.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", ">>ParamAlterarDispProdutoPP>ListaIdProduto");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string");
            qName2 = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", "idProduto");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", ">>ParamAlterarDispProdutoPP>ListaSgAcao");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string");
            qName2 = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", "sgAcao");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", ">>ParamCarregarDispProdutoPP>ListaAcao");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string");
            qName2 = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", "acao");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", ">>ParamCarregarDispProdutoPP>ListaCanalAtendimento");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string");
            qName2 = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", "idCanalAtendimento");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", ">>ParamCarregarDispProdutoPP>ListaOrgVendas");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string");
            qName2 = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", "idOrgVendas");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", ">>ResultBuscarConfigProdutoPP>ListaIdCanal");
            cachedSerQNames.add(qName);
            cls = long[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long");
            qName2 = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", "idCanalAtend");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", ">>ResultBuscarConfigProdutoPP>ListaIdOrgVendas");
            cachedSerQNames.add(qName);
            cls = long[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long");
            qName2 = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", "idOrgVendas");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", ">>ResultBuscarConfigProdutoPP>ListaSgAcao");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string");
            qName2 = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", "sgAcaoMarketing");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", ">>ResultBuscarPrecoProdutoPP>ListaProduto");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.ResultBuscarPrecoProdutoPPListaProdutoProduto[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", ">>>ResultBuscarPrecoProdutoPP>ListaProduto>Produto");
            qName2 = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", "Produto");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", ">alterarDispProdutoPPRequest");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.AlterarDispProdutoPPRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", ">alterarDispProdutoPPResponse");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.AlterarDispProdutoPPResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", ">buscarConfigProdutoPPRequest");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.BuscarConfigProdutoPPRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", ">buscarConfigProdutoPPResponse");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.BuscarConfigProdutoPPResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", ">buscarListaPrecoProdutoPPRequest");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.BuscarListaPrecoProdutoPPRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", ">buscarListaPrecoProdutoPPResponse");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.BuscarListaPrecoProdutoPPResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", ">carregarDispProdutoPPRequest");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.CarregarDispProdutoPPRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", ">carregarDispProdutoPPResponse");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.CarregarDispProdutoPPResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", ">PaginacaoIn");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.PaginacaoIn.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", ">PaginacaoOut");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.PaginacaoOut.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", ">ParamAlterarDispProdutoPP");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.ParamAlterarDispProdutoPP.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", ">ParamBuscarConfigProdutoPP");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.ParamBuscarConfigProdutoPP.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", ">ParamBuscarPrecoProdutoPP");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.ParamBuscarPrecoProdutoPP.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", ">ParamCarregarDispProdutoPP");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.ParamCarregarDispProdutoPP.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", ">ResultBuscarConfigProdutoPP");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.ResultBuscarConfigProdutoPP.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", ">ResultBuscarPrecoProdutoPP");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.ResultBuscarPrecoProdutoPP.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setEncodingStyle(null);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            java.lang.Class sf = (java.lang.Class)
                                 cachedSerFactories.get(i);
                            java.lang.Class df = (java.lang.Class)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
                                 cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            
            SecurityHeader securityHeader = new SecurityHeader();
            CabecalhoVivoHeader cabecalhoVivoHeader = new CabecalhoVivoHeader();
            
            _call.addHeader(cabecalhoVivoHeader.getCabecalhoVivoHeader());
			_call.addHeader(securityHeader.getSecurityHeader(user,password));
            
            return _call;
        }
        catch (java.lang.Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    public br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.BuscarListaPrecoProdutoPPResponse buscarListaPrecoProdutoPP(br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.BuscarListaPrecoProdutoPPRequest buscarListaPrecoProdutoPPRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.mc.geral.ErroInfo {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        
        this.user = username;
		this.password = password;
        
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "buscarListaPrecoProdutoPP"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {buscarListaPrecoProdutoPPRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.BuscarListaPrecoProdutoPPResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.BuscarListaPrecoProdutoPPResponse) org.apache.axis.utils.JavaUtils.convert(_resp, br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.BuscarListaPrecoProdutoPPResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.mc.geral.ErroInfo) {
              throw (br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.mc.geral.ErroInfo) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.AlterarDispProdutoPPResponse alterarDispProdutoPP(br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.AlterarDispProdutoPPRequest alterarDispProdutoPPRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.mc.geral.ErroInfo {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        this.user = username;
		this.password = password;
        
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "alterarDispProdutoPP"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {alterarDispProdutoPPRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.AlterarDispProdutoPPResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.AlterarDispProdutoPPResponse) org.apache.axis.utils.JavaUtils.convert(_resp, br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.AlterarDispProdutoPPResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.mc.geral.ErroInfo) {
              throw (br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.mc.geral.ErroInfo) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.BuscarConfigProdutoPPResponse buscarConfigProdutoPP(br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.BuscarConfigProdutoPPRequest buscarConfigProdutoPPRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.mc.geral.ErroInfo {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        this.user = username;
		this.password = password;
        
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "buscarConfigProdutoPP"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {buscarConfigProdutoPPRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.BuscarConfigProdutoPPResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.BuscarConfigProdutoPPResponse) org.apache.axis.utils.JavaUtils.convert(_resp, br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.BuscarConfigProdutoPPResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.mc.geral.ErroInfo) {
              throw (br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.mc.geral.ErroInfo) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.CarregarDispProdutoPPResponse carregarDispProdutoPP(br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.CarregarDispProdutoPPRequest carregarDispProdutoPPRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.mc.geral.ErroInfo {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        
        this.user = username;
		this.password = password;
        
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "carregarDispProdutoPP"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {carregarDispProdutoPPRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.CarregarDispProdutoPPResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.CarregarDispProdutoPPResponse) org.apache.axis.utils.JavaUtils.convert(_resp, br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.CarregarDispProdutoPPResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.mc.geral.ErroInfo) {
              throw (br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.mc.geral.ErroInfo) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
