package br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP;

import javax.ejb.Local;

@Local
public interface ManterAgendamentoVIPFacade {

    public br.com.vivo.vol.dados.vo.ListaAlertaAgendamentoVIPDocument.ListaAlertaAgendamentoVIP consultaListaAlerta(java.lang.String cdAreaRegistro, java.lang.String nrLinha, java.lang.String user) throws java.lang.Exception;

    public br.com.vivo.vol.dados.vo.ConsultarTiposEmailVODocument.ConsultarTiposEmailVO consultaTiposEmail(java.lang.String user) throws java.lang.Exception;

    public void excluirLinha(java.lang.String ddd, java.lang.String linha) throws br.com.vivo.fo.exception.FacadeException;

    public void incluirLinha(java.lang.String ddd, java.lang.String linha) throws br.com.vivo.fo.exception.FacadeException;

    public boolean isPremium(java.lang.String ddd, java.lang.String linha) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.vol.dados.vo.ListaAlertaDocument.ListaAlerta[] consultarAlerta(java.lang.String cdAreaRegistro, java.lang.String nrLinha, java.lang.String user) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.vol.dados.vo.ConsultarEmailsVODocument.ConsultarEmailsVO consultaEmail(java.lang.String idPessoa, java.lang.String user) throws java.lang.Exception;

    public boolean primeiroAgendamento(java.lang.String cdAreaRegistro, java.lang.String nrLinha, java.lang.String user) throws br.com.vivo.fo.exception.FacadeException;

    public void incluirAlerta(br.com.vivo.vol.dados.vo.ListaAlertaDocument.ListaAlerta[] alertas, java.lang.String user) throws br.com.vivo.fo.exception.FacadeException;

    public void alterarAlerta(br.com.vivo.vol.dados.vo.ListaAlertaDocument.ListaAlerta[] alertas, java.lang.String user) throws br.com.vivo.fo.exception.FacadeException;

    public void cancelarAlerta(br.com.vivo.vol.dados.vo.ListaAlertaDocument.ListaAlerta[] alertas, java.lang.String user) throws br.com.vivo.fo.exception.FacadeException;

    public noNamespace.MsgDocument.Msg incluiEmail(java.lang.String user, java.lang.String idPessoa, java.lang.String dsEmail, java.lang.String idTipoemail, java.lang.String foneArea, java.lang.String foneNumero, java.lang.String tarefa) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xsd.consultaAgendamentoPorLinha.output.AgendamentoType[] listarAgendamentos(java.lang.String ddd, java.lang.String telefone, java.lang.String dataInicio, java.lang.String dataFim, java.lang.String user) throws java.lang.Exception;

    public br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.db.ManterAgendamentoDB.LinhaPremmiun getTotalLinhasPremiun(java.lang.String ddd, java.lang.String linha) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.db.ManterAgendamentoDB.LinhaPremmiun[] getLinhasPremmiun(java.lang.String paginaInicial, java.lang.String paginaFinal, java.lang.String ddd, java.lang.String linha) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xsd.consultaArvoreAssuntos.output.AssuntoType[] consultarAssuntos(java.lang.String user) throws java.lang.Exception;

    public br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xsd.consultaLojas.output.LojaType[] consultarLojas(java.lang.String uf, java.lang.String municipio, java.lang.String user) throws java.lang.Exception;

    public java.lang.String[] consultarMunicipios(java.lang.String uf, java.lang.String user) throws java.lang.Exception;

    public java.lang.String[] consultarHorarios(java.lang.String loja, java.lang.String data, java.lang.String user) throws java.lang.Exception;

    public void registrarContato(java.lang.String user, java.lang.String nrLinha, java.lang.String cdAreaRegistro, java.lang.String idTipoRelacionamento, java.lang.String idCanal, java.lang.String cdContato);

    public java.lang.String getUfByDddLinha(java.lang.String ddd, java.lang.String linha) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xsd.registraAgendamento.output.RegistraAgendamentoOutputType incluirAgendamento(br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xsd.registraAgendamento.input.RegistraAgendamentoInputType agendamento, java.lang.String observacao, java.lang.String user) throws java.lang.Exception;

    public br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xsd.registraAgendamento.output.RegistraAgendamentoOutputType alterarAgendamento(br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xsd.registraAgendamento.input.RegistraAgendamentoInputType agendamento, java.lang.String observacao, java.lang.String user) throws java.lang.Exception;

    public br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xsd.registraAgendamento.output.RegistraAgendamentoOutputType cancelarAgendamento(br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xsd.registraAgendamento.input.RegistraAgendamentoInputType agendamento, java.lang.String observacao, java.lang.String user) throws java.lang.Exception;

    public void enviarSMS(java.lang.String ddd, java.lang.String telefone, java.lang.String dataHora, java.lang.String loja, java.lang.String operacao, java.lang.String user) throws br.com.vivo.fo.exception.FacadeException;
}
