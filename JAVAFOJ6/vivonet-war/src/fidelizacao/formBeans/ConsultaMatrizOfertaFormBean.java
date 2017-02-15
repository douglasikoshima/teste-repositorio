package fidelizacao.formBeans;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.fidelizacao.vo.ItemListaDescricaoVODocument.ItemListaDescricaoVO;
import br.com.vivo.fo.fidelizacao.vo.ItemOfertaAparelhoVODocument.ItemOfertaAparelhoVO;
import fidelizacao.FidelizacaoController.ShowMatrizOfertaForm;

public class ConsultaMatrizOfertaFormBean extends ActionForm {

    private static final long      serialVersionUID     = -8615422097604318666L;

    private ShowMatrizOfertaForm   showMatrizOferForm;
    private String                 valorMultaContrato   = ConstantesCRM.SVAZIO;
    private String                 vencimentoContrato   = ConstantesCRM.SVAZIO;
    private String                 mensagemCancel       = ConstantesCRM.SVAZIO;
    private String                 multaProRata         = ConstantesCRM.SVAZIO;
    private String                 idUfOperadora        = ConstantesCRM.SVAZIO;
    private ItemOfertaAparelhoVO[] aparelhosArray       = new ItemOfertaAparelhoVO[0];
    private String                 tipoEncerramento     = ConstantesCRM.SVAZIO;
    private String                 idRetencao           = ConstantesCRM.SVAZIO;
    private String                 observacao           = ConstantesCRM.SVAZIO;
    private String                 planoSelecionado     = ConstantesCRM.SVAZIO;
    private String                 plano                = ConstantesCRM.SVAZIO;
    private String                 saldo                = ConstantesCRM.SVAZIO;
    private String                 validade             = ConstantesCRM.SVAZIO;
    private String                 numNovo              = ConstantesCRM.SVAZIO;
    private String                 quantidade           = ConstantesCRM.SVAZIO;
    private String                 dtFim                = ConstantesCRM.SVAZIO;
    private String                 dtInicio             = ConstantesCRM.SVAZIO;
    private String                 bonusSelecionado     = ConstantesCRM.SVAZIO;
    // private String[] ofertasAceitas;
    private String                 index                = ConstantesCRM.SVAZIO;
    private boolean                inExcecao            = false;
    private String                 meioPagamento        = ConstantesCRM.SVAZIO;
    private String                 idMeioPagamento      = ConstantesCRM.SVAZIO;
    private String                 vlParcela            = ConstantesCRM.SVAZIO;
    private String                 nroParcela           = ConstantesCRM.SVAZIO;
    private String                 vlDesconto           = ConstantesCRM.SVAZIO;
    private String                 percDesconto         = ConstantesCRM.SVAZIO;
    private String                 przVigencia          = ConstantesCRM.SVAZIO;
    private String                 SAP                  = ConstantesCRM.SVAZIO;
    private String                 ofertaSelecionada    = ConstantesCRM.SVAZIO;
    private String[]               ofertasDisp          = new String[0];
    private String[]               ofertasReal          = new String[0];
    private String[]               ofertasAceita        = new String[0];
    private ItemListaDescricaoVO[] listaOfertaDisp      = new ItemListaDescricaoVO[0];
    private ItemListaDescricaoVO[] listaOfertaReal      = new ItemListaDescricaoVO[0];
    private ItemListaDescricaoVO[] listaOfertaAceita    = new ItemListaDescricaoVO[0];
    //private FinalRetencaoVO        finalRetencaoFormVO  = FinalRetencaoVO.Factory.newInstance();
    private String                 vlCalcularDesconto   = ConstantesCRM.SVAZIO;
    private String                 sgOfertaAceita       = ConstantesCRM.SVAZIO;
    private String                 inTipoPessoa         = ConstantesCRM.SVAZIO;
    private String                 idTipoCarteira       = ConstantesCRM.SVAZIO;
    private boolean                inCancelamentoLinhasAssociadas;
    private String[]               nrLinhasSelecionadas = null;
    private int                    idPalitagem;
    private String                 vlMinimoParcela      = ConstantesCRM.SZERO;

