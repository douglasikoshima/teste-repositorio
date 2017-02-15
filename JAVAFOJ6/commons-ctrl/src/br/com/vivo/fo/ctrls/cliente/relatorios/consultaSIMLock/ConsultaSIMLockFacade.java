package br.com.vivo.fo.ctrls.cliente.relatorios.consultaSIMLock;

import javax.ejb.Local;

@Local
public interface ConsultaSIMLockFacade {

    public br.com.vivo.fo.cliente.vo.RelatorioSimLockVODocument.RelatorioSimLockVO getRelatorioDownloadOld(java.lang.String user, br.com.vivo.fo.cliente.vo.RelatorioSimLockVODocument.RelatorioSimLockVO relatorio) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.RelatorioSimLockVODocument.RelatorioSimLockVO getRelatorioDownload(java.lang.String user, br.com.vivo.fo.cliente.vo.RelatorioSimLockVODocument.RelatorioSimLockVO relatorio) throws java.lang.Exception;

    public br.com.vivo.fo.cliente.vo.RelatorioSimLockVODocument.RelatorioSimLockVO getPaginaRelatorio(java.lang.String user, br.com.vivo.fo.cliente.vo.RelatorioSimLockVODocument.RelatorioSimLockVO relatorio) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.RelatorioSimLockVODocument.RelatorioSimLockVO getTotalRegistros(java.lang.String user, br.com.vivo.fo.cliente.vo.RelatorioSimLockVODocument.RelatorioSimLockVO relatorio) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;
}
