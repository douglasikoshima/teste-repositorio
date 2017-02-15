package br.com.vivo.fo.cliente;

import br.com.vivo.fo.cliente.vo.ParametrosVODocument.ParametrosVO;
import br.com.vivo.fo.commons.utils.businessDelegate.JATMIBusinessDelegate;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.workflow.vo.AtendimentoVODocument;
import br.com.vivo.fo.workflow.vo.AtendimentoVODocument.AtendimentoVO;
import br.com.vivo.fo.workflow.vo.DadosProtocoloVODocument.DadosProtocoloVO;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import noNamespace.MsgDocument;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlOptions;

@SuppressWarnings("deprecation")
public class ClienteUtils implements Serializable {

	private static final long serialVersionUID = 1L;

	public ClienteUtils() {
	}

	public static String registrarPalitagem(String user, ParametrosVO parametros, String idLinha, String nrLinha, String nmPath, String dsObservacao) {

		AtendimentoVO atendimentoVO = AtendimentoVO.Factory.newInstance();
		AtendimentoVO retornoRegContato = AtendimentoVO.Factory.newInstance();

		atendimentoVO.setIdLinhaAtendimento((parametros.getIdLinhaAtendimento() != null && !parametros.getIdLinhaAtendimento().equals(ConstantesCRM.SZERO)) ? parametros.getIdLinhaAtendimento() : parametros.getIdLinha());
		atendimentoVO.setIdUfOperadora(parametros.getIdUfOperadora() == null || parametros.getIdUfOperadora().equals(ConstantesCRM.SVAZIO) ? ConstantesCRM.SONE : parametros.getIdUfOperadora());
		atendimentoVO.setIdTipoLinha(parametros.getIdTipoLinha() == null || parametros.getIdTipoLinha().equals(ConstantesCRM.SVAZIO) ? ConstantesCRM.SONE : parametros.getIdTipoLinha());
		atendimentoVO.setIdChamadaTelefonica(Long.parseLong(parametros.getIdChamadaTelefonica() == null || parametros.getIdChamadaTelefonica().equals(ConstantesCRM.SVAZIO) ? ConstantesCRM.SONE : parametros.getIdChamadaTelefonica()));
		atendimentoVO.setIdGrupoAbertura(Long.parseLong(parametros.getIdGrupo() == null ? ConstantesCRM.SONE : parametros.getIdGrupo()));
		atendimentoVO.setInResponsavelAbertura(parametros.getIdTipoRelacionamento() == null ? ConstantesCRM.SONE : parametros.getIdTipoRelacionamento());
		atendimentoVO.setInTipoPessoa(parametros.getInTipoPessoa() == null || parametros.getInTipoPessoa().equals(ConstantesCRM.SVAZIO) ? "PF" : parametros.getInTipoPessoa());
		if (nrLinha != null && !ConstantesCRM.SVAZIO.equals(nrLinha)) {
			atendimentoVO.setNrTelefone(nrLinha);
		} else {
			atendimentoVO.setNrTelefone(parametros.getNrLinha() == null ? "1100000000" : parametros.getNrLinha());
		}
		atendimentoVO.setTpOperacao(ConstantesCRM.SONE);/*
		 * tpOperacao: 1=fechar, 2=encaminhar
		 */
		atendimentoVO.addNewProcedenciaVO().setIdProcedencia(1);/*
		 * Procedência: TELEFONE - ID 1
		 */
		atendimentoVO.addNewCanalVO().setIdCanal(1);/* Canal: CRC - ID 1 */

		if (idLinha != null && !ConstantesCRM.SVAZIO.equals(idLinha)) {
			atendimentoVO.addNewContas().addNewContaVO().addNewLinhaVO().setIdPessoaLinhaHistorico(Long.parseLong(idLinha));
		} else if (parametros.getIdLinha() != null) {
			if (!ConstantesCRM.SVAZIO.equals(parametros.getIdLinha())) {
				atendimentoVO.addNewContas().addNewContaVO().addNewLinhaVO().setIdPessoaLinhaHistorico(Long.parseLong(parametros.getIdLinha()));
			}
		}
		atendimentoVO.addNewPessoaVO().setIdPessoa(Long.parseLong(parametros.getIdClienteDePara() == null ? "21" : parametros.getIdClienteDePara()));
		atendimentoVO.addNewUsuarioLinhaVO().setIdPessoa(parametros.getIdUsuarioDePara() == null ? "21" : parametros.getIdUsuarioDePara());
		atendimentoVO.addNewArvoreAtendimentoVO().setNmPath(nmPath);
		atendimentoVO.getArvoreAtendimentoVO().addNewCarterizacaoVO().setIdTipoCarteira(Integer.parseInt(parametros.getIdTipoCarteira() == null || parametros.getIdTipoCarteira().equals(ConstantesCRM.SVAZIO) ? ConstantesCRM.SONE : parametros.getIdTipoCarteira()));
		atendimentoVO.getArvoreAtendimentoVO().addNewSegmentacaoVO().setIdSegmentacao(Integer.parseInt((parametros.getIdSegmentacao() == null || parametros.getIdSegmentacao().equals(ConstantesCRM.SVAZIO)) ? ConstantesCRM.SONE : parametros.getIdSegmentacao()));

		String nrProtocolo = parametros.getNrProtocolo();

		DadosProtocoloVO dadosProtocoloVO = DadosProtocoloVO.Factory.newInstance();

		if (idLinha != null && !ConstantesCRM.SVAZIO.equals(idLinha)) {
			dadosProtocoloVO.setIdTipoAberturaProtocolo(ConstantesCRM.STHREE);
		} else if (parametros.getIdLinha() != null && !ConstantesCRM.SVAZIO.equals(parametros.getIdLinha())) {
			dadosProtocoloVO.setIdTipoAberturaProtocolo(ConstantesCRM.STHREE);
		} else if (nrLinha != null && !ConstantesCRM.SVAZIO.equals(nrLinha)) {
			dadosProtocoloVO.setIdTipoAberturaProtocolo(ConstantesCRM.STWO);
		} else if (parametros.getIdClienteDePara() != null) {
			dadosProtocoloVO.setIdTipoAberturaProtocolo(ConstantesCRM.SFOUR);
		}
		dadosProtocoloVO.setIdSistemaOrigemProtocolo(ConstantesCRM.SSEVEN);
		dadosProtocoloVO.setNrProtocolo(nrProtocolo != null ? nrProtocolo : ConstantesCRM.SVAZIO);

		if (nrLinha != null && !ConstantesCRM.SVAZIO.equals(nrLinha)) {
			dadosProtocoloVO.setDddSMSProtocolo(nrLinha.substring(0, 2));
			dadosProtocoloVO.setNrLinhaSMSProtocolo(nrLinha.substring(2));
		}

		atendimentoVO.setDadosProtocoloVO(dadosProtocoloVO);
		atendimentoVO.setNrProtocolo(nrProtocolo != null ? nrProtocolo : ConstantesCRM.SVAZIO);
		atendimentoVO.setObservacao(dsObservacao);
		try {
			JATMIBusinessDelegate jatmi = new JATMIBusinessDelegate();
			String retorno = jatmi.executeCommnad(user, "REGCONTATO", atendimentoVO.xmlText());
			retornoRegContato = AtendimentoVODocument.Factory.parse(retorno).getAtendimentoVO();
			atendimentoVO = null;
			dadosProtocoloVO = null;
		} catch (Exception e) {
			return (e.getMessage());
		}
		return retornoRegContato.getNrProtocolo();
	}