    /*
     * //public FinalRetencaoVO getFinalRetencaoFormVO(DadosClienteForm dadosCliente, Hashtable hashCaracteristicas)
     * public FinalRetencaoVO getFinalRetencaoFormVO(DadosClienteForm dadosCliente, CaractOfertasAceitasVO[]
     * listaCaracteristicaVO, RetencaoDadosEntregaVO dadosEntregaVO){ /************* Preenche informações cabeçalho
     * ***********************************
     */
    /*
     * finalRetencaoFormVO.setIdTipoEncerramento(tipoEncerramento);
     * finalRetencaoFormVO.setIdPessoaDePara(dadosCliente.getIdPessoaDePara());
     * finalRetencaoFormVO.setIdRespostaIntencao(dadosCliente.getIdIntencaoCancelamento());
     * finalRetencaoFormVO.setIdRespostaDestino(dadosCliente.getIdDestinoPrevisto());
     * finalRetencaoFormVO.setDsObservacao("");
     * finalRetencaoFormVO.setIdLinhaTelefonica(dadosCliente.getDetalheLinha().getIdLinha());
     * finalRetencaoFormVO.setNrLinha(dadosCliente.getDetalheLinha().getNumero());
     */
    /********************** Listas Ofertas Realizadas **********************************/
    /*
     * int tamArr = 0; if ((ofertasReal != null)&&(ofertasReal.length > 0)){ tamArr += ofertasReal.length; } if
     * ((ofertasAceita != null)&&(ofertasAceita.length > 0)){ tamArr += ofertasAceita.length; } ListaOfertasRetencaoVO[]
     * listaOfertas = new ListaOfertasRetencaoVO[tamArr];
     * int i = 0; if (ofertasReal != null){ for(i=0;i<ofertasReal.length;i++){ ListaOfertasRetencaoVO ofertaRealVO =
     * ListaOfertasRetencaoVO.Factory.newInstance(); ofertaRealVO.setId(ofertasReal[i]);
     * ofertaRealVO.setComentOferta(""); ofertaRealVO.setInAceita("0"); ofertaRealVO.setInExcecao("0"); listaOfertas[i]
     * = ofertaRealVO; } }
     */
    /********************** Ofertas Aceitas **********************************/
    /*
     * if ((ofertasAceita != null)&&(ofertasAceita.length > 0)){ ListaOfertasRetencaoVO ofertaAceitasVO =
     * ListaOfertasRetencaoVO.Factory.newInstance(); ofertaAceitasVO.setId(ofertasAceita[0]);
     * ofertaAceitasVO.setComentOferta(""); ofertaAceitasVO.setInAceita("1"); if ((inExcecao !=
     * null)&&((inExcecao.equals("1"))||(inExcecao.equalsIgnoreCase("true")))) ofertaAceitasVO.setInExcecao("1"); else
     * ofertaAceitasVO.setInExcecao("0"); listaOfertas[i] = ofertaAceitasVO; }
     */
    /****************** Adicionando listas ao VO Principal ***************************************/
    /*
     * finalRetencaoFormVO.addNewListaOfertasRetencaoVO();
     * finalRetencaoFormVO.setListaOfertasRetencaoVOArray(listaOfertas);
     */
    /***************** Caracterisiticas ofertas Aceitas *******************************************/
    /*
     * if(listaCaracteristicaVO != null){ finalRetencaoFormVO.addNewCaractOfertasAceitasVO();
     * finalRetencaoFormVO.setCaractOfertasAceitasVOArray(listaCaracteristicaVO); }
     */
    /***************** Dados para entrega (somente para aparelhos) **********************************/
    /*
     * if(dadosEntregaVO != null){ finalRetencaoFormVO.addNewRetencaoDadosEntregaVO();
     * finalRetencaoFormVO.setRetencaoDadosEntregaVO(dadosEntregaVO); }
     * return this.finalRetencaoFormVO; }
     */
    /************** Fim Décio JR *********************************************************/

    public void setVlMinimoParcela(String vlMinimoParcela) {
        this.vlMinimoParcela = vlMinimoParcela;
    }

