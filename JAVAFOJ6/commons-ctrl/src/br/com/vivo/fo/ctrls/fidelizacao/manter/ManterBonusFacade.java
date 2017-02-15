package br.com.vivo.fo.ctrls.fidelizacao.manter;

import javax.ejb.Local;

@Local
public interface ManterBonusFacade {

    public br.com.vivo.fo.fidelizacao.vo.ListaBonusVODocument.ListaBonusVO getBonus(java.lang.String user, br.com.vivo.fo.fidelizacao.vo.BonusPesquisaVODocument.BonusPesquisaVO bonuspVO) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.fidelizacao.vo.FidelizacaoListaGeralDescricaoVODocument.FidelizacaoListaGeralDescricaoVO getTipoOfertaBonus(java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.fidelizacao.vo.FidelizacaoListaGeralVODocument.FidelizacaoListaGeralVO getApoioBonus(java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.retornotux.vo.RetornoVODocument.RetornoVO delBonus(java.lang.String user, br.com.vivo.fo.fidelizacao.vo.BonusDelVODocument.BonusDelVO idBonus) throws br.com.vivo.fo.exception.FacadeException, br.com.vivo.fo.exception.TuxedoException;

    public br.com.vivo.fo.retornotux.vo.RetornoVODocument.RetornoVO setBonus(java.lang.String user, br.com.vivo.fo.fidelizacao.vo.BonusVODocument.BonusVO bonus) throws br.com.vivo.fo.exception.TuxedoException, org.apache.xmlbeans.XmlException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.retornotux.vo.RetornoVODocument.RetornoVO addBonus(java.lang.String user, br.com.vivo.fo.fidelizacao.vo.BonusPesquisaInVODocument.BonusPesquisaInVO bonusInVO) throws br.com.vivo.fo.exception.FacadeException, org.apache.xmlbeans.XmlException, br.com.vivo.fo.exception.TuxedoException;
}
