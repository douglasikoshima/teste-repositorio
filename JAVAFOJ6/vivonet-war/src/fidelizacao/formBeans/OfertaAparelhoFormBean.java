package fidelizacao.formBeans;

import org.apache.struts.action.ActionForm;
import br.com.vivo.fo.cliente.vo.EnderecoVODocument.EnderecoVO;
import br.com.vivo.fo.cliente.vo.PesquisaEnderecoVODocument.PesquisaEnderecoVO;
import br.com.vivo.fo.cliente.vo.UFVODocument.UFVO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.fidelizacao.vo.ItemListaVODocument.ItemListaVO;

public class OfertaAparelhoFormBean extends ActionForm {

    private static final long  serialVersionUID    = -4805551032424827973L;
    private PesquisaEnderecoVO pesquisaEndereco    = PesquisaEnderecoVO.Factory.newInstance();
    private String             tipoEncerramento;
    private UFVO[]             regionais           = new UFVO[0];
    private String             idRetencao;
    private String             dtRetirada;
    private String[]           ofertasReal         = new String[0];
    private String             telContato;
    private String             SAP;
    private String             idAparelhoCor;
    private String             idMeioPagamento;
    private String             przVigencia         = ConstantesCRM.SVAZIO;
    private ItemListaVO[]      listaDescontoVO;
    private String             descontoSelecionado = ConstantesCRM.SVAZIO;
    private ItemListaVO[]      listaParcelaVO;
    private String             parcelaSelecionada  = ConstantesCRM.SVAZIO;
    private String             qtdEstoque;
    private String             nmLoja;
    private String             rgContato;
    private String             nmContato;
    private String             estado;
    private String             cidade;
    private String             bairro;
    private String             complemento;
    private String             cep;
    private String             numero;
    private String             rua;
    private boolean            loja                = false;
    private boolean            delivery            = false;
    private String             ofertaSelecionada;
    private String             vlDesconto;
    private String             vlParcela;
    private String             percDesconto;
    private String             nroParcela;
    private String             meioPagamento;
    private boolean            inExcecao           = false;
    private String             cor;
    private String             marca;
    private String             modelo;
    private String             idAparelho;
    private String             preco;
    private String             index;
    private String             dsMaterial;
    private boolean            inAlteracaoEndereco;
    private int                idEntrega           = -1;
    private String             idUFSelecionado     = ConstantesCRM.SVAZIO;
    private EnderecoVO         enderecoVO          = EnderecoVO.Factory.newInstance();
    private String             sgOfertaAceita      = ConstantesCRM.SVAZIO;
    private String             inTipoPessoa        = ConstantesCRM.SVAZIO;
    private String             idSegmentacaoLinha  = ConstantesCRM.SVAZIO;
    private boolean            semSimCard          = false;
    
    

    public boolean isSemSimCard() {
		return semSimCard;
	}

	public void setSemSimCard(boolean semSimCard) {
		this.semSimCard = semSimCard;
	}

	public void setInTipoPessoa(String valor) {
        this.inTipoPessoa = SAP;
    }

    public String getInTipoPessoa() {
        return this.inTipoPessoa;
    }

    public void setIdSegmentacaoLinha(String idSegmentacaoLinha) {
        this.idSegmentacaoLinha = idSegmentacaoLinha;
    }

    public String getIdSegmentacaoLinha() {
        return this.idSegmentacaoLinha;
    }

    public void setSgOfertaAceita(String valor) {
        this.sgOfertaAceita = valor;
    }

    public String getSgOfertaAceita() {
        return this.sgOfertaAceita;
    }

    public String getIdMeioPagamento() {
        return this.idMeioPagamento;
    }

    public void setIdMeioPagamento(String idMeioPagamento) {
        this.idMeioPagamento = idMeioPagamento;
    }

    public String getSAP() {
        return this.SAP;
    }

    public void setSAP(String SAP) {
        this.SAP = SAP;
    }

    public String getIdAparelhoCor() {
        return this.idAparelhoCor;
    }

    public void setIdAparelhoCor(String id) {
        this.idAparelhoCor = id;
    }

    /***************** Modificacao Decio JR 03/10/2004 ***********************************/
    /******** Criado por Decio JR 28/09/2004 ********************************************/
    /**************************** Lista de Descontos *************************************/

    public ItemListaVO[] getListaDescontoVO() {
        if (this.listaDescontoVO == null) {
            this.listaDescontoVO = new ItemListaVO[0];
        }
        return this.listaDescontoVO;
    }

    public void setListaDescontoVO(ItemListaVO[] lista) {
        this.listaDescontoVO = lista;
    }

    public String getDescontoSelecionado() {
        return this.descontoSelecionado;
    }

    public void setDescontoSelecionado(String s) {
        this.descontoSelecionado = s;
    }

    /****************************** Listas de Parcelas ***********************************/

    public ItemListaVO[] getListaParcelaVO() {
        if (this.listaParcelaVO == null) {
            this.listaParcelaVO = new ItemListaVO[0];
        }
        return this.listaParcelaVO;
    }

    public void setListaParcelaVO(ItemListaVO[] lista) {
        this.listaParcelaVO = lista;
    }

    public String getParcelaSelecionada() {
        return this.parcelaSelecionada;
    }

    public void setParcelaSelecionada(String s) {
        this.parcelaSelecionada = s;
    }

    public EnderecoVO getEnderecoVO() {
        return this.enderecoVO;
    }

