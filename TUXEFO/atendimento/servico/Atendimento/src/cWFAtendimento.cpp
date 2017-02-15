/**
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.4.6.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/05 17:12:50 $
 **/


#include "../include/cWFAtendimento.h"
#include "../../AtdInBoxCri/include/stWFAtdInBox.h"
#include "../../../commons/msgPadrao.h"

extern long  proCIncluirWFAtendimento(st_Atendimento* dados, st_vlAtendimento* status, XMLDPR *xmlDpr);
extern bool proCAlterarWFAtendimento(st_Atendimento* dados, st_vlAtendimento* status, XMLDPR *xmlDpr);
extern bool proCExcluirWFAtendimento(st_Atendimento* dados, st_vlAtendimento* status, XMLDPR *xmlDpr);

/******************************************************************************************
*
* INICIO DOS construtores e destrutores
*
********************************************************************/
cWFAtendimento::cWFAtendimento()
{
    entrada=0;
    saida=0;

    memset(&m_stDados,0,sizeof(m_stDados));
    memset(&m_vlDados,-1,sizeof(m_vlDados));
    
    memset(&m_stFila,0,sizeof(m_stFila));
    memset(&m_vlFila,-1,sizeof(m_vlFila));

    memset(&dadosAtendimento,0,sizeof(dadosAtendimento));
    
    inMigracao = false;
}

cWFAtendimento::~cWFAtendimento()
{
    // Warning: Este ponteiro não foi alocado pela classe e portanto não é de
    //          responsabilidade da classe desalocá-lo. Como consequencia o VC
    //          estava gerando uma exception em ambiente de depuração pois a
    //          área do ponteiro já havia sido liberada pelo sistema operacional
    //
    // delete[] m_stFila.gruposUsuario;
}

/**
    Construtor da classe concebido para sobrepor o construtor original que recebia como parametros
    um DOMNode e um XMLGen, esse novo construtor recebe duas structs e um XMLGen, essas duas structs
    tem a função de fornecer os valores a serem manipulados e a indicação dos campos que possuem valor.
    O XMLGen continua existindo na declaração pois os métodos de consulta devolverão os dados diretamente
    ao XMLGen, assim não sendo necessário criar um segundo processo para a conversão do resultado diferente.
    @autor Renato Teixeira
    @since 21/10/2004
*/
cWFAtendimento::cWFAtendimento(st_Atendimento* dados, st_vlAtendimento* status, XMLGen*xml_g)
{
    memcpy(&m_stDados, dados, sizeof(m_stDados));
    memcpy(&m_vlDados, status, sizeof(m_vlDados));
    
    memset(&m_stFila,0,sizeof(m_stFila));
    memset(&m_vlFila,-1,sizeof(m_vlFila));

    entrada = 0;
    saida   = xml_g;
    inMigracao = false;
}

/**
    Esse construtor foi desenvolvido levando em conta as necessidades do SSKlunk10.
    @autor Renato Teixeira
    @since 21/10/2004
*/
cWFAtendimento::cWFAtendimento(DOMNode*dnode, XMLGen*xml_g)
{
    entrada = dnode;
    saida   = xml_g;
    
    memset(&m_stFila,0,sizeof(m_stFila));
    memset(&m_vlFila,-1,sizeof(m_vlFila));

    inMigracao = false;
    
    carregaDados();
}
/******************************************************************************************
*
* FIM DOS construtores e destrutores
*
********************************************************************/


/******************************************************************************************
*
* INICIO DOS METODOS básicos (incluir, editar, excluir)
*
********************************************************************/

// nao é para usar este método, criado apenas monitorar chamadas pelo
// SSKlunk. Este método gera exceção!
long cWFAtendimento::incluir()
{
    throw new TuxException("04E9999","Uso de cWFAtendimento::incluir() indevido!");
}

long cWFAtendimento::incluir(XMLDPR *xmlDpr)
{
    long idAtendimento = proCIncluirWFAtendimento(&m_stDados, &m_vlDados,xmlDpr);

    if ( saida )
    {
        saida->createTag( "AtendimentoVO" );
            saida->addItem( "idAtendimento" , idAtendimento );
        saida->closeTag();
    }

    return idAtendimento;
}

long cWFAtendimento::incluir(st_Atendimento* dados, st_vlAtendimento* status,XMLDPR *xmlDpr)
{
    return proCIncluirWFAtendimento(dados,status,xmlDpr);
}

// nao é para usar este método, criado apenas monitorar chamadas pelo
// SSKlunk. Este método gera exceção!
long cWFAtendimento::alterar()
{
    throw new TuxException("04E9999","Uso de cWFAtendimento::incluir() indevido!");
}

