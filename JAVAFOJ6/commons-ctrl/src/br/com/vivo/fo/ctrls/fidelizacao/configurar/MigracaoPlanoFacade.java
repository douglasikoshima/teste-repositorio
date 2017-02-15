package br.com.vivo.fo.ctrls.fidelizacao.configurar;

import javax.ejb.Local;

@Local
public interface MigracaoPlanoFacade {

    public br.com.vivo.fo.fidelizacao.vo.FidelizacaoListaGeralVODocument.FidelizacaoListaGeralVO getRegionais(java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public void delMigracaoPlano(java.lang.String user, java.lang.String id) throws br.com.vivo.fo.exception.FacadeException, br.com.vivo.fo.exception.TuxedoException;

    public br.com.vivo.fo.fidelizacao.vo.ListaMigracaoVODocument.ListaMigracaoVO getMigracaoPlano(java.lang.String user, br.com.vivo.fo.fidelizacao.vo.BonusPesquisaVODocument.BonusPesquisaVO bonusPVO) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public java.lang.String setMigracaoPlano(java.lang.String user, br.com.vivo.fo.fidelizacao.vo.MigracaoVODocument.MigracaoVO migracao) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public void addMigracaoPlano(java.lang.String user, br.com.vivo.fo.fidelizacao.vo.MigracaoInVODocument.MigracaoInVO migacaoInVO) throws br.com.vivo.fo.exception.FacadeException, org.apache.xmlbeans.XmlException, br.com.vivo.fo.exception.TuxedoException;
}