    public String getVlMinimoParcela() {
        return this.vlMinimoParcela;
    }

    public void setInTipoPessoa(String valor) {
        this.inTipoPessoa = SAP;
    }

    public String getInTipoPessoa() {
        return this.inTipoPessoa;
    }

    public void setIdTipoCarteira(String valor) {
        this.idTipoCarteira = SAP;
    }

    public String getIdTipoCarteira() {
        return this.idTipoCarteira;
    }

    public void setSAP(String SAP) {
        this.SAP = SAP;
    }

    public String getSAP() {
        return this.SAP;
    }

    public void setOfertaSelecionada(String ofertaSelecionada) {
        this.ofertaSelecionada = ofertaSelecionada;
    }

    public String getOfertaSelecionada() {
        return this.ofertaSelecionada;
    }

    public String[] getOfertasDisp() {
        return ofertasDisp;
    }

    public void setOfertasDisp(String[] ofertas) {
        this.ofertasDisp = ofertas;
    }

    public ItemListaDescricaoVO[] getListaOfertaDisp() {
        return this.listaOfertaDisp;
    }

    public void setListaOfertaDisp(ItemListaDescricaoVO[] sLista) {
        this.listaOfertaDisp = sLista;
    }

    public String[] getOfertasReal() {
        return ofertasReal;
    }

    public void setOfertasReal(String[] ofertas) {
        this.ofertasReal = ofertas;
    }

    public ItemListaDescricaoVO[] getListaOfertaReal() {
        return this.listaOfertaReal;
    }

    public void setListaOfertaReal(ItemListaDescricaoVO[] sLista) {
        this.listaOfertaReal = sLista;
    }

    public String[] getOfertasAceita() {
        return ofertasAceita;
    }

    public void setOfertasAceita(String[] ofertas) {
        this.ofertasAceita = ofertas;
    }

    public ItemListaDescricaoVO[] getListaOfertaAceita() {
        return this.listaOfertaAceita;
    }

    public void setListaOfertaAceita(ItemListaDescricaoVO[] sLista) {
        this.listaOfertaAceita = sLista;
    }

    public void setPrzVigencia(String valor) {
        this.przVigencia = valor;
    }

    public String getPrzVigencia() {
        return this.przVigencia;
    }

    public void setPercDesconto(String percDesconto) {
        this.percDesconto = percDesconto;
    }

    public String getPercDesconto() {
        return this.percDesconto;
    }

    public void setVlDesconto(String vlDesconto) {
        this.vlDesconto = vlDesconto;
    }

    public String getVlDesconto() {
        return this.vlDesconto;
    }

    public void setVlCalcularDesconto(String vlCalcularDesconto) {
        this.vlCalcularDesconto = vlCalcularDesconto;
    }

    public String getVlCalcularDesconto() {
        return this.vlCalcularDesconto;
    }

    public void setNroParcela(String nroParcela) {
        this.nroParcela = nroParcela;
    }

    public String getNroParcela() {
        return this.nroParcela;
    }

    public void setVlParcela(String vlParcela) {
        this.vlParcela = vlParcela;
    }

    public String getVlParcela() {
        return this.vlParcela;
    }

    public void setMeioPagamento(String meioPagamento) {
        this.meioPagamento = meioPagamento;
    }

    public String getMeioPagamento() {
        return this.meioPagamento;
    }

    public void setIdMeioPagamento(String idMeioPagamento) {
        this.idMeioPagamento = idMeioPagamento;
    }

    public String getIdMeioPagamento() {
        if (this.idMeioPagamento == null) {
            return "";
        } else {
            return this.idMeioPagamento;
        }
    }

    public void setInExcecao(boolean inExcecao) {
        this.inExcecao = inExcecao;
    }