long cWFAtendimento::alterar(XMLDPR *xmlDpr)
{
    if (m_vlDados.idAtendimento == -1) 
    {
        return -1;
    }

    return proCAlterarWFAtendimento(&m_stDados, &m_vlDados, xmlDpr);
}

// nao é para usar este método, criado apenas monitorar chamadas pelo
// SSKlunk. Este método gera exceção!
long cWFAtendimento::excluir()
{
    throw new TuxException("04E9999","Uso de cWFAtendimento::excluir() indevido!");
}

long cWFAtendimento::excluir(XMLDPR *xmlDpr)
{
    if (m_vlDados.idAtendimento == -1) 
    {
        return -1;
    }

    return proCExcluirWFAtendimento(&m_stDados, &m_vlDados, xmlDpr);
}
/******************************************************************************************
*
* FIM DOS METODOS básicos (incluir, editar, excluir)
*
********************************************************************/


/******************************************************************************************
*
* INICIO DOS METODOS ObtemXXXX que retornam dados especificos das estruturas internas
*
********************************************************************/
char *cWFAtendimento::ObterDtAbertura()
{
    return dadosAtendimento.dtAbertura;
}

char *cWFAtendimento::ObterDtPrazoFinalAnatel()
{
    return dadosAtendimento.dtPrazoFinalAnatel;
}

char *cWFAtendimento::ObterDtPrazoFinalInterno()
{
    return dadosAtendimento.dtPrazoFinalInterno;
}

char *cWFAtendimento::ObterDtUltimaAlteracao()
{
    return dadosAtendimento.dtUltimaAlteracao;
}

int cWFAtendimento::ObterIdCanal()
{
    return dadosAtendimento.idCanal;
}

int cWFAtendimento::ObterIdContato()
{
    return dadosAtendimento.idContato;
}

// Remodelagem de ATENDIMENTO.ATENDIMENTO - idDiscador não existe mais
//int cWFAtendimento::ObterIdDiscador()
//{
//    return dadosAtendimento.idDiscador;
//}

int cWFAtendimento::ObterIdFase()
{
    return dadosAtendimento.idFase;
}

int cWFAtendimento::ObterIdGrupoAbertura()
{
    return dadosAtendimento.idGrupoAbertura;
}

long cWFAtendimento::ObterIdPessoaUsuarioAbertura()
{
    return dadosAtendimento.idPessoaUsuarioAbertura;
}

int cWFAtendimento::ObterIdProcedencia()
{
    return dadosAtendimento.idProcedencia;
}

int cWFAtendimento::ObterIdSegmentacao()
{
    return dadosAtendimento.idSegmentacao;
}

int cWFAtendimento::ObterIdTipoCarteira()
{
    return dadosAtendimento.idTipoCarteira;
}

int cWFAtendimento::ObterIdTipoRetornoContato()
{
    return dadosAtendimento.idTipoRetornoContato;
}

int cWFAtendimento::ObterIdUsuarioAlteracao()
{
    return dadosAtendimento.idUsuarioAlteracao;
}

int cWFAtendimento::ObterInAlarme()
{
    return dadosAtendimento.inAlarme;
}

int cWFAtendimento::ObterNrNivel()
{
    return dadosAtendimento.nrNivel;
}

int cWFAtendimento::ObterQtHorasPrazoAtendimento()
{
    return dadosAtendimento.qtHorasPrazoAtendimento;
}

int cWFAtendimento::ObterQtInsistencia()
{
    return dadosAtendimento.qtInsistencia;
}

char *cWFAtendimento::ObterVlPesoAtendimento()
{
    return dadosAtendimento.vlPesoAtendimento;
}

char *cWFAtendimento::ObterPsqNrProtocolo()
{
    return m_stDados.PsqNrProtocolo;
}


bool cWFAtendimento::ObterMigracao()
{
    return inMigracao;
}


/******************************************************************************************
*
* FIM DOS METODOS ObtemXXXX que retornam dados especificos das estruturas internas
*
********************************************************************/

