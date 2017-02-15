package fidelizacao.formBeans;

import org.apache.struts.action.ActionForm;
import br.com.vivo.fo.fidelizacao.vo.DetalheHistoricoRetencaoVODocument.DetalheHistoricoRetencaoVO.CaractOfertasAceitas;
import br.com.vivo.fo.fidelizacao.vo.OfertaRealizadaVODocument;
import br.com.vivo.fo.fidelizacao.vo.OfertaRealizadaVODocument.OfertaRealizadaVO;

public class ShowDetalheHistoricoFormBean extends ActionForm {

    private static final long         serialVersionUID = 1564494869029743416L;

    private OfertaRealizadaVO[]       ofertaRecusadaArray;
    private OfertaRealizadaVODocument ofertaRecusada;
    private String                    observacao;
    private String                    ofertaAceita;
    private String                    intencao;
    private String                    destino;

    private CaractOfertasAceitas      caractOfertasAceitas;
    private int                       caractLength;

    public ShowDetalheHistoricoFormBean() {
        ofertaRecusadaArray = new OfertaRealizadaVO[0];
    }

    public int getCaractLength() {
        return caractLength;
    }

    public void setCaractLength(int caractLength) {
        this.caractLength = caractLength;
    }

    public CaractOfertasAceitas getCaractOfertasAceitas() {
        if (this.caractOfertasAceitas == null) {
            this.caractOfertasAceitas = CaractOfertasAceitas.Factory.newInstance();
        }
        return this.caractOfertasAceitas;
    }

    public void setCaractOfertasAceitas(CaractOfertasAceitas caractOfertasAceitas) {
        this.caractOfertasAceitas = caractOfertasAceitas;
    }

    public void setOfertaAceita(String ofertaAceita) {
        this.ofertaAceita = ofertaAceita;
    }

    public String getOfertaAceita() {
        return this.ofertaAceita;
    }

    public void setIntencao(String intencao) {
        this.intencao = intencao;
    }

    public String getIntencao() {
        return this.intencao;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getDestino() {
        return this.destino;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getObservacao() {
        return this.observacao;
    }

    public void setOfertaRecusada(OfertaRealizadaVODocument ofertaRecusada) {
        this.ofertaRecusada = ofertaRecusada;
    }

    public OfertaRealizadaVODocument getOfertaRecusada() {
        return this.ofertaRecusada;
    }

    public void setOfertaRecusadaArray(OfertaRealizadaVO[] ofertaRecusadaArray) {
        this.ofertaRecusadaArray = ofertaRecusadaArray;
    }

    public OfertaRealizadaVO[] getOfertaRecusadaArray() {

        return this.ofertaRecusadaArray;
    }

}