    public void setEnderecoVO(EnderecoVO endereco) {
        if ((pesquisaEndereco.getUFVOArray() == null) || (pesquisaEndereco.getUFVOArray().length == 0)) {
            pesquisaEndereco.addNewUFVO();
        }
        this.enderecoVO = endereco;
    }

    public String getIdUFSelecionado() {
        return this.idUFSelecionado;
    }

    public void setIdUFSelecionado(String s) {
        this.idUFSelecionado = s;
    }

    public void loadEndereco(EnderecoVO endereco) {
        if (endereco != null) {
            this.rua = endereco.getNmTituloLogradouro() + " " + endereco.getNmLogradouro();
            this.bairro = endereco.getNmBairro();
            this.cidade = endereco.getNmMunicipio();
            this.cep = endereco.getNrCEP();
            this.numero = ConstantesCRM.SVAZIO;
            this.complemento = ConstantesCRM.SVAZIO;
            if (endereco.getUFVO() != null) {
                this.idUFSelecionado = endereco.getUFVO().getSgUF();
            }
        }
    }

    public int getIdEntrega() {
        return this.idEntrega;
    }

    public void setIdEntrega(int i) {
        this.idEntrega = i;
    }

    public void setDsMaterial(String dsMaterial) {
        this.dsMaterial = dsMaterial;
    }

    public String getDsMaterial() {
        return this.dsMaterial;
    }

    public void setInAlteracaoEndereco(boolean inAlteracaoEndereco) {
        this.inAlteracaoEndereco = inAlteracaoEndereco;
    }

    public boolean getInAlteracaoEndereco() {
        return this.inAlteracaoEndereco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public String getPreco() {
        return this.preco;
    }

    public UFVO[] getRegionais() {
        return this.regionais;
    }

    public void setRegionais(UFVO[] regionais) {
        this.regionais = regionais;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getIndex() {
        return this.index;
    }

    public void setIdAparelho(String idAparelho) {
        this.idAparelho = idAparelho;
    }

    public String getIdAparelho() {
        return this.idAparelho;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getModelo() {
        return this.modelo;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getMarca() {
        return this.marca;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getCor() {
        return this.cor;
    }

    public void setInExcecao(boolean inExcecao) {
        this.inExcecao = inExcecao;
    }

    public boolean getInExcecao() {
        return this.inExcecao;
    }

    public void setMeioPagamento(String meioPagamento) {
        this.meioPagamento = meioPagamento;
    }

    public String getMeioPagamento() {
        return this.meioPagamento;
    }

    public String[] getOfertasReal() {
        return ofertasReal;
    }

    public void setOfertasReal(String[] ofertas) {
        this.ofertasReal = ofertas;
    }

    public void setNroParcela(String nroParcela) {
        this.nroParcela = nroParcela;
    }

    public String getNroParcela() {
        return this.nroParcela;
    }

    public void setPercDesconto(String percDesconto) {
        this.percDesconto = percDesconto;
    }

    public String getPercDesconto() {
        return this.percDesconto;
    }

    public void setVlParcela(String vlParcela) {
        this.vlParcela = vlParcela;
    }

    public String getVlParcela() {
        return this.vlParcela;
    }

    public void setVlDesconto(String vlDesconto) {
        this.vlDesconto = vlDesconto;
    }

    public String getVlDesconto() {
        return this.vlDesconto;
    }

    public void setOfertaSelecionada(String ofertaSelecionada) {
        this.ofertaSelecionada = ofertaSelecionada;
    }

    public String getOfertaSelecionada() {
        return this.ofertaSelecionada;
    }

    public void setDelivery(boolean delivery) {
        this.delivery = delivery;
    }

    public boolean getDelivery() {
        return this.delivery;
    }

    public void setLoja(boolean inLoja) {
        this.loja = inLoja;
    }

    public boolean getLoja() {
        return this.loja;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getRua() {
        return this.rua;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNumero() {
        return this.numero;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCep() {
        return this.cep;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getComplemento() {
        return this.complemento;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getBairro() {
        return this.bairro;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCidade() {
        return this.cidade;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setNmContato(String nmContato) {
        this.nmContato = nmContato;
    }

    public String getNmContato() {
        return this.nmContato;
    }

    public void setRgContato(String rgContato) {
        this.rgContato = rgContato;
    }

    public String getRgContato() {
        return this.rgContato;
    }

    public void setNmLoja(String nmLoja) {
        this.nmLoja = nmLoja;
    }

    public String getNmLoja() {
        return this.nmLoja;
    }

    public void setQtdEstoque(String qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public String getQtdEstoque() {
        return this.qtdEstoque;
    }

    public void setTelContato(String telContato) {
        this.telContato = telContato;
    }

    public String getTelContato() {
        return this.telContato;
    }

    public void setDtRetirada(String dtRetirada) {
        this.dtRetirada = dtRetirada;
    }

    public String getDtRetirada() {
        return this.dtRetirada;
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

    public void setPesquisaEndereco(PesquisaEnderecoVO pesquisaEndereco) {
        this.pesquisaEndereco = pesquisaEndereco;
    }

    public PesquisaEnderecoVO getPesquisaEndereco() {
        return this.pesquisaEndereco;
    }

    public void setPrzVigencia(String valor) {
        this.przVigencia = valor;
    }

    public String getPrzVigencia() {
        return this.przVigencia;
    }
}