/******************************************************************************************
* INICIO
* Esse método é usado para carregar os dados usados na classe a partir de um DOMNode.
* Os dados são carregados em duas estruturas, sendo a primeira, a que contém o valor obtido e a segunda
* indicado que aquele atributo da primeira estrutrua possui valor.
* O método é chamado pelo construtor que recebe como parametro um DOMNode e um XMLGen.
* @autor Renato Teixeira
* @since 21/10/2004
********************************************************************/
void cWFAtendimento::carregaDados()
{
    // Inicializa as estruturas de dados para armazenar os valores.
    memset(&m_stDados,0,sizeof(m_stDados));
    memset(&m_vlDados,-1,sizeof(m_vlDados));

    char* p;


    m_stDados.PsqNrProtocolo[0] = 0x0;
    if (p = tx.walkTree( entrada, "cdOperacao", 0 ), p) 
    {
        
        strcpy( m_stDados.operacao, p );
        
        ULOG( "*** Identificando operacao [%s]", (char*)m_stDados.operacao);
        
        m_vlDados.operacao = 1;
        XMLString::release(&p);

        if ( strcmp((char*)m_stDados.operacao,"processosPorProtocolo") == 0 )
        {
            if (p = tx.walkTree( entrada, "nrProtocolo", 0 ), p)
            {
                strcpy( m_stDados.PsqNrProtocolo, p );
                m_vlDados.PsqNrProtocolo = 1;
                XMLString::release(&p);
            }
        }

        if ( strcmp((char*)m_stDados.operacao,"processosPorProtocoloHistoricoMG") == 0 )
        {
            ULOG( "*** Historico de Migracao" );
            if (p = tx.walkTree( entrada, "nrProtocolo", 0 ), p)
            {
                strcpy( m_stDados.PsqNrProtocolo, p );
                m_vlDados.PsqNrProtocolo = 1;
                XMLString::release(&p);
            }
            inMigracao = true;
        }

        //else if ( strcmp((char*)m_stDados.operacao,"historicoMG") == 0 )
        if ( strcmp((char*)m_stDados.operacao,"historicoMG") == 0 )
        {
            ULOG( "(2)*** Historico de Migracao" );
            inMigracao = true;
        }
    }

    if (p = tx.walkTree( entrada, "idAtendimento", 0 ), p) 
    {
        //m_stDados.idAtendimento = atoi(p);
		m_stDados.idAtendimento = atol(p);

        m_vlDados.idAtendimento = 1;
        XMLString::release(&p);
    }

    if (p = tx.walkTree( entrada, "dtAbertura", 0 ), p) 
    {
        sprintf(m_stDados.dtAbertura, "%.*s",sizeof(m_stDados.dtAbertura)-1,p );
        m_vlDados.dtAbertura = 1;
        XMLString::release(&p);
    }

    if (p = tx.walkTree( entrada, "idContato", 0 ), p) 
    {
        m_stDados.idContato = atoi(p);
        m_vlDados.idContato = 1;
        XMLString::release(&p);
    }

    if (p = tx.walkTree( entrada, "dtPrazoFinalInterno", 0 ), p) 
    {
        sprintf(m_stDados.dtPrazoFinalInterno, "%.*s",sizeof(m_stDados.dtPrazoFinalInterno)-1,p );
        m_vlDados.dtPrazoFinalInterno = 1;
        XMLString::release(&p);
    }

    if (p = tx.walkTree( entrada, "qtInsistencia", 0 ), p) 
    {
        m_stDados.qtInsistencia = atoi(p);
        m_vlDados.qtInsistencia = 1;
        XMLString::release(&p);
    }

    if (p = tx.walkTree( entrada, "inAlarme", 0 ), p) 
    {
        m_stDados.inAlarme = atoi(p);
        m_vlDados.inAlarme = 1;
        XMLString::release(&p);
    }

    if (p = tx.walkTree( entrada, "idCanal", 0 ), p) 
    {
        m_stDados.idCanal = atoi(p);
        m_vlDados.idCanal = 1;
        XMLString::release(&p);
    }

    if (p = tx.walkTree( entrada, "idProcedencia", 0 ), p) 
    {
        m_stDados.idProcedencia = atoi(p);
        m_vlDados.idProcedencia = 1;
        XMLString::release(&p);
    }

    if (p = tx.walkTree( entrada, "idTipoCarteira", 0 ), p) 
    {
        m_stDados.idTipoCarteira = atoi(p);
        m_vlDados.idTipoCarteira = 1;
        XMLString::release(&p);
    }

    if (p = tx.walkTree( entrada, "idSegmentacao", 0 ), p) 
    {
        m_stDados.idSegmentacao = atoi(p);
        m_vlDados.idSegmentacao = 1;
        XMLString::release(&p);
    }

    // Remodelagem de ATENDIMENTO.ATENDIMENTO - idDiscador não existe mais
    // if (p = tx.walkTree( entrada, "idDiscador", 0 ), p) 
    // {
    //      m_stDados.idDiscador = atoi(p);
    //      m_vlDados.idDiscador = 1;
    //      XMLString::release(&p);
    // }
 
    if (p = tx.walkTree( entrada, "idPessoaUsuarioAbertura", 0 ), p) 
    {
        m_stDados.idPessoaUsuarioAbertura = atol(p);
        m_vlDados.idPessoaUsuarioAbertura = 1;
        XMLString::release(&p);
    }

    if (p = tx.walkTree( entrada, "idGrupoAbertura", 0 ), p) 
    {
        m_stDados.idGrupoAbertura = atoi(p);
        m_vlDados.idGrupoAbertura = 1;
        XMLString::release(&p);
    }

    if (p = tx.walkTree( entrada, "dtPrazoFinalAnatel", 0 ), p) 
    {
        sprintf(m_stDados.dtPrazoFinalAnatel,"%.*s",sizeof(m_stDados.dtPrazoFinalAnatel)-1,p);
        m_vlDados.dtPrazoFinalAnatel = 1;
        XMLString::release(&p);
    }

    if (p = tx.walkTree( entrada, "nrNivel", 0 ), p) 
    {
        m_stDados.nrNivel = atoi(p);
        m_vlDados.nrNivel = 1;
        XMLString::release(&p);
    }

    if (p = tx.walkTree( entrada, "idFase", 0 ), p) 
    {
        m_stDados.idFase = atoi(p);
        m_vlDados.idFase = 1;
        XMLString::release(&p);
    }

    if (p = tx.walkTree( entrada, "qtHorasPrazoAtendimento", 0 ), p) 
    {
        m_stDados.qtHorasPrazoAtendimento = atoi(p);
        m_vlDados.qtHorasPrazoAtendimento = 1;
        XMLString::release(&p);
    }

    if (p = tx.walkTree( entrada, "vlPesoAtendimento", 0 ), p) 
    {
        SAFE_STRNCPY(m_stDados.vlPesoAtendimento,p);
        //m_stDados.vlPesoAtendimento = atoi(p);
        m_vlDados.vlPesoAtendimento = 1;
        XMLString::release(&p);
    }

    if (p = tx.walkTree( entrada, "idTipoRetornoContato", 0 ), p) 
    {
        m_stDados.idTipoRetornoContato = atoi(p);
        m_vlDados.idTipoRetornoContato = 1;
        XMLString::release(&p);
    }

    if (p = tx.walkTree( entrada, "idSequencia", 0 ), p) 
    {
        m_stDados.idSequencia = atoi(p);
        m_vlDados.idSequencia = 1;
        XMLString::release(&p);
    }

    if (p = tx.walkTree( entrada, "idUsuarioAlteracao", 0 ), p) 
    {
        m_stDados.idUsuarioAlteracao = atoi(p);
        m_vlDados.idUsuarioAlteracao = 1;
        XMLString::release(&p);
    }

    if (p = tx.walkTree( entrada, "dtUltimaAlteracao", 0 ), p) 
    {
        sprintf( m_stDados.dtUltimaAlteracao,"%.*s",sizeof(m_stDados.dtUltimaAlteracao)-1, p );
        m_vlDados.dtUltimaAlteracao = 1;
        XMLString::release(&p);
    }
    
    ULOG( ">>> Nro. Protocolo [%s]",(char*)m_stDados.PsqNrProtocolo );
    
}


