package br.com.vivo.fo.ctrls.fidelizacao.configurar;

import javax.ejb.Local;

@Local
public interface MatrizAparelhoFacade {

    public br.com.vivo.fo.fidelizacao.vo.DetalheAparelhoVODocument.DetalheAparelhoVO getDetalheAparelho(java.lang.String user, java.lang.String id) throws br.com.vivo.fo.exception.FacadeException, br.com.vivo.fo.exception.TuxedoException;

    public br.com.vivo.fo.fidelizacao.vo.ArrayListaGeralVODocument.ArrayListaGeralVO getDadosIniciais(java.lang.String user) throws br.com.vivo.fo.exception.FacadeException, br.com.vivo.fo.exception.TuxedoException;

    public br.com.vivo.fo.fidelizacao.vo.FidelizacaoListaGeralVODocument.FidelizacaoListaGeralVO getParcelas(java.lang.String user, java.lang.String idMatrizAparelho, java.lang.String operacao) throws br.com.vivo.fo.exception.FacadeException, br.com.vivo.fo.exception.TuxedoException;

    public br.com.vivo.fo.fidelizacao.vo.ItemArvoreVODocument.ItemArvoreVO getArvoreMatriz(java.lang.String user, java.lang.String xmlIdPais) throws br.com.vivo.fo.exception.FacadeException, br.com.vivo.fo.exception.TuxedoException;

    public br.com.vivo.fo.fidelizacao.vo.ArrayListaGeralVODocument.ArrayListaGeralVO getAparelhos(java.lang.String user, java.lang.String regional, java.lang.String segmentacao, java.lang.String tipoCliente, java.lang.String idGrupo) throws br.com.vivo.fo.exception.FacadeException, br.com.vivo.fo.exception.TuxedoException;

    public void setAparelhos(java.lang.String user, java.lang.String regional, java.lang.String segmentacao, java.lang.String tipoCliente, java.lang.String[] aparelhos, java.lang.String idGrupo) throws br.com.vivo.fo.exception.FacadeException, br.com.vivo.fo.exception.TuxedoException;

    public void setDetAparelho(java.lang.String user, java.lang.String idMatriz, java.lang.String vlMulta, br.com.vivo.fo.fidelizacao.vo.AparelhoCorVODocument.AparelhoCorVO[] detalheAparelhos, br.com.vivo.fo.fidelizacao.vo.ListaDDDVODocument.ListaDDDVO[] listaDDD, java.lang.String[] descontos, java.lang.String[] parcelas, java.lang.String regional, java.lang.String inChipAvulso, java.lang.String inChipPreProgramado) throws br.com.vivo.fo.exception.FacadeException, br.com.vivo.fo.exception.TuxedoException;
}
