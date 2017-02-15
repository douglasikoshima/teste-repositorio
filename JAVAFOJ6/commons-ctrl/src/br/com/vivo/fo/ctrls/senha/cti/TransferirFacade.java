package br.com.vivo.fo.ctrls.senha.cti;

import javax.ejb.Local;

@Local
public interface TransferirFacade {

    public br.com.vivo.fo.senha.vo.ArvoreNavegacaoUraVODocument.ArvoreNavegacaoUraVO ConsArvoreNavegacaoUra(java.lang.String xmlIn, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.senha.vo.RamaisUraVODocument.RamaisUraVO ConsRamalURA(java.lang.String idUra, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.senha.vo.RamaisVODocument.RamaisVO ConsRamal(java.lang.String idCallCenter, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;
}
