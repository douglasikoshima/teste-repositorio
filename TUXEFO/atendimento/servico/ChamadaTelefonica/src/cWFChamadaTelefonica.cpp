/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:27 $
 **/ 


#include "../include/cWFChamadaTelefonica.h"

extern unsigned long  proCIncluirWFChamadaTelefonica(st_ChamadaTelefonica* dados, st_vlChamadaTelefonica* status, XMLGen* saida);
extern unsigned long  proCIncluirWFChamadaTelefonicaB(st_ChamadaTelefonica* dados, st_vlChamadaTelefonica* status, XMLGen* saida, char *pszTipoPessoa);
extern bool proCAlterarWFChamadaTelefonica(st_ChamadaTelefonica* dados, st_vlChamadaTelefonica* status, XMLGen* saida);
extern bool proCExcluirWFChamadaTelefonica(st_ChamadaTelefonica* dados, st_vlChamadaTelefonica* status, XMLGen* saida);
extern bool proCConsultaWFChamadaTelefonica(st_ChamadaTelefonica* dados, st_vlChamadaTelefonica* status, char* order, XMLGen* saida);

/**
    Construtor da classe concebido para sobrepor o construtor original que recebia como parametros
    um DOMNode e um XMLGen, esse novo construtor recebe duas structs e um XMLGen, essas duas structs
    tem a função de fornecer os valores a serem manipulados e a indicação dos campos que possuem valor.
    O XMLGen continua existindo na declaração pois os métodos de consulta devolverão os dados diretamente
    ao XMLGen, assim não sendo necessário criar um segundo processo para a conversão do resultado diferente.
    @autor Renato Teixeira
    @since 21/10/2004
*/
cWFChamadaTelefonica::cWFChamadaTelefonica(st_ChamadaTelefonica* dados, st_vlChamadaTelefonica* indicator, XMLGen*xml_g)
{
    locado = false;

    m_stDados = dados;
    m_vlDados = indicator;

    entrada = 0;
    saida   = xml_g;
}

/**
    Esse construtor foi desenvolvido levando em conta as necessidades do SSKlunk10.
    @autor Renato Teixeira
    @since 21/10/2004
*/
cWFChamadaTelefonica::cWFChamadaTelefonica(DOMNode*dnode, XMLGen*xml_g)
{

    m_stDados = (st_ChamadaTelefonica*) malloc(sizeof(st_ChamadaTelefonica));
    m_vlDados = (st_vlChamadaTelefonica*) malloc(sizeof(st_vlChamadaTelefonica));

    locado = true;
    
    entrada = dnode;
    saida   = xml_g;

    carregaDados();
}

/**
    Destrutor da classe.
    Libera a estruturas de dados quando elas foram alocadas internamente e não recebidas no construtor.
    @autor Renato Teixeira
    @since 21/10/2004
*/
cWFChamadaTelefonica::~cWFChamadaTelefonica()
{
    if (locado)
    {
        if (m_stDados) free(m_stDados);
        if (m_vlDados) free(m_vlDados);
    }
}

unsigned long cWFChamadaTelefonica::incluir()
{
    unsigned long idChamadaTelefonica = proCIncluirWFChamadaTelefonica(m_stDados, m_vlDados, saida);
    
    return idChamadaTelefonica; 
}

int cWFChamadaTelefonica::alterar()
{
    if (m_vlDados->idChamadaTelefonica == -1)  return -1;

    return proCAlterarWFChamadaTelefonica(m_stDados, m_vlDados, saida);
}

int cWFChamadaTelefonica::excluir()
{
    if (m_vlDados->idChamadaTelefonica == -1)  return -1;

    return proCExcluirWFChamadaTelefonica(m_stDados, m_vlDados, saida);
}

bool cWFChamadaTelefonica::consultar()
{
    char order[256];

    order[0] = 0;

    if ( entrada )
    {
        char *p;
        if ( p=tx.walkTree( entrada, "order", 0 ),p )
        {
            sprintf(order,"%.*s",sizeof(order)-1,p);
            XMLString::release(&p);
        }
    }

    return proCConsultaWFChamadaTelefonica(m_stDados, m_vlDados, order, saida);
}

void cWFChamadaTelefonica::carregaDados()
{
    char *p;

    // Inicializa as estruturas de dados para armazenar os valores.
    memset(m_stDados,0,sizeof(st_ChamadaTelefonica));
    memset(m_vlDados,-1,sizeof(st_vlChamadaTelefonica));

    if (p=tx.walkTree( entrada, "idChamadaTelefonica", 0 ),p)
    {
        m_stDados->idChamadaTelefonica = atoi(p);
        m_vlDados->idChamadaTelefonica = 1;
        XMLString::release(&p);
    }

    if (p=tx.walkTree( entrada, "idGrauSatisfacao", 0 ),p)
    {
        m_stDados->idGrauSatisfacao = atoi(p);
        m_vlDados->idGrauSatisfacao = 1;
        XMLString::release(&p);
    }

    if (p=tx.walkTree( entrada, "dtChamada", 0 ),p)
    {
        strcpy(m_stDados->dtChamada, p); 
        m_vlDados->dtChamada = 1;
        XMLString::release(&p);
    }
    if (p=tx.walkTree( entrada, "idPessoaDePara", 0 ),p)
    {
        m_stDados->idPessoaDePara =  atol(p); 
        m_vlDados->idPessoaDePara = 1;
        XMLString::release(&p);
    }
    if (p=tx.walkTree( entrada, "tipoPessoa", 0 ),p)
    {
        strcpy( m_stDados->sgTipoRelacionamento, p ); 
        m_vlDados->sgTipoRelacionamento = 1;
        XMLString::release(&p);
    }
}