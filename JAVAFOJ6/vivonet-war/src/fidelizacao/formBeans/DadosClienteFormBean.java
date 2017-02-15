package fidelizacao.formBeans;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.fidelizacao.vo.DetalheLinhaVODocument.DetalheLinhaVO;

public class DadosClienteFormBean extends ActionForm {

    private static final long serialVersionUID       = 4866862741765457846L;
    private String            hora                   = ConstantesCRM.SVAZIO;
    private String            idRetencao             = ConstantesCRM.SVAZIO;
    private String            nomeContato            = ConstantesCRM.SVAZIO;
    private String            telefoneContato        = ConstantesCRM.SVAZIO;
    private String            data                   = ConstantesCRM.SVAZIO;
    private String            horas                  = ConstantesCRM.SVAZIO;
    private String            minutos                = ConstantesCRM.SVAZIO;
    private String            comentario             = ConstantesCRM.SVAZIO;
    private String            idUfOperadora          = ConstantesCRM.SVAZIO;
    private String            idCliente              = ConstantesCRM.SVAZIO;
    private String            nomeCliente            = ConstantesCRM.SVAZIO;
    private String            idSegmentaco           = ConstantesCRM.SVAZIO;
    private String            segmentacao            = ConstantesCRM.SVAZIO;
    private String            idCarteirizacao        = ConstantesCRM.SVAZIO;
    private String            carteirizacao          = ConstantesCRM.SVAZIO;
    private String            idTipoCliente          = ConstantesCRM.SVAZIO;
    private String            tipoCliente            = ConstantesCRM.SVAZIO;
    private String            numeroLinha            = ConstantesCRM.SVAZIO;
    private String            idIntencaoCancelamento = ConstantesCRM.SVAZIO;
    private String            idDestinoPrevisto      = ConstantesCRM.SVAZIO;
    private String            idLinhaTelefonica      = ConstantesCRM.SVAZIO;
    private String            valorMultaContrato     = ConstantesCRM.SVAZIO;
    private String            vencimentoContrato     = ConstantesCRM.SVAZIO;
    private String            numeroContrato         = ConstantesCRM.SVAZIO;
    private DetalheLinhaVO    detalheLinha;

    /*************** Modificado por Decio Jr 06/10/2004 *****************************************************/
    /**************************** Envio de ofertas realizadas e aceitas para agendamento ********************/
    private String            saldoPontos            = ConstantesCRM.SVAZIO;
    private String[]          ofertaRealizada        = new String[0];
    private String[]          ofertaAceita           = new String[0];
    private String            idPessoaDePara         = ConstantesCRM.SVAZIO;

    public String getIdPessoaDePara() {
        return this.idPessoaDePara;
    }

    public void setIdPessoaDePara(String s) {
        this.idPessoaDePara = s;
    }

    public String[] getOfertaRealizada() {
        return this.ofertaRealizada;
    }

    public void setOfertaRealizada(String[] lista) {
        this.ofertaRealizada = lista;
    }

    public String[] getOfertaAceita() {
        return this.ofertaAceita;
    }

    public void setOfertaAceita(String[] lista) {
        this.ofertaAceita = lista;
    }

    public String getSaldoPontos() {
        return this.saldoPontos;
    }

    public void setSaldoPontos(String s) {
        this.saldoPontos = s;
    }

    /**************************** Fim Modificação **************************************************************/

    public void setDetalheLinha(DetalheLinhaVO detalheLinha) {
        this.detalheLinha = detalheLinha;
    }

    public DetalheLinhaVO getDetalheLinha() {
        return this.detalheLinha;
    }

    public void setIdDestinoPrevisto(String idDestinoPrevisto) {
        this.idDestinoPrevisto = idDestinoPrevisto;
    }

    public String getIdDestinoPrevisto() {
        return this.idDestinoPrevisto;
    }

    public void setIdIntencaoCancelamento(String idIntencaoCancelamento) {
        this.idIntencaoCancelamento = idIntencaoCancelamento;
    }

    public String getIdIntencaoCancelamento() {
        return this.idIntencaoCancelamento;
    }

    public void setValorMultaContrato(String valorMultaContrato) {
        this.valorMultaContrato = valorMultaContrato;

    }

    public String getValorMultaContrato() {
        return this.valorMultaContrato;

    }

    public void setNumeroContrato(String numeroContrato) {
        this.numeroContrato = numeroContrato;

    }

    public String getNumeroContrato() {
        return this.numeroContrato;

    }

    public void setVencimentoContrato(String vencimentoContrato) {
        this.vencimentoContrato = vencimentoContrato;
    }

    public String getVencimentoContrato() {
        return this.vencimentoContrato;
    }

    public void setIdUfOperadora(String idUfOperadora) {
        this.idUfOperadora = idUfOperadora;
    }

    public String getIdUfOperadora() {
        return this.idUfOperadora;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getIdCliente() {
        return this.idCliente;
    }

    public void setIdLinhaTelefonica(String idLinhaTelefonica) {
        this.idLinhaTelefonica = idLinhaTelefonica;
    }

    public String getIdLinhaTelefonica() {
        return this.idLinhaTelefonica;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getNomeCliente() {
        return this.nomeCliente;
    }

    public void setIdSegmentaco(String idSegmentaco) {
        this.idSegmentaco = idSegmentaco;
    }

    public String getIdSegmentaco() {
        return this.idSegmentaco;
    }

    public void setSegmentacao(String segmentacao) {
        this.segmentacao = segmentacao;
    }

    public String getSegmentacao() {
        return this.segmentacao;
    }

    public void setIdCarteirizacao(String idCarteirizacao) {
        this.idCarteirizacao = idCarteirizacao;
    }

    public String getIdCarteirizacao() {
        return this.idCarteirizacao;
    }

    public void setCarteirizacao(String carteirizacao) {
        this.carteirizacao = carteirizacao;
    }

    public String getCarteirizacao() {
        return this.carteirizacao;
    }

    public void setIdTipoCliente(String idTipoCliente) {
        this.idTipoCliente = idTipoCliente;
    }

    public String getIdTipoCliente() {
        return this.idTipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public String getTipoCliente() {
        return this.tipoCliente;
    }

    public void setNumeroLinha(String numeroLinha) {
        this.numeroLinha = numeroLinha;
    }

    public String getNumeroLinha() {
        return this.numeroLinha;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public void setMinutos(String minutos) {
        this.minutos = minutos;
    }

    public String getMinutos() {
        return this.minutos;
    }

    public void setHoras(String horas) {
        this.horas = horas;
    }

    public String getHoras() {
        return this.horas;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return this.data;
    }

    public void setTelefoneContato(String telefoneContato) {
        this.telefoneContato = telefoneContato;
    }

    public String getTelefoneContato() {
        return this.telefoneContato;
    }

    public void setNomeContato(String nomeContato) {
        this.nomeContato = nomeContato;
    }

    public String getNomeContato() {
        return this.nomeContato;
    }

    public void setIdRetencao(String idRetencao) {
        this.idRetencao = idRetencao;
    }

    public String getIdRetencao() {
        return this.idRetencao;
    }

    public void clear() {
        this.comentario = ConstantesCRM.SVAZIO;
        this.minutos = ConstantesCRM.SVAZIO;
        this.horas = ConstantesCRM.SVAZIO;
        this.hora = ConstantesCRM.SVAZIO;
        this.data = ConstantesCRM.SVAZIO;
        this.telefoneContato = ConstantesCRM.SVAZIO;
        this.nomeContato = ConstantesCRM.SVAZIO;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getHora() {
        return this.hora;
    }

    public String getComentario() {
        return this.comentario;
    }

}
