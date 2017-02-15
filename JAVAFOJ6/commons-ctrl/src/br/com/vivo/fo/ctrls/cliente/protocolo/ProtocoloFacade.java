package br.com.vivo.fo.ctrls.cliente.protocolo;

import javax.ejb.Local;

import br.com.vivo.fo.atendimento.vo.AbreProtocoloOutTODocument.AbreProtocoloOutTO;
import br.com.vivo.fo.atendimento.vo.AlterProtocoloOutTODocument.AlterProtocoloOutTO;
import br.com.vivo.fo.cliente.vo.AlterProtocoloTODocument.AlterProtocoloTO;
import br.com.vivo.fo.atendimento.vo.FechaProtocoloOutTODocument.FechaProtocoloOutTO;
import br.com.vivo.fo.atendimento.vo.GetDadosProtocoloOutTODocument.GetDadosProtocoloOutTO;
import br.com.vivo.fo.atendimento.vo.AbreProtocoloTODocument.AbreProtocoloTO;
import br.com.vivo.fo.atendimento.vo.FechaProtocoloTODocument.FechaProtocoloTO;

@Local
public interface ProtocoloFacade {

    public AlterProtocoloOutTO setAlteracaoProtocolo(String user, AlterProtocoloTO alterProtocoloTO, String operacao, String nrProtocolo, int qtProcessosAbertos, int qtProcessosPendentes) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public AbreProtocoloOutTO setAbreValidaProtocolo(String user, AbreProtocoloTO abreProtocolo) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public FechaProtocoloOutTO setFechaProtocolo(String user, FechaProtocoloTO fechaProtocoloTO) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public GetDadosProtocoloOutTO getDadosProtocolo(String user, String nrLinha, String nrProtocolo, String idSistema) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;
}
