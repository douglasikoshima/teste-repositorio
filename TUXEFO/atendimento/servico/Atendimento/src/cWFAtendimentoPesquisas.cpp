/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Eder Jani Martins
 * @version $Revision: 1.1.2.5.6.4 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/05 17:24:25 $
 **/

#include "../include/cWFAtendimento.h"
#include "../../AtdInBoxCri/include/stWFAtdInBox.h"
#include "../../../commons/msgPadrao.h"

//Pesquisas de para obter dados específicos de objetos
bool proCObtemWFDetalheAtendimentoEx_Migracao( long _idAtendimento, DetalheAtendimento *_pDadosSaida );
bool proCConsultaWFAtendimentoFilaLinha_Migracao(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
bool proCConsultaWFAtendimentoFilaLinhaTI_Migracao( st_AtendimentoFila * dados, st_vlAtendimentoFila * status, XMLGen * saida );
bool proCConsultaWFAtendimentoFilaLinha_Protocolo(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
bool proCConsultaWFAtendimentoFilaLinhaTI_Protocolos(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCObtemWFAtendimento(int idContato, int idPessoaDePara, int idLinha, XMLGen *saida,long *_idAtendimento);
extern bool proCObtemWFReaberturaAtendimento( long idAtendimento,XMLGen *saida,DetReabertura *detReabertura=0);
extern bool proCObtemWFContasCliente( int idPessoaDePara , int sIdConta, int sIdTipoLinha, int sIdUfOperadora, XMLGen *saida );
extern bool proCObtemWFContasCliente( int idPessoaDePara , XMLGen *saida );

extern bool proCObtemWFDetalheAtendimento(long _idAtendimento,XMLGen *saida,int *_idAgrEstadoTProc=0);
extern bool proCObtemWFDetalheAtendimento( long _idAtendimento,DetalheAtendimento *pDetalheAtendimento );
extern bool proCObtemWFDetalheAtendimento( long _idAtendimento,DetalheAtendimento *pDetalheAtendimento, char * c_idAtendimentoOrigem );
extern bool proCObtemWFDetalheAtendimentoEx( long _idAtendimento,DetalheAtendimento *pDetalheAtendimento );

extern bool proCObtemWFidContatoAtendimento( long _idAtendimento , int *_idContato);
extern bool proCWFObterTipoRetornoAtendimento(long _idAtendimento, int *_idTipoRetornoContato);
extern int  proCObterTipoGrupo(long _idAtendimento);
extern bool proCObtemWFAtdLinhas( int idConta , XMLGen *saida );
extern bool proCObtemWFAtdPessoa( int idPessoaDePara , XMLGen *saida );
extern bool proCObtemWFAtdCanal( int idCanal , XMLGen *saida );
extern bool proCObtemWFTipoComunic( int idPessoaDePara , XMLGen *saida );
extern bool proCObtemWFComunicacao( int idPessoaComunic , XMLGen *saida );
extern bool proCObtemWFPessoaComunic( int idPessoaDePara , XMLGen *saida );
extern bool proCObtemWFPessoaComunic2( int idPessoaDePara , long idPessoa, XMLGen *saida );
//Consultas gerais de dados de atendimento
extern bool proCConsultaWFAtendimento(st_Atendimento* dados, st_vlAtendimento* status, char* order, XMLGen* saida);
extern bool proCConsultaWFAtendimentoEx(st_Atendimento* dados, st_vlAtendimento* status, char* order, XMLGen* saida);
extern bool proCConsultaWFAtendimentoAlertaFila(long* _idAtendimento, XMLGen* saida);
extern bool proCConsultaWFAtendimentoAlertaDetalhe(long* _idAtendimento, XMLGen* saida);
extern bool proCConsultaWFAtendimentoAlertaFilaEx(long* _idAtendimento, XMLGen* saida);
extern bool proCConsultaWFAtendimentoAlertaDetalheEx(long* _idAtendimento, XMLGen* saida);
extern bool proCConsultaWFAtendimentoFilaLinha(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFAtendimentoFilaPessoa(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaMassa(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
//Pesquisas utilizadas na Tela Inicial
extern bool proCConsultaWFAtendimentoFilaLinhaTI(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
//Pesquisa para calcular dias uteis
extern void proCCalculaData( int cdAreaRegistro, double nrHoras, int incremento, char* saida );
extern bool proCAlterarWFAtendimento(st_Atendimento* dados, st_vlAtendimento* status, XMLDPR *xmlDpr);
extern int proCObtemWFTipoRetornoContatoAtual(long _idAtendimento);
extern bool proCObterTipoRetornoContato(long _idAtendimento,int *idTipoRetornoContato);
extern bool proCObterNrProtocolo(long _idAtendimento,char *_idAtendimentoProtocolo);
extern bool proCConsultaWFAtendimentoFilaPessoaMig(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);


/******************************************************************************************
*
* INICIO DOS METODOS ObtemXXXX que retornam dados especificos de atendimento
*
********************************************************************/

bool cWFAtendimento::ObtemAtend(long *idAtendimento)
{
    char *p;
    int idContato=0;
    int idPessoaDePara=0;
    int idLinha=0;

    if ( p = tx.walkTree( entrada , "idContato" , 0 ),p )
    {
        idContato = atoi(p);
        XMLString::release(&p);
    }

    if ( p = tx.walkTree( entrada , "idPessoaDePara" , 0 ),p )
    {
        idPessoaDePara = atoi(p);
        XMLString::release(&p);
    }

    if ( p = tx.walkTree( entrada , "idLinha" , 0 ),p )
    {
        idLinha = atoi(p);
        XMLString::release(&p);
    }

    return proCObtemWFAtendimento( idContato, 
                                   idPessoaDePara,
                                   idLinha,
                                   saida,
                                   idAtendimento);
}

bool cWFAtendimento::ObtemReaberAtend(long idAtendimento,XMLGen* saida,DetReabertura *detReabertura)
{
    return proCObtemWFReaberturaAtendimento(idAtendimento,saida,detReabertura);
}

bool cWFAtendimento::ObtemReaberAtend()
{
    if (m_vlDados.idAtendimento == -1)
        return false;
    
    return proCObtemWFReaberturaAtendimento(m_stDados.idAtendimento, saida,0 );
}

bool cWFAtendimento::ObtemContasCliente(int idPessoaDePara, int idConta, int idTipoLinha,  int idUfOperadora, XMLGen* saida)
{
    if ( !saida ) { saida = cWFAtendimento::saida; }

    return proCObtemWFContasCliente( idPessoaDePara, idConta, idTipoLinha, idUfOperadora, saida );
}

bool cWFAtendimento::ObtemContasCliente()
{
    long idPessoa;

    char* p =  tx.walkTree( entrada , "idPessoaDePara" , 0 );

    idPessoa = p ? atol(p) : 0;

    if (p) XMLString::release(&p);

    return proCObtemWFContasCliente( idPessoa , saida );
}

bool cWFAtendimento::ObtemDetalheAtend(long idAtendimento,XMLGen* saida,int *idAgrEstadoTProc)
{
    return proCObtemWFDetalheAtendimento( idAtendimento,saida,idAgrEstadoTProc );
}

bool cWFAtendimento::ObtemDetalheAtend(long idAtendimento,XMLGen* saida)
{
    return proCObtemWFDetalheAtendimento( idAtendimento,saida );
}

bool cWFAtendimento::ObtemDetalheAtend(long idAtendimento,DetalheAtendimento *pDetalheAtendimento)
{
   bool retorno;
   retorno = proCObtemWFDetalheAtendimento( idAtendimento,pDetalheAtendimento );
   ULOG( "### (1) Retornou de proCObtemWFDetalheAtendimento() - idAtendimentoOrigem [%ld]", pDetalheAtendimento->idAtendimentoOrigem );
   return retorno;
}



bool cWFAtendimento::ObtemDetalheAtend( long idAtendimento, DetalheAtendimento * pDetalheAtendimento, char * c_idAtendimentoOrigem )
{
   bool retorno;
   retorno = proCObtemWFDetalheAtendimento( idAtendimento, pDetalheAtendimento, c_idAtendimentoOrigem );
   ULOG( "### (2) Retornou de proCObtemWFDetalheAtendimento() - idAtendimentoOrigem [%s]", c_idAtendimentoOrigem );
   return retorno;
}

/*
 *
 * OS 1013 - Expurgo
 *
 */
bool cWFAtendimento::ObtemDetalheAtendEx( long idAtendimento, DetalheAtendimento *pDetalheAtendimento )
{
    return proCObtemWFDetalheAtendimentoEx( idAtendimento, pDetalheAtendimento );
}



bool cWFAtendimento::ObtemDetalheAtendEx_Migracao( long idAtendimento, DetalheAtendimento *pDetalheAtendimento )
{
    return proCObtemWFDetalheAtendimentoEx_Migracao( idAtendimento, pDetalheAtendimento );
}



int cWFAtendimento::ObterTipoRetornoContato(long idAtendimento)
{
    ULOG_START( "ObterTipoRetornoContato()" );

    ULOG("idAtendimento=%ld",idAtendimento);

    // Se o chamador não passou o tipo de retorno do contato, então busca
    int idTipoRetornoContato = proCObtemWFTipoRetornoContatoAtual(idAtendimento);

    ULOG("idTipoRetornoContato (entrada)=%d",idTipoRetornoContato);

    if ( -1 == idTipoRetornoContato )
    {
        ULOG("Tipo de retorno do contato do processo não pôde ser encontrado.");
    }
    else
    {
        // Se processo é COM RETORNO, define em tempo real qual o tipo de retorno do atendimento
        if ( TP_RET_SEM_RETORNO != idTipoRetornoContato )
        {
            proCObterTipoRetornoContato(idAtendimento,&idTipoRetornoContato);
            ULOG("idTipoRetornoContato (saida)=%d",idTipoRetornoContato);
        }
    }

    ULOG_END( "ObterTipoRetornoContato()" );

    return idTipoRetornoContato;
}

void cWFAtendimento::AtualizarTipoRetornoContato(long idAtendimento,int idTipoRetornoContato,XMLDPR *xmlDpr)
{
    ULOG_START( "AtualizarTipoRetornoContato()" );

    // Persiste o tipo de retorno do contato encontrado
    st_Atendimento dados;
    st_vlAtendimento status;

    memset(&dados,0,sizeof(dados));
    memset(&status,-1,sizeof(status));

    dados.idAtendimento = idAtendimento;
    status.idAtendimento = 1;

    dados.idTipoRetornoContato = idTipoRetornoContato;
    status.idTipoRetornoContato = 1;

    proCAlterarWFAtendimento(&dados,&status,xmlDpr);

    ULOG_END( "AtualizarTipoRetornoContato()" );
}

bool cWFAtendimento::ObtemDetalheAtend()
{
    if (m_vlDados.idAtendimento == -1)
    {
        return false;
    }

    return proCObtemWFDetalheAtendimento( m_stDados.idAtendimento,saida );
}

bool cWFAtendimento::ObtemDetalheAtend(long idAtendimento,int * idContato )
{
    return proCObtemWFidContatoAtendimento( idAtendimento  , idContato );
}

bool cWFAtendimento::ObterTipoRetornoAtendimento(long idAtendimento,int *tipoRetorno )
{
    return proCWFObterTipoRetornoAtendimento(idAtendimento,tipoRetorno);
}

int cWFAtendimento::ObterTipoGrupo(long idAtendimento)
{
    return proCObterTipoGrupo(idAtendimento);
}

bool cWFAtendimento::ObterNrProtocolo(long idAtendimento,char *idAtendimentoProtocolo)
{
    return proCObterNrProtocolo(idAtendimento,idAtendimentoProtocolo);
}

bool cWFAtendimento::ObtemLinhas()
{
    int idConta;

    char* p = tx.walkTree( entrada , "idConta" , 0 );

    idConta = p ? atoi(p) : 0;

    if (p) XMLString::release(&p);

    return proCObtemWFAtdLinhas( idConta , saida );
}

bool cWFAtendimento::ObtemPessoa()
{
    int idPessoaDePara;

    char* p = tx.walkTree( entrada , "idPessoaDePara" , 0 );

    idPessoaDePara = p ? atoi(p) : 0;

    if (p) XMLString::release(&p);

    return proCObtemWFAtdPessoa( idPessoaDePara , saida );
}

bool cWFAtendimento::ObtemCanal()
{
    int idCanal;

    char* p = tx.walkTree( entrada , "idCanal" , 0 );

    idCanal = p ? atoi(p) : 0;

    if (p) XMLString::release(&p);

    return proCObtemWFAtdCanal( idCanal , saida );
}

bool cWFAtendimento::ObtemTipoComunic()
{
    long idPessoa;

    char* p = tx.walkTree( entrada , "idPessoaDePara" , 0 );

    idPessoa = p ? atol(p) : 0;

    if (p) XMLString::release(&p);

    return proCObtemWFTipoComunic( idPessoa , saida );
}

bool cWFAtendimento::ObtemComunicacao()
{
    int idPessoaComunic;

    char* p = tx.walkTree( entrada , "idPessoaComunic", 0 );

    idPessoaComunic = p ? atoi(p) : 0;

    if (p) XMLString::release(&p);

    return proCObtemWFComunicacao( idPessoaComunic , saida );
}

bool cWFAtendimento::ObtemPessoaComunic(int idPessoaDePara,XMLGen* saida)
{
    if ( !saida ) { saida = cWFAtendimento::saida; }

    return proCObtemWFPessoaComunic( idPessoaDePara , saida );
}

bool cWFAtendimento::ObtemPessoaComunic()
{
    char* p = tx.walkTree( entrada , "idPessoaDePara" , 0 );

    long idPessoa = p ? atol(p) : 0;

    if (p) XMLString::release(&p);

    return proCObtemWFPessoaComunic( idPessoa , saida );
}

bool cWFAtendimento::ObtemPessoaComunic(int idPessoaDePara, long idPessoa, XMLGen* saida)
{
    if ( !saida ) { saida = cWFAtendimento::saida; }

    return proCObtemWFPessoaComunic2( idPessoaDePara , idPessoa, saida );
}

/******************************************************************************************
*
* FIM DOS METODOS ObtemXXXX que retornam dados especificos de atendimento
*
********************************************************************/


/******************************************************************************************
*
* INICIO de pesquisas em geral de atendimento
*
********************************************************************/
bool cWFAtendimento::consultar()
{
    char order[256];

    order[0] = 0;

    if ( entrada )
    {
        char* p;
        if ( p = tx.walkTree( entrada, "order", 0 ), p )
        {
            sprintf(order, "%.*s",sizeof(order)-1,p );
            XMLString::release(&p);
        }
    }

    return proCConsultaWFAtendimento(&m_stDados, &m_vlDados, order, saida);
}

bool cWFAtendimento::consultarEx()
{
    char order[256];

    order[0] = 0;

    if ( entrada )
    {
        char* p;
        if ( p = tx.walkTree( entrada, "order", 0 ), p )
        {
            sprintf(order, "%.*s",sizeof(order)-1,p );
            XMLString::release(&p);
        }
    }

    return proCConsultaWFAtendimentoEx(&m_stDados, &m_vlDados, order, saida);
}


bool cWFAtendimento::consultarProtocolos()
{
    carregaDadosConsultaFila();

    if (m_stFila.tpPesquisa == FILA_TPPESQ_LINHA || m_stFila.tpPesquisa == FILA_TPPESQ_LINHACLIENTE)
    {
        //Se inPrimeiraChamada = 1, então veio da tela inicial, tem que retornar 10 registros
        //e a pesquisa foi atimizada para isto. Caso contrário não muda nada.
        if( m_stFila.inPrimeiraChamada == 1 )
        {
            return proCConsultaWFAtendimentoFilaLinhaTI_Protocolos(&m_stFila, &m_vlFila, saida);
        }
        else
        {
            return proCConsultaWFAtendimentoFilaLinha_Protocolo(&m_stFila, &m_vlFila, saida);
        }
    }

    if (m_stFila.tpPesquisa == FILA_TPPESQ_CLIENTE)
    {
        m_stFila.inPessoa = 0;
        m_vlFila.inPessoa = -1;
    }
    else
    {
        m_stFila.inPessoa = 1;
        m_vlFila.inPessoa = 1;
    }

    return proCConsultaWFAtendimentoFilaPessoa(&m_stFila, &m_vlFila, saida);
}


bool cWFAtendimento::consultarProtocolosMig()
{
    carregaDadosConsultaFila();

    if (m_stFila.tpPesquisa == FILA_TPPESQ_LINHA || m_stFila.tpPesquisa == FILA_TPPESQ_LINHACLIENTE)
    {
        //Se inPrimeiraChamada = 1, então veio da tela inicial, tem que retornar 10 registros
        //e a pesquisa foi atimizada para isto. Caso contrário não muda nada.
        if( m_stFila.inPrimeiraChamada == 1 )
        {
        	return proCConsultaWFAtendimentoFilaLinhaTI_Migracao( &m_stFila, &m_vlFila, saida );
        }
        else
        {
            return proCConsultaWFAtendimentoFilaLinha_Migracao(&m_stFila, &m_vlFila, saida);
        }
    }

    if (m_stFila.tpPesquisa == FILA_TPPESQ_CLIENTE)
    {
        m_stFila.inPessoa = 0;
        m_vlFila.inPessoa = -1;
    }
    else
    {
        m_stFila.inPessoa = 1;
        m_vlFila.inPessoa = 1;
    }

    return proCConsultaWFAtendimentoFilaPessoaMig(&m_stFila, &m_vlFila, saida);
}


bool cWFAtendimento::consultarRelacionamento()
{
    carregaDadosConsultaFila();

    if (m_stFila.tpPesquisa == FILA_TPPESQ_LINHA || m_stFila.tpPesquisa == FILA_TPPESQ_LINHACLIENTE)
    {
        //Se inPrimeiraChamada = 1, então veio da tela inicial, tem que retornar 10 registros
        //e a pesquisa foi atimizada para isto. Caso contrário não muda nada.
        if( m_stFila.inPrimeiraChamada == 1 )
        {
        	//return proCConsultaWFAtendimentoFilaLinhaTI(&m_stFila, &m_vlFila, saida);
        	return proCConsultaWFAtendimentoFilaLinhaTI_Protocolos(&m_stFila, &m_vlFila, saida);
        }
        else
        {
            //return proCConsultaWFAtendimentoFilaLinha(&m_stFila, &m_vlFila, saida);
            return proCConsultaWFAtendimentoFilaLinha_Protocolo(&m_stFila, &m_vlFila, saida);
        }
    }

    if (m_stFila.tpPesquisa == FILA_TPPESQ_CLIENTE)
    {
        m_stFila.inPessoa = 0;
        m_vlFila.inPessoa = -1;
    }
    else
    {
        m_stFila.inPessoa = 1;
        m_vlFila.inPessoa = 1;
    }

    return proCConsultaWFAtendimentoFilaPessoa(&m_stFila, &m_vlFila, saida);
}


bool cWFAtendimento::consultarRelacionamentoMig()
{
    carregaDadosConsultaFila();

    if (m_stFila.tpPesquisa == FILA_TPPESQ_LINHA || m_stFila.tpPesquisa == FILA_TPPESQ_LINHACLIENTE)
    {
        //Se inPrimeiraChamada = 1, então veio da tela inicial, tem que retornar 10 registros
        //e a pesquisa foi atimizada para isto. Caso contrário não muda nada.
        if( m_stFila.inPrimeiraChamada == 1 )
        {
        	return proCConsultaWFAtendimentoFilaLinhaTI_Migracao( &m_stFila, &m_vlFila, saida );
        }
        else
        {
            return proCConsultaWFAtendimentoFilaLinha_Migracao(&m_stFila, &m_vlFila, saida);
        }
    }

    if (m_stFila.tpPesquisa == FILA_TPPESQ_CLIENTE)
    {
        m_stFila.inPessoa = 0;
        m_vlFila.inPessoa = -1;
    }
    else
    {
        m_stFila.inPessoa = 1;
        m_vlFila.inPessoa = 1;
    }

    return proCConsultaWFAtendimentoFilaPessoa(&m_stFila, &m_vlFila, saida);
}


bool cWFAtendimento::consultarMassa()
{
    carregaDadosConsultaFila();

    return proCConsultaWFFilaMassa(&m_stFila, &m_vlFila, saida);
}

bool cWFAtendimento::consultarAlerta(long* _idAtendimento, XMLGen* saida)
{
    return consultarAlerta(_idAtendimento, 0, saida);
}

bool cWFAtendimento::consultarAlertaEx(long* _idAtendimento, XMLGen* saida)
{
    return consultarAlertaEx(_idAtendimento, 0, saida);
}

bool cWFAtendimento::consultarAlerta(long* _idAtendimento, int _isSimplificado, XMLGen* saida)
{
    if (_isSimplificado == 0)
        return proCConsultaWFAtendimentoAlertaFila(_idAtendimento, saida);
    else
        return proCConsultaWFAtendimentoAlertaDetalhe(_idAtendimento, saida);
}

bool cWFAtendimento::consultarAlertaEx(long* _idAtendimento, int _isSimplificado, XMLGen* saida)
{
    if (_isSimplificado == 0)
        return proCConsultaWFAtendimentoAlertaFilaEx(_idAtendimento, saida);
    else
        return proCConsultaWFAtendimentoAlertaDetalheEx(_idAtendimento, saida);
}

/******************************************************************************************
*
* FIM de pesquisas em geral de atendimento
*
********************************************************************/


/******************************************************************************************
*
* INICIO de pesquisas de DATA
*
********************************************************************/

/**
    Esse método foi construido para manter compatibilidade com os serviços que ja usam esse
    método no SSKlunk10.
    A nova versão do método encontra-se acima.
    @autor Renato Teixeira
    @since 21/10/2004
*/
void cWFAtendimento::diasUteis()
{
    ULOG_START( "cWFAtendimento::diasUteis()" );

    char data[256];
    char *p;
    char *telefone = tx.walkTree( entrada, "nrTelefone", 0 );
    double horas=0;
    int incremento=0;

    if (p = tx.walkTree( entrada, "nrHoras", 0 ),p)
    {
        horas = atof(p);
        XMLString::release(&p);
    }

    if (p = tx.walkTree( entrada, "incremento", 0 ),p)
    {
        incremento = atoi(p);
        XMLString::release(&p);
    }

    diasUteis(telefone, horas, incremento, data);

    saida->createTag( "DataRetornada");
        saida->addItem( "data", data);
    saida->closeTag();  

    if (telefone)
    {
        XMLString::release(&telefone);
    }

    ULOG_END( "cWFAtendimento::diasUteis()" );
}

/**
    Determina o dia útil equivalente ao número de horas informado e o incremento.
    @param telefone O número do telefone que será usado para se obter a região a ser testada.
    @param horas O número de horas para cálculo da data.
    @param incremento Valor a ser adicionado na data base.
    Essa função usa como base a data atual para cálculo do dia útil, caso seja informado um valor de 
    incremento, esse valor será somado a data base e a partir da data resultante será calculada o
    dia útil.
*/
void cWFAtendimento::diasUteis(char* telefone,double horas,int incremento,char* data)
{
    ULOG_START("cWFAtendimento::diasUteis()");

    ULOG( "telefone=[%s]", telefone?telefone:"(null)");

    int prefixo = 11; // São Paulo por default

    if ( telefone && *telefone )
    {
        char cConv[3];

        if ( telefone[0] == '0' )
        {
            memcpy(cConv,telefone+1,2);
        }
        else
        {
            memcpy(cConv,telefone,2);
        }
        cConv[2]=0;
        prefixo = atoi(cConv);
    }

    proCCalculaData( prefixo, horas, incremento, data );

    ULOG_END("cWFAtendimento::diasUteis()");
}

/******************************************************************************************
*
* FIM de pesquisas de DATA
*
********************************************************************/