	public static String palitagemPorCDPalito(String user, ParametrosVO parametros, String idLinha, String nrLinha, String cdServico, String dsObservacao) {

		AtendimentoVO atendimentoVO = AtendimentoVO.Factory.newInstance();
		AtendimentoVO retornoRegContato = AtendimentoVO.Factory.newInstance();

		atendimentoVO.setIdLinhaAtendimento((parametros.getIdLinhaAtendimento() != null && !parametros.getIdLinhaAtendimento().equals(ConstantesCRM.SZERO)) ? parametros.getIdLinhaAtendimento() : parametros.getIdLinha());
		atendimentoVO.setIdUfOperadora(parametros.getIdUfOperadora() == null || parametros.getIdUfOperadora().equals(ConstantesCRM.SVAZIO) ? ConstantesCRM.SONE : parametros.getIdUfOperadora());
		atendimentoVO.setIdTipoLinha(parametros.getIdTipoLinha() == null || parametros.getIdTipoLinha().equals(ConstantesCRM.SVAZIO) ? ConstantesCRM.SONE : parametros.getIdTipoLinha());
		atendimentoVO.setIdChamadaTelefonica(Long.parseLong(parametros.getIdChamadaTelefonica() == null || parametros.getIdChamadaTelefonica().equals(ConstantesCRM.SVAZIO) ? ConstantesCRM.SONE : parametros.getIdChamadaTelefonica()));
		atendimentoVO.setIdGrupoAbertura(Long.parseLong(parametros.getIdGrupo() == null ? ConstantesCRM.SONE : parametros.getIdGrupo()));
		atendimentoVO.setInResponsavelAbertura(parametros.getIdTipoRelacionamento() == null ? ConstantesCRM.SONE : parametros.getIdTipoRelacionamento());
		atendimentoVO.setInTipoPessoa(parametros.getInTipoPessoa() == null || parametros.getInTipoPessoa().equals(ConstantesCRM.SVAZIO) ? "PF" : parametros.getInTipoPessoa());
		if (nrLinha != null && !ConstantesCRM.SVAZIO.equals(nrLinha)) {
			atendimentoVO.setNrTelefone(nrLinha);
		} else {
			atendimentoVO.setNrTelefone(parametros.getNrLinha() == null ? "1100000000" : parametros.getNrLinha());
		}
		atendimentoVO.setTpOperacao(ConstantesCRM.SONE);/*
		 * tpOperacao: 1=fechar, 2=encaminhar
		 */
		atendimentoVO.addNewProcedenciaVO().setIdProcedencia(1);/*
		 * Procedência: TELEFONE - ID 1
		 */
		atendimentoVO.addNewCanalVO().setIdCanal(1);/* Canal: CRC - ID 1 */

		if (idLinha != null && !ConstantesCRM.SVAZIO.equals(idLinha)) {
			atendimentoVO.addNewContas().addNewContaVO().addNewLinhaVO().setIdPessoaLinhaHistorico(Long.parseLong(idLinha));
		} else if (parametros.getIdLinha() != null) {
			if (!ConstantesCRM.SVAZIO.equals(parametros.getIdLinha())) {
				atendimentoVO.addNewContas().addNewContaVO().addNewLinhaVO().setIdPessoaLinhaHistorico(Long.parseLong(parametros.getIdLinha()));
			}
		}
		atendimentoVO.addNewPessoaVO().setIdPessoa(Long.parseLong(parametros.getIdClienteDePara() == null ? "21" : parametros.getIdClienteDePara()));
		atendimentoVO.addNewUsuarioLinhaVO().setIdPessoa(parametros.getIdUsuarioDePara() == null ? "21" : parametros.getIdUsuarioDePara());
		atendimentoVO.addNewArvoreAtendimentoVO().setCdServico(cdServico);
		atendimentoVO.getArvoreAtendimentoVO().addNewCarterizacaoVO().setIdTipoCarteira(Integer.parseInt(parametros.getIdTipoCarteira() == null || parametros.getIdTipoCarteira().equals(ConstantesCRM.SVAZIO) ? ConstantesCRM.SONE : parametros.getIdTipoCarteira()));
		atendimentoVO.getArvoreAtendimentoVO().addNewSegmentacaoVO().setIdSegmentacao(Integer.parseInt((parametros.getIdSegmentacao() == null || parametros.getIdSegmentacao().equals(ConstantesCRM.SVAZIO)) ? ConstantesCRM.SONE : parametros.getIdSegmentacao()));

		String nrProtocolo = parametros.getNrProtocolo();

		DadosProtocoloVO dadosProtocoloVO = DadosProtocoloVO.Factory.newInstance();

		if (idLinha != null && !ConstantesCRM.SVAZIO.equals(idLinha)) {
			dadosProtocoloVO.setIdTipoAberturaProtocolo(ConstantesCRM.STHREE);
		} else if (parametros.getIdLinha() != null && !ConstantesCRM.SVAZIO.equals(parametros.getIdLinha())) {
			dadosProtocoloVO.setIdTipoAberturaProtocolo(ConstantesCRM.STHREE);
		} else if (nrLinha != null && !ConstantesCRM.SVAZIO.equals(nrLinha)) {
			dadosProtocoloVO.setIdTipoAberturaProtocolo(ConstantesCRM.STWO);
		} else if (parametros.getIdClienteDePara() != null) {
			dadosProtocoloVO.setIdTipoAberturaProtocolo(ConstantesCRM.SFOUR);
		}
		dadosProtocoloVO.setIdSistemaOrigemProtocolo(ConstantesCRM.SSEVEN);
		dadosProtocoloVO.setNrProtocolo(nrProtocolo != null ? nrProtocolo : ConstantesCRM.SVAZIO);

		if (nrLinha != null && !ConstantesCRM.SVAZIO.equals(nrLinha)) {
			dadosProtocoloVO.setDddSMSProtocolo(nrLinha.substring(0, 2));
			dadosProtocoloVO.setNrLinhaSMSProtocolo(nrLinha.substring(2));
		}

		atendimentoVO.setDadosProtocoloVO(dadosProtocoloVO);
		atendimentoVO.setNrProtocolo(nrProtocolo != null ? nrProtocolo : ConstantesCRM.SVAZIO);
		atendimentoVO.setObservacao(dsObservacao);
		try {
			JATMIBusinessDelegate jatmi = new JATMIBusinessDelegate();
			String xmlIN = atendimentoVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			String xmlOUT = jatmi.executeCommnad(user, "REGPALITAGEFO", xmlIN);
			
			xmlOUT = MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText();
			retornoRegContato = AtendimentoVODocument.Factory.parse(xmlOUT).getAtendimentoVO();
			
			atendimentoVO = null;
			dadosProtocoloVO = null;
		} catch (Exception e) {
			return (e.getMessage());
		}
		return retornoRegContato.getNrProtocolo();
	}

