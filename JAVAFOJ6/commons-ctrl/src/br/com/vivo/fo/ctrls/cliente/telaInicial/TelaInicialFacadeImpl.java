package br.com.vivo.fo.ctrls.cliente.telaInicial;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import noNamespace.MsgDocument;

import org.apache.xmlbeans.XmlException;

import weblogic.ejbgen.Session;
import weblogic.ejbgen.Constants.Bool;
import weblogic.ejbgen.Session.SessionType;

import br.com.vivo.fo.atmi.TuxedoServiceCall;
import br.com.vivo.fo.atmi.TuxedoServiceCallerException;
import br.com.vivo.fo.cliente.vo.AbaServicosFiltroVODocument;
import br.com.vivo.fo.cliente.vo.AbaServicosFiltroVODocument.AbaServicosFiltroVO;
import br.com.vivo.fo.cliente.vo.CarregarNovoEnderecoVODocument;
import br.com.vivo.fo.cliente.vo.CarregarNovoEnderecoVODocument.CarregarNovoEnderecoVO;
import br.com.vivo.fo.cliente.vo.ComunicacoesRecusadasVODocument;
import br.com.vivo.fo.cliente.vo.ComunicacoesRecusadasVODocument.ComunicacoesRecusadasVO;
import br.com.vivo.fo.cliente.vo.ConvergenciaVODocument;
import br.com.vivo.fo.cliente.vo.ConvergenciaVODocument.ConvergenciaVO;
import br.com.vivo.fo.cliente.vo.DadosChipVODocument;
import br.com.vivo.fo.cliente.vo.DadosChipVODocument.DadosChipVO;
import br.com.vivo.fo.cliente.vo.DesbloqueioGsmVODocument;
import br.com.vivo.fo.cliente.vo.DesbloqueioGsmVODocument.DesbloqueioGsmVO;
import br.com.vivo.fo.cliente.vo.EnderecoVODocument;
import br.com.vivo.fo.cliente.vo.EnderecoVODocument.EnderecoVO;
import br.com.vivo.fo.cliente.vo.HistoricoAtendimentoVODocument;
import br.com.vivo.fo.cliente.vo.HistoricoAtendimentoVODocument.HistoricoAtendimentoVO;
import br.com.vivo.fo.cliente.vo.HistoricoServicosVODocument;
import br.com.vivo.fo.cliente.vo.HistoricoServicosVODocument.HistoricoServicosVO;
import br.com.vivo.fo.cliente.vo.LinhasPorIdVODocument;
import br.com.vivo.fo.cliente.vo.LinhasPorIdVODocument.LinhasPorIdVO;
import br.com.vivo.fo.cliente.vo.ListaTipoComunicacaoVODocument;
import br.com.vivo.fo.cliente.vo.ListaTipoComunicacaoVODocument.ListaTipoComunicacaoVO;
import br.com.vivo.fo.cliente.vo.LupaCarteirizacaoPFVODocument;
import br.com.vivo.fo.cliente.vo.LupaCarteirizacaoPFVODocument.LupaCarteirizacaoPFVO;
import br.com.vivo.fo.cliente.vo.LupaCarteirizacaoPJVODocument;
import br.com.vivo.fo.cliente.vo.LupaCarteirizacaoPJVODocument.LupaCarteirizacaoPJVO;
import br.com.vivo.fo.cliente.vo.LupaClienteVODocument;
import br.com.vivo.fo.cliente.vo.LupaClienteVODocument.LupaClienteVO;
import br.com.vivo.fo.cliente.vo.LupaLinhaAbasGSMVODocument;
import br.com.vivo.fo.cliente.vo.LupaLinhaAbasGSMVODocument.LupaLinhaAbasGSMVO;
import br.com.vivo.fo.cliente.vo.LupaLinhaVODocument;
import br.com.vivo.fo.cliente.vo.LupaLinhaVODocument.LupaLinhaVO;
import br.com.vivo.fo.cliente.vo.LupaLinhaVODocument.LupaLinhaVO.FiltroLinhaVO;
import br.com.vivo.fo.cliente.vo.LupaSegmentacaoVODocument;
import br.com.vivo.fo.cliente.vo.LupaSegmentacaoVODocument.LupaSegmentacaoVO;
import br.com.vivo.fo.cliente.vo.MeioContatoVODocument;
import br.com.vivo.fo.cliente.vo.MeioContatoVODocument.MeioContatoVO;
import br.com.vivo.fo.cliente.vo.ParametroVODocument;
import br.com.vivo.fo.cliente.vo.ParametroVODocument.ParametroVO;
import br.com.vivo.fo.cliente.vo.ParametrosVODocument;
import br.com.vivo.fo.cliente.vo.ParametrosVODocument.ParametrosVO;
import br.com.vivo.fo.cliente.vo.PesquisaClienteVODocument.PesquisaClienteVO;
import br.com.vivo.fo.cliente.vo.PesquisaEnderecoVODocument;
import br.com.vivo.fo.cliente.vo.PesquisaEnderecoVODocument.PesquisaEnderecoVO;
import br.com.vivo.fo.cliente.vo.PesquisaTIVODocument;
import br.com.vivo.fo.cliente.vo.PesquisaTIVODocument.PesquisaTIVO;
import br.com.vivo.fo.cliente.vo.PortabilidadeVODocument;
import br.com.vivo.fo.cliente.vo.PortabilidadeVODocument.PortabilidadeVO;
import br.com.vivo.fo.cliente.vo.ReloadLinhaVODocument;
import br.com.vivo.fo.cliente.vo.ReloadLinhaVODocument.ReloadLinhaVO;
import br.com.vivo.fo.cliente.vo.TIClienteDocument;
import br.com.vivo.fo.cliente.vo.TIClienteDocument.TICliente;
import br.com.vivo.fo.cliente.vo.TIFaturamentoDocument;
import br.com.vivo.fo.cliente.vo.TIFaturamentoDocument.TIFaturamento;
import br.com.vivo.fo.cliente.vo.TILinhaDocument;
import br.com.vivo.fo.cliente.vo.TILinhaDocument.TILinha;
import br.com.vivo.fo.cliente.vo.TelaInicialVODocument;
import br.com.vivo.fo.cliente.vo.TelaInicialVODocument.TelaInicialVO;
import br.com.vivo.fo.cliente.vo.TrackingAparelhosVODocument;
import br.com.vivo.fo.cliente.vo.TrackingAparelhosVODocument.TrackingAparelhosVO;
import br.com.vivo.fo.commons.utils.geral.TuxedoServiceBridge;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.retornotux.vo.RetornoVODocument;
import br.com.vivo.fo.retornotux.vo.RetornoVODocument.RetornoVO;
import br.com.vivo.fo.usuario.vo.CanaisExistentesVODocument;
import br.com.vivo.fo.usuario.vo.CanaisExistentesVODocument.CanaisExistentesVO;
import br.com.vivo.fo.usuario.vo.CanaisRelacionadosVODocument;
import br.com.vivo.fo.usuario.vo.CanaisRelacionadosVODocument.CanaisRelacionadosVO;
import br.com.vivo.fo.usuario.vo.UsuarioVODocument;
import br.com.vivo.fo.usuario.vo.UsuarioVODocument.UsuarioVO;
import br.com.vivo.fo.workflow.vo.WFExecucaoDocument.WFExecucao;
import br.com.vivo.fo.xml.RetornoTuxedo;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name = "TelaInicialFacade", mappedName = "TelaInicialFacade")
@Local(TelaInicialFacade.class)
@Session(ejbName = "TelaInicialFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class TelaInicialFacadeImpl implements TelaInicialFacade {

	@EJB
	private TuxedoServiceCall tuxedo;

	private transient Logger log = new Logger("clientes");

	/**
	 * @common:operation
	 */
	public TelaInicialVO getTelaInicialById(String user, String idLinhaTelefonica) throws TuxedoException, FacadeException {
		TelaInicialVO telaInicial;
		String xmlIN = ConstantesCRM.SVAZIO;
		String xmlOUT = ConstantesCRM.SVAZIO;
		try {
			xmlIN = "<idLinhaTelefonica>" + idLinhaTelefonica.trim() + "</idLinhaTelefonica>";

			xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "CarregaTI", xmlIN));

			telaInicial = TelaInicialVODocument.Factory.parse(MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText()).getTelaInicialVO();

			if (ConstantesCRM.SVAZIO.equals(telaInicial.getTILinha().getIdLinha())) {
				xmlIN = "<idLinhaTelefonica>" + idLinhaTelefonica.trim() + "</idLinhaTelefonica>";

				xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "CARREGATIPORT", xmlIN));

				telaInicial = TelaInicialVODocument.Factory.parse(MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText()).getTelaInicialVO();
				if (!ConstantesCRM.SVAZIO.equals(telaInicial.getTICliente().getIdPessoa()) && !ConstantesCRM.SVAZIO.equals(telaInicial.getTICliente().getIdPessoaDePara())) {
					telaInicial.getTILinha().setIsPortada(ConstantesCRM.SONE);
				} else {
					telaInicial.getTILinha().setIsPortada(ConstantesCRM.SZERO);
				}
			} else {
				telaInicial.getTILinha().setIsPortada(ConstantesCRM.SZERO);
			}

		} catch (XmlException ex) {
			log.error("XmlException - TelaInicialFacadeImpl:getTelaInicial(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: TelaInicialFacadeImpl:getTelaInicial", ex));

		} catch (Exception ex) {
			log.error("Exception - TelaInicialFacadeImpl:getTelaInicial(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
		return telaInicial;
	}

    public TelaInicialVO getTelaInicial(String user, String idLinhaTelefonica) throws TuxedoException, FacadeException {
        TelaInicialVO telaInicial;
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
        try {
            xmlIN = "<nrLinha>" + idLinhaTelefonica.trim() + "</nrLinha>";

            xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "CarregaTI", xmlIN));

            telaInicial = TelaInicialVODocument.Factory.parse(MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText()).getTelaInicialVO();

            if (ConstantesCRM.SVAZIO.equals(telaInicial.getTILinha().getIdLinha())) {
                xmlIN = "<nrLinha>" + idLinhaTelefonica.trim() + "</nrLinha>";

                xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "CARREGATIPORT", xmlIN));

                telaInicial = TelaInicialVODocument.Factory.parse(MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText()).getTelaInicialVO();
                if (!ConstantesCRM.SVAZIO.equals(telaInicial.getTICliente().getIdPessoa()) && !ConstantesCRM.SVAZIO.equals(telaInicial.getTICliente().getIdPessoaDePara())) {
                    telaInicial.getTILinha().setIsPortada(ConstantesCRM.SONE);
                } else {
                    telaInicial.getTILinha().setIsPortada(ConstantesCRM.SZERO);
                }
            } else {
                telaInicial.getTILinha().setIsPortada(ConstantesCRM.SZERO);
            }

        } catch (XmlException ex) {
            log.error("XmlException - TelaInicialFacadeImpl:getTelaInicial(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: TelaInicialFacadeImpl:getTelaInicial", ex));

        } catch (Exception ex) {
            log.error("Exception - TelaInicialFacadeImpl:getTelaInicial(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
        return telaInicial;
    }
	
	/**
	 * @common:operation
	 */
	public ConvergenciaVO getLinhasConvergentes(String user, ConvergenciaVO convergenciaVO) throws TuxedoException, FacadeException {
		ConvergenciaVO convergenciaVORet = ConvergenciaVO.Factory.newInstance();
		try {
			String xmlIN = convergenciaVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			String xmlOUT = ConstantesCRM.SVAZIO;
			xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "GETLINHACONV", xmlIN));
			convergenciaVORet = ConvergenciaVODocument.Factory.parse(MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText()).getConvergenciaVO();
		} catch (Exception ex) {
			log.error("Exception - getLinhasConvergentes(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
		return convergenciaVORet;
	}

	/**
	 * @common:operation
	 */
	public LupaClienteVO getLupaClienteVO(String user, String idPessoa, String nrLinha, String idContaSistemaOrigem, int idTipoLinha) throws TuxedoException, FacadeException {
		LupaClienteVO lupaCliente;
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
		try {
			xmlIN = "<nrLinha>" + nrLinha + "</nrLinha><idPessoa>" + idPessoa + "</idPessoa><idcontasistemaorigem>" + idContaSistemaOrigem + "</idcontasistemaorigem>";
			xmlIN = xmlIN + "<idTipoLinha>" + idTipoLinha + "</idTipoLinha>";

			xmlOUT = tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user, "LCInicial", xmlIN));

			LupaClienteVODocument lupaClientedoc = LupaClienteVODocument.Factory.parse(MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText());
			lupaCliente = lupaClientedoc.getLupaClienteVO();

		} catch (XmlException ex) {
			log.error("XmlException - TelaInicialFacadeImpl:getLupaClienteVO(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: TelaInicialFacadeImpl:getLupaClienteVO", ex));

		} catch (Exception ex) {
			log.error("Exception - TelaInicialFacadeImpl:getLupaClienteVO(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
		return lupaCliente;
	}

	/**
	 * @common:operation
	 */
	public LupaLinhaVO getLupaLinhaVO(FiltroLinhaVO filtroLinhaVO, String user, String nrLinha, String idLinhaTelefonica, int idTipoLinha) throws TuxedoException, FacadeException {
		LupaLinhaVO lupaLinha = LupaLinhaVO.Factory.newInstance();
		String xmlIN = ConstantesCRM.SVAZIO;
		String xmlOUT = ConstantesCRM.SVAZIO;
		try {
			xmlIN = filtroLinhaVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			if (idLinhaTelefonica != null && !idLinhaTelefonica.equals(ConstantesCRM.SVAZIO)) {
				xmlIN = xmlIN + "<idLinhaTelefonica>" + idLinhaTelefonica + "</idLinhaTelefonica>";
			}
			xmlIN = xmlIN + "<nrLinha>" + nrLinha + "</nrLinha>";
			xmlIN = xmlIN + "<idTipoLinha>" + idTipoLinha + "</idTipoLinha>";
			xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "LupaLinhaIni", xmlIN));

			lupaLinha = LupaLinhaVODocument.Factory.parse(MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText()).getLupaLinhaVO();

		} catch (XmlException ex) {
			log.error("XmlException - TelaInicialFacadeImpl:getLupaLinhaVO(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: TelaInicialFacadeImpl:getLupaLinhaVO", ex));

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - TelaInicialFacadeImpl:getLupaLinhaVO(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - TelaInicialFacadeImpl:getLupaLinhaVO(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
		return lupaLinha;
	}

	/**
	 * @common:operation
	 */
	public LupaLinhaVO obterLinhaVO(FiltroLinhaVO filtroLinhaVO, String user) throws TuxedoException, FacadeException {
		LupaLinhaVO lupaLinha = LupaLinhaVO.Factory.newInstance();
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
		try {
			xmlIN = filtroLinhaVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);

			xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "FILTROLUPALIN", xmlIN));

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

			lupaLinha = LupaLinhaVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getLupaLinhaVO();

		} catch (XmlException ex) {
			log.error("XmlException - TelaInicialFacadeImpl:obterLinhaVO(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: TelaInicialFacadeImpl:obterLinhaVO", ex));

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - TelaInicialFacadeImpl:obterLinhaVO(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - TelaInicialFacadeImpl:obterLinhaVO(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
		return lupaLinha;
	}

	/**
	 * @common:operation
	 */
	public LupaLinhaVO consultaLinhaVOPorIdProcesso(String user, String idProcesso) throws TuxedoException, FacadeException {
		LupaLinhaVO lupaLinha = LupaLinhaVO.Factory.newInstance();
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
		try {
			xmlIN = "<idProcesso>" + idProcesso + "</idProcesso>";
			xmlIN += "<dsOperacao>dadosLinhaVOProcesso</dsOperacao>";

			xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "AbaGeral", xmlIN));

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

			lupaLinha = LupaLinhaVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getLupaLinhaVO();

		} catch (XmlException ex) {
			log.error("XmlException - TelaInicialFacadeImpl:consultaLinhaVOPorIdAtendimento(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: TelaInicialFacadeImpl:consultaLinhaVOPorIdAtendimento", ex));

		} catch (Exception ex) {
			log.error("Exception - TelaInicialFacadeImpl:consultaLinhaVOPorIdAtendimento(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
		return lupaLinha;
	}

	/**
	 * @common:operation
	 */
	public LupaLinhaVO consultaLinhaVO(FiltroLinhaVO filtroLinhaVO, String user, String nrLinhaFiltro, int idTipoLinha) throws TuxedoException, FacadeException {
		LupaLinhaVO lupaLinha = LupaLinhaVO.Factory.newInstance();
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
		try {
			xmlIN = filtroLinhaVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = xmlIN + "<nrLinha>" + nrLinhaFiltro + "</nrLinha>";
			xmlIN = xmlIN + "<idTipoLinha>" + idTipoLinha + "</idTipoLinha>";

			xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "AbaGeral", xmlIN));

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

			lupaLinha = LupaLinhaVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getLupaLinhaVO();

		} catch (XmlException ex) {
			log.error("XmlException - TelaInicialFacadeImpl:consultaLinhaVO(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: TelaInicialFacadeImpl:consultaLinhaVO", ex));

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - TelaInicialFacadeImpl:consultaLinhaVO(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - TelaInicialFacadeImpl:consultaLinhaVO(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
		return lupaLinha;
	}

	/**
	 * @common:operation
	 */
	public void salvarLinhaVO(FiltroLinhaVO filtroLinhaVO, String user) throws TuxedoException, FacadeException {
        String xmlIN = ConstantesCRM.SVAZIO;
		try {
			xmlIN = filtroLinhaVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);

			tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "DivNumeroAlt", xmlIN));

		} catch (XmlException ex) {
			log.error("XmlException - TelaInicialFacadeImpl:salvarLinhaVO(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: TelaInicialFacadeImpl:salvarLinhaVO", ex));

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - TelaInicialFacadeImpl:salvarLinhaVO(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - TelaInicialFacadeImpl:salvarLinhaVO(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public LupaCarteirizacaoPFVO getLupaCarteirizacaoPFVO(String user, String idPessoa) throws TuxedoException, FacadeException {
		LupaCarteirizacaoPFVO carteirizacaoPF = LupaCarteirizacaoPFVO.Factory.newInstance();
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
		try {
			xmlIN = "<idPessoa>" + idPessoa + "</idPessoa>";

			xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "LupaCarteiraPF", xmlIN));

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

			carteirizacaoPF = LupaCarteirizacaoPFVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getLupaCarteirizacaoPFVO();

		} catch (XmlException ex) {
			log.error("XmlException - TelaInicialFacadeImpl:getLupaCarteirizacaoPFVO(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: TelaInicialFacadeImpl:getLupaCarteirizacaoPFVO", ex));

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - TelaInicialFacadeImpl:getLupaCarteirizacaoPFVO(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - TelaInicialFacadeImpl:getLupaCarteirizacaoPFVO(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
		return carteirizacaoPF;
	}

	/**
	 * @common:operation
	 */
	public LupaCarteirizacaoPJVO getLupaCarteirizacaoPJVO(String user, String idPessoa) throws TuxedoException, FacadeException {
		LupaCarteirizacaoPJVO carteirizacaoPJ = LupaCarteirizacaoPJVO.Factory.newInstance();
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
		try {
			xmlIN = XmlManager.MakeXmlIn(user, "LupaCarteiraPJ", "<idPessoa>" + idPessoa + "</idPessoa>");

			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

			carteirizacaoPJ = LupaCarteirizacaoPJVODocument.Factory.parse(MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText()).getLupaCarteirizacaoPJVO();

		} catch (XmlException ex) {
			log.error("XmlException - TelaInicialFacadeImpl:getLupaCarteirizacaoPJVO(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: TelaInicialFacadeImpl:getLupaCarteirizacaoPJVO", ex));

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - TelaInicialFacadeImpl:getLupaCarteirizacaoPJVO(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - TelaInicialFacadeImpl:getLupaCarteirizacaoPJVO(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
		return carteirizacaoPJ;
	}

	/**
	 * @common:operation
	 */
	public LupaSegmentacaoVO getLupaSegmentacaoVO(String user, String idPessoa) throws TuxedoException, FacadeException {
		LupaSegmentacaoVO segmentacao = LupaSegmentacaoVO.Factory.newInstance();
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
		try {
			xmlIN = "<idPessoa>" + idPessoa + "</idPessoa>";

			xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "DetSegConsulta", xmlIN));

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

			segmentacao = LupaSegmentacaoVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getLupaSegmentacaoVO();

		} catch (XmlException ex) {
			log.error("XmlException - TelaInicialFacadeImpl:getLupaSegmentacaoVO(" + user + ") - [" + ex.getMessage() + "]");
			log.error("xmlOUT := " + xmlOUT);
			throw (new FacadeException("Erro na montagem do XML de entrada: TelaInicialFacadeImpl:getLupaSegmentacaoVO", ex));

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - TelaInicialFacadeImpl:getLupaSegmentacaoVO(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - TelaInicialFacadeImpl:getLupaSegmentacaoVO(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
		return segmentacao;
	}

	/**
	 * @common:operation
	 */
	public PesquisaClienteVO getClientes(String pesquisa, String valor) throws TuxedoException, FacadeException {
		PesquisaClienteVO pesquisaCliente = PesquisaClienteVO.Factory.newInstance();
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
		try {
			xmlIN = "<pesquisa>" + pesquisa + "</pesquisa><valor>" + valor + "</valor>";

			xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn("CustomUser", "GetClientes", xmlIN));

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - TelaInicialFacadeImpl:getLupaSegmentacaoVO(CustomUser) - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (XmlException ex) {
			log.error("XmlException - TelaInicialFacadeImpl:getClientes(" + pesquisa + ", " + valor + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: TelaInicialFacadeImpl:getClientes", ex));
		}
		
		String[] retTux = new String[0];
		try {
			retTux = XmlManager.ParseXmlOut(xmlOUT);
		} catch (Exception ex) {
			log.error("Exception - TelaInicialFacadeImpl:getClientes(" + pesquisa + ", " + valor + ") - [" + ex.getMessage() + "]");
			throw (new TuxedoException(ex));
		}
		
		RetornoTuxedo.TrataCodigoExecucao(getClass(), retTux);
		return pesquisaCliente;
	}

	/**
	 * @common:operation
	 */
	public CanaisExistentesVO getCanaisExistentesVO(String user) throws TuxedoException, FacadeException {
		CanaisExistentesVO canaisExistentes = CanaisExistentesVO.Factory.newInstance();
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
		try {
			xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "LCanal", xmlIN));

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

			canaisExistentes = CanaisExistentesVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getCanaisExistentesVO();

		} catch (XmlException ex) {
			log.error("XmlException - TelaInicialFacadeImpl:getCanaisExistentesVO(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: TelaInicialFacadeImpl:getCanaisExistentesVO", ex));

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - TelaInicialFacadeImpl:getCanaisExistentesVO(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - TelaInicialFacadeImpl:getCanaisExistentesVO(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
		return canaisExistentes;
	}

	/**
	 * @common:operation
	 */
	public CanaisRelacionadosVO getCanaisRelacionadosVO(CanaisRelacionadosVO canaisRelacionadosVO, String user) throws TuxedoException, FacadeException {
		CanaisRelacionadosVO canaisRelacionados = CanaisRelacionadosVO.Factory.newInstance();
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
		try {
			xmlIN = canaisRelacionadosVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);

			xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "LCANALPREFERI", xmlIN));

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

			canaisRelacionados = CanaisRelacionadosVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getCanaisRelacionadosVO();

		} catch (XmlException ex) {
			log.error("XmlException - TelaInicialFacadeImpl:getCanaisRelacionadosVO(" + user + ", " + canaisRelacionadosVO + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: TelaInicialFacadeImpl:getCanaisRelacionadosVO", ex));

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - TelaInicialFacadeImpl:getCanaisRelacionadosVO(" + user + ", " + canaisRelacionadosVO + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - TelaInicialFacadeImpl:getCanaisRelacionadosVO(" + user + ", " + canaisRelacionadosVO + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
		return canaisRelacionados;
	}

	/**
	 * @common:operation
	 */
	public MeioContatoVO getMeioContatoVO(String user) throws TuxedoException, FacadeException {
		MeioContatoVO meioContato = MeioContatoVO.Factory.newInstance();
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
		try {
			xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "LMeioContato", xmlIN));

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

			meioContato = MeioContatoVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getMeioContatoVO();

		} catch (XmlException ex) {
			log.error("XmlException - TelaInicialFacadeImpl:getMeioContatoVO(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: TelaInicialFacadeImpl:getMeioContatoVO", ex));

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - TelaInicialFacadeImpl:getMeioContatoVO(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - TelaInicialFacadeImpl:getMeioContatoVO(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
		return meioContato;
	}

	/**
	 * @common:operation
	 */
	public ComunicacoesRecusadasVO getComunicacoesRecusadasVO(ComunicacoesRecusadasVO recusadasVO, String user) throws TuxedoException, FacadeException {
		ComunicacoesRecusadasVO recusadas = ComunicacoesRecusadasVO.Factory.newInstance();
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
		try {
			xmlIN = recusadasVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);

			xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "LRECUSAMCONTA", xmlIN));

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

			recusadas = ComunicacoesRecusadasVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getComunicacoesRecusadasVO();

		} catch (XmlException ex) {
			log.error("XmlException - TelaInicialFacadeImpl:getComunicacoesRecusadasVO(" + user + ", " + recusadasVO + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: TelaInicialFacadeImpl:getComunicacoesRecusadasVO", ex));

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - TelaInicialFacadeImpl:getComunicacoesRecusadasVO(" + user + ", " + recusadasVO + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - TelaInicialFacadeImpl:getComunicacoesRecusadasVO(" + user + ", " + recusadasVO + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
		return recusadas;
	}

	/**
	 * @common:operation
	 */
	public void salvarCanaisRelacionados(CanaisRelacionadosVO canaisRelacionados, String user) throws TuxedoException, FacadeException {
        String xmlIN = ConstantesCRM.SVAZIO;
		try {
			xmlIN = canaisRelacionados.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);

			tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "ACANALPREFERI", xmlIN));

		} catch (XmlException ex) {
			log.error("XmlException - TelaInicialFacadeImpl:salvarCanaisRelacionados(" + user + ", " + canaisRelacionados + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: TelaInicialFacadeImpl:salvarCanaisRelacionados", ex));

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - TelaInicialFacadeImpl:salvarCanaisRelacionados(" + user + ", " + canaisRelacionados + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - TelaInicialFacadeImpl:salvarCanaisRelacionados(" + user + ", " + canaisRelacionados + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public void salvarComunicacoesRecusadas(ComunicacoesRecusadasVO recusadas, String user) throws TuxedoException, FacadeException {
        String xmlIN = ConstantesCRM.SVAZIO;
		try {
			xmlIN = recusadas.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);

			tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "ARECUSAMCONTA", xmlIN));

		} catch (XmlException ex) {
			log.error("XmlException - TelaInicialFacadeImpl:salvarComunicacoesRecusadas(" + user + ", " + recusadas + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: TelaInicialFacadeImpl:salvarComunicacoesRecusadas", ex));

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - TelaInicialFacadeImpl:salvarComunicacoesRecusadas(" + user + ", " + recusadas + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - TelaInicialFacadeImpl:salvarComunicacoesRecusadas(" + user + ", " + recusadas + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public String setSalvarNovoEndereco(String user, LupaClienteVO enderecoIncluido) throws TuxedoException, FacadeException {
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
		try {
			log.debug("TelaInicialFacadeImpl:setSalvarNovoEndereco(" + user + ", " + enderecoIncluido + ")");
			xmlIN = "<acao>INCLUIR</acao>" + enderecoIncluido.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);

			xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "AbaEndereco", xmlIN));
			xmlOUT = MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().toString();
			if (xmlOUT.equals("<xml-fragment/>")) {
				xmlOUT = ConstantesCRM.SVAZIO;
			}

		} catch (XmlException ex) {
			log.error("XmlException - TelaInicialFacadeImpl:setSalvarNovoEndereco(" + user + ", " + enderecoIncluido + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: TelaInicialFacadeImpl:setSalvarNovoEndereco", ex));

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - TelaInicialFacadeImpl:setSalvarNovoEndereco(" + user + ", " + enderecoIncluido + ") - [" + ex.getMessage() + "]");
			xmlOUT = ex.getMessage();

		} catch (Exception ex) {
			log.error("Exception - TelaInicialFacadeImpl:setSalvarNovoEndereco(" + user + ", " + enderecoIncluido + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
		return xmlOUT;
	}

	/**
	 * @common:operation
	 */
	public String setSalvarAlterarEndereco(String user, LupaClienteVO enderecoAlterado) throws TuxedoException, FacadeException {
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
		try {
			log.debug("TelaInicialFacadeImpl:setSalvarAlterarEndereco(" + user + ", " + enderecoAlterado + ")");
			xmlIN = "<acao>ALTERAR</acao>" + enderecoAlterado.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);

			xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "AbaEndereco", xmlIN));
			xmlOUT = MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText();
			if (xmlOUT.equals("<xml-fragment/>")) {
				xmlOUT = ConstantesCRM.SVAZIO;
			}

		} catch (XmlException ex) {
			log.error("XmlException - TelaInicialFacadeImpl:setSalvarAlterarEndereco(" + user + ", " + enderecoAlterado + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: TelaInicialFacadeImpl:setSalvarAlterarEndereco", ex));

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - TelaInicialFacadeImpl:setSalvarAlterarEndereco(" + user + ", " + enderecoAlterado + ") - [" + ex.getMessage() + "]");
			xmlOUT = ex.getMessage();

		} catch (Exception ex) {
			log.error("Exception - TelaInicialFacadeImpl:setSalvarAlterarEndereco(" + user + ", " + enderecoAlterado + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
		return xmlOUT;
	}

	/**
	 * @common:operation
	 */
	public CarregarNovoEnderecoVO getIncluirEndereco(String user) throws TuxedoException, FacadeException {
		CarregarNovoEnderecoVO listasNovoEndereco = CarregarNovoEnderecoVO.Factory.newInstance();
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
		try {
			xmlIN = ConstantesCRM.SVAZIO;

			xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "MANUENDERECOI", xmlIN));

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

			listasNovoEndereco = CarregarNovoEnderecoVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getCarregarNovoEnderecoVO();

		} catch (XmlException ex) {
			log.error("XmlException - TelaInicialFacadeImpl:getIncluirEndereco(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: TelaInicialFacadeImpl:getIncluirEndereco", ex));

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - TelaInicialFacadeImpl:getIncluirEndereco(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - TelaInicialFacadeImpl:getIncluirEndereco(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
		return listasNovoEndereco;
	}

	/**
	 * @common:operation
	 */
	public void setExcluirEndereco(String user, LupaClienteVO enderecoExcluido) throws TuxedoException, FacadeException {
        String xmlIN = ConstantesCRM.SVAZIO;
		try {
			log.debug("TelaInicialFacadeImpl:setExcluirEndereco(" + user + ", " + enderecoExcluido + ")");
			xmlIN = "<acao>EXCLUIR</acao>";
			xmlIN = xmlIN + enderecoExcluido.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);

			tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "AbaEndereco", xmlIN));

		} catch (XmlException ex) {
			log.error("XmlException - TelaInicialFacadeImpl:setExcluirEndereco(" + user + ", " + enderecoExcluido + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: TelaInicialFacadeImpl:setExcluirEndereco", ex));

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - TelaInicialFacadeImpl:setExcluirEndereco(" + user + ", " + enderecoExcluido + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - TelaInicialFacadeImpl:setExcluirEndereco(" + user + ", " + enderecoExcluido + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public String setSalvarNovaComunicacao(String user, LupaClienteVO comunicacaoIncluida) throws TuxedoException, FacadeException {
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
		try {
			log.debug("TelaInicialFacadeImpl:setSalvarNovaComunicacao(" + user + ", " + comunicacaoIncluida + ")");
			xmlIN = "<acao>INCLUIR</acao>";
			xmlIN = xmlIN + comunicacaoIncluida.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);

			xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "AbaComunicacao", xmlIN));

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			if ("<xml-fragment/>".equals(xmlOUT)) {
				xmlOUT = ConstantesCRM.SVAZIO;
			}

		} catch (XmlException ex) {
			log.error("XmlException - TelaInicialFacadeImpl:setSalvarNovaComunicacao(" + user + ", " + comunicacaoIncluida + ") - [" + ex.getMessage() + "]");
			log.error("xmlOUT := " + xmlOUT);
			throw (new FacadeException("Erro na montagem do XML de entrada: TelaInicialFacadeImpl:setSalvarNovaComunicacao", ex));

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - TelaInicialFacadeImpl:setSalvarNovaComunicacao(" + user + ", " + comunicacaoIncluida + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - TelaInicialFacadeImpl:setSalvarNovaComunicacao(" + user + ", " + comunicacaoIncluida + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
		return xmlOUT;
	}

	/**
	 * @common:operation
	 */
	public String setSalvarAlterarComunicacao(String user, LupaClienteVO comunicacaoAlterada, String idPessoa) throws TuxedoException, FacadeException {
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
		try {
			log.debug("TelaInicialFacadeImpl:setSalvarAlterarComunicacao(" + user + ", " + comunicacaoAlterada + ")");
			xmlIN = "<acao>ALTERAR</acao><idPessoa>" + idPessoa + "</idPessoa>";
			xmlIN = xmlIN + comunicacaoAlterada.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);

			xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "AbaComunicacao", xmlIN));

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			if ("<xml-fragment/>".equals(xmlOUT)) {
                xmlOUT = ConstantesCRM.SVAZIO;
            }

		} catch (XmlException ex) {
			log.error("XmlException - TelaInicialFacadeImpl:setSalvarAlterarComunicacao(" + user + ", " + comunicacaoAlterada + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: TelaInicialFacadeImpl:setSalvarAlterarComunicacao", ex));

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - TelaInicialFacadeImpl:setSalvarAlterarComunicacao(" + user + ", " + comunicacaoAlterada + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - TelaInicialFacadeImpl:setSalvarAlterarComunicacao(" + user + ", " + comunicacaoAlterada + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
		return xmlOUT;
	}

	/**
	 * @common:operation
	 */
	public void setExcluirComunicacao(String user, LupaClienteVO comunicacaoExcluida) throws TuxedoException, FacadeException {
        String xmlIN = ConstantesCRM.SVAZIO;
		try {
			log.debug("TelaInicialFacadeImpl:setExcluirComunicacao(" + user + ", " + comunicacaoExcluida + ")");
			xmlIN = "<acao>EXCLUIR</acao>";
			xmlIN += comunicacaoExcluida.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);

			tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "AbaComunicacao", xmlIN));

		} catch (XmlException ex) {
			log.error("XmlException - TelaInicialFacadeImpl:setExcluirComunicacao(" + user + ", " + comunicacaoExcluida + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: TelaInicialFacadeImpl:setExcluirComunicacao", ex));

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - TuxedoException - TelaInicialFacadeImpl:setExcluirComunicacao(" + user + ", " + comunicacaoExcluida + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - TelaInicialFacadeImpl:setExcluirComunicacao(" + user + ", " + comunicacaoExcluida + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public LupaClienteVO getContato(String user, String idPessoa) throws TuxedoException, FacadeException {
		LupaClienteVO comunicacao = LupaClienteVODocument.LupaClienteVO.Factory.newInstance();
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
		try {
			xmlIN = "<acao>LISTAR</acao><idPessoa>" + idPessoa + "</idPessoa>";

			xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "AbaComunicacao", xmlIN));
			xmlOUT = MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText();

			comunicacao = LupaClienteVODocument.Factory.parse(xmlOUT).getLupaClienteVO();
			log.debug("TelaInicialFacadeImpl:getContato(" + user + ", " + idPessoa + ") = " + comunicacao.toString());

		} catch (XmlException ex) {
			log.error("XmlException - TelaInicialFacadeImpl:getContato(" + user + ", " + idPessoa + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: TelaInicialFacadeImpl:getContato", ex));

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - TelaInicialFacadeImpl:getContato(" + user + ", " + idPessoa + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - TelaInicialFacadeImpl:getContato(" + user + ", " + idPessoa + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
		return comunicacao;
	}

	/**
	 * @common:operation
	 */
	public DadosChipVO getDadosChip(String user, DadosChipVO dadosChipVO) throws TuxedoException, FacadeException {
		DadosChipVO dadosChip = DadosChipVODocument.DadosChipVO.Factory.newInstance();
		MsgDocument msgDocument = null;
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
		try {
			try {
				xmlIN = dadosChipVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);

				xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "GETDADOSCHIP", xmlIN));

			} catch (XmlException ex) {
				log.error("XmlException - TelaInicialFacadeImpl:getDadosChip(" + user + ") - [" + ex.getMessage() + "]");
				throw new FacadeException("Erro na montagem do XML de entrada: TelaInicialFacadeImpl:getDadosChip", ex);
			}

			try{
				msgDocument = MsgDocument.Factory.parse(xmlOUT);
				xmlOUT = msgDocument.getMsg().getMsgBody().xmlText();
				dadosChip = DadosChipVODocument.Factory.parse(xmlOUT).getDadosChipVO();

			} catch (XmlException ex) {
				log.error("XmlException - TelaInicialFacadeImpl:getDadosChip(" + user + ") - [" + ex.getMessage() + "]");
				log.error("xmlOUT := " + xmlOUT);
				dadosChip = DadosChipVODocument.DadosChipVO.Factory.newInstance();
				dadosChip.setErrorCode(msgDocument.getMsg().getMsgHdr().getStatusCode());
				dadosChip.setErrorDescription(msgDocument.getMsg().getMsgHdr().getStatusText());
			}

			return dadosChip;

		} catch (TuxedoServiceCallerException ex) {
			TuxedoException e = new TuxedoException(ex);
			if (!ConstantesCRM.SVAZIO.equals(e.getXmlHeader().getStatusText())) {
				dadosChip = DadosChipVODocument.DadosChipVO.Factory.newInstance();
				dadosChip.setErrorCode(e.getXmlHeader().getStatusCode());
				dadosChip.setErrorDescription(e.getXmlHeader().getStatusText());
				return dadosChip;
			} else {
				log.error("TuxedoException - TelaInicialFacadeImpl:getDadosChip(" + user + ") - [" + ex.getMessage() + "]");
				throw e;
			}
		}
	}

	/**
	 * @common:operation
	 */
	public DesbloqueioGsmVO getSimlockAparelho(String user, DesbloqueioGsmVO desbloqueioVO) throws TuxedoException, FacadeException {
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
	    long iniTemp=0;
	    long fimTemp=0;
		try {
			xmlIN = desbloqueioVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "GETSIMLOCK", xmlIN);

			try {
	            iniTemp = System.currentTimeMillis();
	            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

			} catch (TuxedoServiceCallerException ex) {
                fimTemp = System.currentTimeMillis() - iniTemp;
                boolean hasTimeout = (fimTemp>17000);

                desbloqueioVO.setErrorCode("00E0000");
                if(hasTimeout) {
                    desbloqueioVO.setErrorDescription("Ocorreu um Timeout na chamada ao legado.");
                }else {
                    desbloqueioVO.setErrorDescription("Ocorreu um Erro na chamada ao legado.");
                }
                return desbloqueioVO;
			}

			if(xmlOUT==null || ConstantesCRM.SVAZIO.equals(xmlOUT)) {
                fimTemp = System.currentTimeMillis() - iniTemp;
                boolean hasTimeout = (fimTemp>17000);
                desbloqueioVO.setErrorCode("00E0000");
                if(hasTimeout) {
                    desbloqueioVO.setErrorDescription("Ocorreu um Timeout na chamada ao legado.");
                }else {
                    desbloqueioVO.setErrorDescription("Ocorreu um Erro na chamada ao legado.");
                }
			}else {
	            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

	            String statusCode = msgDocRet.getMsg().getMsgHdr().getStatusCode();
	            String statusText = msgDocRet.getMsg().getMsgHdr().getStatusText();
	            String innerText = msgDocRet.getMsg().getMsgBody().xmlText();

	            if (statusCode.indexOf("E") > -1 || innerText==null || ConstantesCRM.SVAZIO.equals(innerText) || "<xml-fragment/>".equals(innerText) ) {
	                desbloqueioVO.setErrorCode(statusCode);
	                desbloqueioVO.setErrorDescription(statusText);
	                return desbloqueioVO;
	            }

	            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();
	            desbloqueioVO = DesbloqueioGsmVODocument.Factory.parse(xmlOUT).getDesbloqueioGsmVO();
			}

			return desbloqueioVO;

		} catch (XmlException ex) {
			log.error("XmlException - TelaInicialFacadeImpl:getSimlockAparelho(" + user + ") - [" + ex.getMessage() + "]");
			throw new FacadeException("Erro na montagem do XML de entrada: TelaInicialFacadeImpl:getSimlockAparelho", ex);
		}
	}

	/**
	 * @common:operation
	 */
	public LupaClienteVO getEndereco(String user, String idPessoa) throws TuxedoException, FacadeException {
		LupaClienteVO endereco = LupaClienteVODocument.LupaClienteVO.Factory.newInstance();
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
		try {
			xmlIN = "<acao>LISTAR</acao>" + "<id>" + idPessoa + "</id>";

			xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "AbaEndereco", xmlIN));

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

			endereco = LupaClienteVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getLupaClienteVO();
			log.debug("TelaInicialFacadeImpl:getEndereco(" + user + ", " + idPessoa + ") = " + endereco.toString());

		} catch (XmlException ex) {
			log.error("XmlException - TelaInicialFacadeImpl:getEndereco(" + user + ", " + idPessoa + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: TelaInicialFacadeImpl:getEndereco", ex));

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - TelaInicialFacadeImpl:getEndereco(" + user + ", " + idPessoa + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - TelaInicialFacadeImpl:getEndereco(" + user + ", " + idPessoa + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
		return endereco;
	}

	/**
	 * @common:operation
	 */
	public ListaTipoComunicacaoVO getIncluirComunicacao(String user) throws TuxedoException, FacadeException {
		ListaTipoComunicacaoVO tiposComunicacao = ListaTipoComunicacaoVO.Factory.newInstance();
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
		try {
			xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "MANUCOMUNICAI", xmlIN));

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

			tiposComunicacao = ListaTipoComunicacaoVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getListaTipoComunicacaoVO();

		} catch (XmlException ex) {
			log.error("XmlException - TelaInicialFacadeImpl:getIncluirComunicacao(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ", ex));

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - TelaInicialFacadeImpl:getIncluirComunicacao(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - TelaInicialFacadeImpl:getIncluirComunicacao(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
		return tiposComunicacao;
	}

	/**
	 * @common:operation
	 */
	public EnderecoVO getEndecoPorCEP(String user, String nrCEP) throws TuxedoException, FacadeException {
		EnderecoVO enderecoPesquisado = EnderecoVODocument.EnderecoVO.Factory.newInstance();
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
		try {
			xmlIN = "<numCEP>" + nrCEP + "</numCEP>";

			xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "BuscaDadosCEP", xmlIN));

			enderecoPesquisado = EnderecoVODocument.Factory.parse(MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText()).getEnderecoVO();
			log.debug("TelaInicialFacadeImpl:getEndecoPorCEP(" + user + ", " + nrCEP + ") = " + enderecoPesquisado.toString());

		} catch (XmlException ex) {
			log.error("XmlException - TelaInicialFacadeImpl:getEndecoPorCEP(" + user + ", " + nrCEP + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: TelaInicialFacadeImpl:getEndecoPorCEP", ex));

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - TelaInicialFacadeImpl:getEndecoPorCEP(" + user + ", " + nrCEP + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - TelaInicialFacadeImpl:getEndecoPorCEP(" + user + ", " + nrCEP + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
		return enderecoPesquisado;
	}

	/**
	 * @common:operation
	 */
	public PesquisaTIVO getPesquisaTIClientes(String user, String pesquisa, String pagina, String valor, String nmMeio, String nmSobreNome, String inPrePago) throws TuxedoException, FacadeException {
		PesquisaTIVO clientesPesquisados = PesquisaTIVODocument.PesquisaTIVO.Factory.newInstance();
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
		try {
			String paginacao = "<Paginacao><hasNext></hasNext><pageNumber>" + pagina + "</pageNumber></Paginacao>";
			if (!inPrePago.equalsIgnoreCase(ConstantesCRM.SVAZIO)) {
				if ("PF".equalsIgnoreCase(inPrePago)) {
					xmlIN = "<pesquisa>" + pesquisa + "</pesquisa><valor>" + valor + "</valor><nmMeio>" + nmMeio + "</nmMeio><nmSobreNome>" + nmSobreNome + "</nmSobreNome><inPrePago>1</inPrePago><inTipoPessoa>PF</inTipoPessoa>";
				} else {
					xmlIN = "<pesquisa>" + pesquisa + "</pesquisa><valor>" + valor + "</valor><nmMeio>" + nmMeio + "</nmMeio><nmSobreNome>" + nmSobreNome + "</nmSobreNome><inPrePago>1</inPrePago>";
				}
			} else {
				xmlIN = "<pesquisa>" + pesquisa + "</pesquisa><valor>" + valor + "</valor><nmMeio>" + nmMeio + "</nmMeio><nmSobreNome>" + nmSobreNome + "</nmSobreNome>";
			}
			xmlIN += paginacao;

			xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "PesquisaPessoa", xmlIN));

			clientesPesquisados = PesquisaTIVODocument.Factory.parse(MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText()).getPesquisaTIVO();
			log.debug("TelaInicialFacadeImpl:getPesquisaTIClientes(" + user + ") = " + clientesPesquisados.toString());

		} catch (XmlException ex) {
			log.error("XmlException - TelaInicialFacadeImpl:getPesquisaTIClientes(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: TelaInicialFacadeImpl:getPesquisaTIClientes", ex));

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - TelaInicialFacadeImpl:getPesquisaTIClientes(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - TelaInicialFacadeImpl:getPesquisaTIClientes(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
		return clientesPesquisados;
	}

	/**
	 * @common:operation
	 */
	public PesquisaTIVO getPesquisaTILinhas(String user, String idPessoa) throws TuxedoException, FacadeException {
		PesquisaTIVO linhasPesquisadas = PesquisaTIVODocument.PesquisaTIVO.Factory.newInstance();
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
		try {
			xmlIN = "<idPessoa>" + idPessoa + "</idPessoa>";

			xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "PesquisaLinha", xmlIN));

			linhasPesquisadas = PesquisaTIVODocument.Factory.parse(MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText()).getPesquisaTIVO();
			log.debug("TelaInicialFacadeImpl:getPesquisaTILinhas(" + user + ", " + idPessoa + ") = " + linhasPesquisadas.toString());

		} catch (XmlException ex) {
			log.error("XmlException - TelaInicialFacadeImpl:getPesquisaTILinhas(" + user + ", " + idPessoa + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: TelaInicialFacadeImpl:getPesquisaTILinhas", ex));

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - TelaInicialFacadeImpl:getPesquisaTILinhas(" + user + ", " + idPessoa + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - TelaInicialFacadeImpl:getPesquisaTILinhas(" + user + ", " + idPessoa + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
		return linhasPesquisadas;
	}

	/**
	 * @common:operation
	 */
	public PesquisaTIVO getPesquisaTILinhasPaginada(String user, String idPessoa, String pagina) throws TuxedoException, FacadeException {
		PesquisaTIVO linhasPesquisadas = PesquisaTIVODocument.PesquisaTIVO.Factory.newInstance();
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
		try {
			String paginacao = "<Paginacao><hasNext></hasNext><pageNumber>" + pagina + "</pageNumber></Paginacao>";
			xmlIN = "<idPessoa>" + idPessoa + "</idPessoa>";
			xmlIN += paginacao;

			xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "PesquisaLinha", xmlIN));

			linhasPesquisadas = PesquisaTIVODocument.Factory.parse(MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText()).getPesquisaTIVO();
			log.debug("TelaInicialFacadeImpl:getPesquisaTILinhasPaginada(" + user + ", " + idPessoa + ") = " + linhasPesquisadas.toString());

		} catch (XmlException ex) {
			log.error("XmlException - TelaInicialFacadeImpl:getPesquisaTILinhasPaginada(" + user + ", " + idPessoa + ", " + pagina + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: TelaInicialFacadeImpl:getPesquisaTILinhas", ex));

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - TelaInicialFacadeImpl:getPesquisaTILinhasPaginada(" + user + ", " + idPessoa + ", " + pagina + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - TelaInicialFacadeImpl:getPesquisaTILinhasPaginada(" + user + ", " + idPessoa + ", " + pagina + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
		return linhasPesquisadas;
	}

	/**
	 * @common:operation
	 */
	public PesquisaTIVO getPesquisaTILinhasConta(String user, String nrConta) throws TuxedoException, FacadeException {
		PesquisaTIVO linhasPesquisadas = PesquisaTIVODocument.PesquisaTIVO.Factory.newInstance();
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
		try {
			log.debug("TelaInicialFacadeImpl:getPesquisaTILinhasConta(" + user + ", " + linhasPesquisadas.toString() + ")");
			xmlIN = "<nrConta>" + nrConta + "</nrConta>";

			xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "PesquisaLinha", xmlIN));

			linhasPesquisadas = PesquisaTIVODocument.Factory.parse(MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText()).getPesquisaTIVO();
			log.debug("TelaInicialFacadeImpl:getPesquisaTILinhasConta(" + user + ", " + nrConta + ") = " + linhasPesquisadas.toString());

		} catch (XmlException ex) {
			log.error("XmlException - TelaInicialFacadeImpl:getPesquisaTILinhasConta(" + user + ", " + nrConta + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: TelaInicialFacadeImpl:getPesquisaTILinhasConta", ex));

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - TelaInicialFacadeImpl:getPesquisaTILinhasConta(" + user + ", " + nrConta + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - TelaInicialFacadeImpl:getPesquisaTILinhasConta(" + user + ", " + nrConta + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
		return linhasPesquisadas;
	}

	/**
	 * @common:operation
	 */
	public PesquisaTIVO getPesquisaTILinhasContaPaginada(String user, String nrConta, String pagina) throws TuxedoException, FacadeException {
		PesquisaTIVO linhasPesquisadas = PesquisaTIVODocument.PesquisaTIVO.Factory.newInstance();
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
		try {
			log.debug("TelaInicialFacadeImpl:getPesquisaTILinhasConta(" + user + ", " + linhasPesquisadas.toString() + ")");
			String paginacao = "<Paginacao><hasNext></hasNext><pageNumber>" + pagina + "</pageNumber></Paginacao>";
			xmlIN = "<nrConta>" + nrConta + "</nrConta>";
			xmlIN += paginacao;

			xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "PesquisaLinha", xmlIN));

			linhasPesquisadas = PesquisaTIVODocument.Factory.parse(MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText()).getPesquisaTIVO();
			log.debug("TelaInicialFacadeImpl:getPesquisaTILinhasContaPaginada(" + user + ", " + nrConta + ", " + pagina + ") = " + linhasPesquisadas.toString());

		} catch (XmlException ex) {
			log.error("XmlException - TelaInicialFacadeImpl:getPesquisaTILinhasContaPaginada(" + user + ", " + nrConta + ", " + pagina + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: TelaInicialFacadeImpl:getPesquisaTILinhasContaPaginada", ex));

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - TelaInicialFacadeImpl:getPesquisaTILinhasContaPaginada(" + user + ", " + nrConta + ", " + pagina + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - TelaInicialFacadeImpl:getPesquisaTILinhasContaPaginada(" + user + ", " + nrConta + ", " + pagina + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
		return linhasPesquisadas;
	}

	/**
	 * @common:operation
	 */
	public HistoricoAtendimentoVO getAbaServicos(String user, String idContaSO, String idLinhaSO, String nrLinha, int idTipoLinha) throws TuxedoException, FacadeException {
		HistoricoAtendimentoVODocument servicos = HistoricoAtendimentoVODocument.Factory.newInstance();
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
		try {
			GregorianCalendar cal = new GregorianCalendar();
			cal.setLenient(true);
			Date dataFim = cal.getTime();
			cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 3);
			Date dataIni = cal.getTime();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			xmlIN = "<ProxyLinha>" + nrLinha + "</ProxyLinha>" +
					"<ProxyOperacao>getHistoricoAtendimento</ProxyOperacao>" +
					"<xmlns>cliente.fo.vivo.com.br/vo</xmlns>" +
					"<idcontasistemaorigem>" + idContaSO + "</idcontasistemaorigem>" +
					"<dataIni>" + sdf.format(dataIni) + " 00:00:00</dataIni>" +
					"<dataFim>" + sdf.format(dataFim) + " 23:59:59</dataFim>" +
					"<usuario>FO</usuario>" +
					"<idCanal>1</idCanal>";
			
			if (idLinhaSO != null && idLinhaSO.length() > 0) {
				xmlIN = XmlManager.MakeXmlIn(user, getTuxProxy(idTipoLinha), xmlIN + "<idlinhasistemaorigem>" + idLinhaSO + "</idlinhasistemaorigem> ");
			} else {
				xmlIN = XmlManager.MakeXmlIn(user, getTuxProxy(idTipoLinha), xmlIN);
			}

			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			xmlOUT = MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText();

			servicos = HistoricoAtendimentoVODocument.Factory.parse(xmlOUT);
			log.debug("TelaInicialFacadeImpl:getAbaServicos(" + user + ", " + idContaSO + ", " + idLinhaSO + ", " + nrLinha + ", " + idTipoLinha + ") = " + servicos.toString());

		} catch (XmlException ex) {
			log.error("XmlException - TelaInicialFacadeImpl:getAbaServicos(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na  montagem do XML de entrada: TelaInicialFacadeImpl:getAbaServicos", ex));

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - TelaInicialFacadeImpl:getAbaServicos(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - TelaInicialFacadeImpl:getAbaServicos(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
		return servicos.getHistoricoAtendimentoVO();
	}

	/**
	 * @common:operation
	 */
	public TrackingAparelhosVO getAbaTrackingLista(String user, String idPessoaCliente, String pageNumber, String nrDocumento, String dsTipoDocumento) throws TuxedoException, FacadeException {
		TrackingAparelhosVO servicos = TrackingAparelhosVO.Factory.newInstance();
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
		try {
			StringBuffer xmlSB = new StringBuffer();
			if (idPessoaCliente != null && !idPessoaCliente.equals(ConstantesCRM.SVAZIO) && !"26".equals(idPessoaCliente)) {
				xmlSB.append("<idPessoaCliente>").append(idPessoaCliente).append("</idPessoaCliente>");

			} else if (nrDocumento != null && !ConstantesCRM.SVAZIO.equals(nrDocumento) && dsTipoDocumento != null && !ConstantesCRM.SVAZIO.equals(dsTipoDocumento)) {
				xmlSB.append("<nrDocumento>").append(nrDocumento).append("</nrDocumento>");
				xmlSB.append("<dsTipoDocumento>").append(dsTipoDocumento).append("</dsTipoDocumento>");
			}
			xmlSB.append("<pageNumber>").append(pageNumber).append("</pageNumber>");

			xmlIN = XmlManager.MakeXmlIn(user, "TRCKLSTPEDIDO", xmlSB.toString());

			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

			servicos = TrackingAparelhosVODocument.Factory.parse(MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText()).getTrackingAparelhosVO();
			log.debug("TelaInicialFacadeImpl:getAbaTrackingLista(" + user + ", " + idPessoaCliente + ", " + pageNumber + ", " + nrDocumento + ", " + dsTipoDocumento + ") = " + servicos.toString());

		} catch (XmlException ex) {
			log.error("XmlException - TelaInicialFacadeImpl:getAbaTrackingLista(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na  montagem do XML de entrada: TelaInicialFacadeImpl:getAbaTrackingLista", ex));

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - TelaInicialFacadeImpl:getAbaTrackingLista(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - TelaInicialFacadeImpl:getAbaTrackingLista(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
		return servicos;
	}

	/**
	 * @common:operation
	 */
	public TrackingAparelhosVO getAbaTrackingDetalhe(String user, String nrDocumento, String numPedido, String idSistemaOrigem, String nmSistemaOrigem, String nrOrdemVenda, String nrFornecimento, String nrPicking, String nrNotaFiscal, String sgUF) throws TuxedoException, FacadeException {
		TrackingAparelhosVO servicos = TrackingAparelhosVO.Factory.newInstance();
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
		try {
			xmlIN = "<nrDoc>" + nrDocumento + "</nrDoc>";
			xmlIN += "<nrPedido>" + numPedido + "</nrPedido>";
			xmlIN += "<idSistemaOrigem>" + idSistemaOrigem + "</idSistemaOrigem>";
			xmlIN += "<nmSistemaOrigem>" + nmSistemaOrigem + "</nmSistemaOrigem>";
			xmlIN += "<nrOrdemVenda>" + nrOrdemVenda + "</nrOrdemVenda>";
			xmlIN += "<nrFornecimento>" + nrFornecimento + "</nrFornecimento>";
			xmlIN += "<nrPicking>" + nrPicking + "</nrPicking>";
			xmlIN += "<nrNotaFiscal>" + nrNotaFiscal + "</nrNotaFiscal>";
			xmlIN += "<sgUF>" + sgUF + "</sgUF>";

			xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "TRCKDADPEDIDO", xmlIN));

			servicos = TrackingAparelhosVODocument.Factory.parse(MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText()).getTrackingAparelhosVO();
			log.debug("TelaInicialFacadeImpl:getAbaTrackingDetalhe(" + user + ", " + nrDocumento + ", " + numPedido + ", " + idSistemaOrigem + ", " + nrOrdemVenda + ", " + nrFornecimento + ", " + nrPicking + ", " + nrNotaFiscal + ") = " + servicos.toString());

		} catch (XmlException ex) {
			log.error("XmlException - TelaInicialFacadeImpl:getAbaTrackingDetalhe(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro no recebimento do XML do serviço [TRCKDADPEDIDO]: TelaInicialFacadeImpl:getAbaTrackingDetalhe", ex));

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - TelaInicialFacadeImpl:getAbaTrackingDetalhe(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - TelaInicialFacadeImpl:getAbaTrackingDetalhe(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
		return servicos;
	}

	/**
	 * @common:operation
	 */
	public TrackingAparelhosVO getAbaTrackingComparar(String user, String nrDocumento, String numPedido, String idSistemaOrigem, String nrOrdemVenda, String nrFornecimento, String nrPicking, String nrNotaFiscal) throws TuxedoException, FacadeException {
		TrackingAparelhosVO servicos = TrackingAparelhosVO.Factory.newInstance();
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
		try {
			xmlIN = "<nrDoc>" + nrDocumento + "</nrDoc>";
			xmlIN += "<nrPedido>" + numPedido + "</nrPedido>";
			xmlIN += "<idSistemaOrigem>" + idSistemaOrigem + "</idSistemaOrigem>";
			xmlIN += "<nrOrdemVenda>" + nrOrdemVenda + "</nrOrdemVenda>";
			xmlIN += "<nrFornecimento>" + nrFornecimento + "</nrFornecimento>";
			xmlIN += "<nrPicking>" + nrPicking + "</nrPicking>";
			xmlIN += "<nrNotaFiscal>" + nrNotaFiscal + "</nrNotaFiscal>";

			xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "TRCKCMPPEDIDO", xmlIN));

			servicos = TrackingAparelhosVODocument.Factory.parse(MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText()).getTrackingAparelhosVO();
			log.debug("TelaInicialFacadeImpl:getAbaTrackingComparar(" + user + ", " + nrDocumento + ", " + numPedido + ", " + idSistemaOrigem + ", " + nrOrdemVenda + ", " + nrFornecimento + ", " + nrPicking + ", " + nrNotaFiscal + ") = " + servicos.toString());

		} catch (XmlException ex) {
			log.error("XmlException - TelaInicialFacadeImpl:getAbaTrackingComparar(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na  montagem do XML de entrada: TelaInicialFacadeImpl:getAbaTrackingComparar", ex));

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - TelaInicialFacadeImpl:getAbaTrackingComparar(" + user + ") - [" + ex.getMessage() + "]", ex);
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - TelaInicialFacadeImpl:getAbaTrackingComparar(" + user + ") - [" + ex.getMessage() + "]", ex);
			throw (new FacadeException(ex));
		}
		return servicos;
	}

	/**
	 * @common:operation
	 */
	public void setComentarioTracking(String user, WFExecucao wFExecucao) throws TuxedoException, FacadeException {
        String xmlIN = ConstantesCRM.SVAZIO;
		try {
			log.debug("TelaInicialFacadeImpl:setComentarioTracking(" + user + ", " + wFExecucao + ")");
			xmlIN = XmlManager.MakeXmlIn(user, "WFCOMACAO", wFExecucao.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO));

			tuxedo.callService("TuxConnector", xmlIN);

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - TelaInicialFacadeImpl:setComentarioTracking(" + user + ", " + wFExecucao + ") - [" + ex.getMessage() + "]", ex);
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - TelaInicialFacadeImpl:setComentarioTracking(" + user + ", " + wFExecucao + ") - [" + ex.getMessage() + "]", ex);
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public AbaServicosFiltroVO getAbaServicosFiltro(String user, String nrConta, String nrLinha, String idPessoa, String idTipoRelacionamento) throws TuxedoException, FacadeException {
		AbaServicosFiltroVODocument servicos = AbaServicosFiltroVODocument.Factory.newInstance();
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
		try {
			xmlIN = "<nrConta>" + nrConta + "</nrConta>" + "<nrLinha>" + nrLinha + "</nrLinha>" + "<idPessoa>" + idPessoa + "</idPessoa><idTipoRelacionamento>" + idTipoRelacionamento + "</idTipoRelacionamento>";

			xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "AbaServFiltro", xmlIN));

			servicos = AbaServicosFiltroVODocument.Factory.parse(MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText());
			log.debug("TelaInicialFacadeImpl:getAbaServicosFiltro(" + user + ", " + nrConta + ", " + nrLinha + ", " + idPessoa + ", " + idTipoRelacionamento + ") = " + servicos.toString());

		} catch (XmlException ex) {
			log.error("XmlException - TelaInicialFacadeImpl:getAbaServicosFiltro(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: TelaInicialFacadeImpl:getAbaServicosFiltro", ex));

		} catch (Exception ex) {
			log.error("Exception - TelaInicialFacadeImpl:getAbaServicosFiltro(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
		return servicos.getAbaServicosFiltroVO();
	}

	/**
	 * @common:operation
	 */
	public ParametrosVO getParametrosVO(String user, String idLinhaTelefonica, String idTipoRelacionamento) throws TuxedoException, FacadeException {
		ParametrosVODocument parametros = ParametrosVODocument.Factory.newInstance();
		String xmlIN = ConstantesCRM.SVAZIO;
		String xmlOUT = ConstantesCRM.SVAZIO;
		try {
			xmlIN = "<idLinhaTelefonica>" + (idLinhaTelefonica != null ? idLinhaTelefonica.trim() : ConstantesCRM.SVAZIO) + "</idLinhaTelefonica>" + "<idTipoRelacionamento>" + idTipoRelacionamento + "</idTipoRelacionamento>";

			xmlOUT = tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user, "CarregaParam", xmlIN));

			parametros = ParametrosVODocument.Factory.parse(MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText());

			log.debug("TelaInicialFacadeImpl:getParametrosVO(" + user + ", " + idLinhaTelefonica + ", " + idTipoRelacionamento + ") = " + parametros.toString());

		} catch (XmlException ex) {
			log.error("XmlException - TelaInicialFacadeImpl:getParametrosVO(" + user + ", " + idLinhaTelefonica + ", " + idTipoRelacionamento + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: TelaInicialFacadeImpl:getParametrosVO", ex));

		} catch (Exception ex) {
			log.error("Exception - TelaInicialFacadeImpl:getParametrosVO(" + user + ", " + idLinhaTelefonica + ", " + idTipoRelacionamento + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
		return parametros.getParametrosVO();
	}

	/**
	 * @common:operation
	 */
	public TIFaturamento getFaturamento(String user, String idContaSO, int idTipoLinha) throws TuxedoException, FacadeException {
		TIFaturamentoDocument faturamento = TIFaturamentoDocument.Factory.newInstance();
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
		try {
			xmlIN = "<ProxyLinha></ProxyLinha><ProxyOperacao>getInfoFaturamento</ProxyOperacao><idcontasistemaorigem>" + idContaSO + "</idcontasistemaorigem><usuario>FO</usuario><idCanal>1</idCanal>";
			xmlIN = XmlManager.MakeXmlIn(user, getTuxProxy(idTipoLinha, ConstantesCRM.OPERACAO_INFO_FATURAMENTO), xmlIN);

			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

			faturamento = TIFaturamentoDocument.Factory.parse(MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText());
			log.debug("TelaInicialFacadeImpl:getFaturamento(" + user + ", " + idContaSO + ", " + idTipoLinha + ") = " + faturamento.toString());

		} catch (XmlException ex) {
			log.error("XmlException - TelaInicialFacadeImpl:getFaturamento(" + user + ", " + idContaSO + ", " + idTipoLinha + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: TelaInicialFacadeImpl:getFaturamento", ex));

		} catch (Exception ex) {
			log.error("Exception - TelaInicialFacadeImpl:getFaturamento(" + user + ", " + idContaSO + ", " + idTipoLinha + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
		return faturamento.getTIFaturamento();
	}

	/**
	 * @common:operation
	 */
	public PesquisaEnderecoVO getPesquisaEnderecoIni(String user) throws TuxedoException, FacadeException {
		PesquisaEnderecoVODocument inicioEndereco = PesquisaEnderecoVODocument.Factory.newInstance();
        String xmlOUT = ConstantesCRM.SVAZIO;
		try {
			xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "PesquisaEndIni", ConstantesCRM.SVAZIO));

			inicioEndereco = PesquisaEnderecoVODocument.Factory.parse(MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText());
			log.debug("TelaInicialFacadeImpl:getPesquisaEnderecoIni(" + user + ") = " + inicioEndereco.toString());

		} catch (XmlException ex) {
			log.error("XmlException - TelaInicialFacadeImpl:getPesquisaEnderecoIni(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: TelaInicialFacadeImpl:getPesquisaEnderecoIni", ex));

		} catch (Exception ex) {
			log.error("Exception - TelaInicialFacadeImpl:getPesquisaEnderecoIni(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
		return inicioEndereco.getPesquisaEnderecoVO();
	}

	/**
	 * @common:operation
	 */
	public PesquisaEnderecoVO getPesquisaEnderecoFil(String user, PesquisaEnderecoVO filtroEndereco) throws TuxedoException, FacadeException {
		PesquisaEnderecoVODocument enderecosPesquisados = PesquisaEnderecoVODocument.Factory.newInstance();
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
		try {
			xmlIN = filtroEndereco.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);

			xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "PesquisaEndFil", xmlIN));

			enderecosPesquisados = PesquisaEnderecoVODocument.Factory.parse(MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText());
			log.debug("TelaInicialFacadeImpl:getPesquisaEnderecoFil(" + user + ", " + filtroEndereco.toString() + ") = " + enderecosPesquisados.toString());

		} catch (XmlException ex) {
			log.error("XmlException - TelaInicialFacadeImpl:getPesquisaEnderecoFil(" + user + ", " + filtroEndereco.toString() + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: TelaInicialFacadeImpl:getPesquisaEnderecoFil", ex));

		} catch (Exception ex) {
			log.error("Exception - TelaInicialFacadeImpl:getPesquisaEnderecoFil(" + user + ", " + filtroEndereco.toString() + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
		return enderecosPesquisados.getPesquisaEnderecoVO();
	}

	/**
	 * @common:operation
	 */
	public EnderecoVO getEnderecoEscolhido(String user, String idEndereco) throws TuxedoException, FacadeException {
		EnderecoVODocument enderecoEscolhido = EnderecoVODocument.Factory.newInstance();
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
		try {
			xmlIN = "<idEndereco>" + idEndereco + "</idEndereco>";

			xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "PESQUISAEND", xmlIN));

			enderecoEscolhido = EnderecoVODocument.Factory.parse(MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText());
			log.debug("TelaInicialFacadeImpl:getEnderecoEscolhido(" + user + ", " + idEndereco + ") = " + enderecoEscolhido.toString());

		} catch (XmlException ex) {
			log.error("XmlException - TelaInicialFacadeImpl:getEnderecoEscolhido(" + user + ", " + idEndereco + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: TelaInicialFacadeImpl:getEnderecoEscolhido", ex));

		} catch (Exception ex) {
			log.error("Exception - TelaInicialFacadeImpl:getEnderecoEscolhido(" + user + ", " + idEndereco + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
		return enderecoEscolhido.getEnderecoVO();
	}

	/**
	 * @common:operation
	 */
	public LupaClienteVO getPontos(String user, String nrLinha) throws TuxedoException, FacadeException {
		LupaClienteVO abaPontos = LupaClienteVO.Factory.newInstance();
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
		try {
			xmlIN = "<nrLinha>" + nrLinha + "</nrLinha>";

			xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "ABACTAPONTUAC", xmlIN));

			abaPontos = LupaClienteVODocument.Factory.parse(MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText()).getLupaClienteVO();
			log.debug("TelaInicialFacadeImpl:getPontos(" + user + ", " + nrLinha + ") = " + abaPontos.toString());

		} catch (XmlException ex) {
			log.error("XmlException - TelaInicialFacadeImpl:getPontos(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: TelaInicialFacadeImpl:getPontos", ex));

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - TelaInicialFacadeImpl:getPontos(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - TelaInicialFacadeImpl:getPontos(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
		return abaPontos;
	}

	/**
	 * @common:operation
	 */
	public RetornoVO inibeMsgPontos(String user, String nrLinha) throws TuxedoException, FacadeException {
		RetornoVO retorno = RetornoVO.Factory.newInstance();
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
		try {
			xmlIN = "<nrTelefone>" + nrLinha + "</nrTelefone>";

			xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "INSSCRIPT", xmlIN));

			retorno = RetornoVODocument.Factory.parse(MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText()).getRetornoVO();

		} catch (XmlException ex) {
			log.error("XmlException - TelaInicialFacadeImpl:inibeMsgPontos(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: TelaInicialFacadeImpl:inibeMsgPontos", ex));

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - TelaInicialFacadeImpl:inibeMsgPontos(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - TelaInicialFacadeImpl:inibeMsgPontos(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
		return retorno;
	}

	/**
	 * @common:operation
	 */
	public ReloadLinhaVO getLinhaPesquisada(String user, String idLinha, String idClienteDePara) throws TuxedoException, FacadeException {
		ReloadLinhaVODocument linhaPesquisada = ReloadLinhaVODocument.Factory.newInstance();
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
		try {
			xmlIN = "<idLinha>" + idLinha + "</idLinha><idPessoaDePara>" + idClienteDePara + "</idPessoaDePara>";

			xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "RELOADLINHA", xmlIN));

			linhaPesquisada = ReloadLinhaVODocument.Factory.parse(MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText());
			log.debug("TelaInicialFacadeImpl:getLinhaPesquisada(" + user + ", " + idLinha + ", " + idClienteDePara + ") = " + linhaPesquisada.toString());

		} catch (XmlException ex) {
			log.error("XmlException - TelaInicialFacadeImpl:getLinhaPesquisada(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: TelaInicialFacadeImpl:getLinhaPesquisada", ex));

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - TelaInicialFacadeImpl:getLinhaPesquisada(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - TelaInicialFacadeImpl:getLinhaPesquisada(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
		return linhaPesquisada.getReloadLinhaVO();
	}

	/**
	 * @common:operation
	 */
	public TelaInicialVO getTelaInicialProspect(String user, String idProspect) throws TuxedoException, FacadeException {
		TelaInicialVODocument tivo;
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
		try {
			xmlIN = "<idPessoa>" + idProspect + "</idPessoa>";

			xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "PROSPECTIT", xmlIN));

			tivo = TelaInicialVODocument.Factory.parse(MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText());
			log.debug("TelaInicialFacadeImpl:getTelaInicialProspect(" + user + ", " + idProspect + ") = " + tivo.toString());

		} catch (XmlException ex) {
			log.error("XmlException - TelaInicialFacadeImpl:getTelaInicialProspect(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: TelaInicialFacadeImpl:getTelaInicialProspect", ex));

		} catch (Exception ex) {
			log.error("Exception - TelaInicialFacadeImpl:getTelaInicialProspect(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
		return tivo.getTelaInicialVO();
	}

	/**
	 * @common:operation
	 */
	public UsuarioVO getDADUSUARIOTI(String user, String nrLinha) throws FacadeException, FacadeException {
		UsuarioVODocument usuarioDoc;
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
		try {
			xmlIN = "<nrLinha>" + nrLinha + "</nrLinha>";
			xmlOUT = tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user,"DADUSUARIOTI",xmlIN));

			usuarioDoc = UsuarioVODocument.Factory.parse(MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText());

			log.debug("TelaInicialFacadeImpl:getDADUSUARIOTI(" + user + ", " + nrLinha + ")");

		} catch (XmlException ex) {
			log.error("XmlException - TelaInicialFacadeImpl:getDADUSUARIOTI(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: TelaInicialFacadeImpl:getDADUSUARIOTI", ex));

		} catch (Exception ex) {
			log.error("Exception - TelaInicialFacadeImpl:getDADUSUARIOTI(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
		return usuarioDoc.getUsuarioVO();
	}

	/**
	 * @common:operation
	 */
	public TILinha getDADLINHATI(String user, String nrLinha) throws FacadeException, FacadeException {
		TILinhaDocument linhaDoc;
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
		try {
			xmlIN = "<nrLinha>" + nrLinha + "</nrLinha>";
			xmlOUT = tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user, "DADLINHATI", xmlIN));

			linhaDoc = TILinhaDocument.Factory.parse(MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText());
			log.debug("TelaInicialFacadeImpl:getDADLINHATI(" + user + ", " + nrLinha + ")");

		} catch (XmlException ex) {
			log.error("XmlException - TelaInicialFacadeImpl:getDADLINHATI(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: TelaInicialFacadeImpl:getDADLINHATI", ex));

		} catch (Exception ex) {
			log.error("Exception - TelaInicialFacadeImpl:getDADLINHATI(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
		return linhaDoc.getTILinha();
	}

	/**
	 * @common:operation
	 */
	public TICliente getDADCLIENTETI(String user, String nrLinha) throws FacadeException, FacadeException {
		TIClienteDocument cliDoc;
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
		try {
			xmlIN = "<nrLinha>" + nrLinha + "</nrLinha>";
			xmlOUT = tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user, "DADCLIENTETI", xmlIN));

			cliDoc = TIClienteDocument.Factory.parse(MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText());
			log.debug("TelaInicialFacadeImpl:getDADCLIENTETI(" + user + ", " + nrLinha + ")");

		} catch (XmlException ex) {
			log.error("XmlException - TelaInicialFacadeImpl:getDADCLIENTETI(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: TelaInicialFacadeImpl:getDADCLIENTETI", ex));

		} catch (Exception ex) {
			log.error("Exception - TelaInicialFacadeImpl:getDADCLIENTETI(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
		return cliDoc.getTICliente();
	}

	/**
	 * @common:operation
	 */
	public TIFaturamento getDADFACTURATI(String user, String nrLinha) throws FacadeException, FacadeException {
		TIFaturamentoDocument faDoc;
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
		try {
			xmlIN = "<nrLinha>" + nrLinha + "</nrLinha>";
			xmlOUT = tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user, "DADCLIENTETI", xmlIN));

			faDoc = TIFaturamentoDocument.Factory.parse(MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText());
			log.debug("TelaInicialFacadeImpl:getDADFACTURATI(" + user + ", " + nrLinha + ")");

		} catch (XmlException ex) {
			log.error("XmlException - TelaInicialFacadeImpl:getDADFACTURATI(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: TelaInicialFacadeImpl:getDADFACTURATI", ex));

		} catch (Exception ex) {
			log.error("Exception - TelaInicialFacadeImpl:getDADFACTURATI(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
		return faDoc.getTIFaturamento();
	}

	/**
	 * @common:operation
	 */
	public HistoricoServicosVO getHistoricoServicos(String user, HistoricoServicosVO filtro, int idTipoLinha) throws TuxedoException, FacadeException {
		HistoricoServicosVODocument servicos = HistoricoServicosVODocument.Factory.newInstance();
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
		try {
			xmlIN = filtro.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = xmlIN + "<idTipoLinha>" + idTipoLinha + "</idTipoLinha>";

			xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "HISTORICOSERV", xmlIN));

			servicos = HistoricoServicosVODocument.Factory.parse(MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText());
			log.debug("TelaInicialFacadeImpl:getHistoricoServicos(" + user + ", " + filtro.toString() + ", " + idTipoLinha + ") = " + servicos.toString());

		} catch (XmlException ex) {
			log.error("XmlException - TelaInicialFacadeImpl:getHistoricoServicos(" + user + ", " + filtro.toString() + ", " + idTipoLinha + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na  montagem do XML de entrada: TelaInicialFacadeImpl:getHistoricoServicos", ex));

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - TelaInicialFacadeImpl:getHistoricoServicos(" + user + ", " + filtro.toString() + ", " + idTipoLinha + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - TelaInicialFacadeImpl:getHistoricoServicos(" + user + ", " + filtro.toString() + ", " + idTipoLinha + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
		return servicos.getHistoricoServicosVO();
	}

	/**
	 * @common:operation
	 */
	public LinhasPorIdVO getLinhasPorId(String user, LinhasPorIdVO filtro) throws TuxedoException, FacadeException {
		LinhasPorIdVODocument parametros = LinhasPorIdVODocument.Factory.newInstance();
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
		try {
			xmlIN = filtro.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);

			xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "GETLINHAPORID", xmlIN));

			parametros = LinhasPorIdVODocument.Factory.parse(MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText());
			log.debug("TelaInicialFacadeImpl:getLinhasPorId(" + user + ", " + filtro.toString() + ") = " + parametros.toString());

		} catch (XmlException ex) {
			log.error("XmlException - TelaInicialFacadeImpl:getLinhasPorId(" + user + ", " + filtro.toString() + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: TelaInicialFacadeImpl:getLinhasPorId", ex));

		} catch (Exception ex) {
			log.error("Exception - TelaInicialFacadeImpl:getLinhasPorId(" + user + ", " + filtro.toString() + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
		return parametros.getLinhasPorIdVO();
	}

	private String getTuxProxy(int idTipoLinha) {
	    String serviceName = ConstantesCRM.SVAZIO;
		switch (idTipoLinha) {
    		case 1:
    		case 5:
    		case 4:
    		case 7: // Pos e Pos Chip
    			serviceName = "TUXATLYSBE";
    			break;
    		case 2:
    		case 6:// Pre e Pre Chip
    			serviceName = "TUXNGINBE";
    			break;
		}
		return serviceName;
	}

	/**
	 * Novo metodo responsavel por identificar qual o serviço que devera ser chamado no caso da linha CONTROLE
	 */
	private String getTuxProxy(int idTipoLinha, int idOperacaoTipoLinhaControle) {
	    String serviceName = ConstantesCRM.SVAZIO;
		switch (idTipoLinha) {
    		case 1:
    		case 5: // Pos e Pos Chip
    			serviceName = "TUXATLYSBE";
    			break;
    		case 2:
    		case 6: // Pre e Pre Chip
    			serviceName = "TUXNGINBE";
    			break;
    		case 4:
    		case 7: // Controle
    			serviceName = getOperacaoControle(idOperacaoTipoLinhaControle);
    			break;
		}
		return serviceName;
	}

	private String getOperacaoControle(int idOperacaoTipoLinhaControle) {
	    String serviceName = ConstantesCRM.SVAZIO;
		switch (idOperacaoTipoLinhaControle) {
    		case ConstantesCRM.OPERACAO_SERVICO:
    			serviceName = "TUXATLYSBE";
    			break;
    		case ConstantesCRM.OPERACAO_CONSUMO:
    			serviceName = "TUXATLYSBE";
    			break;
    		case ConstantesCRM.OPERACAO_DETALHE_SALDO:
    			serviceName = "TUXNGINBE";
    			break;
    		case ConstantesCRM.OPERACAO_ESTIMATIVA_SALDO:
    			serviceName = "TUXATLYSBE";
    			break;
    		case ConstantesCRM.OPERACAO_EXTRATO:
    			serviceName = "TUXNGINBE";
    			break;
    		case ConstantesCRM.OPERACAO_HISTORICO_MOVIMENTO:
    			serviceName = "TUXNGINBE";
    			break;
    		case ConstantesCRM.OPERACAO_PLANO:
    			serviceName = "TUXNGINBE";
    			break;
    		case ConstantesCRM.OPERACAO_TARIFA_REDUZIDA:
    			serviceName = "TUXNGINBE";
    			break;
    		case ConstantesCRM.OPERACAO_CAIXA_POSTAL:
    			serviceName = "TUXNGINBE";
    			break;
    		case ConstantesCRM.OPERACAO_SUSPENDE_CELULAR:
    			serviceName = "TUXATLYSBE";
    			break;
    		case ConstantesCRM.OPERACAO_DETALHE_FATURA:
    			serviceName = "TUXNGINBE";
    			break;
		}
		return serviceName;
	}

	/**
	 * @common:operation
	 */
	public LupaLinhaAbasGSMVO getLupaLinhaAbasGSM(String user, String tpOperacao, String idLinha, String nrLinha, String nrIP, String parametro) throws TuxedoException, FacadeException {
		LupaLinhaAbasGSMVODocument servicos = LupaLinhaAbasGSMVODocument.Factory.newInstance();
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
		try {
			xmlIN = "<tpOperacao>" + tpOperacao + "</tpOperacao><nrLinha>" + nrLinha + "</nrLinha><idLinhaTelefonica>" + idLinha + "</idLinhaTelefonica><nrIP>" + nrIP + "</nrIP>";
			if (parametro != null) {
				xmlIN += "<nmParametro>" + parametro + "</nmParametro>";
			}

			xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "DEVICEMANAGER", xmlIN));

			servicos = LupaLinhaAbasGSMVODocument.Factory.parse(MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText());
			log.debug("TelaInicialFacadeImpl:getLupaLinhaAbasGSM(" + user + ", " + tpOperacao + ") = " + servicos.toString());

		} catch (XmlException ex) {
			log.error("XmlException - TelaInicialFacadeImpl:getLupaLinhaAbasGSM(" + user + ", " + tpOperacao + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na  montagem do XML de entrada: TelaInicialFacadeImpl:getLupaLinhaAbasGSM", ex));

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - TelaInicialFacadeImpl:getLupaLinhaAbasGSM(" + user + ", " + tpOperacao + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - TelaInicialFacadeImpl:getLupaLinhaAbasGSM(" + user + ", " + tpOperacao + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
		return servicos.getLupaLinhaAbasGSMVO();
	}

	/**
	 * @common:operation
	 */
	public ParametroVO getParametro(String user, String parametro) throws TuxedoException, FacadeException {
		ParametroVODocument servicos = ParametroVODocument.Factory.newInstance();
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
		try {
			xmlIN = ConstantesCRM.SVAZIO;
			if (parametro != null) {
				xmlIN += "<cdParametro>" + parametro + "</cdParametro>";
			}

			xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "GETPARAMETRO", xmlIN));

			servicos = ParametroVODocument.Factory.parse(MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText());
			log.debug("TelaInicialFacadeImpl:getParametro(" + user + ", " + parametro + ") = " + servicos.toString());

		} catch (XmlException ex) {
			log.error("XmlException - TelaInicialFacadeImpl:getParametro(" + user + ", " + parametro + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na  montagem do XML de entrada: TelaInicialFacadeImpl:getParametro", ex));

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - TelaInicialFacadeImpl:getParametro(" + user + ", " + parametro + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - TelaInicialFacadeImpl:getParametro(" + user + ", " + parametro + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
		return servicos.getParametroVO();
	}

	/**
	 * @common:operation
	 */
	public void setAparelhoImei(String user, String marca, String modelo, String imei, String idLinhaTelefonica, String nmLoja, String nrProtocolo) throws TuxedoException, FacadeException {
        String xmlIN = ConstantesCRM.SVAZIO;
		try {
			xmlIN = "<idLinhaTelefonica>" + idLinhaTelefonica + "</idLinhaTelefonica>";
			xmlIN += "<dsMarca>" + marca + "</dsMarca>";
			xmlIN += "<dsModelo>" + modelo + "</dsModelo>";
			xmlIN += "<IMEI>" + imei + "</IMEI>";
			xmlIN += "<nmLoja>" + nmLoja + "</nmLoja>";
			xmlIN += "<nrProtocolo>" + nrProtocolo + "</nrProtocolo>";

			tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "SETIMEI", xmlIN));

		} catch (XmlException ex) {
			log.error("Exception - TelaInicialFacadeImpl:setAparelhoImei(" + user + ", " + marca + ", " + modelo + ", " + imei + ", " + idLinhaTelefonica + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na  montagem do XML de entrada: TelaInicialFacadeImpl:getParametro", ex));

		} catch (TuxedoServiceCallerException ex) {
			log.error("Exception - TelaInicialFacadeImpl:setAparelhoImei(" + user + ", " + marca + ", " + modelo + ", " + imei + ", " + idLinhaTelefonica + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - TelaInicialFacadeImpl:setAparelhoImei(" + user + ", " + marca + ", " + modelo + ", " + imei + ", " + idLinhaTelefonica + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public PortabilidadeVO getPortabilidadeVO(String idUsuario, PortabilidadeVO portabilidadeVO) throws TuxedoException, FacadeException {
		PortabilidadeVO out = PortabilidadeVO.Factory.newInstance();
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
		try {
			xmlIN = portabilidadeVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);

			xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(idUsuario, "ATDPORTHIST", xmlIN));
			xmlOUT = MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText();

			out = PortabilidadeVODocument.Factory.parse(xmlOUT).getPortabilidadeVO();

		} catch (XmlException ex) {
			log.error("XmlException - TelaInicialFacadeImpl:getPortabilidadeVO(" + idUsuario + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ProtocoloFacadeImpl:getPortabilidadeVO", ex));

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - TelaInicialFacadeImpl:getPortabilidadeVO(" + idUsuario + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - TelaInicialFacadeImpl:getPortabilidadeVO(" + idUsuario + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
		return out;
	}
}