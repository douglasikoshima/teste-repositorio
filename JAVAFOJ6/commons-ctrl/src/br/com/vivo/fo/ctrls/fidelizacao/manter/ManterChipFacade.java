package br.com.vivo.fo.ctrls.fidelizacao.manter;

import javax.ejb.Local;

@Local
public interface ManterChipFacade {

    public br.com.vivo.fo.fidelizacao.vo.FidelizacaoManutChipVODocument.FidelizacaoManutChipVO getListaDDD(java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.fidelizacao.vo.FidelizacaoManutChipVODocument.FidelizacaoManutChipVO getChipsCadastrados(java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.retornotux.vo.RetornoVODocument.RetornoVO incluirAlterarChip(java.lang.String user, br.com.vivo.fo.fidelizacao.vo.FidelizacaoManutChipVODocument.FidelizacaoManutChipVO fidelizacaoManutChip) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.fidelizacao.vo.FidelizacaoManutChipVODocument.FidelizacaoManutChipVO getChipsCadastradosPorDDD(java.lang.String user, java.lang.String DDD) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.retornotux.vo.RetornoVODocument.RetornoVO excluirChip(java.lang.String user, br.com.vivo.fo.fidelizacao.vo.FidelizacaoManutChipVODocument.FidelizacaoManutChipVO fidelizacaoManutChip) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;
}