	/*
	 * Método utilizado para retorno de um XML limpo para conversão em Javscript Object (JSON).
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static String getCleanXMLFromXSD(Object obj, String[] stringsToDelete) throws Exception {

		Class cls = obj.getClass();
		String out = ConstantesCRM.SVAZIO;

		if (obj instanceof String) {
			return obj.toString();
		} else {

			XmlOptions xmlOptions = new XmlOptions();
			xmlOptions.setSaveAggresiveNamespaces();
			xmlOptions.setCharacterEncoding(ConstantesCRM.SISO);

			try {
				Method methodXmlText = cls.getMethod("xmlText", new Class[] { XmlOptions.class });
				Method methodSchemaType = cls.getMethod("schemaType", new Class[] {});

				Object objSchemaType = methodSchemaType.invoke(obj, new Object[] {});
				SchemaType schemaType = (SchemaType) objSchemaType;

				if (schemaType.getSourceName().toLowerCase().indexOf(".xsd") < 0) {
					throw new Exception("Objeto " + obj.toString() + " não é um XSD válido.");
				}

				Object objXML = methodXmlText.invoke(obj, new Object[] { xmlOptions });
				out = (String) objXML;
				out = out.replaceAll("xml-fragment", schemaType.getShortJavaName()).replaceAll(":vo", ConstantesCRM.SVAZIO).replaceAll("vo:", ConstantesCRM.SVAZIO).replaceAll("tns:", ConstantesCRM.SVAZIO).replaceAll("ns[0-9]:", ConstantesCRM.SVAZIO).replaceAll("vo[0-9]:", ConstantesCRM.SVAZIO).replaceAll("undefined", "xmlObject");

				if (stringsToDelete != null) {
					for (int i = 0; i < stringsToDelete.length; i++) {
						out = out.replaceAll(stringsToDelete[i], ConstantesCRM.SVAZIO);
					}
				}

			} catch (NoSuchMethodException ex) {
				throw new Exception("Método não encontrado.", ex);
			} catch (IllegalAccessException ex) {
				throw new Exception("Acesso não permitido.", ex);
			} catch (InvocationTargetException ex) {
				throw new Exception("Destino não encontrado.", ex);
			}
		}
		return out;
	}

}
