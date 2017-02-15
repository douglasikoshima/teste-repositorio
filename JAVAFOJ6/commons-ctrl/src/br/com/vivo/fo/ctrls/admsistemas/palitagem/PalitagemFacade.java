package br.com.vivo.fo.ctrls.admsistemas.palitagem;

import javax.ejb.Local;

@Local
public interface PalitagemFacade {

    public void setAdmContatoPalitagemVO(java.lang.String idUsuario, br.com.vivo.fo.admsistemas.vo.AdmContatoPalitagemVODocument.AdmContatoPalitagemVO admContatoPalitagemVO) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.admsistemas.vo.AdmContatoPalitagemVODocument.AdmContatoPalitagemVO getAdmContatoPalitagemVO(java.lang.String idUsuario, br.com.vivo.fo.admsistemas.vo.AdmContatoPalitagemVODocument.AdmContatoPalitagemVO admContatoPalitagemVO) throws br.com.vivo.fo.exception.FacadeException;
}
