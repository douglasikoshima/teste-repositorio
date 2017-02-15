package br.com.vivo.fo.ctrls.usuario.manterUsuarioSkill;

import javax.ejb.Local;

@Local
public interface ManterUsuarioSkillFacade {

    public br.com.vivo.fo.admsistemas.vo.AdmSkillUsuarioVODocument.AdmSkillUsuarioVO getUsuarioSkill(br.com.vivo.fo.admsistemas.vo.AdmSkillUsuarioVODocument.AdmSkillUsuarioVO admSkillUsuarioVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public void setUsuarioSkill(br.com.vivo.fo.admsistemas.vo.AdmSkillUsuarioVODocument.AdmSkillUsuarioVO admSkillUsuarioVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public void excluiUsuarioSkill(br.com.vivo.fo.admsistemas.vo.AdmSkillUsuarioVODocument.AdmSkillUsuarioVO admSkillUsuarioVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.admsistemas.vo.AdmSkillUsuarioVODocument.AdmSkillUsuarioVO pesquisaUsuarioSkill(br.com.vivo.fo.admsistemas.vo.AdmSkillUsuarioVODocument.AdmSkillUsuarioVO admSkillUsuarioVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.commons.utils.EstruturaSkillTO getUsuarioSkillTunado(br.com.vivo.fo.admsistemas.vo.AdmSkillUsuarioVODocument.AdmSkillUsuarioVO admSkillUsuarioVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.commons.utils.EstruturaSkillTO getSkillsByIdGrupo(br.com.vivo.fo.admsistemas.vo.AdmSkillUsuarioVODocument.AdmSkillUsuarioVO admSkillUsuarioVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.commons.utils.EstruturaSkillTO getListaContatosByIdSkill(br.com.vivo.fo.admsistemas.vo.AdmSkillContatoVODocument.AdmSkillContatoVO admSkillContatoVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.commons.utils.EstruturaSkillTO setListaContatos(br.com.vivo.fo.admsistemas.vo.AdmSkillContatoVODocument.AdmSkillContatoVO admSkillContatoVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.commons.utils.EstruturaSkillTO getUsuarioSkillExistente(br.com.vivo.fo.admsistemas.vo.AdmSkillUsuarioVODocument.AdmSkillUsuarioVO admSkillUsuarioVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.commons.utils.EstruturaSkillTO getUsuarioSkillAssociado(br.com.vivo.fo.admsistemas.vo.AdmSkillUsuarioVODocument.AdmSkillUsuarioVO admSkillUsuarioVO, java.lang.String user, br.com.vivo.fo.commons.utils.EstruturaSkillTO estruturaSkillTO) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.commons.utils.EstruturaSkillTO getContatoSkillExistente(br.com.vivo.fo.admsistemas.vo.AdmSkillUsuarioVODocument.AdmSkillUsuarioVO admSkillUsuarioVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.commons.utils.EstruturaSkillTO getContatoSkillAssociado(br.com.vivo.fo.admsistemas.vo.AdmSkillUsuarioVODocument.AdmSkillUsuarioVO admSkillUsuarioVO, java.lang.String user, br.com.vivo.fo.commons.utils.EstruturaSkillTO estruturaSkillTO) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public void setContatoSkill(br.com.vivo.fo.admsistemas.vo.AdmSkillUsuarioVODocument.AdmSkillUsuarioVO admSkillUsuarioVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public void setContatoSkillMult(br.com.vivo.fo.admsistemas.vo.AdmSkillUsuarioVODocument.AdmSkillUsuarioVO admSkillUsuarioVO, java.lang.String user, java.lang.String[] contatos) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public void setUsuarioSkillMult(br.com.vivo.fo.admsistemas.vo.AdmSkillUsuarioVODocument.AdmSkillUsuarioVO admSkillUsuarioVO, java.lang.String user, java.lang.String[] usuarios) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;
}
