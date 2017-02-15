package br.com.vivo.fo.ctrls.admsistemas.variaveisArvoreContato;

import javax.ejb.Local;

@Local
public interface VariaveisArvoreContato {

    public void exportarVariaveisArvoreContato(br.com.vivo.fo.admsistemas.vo.VariaveisArvoreContatoVODocument.VariaveisArvoreContatoVO variaveisArvoreContatoVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.admsistemas.vo.VariaveisArvoreContatoVODocument.VariaveisArvoreContatoVO getFiltrosSelecionados(br.com.vivo.fo.admsistemas.vo.VariaveisArvoreContatoVODocument.VariaveisArvoreContatoVO variaveisArvoreContatoVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.admsistemas.vo.AdmVarAContatoArqGeradosVODocument.AdmVarAContatoArqGeradosVO getListaArquivosGerados(br.com.vivo.fo.admsistemas.vo.VariaveisArvoreContatoVODocument.VariaveisArvoreContatoVO variaveisArvoreContatoVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;
}
