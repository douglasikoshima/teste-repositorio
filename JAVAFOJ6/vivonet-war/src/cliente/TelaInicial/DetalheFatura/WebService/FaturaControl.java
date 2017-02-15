package cliente.TelaInicial.DetalheFatura.WebService;

/**
 * @jc:location http-url="http://alsb3-soa:80/ContaOnline/Fatura"
 * @jc:wsdl file="#FaturaWsdl"
 * @editor-info:link autogen-style="java" source="Fatura.wsdl" autogen="true"
 */
public interface FaturaControl {

    public static class ParametrosObterGrafico implements java.io.Serializable {

		private static final long serialVersionUID = -5633368435251059153L;
		public java.lang.String nrConta;
        public java.lang.String tpGrafico;
        public java.lang.String tpVetor;
        public java.lang.String tpConsolidacao;
        public valoresSerie[] parametrosGraficoPizza;
        public itens[] parametrosGraficoLinhaColuna;
    }

    public static class valoresSerie implements java.io.Serializable {
		private static final long serialVersionUID = 1419078509144913639L;
		public java.lang.String nmSerie;
        public java.lang.String vlSerie;
    }

    public static class itens implements java.io.Serializable {
		private static final long serialVersionUID = -3090063406070376634L;
		public java.lang.String nrItem;
        public java.lang.String labeleixoX;
        public java.lang.String vleixoX;
        public valoresSerie1[] valoresSerie;
    }

    public static class valoresSerie1 implements java.io.Serializable {
		private static final long serialVersionUID = -7803199825834110451L;
		public java.lang.String lbSerie;
        public java.lang.String vlSerie;
    }

    /**
     * @jc:protocol form-post="false" form-get="false"
     */
    public Fatura[] consultarHistorico (java.lang.String nrConta, java.lang.String nrLinha);

    /**
     * @jc:protocol form-post="false" form-get="false"
     */
    public Fatura[] obterFatura (java.lang.String nrConta, java.lang.String tpFormato, java.lang.String dtVencimento, java.lang.String dtReferencia, java.lang.String cd_uf, java.math.BigInteger nrPagina, java.lang.String nrLinha);

    /**
     * @jc:protocol form-post="false" form-get="false"
     */
    public Fatura obterGrafico (java.lang.String nrConta, java.lang.String tpGrafico, java.lang.String tpVetor, java.lang.String tpConsolidacao, valoresSerie[] parametrosGraficoPizza, itens[] parametrosGraficoLinhaColuna);

    /**
     * @jc:protocol form-post="false" form-get="false" http-soap="true"
     */
    public Chamada[] buscarHistoricoFaturaComFiltro (java.lang.String nrConta, java.lang.String dtReferencia, java.lang.String dtInicio, java.lang.String dtTermino, java.lang.String hrInicio, java.lang.String hrTermino, java.lang.String nrLinhaOrigem, java.lang.String nrLinhaDestino, java.lang.String tpOrigemChamada, java.lang.String tpArea, java.lang.String tpDetalheChamada, java.lang.String tpServico, java.lang.String tpDestino, boolean inAgendaOrigem, boolean inAgendaDestino) throws cliente.TelaInicial.DetalheFatura.WebService.ErroInfo;

    static final long serialVersionUID = 1L;
}