void cWFAtendimento::carregaDadosConsultaFila()
{
    ULOG_START( "cWFAtendimento::carregaDadosConsultaFila()" );

    memset(&m_stFila,0,sizeof(m_stFila));
    memset(&m_vlFila,-1,sizeof(m_vlFila));

    char* p;
    DOMNode* dom;

    m_stDados.PsqNrProtocolo[0] = 0x0;
    ULOG( ">>> Verificando historico..." );
    if (p = tx.walkTree( entrada, "cdOperacao", 0 ), p) 
    {
        
        strcpy( m_stDados.operacao, p );
        
        ULOG( "*** Identificando operacao [%s]", (char*)m_stDados.operacao);
        
        m_vlDados.operacao = 1;
        XMLString::release(&p);

        if ( strcmp((char*)m_stDados.operacao,"processosPorProtocolo") == 0 )
        {
            if (p = tx.walkTree( entrada, "nrProtocolo", 0 ), p)
            {
                strcpy( m_stDados.PsqNrProtocolo, p );
                m_vlDados.PsqNrProtocolo = 1;
                XMLString::release(&p);
            }
        }

        if ( strcmp((char*)m_stDados.operacao,"processosPorProtocoloHistoricoMG") == 0 )
        {
            ULOG( "*** Historico de Migracao" );
            if (p = tx.walkTree( entrada, "nrProtocolo", 0 ), p)
            {
                strcpy( m_stDados.PsqNrProtocolo, p );
                m_vlDados.PsqNrProtocolo = 1;
                XMLString::release(&p);
            }
            inMigracao = true;
        }

        //else if ( strcmp((char*)m_stDados.operacao,"historicoMG") == 0 )
        if ( strcmp((char*)m_stDados.operacao,"historicoMG") == 0 )
        {
            ULOG( "(2)*** Historico de Migracao" );
            inMigracao = true;
        }
    }

    if (p = tx.walkTree( entrada, "idGrupo", 0 ), p) 
    {
        if( STRLENNULL( p ) > 0 )
        {
            m_stFila.idGrupo = atoi(p);
            m_vlFila.idGrupo = 1;
            ULOG("idGrupo [%i]", m_stFila.idGrupo);
        }
        XMLString::release(&p);
    }

    if (p = tx.walkTree( entrada, "idContato", 0 ), p) 
    {
        if( STRLENNULL( p ) > 0 )
        {
            m_stFila.idContato = atoi(p);
            m_vlFila.idContato = 1;
        }
        XMLString::release(&p);
        ULOG("idContato [%i]", m_stFila.idContato);
    }

    if (p = tx.walkTree( entrada, "idEstado", 0 ), p) 
    {
        if( STRLENNULL( p ) > 0 )
        {
            m_stFila.idEstado = atoi(p);
            m_vlFila.idEstado = 1;
        }
        XMLString::release(&p);
        ULOG("idEstado [%i]", m_stFila.idEstado);
    }

    if (p = tx.walkTree( entrada, "idSubEstado", 0 ), p) 
    {
        if( STRLENNULL( p ) > 0 )
        {
            m_stFila.idSubEstado = atoi(p);
            m_vlFila.idSubEstado = 1;
        }
        XMLString::release(&p);
        ULOG("idSubEstado [%i]", m_stFila.idSubEstado);
    }

    if (p = tx.walkTree( entrada, "idPessoaUsuario", 0 ), p) 
    {
        if( STRLENNULL( p ) > 0 )
        {
            m_stFila.idPessoaUsuario = atoi(p);
            m_vlFila.idPessoaUsuario = 1;
        }
        XMLString::release(&p);
        ULOG("idPessoaUsuario [%i]", m_stFila.idPessoaUsuario);
    }

    if (p = tx.walkTree( entrada, "dtAberturaInicio", 0 ), p) 
    {
        if( STRLENNULL( p ) > 0 )
        {
            sprintf(m_stFila.dtAberturaInicio,"%.*s",sizeof(m_stFila.dtAberturaInicio)-1, p );
            m_vlFila.dtAberturaInicio= 1;
        }
        XMLString::release(&p);
        ULOG("dtAberturaInicio [%s]", m_stFila.dtAberturaInicio);
    }

    if (p = tx.walkTree( entrada, "dtAberturaFim", 0 ), p) 
    {
        if( STRLENNULL( p ) > 0 )
        {
            sprintf(m_stFila.dtAberturaFim,"%.*s",sizeof(m_stFila.dtAberturaFim)-1, p );
            m_vlFila.dtAberturaFim= 1;
        }       
        XMLString::release(&p);
        ULOG("dtAberturaFim [%s]", m_stFila.dtAberturaFim);
    }

    if (p = tx.walkTree( entrada, "dtFechamentoInicio", 0 ), p) 
    {
        if( STRLENNULL( p ) > 0 )
        {
            sprintf(m_stFila.dtFechamentoInicio,"%.*s",sizeof(m_stFila.dtFechamentoInicio)-1, p );
            m_vlFila.dtFechamentoInicio= 1;
        }
        XMLString::release(&p);
        ULOG("dtFechamentoInicio [%s]", m_stFila.dtFechamentoInicio);
    }

    if (p = tx.walkTree( entrada, "dtFechamentoFim", 0 ), p) 
    {
        if( STRLENNULL( p ) > 0 )
        {
            sprintf(m_stFila.dtFechamentoFim,"%.*s",sizeof(m_stFila.dtFechamentoFim)-1, p );
            m_vlFila.dtFechamentoFim= 1;
        }
        XMLString::release(&p);
        ULOG("dtFechamentoFim [%s]", m_stFila.dtFechamentoFim);
    }

    if (p = tx.walkTree( entrada, "pesquisa", 0 ), p) 
    {
        sprintf(m_stFila.pesquisa,"%.*s",sizeof(m_stFila.pesquisa)-1, p );
        m_vlFila.pesquisa= 1;
        XMLString::release(&p);
        ULOG("pesquisa [%s]", m_stFila.pesquisa);
    }

    if (p = tx.walkTree( entrada, "tpPesquisa", 0 ), p)
    {
        if( STRLENNULL( p ) > 0 )
        {
            m_stFila.tpPesquisa = atoi(p);
            m_vlFila.tpPesquisa = 1;
        }
        XMLString::release(&p);
        ULOG("tpPesquisa [%i]", m_stFila.tpPesquisa);
    }

    if (p = tx.walkTree( entrada, "inTipoPesquisa", 0 ), p) 
    {
        if( STRLENNULL( p ) > 0 )
        {
            sprintf(m_stFila.sgTipoPesquisa,"%.*s",sizeof(m_stFila.sgTipoPesquisa)-1,p);
            m_vlFila.sgTipoPesquisa = 1;
            m_vlFila.inTipoPesquisa = 1;
        }
        XMLString::release(&p);
        ULOG("tpPesquisa [%i]", m_stFila.tpPesquisa);
    }

    if (p = tx.walkTree( entrada, "documento", 0 ), p) 
    {
        if( STRLENNULL( p ) > 0 )
        {
            sprintf(m_stFila.nrDocumento,"%.*s",sizeof(m_stFila.nrDocumento)-1,p);
            m_vlFila.nrDocumento = 1;
        }
        XMLString::release(&p);
        ULOG("documento [%i]", m_stFila.nrDocumento);
    }

    if (p = tx.walkTree( entrada, "idPessoaDePara", 0 ), p) 
    {
        if( STRLENNULL( p ) > 0 )
        {
            m_stFila.idPessoaDePara = atoi(p);
            m_vlFila.idPessoaDePara = 1;
        }
        XMLString::release(&p);
        ULOG("idPessoaDePara [%i]", m_stFila.idPessoaDePara);
    }

    if (p = tx.walkTree( entrada, "tpRelacionamento", 0 ), p) 
    {
        m_stFila.tpRelacionamento = atoi(p);
        m_vlFila.tpRelacionamento = 1;
        XMLString::release(&p);
        ULOG("tpRelacionamento [%i]", m_stFila.tpRelacionamento);
    }

    if (p = tx.walkTree( entrada, "idPessoaLinhaHistorico", 0 ), p) 
    {
        if( STRLENNULL( p ) > 0 )
        {
            m_stFila.idPessoaLinhaHistorico = atol(p);
            m_vlFila.idPessoaLinhaHistorico = 1;
        }
        XMLString::release(&p);
        ULOG("idPessoaLinhaHistorico [%ld]", m_stFila.idPessoaLinhaHistorico);
    }

    if (p = tx.walkTree( entrada, "textoContato", 0 ), p) 
    {
        sprintf(m_stFila.textoContato,"%.*s",sizeof(m_stFila.textoContato)-1, p );
        m_vlFila.textoContato = 1;
        XMLString::release(&p);
        ULOG("textoContato [%s]", m_stFila.textoContato);
    }

    if (p = tx.walkTree( entrada, "idAtendimento", 0 ), p) 
    {
        if( STRLENNULL( p ) > 0 )
        {
            //m_stFila.idAtendimento = atoi(p);
			m_stFila.idAtendimento = atol(p);
            m_vlFila.idAtendimento = 1;
        }
        XMLString::release(&p);
        ULOG("idAtendimento [%lu]", m_stFila.idAtendimento);
    }

    if (p = tx.walkTree( entrada, "nrProtocolo", 0 ), p) 
    {
        if( STRLENNULL( p ) > 0 )
        {
            SAFE_STRNCPY(m_stFila.idAtendimentoProtocolo,p);
            m_vlFila.idAtendimentoProtocolo = 1;
        }
        XMLString::release(&p);
        ULOG("idAtendimentoProtocolo [%s]", m_stFila.idAtendimentoProtocolo);
    }

    if (p = tx.walkTree( entrada, "nrLinha", 0 ), p) 
    {
        if( STRLENNULL( p ) > 0 )
        {
            sprintf(m_stFila.nrLinha,"%.*s",sizeof(m_stFila.nrLinha)-1, p );
            m_vlFila.nrLinha = 1;
            ULOG("nrLinha [%s]", m_stFila.nrLinha);
        }
        XMLString::release(&p);
    }

    // Flag que indica se a pesquisa é em atendimentos abertos
    // Utilizado pela pequisa por número de linha relativo a incidências de WR 2984 e 3038
    if (p = tx.walkTree( entrada, "inFilaAbertos", 0 ), p) 
    {
        if( STRLENNULL( p ) > 0 )
        {
            m_stFila.inFilaAbertos = atoi(p);
            m_vlFila.inFilaAbertos = 1;
        }
        else
        {
            m_stFila.inFilaAbertos = 0;
            m_vlFila.inFilaAbertos = -1;
        }
        XMLString::release(&p);
        ULOG("inFilaAbertos [%i]", m_stFila.inFilaAbertos);
    }


    if (dom = tx.walkDOM( entrada, "WFPesquisaAvancadaVO", 0 ), dom) 
    {
        m_stFila.pesquisaDinamica = dom;
        m_vlFila.pesquisaDinamica = 1;
        ULOG("WFPesquisaAvancadaVO");
        if (p = tx.walkTree( dom, "nrCampos", 0 ), p) 
            {
            m_stFila.nrCampos = atoi(p);
            m_vlFila.nrCampos = 1;
            XMLString::release(&p);
            }
    }

    if (p = tx.walkTree( entrada, "dtSuspeitoInicio", 0 ), p) 
    {
        if( STRLENNULL( p ) > 0 )
        {
            sprintf(m_stFila.dtSuspeitoInicio,"%.*s",sizeof(m_stFila.dtSuspeitoInicio)-1, p );
            m_vlFila.dtSuspeitoInicio = 1;
            ULOG("dtSuspeitoInicio [%s]", m_stFila.dtSuspeitoInicio);
        }
        XMLString::release(&p);
    }

    if (p = tx.walkTree( entrada, "dtSuspeitoFim", 0 ), p) 
    {
        if( STRLENNULL( p ) > 0 )
        {
            sprintf(m_stFila.dtSuspeitoFim,"%.*s",sizeof(m_stFila.dtSuspeitoFim)-1, p );
            m_vlFila.dtSuspeitoFim = 1;
            ULOG("dtSuspeitoFim [%s]", m_stFila.dtSuspeitoFim);
        }
        XMLString::release(&p);
    }

    if (p = tx.walkTree( entrada, "nmLoginUsuario", 0 ), p) 
    {
        if( STRLENNULL( p ) > 0 )
        {
            sprintf(m_stFila.nmLoginUsuario,"%.*s",sizeof(m_stFila.nmLoginUsuario)-1, p );
            m_vlFila.nmLoginUsuario = 1;
            ULOG("nmLoginUsuario [%s]", m_stFila.nmLoginUsuario);
        }
        XMLString::release(&p);
    }

    if (p = tx.walkTree( entrada, "dsAndamentoObservacao", 0 ), p) 
    {
        sprintf(m_stFila.dsAndamentoObservacao,"%.*s",sizeof(m_stFila.dsAndamentoObservacao)-1, p );
        m_vlFila.dsAndamentoObservacao = 1;
        XMLString::release(&p);
        ULOG("dsAndamentoObservacao [%s]", m_stFila.dsAndamentoObservacao);
    }

    if (dom = tx.walkDOM( entrada, "GruposDoUsuario", 0 ), dom) 
    {
        m_stFila.gruposUsuario = new Collection();
        char* grupo;
        char* x;
        int contador = 0;

        while (grupo = tx.walkTree(dom, "idGrupoFiltro", contador++) )
        {
            if ( x = new char[strlen(grupo)+1],x )
            {
                strcpy(x, grupo);
                m_stFila.gruposUsuario->AddItem((void*) x);

                ULOG( "<idGrupoFiltro>=%s",grupo );
            }
            XMLString::release(&grupo);
        }

        m_vlFila.gruposUsuario = 1;
        ULOG("gruposUsuario - registro = [%i]", contador);
    }

    if (p = tx.walkTree( entrada, "inPrimeiraChamada", 0 ), p) 
    {
        if( STRLENNULL( p ) > 0 )
        {
            m_stFila.inPrimeiraChamada = atoi(p);
            m_vlFila.inPrimeiraChamada = 1;
        }
        XMLString::release(&p);
        ULOG("inPrimeiraChamada [%i]", m_stFila.inPrimeiraChamada);
    }

    if (p = tx.walkTree( entrada, "tabPausa", 0 ), p) 
    {
        if( STRLENNULL( p ) > 0 )
        {
            m_stFila.tbPausa = atoi(p);
            m_vlFila.tbPausa = 1;
        }
        XMLString::release(&p);
        ULOG("tbPausa[%i]", m_stFila.tbPausa);
    }

    if (p = tx.walkTree( entrada, "idRegional", 0 ), p) 
    {
        if( STRLENNULL( p ) > 0 )
        {
            m_stFila.idUfOperadora = atoi(p);
            m_vlFila.idUfOperadora = 1;
        }
        XMLString::release(&p);
        ULOG("idUfOperadora[%i]", m_stFila.idUfOperadora);
    }

    if (p = tx.walkTree( entrada, "inTotalRegistros", 0 ), p) 
    {
        if( STRLENNULL( p ) > 0 )
        {
        	if( strcmp( p, "1" ) == 0 )
        	{
	            m_stFila.inTotalRegistros = atoi(p);
	            m_vlFila.inTotalRegistros = 1;
	        }
        }
        XMLString::release(&p);
        ULOG("inTotalRegistros[%i]", m_stFila.inTotalRegistros);
    }

    if (p = tx.walkTree( entrada, "nrProtocoloPortabilidade", 0 ), p) 
    {
        if( STRLENNULL( p ) > 0 )
        {
	        SAFE_STRNCPY(m_stFila.nrProtocoloPortabilidade,p);
	        m_vlFila.nrProtocoloPortabilidade = 1;
        }
        XMLString::release(&p);
        ULOG("nrProtocoloPortabilidade[%s]", m_stFila.nrProtocoloPortabilidade);
    }

    if (p = tx.walkTree( entrada, "nrConta", 0 ), p) 
    {
        if( STRLENNULL( p ) > 0 )
        {
	        SAFE_STRNCPY(m_stFila.nrConta,p);
	        m_vlFila.nrConta = 1;
        }
        XMLString::release(&p);
        ULOG("nrConta[%s]", m_stFila.nrConta);
    }

    if (p = tx.walkTree( entrada, "idAlerta", 0 ), p) 
    {
        if( STRLENNULL( p ) > 0 )
        {
            m_stFila.idAlerta = atoi(p);
            m_vlFila.idAlerta = 1;
        }
        XMLString::release(&p);
        ULOG("idAlerta[%i]", m_stFila.idAlerta);
    }

    if (p = tx.walkTree( entrada, "idTipoCarteira", 0 ), p) 
    {
        if( STRLENNULL( p ) > 0 )
        {
            m_stFila.idTipoCarteira = atoi(p);
            m_vlFila.idTipoCarteira = 1;
        }
        XMLString::release(&p);
        ULOG("idTipoCarteira[%i]", m_stFila.idTipoCarteira);
    }

    if (p = tx.walkTree( entrada, "idSegmentacao", 0 ), p) 
    {
        if( STRLENNULL( p ) > 0 )
        {
            m_stFila.idSegmentacao = atoi(p);
            m_vlFila.idSegmentacao = 1;
        }
        XMLString::release(&p);
        ULOG("idSegmentacao[%i]", m_stFila.idSegmentacao);
    }

    if (p = tx.walkTree( entrada, "idClassificacaoTipoLinhaVO", 0 ), p) 
    {
        if( STRLENNULL( p ) > 0 )
        {
            SAFE_STRNCPY(m_stFila.idClassificacaoTipoLinha,p);
            m_vlFila.idClassificacaoTipoLinha = 1;
        }
        XMLString::release(&p);
        ULOG("idClassificacaoTipoLinha[%s]", m_stFila.idClassificacaoTipoLinha);
    }

    if (p = tx.walkTree( entrada, "dsStatusProtocolo", 0 ), p) 
    {
        if( STRLENNULL( p ) > 0 )
        {
            SAFE_STRNCPY(m_stFila.dsStatusProtocolo,p);
            m_vlFila.dsStatusProtocolo = 1;
        }
        XMLString::release(&p);
        ULOG("dsStatusProtocolo[%s]", m_stFila.dsStatusProtocolo);
    }

    if (p = tx.walkTree( entrada, "inPesquisaFullMC", 0 ), p) 
    {
        if( STRLENNULL( p ) > 0 )
        {
            m_stFila.inPesquisaFullMC = atoi(p);
            m_vlFila.inPesquisaFullMC = 1;
        }
        XMLString::release(&p);
        ULOG("inPesquisaFullMC[%i]", m_stFila.inPesquisaFullMC);
    }

    ULOG( ">>> Nro. Protocolo [%s]",(char*)m_stDados.PsqNrProtocolo );

    ULOG_END( "cWFAtendimento::carregaDadosConsultaFila()" );
}
/******************************************************************************************
* FIM
********************************************************************/
