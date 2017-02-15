package br.com.vivo.fo.ctrls.campanha.agendaContato;

import javax.ejb.Local;

@Local
public interface AgendaContatoCmpFacade {

	public br.com.vivo.fo.campanha.vo.ListaAgendamentoCampanhaVODocument.ListaAgendamentoCampanhaVO getAgendaContato(java.lang.String user, java.lang.String parametro) throws br.com.vivo.fo.exception.FacadeException;

	public void addAgendarContato(java.lang.String user, java.lang.String[] dados) throws br.com.vivo.fo.exception.FacadeException;
}