    public boolean getInExcecao() {
        return this.inExcecao;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getIndex() {
        return this.index;
    }

    /*
     * public void setOfertasAceitas(String[] ofertasAceitas){ this.ofertasAceitas = ofertasAceitas; }
     * public String[] getOfertasAceitas(){ return this.ofertasAceitas; }
     */
    public void setBonusSelecionado(String bonusSelecionado) {
        this.bonusSelecionado = bonusSelecionado;
    }

    public String getBonusSelecionado() {
        return this.bonusSelecionado;
    }

    public void setDtInicio(String dtInicio) {
        this.dtInicio = dtInicio;
    }

    public String getDtInicio() {
        return this.dtInicio;
    }

    public void setDtFim(String dtFim) {
        this.dtFim = dtFim;
    }

    public String getDtFim() {
        return this.dtFim;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public String getQuantidade() {
        return this.quantidade;
    }

    public void setNumNovo(String numNovo) {
        this.numNovo = numNovo;
    }

    public String getNumNovo() {
        return this.numNovo;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public String getValidade() {
        return this.validade;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }

    public String getSaldo() {
        return this.saldo;
    }

    public void setPlano(String plano) {
        this.plano = plano;
    }

    public String getPlano() {
        return this.plano;
    }

    public void setPlanoSelecionado(String planoSelecionado) {
        this.planoSelecionado = planoSelecionado;
    }

    public String getPlanoSelecionado() {
        return this.planoSelecionado;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getObservacao() {
        return this.observacao;
    }

    public void setIdRetencao(String idRetencao) {
        this.idRetencao = idRetencao;
    }

    public String getIdRetencao() {
        return this.idRetencao;
    }

    public void setTipoEncerramento(String tipoEncerramento) {
        this.tipoEncerramento = tipoEncerramento;
    }

    public String getTipoEncerramento() {
        return this.tipoEncerramento;
    }

    public void setAparelhosArray(ItemOfertaAparelhoVO[] aparelhosArray) {
        this.aparelhosArray = aparelhosArray;
    }

    public ItemOfertaAparelhoVO[] getAparelhosArray() {
        return this.aparelhosArray;
    }

    public void setIdUfOperadora(String idUfOperadora) {
        this.idUfOperadora = idUfOperadora;
    }

    public String getIdUfOperadora() {
        return this.idUfOperadora;
    }

    public void setMultaProRata(String multaProRata) {
        this.multaProRata = multaProRata;
    }

    public String getMultaProRata() {
        return this.multaProRata;
    }

    public void setMensagemCancel(String mensagemCancel) {
        this.mensagemCancel = mensagemCancel;
    }

    public String getMensagemCancel() {
        return this.mensagemCancel;
    }

    public void setVencimentoContrato(String vencimentoContrato) {
        this.vencimentoContrato = vencimentoContrato;
    }

    public String getVencimentoContrato() {
        return this.vencimentoContrato;
    }

    public void setValorMultaContrato(String valorMultaContrato) {
        this.valorMultaContrato = valorMultaContrato;
    }

    public String getValorMultaContrato() {
        return this.valorMultaContrato;
    }

    public void setSgOfertaAceita(String valor) {
        this.sgOfertaAceita = valor;
    }

    public String getSgOfertaAceita() {
        return this.sgOfertaAceita;
    }

    public void setInCancelamentoLinhasAssociadas(boolean arg1) {
        this.inCancelamentoLinhasAssociadas = arg1;
    }

    public boolean getInCancelamentoLinhasAssociadas() {
        return this.inCancelamentoLinhasAssociadas;
    }

    public void setShowMatrizOferForm(ShowMatrizOfertaForm showMatrizOferForm) {
        this.showMatrizOferForm = showMatrizOferForm;
    }

    public ShowMatrizOfertaForm getShowMatrizOferForm() {
        if (this.showMatrizOferForm == null) {
            this.showMatrizOferForm = new ShowMatrizOfertaForm();
        }
        return this.showMatrizOferForm;
    }

    public void setNrLinhasSelecionadas(String[] arg1) {
        this.nrLinhasSelecionadas = arg1;
    }

    public String[] getNrLinhasSelecionadas() {
        if (this.nrLinhasSelecionadas == null) {
            this.nrLinhasSelecionadas = new String[0];
        }
        return this.nrLinhasSelecionadas;
    }

    public void setIdPalitagem(int arg1) {
        this.idPalitagem = arg1;
    }

    public int getIdPalitagem() {
        return this.idPalitagem;
    }

}