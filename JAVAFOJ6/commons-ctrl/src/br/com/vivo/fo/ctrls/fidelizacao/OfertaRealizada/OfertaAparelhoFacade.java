package br.com.vivo.fo.ctrls.fidelizacao.OfertaRealizada;

public interface OfertaAparelhoFacade {

    public br.com.vivo.fo.cliente.vo.PesquisaEnderecoVODocument.PesquisaEnderecoVO getPesquisaEnderecoIni(java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.PesquisaEnderecoVODocument.PesquisaEnderecoVO getPesquisaEnderecoFil(java.lang.String user, br.com.vivo.fo.cliente.vo.PesquisaEnderecoVODocument.PesquisaEnderecoVO filtroEndereco) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.fidelizacao.vo.FidelizacaoListaGeralVODocument.FidelizacaoListaGeralVO getRegional(java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.retornotux.vo.RetornoVODocument.RetornoVO addAparelho2(java.lang.String user, java.lang.String[] dados, br.com.vivo.fo.fidelizacao.vo.FidelizacaoListaGeralDescricaoVODocument.FidelizacaoListaGeralDescricaoVO ofertasReal, java.lang.String[] ofertasAceita, java.lang.String[] dadosEntrega, java.lang.String tipoEncerramento) throws br.com.vivo.fo.exception.FacadeException, org.apache.xmlbeans.XmlException, br.com.vivo.fo.exception.TuxedoException;

    public br.com.vivo.fo.cliente.vo.EnderecoVODocument.EnderecoVO getEndecoPorCEP(java.lang.String user, java.lang.String nrCEP) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.retornotux.vo.RetornoVODocument.RetornoVO addAparelho(java.lang.String user, java.lang.String[] dados, java.lang.String[] ofertasReal, java.lang.String[] ofertasAceita, java.lang.String[] dadosEntrega, java.lang.String tipoEncerramento) throws br.com.vivo.fo.exception.FacadeException, org.apache.xmlbeans.XmlException, br.com.vivo.fo.exception.TuxedoException;

    public br.com.vivo.fo.retornotux.vo.RetencaoRetornoSAPVODocument.RetencaoRetornoSAPVO getComSAP(java.lang.String user, br.com.vivo.fo.fidelizacao.vo.RetencaoSAPVODocument.RetencaoSAPVO rSAPVO) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.retornotux.vo.RetornoVODocument.RetornoVO getDiasSuspTemp(java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.fidelizacao.vo.OfertaAparelhoVODocument.OfertaAparelhoVO getAparelho(java.lang.String user, br.com.vivo.fo.fidelizacao.vo.OfAparelhoParamVODocument.OfAparelhoParamVO ofAparelhoParam) throws br.com.vivo.fo.exception.FacadeException, br.com.vivo.fo.exception.TuxedoException;

    public br.com.vivo.fo.fidelizacao.vo.FidelizacaoListaGeralVODocument.FidelizacaoListaGeralVO getPercDesconto(java.lang.String user, java.lang.String idMatrizAparelho, java.lang.String excecao, int idSegmentacao) throws br.com.vivo.fo.exception.FacadeException, br.com.vivo.fo.exception.TuxedoException;

    public br.com.vivo.fo.fidelizacao.vo.FidelizacaoListaGeralVODocument.FidelizacaoListaGeralVO getParcelas(java.lang.String user, java.lang.String idMatrizAparelho, java.lang.String idOperacao, java.lang.String idTipoPessoa) throws br.com.vivo.fo.exception.FacadeException, br.com.vivo.fo.exception.TuxedoException;
}
