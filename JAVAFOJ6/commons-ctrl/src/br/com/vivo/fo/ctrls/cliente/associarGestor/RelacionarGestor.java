package br.com.vivo.fo.ctrls.cliente.associarGestor;

import javax.ejb.Local;

@Local
public interface RelacionarGestor {

    public br.com.vivo.fo.cliente.vo.GrupoCRIVODocument.GrupoCRIVO getGruposCRI(java.lang.String user) throws java.lang.Exception;

    public br.com.vivo.fo.cliente.vo.GrupoCRIVODocument.GrupoCRIVO getPesquisaGruposCRI(java.lang.String user, br.com.vivo.fo.cliente.vo.GrupoCRIVODocument.GrupoCRIVO filtro) throws java.lang.Exception;

    public void setGravarGruposCRI(java.lang.String user, br.com.vivo.fo.cliente.vo.GrupoCRIVODocument.GrupoCRIVO salvar) throws java.lang.Exception;

    public br.com.vivo.fo.cliente.vo.AssociarCRVODocument.AssociarCRVO getListasAssociar(java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.AssociarCRVODocument.AssociarCRVO getClientesPesquisa(java.lang.String user, br.com.vivo.fo.cliente.vo.AssociarCRVODocument.AssociarCRVO filtroPesquisa) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public void setGravarAssociar(java.lang.String user, br.com.vivo.fo.cliente.vo.AssociarCRVODocument.AssociarCRVO salvarAssociar, java.lang.String idConsultor) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.ConsultorRelacionamentoVODocument.ConsultorRelacionamentoVO getConsultorRelacionamentoVO(java.lang.String user, br.com.vivo.fo.cliente.vo.ConsultorRelacionamentoVODocument.ConsultorRelacionamentoVO filtros) throws java.lang.Exception;

    public br.com.vivo.fo.cliente.vo.GestorContasVODocument.GestorContasVO getGestorContas(java.lang.String user, br.com.vivo.fo.cliente.vo.GestorContasVODocument.GestorContasVO gestorContasVO) throws java.lang.Exception;

    public void setGestorContas(java.lang.String user, br.com.vivo.fo.cliente.vo.GestorContasVODocument.GestorContasVO gestorContasVO) throws java.lang.Exception;

    public br.com.vivo.fo.cliente.vo.ConsultorRelacionamentoVODocument.ConsultorRelacionamentoVO setConsultorRelacionamentoVO(java.lang.String user, br.com.vivo.fo.cliente.vo.ConsultorRelacionamentoVODocument.ConsultorRelacionamentoVO consultorRelacionamento) throws java.lang.Exception;

    public br.com.vivo.fo.ctrls.cliente.associarGestor.db.PessoaConsultor[] getConsultorRelacionamento(java.lang.String cdCnpjEmpresa) throws java.lang.Exception;

    public br.com.vivo.fo.ctrls.cliente.associarGestor.db.Gestor[] getGestoresConta(java.lang.String cdCnpjEmpresa, java.lang.String idconta) throws java.lang.Exception;

    public br.com.vivo.fo.ctrls.cliente.associarGestor.db.ClienteEspecial[] getClienteEspecial(java.lang.String cdCnpjEmpresa) throws java.lang.Exception;
}
