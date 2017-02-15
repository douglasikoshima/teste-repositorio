/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.2 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:31 $
 **/ 


#include "../include/cWFAtendimentoNivel.h"

extern long proCIncluirWFAtendimentoNivel(st_AtendimentoNivel* dados, st_vlAtendimentoNivel* status);
extern bool proCAlterarWFAtendimentoNivel(st_AtendimentoNivel* dados, st_vlAtendimentoNivel* status, XMLGen* saida);
extern bool proCExcluirWFAtendimentoNivel(st_AtendimentoNivel* dados, st_vlAtendimentoNivel* status, XMLGen* saida);
extern bool proCConcluirWFAtendimentoNivel(st_AtendimentoNivel* dados, st_vlAtendimentoNivel* status, XMLGen* saida);
extern bool proCConsultaWFAtendimentoNivel(st_AtendimentoNivel* dados, st_vlAtendimentoNivel* status, char* order, XMLGen* saida);
extern bool proCObtemWFNivelAtendimento(long sIdAtendimento, int sIdFase, XMLGen* saida);
extern bool proCObtemWFHistoricoNivelAtendimento(long sIdAtendimento,int sIdFase,int sIdFaseAtual,XMLGen* saida);
extern bool proCObtemWFHistoricoNivelAtendimentoMC1(long sIdAtendimento,XMLGen* saida);
extern bool proCObtemWFHistoricoNivelAtendimentoEx(long sIdAtendimento,int sIdFase,int sIdFaseAtual,XMLGen* saida);
extern bool proCObtemWFNivelGrAt(long sIdAtendimento, int sNrNivel, XMLGen* saida, int *contadorLinhas=0);
extern bool proCObtemWFNivelContato(int sIdContato, int sIdFase, XMLGen* saida);

/**
    Construtor da classe concebido para sobrepor o construtor original que recebia como parametros
    um DOMNode e um XMLGen, esse novo construtor recebe duas structs e um XMLGen, essas duas structs
    tem a função de fornecer os valores a serem manipulados e a indicação dos campos que possuem valor.
    O XMLGen continua existindo na declaração pois os métodos de consulta devolverão os dados diretamente
    ao XMLGen, assim não sendo necessário criar um segundo processo para a conversão do resultado diferente.
    @autor Renato Teixeira
    @since 24/10/2004
*/
cWFAtendimentoNivel::cWFAtendimentoNivel(st_AtendimentoNivel* dados, st_vlAtendimentoNivel* status, XMLGen*xml_g)
{
    memcpy(&m_stDados,dados,sizeof(m_stDados));
    memcpy(&m_vlDados,status,sizeof(m_vlDados));

    entrada = 0;
    saida   = xml_g;
}

/**
    Esse construtor foi desenvolvido levando em conta as necessidades do SSKlunk10.
    @autor Renato Teixeira
    @since 24/10/2004
*/
cWFAtendimentoNivel::cWFAtendimentoNivel(DOMNode*dnode, XMLGen*xml_g)
{
    entrada = dnode;
    saida   = xml_g;

    carregaDados();
}

long cWFAtendimentoNivel::incluir()
{
    long idAtendimentoNivel =  proCIncluirWFAtendimentoNivel(&m_stDados, &m_vlDados);
    
    saida->createTag("WFAtendimentoNivelVO");
        saida->addItem("idAtendimentoNivel", idAtendimentoNivel);
    saida->closeTag();

    return idAtendimentoNivel;
}

int cWFAtendimentoNivel::alterar()
{
    if (m_vlDados.idAtendimentoNivel == -1) 
    {
        return -1;
    }

    return proCAlterarWFAtendimentoNivel(&m_stDados, &m_vlDados, saida);
}

int cWFAtendimentoNivel::excluir()
{
    if (m_vlDados.idAtendimentoNivel == -1) 
    {
        return -1;
    }

    return proCExcluirWFAtendimentoNivel(&m_stDados, &m_vlDados, saida);
}

bool cWFAtendimentoNivel::concluir()
{
    if (m_vlDados.idAtendimento == -1) 
    {
        return false;
    }

    return proCConcluirWFAtendimentoNivel(&m_stDados, &m_vlDados, saida);
}

bool cWFAtendimentoNivel::consultar()
{
    char order[256];

    order[0] = 0;

    if ( entrada )
    {
        char* p;
        if (p = tx.walkTree( entrada, "order", 0 ), p)
        {
            sprintf(order, "%.*s",sizeof(order)-1,p);
            XMLString::release(&p);
        }
    }

    return proCConsultaWFAtendimentoNivel(&m_stDados, &m_vlDados, order, saida);
}

bool cWFAtendimentoNivel::ObterHistoricoAtendNivelMC1( long idAtendimento,XMLGen* saida )
{
    ULOG_START("ObterHistoricoAtendNivelMC1()");
    bool retorno = proCObtemWFHistoricoNivelAtendimentoMC1( idAtendimento,saida );
    ULOG_END("ObterHistoricoAtendNivelMC1()");
    return retorno;
}

bool cWFAtendimentoNivel::ObterHistoricoAtendNivel( long idAtendimento,int idFase,int idFaseAtual,XMLGen* saida )
{
    return proCObtemWFHistoricoNivelAtendimento( idAtendimento,idFase,idFaseAtual,saida );
}

