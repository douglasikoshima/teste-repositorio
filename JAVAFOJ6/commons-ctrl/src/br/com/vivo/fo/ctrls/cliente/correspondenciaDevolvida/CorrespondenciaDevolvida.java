package br.com.vivo.fo.ctrls.cliente.correspondenciaDevolvida;

import javax.ejb.Local;

@Local
public interface CorrespondenciaDevolvida {

    public void setSalvarManterCorrespDevolvida(java.lang.String user, br.com.vivo.fo.cliente.vo.ManterCorrespDevolvidaVODocument.ManterCorrespDevolvidaVO manterCorrespDevolvida) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public java.lang.String setGravarTipoCorresp(java.lang.String user, br.com.vivo.fo.cliente.vo.ListaTipoCorrespVODocument.ListaTipoCorrespVO listaTipoCorrespVO) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public java.lang.String setGravarStatusCorresp(java.lang.String user, br.com.vivo.fo.cliente.vo.ListaStatusCorrespVODocument.ListaStatusCorrespVO listaStatusCorrespVO) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public java.lang.String setGravarMotivoDevolucao(java.lang.String user, br.com.vivo.fo.cliente.vo.ListaMotivoDevolucaoVODocument.ListaMotivoDevolucaoVO listaMotivoDevolucaoVO) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public java.lang.String setAlterarStatusCorresp(java.lang.String user, br.com.vivo.fo.cliente.vo.ListaStatusCorrespVODocument.ListaStatusCorrespVO listaStatusCorrespVO) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public java.lang.String setAlterarMotivoDevolucao(java.lang.String user, br.com.vivo.fo.cliente.vo.ListaMotivoDevolucaoVODocument.ListaMotivoDevolucaoVO listaMotivoDevolucaoVO) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public java.lang.String setAlterarTipoCorresp(java.lang.String user, br.com.vivo.fo.cliente.vo.ListaTipoCorrespVODocument.ListaTipoCorrespVO listaTipoCorrespVO) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public void setSalvarTratarCorresp(java.lang.String status, java.lang.String user, br.com.vivo.fo.cliente.vo.TratarCorrespDevolvidaVODocument.TratarCorrespDevolvidaVO tratar, java.lang.String ti) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.TratarCorrespDevolvidaVODocument.TratarCorrespDevolvidaVO getTratarCorrepDevolvida(java.lang.String user, br.com.vivo.fo.cliente.vo.TratarCorrespDevolvidaVODocument.TratarCorrespDevolvidaVO tratarCorrespVO) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.CorrespDevolvidaVODocument.CorrespDevolvidaVO getCorrespDevolvida(java.lang.String user, br.com.vivo.fo.cliente.vo.CorrespDevolvidaVODocument.CorrespDevolvidaVO.FiltroCorrespDevolvida filtroCorrespDevolvida) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.ManterCorrespDevolvidaVODocument.ManterCorrespDevolvidaVO getManterCorrespDevolvidaBusca(java.lang.String user, java.lang.String pesquisa, br.com.vivo.fo.cliente.vo.ManterCorrespDevolvidaVODocument.ManterCorrespDevolvidaVO manterCorrespDevolvida) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.ListaMotivoDevolucaoVODocument.ListaMotivoDevolucaoVO getListaMotivoDevolucao(java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.ListaTipoCorrespVODocument.ListaTipoCorrespVO getListaTipoCorresp(java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.UnidadeVODocument.UnidadeVO getUnidadeDisponivelVO(java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public java.lang.String removeTipoCorresp(java.lang.String user, java.lang.String codigo) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public java.lang.String removeMotivo(java.lang.String user, java.lang.String codigo) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public java.lang.String removeStatus(java.lang.String user, java.lang.String codigo) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.CorrespDevolvidaTelaInicialVODocument.CorrespDevolvidaTelaInicialVO getCorrespDevolvidaTI(java.lang.String user, br.com.vivo.fo.cliente.vo.CorrespDevolvidaTelaInicialVODocument.CorrespDevolvidaTelaInicialVO.FiltroCorrespDevolvida filtroCorrespDevolvida) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.ListaStatusCorrespVODocument.ListaStatusCorrespVO getListaStatusCorrespVO(java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;
}
