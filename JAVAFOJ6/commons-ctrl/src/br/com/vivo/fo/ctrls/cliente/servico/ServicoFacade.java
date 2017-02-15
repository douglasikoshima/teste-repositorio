package br.com.vivo.fo.ctrls.cliente.servico;

import javax.ejb.Local;

@Local
public interface ServicoFacade {

    public br.com.vivo.fo.cliente.vo.ReducaoVelocidadeVODocument.ReducaoVelocidadeVO getConsultaVelocidade(java.lang.String user, java.lang.String nrLinha) throws java.lang.Exception;
}
