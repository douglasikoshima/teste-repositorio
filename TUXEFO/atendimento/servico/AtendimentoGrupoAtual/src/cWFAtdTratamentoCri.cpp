/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.2.6.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/06 12:36:43 $
 **/ 
 

#include "../include/cWFAtdTratamentoCri.h"

extern long  proCIncluirWFAtendimentoTratamentoCri(st_AtdTratamentoCri* dados, st_vlAtdTratamentoCri* status);
extern long  proCIncluirWFAtendimentoCri(st_AtdTratamentoCri* dados, st_vlAtdTratamentoCri* status);
extern long proCAlterarWFAtendimentoGrupoAtualCri(st_AtdTratamentoCri* dados, st_vlAtdTratamentoCri* status);
extern long proCAlterarWFAtendimentoGrupoCri(st_AtdTratamentoCri* dados, st_vlAtdTratamentoCri* status);
extern int proCAlterarWFGrupoCri(st_AtdTratamentoCri* dados, st_vlAtdTratamentoCri* status);
extern long proCAlterarWFAtendimentoCri(st_AtdTratamentoCri* dados, st_vlAtdTratamentoCri* status);
extern unsigned long proCAlterarWFTratamentoCri(st_AtdTratamentoCri* dados, st_vlAtdTratamentoCri* status);
extern int proCAlterarWFUsuarioDevolucaoCri(st_AtdTratamentoCri* dados, st_vlAtdTratamentoCri* status);
extern int proCAlterarWFGrupoDevolucaoCri(st_AtdTratamentoCri* dados, st_vlAtdTratamentoCri* status);
extern bool proCObtemWFGrupoCri(st_AtdTratamentoCri* dados, st_vlAtdTratamentoCri* status, XMLGen* saida);
extern bool proCObtemWFTratamentoCri(st_AtdTratamentoCri* dados, st_vlAtdTratamentoCri* status, XMLGen* saida);

/**
	Construtor da classe concebido para sobrepor o construtor original que recebia como parametros
	um DOMNode e um XMLGen, esse novo construtor recebe duas structs e um XMLGen, essas duas structs
	tem a função de fornecer os valores a serem manipulados e a indicação dos campos que possuem valor.
	O XMLGen continua existindo na declaração pois os métodos de consulta devolverão os dados diretamente
	ao XMLGen, assim não sendo necessário criar um segundo processo para a conversão do resultado diferente.
	@autor Renato Teixeira
	@since 25/10/2004
*/
cWFAtdTratamentoCri::cWFAtdTratamentoCri(st_AtdTratamentoCri* dados, st_vlAtdTratamentoCri* status, XMLGen*xml_g)
{
    memcpy(&m_stDados,dados,sizeof(m_stDados));
    memcpy(&m_vlDados,status,sizeof(m_vlDados));

    entrada = 0;
    saida   = xml_g;
}

/**
	Esse construtor foi desenvolvido levando em conta as necessidades do SSKlunk10.
	@autor Renato Teixeira
	@since 25/10/2004
*/
cWFAtdTratamentoCri::cWFAtdTratamentoCri(DOMNode*dnode, XMLGen*xml_g)
{
	entrada = dnode;
	saida   = xml_g;

	carregaDados();
}

long cWFAtdTratamentoCri::incluirTratamentoCri()
{
	return proCIncluirWFAtendimentoTratamentoCri(&m_stDados, &m_vlDados);

}

long cWFAtdTratamentoCri::incluirAtendimentoCri()
{
	return proCIncluirWFAtendimentoCri(&m_stDados, &m_vlDados);

}

long cWFAtdTratamentoCri::alterarAtendimentoGrupoAtualCri()
{
	return proCAlterarWFAtendimentoGrupoAtualCri(&m_stDados, &m_vlDados);

}

long cWFAtdTratamentoCri::alterarAtendimentoGrupoCri()
{
	return proCAlterarWFAtendimentoGrupoCri(&m_stDados, &m_vlDados);

}

int cWFAtdTratamentoCri::alterarGrupoCri()
{
	return proCAlterarWFGrupoCri(&m_stDados, &m_vlDados);

}

long cWFAtdTratamentoCri::alterarAtendimentoCri()
{
	return proCAlterarWFAtendimentoCri(&m_stDados, &m_vlDados);

}

long cWFAtdTratamentoCri::alterarTratamentoCri()
{
	return proCAlterarWFTratamentoCri(&m_stDados, &m_vlDados);

}

long cWFAtdTratamentoCri::alterarUsuarioDevolucaoCri()
{
	return proCAlterarWFUsuarioDevolucaoCri(&m_stDados, &m_vlDados);

}

long cWFAtdTratamentoCri::alterarGrupoDevolucaoCri()
{
	return proCAlterarWFGrupoDevolucaoCri(&m_stDados, &m_vlDados);

}

bool cWFAtdTratamentoCri::getGrupoCri()
{
	if (m_vlDados.idAtendimento == -1)
		return false;

	return proCObtemWFGrupoCri(&m_stDados, &m_vlDados, saida );
}

bool cWFAtdTratamentoCri::getTratamentoCri()
{
	if (m_vlDados.idAtendimento == -1)
		return false;

	return proCObtemWFTratamentoCri(&m_stDados, &m_vlDados, saida );
}

void cWFAtdTratamentoCri::carregaDados()
{
	// Inicializa as estruturas de dados para armazenar os valores.
	memset(&m_stDados,0,sizeof(st_AtdTratamentoCri));
	memset(&m_vlDados,-1,sizeof(st_vlAtdTratamentoCri));

    if ( !entrada )
    {
        return;
    }

	char* p;

	if (p = tx.walkTree( entrada, "idAtendimento", 0 ), p ) 
	{
		m_stDados.idAtendimento = atol( p );
		m_vlDados.idAtendimento = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "idGrupo", 0 ), p ) 
	{
		m_stDados.idGrupo = atoi( p );
		m_vlDados.idGrupo = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "idPessoaUsuario", 0 ), p ) 
	{
		m_stDados.idPessoaUsuario = atoi( p );
		m_vlDados.idPessoaUsuario = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "dtAbertura", 0 ), p ) 
	{
		strcpy(m_stDados.dtAbertura, p );
		m_vlDados.dtAbertura = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "idPessoaLinhaHistorico", 0 ), p ) 
	{
		m_stDados.idPessoaLinhaHistorico = atol( p );
		m_vlDados.idPessoaLinhaHistorico = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "idUsuarioAlteracao", 0 ), p ) 
	{
		m_stDados.idUsuarioAlteracao = atoi( p );
		m_vlDados.idUsuarioAlteracao = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "dtUltimaAlteracao", 0 ), p ) 
	{
		strcpy(m_stDados.dtUltimaAlteracao, p );
		m_vlDados.dtUltimaAlteracao = 1;
		XMLString::release(&p);
	}
}


int cWFAtdTratamentoCri::getGrupo()
{
  return ( m_stDados.idGrupo ); 
}

int cWFAtdTratamentoCri::getPessoaLinhaHistorico()
{
  return ( m_stDados.idPessoaLinhaHistorico ); 
}
