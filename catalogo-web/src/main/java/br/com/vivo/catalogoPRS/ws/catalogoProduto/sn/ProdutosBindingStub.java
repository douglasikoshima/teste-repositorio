/**
 * ProdutosBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoProduto.sn;

import br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.CabecalhoVivoHeader;
import br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.SecurityHeader;

public class ProdutosBindingStub extends org.apache.axis.client.Stub implements br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ProdutosPortType {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    private String user = new String();
    private String password = new String();
    
    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[10];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("atualizaCorProdutos");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "atualizaCorProdutosRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">atualizaCorProdutosRequest"), br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.AtualizaCorProdutosRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyType"));
        oper.setReturnClass(java.lang.Object.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "atualizaCorProdutosResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "erroInfo"),
                      "br.com.vivo.catalogoPRS.ws.catalogoProduto.mc.geral.ErroInfo",
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "ErroInfo"), 
                      true
                     ));
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("buscarListaCoresPorModelo");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "buscarListaCoresPorModeloRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">buscarListaCoresPorModeloRequest"), br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.BuscarListaCoresPorModeloRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">buscarListaCoresPorModeloResponse"));
        oper.setReturnClass(br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.BuscarListaCoresPorModeloResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "buscarListaCoresPorModeloResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "erroInfo"),
                      "br.com.vivo.catalogoPRS.ws.catalogoProduto.mc.geral.ErroInfo",
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "ErroInfo"), 
                      true
                     ));
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("buscarListaProdutoPorModelo");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "buscarListaProdutoPorModeloRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">buscarListaProdutoPorModeloRequest"), br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.BuscarListaProdutoPorModeloRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">buscarListaProdutoPorModeloResponse"));
        oper.setReturnClass(br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.BuscarListaProdutoPorModeloResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "buscarListaProdutoPorModeloResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "erroInfo"),
                      "br.com.vivo.catalogoPRS.ws.catalogoProduto.mc.geral.ErroInfo",
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "ErroInfo"), 
                      true
                     ));
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("buscarListaProduto");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "buscarListaProdutoRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">buscarListaProdutoRequest"), br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.BuscarListaProdutoRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">buscarListaProdutoResponse"));
        oper.setReturnClass(br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.BuscarListaProdutoResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "buscarListaProdutoResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "erroInfo"),
                      "br.com.vivo.catalogoPRS.ws.catalogoProduto.mc.geral.ErroInfo",
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "ErroInfo"), 
                      true
                     ));
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("associarProdModeloHabilitarVariaveis");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "associarProdModeloHabilitarVariaveisRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">associarProdModeloHabilitarVariaveisRequest"), br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.AssociarProdModeloHabilitarVariaveisRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">associarProdModeloHabilitarVariaveisResponse"));
        oper.setReturnClass(br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.AssociarProdModeloHabilitarVariaveisResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "associarProdModeloHabilitarVariaveisResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "erroInfo"),
                      "br.com.vivo.catalogoPRS.ws.catalogoProduto.mc.geral.ErroInfo",
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "ErroInfo"), 
                      true
                     ));
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("buscarListaProdutoSemCodigoAssociado");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "buscarListaProdutoSemCodigoAssociadoRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">buscarListaProdutoSemCodigoAssociadoRequest"), br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.BuscarListaProdutoSemCodigoAssociadoRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">buscarListaProdutoSemCodigoAssociadoResponse"));
        oper.setReturnClass(br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.BuscarListaProdutoSemCodigoAssociadoResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "buscarListaProdutoSemCodigoAssociadoResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "erroInfo"),
                      "br.com.vivo.catalogoPRS.ws.catalogoProduto.mc.geral.ErroInfo",
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "ErroInfo"), 
                      true
                     ));
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("alterarDescricaoCatalogo");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "alterarDescricaoCatalogoRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">alterarDescricaoCatalogoRequest"), br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.AlterarDescricaoCatalogoRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyType"));
        oper.setReturnClass(java.lang.Object.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "alterarDescricaoCatalogoResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "erroInfo"),
                      "br.com.vivo.catalogoPRS.ws.catalogoProduto.mc.geral.ErroInfo",
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "ErroInfo"), 
                      true
                     ));
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("disponibilizarProduto");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "disponibilizarProdutoRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">disponibilizarProdutoRequest"), br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.DisponibilizarProdutoRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">disponibilizarProdutoResponse"));
        oper.setReturnClass(br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.DisponibilizarProdutoResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "disponibilizarProdutoResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "erroInfo"),
                      "br.com.vivo.catalogoPRS.ws.catalogoProduto.mc.geral.ErroInfo",
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "ErroInfo"), 
                      true
                     ));
        _operations[7] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("desassociarProdModDesabilitarVariaveis");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "desassociarProdModDesabilitarVariaveisRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">desassociarProdModDesabilitarVariaveisRequest"), br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.DesassociarProdModDesabilitarVariaveisRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">desassociarProdModDesabilitarVariaveisResponse"));
        oper.setReturnClass(br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.DesassociarProdModDesabilitarVariaveisResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "desassociarProdModDesabilitarVariaveisResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "erroInfo"),
                      "br.com.vivo.catalogoPRS.ws.catalogoProduto.mc.geral.ErroInfo",
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "ErroInfo"), 
                      true
                     ));
        _operations[8] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("buscarQuantidadeModeloPorProduto");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "buscarQuantidadeModeloPorProdutoRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">buscarQuantidadeModeloPorProdutoRequest"), br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.BuscarQuantidadeModeloPorProdutoRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">buscarQuantidadeModeloPorProdutoResponse"));
        oper.setReturnClass(br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.BuscarQuantidadeModeloPorProdutoResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "buscarQuantidadeModeloPorProdutoResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "erroInfo"),
                      "br.com.vivo.catalogoPRS.ws.catalogoProduto.mc.geral.ErroInfo",
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "ErroInfo"), 
                      true
                     ));
        _operations[9] = oper;

    }

    public ProdutosBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public ProdutosBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public ProdutosBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
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
            cls = br.com.vivo.catalogoPRS.ws.catalogoProduto.mc.geral.CabecalhoVivoNomeAplicacao.class;
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
            cls = br.com.vivo.catalogoPRS.ws.catalogoProduto.mc.geral.CabecalhoVivo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "ErroInfo");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProduto.mc.geral.ErroInfo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "ErrosInfo");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProduto.mc.geral.ErroInfo[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "ErroInfo");
            qName2 = new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "erroInfo");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">>>>>ResultadoBuscarListaProduto>ListaProduto>Produto>ListaSistemaProduto>SistemaProduto");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ResultadoBuscarListaProdutoListaProdutoProdutoListaSistemaProdutoSistemaProduto.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">>>>>ResultadoBuscarListaProdutoPorModelo>ListaProduto>Produto>ListaSistemaProduto>SistemaProduto");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ResultadoBuscarListaProdutoPorModeloListaProdutoProdutoListaSistemaProdutoSistemaProduto.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">>>>>ResultadoBuscarListaProdutoSemCodigoAssociado>ListaProduto>Produto>ListaSistemaProduto>SistemaProduto");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ResultadoBuscarListaProdutoSemCodigoAssociadoListaProdutoProdutoListaSistemaProdutoSistemaProduto.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">>>>ResultadoBuscarListaProduto>ListaProduto>Produto>ListaSistemaProduto");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ResultadoBuscarListaProdutoListaProdutoProdutoListaSistemaProdutoSistemaProduto[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">>>>>ResultadoBuscarListaProduto>ListaProduto>Produto>ListaSistemaProduto>SistemaProduto");
            qName2 = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "SistemaProduto");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">>>>ResultadoBuscarListaProdutoPorModelo>ListaProduto>Produto>ListaSistemaProduto");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ResultadoBuscarListaProdutoPorModeloListaProdutoProdutoListaSistemaProdutoSistemaProduto[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">>>>>ResultadoBuscarListaProdutoPorModelo>ListaProduto>Produto>ListaSistemaProduto>SistemaProduto");
            qName2 = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "SistemaProduto");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">>>>ResultadoBuscarListaProdutoSemCodigoAssociado>ListaProduto>Produto>ListaSistemaProduto");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ResultadoBuscarListaProdutoSemCodigoAssociadoListaProdutoProdutoListaSistemaProdutoSistemaProduto[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">>>>>ResultadoBuscarListaProdutoSemCodigoAssociado>ListaProduto>Produto>ListaSistemaProduto>SistemaProduto");
            qName2 = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "SistemaProduto");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">>>ParametrosAssociaProdutoModelo>ProdutoModeloAssociacao>ListaProdutoIn");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosAssociaProdutoModeloProdutoModeloAssociacaoListaProdutoIn.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">>>ParametrosDesabilitarVariaveis>ListaDesassociaProdutoModelo>DesassociacaoProdutoModelo");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosDesabilitarVariaveisListaDesassociaProdutoModeloDesassociacaoProdutoModelo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">>>ParametrosDesassociarProdutoModelo>ListaProdutoModeloDesassociacao>ProdutoModeloDesassociacao");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosDesassociarProdutoModeloListaProdutoModeloDesassociacaoProdutoModeloDesassociacao.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">>>ParametrosHabilitarVariaveis>ListaAssociaProdutoModelo>HabilitaVariaveis");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosHabilitarVariaveisListaAssociaProdutoModeloHabilitaVariaveis.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">>>ResultadoBuscarListaCoresPorModelo>ListaCores>Cor");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ResultadoBuscarListaCoresPorModeloListaCoresCor.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">>>ResultadoBuscarListaProduto>ListaProduto>Produto");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ResultadoBuscarListaProdutoListaProdutoProduto.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">>>ResultadoBuscarListaProdutoPorModelo>ListaProduto>Produto");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ResultadoBuscarListaProdutoPorModeloListaProdutoProduto.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">>>ResultadoBuscarListaProdutoSemCodigoAssociado>ListaProduto>Produto");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ResultadoBuscarListaProdutoSemCodigoAssociadoListaProdutoProduto.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">>ParametrosAlterarDescricaoCatalogo>DescricaoCatalogo");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosAlterarDescricaoCatalogoDescricaoCatalogo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">>ParametrosAssociaProdutoModelo>ProdutoModeloAssociacao");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosAssociaProdutoModeloProdutoModeloAssociacaoListaProdutoIn[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">>>ParametrosAssociaProdutoModelo>ProdutoModeloAssociacao>ListaProdutoIn");
            qName2 = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "ListaProdutoIn");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">>ParametrosAtualizaCorProdutos>ListaProdutos");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string");
            qName2 = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "idProduto");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">>ParametrosDesabilitarVariaveis>ListaDesassociaProdutoModelo");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosDesabilitarVariaveisListaDesassociaProdutoModeloDesassociacaoProdutoModelo[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">>>ParametrosDesabilitarVariaveis>ListaDesassociaProdutoModelo>DesassociacaoProdutoModelo");
            qName2 = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "DesassociacaoProdutoModelo");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">>ParametrosDesassociarProdutoModelo>ListaProdutoModeloDesassociacao");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosDesassociarProdutoModeloListaProdutoModeloDesassociacaoProdutoModeloDesassociacao[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">>>ParametrosDesassociarProdutoModelo>ListaProdutoModeloDesassociacao>ProdutoModeloDesassociacao");
            qName2 = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "ProdutoModeloDesassociacao");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">>ParametrosDisponibilizarProduto>ParametrosDispProduto");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosDisponibilizarProdutoParametrosDispProduto.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">>ParametrosHabilitarVariaveis>ListaAssociaProdutoModelo");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosHabilitarVariaveisListaAssociaProdutoModeloHabilitaVariaveis[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">>>ParametrosHabilitarVariaveis>ListaAssociaProdutoModelo>HabilitaVariaveis");
            qName2 = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "HabilitaVariaveis");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">>ResultadoBuscarListaCoresPorModelo>ListaCores");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ResultadoBuscarListaCoresPorModeloListaCoresCor[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">>>ResultadoBuscarListaCoresPorModelo>ListaCores>Cor");
            qName2 = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "Cor");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">>ResultadoBuscarListaProduto>ListaProduto");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ResultadoBuscarListaProdutoListaProdutoProduto[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">>>ResultadoBuscarListaProduto>ListaProduto>Produto");
            qName2 = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "Produto");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">>ResultadoBuscarListaProdutoPorModelo>ListaProduto");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ResultadoBuscarListaProdutoPorModeloListaProdutoProduto[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">>>ResultadoBuscarListaProdutoPorModelo>ListaProduto>Produto");
            qName2 = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "Produto");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">>ResultadoBuscarListaProdutoSemCodigoAssociado>ListaProduto");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ResultadoBuscarListaProdutoSemCodigoAssociadoListaProdutoProduto[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">>>ResultadoBuscarListaProdutoSemCodigoAssociado>ListaProduto>Produto");
            qName2 = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "Produto");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">alterarDescricaoCatalogoRequest");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.AlterarDescricaoCatalogoRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">associarProdModeloHabilitarVariaveisRequest");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.AssociarProdModeloHabilitarVariaveisRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">associarProdModeloHabilitarVariaveisResponse");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.AssociarProdModeloHabilitarVariaveisResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">atualizaCorProdutosRequest");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.AtualizaCorProdutosRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">buscarListaCoresPorModeloRequest");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.BuscarListaCoresPorModeloRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">buscarListaCoresPorModeloResponse");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.BuscarListaCoresPorModeloResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">buscarListaProdutoPorModeloRequest");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.BuscarListaProdutoPorModeloRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">buscarListaProdutoPorModeloResponse");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.BuscarListaProdutoPorModeloResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">buscarListaProdutoRequest");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.BuscarListaProdutoRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">buscarListaProdutoResponse");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.BuscarListaProdutoResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">buscarListaProdutoSemCodigoAssociadoRequest");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.BuscarListaProdutoSemCodigoAssociadoRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">buscarListaProdutoSemCodigoAssociadoResponse");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.BuscarListaProdutoSemCodigoAssociadoResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">buscarQuantidadeModeloPorProdutoRequest");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.BuscarQuantidadeModeloPorProdutoRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">buscarQuantidadeModeloPorProdutoResponse");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.BuscarQuantidadeModeloPorProdutoResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">desassociarProdModDesabilitarVariaveisRequest");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.DesassociarProdModDesabilitarVariaveisRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">desassociarProdModDesabilitarVariaveisResponse");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.DesassociarProdModDesabilitarVariaveisResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">disponibilizarProdutoRequest");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.DisponibilizarProdutoRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">disponibilizarProdutoResponse");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.DisponibilizarProdutoResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">PaginacaoIn");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.PaginacaoIn.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">PaginacaoOut");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.PaginacaoOut.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">ParametroBuscarListaCoresPorModelo");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametroBuscarListaCoresPorModelo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">ParametrosAlterarDescricaoCatalogo");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosAlterarDescricaoCatalogo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">ParametrosAssociaProdutoModelo");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosAssociaProdutoModelo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">ParametrosAtualizaCorProdutos");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosAtualizaCorProdutos.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">ParametrosBuscarListaProduto");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosBuscarListaProduto.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">ParametrosBuscarListaProdutoPorModelo");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosBuscarListaProdutoPorModelo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">ParametrosBuscarListaProdutoSemCodigoAssociado");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosBuscarListaProdutoSemCodigoAssociado.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">ParametrosBuscarQuantidadeProdutoPorModelo");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosBuscarQuantidadeProdutoPorModelo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">ParametrosDesabilitarVariaveis");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosDesabilitarVariaveis.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">ParametrosDesassociarProdutoModelo");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosDesassociarProdutoModelo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">ParametrosDisponibilizarProduto");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosDisponibilizarProduto.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">ParametrosHabilitarVariaveis");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosHabilitarVariaveis.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">ParametrosProdutosIn");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosProdutosIn.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">ResultadoBuscarListaCoresPorModelo");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ResultadoBuscarListaCoresPorModelo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">ResultadoBuscarListaProduto");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ResultadoBuscarListaProduto.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">ResultadoBuscarListaProdutoPorModelo");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ResultadoBuscarListaProdutoPorModelo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">ResultadoBuscarListaProdutoSemCodigoAssociado");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ResultadoBuscarListaProdutoSemCodigoAssociado.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">ResultadoBuscarQuantidadeModeloPorProduto");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ResultadoBuscarQuantidadeModeloPorProduto.class;
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

    public java.lang.Object atualizaCorProdutos(br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.AtualizaCorProdutosRequest atualizaCorProdutosRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoProduto.mc.geral.ErroInfo {
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
        _call.setOperationName(new javax.xml.namespace.QName("", "atualizaCorProdutos"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {atualizaCorProdutosRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.Object) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.Object) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.Object.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof br.com.vivo.catalogoPRS.ws.catalogoProduto.mc.geral.ErroInfo) {
              throw (br.com.vivo.catalogoPRS.ws.catalogoProduto.mc.geral.ErroInfo) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.BuscarListaCoresPorModeloResponse buscarListaCoresPorModelo(br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.BuscarListaCoresPorModeloRequest buscarListaCoresPorModeloRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoProduto.mc.geral.ErroInfo {
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
        _call.setOperationName(new javax.xml.namespace.QName("", "buscarListaCoresPorModelo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {buscarListaCoresPorModeloRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.BuscarListaCoresPorModeloResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.BuscarListaCoresPorModeloResponse) org.apache.axis.utils.JavaUtils.convert(_resp, br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.BuscarListaCoresPorModeloResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof br.com.vivo.catalogoPRS.ws.catalogoProduto.mc.geral.ErroInfo) {
              throw (br.com.vivo.catalogoPRS.ws.catalogoProduto.mc.geral.ErroInfo) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.BuscarListaProdutoPorModeloResponse buscarListaProdutoPorModelo(br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.BuscarListaProdutoPorModeloRequest buscarListaProdutoPorModeloRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoProduto.mc.geral.ErroInfo {
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
        _call.setOperationName(new javax.xml.namespace.QName("", "buscarListaProdutoPorModelo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {buscarListaProdutoPorModeloRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.BuscarListaProdutoPorModeloResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.BuscarListaProdutoPorModeloResponse) org.apache.axis.utils.JavaUtils.convert(_resp, br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.BuscarListaProdutoPorModeloResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof br.com.vivo.catalogoPRS.ws.catalogoProduto.mc.geral.ErroInfo) {
              throw (br.com.vivo.catalogoPRS.ws.catalogoProduto.mc.geral.ErroInfo) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.BuscarListaProdutoResponse buscarListaProduto(br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.BuscarListaProdutoRequest buscarListaProdutoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoProduto.mc.geral.ErroInfo {
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
        _call.setOperationName(new javax.xml.namespace.QName("", "buscarListaProduto"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {buscarListaProdutoRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.BuscarListaProdutoResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.BuscarListaProdutoResponse) org.apache.axis.utils.JavaUtils.convert(_resp, br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.BuscarListaProdutoResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof br.com.vivo.catalogoPRS.ws.catalogoProduto.mc.geral.ErroInfo) {
              throw (br.com.vivo.catalogoPRS.ws.catalogoProduto.mc.geral.ErroInfo) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.AssociarProdModeloHabilitarVariaveisResponse associarProdModeloHabilitarVariaveis(br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.AssociarProdModeloHabilitarVariaveisRequest associarProdModeloHabilitarVariaveisRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoProduto.mc.geral.ErroInfo {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        
		this.user = username;
		this.password = password;
		
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "associarProdModeloHabilitarVariaveis"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {associarProdModeloHabilitarVariaveisRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.AssociarProdModeloHabilitarVariaveisResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.AssociarProdModeloHabilitarVariaveisResponse) org.apache.axis.utils.JavaUtils.convert(_resp, br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.AssociarProdModeloHabilitarVariaveisResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof br.com.vivo.catalogoPRS.ws.catalogoProduto.mc.geral.ErroInfo) {
              throw (br.com.vivo.catalogoPRS.ws.catalogoProduto.mc.geral.ErroInfo) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.BuscarListaProdutoSemCodigoAssociadoResponse buscarListaProdutoSemCodigoAssociado(br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.BuscarListaProdutoSemCodigoAssociadoRequest buscarListaProdutoSemCodigoAssociadoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoProduto.mc.geral.ErroInfo {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        
		this.user = username;
		this.password = password;
		
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "buscarListaProdutoSemCodigoAssociado"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {buscarListaProdutoSemCodigoAssociadoRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.BuscarListaProdutoSemCodigoAssociadoResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.BuscarListaProdutoSemCodigoAssociadoResponse) org.apache.axis.utils.JavaUtils.convert(_resp, br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.BuscarListaProdutoSemCodigoAssociadoResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof br.com.vivo.catalogoPRS.ws.catalogoProduto.mc.geral.ErroInfo) {
              throw (br.com.vivo.catalogoPRS.ws.catalogoProduto.mc.geral.ErroInfo) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.Object alterarDescricaoCatalogo(br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.AlterarDescricaoCatalogoRequest alterarDescricaoCatalogoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoProduto.mc.geral.ErroInfo {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        
		this.user = username;
		this.password = password;
		
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "alterarDescricaoCatalogo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {alterarDescricaoCatalogoRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.Object) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.Object) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.Object.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof br.com.vivo.catalogoPRS.ws.catalogoProduto.mc.geral.ErroInfo) {
              throw (br.com.vivo.catalogoPRS.ws.catalogoProduto.mc.geral.ErroInfo) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.DisponibilizarProdutoResponse disponibilizarProduto(br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.DisponibilizarProdutoRequest disponibilizarProdutoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoProduto.mc.geral.ErroInfo {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        
		this.user = username;
		this.password = password;
		
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "disponibilizarProduto"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {disponibilizarProdutoRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.DisponibilizarProdutoResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.DisponibilizarProdutoResponse) org.apache.axis.utils.JavaUtils.convert(_resp, br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.DisponibilizarProdutoResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof br.com.vivo.catalogoPRS.ws.catalogoProduto.mc.geral.ErroInfo) {
              throw (br.com.vivo.catalogoPRS.ws.catalogoProduto.mc.geral.ErroInfo) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.DesassociarProdModDesabilitarVariaveisResponse desassociarProdModDesabilitarVariaveis(br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.DesassociarProdModDesabilitarVariaveisRequest desassociarProdModDesabilitarVariaveisRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoProduto.mc.geral.ErroInfo {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        
		this.user = username;
		this.password = password;
		
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[8]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "desassociarProdModDesabilitarVariaveis"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {desassociarProdModDesabilitarVariaveisRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.DesassociarProdModDesabilitarVariaveisResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.DesassociarProdModDesabilitarVariaveisResponse) org.apache.axis.utils.JavaUtils.convert(_resp, br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.DesassociarProdModDesabilitarVariaveisResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof br.com.vivo.catalogoPRS.ws.catalogoProduto.mc.geral.ErroInfo) {
              throw (br.com.vivo.catalogoPRS.ws.catalogoProduto.mc.geral.ErroInfo) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.BuscarQuantidadeModeloPorProdutoResponse buscarQuantidadeModeloPorProduto(br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.BuscarQuantidadeModeloPorProdutoRequest buscarQuantidadeModeloPorProdutoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoProduto.mc.geral.ErroInfo {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        
		this.user = username;
		this.password = password;
		
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[9]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "buscarQuantidadeModeloPorProduto"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {buscarQuantidadeModeloPorProdutoRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.BuscarQuantidadeModeloPorProdutoResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.BuscarQuantidadeModeloPorProdutoResponse) org.apache.axis.utils.JavaUtils.convert(_resp, br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.BuscarQuantidadeModeloPorProdutoResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof br.com.vivo.catalogoPRS.ws.catalogoProduto.mc.geral.ErroInfo) {
              throw (br.com.vivo.catalogoPRS.ws.catalogoProduto.mc.geral.ErroInfo) axisFaultException.detail;
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
