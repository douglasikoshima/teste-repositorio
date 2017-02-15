package br.com.indrasistemas.vivoservices.sittel.assinante.to;

import java.util.List;
import br.com.indrasistemas.framework.service.BaseTransferObject;

@SuppressWarnings("rawtypes")
public class AssinanteTO extends BaseTransferObject {

    private static final long   serialVersionUID = -8004869675628372648L;

    private String              codigoOrdem;
    private String              codigoRequisicao;
    private String              numeroSolicitacao;
    private String              tipoServico;
    private String              direcao;

    private Boolean             dadosCadastraisInterlocutor;
    //private ListaInvestigadosTO listaInvestigado;
    private List                investigados;

    public String getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(String tipoServico) {
        this.tipoServico = tipoServico;
    }

    public String getDirecao() {
        return direcao;
    }

    public void setDirecao(String direcao) {
        this.direcao = direcao;
    }

    public Boolean getDadosCadastraisInterlocutor() {
        return dadosCadastraisInterlocutor;
    }

    public void setDadosCadastraisInterlocutor(Boolean dadosCadastraisInterlocutor) {
        this.dadosCadastraisInterlocutor = dadosCadastraisInterlocutor;
    }

    @Override
    public String toString() {
        return "ConsultaAssinanteTO [codigoOrdem=" + codigoOrdem + "]";
    }

    public String getCodigoOrdem() {
        return codigoOrdem;
    }

    public void setCodigoOrdem(String codigoOrdem) {
        this.codigoOrdem = codigoOrdem;
    }

    public String getCodigoRequisicao() {
        return codigoRequisicao;
    }

    public void setCodigoRequisicao(String codigoRequisicao) {
        this.codigoRequisicao = codigoRequisicao;
    }

    public String getNumeroSolicitacao() {
        return numeroSolicitacao;
    }

    public void setNumeroSolicitacao(String numeroSolicitacao) {
        this.numeroSolicitacao = numeroSolicitacao;
    }

    /*public ListaInvestigadosTO getListaInvestigado() {
        return listaInvestigado;
    }

    public void setListaInvestigado(ListaInvestigadosTO listaInvestigado) {
        this.listaInvestigado = listaInvestigado;
    }*/

    public List getInvestigados() {
        return investigados;
    }

    public void setInvestigados(List investigados) {
        this.investigados = investigados;
    }

}
