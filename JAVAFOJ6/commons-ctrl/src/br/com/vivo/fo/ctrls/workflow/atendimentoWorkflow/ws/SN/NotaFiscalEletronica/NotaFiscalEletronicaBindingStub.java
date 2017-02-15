/**
 * NotaFiscalEletronicaBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.SN.NotaFiscalEletronica;

import org.apache.axis.message.MessageElement;
import org.apache.axis.message.PrefixedQName;
import org.apache.axis.message.SOAPHeaderElement;

public class NotaFiscalEletronicaBindingStub extends
		org.apache.axis.client.Stub
		implements
		br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.SN.NotaFiscalEletronica.NotaFiscalEletronicaPortType {
	private java.util.Vector cachedSerClasses = new java.util.Vector();
	private java.util.Vector cachedSerQNames = new java.util.Vector();
	private java.util.Vector cachedSerFactories = new java.util.Vector();
	private java.util.Vector cachedDeserFactories = new java.util.Vector();

	static org.apache.axis.description.OperationDesc[] _operations;

	static {
		_operations = new org.apache.axis.description.OperationDesc[1];
		_initOperationDesc1();
	}

	private static void _initOperationDesc1() {
		org.apache.axis.description.OperationDesc oper;
		org.apache.axis.description.ParameterDesc param;
		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("consultarInformacoesConfianca");
		param = new org.apache.axis.description.ParameterDesc(
				new javax.xml.namespace.QName(
						"http://www.vivo.com.br/SN/NotaFiscalEletronica",
						"consultarInformacoesConfiancaRequest"),
				org.apache.axis.description.ParameterDesc.IN,
				new javax.xml.namespace.QName(
						"http://www.vivo.com.br/SN/NotaFiscalEletronica",
						"ParametrosConsultarInformacoesConfianca"),
				br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.SN.NotaFiscalEletronica.ParametrosConsultarInformacoesConfianca.class,
				false, false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName(
				"http://www.w3.org/2001/XMLSchema", "anyType"));
		oper.setReturnClass(java.lang.Object.class);
		oper.setReturnQName(new javax.xml.namespace.QName(
				"http://www.vivo.com.br/SN/NotaFiscalEletronica",
				"consultarInformacoesConfiancaResponse"));
		oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		oper.addFault(new org.apache.axis.description.FaultDesc(
				new javax.xml.namespace.QName(
						"http://www.vivo.com.br/MC/Geral", "erroInfo"),
				"br.com.vivo.www.MC.Geral.ErroInfo",
				new javax.xml.namespace.QName(
						"http://www.vivo.com.br/MC/Geral", "ErroInfo"), true));
		_operations[0] = oper;

	}

	public NotaFiscalEletronicaBindingStub() throws org.apache.axis.AxisFault {
		this(null);
	}

	public NotaFiscalEletronicaBindingStub(java.net.URL endpointURL,
			javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
		this(service);
		super.cachedEndpoint = endpointURL;
	}

	public NotaFiscalEletronicaBindingStub(javax.xml.rpc.Service service)
			throws org.apache.axis.AxisFault {
		if (service == null) {
			super.service = new org.apache.axis.client.Service();
		} else {
			super.service = service;
		}
		((org.apache.axis.client.Service) super.service)
				.setTypeMappingVersion("1.2");
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
		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Geral",
				">CabecalhoVivo>canalAtendimento");
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories
				.add(org.apache.axis.encoding.ser.BaseSerializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleSerializerFactory.class,
								cls, qName));
		cachedDeserFactories
				.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleDeserializerFactory.class,
								cls, qName));

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Geral",
				">CabecalhoVivo>codigoFuncionalidade");
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories
				.add(org.apache.axis.encoding.ser.BaseSerializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleSerializerFactory.class,
								cls, qName));
		cachedDeserFactories
				.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleDeserializerFactory.class,
								cls, qName));

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Geral",
				">CabecalhoVivo>codigoSessao");
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories
				.add(org.apache.axis.encoding.ser.BaseSerializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleSerializerFactory.class,
								cls, qName));
		cachedDeserFactories
				.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleDeserializerFactory.class,
								cls, qName));

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Geral",
				">CabecalhoVivo>dataTransacao");
		cachedSerQNames.add(qName);
		cls = java.util.Calendar.class;
		cachedSerClasses.add(cls);
		cachedSerFactories
				.add(org.apache.axis.encoding.ser.BaseSerializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleSerializerFactory.class,
								cls, qName));
		cachedDeserFactories
				.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleDeserializerFactory.class,
								cls, qName));

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Geral", ">CabecalhoVivo>enderecoIP");
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories
				.add(org.apache.axis.encoding.ser.BaseSerializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleSerializerFactory.class,
								cls, qName));
		cachedDeserFactories
				.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleDeserializerFactory.class,
								cls, qName));

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Geral",
				">CabecalhoVivo>loginUsuario");
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories
				.add(org.apache.axis.encoding.ser.BaseSerializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleSerializerFactory.class,
								cls, qName));
		cachedDeserFactories
				.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleDeserializerFactory.class,
								cls, qName));

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Geral",
				">CabecalhoVivo>nomeAplicacao");
		cachedSerQNames.add(qName);
		cls = br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.MC.Geral.CabecalhoVivoNomeAplicacao.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(enumsf);
		cachedDeserFactories.add(enumdf);

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Geral", ">CabecalhoVivo>nomeServico");
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories
				.add(org.apache.axis.encoding.ser.BaseSerializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleSerializerFactory.class,
								cls, qName));
		cachedDeserFactories
				.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleDeserializerFactory.class,
								cls, qName));

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Geral", ">CabecalhoVivo>token");
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories
				.add(org.apache.axis.encoding.ser.BaseSerializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleSerializerFactory.class,
								cls, qName));
		cachedDeserFactories
				.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleDeserializerFactory.class,
								cls, qName));

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Geral", ">CabecalhoVivo>versao");
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories
				.add(org.apache.axis.encoding.ser.BaseSerializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleSerializerFactory.class,
								cls, qName));
		cachedDeserFactories
				.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleDeserializerFactory.class,
								cls, qName));

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Geral", ">ErroInfo>codigo");
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories
				.add(org.apache.axis.encoding.ser.BaseSerializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleSerializerFactory.class,
								cls, qName));
		cachedDeserFactories
				.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleDeserializerFactory.class,
								cls, qName));

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Geral",
				">ErroInfo>codigoClassificacao");
		cachedSerQNames.add(qName);
		cls = java.math.BigInteger.class;
		cachedSerClasses.add(cls);
		cachedSerFactories
				.add(org.apache.axis.encoding.ser.BaseSerializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleSerializerFactory.class,
								cls, qName));
		cachedDeserFactories
				.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleDeserializerFactory.class,
								cls, qName));

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Geral", ">ErroInfo>dataTransacao");
		cachedSerQNames.add(qName);
		cls = java.util.Calendar.class;
		cachedSerClasses.add(cls);
		cachedSerFactories
				.add(org.apache.axis.encoding.ser.BaseSerializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleSerializerFactory.class,
								cls, qName));
		cachedDeserFactories
				.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleDeserializerFactory.class,
								cls, qName));

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Geral",
				">ErroInfo>descClassificacao");
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories
				.add(org.apache.axis.encoding.ser.BaseSerializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleSerializerFactory.class,
								cls, qName));
		cachedDeserFactories
				.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleDeserializerFactory.class,
								cls, qName));

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Geral", ">ErroInfo>descricao");
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories
				.add(org.apache.axis.encoding.ser.BaseSerializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleSerializerFactory.class,
								cls, qName));
		cachedDeserFactories
				.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleDeserializerFactory.class,
								cls, qName));

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Geral", "CabecalhoVivo");
		cachedSerQNames.add(qName);
		cls = br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.MC.Geral.CabecalhoVivo.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Geral", "ErroInfo");
		cachedSerQNames.add(qName);
		cls = br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.MC.Geral.ErroInfo.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Geral", "ErrosInfo");
		cachedSerQNames.add(qName);
		cls = br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.MC.Geral.ErroInfo[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Geral", "ErroInfo");
		qName2 = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Geral", "erroInfo");
		cachedSerFactories
				.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(
						qName, qName2));
		cachedDeserFactories
				.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/OrdemVenda", ">NotaFiscal>cfop");
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories
				.add(org.apache.axis.encoding.ser.BaseSerializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleSerializerFactory.class,
								cls, qName));
		cachedDeserFactories
				.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleDeserializerFactory.class,
								cls, qName));

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/OrdemVenda",
				">NotaFiscal>codigoHash");
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories
				.add(org.apache.axis.encoding.ser.BaseSerializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleSerializerFactory.class,
								cls, qName));
		cachedDeserFactories
				.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleDeserializerFactory.class,
								cls, qName));

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/OrdemVenda",
				">NotaFiscal>dataAnulacaoNF");
		cachedSerQNames.add(qName);
		cls = java.util.Calendar.class;
		cachedSerClasses.add(cls);
		cachedSerFactories
				.add(org.apache.axis.encoding.ser.BaseSerializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleSerializerFactory.class,
								cls, qName));
		cachedDeserFactories
				.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleDeserializerFactory.class,
								cls, qName));

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/OrdemVenda",
				">NotaFiscal>dataEmissaoNF");
		cachedSerQNames.add(qName);
		cls = java.util.Calendar.class;
		cachedSerClasses.add(cls);
		cachedSerFactories
				.add(org.apache.axis.encoding.ser.BaseSerializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleSerializerFactory.class,
								cls, qName));
		cachedDeserFactories
				.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleDeserializerFactory.class,
								cls, qName));

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/OrdemVenda",
				">NotaFiscal>nomeEmpresa");
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories
				.add(org.apache.axis.encoding.ser.BaseSerializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleSerializerFactory.class,
								cls, qName));
		cachedDeserFactories
				.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleDeserializerFactory.class,
								cls, qName));

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/OrdemVenda", ">NotaFiscal>numeroNF");
		cachedSerQNames.add(qName);
		cls = java.math.BigInteger.class;
		cachedSerClasses.add(cls);
		cachedSerFactories
				.add(org.apache.axis.encoding.ser.BaseSerializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleSerializerFactory.class,
								cls, qName));
		cachedDeserFactories
				.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleDeserializerFactory.class,
								cls, qName));

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/OrdemVenda",
				">NotaFiscal>numeroSerieNF");
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories
				.add(org.apache.axis.encoding.ser.BaseSerializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleSerializerFactory.class,
								cls, qName));
		cachedDeserFactories
				.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleDeserializerFactory.class,
								cls, qName));

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/OrdemVenda",
				">NotaFiscal>porcentagemFUNTEL");
		cachedSerQNames.add(qName);
		cls = java.math.BigDecimal.class;
		cachedSerClasses.add(cls);
		cachedSerFactories
				.add(org.apache.axis.encoding.ser.BaseSerializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleSerializerFactory.class,
								cls, qName));
		cachedDeserFactories
				.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleDeserializerFactory.class,
								cls, qName));

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/OrdemVenda",
				">NotaFiscal>porcentagemFUST");
		cachedSerQNames.add(qName);
		cls = java.math.BigDecimal.class;
		cachedSerClasses.add(cls);
		cachedSerFactories
				.add(org.apache.axis.encoding.ser.BaseSerializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleSerializerFactory.class,
								cls, qName));
		cachedDeserFactories
				.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleDeserializerFactory.class,
								cls, qName));

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/OrdemVenda",
				">NotaFiscal>porcentagemICMS");
		cachedSerQNames.add(qName);
		cls = java.math.BigDecimal.class;
		cachedSerClasses.add(cls);
		cachedSerFactories
				.add(org.apache.axis.encoding.ser.BaseSerializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleSerializerFactory.class,
								cls, qName));
		cachedDeserFactories
				.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleDeserializerFactory.class,
								cls, qName));

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/OrdemVenda",
				">NotaFiscal>valorBaseCalculoFUNTEL");
		cachedSerQNames.add(qName);
		cls = java.math.BigDecimal.class;
		cachedSerClasses.add(cls);
		cachedSerFactories
				.add(org.apache.axis.encoding.ser.BaseSerializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleSerializerFactory.class,
								cls, qName));
		cachedDeserFactories
				.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleDeserializerFactory.class,
								cls, qName));

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/OrdemVenda",
				">NotaFiscal>valorBaseCalculoFUST");
		cachedSerQNames.add(qName);
		cls = java.math.BigDecimal.class;
		cachedSerClasses.add(cls);
		cachedSerFactories
				.add(org.apache.axis.encoding.ser.BaseSerializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleSerializerFactory.class,
								cls, qName));
		cachedDeserFactories
				.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleDeserializerFactory.class,
								cls, qName));

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/OrdemVenda",
				">NotaFiscal>valorBaseCalculoICMS");
		cachedSerQNames.add(qName);
		cls = java.math.BigDecimal.class;
		cachedSerClasses.add(cls);
		cachedSerFactories
				.add(org.apache.axis.encoding.ser.BaseSerializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleSerializerFactory.class,
								cls, qName));
		cachedDeserFactories
				.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleDeserializerFactory.class,
								cls, qName));

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/OrdemVenda",
				">NotaFiscal>valorFUNTEL");
		cachedSerQNames.add(qName);
		cls = java.math.BigDecimal.class;
		cachedSerClasses.add(cls);
		cachedSerFactories
				.add(org.apache.axis.encoding.ser.BaseSerializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleSerializerFactory.class,
								cls, qName));
		cachedDeserFactories
				.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleDeserializerFactory.class,
								cls, qName));

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/OrdemVenda", ">NotaFiscal>valorFUST");
		cachedSerQNames.add(qName);
		cls = java.math.BigDecimal.class;
		cachedSerClasses.add(cls);
		cachedSerFactories
				.add(org.apache.axis.encoding.ser.BaseSerializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleSerializerFactory.class,
								cls, qName));
		cachedDeserFactories
				.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleDeserializerFactory.class,
								cls, qName));

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/OrdemVenda", ">NotaFiscal>valorICMS");
		cachedSerQNames.add(qName);
		cls = java.math.BigDecimal.class;
		cachedSerClasses.add(cls);
		cachedSerFactories
				.add(org.apache.axis.encoding.ser.BaseSerializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleSerializerFactory.class,
								cls, qName));
		cachedDeserFactories
				.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleDeserializerFactory.class,
								cls, qName));

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/OrdemVenda",
				">NotaFiscal>valorPagoNF");
		cachedSerQNames.add(qName);
		cls = java.math.BigDecimal.class;
		cachedSerClasses.add(cls);
		cachedSerFactories
				.add(org.apache.axis.encoding.ser.BaseSerializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleSerializerFactory.class,
								cls, qName));
		cachedDeserFactories
				.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleDeserializerFactory.class,
								cls, qName));

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/OrdemVenda",
				">NotaFiscal>valorTotalNF");
		cachedSerQNames.add(qName);
		cls = java.math.BigDecimal.class;
		cachedSerClasses.add(cls);
		cachedSerFactories
				.add(org.apache.axis.encoding.ser.BaseSerializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleSerializerFactory.class,
								cls, qName));
		cachedDeserFactories
				.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleDeserializerFactory.class,
								cls, qName));

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/OrdemVenda",
				">NotaFiscalEletronica>chaveAcesso");
		cachedSerQNames.add(qName);
		cls = java.math.BigInteger.class;
		cachedSerClasses.add(cls);
		cachedSerFactories
				.add(org.apache.axis.encoding.ser.BaseSerializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleSerializerFactory.class,
								cls, qName));
		cachedDeserFactories
				.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleDeserializerFactory.class,
								cls, qName));

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/OrdemVenda",
				">NotaFiscalEletronica>dataCriacaoChaveAcesso");
		cachedSerQNames.add(qName);
		cls = java.util.Date.class;
		cachedSerClasses.add(cls);
		cachedSerFactories
				.add(org.apache.axis.encoding.ser.BaseSerializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleSerializerFactory.class,
								cls, qName));
		cachedDeserFactories
				.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleDeserializerFactory.class,
								cls, qName));

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/OrdemVenda", "NotaFiscal");
		cachedSerQNames.add(qName);
		cls = br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.MC.OrdemVenda.NotaFiscal.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/OrdemVenda", "NotaFiscalEletronica");
		cachedSerQNames.add(qName);
		cls = br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.MC.OrdemVenda.NotaFiscalEletronica.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/OrdemVenda", "NotasFiscais");
		cachedSerQNames.add(qName);
		cls = br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.MC.OrdemVenda.NotaFiscal[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/OrdemVenda", "NotaFiscal");
		qName2 = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/OrdemVenda", "notaFiscal");
		cachedSerFactories
				.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(
						qName, qName2));
		cachedDeserFactories
				.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Pessoa", ">Endereco>bairro");
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories
				.add(org.apache.axis.encoding.ser.BaseSerializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleSerializerFactory.class,
								cls, qName));
		cachedDeserFactories
				.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleDeserializerFactory.class,
								cls, qName));

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Pessoa", ">Endereco>caixaPostal");
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories
				.add(org.apache.axis.encoding.ser.BaseSerializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleSerializerFactory.class,
								cls, qName));
		cachedDeserFactories
				.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleDeserializerFactory.class,
								cls, qName));

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Pessoa", ">Endereco>cep");
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories
				.add(org.apache.axis.encoding.ser.BaseSerializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleSerializerFactory.class,
								cls, qName));
		cachedDeserFactories
				.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleDeserializerFactory.class,
								cls, qName));

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Pessoa", ">Endereco>cidade");
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories
				.add(org.apache.axis.encoding.ser.BaseSerializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleSerializerFactory.class,
								cls, qName));
		cachedDeserFactories
				.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleDeserializerFactory.class,
								cls, qName));

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Pessoa", ">Endereco>codigo");
		cachedSerQNames.add(qName);
		cls = java.math.BigInteger.class;
		cachedSerClasses.add(cls);
		cachedSerFactories
				.add(org.apache.axis.encoding.ser.BaseSerializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleSerializerFactory.class,
								cls, qName));
		cachedDeserFactories
				.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleDeserializerFactory.class,
								cls, qName));

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Pessoa",
				">Endereco>codigoEndSistemaOrigem");
		cachedSerQNames.add(qName);
		cls = java.math.BigInteger.class;
		cachedSerClasses.add(cls);
		cachedSerFactories
				.add(org.apache.axis.encoding.ser.BaseSerializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleSerializerFactory.class,
								cls, qName));
		cachedDeserFactories
				.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleDeserializerFactory.class,
								cls, qName));

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Pessoa", ">Endereco>complemento");
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories
				.add(org.apache.axis.encoding.ser.BaseSerializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleSerializerFactory.class,
								cls, qName));
		cachedDeserFactories
				.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleDeserializerFactory.class,
								cls, qName));

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Pessoa", ">Endereco>dataCadastro");
		cachedSerQNames.add(qName);
		cls = java.util.Date.class;
		cachedSerClasses.add(cls);
		cachedSerFactories
				.add(org.apache.axis.encoding.ser.BaseSerializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleSerializerFactory.class,
								cls, qName));
		cachedDeserFactories
				.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleDeserializerFactory.class,
								cls, qName));

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Pessoa", ">Endereco>dataExpiracao");
		cachedSerQNames.add(qName);
		cls = java.util.Date.class;
		cachedSerClasses.add(cls);
		cachedSerFactories
				.add(org.apache.axis.encoding.ser.BaseSerializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleSerializerFactory.class,
								cls, qName));
		cachedDeserFactories
				.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleDeserializerFactory.class,
								cls, qName));

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Pessoa",
				">Endereco>dataUltimaAlteracao");
		cachedSerQNames.add(qName);
		cls = java.util.Calendar.class;
		cachedSerClasses.add(cls);
		cachedSerFactories
				.add(org.apache.axis.encoding.ser.BaseSerializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleSerializerFactory.class,
								cls, qName));
		cachedDeserFactories
				.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleDeserializerFactory.class,
								cls, qName));

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Pessoa", ">Endereco>descricaoLado");
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories
				.add(org.apache.axis.encoding.ser.BaseSerializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleSerializerFactory.class,
								cls, qName));
		cachedDeserFactories
				.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleDeserializerFactory.class,
								cls, qName));

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Pessoa",
				">Endereco>enderecoAssociadoConta");
		cachedSerQNames.add(qName);
		cls = boolean.class;
		cachedSerClasses.add(cls);
		cachedSerFactories
				.add(org.apache.axis.encoding.ser.BaseSerializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleSerializerFactory.class,
								cls, qName));
		cachedDeserFactories
				.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleDeserializerFactory.class,
								cls, qName));

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Pessoa",
				">Endereco>enderecoPreferencial");
		cachedSerQNames.add(qName);
		cls = boolean.class;
		cachedSerClasses.add(cls);
		cachedSerFactories
				.add(org.apache.axis.encoding.ser.BaseSerializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleSerializerFactory.class,
								cls, qName));
		cachedDeserFactories
				.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleDeserializerFactory.class,
								cls, qName));

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Pessoa", ">Endereco>indicaLado");
		cachedSerQNames.add(qName);
		cls = java.math.BigInteger.class;
		cachedSerClasses.add(cls);
		cachedSerFactories
				.add(org.apache.axis.encoding.ser.BaseSerializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleSerializerFactory.class,
								cls, qName));
		cachedDeserFactories
				.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleDeserializerFactory.class,
								cls, qName));

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Pessoa",
				">Endereco>indicaNumeracaoEndereco");
		cachedSerQNames.add(qName);
		cls = java.math.BigInteger.class;
		cachedSerClasses.add(cls);
		cachedSerFactories
				.add(org.apache.axis.encoding.ser.BaseSerializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleSerializerFactory.class,
								cls, qName));
		cachedDeserFactories
				.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleDeserializerFactory.class,
								cls, qName));

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Pessoa", ">Endereco>logradouro");
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories
				.add(org.apache.axis.encoding.ser.BaseSerializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleSerializerFactory.class,
								cls, qName));
		cachedDeserFactories
				.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleDeserializerFactory.class,
								cls, qName));

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Pessoa", ">Endereco>numero");
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories
				.add(org.apache.axis.encoding.ser.BaseSerializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleSerializerFactory.class,
								cls, qName));
		cachedDeserFactories
				.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleDeserializerFactory.class,
								cls, qName));

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Pessoa", ">Endereco>numeroFinal");
		cachedSerQNames.add(qName);
		cls = java.math.BigInteger.class;
		cachedSerClasses.add(cls);
		cachedSerFactories
				.add(org.apache.axis.encoding.ser.BaseSerializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleSerializerFactory.class,
								cls, qName));
		cachedDeserFactories
				.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleDeserializerFactory.class,
								cls, qName));

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Pessoa", ">Endereco>numeroInicial");
		cachedSerQNames.add(qName);
		cls = java.math.BigInteger.class;
		cachedSerClasses.add(cls);
		cachedSerFactories
				.add(org.apache.axis.encoding.ser.BaseSerializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleSerializerFactory.class,
								cls, qName));
		cachedDeserFactories
				.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleDeserializerFactory.class,
								cls, qName));

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Pessoa", ">EnderecoFiscal>codigo");
		cachedSerQNames.add(qName);
		cls = java.math.BigInteger.class;
		cachedSerClasses.add(cls);
		cachedSerFactories
				.add(org.apache.axis.encoding.ser.BaseSerializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleSerializerFactory.class,
								cls, qName));
		cachedDeserFactories
				.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleDeserializerFactory.class,
								cls, qName));

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Pessoa",
				">EnderecoFiscal>codigoBairro");
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories
				.add(org.apache.axis.encoding.ser.BaseSerializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleSerializerFactory.class,
								cls, qName));
		cachedDeserFactories
				.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleDeserializerFactory.class,
								cls, qName));

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Pessoa",
				">EnderecoFiscal>codigoIBGE");
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories
				.add(org.apache.axis.encoding.ser.BaseSerializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleSerializerFactory.class,
								cls, qName));
		cachedDeserFactories
				.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleDeserializerFactory.class,
								cls, qName));

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Pessoa",
				">EnderecoFiscal>codigoNacLocalidade");
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories
				.add(org.apache.axis.encoding.ser.BaseSerializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleSerializerFactory.class,
								cls, qName));
		cachedDeserFactories
				.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleDeserializerFactory.class,
								cls, qName));

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Pessoa",
				">EnderecoFiscal>siglaNacLocalidade");
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories
				.add(org.apache.axis.encoding.ser.BaseSerializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleSerializerFactory.class,
								cls, qName));
		cachedDeserFactories
				.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleDeserializerFactory.class,
								cls, qName));

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Pessoa", ">Pais>codigo");
		cachedSerQNames.add(qName);
		cls = java.math.BigInteger.class;
		cachedSerClasses.add(cls);
		cachedSerFactories
				.add(org.apache.axis.encoding.ser.BaseSerializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleSerializerFactory.class,
								cls, qName));
		cachedDeserFactories
				.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleDeserializerFactory.class,
								cls, qName));

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Pessoa", ">Pais>nacionalidade");
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories
				.add(org.apache.axis.encoding.ser.BaseSerializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleSerializerFactory.class,
								cls, qName));
		cachedDeserFactories
				.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleDeserializerFactory.class,
								cls, qName));

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Pessoa", ">Pais>nome");
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories
				.add(org.apache.axis.encoding.ser.BaseSerializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleSerializerFactory.class,
								cls, qName));
		cachedDeserFactories
				.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleDeserializerFactory.class,
								cls, qName));

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Pessoa", ">Pais>sigla");
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories
				.add(org.apache.axis.encoding.ser.BaseSerializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleSerializerFactory.class,
								cls, qName));
		cachedDeserFactories
				.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleDeserializerFactory.class,
								cls, qName));

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Pessoa", ">TipoEndereco>codigo");
		cachedSerQNames.add(qName);
		cls = java.math.BigInteger.class;
		cachedSerClasses.add(cls);
		cachedSerFactories
				.add(org.apache.axis.encoding.ser.BaseSerializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleSerializerFactory.class,
								cls, qName));
		cachedDeserFactories
				.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleDeserializerFactory.class,
								cls, qName));

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Pessoa", ">TipoEndereco>descricao");
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories
				.add(org.apache.axis.encoding.ser.BaseSerializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleSerializerFactory.class,
								cls, qName));
		cachedDeserFactories
				.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleDeserializerFactory.class,
								cls, qName));

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Pessoa", ">TipoEndereco>sigla");
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories
				.add(org.apache.axis.encoding.ser.BaseSerializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleSerializerFactory.class,
								cls, qName));
		cachedDeserFactories
				.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleDeserializerFactory.class,
								cls, qName));

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Pessoa", ">TipoLogradouro>codigo");
		cachedSerQNames.add(qName);
		cls = java.math.BigDecimal.class;
		cachedSerClasses.add(cls);
		cachedSerFactories
				.add(org.apache.axis.encoding.ser.BaseSerializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleSerializerFactory.class,
								cls, qName));
		cachedDeserFactories
				.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleDeserializerFactory.class,
								cls, qName));

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Pessoa", ">TipoLogradouro>descricao");
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories
				.add(org.apache.axis.encoding.ser.BaseSerializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleSerializerFactory.class,
								cls, qName));
		cachedDeserFactories
				.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleDeserializerFactory.class,
								cls, qName));

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Pessoa",
				">TipoLogradouro>descricaoAbreviada");
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories
				.add(org.apache.axis.encoding.ser.BaseSerializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleSerializerFactory.class,
								cls, qName));
		cachedDeserFactories
				.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleDeserializerFactory.class,
								cls, qName));

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Pessoa", ">TituloLogradouro>codigo");
		cachedSerQNames.add(qName);
		cls = java.math.BigInteger.class;
		cachedSerClasses.add(cls);
		cachedSerFactories
				.add(org.apache.axis.encoding.ser.BaseSerializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleSerializerFactory.class,
								cls, qName));
		cachedDeserFactories
				.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleDeserializerFactory.class,
								cls, qName));

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Pessoa",
				">TituloLogradouro>descricao");
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories
				.add(org.apache.axis.encoding.ser.BaseSerializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleSerializerFactory.class,
								cls, qName));
		cachedDeserFactories
				.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleDeserializerFactory.class,
								cls, qName));

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Pessoa",
				">TituloLogradouro>descricaoAbreviada");
		cachedSerQNames.add(qName);
		cls = java.lang.String.class;
		cachedSerClasses.add(cls);
		cachedSerFactories
				.add(org.apache.axis.encoding.ser.BaseSerializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleSerializerFactory.class,
								cls, qName));
		cachedDeserFactories
				.add(org.apache.axis.encoding.ser.BaseDeserializerFactory
						.createFactory(
								org.apache.axis.encoding.ser.SimpleDeserializerFactory.class,
								cls, qName));

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Pessoa", "Endereco");
		cachedSerQNames.add(qName);
		cls = br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.MC.Pessoa.Endereco.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Pessoa", "EnderecoFiscal");
		cachedSerQNames.add(qName);
		cls = br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.MC.Pessoa.EnderecoFiscal.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Pessoa", "Enderecos");
		cachedSerQNames.add(qName);
		cls = br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.MC.Pessoa.Endereco[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Pessoa", "Endereco");
		qName2 = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Pessoa", "endereco");
		cachedSerFactories
				.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(
						qName, qName2));
		cachedDeserFactories
				.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Pessoa", "Pais");
		cachedSerQNames.add(qName);
		cls = br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.MC.Pessoa.Pais.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Pessoa", "Paises");
		cachedSerQNames.add(qName);
		cls = br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.MC.Pessoa.Pais[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Pessoa", "Pais");
		qName2 = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Pessoa", "pais");
		cachedSerFactories
				.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(
						qName, qName2));
		cachedDeserFactories
				.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Pessoa", "TipoEndereco");
		cachedSerQNames.add(qName);
		cls = br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.MC.Pessoa.TipoEndereco.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Pessoa", "TipoLogradouro");
		cachedSerQNames.add(qName);
		cls = br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.MC.Pessoa.TipoLogradouro.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Pessoa", "TiposEndereco");
		cachedSerQNames.add(qName);
		cls = br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.MC.Pessoa.TipoEndereco[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Pessoa", "TipoEndereco");
		qName2 = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Pessoa", "tipoEndereco");
		cachedSerFactories
				.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(
						qName, qName2));
		cachedDeserFactories
				.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Pessoa", "TiposLogradouro");
		cachedSerQNames.add(qName);
		cls = br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.MC.Pessoa.TipoLogradouro[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Pessoa", "TipoLogradouro");
		qName2 = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Pessoa", "tipoLogradouro");
		cachedSerFactories
				.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(
						qName, qName2));
		cachedDeserFactories
				.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Pessoa", "TituloLogradouro");
		cachedSerQNames.add(qName);
		cls = br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.MC.Pessoa.TituloLogradouro.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Pessoa", "TitulosLogradouro");
		cachedSerQNames.add(qName);
		cls = br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.MC.Pessoa.TituloLogradouro[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Pessoa", "TituloLogradouro");
		qName2 = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/MC/Pessoa", "tituloLogradouro");
		cachedSerFactories
				.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(
						qName, qName2));
		cachedDeserFactories
				.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/SN/NotaFiscalEletronica",
				">ParametrosConsultarInformacoesConfianca>TipoConsulta");
		cachedSerQNames.add(qName);
		cls = br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.SN.NotaFiscalEletronica.ParametrosConsultarInformacoesConfiancaTipoConsulta.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(enumsf);
		cachedDeserFactories.add(enumdf);

		qName = new javax.xml.namespace.QName(
				"http://www.vivo.com.br/SN/NotaFiscalEletronica",
				"ParametrosConsultarInformacoesConfianca");
		cachedSerQNames.add(qName);
		cls = br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.SN.NotaFiscalEletronica.ParametrosConsultarInformacoesConfianca.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

	}

	protected org.apache.axis.client.Call createCall()
			throws java.rmi.RemoteException {
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
						java.lang.Class cls = (java.lang.Class) cachedSerClasses
								.get(i);
						javax.xml.namespace.QName qName = (javax.xml.namespace.QName) cachedSerQNames
								.get(i);
						java.lang.Object x = cachedSerFactories.get(i);
						if (x instanceof Class) {
							java.lang.Class sf = (java.lang.Class) cachedSerFactories
									.get(i);
							java.lang.Class df = (java.lang.Class) cachedDeserFactories
									.get(i);
							_call
									.registerTypeMapping(cls, qName, sf, df,
											false);
						} else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
							org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory) cachedSerFactories
									.get(i);
							org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory) cachedDeserFactories
									.get(i);
							_call
									.registerTypeMapping(cls, qName, sf, df,
											false);
						}
					}
				}
			}
			_call.addHeader(getCabecalhoVivoHeader());
			return _call;
		} catch (java.lang.Throwable _t) {
			throw new org.apache.axis.AxisFault(
					"Failure trying to get the Call object", _t);
		}
	}

	public java.lang.Object consultarInformacoesConfianca(
			br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.SN.NotaFiscalEletronica.ParametrosConsultarInformacoesConfianca consultarInformacoesConfiancaRequest)
			throws java.rmi.RemoteException,
			br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.MC.Geral.ErroInfo {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[0]);
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR,
				Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS,
				Boolean.FALSE);
		_call
				.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("",
				"consultarInformacoesConfianca"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			java.lang.Object _resp = _call
					.invoke(new java.lang.Object[] { consultarInformacoesConfiancaRequest });

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (java.lang.Object) _resp;
				} catch (java.lang.Exception _exception) {
					return (java.lang.Object) org.apache.axis.utils.JavaUtils
							.convert(_resp, java.lang.Object.class);
				}
			}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			if (axisFaultException.detail != null) {
				if (axisFaultException.detail instanceof java.rmi.RemoteException) {
					throw (java.rmi.RemoteException) axisFaultException.detail;
				}
				if (axisFaultException.detail instanceof br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.MC.Geral.ErroInfo) {
					throw (br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.MC.Geral.ErroInfo) axisFaultException.detail;
				}
			}
			throw axisFaultException;
		}
	}

	public SOAPHeaderElement getCabecalhoVivoHeader() {

		SOAPHeaderElement cabecalhoVivo = null;
		try {
			cabecalhoVivo = new MySoapHeaderElement(new PrefixedQName(
					"http://www.vivo.com.br/MC/Geral", "cabecalhoVivo", "ger"));
			cabecalhoVivo.setActor(null);

			// Login Usuario
			MessageElement loginUsuario = new MessageElement(
					new javax.xml.namespace.QName(
							"http://www.vivo.com.br/MC/Geral",
							">CabecalhoVivo>loginUsuario"));
			loginUsuario.setObjectValue("VivoVivonet");

			// Nome Aplicacao
			MessageElement nomeAplicacao = new MessageElement(
					new javax.xml.namespace.QName(
							"http://www.vivo.com.br/MC/Geral",
							">CabecalhoVivo>nomeAplicacao"));
			nomeAplicacao.setObjectValue("VivoVivonet");

			cabecalhoVivo.addChild(loginUsuario);
			cabecalhoVivo.addChild(nomeAplicacao);

		} catch (Exception e) {

		}
		return cabecalhoVivo;
	}

}
