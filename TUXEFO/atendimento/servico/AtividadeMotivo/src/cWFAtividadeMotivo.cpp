/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:29 $
 **/ 

#include "../include/cWFAtividadeMotivo.h"
#include "../../../commons/msgPadrao.h"

extern bool proCIncluirAtividadeMotivo(st_AtividadeMotivo* dados, st_vlAtividadeMotivo* status, XMLGen* saida);
extern bool proCAlterarAtividadeMotivo(st_AtividadeMotivo* dados, st_vlAtividadeMotivo* status, XMLGen* saida);
extern bool proCExcluirAtividadeMotivo(st_AtividadeMotivo* dados, st_vlAtividadeMotivo* status, XMLGen* saida);
extern bool proCObterAtividadeMotivo(int _idAtividade,XMLGen* saida);
extern bool proCObterAtividadeMotivoFase(int _idAtividade,int _idFase,XMLGen* saida);

cWFAtividadeMotivo::cWFAtividadeMotivo()
{
	entrada = NULL;
	saida = NULL;
}

cWFAtividadeMotivo::cWFAtividadeMotivo(DOMNode* dnode, XMLGen* xml_g)
{
	entrada = dnode;
	saida = xml_g;
}

cWFAtividadeMotivo::cWFAtividadeMotivo(st_AtividadeMotivo* dados, st_vlAtividadeMotivo* status, XMLGen* xml_g)
{
	entrada = 0;
	saida   = xml_g;

    memcpy(&m_stDados,dados,sizeof(m_stDados));
    memcpy(&m_vlDados,status,sizeof(m_vlDados));
}

bool cWFAtividadeMotivo::incluir()
{
    ULOG_START( "cWFAtividadeMotivo::incluir" );
	
    bool retorno = proCIncluirAtividadeMotivo(&m_stDados, &m_vlDados, saida);

    ULOG_END( "cWFAtividadeMotivo::incluir" );

    return retorno;

}

bool cWFAtividadeMotivo::alterar()
{
    ULOG_START( "cWFAtividadeMotivo::alterar" );

    bool retorno = false;

	if ( 1 == m_vlDados.idAtividadeMotivo ) 
	{
	    retorno = proCAlterarAtividadeMotivo(&m_stDados, &m_vlDados, saida);
	}
    else
    {
        ULOG( "nao alterou" );
    }

    ULOG_END( "cWFAtividadeMotivo::alterar" );

    return retorno;
}

bool cWFAtividadeMotivo::excluir()
{
    ULOG_START( "cWFAtividadeMotivo::excluir()" );

    bool retorno = false;

	if ( 1 == m_vlDados.idMotivo ) 
	{
	    retorno = proCExcluirAtividadeMotivo(&m_stDados, &m_vlDados, saida);
	}
    else
    {
        ULOG( "nao alterou" );
    }

    ULOG_END( "cWFAtividadeMotivo::excluir()" );

    return retorno;
}

bool cWFAtividadeMotivo::ObterAtividadeMotivo(int _idAtividade,XMLGen* saida)
{
    return  proCObterAtividadeMotivo(_idAtividade,saida);
}
bool cWFAtividadeMotivo::ObterAtividadeMotivo(int _idAtividade,int _idFase ,XMLGen* saida)
{
    return  proCObterAtividadeMotivoFase(_idAtividade,_idFase,saida);
}