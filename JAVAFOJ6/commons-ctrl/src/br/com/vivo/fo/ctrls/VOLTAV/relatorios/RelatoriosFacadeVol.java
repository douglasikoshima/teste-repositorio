package br.com.vivo.fo.ctrls.VOLTAV.relatorios;

import javax.ejb.Local;

@Local
public interface RelatoriosFacadeVol {

    public br.com.vivo.tav.creditos.LSTTIPORECARVODocument.LSTTIPORECARVO consultaRecargasDisponiveis(java.lang.String user, java.lang.String idRegional) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.voltav.vo.VOLTAVRelatorioLojasVODocument.VOLTAVRelatorioLojasVO consultaLojasDisponiveis(java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.voltav.vo.VOLTAVRelatorioStatusVendaVODocument.VOLTAVRelatorioStatusVendaVO consultaStatusVenda(java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.voltav.vo.VOLTAVRelatoriosFiltroLojaVODocument.VOLTAVRelatoriosFiltroLojaVO[] obtemLojas(java.lang.String user, java.lang.String idUFOperadora) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.voltav.vo.VOLTAVRelatoriosFiltroTerminalVODocument.VOLTAVRelatoriosFiltroTerminalVO[] obtemTerminais(java.lang.String user, java.lang.String idLoja) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.voltav.vo.VOLTAVRelatorioAcessoDiarioVODocument.VOLTAVRelatorioAcessoDiarioVO consultaRelatorioAcessosDiario(java.lang.String user, java.lang.String dataInicio, java.lang.String dataFinal, java.lang.String operadora, java.lang.String regional, java.lang.String tipoCarteira, java.lang.String segmentacao, java.lang.String canal, java.lang.String tipoLinha, java.lang.String cdAreaRegistro, java.lang.String isAgrupado, java.lang.String idTecnologia, java.lang.String idLoja, java.lang.String nrTerminal) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.voltav.vo.VOLTAVRelatorioAcessoFaixaHorarioVODocument.VOLTAVRelatorioAcessoFaixaHorarioVO consultaRelatorioAcessosFaixaHorario(java.lang.String user, java.lang.String dataInicio, java.lang.String dataFinal, java.lang.String operadora, java.lang.String regional, java.lang.String tipoCarteira, java.lang.String segmentacao, java.lang.String canal, java.lang.String tipoLinha, java.lang.String cdAreaRegistro, java.lang.String isAgrupado, java.lang.String idTecnologia, java.lang.String idLoja, java.lang.String nrTerminal) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.voltav.vo.VOLTAVRelatorioAcessoNegadoVODocument.VOLTAVRelatorioAcessoNegadoVO consultaRelatorioAcessosNegado(java.lang.String user, java.lang.String dataInicio, java.lang.String dataFinal, java.lang.String operadora, java.lang.String regional, java.lang.String tipoCarteira, java.lang.String segmentacao, java.lang.String canal, java.lang.String tipoLinha, java.lang.String cdAreaRegistro, java.lang.String isAgrupado, java.lang.String idTecnologia, java.lang.String idLoja, java.lang.String nrTerminal) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.voltav.vo.VOLTAVRelatorioAcessosIncidenciaVODocument.VOLTAVRelatorioAcessosIncidenciaVO consultaRelatorioAcessoIncidencia(java.lang.String user, java.lang.String dataInicio, java.lang.String dataFinal, java.lang.String operadora, java.lang.String regional, java.lang.String tipoCarteira, java.lang.String segmentacao, java.lang.String canal, java.lang.String tipoLinha, java.lang.String idTecnologia, java.lang.String idLoja, java.lang.String nrTerminal) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.voltav.vo.VOLTAVRelatorioPrimeiroAcessoVODocument.VOLTAVRelatorioPrimeiroAcessoVO consultaRelatorioPrimeiroAcesso(java.lang.String user, java.lang.String dataInicio, java.lang.String dataFinal, java.lang.String operadora, java.lang.String regional, java.lang.String tipoCarteira, java.lang.String segmentacao, java.lang.String canal, java.lang.String tipoLinha, java.lang.String cdAreaRegistro, java.lang.String idTecnologia, java.lang.String idLoja, java.lang.String nrTerminal) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.voltav.vo.VOLTAVRelatorioTempoPermanenciaVODocument.VOLTAVRelatorioTempoPermanenciaVO consultaRelatorioTempoPermanencia(java.lang.String user, java.lang.String dataInicio, java.lang.String dataFinal, java.lang.String operadora, java.lang.String regional, java.lang.String tipoCarteira, java.lang.String segmentacao, java.lang.String canal, java.lang.String tipoLinha, java.lang.String idTecnologia, java.lang.String idLoja, java.lang.String nrTerminal) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.voltav.vo.VOLTAVRelatorioFinanceiroVODocument.VOLTAVRelatorioFinanceiroVO consultaRelatorioFinanceiro(java.lang.String user, java.lang.String dataInicio, java.lang.String dataFinal, java.lang.String operadora, java.lang.String regional, java.lang.String idPessoaDePara, java.lang.String idTipoServico, java.lang.String idTipoRecarga, java.lang.String idStatusVenda, java.lang.String nrTerminal) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.vol.acessos.vo.RELACIONAMENTOSDocument.RELACIONAMENTOS[] consultaRelatorioAcessosCliente(java.lang.String user, java.lang.String cdAreaRegistro, java.lang.String nrLinha, java.lang.String dataInicio, java.lang.String dataFinal) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.voltav.vo.VOLTAVRelatorioAdesoesVODocument.VOLTAVRelatorioAdesoesVO consultaRelatorioAdesoesBaseClientes(java.lang.String user, java.lang.String dataInicio, java.lang.String dataFinal, java.lang.String operadora, java.lang.String regional, java.lang.String tipoCarteira, java.lang.String segmentacao, java.lang.String canal, java.lang.String tipoLinha, java.lang.String idTecnologia, java.lang.String idLoja, java.lang.String nrTerminal) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.voltav.vo.VOLTAVRelatorioServicosEfetuadosVODocument.VOLTAVRelatorioServicosEfetuadosVO consultaRelatorioUtilizacaoServicos(java.lang.String user, java.lang.String dataInicio, java.lang.String dataFinal, java.lang.String operadora, java.lang.String regional, java.lang.String tipoCarteira, java.lang.String segmentacao, java.lang.String idContato, java.lang.String canal, java.lang.String tipoLinha, java.lang.String cdAreaRegistro, java.lang.String isAgrupado, java.lang.String idTecnologia, java.lang.String operadoraTerminal, java.lang.String regionalTerminal, java.lang.String idLoja, java.lang.String nrTerminal) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.voltav.vo.VOLTAVRelatorioTipoErroVODocument.VOLTAVRelatorioTipoErroVO consultaTipoErro(java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.voltav.vo.VOLTAVRelatorioFinanceiroVODocument.VOLTAVRelatorioFinanceiroVO consultaRelatorioFinanceiroDetalhado(java.lang.String user, java.lang.String dataInicio, java.lang.String dataFinal, java.lang.String operadora, java.lang.String regional, java.lang.String idPessoaDePara, java.lang.String idTipoServico, java.lang.String idTipoRecarga, java.lang.String idStatusVenda, java.lang.String nrTerminal, java.lang.String idStatusSitefVenda, java.lang.String nrLinha, java.lang.String cdAreaRegistro) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.voltav.vo.VOLTAVOperacaoRecargaVODocument.VOLTAVOperacaoRecargaVO consultaRelatorioFinanceiroDetalhadoOperacaoRecarga(java.lang.String user, java.lang.String idSitefVenda) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.voltav.vo.VOLTAVRelatorioServicosDisponveisVODocument.VOLTAVRelatorioServicosDisponveisVO consultaServicosDisponiveis(java.lang.String user, boolean isURA) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.voltav.vo.VOLTAVRelatorioServicosEfetuadosVODocument.VOLTAVRelatorioServicosEfetuadosVO consultaRelatorioBloqueioURA(java.lang.String user, java.lang.String dataInicio, java.lang.String dataFinal, java.lang.String operadora, java.lang.String regional, java.lang.String tipoCarteira, java.lang.String segmentacao, java.lang.String idContato, java.lang.String canal, java.lang.String tipoLinha, java.lang.String cdAreaRegistro, java.lang.String isAgrupado, java.lang.String idTecnologia) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.ctrls.VOLTAV.iPhone.db.ManterIphoneDB.LinhaRelatorioIphone[] consultaRelatorioIphone(java.lang.String user, java.lang.String dataInicio, java.lang.String dataFinal, java.lang.String nrLinha, java.lang.String cdAreaRegistro, java.lang.String idUFOperadora, java.lang.String idGrupoOperadora, java.lang.String inAtivadoEnvioMail) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;
}
