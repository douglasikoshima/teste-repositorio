package br.com.vivo.fo.ctrls.fidelizacao.configurar;

import javax.ejb.Local;

@Local
public interface MatrizOfertaFacade {

    public br.com.vivo.fo.fidelizacao.vo.ItemArvoreVODocument.ItemArvoreVO getArvore(java.lang.String user) throws br.com.vivo.fo.exception.FacadeException, br.com.vivo.fo.exception.TuxedoException;

    public br.com.vivo.fo.fidelizacao.vo.ItemArvoreVODocument.ItemArvoreVO getArvoreMatriz(java.lang.String user, java.lang.String xmlIdPais) throws br.com.vivo.fo.exception.FacadeException, br.com.vivo.fo.exception.TuxedoException;

    public br.com.vivo.fo.fidelizacao.vo.ArrayListaGeralVODocument.ArrayListaGeralVO getOfertas(java.lang.String user, br.com.vivo.fo.fidelizacao.vo.RetencaoXMLINMatrizOfertasVODocument.RetencaoXMLINMatrizOfertasVO dados) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public void addOferta(java.lang.String user, br.com.vivo.fo.fidelizacao.vo.RetencaoXMLINMatrizOfertasVODocument.RetencaoXMLINMatrizOfertasVO dados) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public void setOferta(java.lang.String user, br.com.vivo.fo.fidelizacao.vo.RetencaoXMLINMatrizOfertasVODocument.RetencaoXMLINMatrizOfertasVO dados) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.fidelizacao.vo.ArrayListaGeralVODocument.ArrayListaGeralVO getDadosIniciais(java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.fidelizacao.vo.FidelizacaoListaGeralVODocument.FidelizacaoListaGeralVO getDestino(java.lang.String user, br.com.vivo.fo.fidelizacao.vo.RetencaoXMLINMatrizOfertasVODocument.RetencaoXMLINMatrizOfertasVO dados) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;
}
