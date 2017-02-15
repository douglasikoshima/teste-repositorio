package br.com.indrasistemas.vivoservices.atendimento.ordemvenda.webservice.to;

import br.com.indrasistemas.vivoservices.webservice.to.RespostaWSTO;

public class manterOrdemVendaResponse extends RespostaWSTO {

    private static final long       serialVersionUID        = -1347032197801232386L;

    // private Long nrProtocolo;
    // private String idAtendimento;
    //private String                  cdRetorno;
    //private String                  dsRetorno;

    private OutManterOrdemVenda outManterOrdemVenda = new OutManterOrdemVenda();

    public manterOrdemVendaResponse() {
    }

    public OutManterOrdemVenda getOutManterOrdemVenda() {
        return outManterOrdemVenda;
    }

    public void setOutManterOrdemVenda(OutManterOrdemVenda outManterOrdemVenda) {
        this.outManterOrdemVenda = outManterOrdemVenda;
    }

}