bool cWFAtendimentoNivel::ObterHistoricoAtendNivelEx( long idAtendimento,int idFase,int idFaseAtual,XMLGen* saida )
{
    return proCObtemWFHistoricoNivelAtendimentoEx( idAtendimento,idFase,idFaseAtual,saida );
}

bool cWFAtendimentoNivel::ObtemDetalheAtend( long idAtendimento, int idFase, XMLGen* saida )
{
    return proCObtemWFNivelAtendimento( idAtendimento,idFase,saida );
}

bool cWFAtendimentoNivel::ObtemDetalheAtend()
{
    if (m_vlDados.idAtendimento == -1) { return false; }
    if (m_vlDados.idFase == -1) { return false; }

    return proCObtemWFNivelAtendimento( m_stDados.idAtendimento
                                      , m_stDados.idFase
                                      , saida );
}

bool cWFAtendimentoNivel::ObtemNivGrAt()
{
	int nrNivel = -1;

	if (m_vlDados.idAtendimento == -1) { return false; }
    
    return proCObtemWFNivelGrAt( m_stDados.idAtendimento, nrNivel, saida );
}

bool cWFAtendimentoNivel::ObtemNivGrAt(st_AtendimentoNivel* dados,XMLGen* saida)
{
	int nrNivel = -1;

	return proCObtemWFNivelGrAt( dados->idAtendimento, nrNivel, saida );
}

bool cWFAtendimentoNivel::ObtemNivGrAt(long idAtendimento, XMLGen* saida,int *contadorLinhas)
{
    if (idAtendimento == -1) { return false; }
    
    return proCObtemWFNivelGrAt( idAtendimento, -1, saida, contadorLinhas );
}

bool cWFAtendimentoNivel::ObtemNivGrAt(long idAtendimento, int nrNivel, XMLGen* saida,int *contadorLinhas)
{
    if (idAtendimento == -1) { return false; }
    
    return proCObtemWFNivelGrAt( idAtendimento, nrNivel, saida, contadorLinhas );
}

bool cWFAtendimentoNivel::ObtemNivContato(st_AtendimentoNivel* dados,XMLGen* saida)
{
    return proCObtemWFNivelContato( dados->idContato,dados->idFase,saida );
}

bool cWFAtendimentoNivel::ObtemNivContato()
{
    if (m_vlDados.idContato == -1) { return false; }
    if (m_vlDados.idFase == -1) { return false; }
    
    return proCObtemWFNivelContato( m_stDados.idContato
                                  , m_stDados.idFase
                                  , saida );
}

bool cWFAtendimentoNivel::ObtemNivContato(int idContato, int idFase, XMLGen* saida)
{
    if (idContato == -1) { return false; }
    if (idFase == -1) { return false; }
    
    return proCObtemWFNivelContato( idContato
                                  , idFase
                                  , saida );
}

void cWFAtendimentoNivel::carregaDados()
{
    // Inicializa as estruturas de dados para armazenar os valores.
    memset(&m_stDados,0,sizeof(st_AtendimentoNivel));
    memset(&m_vlDados,-1,sizeof(st_vlAtendimentoNivel));

    if ( !entrada )
    {
        return;
    }

    char* p;

    if (p = tx.walkTree( entrada, "idAtendimentoNivel", 0 ), p ) 
    {
        m_stDados.idAtendimentoNivel = atol( p );
        m_vlDados.idAtendimentoNivel = 1;
        XMLString::release(&p);
    }

    if (p = tx.walkTree( entrada, "idAtendimento", 0 ), p ) 
    {
        m_stDados.idAtendimento = atol(p);
        m_vlDados.idAtendimento = 1;
        XMLString::release(&p);
    }

    if (p = tx.walkTree( entrada, "idGrupo", 0 ), p ) 
    {
        m_stDados.idGrupo = atoi( p );
        m_vlDados.idGrupo = 1;
        XMLString::release(&p);
    }

    if (p = tx.walkTree( entrada, "idFase", 0 ), p ) 
    {
        m_stDados.idFase = atoi( p );
        m_vlDados.idFase = 1;
        XMLString::release(&p);
    }

    if (p = tx.walkTree( entrada, "idAtividade", 0 ), p ) 
    {
        m_stDados.idAtividade = atoi( p );
        m_vlDados.idAtividade = 1;
        XMLString::release(&p);
    }

    if (p = tx.walkTree( entrada, "idContato", 0 ), p ) 
    {
        m_stDados.idContato = atoi( p );
        m_vlDados.idContato = 1;
        XMLString::release(&p);
    }

    if (p = tx.walkTree( entrada, "idSequencia", 0 ), p ) 
    {
        m_stDados.idSequencia = atoi( p );
        m_vlDados.idSequencia = 1;
        XMLString::release(&p);
    }

    if (p = tx.walkTree( entrada, "nrNivel", 0 ), p ) 
    {
        m_stDados.nrNivel = atoi( p );
        m_vlDados.nrNivel = 1;
        XMLString::release(&p);
    }

    if (p = tx.walkTree( entrada, "dtNivel", 0 ), p ) 
    {
        strcpy(m_stDados.dtNivel, p );
        m_vlDados.dtNivel = 1;
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