/** @common:define name="FaturaWsdl" value::
    <s1:definitions xmlns:s0="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd" xmlns:s1="http://schemas.xmlsoap.org/wsdl/" xmlns:s2="http://www.vivo.com.br/MC/Geral" xmlns:s3="http://www.vivo.com.br/SN/Fatura" xmlns:s4="http://www.vivo.com.br/MC/Conta" xmlns:s5="http://schemas.xmlsoap.org/wsdl/soap/" name="Fatura" targetNamespace="http://www.vivo.com.br/SN/Fatura">
    	<s1:types>
    		<xsd:schema targetNamespace="http://www.vivo.com.br/MC/Conta" xmlns:ct="http://www.vivo.com.br/MC/Conta" xmlns:cta="http://www.vivo.com.br/SN/Fatura" xmlns:gr="http://www.vivo.com.br/MC/Geral" xmlns:s0="http://schemas.xmlsoap.org/wsdl/" xmlns:s1="http://www.vivo.com.br/MC/Geral" xmlns:s2="http://www.vivo.com.br/SN/Fatura" xmlns:s3="http://www.vivo.com.br/MC/Conta" xmlns:s4="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns:xsd="http://www.w3.org/2001/XMLSchema">
    			<xsd:import namespace="http://www.vivo.com.br/MC/Geral" schemaLocation="ErroInfo.xsd"/>
    		</xsd:schema>
    		<xsd:schema targetNamespace="http://www.vivo.com.br/SN/Fatura" xmlns:ct="http://www.vivo.com.br/MC/Conta" xmlns:cta="http://www.vivo.com.br/SN/Fatura" xmlns:gr="http://www.vivo.com.br/MC/Geral" xmlns:s0="http://schemas.xmlsoap.org/wsdl/" xmlns:s1="http://www.vivo.com.br/MC/Geral" xmlns:s2="http://www.vivo.com.br/SN/Fatura" xmlns:s3="http://www.vivo.com.br/MC/Conta" xmlns:s4="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns:xsd="http://www.w3.org/2001/XMLSchema">
    			<xsd:include schemaLocation="ParametrosObterGrafico.xsd"/>
    			<xsd:element name="obterGraficoRequest" type="cta:ParametrosObterGrafico"/>
    		</xsd:schema>
    		<xsd:schema targetNamespace="http://www.vivo.com.br/MC/Conta" xmlns:ct="http://www.vivo.com.br/MC/Conta" xmlns:cta="http://www.vivo.com.br/SN/Fatura" xmlns:gr="http://www.vivo.com.br/MC/Geral" xmlns:s0="http://schemas.xmlsoap.org/wsdl/" xmlns:s1="http://www.vivo.com.br/MC/Geral" xmlns:s2="http://www.vivo.com.br/SN/Fatura" xmlns:s3="http://www.vivo.com.br/MC/Conta" xmlns:s4="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns:xsd="http://www.w3.org/2001/XMLSchema">
    			<xsd:include schemaLocation="Conta.xsd"/>
    			<xsd:include schemaLocation="Fatura.xsd"/>
    			<xsd:include schemaLocation="Chamada.xsd"/>
    			<xsd:element name="Chamadas" type="ct:Chamadas"/>
    			<xsd:element name="Faturas" type="ct:Faturas"/>
    			<xsd:element name="Fatura" type="ct:Fatura"/>
    		</xsd:schema>
    		<xsd:schema targetNamespace="http://www.vivo.com.br/SN/Fatura" xmlns:ct="http://www.vivo.com.br/MC/Conta" xmlns:cta="http://www.vivo.com.br/SN/Fatura" xmlns:gr="http://www.vivo.com.br/MC/Geral" xmlns:s0="http://schemas.xmlsoap.org/wsdl/" xmlns:s1="http://www.vivo.com.br/MC/Geral" xmlns:s2="http://www.vivo.com.br/SN/Fatura" xmlns:s3="http://www.vivo.com.br/MC/Conta" xmlns:s4="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns:xsd="http://www.w3.org/2001/XMLSchema">
    			<xsd:element name="consultarHistoricoRequest">
    				<xsd:complexType>
    					<xsd:sequence>
    						<xsd:choice>
    							<xsd:element name="nrConta" type="xsd:string"/>
    							<xsd:element name="nrLinha">
    								<xsd:simpleType>
    									<xsd:restriction base="xsd:string">
    										<xsd:length value="10"/>
    									</xsd:restriction>
    								</xsd:simpleType>
    							</xsd:element>
    						</xsd:choice>
    					</xsd:sequence>
    				</xsd:complexType>
    			</xsd:element>
    		</xsd:schema>
    		<xsd:schema targetNamespace="http://www.vivo.com.br/SN/Fatura" xmlns:ct="http://www.vivo.com.br/MC/Conta" xmlns:cta="http://www.vivo.com.br/SN/Fatura" xmlns:gr="http://www.vivo.com.br/MC/Geral" xmlns:s0="http://schemas.xmlsoap.org/wsdl/" xmlns:s1="http://www.vivo.com.br/MC/Geral" xmlns:s2="http://www.vivo.com.br/SN/Fatura" xmlns:s3="http://www.vivo.com.br/MC/Conta" xmlns:s4="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns:xsd="http://www.w3.org/2001/XMLSchema">
    			<xsd:element name="obterFaturaRequest">
    				<xsd:complexType>
    					<xsd:sequence>
    						<xsd:element name="nrConta" type="xsd:string"/>
    						<xsd:element name="tpFormato">
    							<xsd:simpleType>
    								<xsd:restriction base="xsd:string">
    									<xsd:enumeration value="GIF"/>
    									<xsd:enumeration value="PDF"/>
    									<xsd:enumeration value="FEBRABAN"/>
    									<xsd:enumeration value="FATURAELETRONICA"/>
    									<xsd:enumeration value="XML"/>
    								</xsd:restriction>
    							</xsd:simpleType>
    						</xsd:element>
    						<xsd:element minOccurs="0" name="dtVencimento" type="xsd:string"/>
    						<xsd:element minOccurs="0" name="dtReferencia" type="xsd:string"/>
    						<xsd:element minOccurs="0" name="cd_uf" type="xsd:string"/>
    						<xsd:element minOccurs="0" name="nrPagina" type="xsd:integer"/>
    						<xsd:element minOccurs="0" name="nrLinha">
    							<xsd:simpleType>
    								<xsd:restriction base="xsd:string">
    									<xsd:length value="10"/>
    								</xsd:restriction>
    							</xsd:simpleType>
    						</xsd:element>
    					</xsd:sequence>
    				</xsd:complexType>
    			</xsd:element>
    		</xsd:schema>
    		<xsd:schema targetNamespace="http://www.vivo.com.br/SN/Fatura" xmlns:ct="http://www.vivo.com.br/MC/Conta" xmlns:cta="http://www.vivo.com.br/SN/Fatura" xmlns:gr="http://www.vivo.com.br/MC/Geral" xmlns:s0="http://schemas.xmlsoap.org/wsdl/" xmlns:s1="http://www.vivo.com.br/MC/Geral" xmlns:s2="http://www.vivo.com.br/SN/Fatura" xmlns:s3="http://www.vivo.com.br/MC/Conta" xmlns:s4="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns:xsd="http://www.w3.org/2001/XMLSchema">
    			<xsd:element name="buscarHistoricoFaturaComFiltroRequest">
    				<xsd:complexType>
    					<xsd:sequence>
    						<xsd:element name="nrConta" type="xsd:string"/>
    						<xsd:element minOccurs="0" name="dtReferencia">
    							<xsd:simpleType>
    								<xsd:restriction base="xsd:string">
    									<xsd:length value="6"/>
    								</xsd:restriction>
    							</xsd:simpleType>
    						</xsd:element>
    						<xsd:element minOccurs="0" name="dtInicio" type="xsd:string"/>
    						<xsd:element minOccurs="0" name="dtTermino" type="xsd:string"/>
    						<xsd:element minOccurs="0" name="hrInicio" type="xsd:time"/>
    						<xsd:element minOccurs="0" name="hrTermino" type="xsd:time"/>
    						<xsd:element minOccurs="0" name="nrLinhaOrigem">
    							<xsd:simpleType>
    								<xsd:restriction base="xsd:string">
    									<xsd:maxLength value="14"/>
    								</xsd:restriction>
    							</xsd:simpleType>
    						</xsd:element>
    						<xsd:element minOccurs="0" name="nrLinhaDestino">
    							<xsd:simpleType>
    								<xsd:restriction base="xsd:string">
    									<xsd:maxLength value="14"/>
    								</xsd:restriction>
    							</xsd:simpleType>
    						</xsd:element>
    						<xsd:element minOccurs="0" name="tpOrigemChamada">
    							<xsd:annotation>
    								<xsd:documentation>Domínio dos parâmetros: Originada: “O"; Recebida: “R"; Todas: “T”</xsd:documentation>
    							</xsd:annotation>
    							<xsd:simpleType>
    								<xsd:restriction base="xsd:string">
    									<xsd:enumeration value="O"/>
    									<xsd:enumeration value="R"/>
    									<xsd:enumeration value="T"/>
    								</xsd:restriction>
    							</xsd:simpleType>
    						</xsd:element>
    						<xsd:element minOccurs="0" name="tpArea">
    							<xsd:annotation>
    								<xsd:documentation>Domínio dos parâmetros: Dentro da área de registro: “D”; Fora da área de registro: “F”; Todas: “T”</xsd:documentation>
    							</xsd:annotation>
    							<xsd:simpleType>
    								<xsd:restriction base="xsd:string">
    									<xsd:enumeration value="D"/>
    									<xsd:enumeration value="F"/>
    									<xsd:enumeration value="T"/>
    								</xsd:restriction>
    							</xsd:simpleType>
    						</xsd:element>
    						<xsd:element minOccurs="0" name="tpDetalheChamada">
    							<xsd:annotation>
    								<xsd:documentation>Domínio dos parâmetros: Local: “L”; Longa Distância Nacional: “N”; Longa Distância Internacional: “I”; Todas: "T"</xsd:documentation>
    							</xsd:annotation>
    							<xsd:simpleType>
    								<xsd:restriction base="xsd:string">
    									<xsd:enumeration value="L"/>
    									<xsd:enumeration value="N"/>
    									<xsd:enumeration value="I"/>
    									<xsd:enumeration value="T"/>
    								</xsd:restriction>
    							</xsd:simpleType>
    						</xsd:element>
    						<xsd:element minOccurs="0" name="tpServico">
    							<xsd:annotation>
    								<xsd:documentation>Domínio dos parâmetros: Voz: "V"; Serviços de Dados: "D"; Valor Agregado: "A"; Outros Serviços: "O"; Todas: "T"</xsd:documentation>
    							</xsd:annotation>
    							<xsd:simpleType>
    								<xsd:restriction base="xsd:string">
    									<xsd:enumeration value="V"/>
    									<xsd:enumeration value="D"/>
    									<xsd:enumeration value="A"/>
    									<xsd:enumeration value="O"/>
    									<xsd:enumeration value="T"/>
    								</xsd:restriction>
    							</xsd:simpleType>
    						</xsd:element>
    						<xsd:element minOccurs="0" name="tpDestino">
    							<xsd:annotation>
    								<xsd:documentation>Domínio dos parâmetros: Fixo: "F"; Número Vivo: "V"; Outras: "O"; Todas: "T"</xsd:documentation>
    							</xsd:annotation>
    							<xsd:simpleType>
    								<xsd:restriction base="xsd:string">
    									<xsd:enumeration value="F"/>
    									<xsd:enumeration value="V"/>
    									<xsd:enumeration value="O"/>
    									<xsd:enumeration value="T"/>
    								</xsd:restriction>
    							</xsd:simpleType>
    						</xsd:element>
                            <xsd:element name="inAgendaOrigem" type="xsd:boolean" /> 
                            <xsd:element name="inAgendaDestino" type="xsd:boolean" /> 
    					</xsd:sequence>
    				</xsd:complexType>
    			</xsd:element>
    		</xsd:schema>
    	</s1:types>
    	<s1:message name="erroInfoFault">
    		<s1:part name="erroInfo" element="s2:erroInfo"/>
    	</s1:message>
    	<s1:message name="consultarHistoricoRequest">
    		<s1:part name="consultarHistoricoRequest" element="s3:consultarHistoricoRequest"/>
    	</s1:message>
    	<s1:message name="obterFaturaRequest">
    		<s1:part name="obterFaturaRequest" element="s3:obterFaturaRequest"/>
    	</s1:message>
    	<s1:message name="obterGraficoRequest">
    		<s1:part name="obterGraficoRequest" element="s3:obterGraficoRequest"/>
    	</s1:message>
    	<s1:message name="buscarHistoricoFaturaComFiltroRequest">
    		<s1:part name="buscarHistoricoFaturaComFiltroRequest" element="s3:buscarHistoricoFaturaComFiltroRequest"/>
    	</s1:message>
    	<s1:message name="Faturas">
    		<s1:part name="Faturas" element="s4:Faturas"/>
    	</s1:message>
    	<s1:message name="Fatura">
    		<s1:part name="Fatura" element="s4:Fatura"/>
    	</s1:message>
    	<s1:message name="Chamadas">
    		<s1:part name="Chamadas" element="s4:Chamadas"/>
    	</s1:message>
    	<s1:portType name="FaturaPortType">
    		<s1:operation name="consultarHistorico">
    			<s1:input message="s3:consultarHistoricoRequest"/>
    			<s1:output message="s3:Faturas"/>
    			<s1:fault name="erroInfoFault" message="s3:erroInfoFault"/>
    		</s1:operation>
    		<s1:operation name="obterFatura">
    			<s1:input message="s3:obterFaturaRequest"/>
    			<s1:output message="s3:Faturas"/>
    			<s1:fault name="erroInfoFault" message="s3:erroInfoFault"/>
    		</s1:operation>
    		<s1:operation name="obterGrafico">
    			<s1:input message="s3:obterGraficoRequest"/>
    			<s1:output message="s3:Fatura"/>
    			<s1:fault name="erroInfoFault" message="s3:erroInfoFault"/>
    		</s1:operation>
    		<s1:operation name="buscarHistoricoFaturaComFiltro">
    			<s1:input message="s3:buscarHistoricoFaturaComFiltroRequest"/>
    			<s1:output message="s3:Chamadas"/>
    			<s1:fault name="erroInfoFault" message="s3:erroInfoFault"/>
    		</s1:operation>
    	</s1:portType>
    	<s1:binding name="FaturaBinding" type="s3:FaturaPortType">
    		<s5:binding style="document" transport="http://schemas.xmlsoap.org/soap/http"/>
    		<s1:operation name="consultarHistorico">
    			<s5:operation soapAction=""/>
    			<s1:input>
    				<s5:body parts="consultarHistoricoRequest" use="literal"/>
    			</s1:input>
    			<s1:output>
    				<s5:body parts="Faturas" use="literal"/>
    			</s1:output>
    			<s1:fault name="erroInfoFault">
    				<s5:fault name="erroInfoFault" use="literal"/>
    			</s1:fault>
    		</s1:operation>
    		<s1:operation name="obterFatura">
    			<s5:operation soapAction=""/>
    			<s1:input>
    				<s5:body parts="obterFaturaRequest" use="literal"/>
    			</s1:input>
    			<s1:output>
    				<s5:body parts="Faturas" use="literal"/>
    			</s1:output>
    			<s1:fault name="erroInfoFault">
    				<s5:fault name="erroInfoFault" use="literal"/>
    			</s1:fault>
    		</s1:operation>
    		<s1:operation name="obterGrafico">
    			<s5:operation soapAction=""/>
    			<s1:input>
    				<s5:body parts="obterGraficoRequest" use="literal"/>
    			</s1:input>
    			<s1:output>
    				<s5:body parts="Fatura" use="literal"/>
    			</s1:output>
    			<s1:fault name="erroInfoFault">
    				<s5:fault name="erroInfoFault" use="literal"/>
    			</s1:fault>
    		</s1:operation>
    		<s1:operation name="buscarHistoricoFaturaComFiltro">
    			<s5:operation soapAction=""/>
    			<s1:input>
    				<s5:body parts="buscarHistoricoFaturaComFiltroRequest" use="literal"/>
    			</s1:input>
    			<s1:output>
    				<s5:body parts="Chamadas" use="literal"/>
    			</s1:output>
    			<s1:fault name="erroInfoFault">
    				<s5:fault name="erroInfoFault" use="literal"/>
    			</s1:fault>
    		</s1:operation>
    	</s1:binding>
    	<s1:service name="Fatura">
    		<s1:port name="FaturaPort" binding="s3:FaturaBinding">
    			<s5:address location="http://alsb3-soa:80/ContaOnline/Fatura"/>
    		</s1:port>
    	</s1:service>
    </s1:definitions>
 * ::
 */
