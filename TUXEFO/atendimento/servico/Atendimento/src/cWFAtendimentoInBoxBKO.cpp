/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:24 $
 **/


#include "../include/cWFAtendimento.h"
#include "../../AtdInBoxCri/include/stWFAtdInBox.h"
#include "../../../commons/msgPadrao.h"

extern bool proCConsultaWFInboxUsuario(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFInboxUsuarioLinha(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFInboxFechadosUsuario(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFInboxFechadosUsuarioLinha(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

bool cWFAtendimento::consultarBox(int idPessoaUsuario)
{
	carregaDadosConsultaFila();

	m_stFila.idPessoaUsuario = idPessoaUsuario;
	m_vlFila.idPessoaUsuario = 1;


	if (m_vlFila.dtFechamentoInicio != -1)
	{
		if (m_vlFila.nrLinha != -1)
			return proCConsultaWFInboxFechadosUsuarioLinha(&m_stFila, &m_vlFila, saida);
		else
			return proCConsultaWFInboxFechadosUsuario(&m_stFila, &m_vlFila, saida);
	}
	if (m_vlFila.nrLinha != -1)
	{
		return proCConsultaWFInboxUsuarioLinha(&m_stFila, &m_vlFila, saida);
	}
	else
		return proCConsultaWFInboxUsuario(&m_stFila, &m_vlFila, saida);
}

