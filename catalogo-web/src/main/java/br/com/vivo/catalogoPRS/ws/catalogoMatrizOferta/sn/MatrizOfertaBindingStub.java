/**
 * MatrizOfertaBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn;

import br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.CabecalhoVivoHeader;
import br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.SecurityHeader;

public class MatrizOfertaBindingStub extends org.apache.axis.client.Stub implements br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.MatrizOfertaPortType {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();
    
    private String user = new String();
    private String password = new String();  

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[25];
        _initOperationDesc1();
        _initOperationDesc2();
        _initOperationDesc3();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obterExistenciaItemPreco");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "obterExistenciaItemPrecoRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">obterExistenciaItemPrecoRequest"), br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ObterExistenciaItemPrecoRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">obterExistenciaItemPrecoResponse"));
        oper.setReturnClass(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ObterExistenciaItemPrecoResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "obterExistenciaItemPrecoResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "erroInfo"),
                      "br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo",
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "ErroInfo"), 
                      true
                     ));
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obterVLAVistaVL10Vezes");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "obterVLAVistaVL10VezesRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">obterVLAVistaVL10VezesRequest"), br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ObterVLAVistaVL10VezesRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">obterVLAVistaVL10VezesResponse"));
        oper.setReturnClass(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ObterVLAVistaVL10VezesResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "obterVLAVistaVL10VezesResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "erroInfo"),
                      "br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo",
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "ErroInfo"), 
                      true
                     ));
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("alterarItemData");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "alterarItemDataRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">alterarItemDataRequest"), br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.AlterarItemDataRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">alterarItemDataResponse"));
        oper.setReturnClass(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.AlterarItemDataResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "alterarItemDataResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "erroInfo"),
                      "br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo",
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "ErroInfo"), 
                      true
                     ));
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("alterarItemPreco");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "alterarItemPrecoRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">alterarItemPrecoRequest"), br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.AlterarItemPrecoRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">alterarItemPrecoResponse"));
        oper.setReturnClass(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.AlterarItemPrecoResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "alterarItemPrecoResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "erroInfo"),
                      "br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo",
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "ErroInfo"), 
                      true
                     ));
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("buscarCriticasImportacao");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "buscarCriticasImportacaoRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">buscarCriticasImportacaoRequest"), br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarCriticasImportacaoRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">buscarCriticasImportacaoResponse"));
        oper.setReturnClass(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarCriticasImportacaoResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "buscarCriticasImportacaoResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "erroInfo"),
                      "br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo",
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "ErroInfo"), 
                      true
                     ));
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("exportarMatrizOfertaArquivoItem");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "exportarMatrizOfertaArquivoItemRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">exportarMatrizOfertaArquivoItemRequest"), br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ExportarMatrizOfertaArquivoItemRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">exportarMatrizOfertaArquivoItemResponse"));
        oper.setReturnClass(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ExportarMatrizOfertaArquivoItemResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "exportarMatrizOfertaArquivoItemResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "erroInfo"),
                      "br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo",
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "ErroInfo"), 
                      true
                     ));
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("buscarListaDetalhesImportacao");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "buscarListaDetalhesImportacaoRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">buscarListaDetalhesImportacaoRequest"), br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaDetalhesImportacaoRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">buscarListaDetalhesImportacaoResponse"));
        oper.setReturnClass(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaDetalhesImportacaoResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "buscarListaDetalhesImportacaoResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "erroInfo"),
                      "br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo",
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "ErroInfo"), 
                      true
                     ));
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("buscarListaResultadoImportacao");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "buscarListaResultadoImportacaoRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">buscarListaResultadoImportacaoRequest"), br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaResultadoImportacaoRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">buscarListaResultadoImportacaoResponse"));
        oper.setReturnClass(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaResultadoImportacaoResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "buscarListaResultadoImportacaoResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "erroInfo"),
                      "br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo",
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "ErroInfo"), 
                      true
                     ));
        _operations[7] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("buscarListaNmMatriz");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "buscarListaNmMatrizRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">buscarListaNmMatrizRequest"), br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaNmMatrizRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">buscarListaNmMatrizResponse"));
        oper.setReturnClass(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaNmMatrizResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "buscarListaNmMatrizResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "erroInfo"),
                      "br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo",
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "ErroInfo"), 
                      true
                     ));
        _operations[8] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("buscarListaStatusImportacao");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "buscarListaStatusImportacaoRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">buscarListaStatusImportacaoRequest"), br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaStatusImportacaoRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">buscarListaStatusImportacaoResponse"));
        oper.setReturnClass(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaStatusImportacaoResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "buscarListaStatusImportacaoResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "erroInfo"),
                      "br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo",
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "ErroInfo"), 
                      true
                     ));
        _operations[9] = oper;

    }

    private static void _initOperationDesc2(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("importarMatrizOfertaArquivo");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "importarMatrizOfertaArquivoRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">importarMatrizOfertaArquivoRequest"), br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ImportarMatrizOfertaArquivoRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">importarMatrizOfertaArquivoResponse"));
        oper.setReturnClass(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ImportarMatrizOfertaArquivoResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "importarMatrizOfertaArquivoResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "erroInfo"),
                      "br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo",
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "ErroInfo"), 
                      true
                     ));
        _operations[10] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("criarCabecalho");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "criarCabecalhoRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">criarCabecalhoRequest"), br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.CriarCabecalhoRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">criarCabecalhoResponse"));
        oper.setReturnClass(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.CriarCabecalhoResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "criarCabecalhoResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "erroInfo"),
                      "br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo",
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "ErroInfo"), 
                      true
                     ));
        _operations[11] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("buscarListaTipoMatriz");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "buscarListaTipoMatrizRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">buscarListaTipoMatrizRequest"), br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaTipoMatrizRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">buscarListaTipoMatrizResponse"));
        oper.setReturnClass(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaTipoMatrizResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "buscarListaTipoMatrizResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "erroInfo"),
                      "br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo",
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "ErroInfo"), 
                      true
                     ));
        _operations[12] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("alterarCabecalho");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "alterarCabecalhoRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">alterarCabecalhoRequest"), br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.AlterarCabecalhoRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">alterarCabecalhoResponse"));
        oper.setReturnClass(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.AlterarCabecalhoResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "alterarCabecalhoResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "erroInfo"),
                      "br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo",
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "ErroInfo"), 
                      true
                     ));
        _operations[13] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("alterarItem");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "alterarItemRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">alterarItemRequest"), br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.AlterarItemRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">alterarItemResponse"));
        oper.setReturnClass(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.AlterarItemResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "alterarItemResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "erroInfo"),
                      "br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo",
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "ErroInfo"), 
                      true
                     ));
        _operations[14] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("buscarListaCabecalho");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "buscarListaCabecalhoRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">buscarListaCabecalhoRequest"), br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaCabecalhoRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">buscarListaCabecalhoResponse"));
        oper.setReturnClass(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaCabecalhoResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "buscarListaCabecalhoResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "erroInfo"),
                      "br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo",
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "ErroInfo"), 
                      true
                     ));
        _operations[15] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("buscarListaVariaveis");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "buscarListaVariaveisRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">buscarListaVariaveisRequest"), br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaVariaveisRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">buscarListaVariaveisResponse"));
        oper.setReturnClass(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaVariaveisResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "buscarListaVariaveisResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "erroInfo"),
                      "br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo",
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "ErroInfo"), 
                      true
                     ));
        _operations[16] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("excluirCabecalho");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "excluirCabecalhoRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">excluirCabecalhoRequest"), br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ExcluirCabecalhoRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">excluirCabecalhoResponse"));
        oper.setReturnClass(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ExcluirCabecalhoResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "excluirCabecalhoResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "erroInfo"),
                      "br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo",
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "ErroInfo"), 
                      true
                     ));
        _operations[17] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("excluirVariaveis");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "excluirVariaveisRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">excluirVariaveisRequest"), br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ExcluirVariaveisRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">excluirVariaveisResponse"));
        oper.setReturnClass(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ExcluirVariaveisResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "excluirVariaveisResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "erroInfo"),
                      "br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo",
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "ErroInfo"), 
                      true
                     ));
        _operations[18] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("associarVariaveis");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "associarVariaveisRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">associarVariaveisRequest"), br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.AssociarVariaveisRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">associarVariaveisResponse"));
        oper.setReturnClass(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.AssociarVariaveisResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "associarVariaveisResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "erroInfo"),
                      "br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo",
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "ErroInfo"), 
                      true
                     ));
        _operations[19] = oper;

    }

    private static void _initOperationDesc3(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("buscarListaItens");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "buscarListaItensRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">buscarListaItensRequest"), br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaItensRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">buscarListaItensResponse"));
        oper.setReturnClass(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaItensResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "buscarListaItensResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "erroInfo"),
                      "br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo",
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "ErroInfo"), 
                      true
                     ));
        _operations[20] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("buscarListaNmOfSAPPorMatriz");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "buscarListaNmOfSAPPorMatrizRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">buscarListaNmOfSAPPorMatrizRequest"), br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaNmOfSAPPorMatrizRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">buscarListaNmOfSAPPorMatrizResponse"));
        oper.setReturnClass(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaNmOfSAPPorMatrizResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "buscarListaNmOfSAPPorMatrizResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "erroInfo"),
                      "br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo",
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "ErroInfo"), 
                      true
                     ));
        _operations[21] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("importarMatrizOfertaTipoContrato");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "importarMatrizOfertaTipoContratoRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">importarMatrizOfertaTipoContratoRequest"), br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ImportarMatrizOfertaTipoContratoRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">importarMatrizOfertaTipoContratoResponse"));
        oper.setReturnClass(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ImportarMatrizOfertaTipoContratoResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "importarMatrizOfertaTipoContratoResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "erroInfo"),
                      "br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo",
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "ErroInfo"), 
                      true
                     ));
        _operations[22] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("buscarIdMatrizOfertaArquivo");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "buscarIdMatrizOfertaArquivoRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">buscarIdMatrizOfertaArquivoRequest"), br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarIdMatrizOfertaArquivoRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">buscarIdMatrizOfertaArquivoResponse"));
        oper.setReturnClass(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarIdMatrizOfertaArquivoResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "buscarIdMatrizOfertaArquivoResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "erroInfo"),
                      "br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo",
                      new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "ErroInfo"), 
                      true
                     ));
        _operations[23] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("processarMatrizOfertaArquivo");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "processarMatrizOfertaArquivoRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">processarMatrizOfertaArquivoRequest"), br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ProcessarMatrizOfertaArquivoRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">processarMatrizOfertaArquivoResponse"));
        oper.setReturnClass(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ProcessarMatrizOfertaArquivoResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "processarMatrizOfertaArquivoResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[24] = oper;

    }

    public MatrizOfertaBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public MatrizOfertaBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public MatrizOfertaBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
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
        addBindings0();
        addBindings1();
    }

    private void addBindings0() {
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
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.CabecalhoVivoNomeAplicacao.class;
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
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.CabecalhoVivo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "ErroInfo");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "ErrosInfo");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "ErroInfo");
            qName2 = new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "erroInfo");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGeral", ">PaginacaoIn");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoIn.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGeral", ">PaginacaoOut");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoOut.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">>>>>>>ResultadoBuscarListaResultadoImportacao>ListaResultadoImportacao>ResultadoImportacao>ListaIdOrganizacaoVendaComDDD>OrganizacaoVenda>ListaDDD>Ddd");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoImportacaoListaIdOrganizacaoVendaComDDDOrganizacaoVendaListaDDDDdd.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">>>>>>ResultadoBuscarListaResultadoImportacao>ListaResultadoImportacao>ResultadoImportacao>ListaIdOrganizacaoVendaComDDD>OrganizacaoVenda>ListaDDD");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoImportacaoListaIdOrganizacaoVendaComDDDOrganizacaoVendaListaDDDDdd[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">>>>>>>ResultadoBuscarListaResultadoImportacao>ListaResultadoImportacao>ResultadoImportacao>ListaIdOrganizacaoVendaComDDD>OrganizacaoVenda>ListaDDD>Ddd");
            qName2 = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "Ddd");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">>>>>ResultadoBuscarListaCabecalho>ListaCabecalho>Cabecalho>ListaCanal>Canal");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoBuscarListaCabecalhoListaCabecalhoCabecalhoListaCanalCanal.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">>>>>ResultadoBuscarListaCabecalho>ListaCabecalho>Cabecalho>ListaPlataforma>Plataforma");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoBuscarListaCabecalhoListaCabecalhoCabecalhoListaPlataformaPlataforma.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">>>>>ResultadoBuscarListaCabecalho>ListaCabecalho>Cabecalho>ListaTipoOperacao>TipoOperacao");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoBuscarListaCabecalhoListaCabecalhoCabecalhoListaTipoOperacaoTipoOperacao.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">>>>>ResultadoBuscarListaResultadoImportacao>ListaResultadoImportacao>ResultadoImportacao>ListaIdOrganizacaoVendaComDDD>OrganizacaoVenda");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoImportacaoListaIdOrganizacaoVendaComDDDOrganizacaoVenda.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">>>>ResultadoBuscarListaCabecalho>ListaCabecalho>Cabecalho>ListaCanal");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoBuscarListaCabecalhoListaCabecalhoCabecalhoListaCanalCanal[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">>>>>ResultadoBuscarListaCabecalho>ListaCabecalho>Cabecalho>ListaCanal>Canal");
            qName2 = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "Canal");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">>>>ResultadoBuscarListaCabecalho>ListaCabecalho>Cabecalho>ListaPlataforma");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoBuscarListaCabecalhoListaCabecalhoCabecalhoListaPlataformaPlataforma[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">>>>>ResultadoBuscarListaCabecalho>ListaCabecalho>Cabecalho>ListaPlataforma>Plataforma");
            qName2 = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "Plataforma");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">>>>ResultadoBuscarListaCabecalho>ListaCabecalho>Cabecalho>ListaTipoOperacao");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoBuscarListaCabecalhoListaCabecalhoCabecalhoListaTipoOperacaoTipoOperacao[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">>>>>ResultadoBuscarListaCabecalho>ListaCabecalho>Cabecalho>ListaTipoOperacao>TipoOperacao");
            qName2 = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "TipoOperacao");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">>>>ResultadoBuscarListaResultadoImportacao>ListaResultadoImportacao>ResultadoImportacao>ListaIdOrganizacaoVendaComDDD");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoImportacaoListaIdOrganizacaoVendaComDDDOrganizacaoVenda[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">>>>>ResultadoBuscarListaResultadoImportacao>ListaResultadoImportacao>ResultadoImportacao>ListaIdOrganizacaoVendaComDDD>OrganizacaoVenda");
            qName2 = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "OrganizacaoVenda");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">>>ResultadoBuscarCriticasImportacao>ListaCriticasImportacao>Critica");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoBuscarCriticasImportacaoListaCriticasImportacaoCritica.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">>>ResultadoBuscarListaCabecalho>ListaCabecalho>Cabecalho");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoBuscarListaCabecalhoListaCabecalhoCabecalho.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">>>ResultadoBuscarListaDetalhesImportacao>ListaDetalhesImportacao>DetalhesImportacao");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoBuscarListaDetalhesImportacaoListaDetalhesImportacaoDetalhesImportacao.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">>>ResultadoBuscarListaItens>ListaItens>Itens");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoBuscarListaItensListaItensItens.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">>>ResultadoBuscarListaResultadoImportacao>ListaResultadoImportacao>ResultadoImportacao");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoImportacaoResultadoImportacao.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">>>ResultadoBuscarListaTipoMatriz>ResultadoListaTipoMatriz>TipoMatriz");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoBuscarListaTipoMatrizResultadoListaTipoMatrizTipoMatriz.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">>>ResultadoBuscarListaVariaveis>ListaVariaveis>Variaveis");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoBuscarListaVariaveisListaVariaveisVariaveis.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">>>ResultadoExportarMatrizOfertaArquivoItem>ExportarMatrizOfertaArquivoItem>MatrizOfertaArquivoItem");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoExportarMatrizOfertaArquivoItemExportarMatrizOfertaArquivoItemMatrizOfertaArquivoItem.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">>ParametrosAlterarCabecalho>ListaIdCanal");
            cachedSerQNames.add(qName);
            cls = long[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long");
            qName2 = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "idCanal");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">>ParametrosAlterarCabecalho>ListaIdPlataforma");
            cachedSerQNames.add(qName);
            cls = long[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long");
            qName2 = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "idPlataforma");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">>ParametrosAlterarCabecalho>ListaIdTipoOperacao");
            cachedSerQNames.add(qName);
            cls = long[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long");
            qName2 = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "idTipoOperacao");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">>ParametrosBuscarListaItens>listaDDD");
            cachedSerQNames.add(qName);
            cls = long[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long");
            qName2 = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "ddd");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">>ParametrosCriarCabecalho>ListaIdCanal");
            cachedSerQNames.add(qName);
            cls = long[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long");
            qName2 = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "idCanal");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">>ParametrosCriarCabecalho>ListaIdPlataforma");
            cachedSerQNames.add(qName);
            cls = long[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long");
            qName2 = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "idPlataforma");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">>ParametrosCriarCabecalho>ListaIdTipoOperacao");
            cachedSerQNames.add(qName);
            cls = long[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long");
            qName2 = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "idTipoOperacao");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">>ResultadoBuscarCriticasImportacao>ListaCriticasImportacao");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoBuscarCriticasImportacaoListaCriticasImportacaoCritica[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">>>ResultadoBuscarCriticasImportacao>ListaCriticasImportacao>Critica");
            qName2 = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "Critica");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">>ResultadoBuscarListaCabecalho>ListaCabecalho");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoBuscarListaCabecalhoListaCabecalhoCabecalho[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">>>ResultadoBuscarListaCabecalho>ListaCabecalho>Cabecalho");
            qName2 = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "Cabecalho");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">>ResultadoBuscarListaDetalhesImportacao>ListaDetalhesImportacao");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoBuscarListaDetalhesImportacaoListaDetalhesImportacaoDetalhesImportacao[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">>>ResultadoBuscarListaDetalhesImportacao>ListaDetalhesImportacao>DetalhesImportacao");
            qName2 = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "DetalhesImportacao");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">>ResultadoBuscarListaItens>ListaItens");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoBuscarListaItensListaItensItens[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">>>ResultadoBuscarListaItens>ListaItens>Itens");
            qName2 = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "Itens");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">>ResultadoBuscarListaNmOfSAPPorMatriz>OfertaSAP");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoBuscarListaNmOfSAPPorMatrizOfertaSAP.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">>ResultadoBuscarListaResultadoImportacao>ListaResultadoImportacao");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoImportacaoResultadoImportacao[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">>>ResultadoBuscarListaResultadoImportacao>ListaResultadoImportacao>ResultadoImportacao");
            qName2 = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "ResultadoImportacao");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">>ResultadoBuscarListaStatusImportacao>ListaStatusImportacao");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoBuscarListaStatusImportacaoListaStatusImportacao.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">>ResultadoBuscarListaTipoMatriz>ResultadoListaTipoMatriz");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoBuscarListaTipoMatrizResultadoListaTipoMatrizTipoMatriz[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">>>ResultadoBuscarListaTipoMatriz>ResultadoListaTipoMatriz>TipoMatriz");
            qName2 = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "TipoMatriz");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">>ResultadoBuscarListaVariaveis>ListaVariaveis");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoBuscarListaVariaveisListaVariaveisVariaveis[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">>>ResultadoBuscarListaVariaveis>ListaVariaveis>Variaveis");
            qName2 = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "Variaveis");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">>ResultadoExportarMatrizOfertaArquivoItem>ExportarMatrizOfertaArquivoItem");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoExportarMatrizOfertaArquivoItemExportarMatrizOfertaArquivoItemMatrizOfertaArquivoItem[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">>>ResultadoExportarMatrizOfertaArquivoItem>ExportarMatrizOfertaArquivoItem>MatrizOfertaArquivoItem");
            qName2 = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "MatrizOfertaArquivoItem");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">>ResultadoListaNomeMatriz>ListaNomeMatriz");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoListaNomeMatrizListaNomeMatriz.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">alterarCabecalhoRequest");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.AlterarCabecalhoRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">alterarCabecalhoResponse");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.AlterarCabecalhoResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">alterarItemDataRequest");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.AlterarItemDataRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">alterarItemDataResponse");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.AlterarItemDataResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">alterarItemPrecoRequest");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.AlterarItemPrecoRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">alterarItemPrecoResponse");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.AlterarItemPrecoResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">alterarItemRequest");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.AlterarItemRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">alterarItemResponse");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.AlterarItemResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">associarVariaveisRequest");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.AssociarVariaveisRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">associarVariaveisResponse");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.AssociarVariaveisResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">buscarCriticasImportacaoRequest");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarCriticasImportacaoRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">buscarCriticasImportacaoResponse");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarCriticasImportacaoResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">buscarIdMatrizOfertaArquivoRequest");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarIdMatrizOfertaArquivoRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">buscarIdMatrizOfertaArquivoResponse");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarIdMatrizOfertaArquivoResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">buscarListaCabecalhoRequest");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaCabecalhoRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">buscarListaCabecalhoResponse");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaCabecalhoResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">buscarListaDetalhesImportacaoRequest");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaDetalhesImportacaoRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">buscarListaDetalhesImportacaoResponse");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaDetalhesImportacaoResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">buscarListaItensRequest");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaItensRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">buscarListaItensResponse");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaItensResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">buscarListaNmMatrizRequest");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaNmMatrizRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">buscarListaNmMatrizResponse");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaNmMatrizResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">buscarListaNmOfSAPPorMatrizRequest");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaNmOfSAPPorMatrizRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">buscarListaNmOfSAPPorMatrizResponse");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaNmOfSAPPorMatrizResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">buscarListaResultadoImportacaoRequest");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaResultadoImportacaoRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">buscarListaResultadoImportacaoResponse");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaResultadoImportacaoResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">buscarListaStatusImportacaoRequest");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaStatusImportacaoRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">buscarListaStatusImportacaoResponse");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaStatusImportacaoResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">buscarListaTipoMatrizRequest");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaTipoMatrizRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">buscarListaTipoMatrizResponse");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaTipoMatrizResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">buscarListaVariaveisRequest");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaVariaveisRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">buscarListaVariaveisResponse");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaVariaveisResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">criarCabecalhoRequest");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.CriarCabecalhoRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">criarCabecalhoResponse");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.CriarCabecalhoResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">excluirCabecalhoRequest");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ExcluirCabecalhoRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">excluirCabecalhoResponse");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ExcluirCabecalhoResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">excluirVariaveisRequest");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ExcluirVariaveisRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">excluirVariaveisResponse");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ExcluirVariaveisResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">exportarMatrizOfertaArquivoItemRequest");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ExportarMatrizOfertaArquivoItemRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">exportarMatrizOfertaArquivoItemResponse");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ExportarMatrizOfertaArquivoItemResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">importarMatrizOfertaArquivoRequest");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ImportarMatrizOfertaArquivoRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">importarMatrizOfertaArquivoResponse");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ImportarMatrizOfertaArquivoResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">importarMatrizOfertaTipoContratoRequest");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ImportarMatrizOfertaTipoContratoRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">importarMatrizOfertaTipoContratoResponse");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ImportarMatrizOfertaTipoContratoResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }
    private void addBindings1() {
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
            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">obterExistenciaItemPrecoRequest");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ObterExistenciaItemPrecoRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">obterExistenciaItemPrecoResponse");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ObterExistenciaItemPrecoResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">obterVLAVistaVL10VezesRequest");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ObterVLAVistaVL10VezesRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">obterVLAVistaVL10VezesResponse");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ObterVLAVistaVL10VezesResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">ParametroNomeMatriz");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ParametroNomeMatriz.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">ParametrosAlterarCabecalho");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ParametrosAlterarCabecalho.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">ParametrosAlterarItem");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ParametrosAlterarItem.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">ParametrosAlterarItemData");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ParametrosAlterarItemData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">ParametrosAlterarItemPreco");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ParametrosAlterarItemPreco.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">ParametrosAssociarVariaveis");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ParametrosAssociarVariaveis.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">ParametrosBuscarCriticasImportacao");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ParametrosBuscarCriticasImportacao.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">ParametrosBuscarListaCabecalho");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ParametrosBuscarListaCabecalho.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">ParametrosBuscarListaDetalhesImportacao");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ParametrosBuscarListaDetalhesImportacao.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">ParametrosBuscarListaItens");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ParametrosBuscarListaItens.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">ParametrosBuscarListaNmOfSAPPorMatriz");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ParametrosBuscarListaNmOfSAPPorMatriz.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">ParametrosBuscarListaResultadoImportacao");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ParametrosBuscarListaResultadoImportacao.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">ParametrosBuscarListaVariaveis");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ParametrosBuscarListaVariaveis.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">ParametrosCriarCabecalho");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ParametrosCriarCabecalho.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">ParametrosExcluirCabecalho");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ParametrosExcluirCabecalho.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">ParametrosExcluirVariavel");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ParametrosExcluirVariavel.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">ParametrosExportarMatrizOfertaArquivoItem");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ParametrosExportarMatrizOfertaArquivoItem.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">ParametrosImportarMatrizOfertaArquivo");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ParametrosImportarMatrizOfertaArquivo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">ParametrosImportarMatrizOfertaTipoContrato");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ParametrosImportarMatrizOfertaTipoContrato.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">ParametrosObterExistenciaItemPreco");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ParametrosObterExistenciaItemPreco.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">ParametrosObterVLAVistaVL10Vezes");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ParametrosObterVLAVistaVL10Vezes.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">ParamProcessarMatrizOfertaArquivo");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ParamProcessarMatrizOfertaArquivo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">processarMatrizOfertaArquivoRequest");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ProcessarMatrizOfertaArquivoRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">processarMatrizOfertaArquivoResponse");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ProcessarMatrizOfertaArquivoResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">ResultadoAlterarCabecalho");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoAlterarCabecalho.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">ResultadoAlterarItem");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoAlterarItem.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">ResultadoBuscarCriticasImportacao");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoBuscarCriticasImportacao.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">ResultadoBuscarListaCabecalho");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoBuscarListaCabecalho.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">ResultadoBuscarListaDetalhesImportacao");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoBuscarListaDetalhesImportacao.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">ResultadoBuscarListaItens");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoBuscarListaItens.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">ResultadoBuscarListaNmOfSAPPorMatriz");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoBuscarListaNmOfSAPPorMatrizOfertaSAP[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">>ResultadoBuscarListaNmOfSAPPorMatriz>OfertaSAP");
            qName2 = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "OfertaSAP");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">ResultadoBuscarListaResultadoImportacao");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoBuscarListaResultadoImportacao.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">ResultadoBuscarListaStatusImportacao");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoBuscarListaStatusImportacaoListaStatusImportacao[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">>ResultadoBuscarListaStatusImportacao>ListaStatusImportacao");
            qName2 = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "ListaStatusImportacao");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">ResultadoBuscarListaTipoMatriz");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoBuscarListaTipoMatriz.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">ResultadoBuscarListaVariaveis");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoBuscarListaVariaveis.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">ResultadoCriarCabecalho");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoCriarCabecalho.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">ResultadoExportarMatrizOfertaArquivoItem");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoExportarMatrizOfertaArquivoItem.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">ResultadoImportarMatrizOfertaArquivo");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoImportarMatrizOfertaArquivo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">ResultadoImportarMatrizOfertaTipoContrato");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoImportarMatrizOfertaTipoContrato.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">ResultadoListaNomeMatriz");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoListaNomeMatrizListaNomeMatriz[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">>ResultadoListaNomeMatriz>ListaNomeMatriz");
            qName2 = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "ListaNomeMatriz");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">ResultadoObterExistenciaItemPreco");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoObterExistenciaItemPreco.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">ResultadoObterVLAVistaVL10Vezes");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoObterVLAVistaVL10Vezes.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">ResultBuscarIdMatrizOfertaArquivo");
            cachedSerQNames.add(qName);
            cls = br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultBuscarIdMatrizOfertaArquivo.class;
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

    public br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ObterExistenciaItemPrecoResponse obterExistenciaItemPreco(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ObterExistenciaItemPrecoRequest obterExistenciaItemPrecoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo {
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
        _call.setOperationName(new javax.xml.namespace.QName("", "obterExistenciaItemPreco"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {obterExistenciaItemPrecoRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ObterExistenciaItemPrecoResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ObterExistenciaItemPrecoResponse) org.apache.axis.utils.JavaUtils.convert(_resp, br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ObterExistenciaItemPrecoResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo) {
              throw (br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ObterVLAVistaVL10VezesResponse obterVLAVistaVL10Vezes(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ObterVLAVistaVL10VezesRequest obterVLAVistaVL10VezesRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo {
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
        _call.setOperationName(new javax.xml.namespace.QName("", "obterVLAVistaVL10Vezes"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {obterVLAVistaVL10VezesRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ObterVLAVistaVL10VezesResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ObterVLAVistaVL10VezesResponse) org.apache.axis.utils.JavaUtils.convert(_resp, br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ObterVLAVistaVL10VezesResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo) {
              throw (br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.AlterarItemDataResponse alterarItemData(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.AlterarItemDataRequest alterarItemDataRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo {
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
        _call.setOperationName(new javax.xml.namespace.QName("", "alterarItemData"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {alterarItemDataRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.AlterarItemDataResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.AlterarItemDataResponse) org.apache.axis.utils.JavaUtils.convert(_resp, br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.AlterarItemDataResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo) {
              throw (br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.AlterarItemPrecoResponse alterarItemPreco(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.AlterarItemPrecoRequest alterarItemPrecoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo {
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
        _call.setOperationName(new javax.xml.namespace.QName("", "alterarItemPreco"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {alterarItemPrecoRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.AlterarItemPrecoResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.AlterarItemPrecoResponse) org.apache.axis.utils.JavaUtils.convert(_resp, br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.AlterarItemPrecoResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo) {
              throw (br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarCriticasImportacaoResponse buscarCriticasImportacao(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarCriticasImportacaoRequest buscarCriticasImportacaoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo {
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
        _call.setOperationName(new javax.xml.namespace.QName("", "buscarCriticasImportacao"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {buscarCriticasImportacaoRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarCriticasImportacaoResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarCriticasImportacaoResponse) org.apache.axis.utils.JavaUtils.convert(_resp, br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarCriticasImportacaoResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo) {
              throw (br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ExportarMatrizOfertaArquivoItemResponse exportarMatrizOfertaArquivoItem(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ExportarMatrizOfertaArquivoItemRequest exportarMatrizOfertaArquivoItemRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo {
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
        _call.setOperationName(new javax.xml.namespace.QName("", "exportarMatrizOfertaArquivoItem"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {exportarMatrizOfertaArquivoItemRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ExportarMatrizOfertaArquivoItemResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ExportarMatrizOfertaArquivoItemResponse) org.apache.axis.utils.JavaUtils.convert(_resp, br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ExportarMatrizOfertaArquivoItemResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo) {
              throw (br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaDetalhesImportacaoResponse buscarListaDetalhesImportacao(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaDetalhesImportacaoRequest buscarListaDetalhesImportacaoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo {
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
        _call.setOperationName(new javax.xml.namespace.QName("", "buscarListaDetalhesImportacao"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {buscarListaDetalhesImportacaoRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaDetalhesImportacaoResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaDetalhesImportacaoResponse) org.apache.axis.utils.JavaUtils.convert(_resp, br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaDetalhesImportacaoResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo) {
              throw (br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaResultadoImportacaoResponse buscarListaResultadoImportacao(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaResultadoImportacaoRequest buscarListaResultadoImportacaoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo {
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
        _call.setOperationName(new javax.xml.namespace.QName("", "buscarListaResultadoImportacao"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {buscarListaResultadoImportacaoRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaResultadoImportacaoResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaResultadoImportacaoResponse) org.apache.axis.utils.JavaUtils.convert(_resp, br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaResultadoImportacaoResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo) {
              throw (br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaNmMatrizResponse buscarListaNmMatriz(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaNmMatrizRequest buscarListaNmMatrizRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo {
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
        _call.setOperationName(new javax.xml.namespace.QName("", "buscarListaNmMatriz"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {buscarListaNmMatrizRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaNmMatrizResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaNmMatrizResponse) org.apache.axis.utils.JavaUtils.convert(_resp, br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaNmMatrizResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo) {
              throw (br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaStatusImportacaoResponse buscarListaStatusImportacao(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaStatusImportacaoRequest buscarListaStatusImportacaoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo {
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
        _call.setOperationName(new javax.xml.namespace.QName("", "buscarListaStatusImportacao"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {buscarListaStatusImportacaoRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaStatusImportacaoResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaStatusImportacaoResponse) org.apache.axis.utils.JavaUtils.convert(_resp, br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaStatusImportacaoResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo) {
              throw (br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ImportarMatrizOfertaArquivoResponse importarMatrizOfertaArquivo(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ImportarMatrizOfertaArquivoRequest importarMatrizOfertaArquivoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        
		this.user = username;
		this.password = password;
		
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[10]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "importarMatrizOfertaArquivo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {importarMatrizOfertaArquivoRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ImportarMatrizOfertaArquivoResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ImportarMatrizOfertaArquivoResponse) org.apache.axis.utils.JavaUtils.convert(_resp, br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ImportarMatrizOfertaArquivoResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo) {
              throw (br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.CriarCabecalhoResponse criarCabecalho(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.CriarCabecalhoRequest criarCabecalhoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        
		this.user = username;
		this.password = password;
		
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[11]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "criarCabecalho"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {criarCabecalhoRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.CriarCabecalhoResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.CriarCabecalhoResponse) org.apache.axis.utils.JavaUtils.convert(_resp, br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.CriarCabecalhoResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo) {
              throw (br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaTipoMatrizResponse buscarListaTipoMatriz(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaTipoMatrizRequest buscarListaTipoMatrizRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        
		this.user = username;
		this.password = password;
		
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[12]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "buscarListaTipoMatriz"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {buscarListaTipoMatrizRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaTipoMatrizResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaTipoMatrizResponse) org.apache.axis.utils.JavaUtils.convert(_resp, br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaTipoMatrizResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo) {
              throw (br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.AlterarCabecalhoResponse alterarCabecalho(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.AlterarCabecalhoRequest alterarCabecalhoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        
		this.user = username;
		this.password = password;
		
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[13]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "alterarCabecalho"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {alterarCabecalhoRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.AlterarCabecalhoResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.AlterarCabecalhoResponse) org.apache.axis.utils.JavaUtils.convert(_resp, br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.AlterarCabecalhoResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo) {
              throw (br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.AlterarItemResponse alterarItem(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.AlterarItemRequest alterarItemRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        
		this.user = username;
		this.password = password;
		
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[14]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "alterarItem"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {alterarItemRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.AlterarItemResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.AlterarItemResponse) org.apache.axis.utils.JavaUtils.convert(_resp, br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.AlterarItemResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo) {
              throw (br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaCabecalhoResponse buscarListaCabecalho(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaCabecalhoRequest buscarListaCabecalhoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        
		this.user = username;
		this.password = password;
		
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[15]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "buscarListaCabecalho"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {buscarListaCabecalhoRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaCabecalhoResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaCabecalhoResponse) org.apache.axis.utils.JavaUtils.convert(_resp, br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaCabecalhoResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo) {
              throw (br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaVariaveisResponse buscarListaVariaveis(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaVariaveisRequest buscarListaVariaveisRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        
		this.user = username;
		this.password = password;
		
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[16]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "buscarListaVariaveis"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {buscarListaVariaveisRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaVariaveisResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaVariaveisResponse) org.apache.axis.utils.JavaUtils.convert(_resp, br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaVariaveisResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo) {
              throw (br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ExcluirCabecalhoResponse excluirCabecalho(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ExcluirCabecalhoRequest excluirCabecalhoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        
		this.user = username;
		this.password = password;
		
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[17]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "excluirCabecalho"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {excluirCabecalhoRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ExcluirCabecalhoResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ExcluirCabecalhoResponse) org.apache.axis.utils.JavaUtils.convert(_resp, br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ExcluirCabecalhoResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo) {
              throw (br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ExcluirVariaveisResponse excluirVariaveis(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ExcluirVariaveisRequest excluirVariaveisRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        
		this.user = username;
		this.password = password;
		
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[18]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "excluirVariaveis"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {excluirVariaveisRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ExcluirVariaveisResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ExcluirVariaveisResponse) org.apache.axis.utils.JavaUtils.convert(_resp, br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ExcluirVariaveisResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo) {
              throw (br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.AssociarVariaveisResponse associarVariaveis(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.AssociarVariaveisRequest associarVariaveisRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        
		this.user = username;
		this.password = password;
		
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[19]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "associarVariaveis"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {associarVariaveisRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.AssociarVariaveisResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.AssociarVariaveisResponse) org.apache.axis.utils.JavaUtils.convert(_resp, br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.AssociarVariaveisResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo) {
              throw (br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaItensResponse buscarListaItens(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaItensRequest buscarListaItensRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        
		this.user = username;
		this.password = password;
		
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[20]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "buscarListaItens"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {buscarListaItensRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaItensResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaItensResponse) org.apache.axis.utils.JavaUtils.convert(_resp, br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaItensResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo) {
              throw (br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaNmOfSAPPorMatrizResponse buscarListaNmOfSAPPorMatriz(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaNmOfSAPPorMatrizRequest buscarListaNmOfSAPPorMatrizRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        
		this.user = username;
		this.password = password;
		
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[21]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "buscarListaNmOfSAPPorMatriz"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {buscarListaNmOfSAPPorMatrizRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaNmOfSAPPorMatrizResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaNmOfSAPPorMatrizResponse) org.apache.axis.utils.JavaUtils.convert(_resp, br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaNmOfSAPPorMatrizResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo) {
              throw (br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ImportarMatrizOfertaTipoContratoResponse importarMatrizOfertaTipoContrato(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ImportarMatrizOfertaTipoContratoRequest importarMatrizOfertaTipoContratoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        
		this.user = username;
		this.password = password;
		
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[22]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "importarMatrizOfertaTipoContrato"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {importarMatrizOfertaTipoContratoRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ImportarMatrizOfertaTipoContratoResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ImportarMatrizOfertaTipoContratoResponse) org.apache.axis.utils.JavaUtils.convert(_resp, br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ImportarMatrizOfertaTipoContratoResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo) {
              throw (br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarIdMatrizOfertaArquivoResponse buscarIdMatrizOfertaArquivo(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarIdMatrizOfertaArquivoRequest buscarIdMatrizOfertaArquivoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        
		this.user = username;
		this.password = password;
		
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[23]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "buscarIdMatrizOfertaArquivo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {buscarIdMatrizOfertaArquivoRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarIdMatrizOfertaArquivoResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarIdMatrizOfertaArquivoResponse) org.apache.axis.utils.JavaUtils.convert(_resp, br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarIdMatrizOfertaArquivoResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo) {
              throw (br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ProcessarMatrizOfertaArquivoResponse processarMatrizOfertaArquivo(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ProcessarMatrizOfertaArquivoRequest processarMatrizOfertaArquivoRequest, String username, String password) throws java.rmi.RemoteException, br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        
		this.user = username;
		this.password = password;
		
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[24]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "processarMatrizOfertaArquivo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {processarMatrizOfertaArquivoRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ProcessarMatrizOfertaArquivoResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ProcessarMatrizOfertaArquivoResponse) org.apache.axis.utils.JavaUtils.convert(_resp, br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ProcessarMatrizOfertaArquivoResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo) {
              throw (br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

}
