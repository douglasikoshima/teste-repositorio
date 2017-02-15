package br.com.vivo.fo.ctrls.fidelizacao.manter;

import javax.ejb.Local;


@Local
public interface ManterDefinicaoFacade {

    public br.com.vivo.fo.retornotux.vo.RetornoVODocument.RetornoVO delDescricao(java.lang.String user, br.com.vivo.fo.fidelizacao.vo.FidelizacaoManterDelVODocument.FidelizacaoManterDelVO fidelizacaoDelVO) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.retornotux.vo.RetornoVODocument.RetornoVO setDescricao(java.lang.String user, br.com.vivo.fo.fidelizacao.vo.FidelizacaoManterAlteraVODocument.FidelizacaoManterAlteraVO fideAltera) throws br.com.vivo.fo.exception.TuxedoException, org.apache.xmlbeans.XmlException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.retornotux.vo.RetornoVODocument.RetornoVO addDescricao(java.lang.String user, br.com.vivo.fo.fidelizacao.vo.FidelizacaoManterInVODocument.FidelizacaoManterInVO fidManteVO) throws br.com.vivo.fo.exception.FacadeException, org.apache.xmlbeans.XmlException, br.com.vivo.fo.exception.TuxedoException;

    public br.com.vivo.fo.fidelizacao.vo.FidelizacaoListaGeralVODocument.FidelizacaoListaGeralVO getDescricao(java.lang.String user, br.com.vivo.fo.fidelizacao.vo.FidelizacaoManterVODocument.FidelizacaoManterVO fideVO) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;
}
