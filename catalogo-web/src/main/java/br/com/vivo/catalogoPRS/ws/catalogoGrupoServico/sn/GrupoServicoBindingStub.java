/**
 * GrupoServicoBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn;

import br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.CabecalhoVivoHeader;
import br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.SecurityHeader;

public class GrupoServicoBindingStub extends org.apache.axis.client.Stub implements br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.GrupoServicoPortType {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();
    
    private String user = new String();
    private String password = new String();        

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[5];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("criarGrupoServicoCatalogo");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", "criarGrupoServicoCatalogoRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", ">criarGrupoServicoCatalogoRequest"), br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.CriarGrupoServicoCatalogoRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", ">criarGrupoServicoCatalogoResponse"));
        oper.setReturnClass(br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.CriarGrupoServicoCatalogoResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", "criarGrupoServicoCatalogoResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "erroInfo"),
                      "br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.mc.geral.ErroInfo",
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "ErroInfo"), 
                      true
                     ));
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("alterarGrupoServicoCatalogo");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", "alterarGrupoServicoCatalogoRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", ">alterarGrupoServicoCatalogoRequest"), br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.AlterarGrupoServicoCatalogoRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", ">alterarGrupoServicoCatalogoResponse"));
        oper.setReturnClass(br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.AlterarGrupoServicoCatalogoResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", "alterarGrupoServicoCatalogoResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "erroInfo"),
                      "br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.mc.geral.ErroInfo",
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "ErroInfo"), 
                      true
                     ));
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("buscarListaGrupoServico");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", "buscarListaGrupoServicoRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", ">buscarListaGrupoServicoRequest"), br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.BuscarListaGrupoServicoRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", ">buscarListaGrupoServicoResponse"));
        oper.setReturnClass(br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.BuscarListaGrupoServicoResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", "buscarListaGrupoServicoResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "erroInfo"),
                      "br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.mc.geral.ErroInfo",
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "ErroInfo"), 
                      true
                     ));
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("excluirListaGrupoServicoCatalogo");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", "excluirListaGrupoServicoCatalogoRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", ">excluirListaGrupoServicoCatalogoRequest"), br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.ExcluirListaGrupoServicoCatalogoRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", ">excluirListaGrupoServicoCatalogoResponse"));
        oper.setReturnClass(br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.ExcluirListaGrupoServicoCatalogoResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", "excluirListaGrupoServicoCatalogoResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "erroInfo"),
                      "br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.mc.geral.ErroInfo",
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "ErroInfo"), 
                      true
                     ));
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("buscarServicoPorGrupoServicoCatalogo");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", "buscarServicoPorGrupoServicoCatalogoRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", ">buscarServicoPorGrupoServicoCatalogoRequest"), br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.BuscarServicoPorGrupoServicoCatalogoRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", ">buscarServicoPorGrupoServicoCatalogoResponse"));
        oper.setReturnClass(br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.BuscarServicoPorGrupoServicoCatalogoResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", "buscarServicoPorGrupoServicoCatalogoResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "erroInfo"),
                      "br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.mc.geral.ErroInfo",
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "ErroInfo"), 
                      true
                     ));
        _operations[4] = oper;

    }

    public GrupoServicoBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public GrupoServicoBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public GrupoServicoBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
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
            cls = br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.mc.geral.CabecalhoVivoNomeAplicacao.class;
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
            cls = br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.mc.geral.CabecalhoVivo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "ErroInfo");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.mc.geral.ErroInfo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", ">>ListaPlanoVariaveis>PlanoVariaveis");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.ListaPlanoVariaveisPlanoVariaveis.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", ">>ListaServico>Servico");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.ListaServicoServico.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", ">>ResultadoListarGrupoServico>Categoria");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.ResultadoListarGrupoServicoCategoria.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", ">alterarGrupoServicoCatalogoRequest");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.AlterarGrupoServicoCatalogoRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", ">alterarGrupoServicoCatalogoResponse");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.AlterarGrupoServicoCatalogoResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", ">buscarListaGrupoServicoRequest");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.BuscarListaGrupoServicoRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", ">buscarListaGrupoServicoResponse");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.BuscarListaGrupoServicoResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", ">buscarServicoPorGrupoServicoCatalogoRequest");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.BuscarServicoPorGrupoServicoCatalogoRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", ">buscarServicoPorGrupoServicoCatalogoResponse");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.BuscarServicoPorGrupoServicoCatalogoResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", ">criarGrupoServicoCatalogoRequest");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.CriarGrupoServicoCatalogoRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", ">criarGrupoServicoCatalogoResponse");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.CriarGrupoServicoCatalogoResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", ">excluirListaGrupoServicoCatalogoRequest");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.ExcluirListaGrupoServicoCatalogoRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", ">excluirListaGrupoServicoCatalogoResponse");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.ExcluirListaGrupoServicoCatalogoResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", ">ListaPlanos");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.Plano[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", "Plano");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", ">ListaPlanoVariaveis");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.ListaPlanoVariaveisPlanoVariaveis[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", ">>ListaPlanoVariaveis>PlanoVariaveis");
            qName2 = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", "PlanoVariaveis");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", ">ListaServico");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.ListaServicoServico[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", ">>ListaServico>Servico");
            qName2 = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", "Servico");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", ">ParametrosAlterarGrupoServicoCatalogo");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.ParametrosAlterarGrupoServicoCatalogo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", ">ParametrosBuscarListaServicoPorGrupoServicoCatalogo");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.ParametrosBuscarListaServicoPorGrupoServicoCatalogo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", ">ParametroscriarGrupoServicoCatalogo");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.ParametroscriarGrupoServicoCatalogo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", ">ParametrosExcluirListaGrupoServicoCatalogo");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.ParametrosExcluirListaGrupoServicoCatalogo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", ">ParametrosListarGrupoServico");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.ParametrosListarGrupoServico.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", ">Plano");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.Plano.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", ">ResultadoAlterarGrupoServicoCatalogo");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.ResultadoAlterarGrupoServicoCatalogo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", ">ResultadoBuscarServicoPorGrupoServicoCatalogo");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.ResultadoBuscarServicoPorGrupoServicoCatalogo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", ">ResultadoCriarGrupoServico");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.ResultadoCriarGrupoServico.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", ">ResultadoListarGrupoServico");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.ResultadoListarGrupoServicoCategoria[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", ">>ResultadoListarGrupoServico>Categoria");
            qName2 = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", "Categoria");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGrupoServico", ">SistemaPlano");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.SistemaPlano.class;
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

    public br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.CriarGrupoServicoCatalogoResponse criarGrupoServicoCatalogo(br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.CriarGrupoServicoCatalogoRequest criarGrupoServicoCatalogoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.mc.geral.ErroInfo {
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
        _call.setOperationName(new javax.xml.namespace.QName("", "criarGrupoServicoCatalogo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {criarGrupoServicoCatalogoRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.CriarGrupoServicoCatalogoResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.CriarGrupoServicoCatalogoResponse) org.apache.axis.utils.JavaUtils.convert(_resp, br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.CriarGrupoServicoCatalogoResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.mc.geral.ErroInfo) {
              throw (br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.mc.geral.ErroInfo) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.AlterarGrupoServicoCatalogoResponse alterarGrupoServicoCatalogo(br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.AlterarGrupoServicoCatalogoRequest alterarGrupoServicoCatalogoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.mc.geral.ErroInfo {
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
        _call.setOperationName(new javax.xml.namespace.QName("", "alterarGrupoServicoCatalogo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {alterarGrupoServicoCatalogoRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.AlterarGrupoServicoCatalogoResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.AlterarGrupoServicoCatalogoResponse) org.apache.axis.utils.JavaUtils.convert(_resp, br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.AlterarGrupoServicoCatalogoResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.mc.geral.ErroInfo) {
              throw (br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.mc.geral.ErroInfo) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.BuscarListaGrupoServicoResponse buscarListaGrupoServico(br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.BuscarListaGrupoServicoRequest buscarListaGrupoServicoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.mc.geral.ErroInfo {
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
        _call.setOperationName(new javax.xml.namespace.QName("", "buscarListaGrupoServico"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {buscarListaGrupoServicoRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.BuscarListaGrupoServicoResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.BuscarListaGrupoServicoResponse) org.apache.axis.utils.JavaUtils.convert(_resp, br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.BuscarListaGrupoServicoResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.mc.geral.ErroInfo) {
              throw (br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.mc.geral.ErroInfo) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.ExcluirListaGrupoServicoCatalogoResponse excluirListaGrupoServicoCatalogo(br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.ExcluirListaGrupoServicoCatalogoRequest excluirListaGrupoServicoCatalogoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.mc.geral.ErroInfo {
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
        _call.setOperationName(new javax.xml.namespace.QName("", "excluirListaGrupoServicoCatalogo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {excluirListaGrupoServicoCatalogoRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.ExcluirListaGrupoServicoCatalogoResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.ExcluirListaGrupoServicoCatalogoResponse) org.apache.axis.utils.JavaUtils.convert(_resp, br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.ExcluirListaGrupoServicoCatalogoResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.mc.geral.ErroInfo) {
              throw (br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.mc.geral.ErroInfo) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.BuscarServicoPorGrupoServicoCatalogoResponse buscarServicoPorGrupoServicoCatalogo(br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.BuscarServicoPorGrupoServicoCatalogoRequest buscarServicoPorGrupoServicoCatalogoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.mc.geral.ErroInfo {
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
        _call.setOperationName(new javax.xml.namespace.QName("", "buscarServicoPorGrupoServicoCatalogo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {buscarServicoPorGrupoServicoCatalogoRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.BuscarServicoPorGrupoServicoCatalogoResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.BuscarServicoPorGrupoServicoCatalogoResponse) org.apache.axis.utils.JavaUtils.convert(_resp, br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.BuscarServicoPorGrupoServicoCatalogoResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.mc.geral.ErroInfo) {
              throw (br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.mc.geral.ErroInfo) axisFaultException.detail;
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
