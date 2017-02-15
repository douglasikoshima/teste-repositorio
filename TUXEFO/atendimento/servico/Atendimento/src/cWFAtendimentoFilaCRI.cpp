/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Eder Jani Martins
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:22 $
 **/


#include "../include/cWFAtendimento.h"
#include "../../../commons/msgPadrao.h"

extern bool proCConsultaWFFilaCRIPorAtendimento(long _idAtendimento,int _idPessoaUsuario,XMLGen* saida);
extern bool proCConsultaWFFilaCRIPorAtendimentoQtd(long _idAtendimento,int _idPessoaUsuario,XMLGen* saida);

extern bool proCConsultaWFFilaAbertosPorLinhaCRI(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaAbertosPorLinhaCRIQtd(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

extern bool proCConsultaWFFilaFechadosPorLinhaCRI(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaFechadosPorLinhaCRIQtd(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

extern bool proCConsultaWFAvanzadaFilaCRI(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFAvanzadaFilaCRIQtd(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

extern bool proCConsultaWFFilaFechadosLinhaCRI(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaFechadosLinhaCRIQtd(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

extern bool proCConsultaWFFilaFechadosUsuarioCRI(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaFechadosUsuarioCRIQtd(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

extern bool proCConsultarBoxAdq(int idPessoaUsuario,st_AtendimentoFila* m_stFila, st_vlAtendimentoFila* m_vlFila, XMLGen* saida);

extern bool proCConsultaWFFilaUsuarioLinhaCRI(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaUsuarioLinhaCRIQtd(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

extern bool proCConsultaWFFilaLinhaCRI(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaLinhaCRIQtd(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

extern bool proCConsultaWFFilaUsuarioCRI(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaUsuarioCRIQtd(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

extern bool proCConsultaWFFilaCRI(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaCRIQtd(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

bool cWFAtendimento::consultarFilaCRI(Collection* _grupos, int idPessoaUsuario)
{

    carregaDadosConsultaFila();

    ULOG( "cWFAtendimento::consultarFilaCRI() idPessoaUsuario [%d]", idPessoaUsuario );

    m_stFila.idUsuarioGrupo = idPessoaUsuario;
    m_vlFila.idUsuarioGrupo = 1;

    ULOG("=== STATUS ===");
    ULOG("dtFechamentoInicio[%s]", m_stFila.dtFechamentoInicio);
    ULOG("nrLinha[%s]", m_stFila.nrLinha);
    ULOG("idPessoaUsuario[%d]", m_stFila.idPessoaUsuario);
    ULOG("idGrupo[%d]", m_stFila.idGrupo);
    ULOG("dtAberturaInicio[%s]", m_stFila.dtAberturaInicio);
    ULOG("dtAberturaFim[%s]", m_stFila.dtAberturaFim);
    ULOG("idAtendimento[%d]", m_stFila.idAtendimento);
    ULOG("idContato[%d]", m_stFila.idContato);
    ULOG("idEstado[%d]", m_stFila.idEstado);
    ULOG("idSubEstado[%d]", m_stFila.idSubEstado);
    ULOG("idUsuarioGrupo[%d]", m_stFila.idUsuarioGrupo);
    ULOG("=== VALORES ===");
    ULOG("dtFechamentoInicio[%d]", m_vlFila.dtFechamentoInicio);
    ULOG("nrLinha[%d]", m_vlFila.nrLinha);
    ULOG("idPessoaUsuario[%d]", m_vlFila.idPessoaUsuario);
    ULOG("idGrupo[%d]", m_vlFila.idGrupo);
    ULOG("dtAberturaInicio[%d]", m_vlFila.dtAberturaInicio);
    ULOG("dtAberturaFim[%d]", m_vlFila.dtAberturaFim);
    ULOG("idAtendimento[%d]", m_vlFila.idAtendimento);
    ULOG("idContato[%d]", m_vlFila.idContato);
    ULOG("idEstado[%d]", m_vlFila.idEstado);
    ULOG("idSubEstado[%d]", m_vlFila.idSubEstado);
    ULOG("idUsuarioGrupo[%d]", m_vlFila.idUsuarioGrupo);

    //
    // Quando o idatendimento for preenchido, realizamos a consulta ignorando os demais parâmetros
    // Alteração para atender as incidências de WR 2984 e 3038, conforme reunião realizada com a VIVO
    //
    if (m_vlFila.idAtendimento != -1)
    {
///////////////////////////////////////////////////Contator/////////////////////////////////
   		if( m_vlFila.inTotalRegistros == -1 )
        	return proCConsultaWFFilaCRIPorAtendimento(m_stFila.idAtendimento,idPessoaUsuario,saida);
        else
        	return proCConsultaWFFilaCRIPorAtendimentoQtd(m_stFila.idAtendimento,idPessoaUsuario,saida);
    }

    //
    // Quando o número da linha for preenchido, realizamos a consulta ignorando os demais parâmetros
    // considerando apenas se foi indicado se a consulta é em processos abertos ou fechados
    // Alteração para atender as incidências de WR 2984 e 3038, conforme reunião realizada com a VIVO
    //
    // Para esta consulta específica os campos de data estarão vazios
    if ( (m_vlFila.nrLinha != -1) && ( STRLENNULL(m_stFila.dtAberturaInicio)==0 ) && ( STRLENNULL(m_stFila.dtFechamentoInicio)==0 ) )
    {
        // Procura por fechados ou Abertos? 
        if(m_vlFila.inFilaAbertos != -1) 
        {
///////////////////////////////////////////////////Contator/////////////////////////////////
	   		if( m_vlFila.inTotalRegistros == -1 )
	            return proCConsultaWFFilaAbertosPorLinhaCRI(&m_stFila, &m_vlFila, saida);
	        else
	            return proCConsultaWFFilaAbertosPorLinhaCRIQtd(&m_stFila, &m_vlFila, saida);
        }
        else
        {
///////////////////////////////////////////////////Contator/////////////////////////////////
	   		if( m_vlFila.inTotalRegistros == -1 )
	            return proCConsultaWFFilaFechadosPorLinhaCRI(&m_stFila, &m_vlFila, saida);
	        else
	            return proCConsultaWFFilaFechadosPorLinhaCRIQtd(&m_stFila, &m_vlFila, saida);
        }
    }

    if (m_stFila.nrCampos != 0)
    {
///////////////////////////////////////////////////Contator/////////////////////////////////
   		if( m_vlFila.inTotalRegistros == -1 )
        	return proCConsultaWFAvanzadaFilaCRI(&m_stFila, &m_vlFila, saida);
        else
        	return proCConsultaWFAvanzadaFilaCRIQtd(&m_stFila, &m_vlFila, saida);
    }

    if (m_vlFila.dtFechamentoInicio != -1)
    {
        if (m_vlFila.nrLinha != -1)
        {
///////////////////////////////////////////////////Contator/////////////////////////////////
	   		if( m_vlFila.inTotalRegistros == -1 )
	            return proCConsultaWFFilaFechadosLinhaCRI(&m_stFila, &m_vlFila, saida);
	        else
	            return proCConsultaWFFilaFechadosLinhaCRIQtd(&m_stFila, &m_vlFila, saida);
        }
        else
        {
///////////////////////////////////////////////////Contator/////////////////////////////////
	   		if( m_vlFila.inTotalRegistros == -1 )
	            return proCConsultaWFFilaFechadosUsuarioCRI(&m_stFila, &m_vlFila, saida);
	        else
	            return proCConsultaWFFilaFechadosUsuarioCRIQtd(&m_stFila, &m_vlFila, saida);
        }
    }

    if(m_vlFila.inTipoPesquisa == 1 && m_vlFila.nrDocumento == 1)
    {
///////////////////////////////////////////////////Contator/////////////////////////////////
   		if( m_vlFila.inTotalRegistros == -1 )
        	return proCConsultarBoxAdq(idPessoaUsuario,&m_stFila, &m_vlFila, saida);
       	else
        	return proCConsultarBoxAdq(idPessoaUsuario,&m_stFila, &m_vlFila, saida);
    }

    if (m_vlFila.nrLinha != -1)
    {
        if (m_vlFila.idPessoaUsuario != -1)
        {
///////////////////////////////////////////////////Contator/////////////////////////////////
	   		if( m_vlFila.inTotalRegistros == -1 )
	            return proCConsultaWFFilaUsuarioLinhaCRI(&m_stFila, &m_vlFila, saida);
	        else
	            return proCConsultaWFFilaUsuarioLinhaCRIQtd(&m_stFila, &m_vlFila, saida);
        }

///////////////////////////////////////////////////Contator/////////////////////////////////
   		if( m_vlFila.inTotalRegistros == -1 )
        	return proCConsultaWFFilaLinhaCRI(&m_stFila, &m_vlFila, saida);
        else
        	return proCConsultaWFFilaLinhaCRIQtd(&m_stFila, &m_vlFila, saida);
    }

    if (m_vlFila.idPessoaUsuario != -1)
    {
///////////////////////////////////////////////////Contator/////////////////////////////////
   		if( m_vlFila.inTotalRegistros == -1 )
	        return proCConsultaWFFilaUsuarioCRI(&m_stFila, &m_vlFila, saida);
	    else
	        return proCConsultaWFFilaUsuarioCRIQtd(&m_stFila, &m_vlFila, saida);
    }
        
///////////////////////////////////////////////////Contator/////////////////////////////////
	if( m_vlFila.inTotalRegistros == -1 )
	    return proCConsultaWFFilaCRI(&m_stFila, &m_vlFila, saida);
	else
	    return proCConsultaWFFilaCRIQtd(&m_stFila, &m_vlFila, saida);
}

