package br.com.vivo.fo.ctrls.admsistemas.acaoIncentivo;

import javax.ejb.Local;
import br.com.vivo.fo.ctrls.admsistemas.acaoIncentivo.db.Acao;
import br.com.vivo.fo.exception.FacadeException;

@Local
public interface AcaoIncentivoFacade {

    public Acao[] getTodasAcoes() throws FacadeException;

    public Acao buscaAcaoIncentivo(String id) throws FacadeException;

    public void excluirAcaoIncentivo(String id) throws FacadeException;

    public void manterAcaoIncentivo(String id, String descricao, String dataInicial, String dataFinal, String tipo, String msgInicial, String msgFinal, String inAtivo, String user) throws FacadeException;

    public Acao[] getPesquisaAcoes(String query) throws FacadeException;
}
