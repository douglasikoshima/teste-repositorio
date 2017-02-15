package br.com.vivo.fo.ctrls.VOLTAV.manterInSid;

import javax.ejb.Local;

@Local
public interface ConfigurarInSidFacade {

    //br.com.vivo.fo.voltav.vo.VOLTAVRegionalVODocument.VOLTAVRegionalVO obterRegional(java.lang.String user) throws br.com.vivo.fo.exception.FacadeException;
    public br.com.vivo.fo.voltav.vo.ListaPesquisaInSidVODocument.ListaPesquisaInSidVO pesquisarInSid(java.lang.String user, java.lang.String idCanal, java.lang.String idOperadora, java.lang.String idApi) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.voltav.vo.ListaFuncionalidadeInSidVODocument.ListaFuncionalidadeInSidVO obterFuncionalidades(java.lang.String user) throws br.com.vivo.fo.exception.FacadeException;

    public void alterarSid(java.lang.String user, java.lang.String[] selecionados, java.lang.String nrNewSid) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.voltav.vo.VOLTAVListaRegionalVODocument.VOLTAVListaRegionalVO obterRegional(java.lang.String user) throws br.com.vivo.fo.exception.FacadeException;
}
