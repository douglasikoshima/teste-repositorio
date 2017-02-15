//---------------------------------------------------------------------
//*
//* Class: classContaPontuacao
//---------------------------------------------------------------------
//* Purpose:
//*
//* Utilizada em Lupa do Cliente - Aba Pontos
//* Retorna todas as Contas Pontuacao de um determinado cliente.
//*
//* Review:
//*
//* Task force to seek potential memory leaks and exception errors - March,2005 - Cassio
//* 
//---------------------------------------------------------------------

#include <tuxfw.h>
#include "../include/Exception.h"
#include "../include/SaldoPontos.h"

#include "../include/Global.h"
#include "../include/classContaPontuacao.h"


class TuxHelperImpl:public TuxHelper
{
public:
 DOMNode * walkDOMImpl( DOMNode*a , char*b , int*c , int d )
 {
  return walkDOM( a , b , c , d );
 }
};

//
// Construtor e Destrutor
CContaPontuacao::CContaPontuacao()
{
    memset(sSaldo  , 0x00, sizeof(sSaldo));
    memset(sNrConta, 0x00, sizeof(sNrConta));
    pObjLinhas = NULL;
}

CContaPontuacao::~CContaPontuacao()
{
    if (pObjLinhas)
        free(pObjLinhas);
}

//
// Metodos getter
char* CContaPontuacao::getSaldo(void)
{
    return ((char *)sSaldo);
}

char* CContaPontuacao::getNrConta(void)
{
    return ((char *)sNrConta);
}

long CContaPontuacao::getQtdLinhaPontos(void)
{
    return (lQtdLinhaPontos);
}

CLinhaPonto* CContaPontuacao::getLinhaPontos(void)
{
    return (pObjLinhas);
}

//
// Metodos setter
void CContaPontuacao::setSaldo(char* pDado)
{
    strcpy(sSaldo, pDado);
}

void CContaPontuacao::setNrConta(char* pDado)
{
    strcpy(sNrConta, pDado);
}

void CContaPontuacao::setQtdLinhaPontos(long lDado)
{
    lQtdLinhaPontos = lDado;
}

void CContaPontuacao::setLinhaPontos(CLinhaPonto* pObj)
{
    pObjLinhas = pObj;
}

//Metodos de acesso ao Banco de Dados

CContaPontuacao* CContaPontuacao::RecuperarTodos(int* iNroObjetos, char* pDdd, char* pLin)
{
    ULOG_START("CContaPontuacao::RecuperarTodos()");
    XMLGen pXmlRetorno;
    
    DOMNode *pNoConta; 
    DOMNode *pNoLinha; 
    DOMNode *pDomSP; 
    
    TuxHelper       tuxHelper;
    TuxHelperImpl   tuxHelperI;
    
    int iRet = 0; 
    int iSubno = 0;
    int iSub2no = 0;
    int iNroObjCta = 1;
    int iNroObjLin = 1;
    
    char  *p;
    char  *pInput=0;
    char  sXmlData[256];
    CContaPontuacao* oCta = 0;
    CLinhaPonto*     oLin = 0;
    
    CSaldoPontos oSaldoPontos;

    oSaldoPontos.setDDD(pDdd);
    oSaldoPontos.setNrFone(pLin);

    oSaldoPontos.ConsultaPontos();
    pDomSP = oSaldoPontos.getXMLRetorno();

    /* Monta o XML para pesquisa a outro servico */
    for(; (pNoConta = tuxHelperI.walkDOMImpl(pDomSP, "CONTA", &iSubno, iNroObjCta-1)) ; iSubno=0, iNroObjCta++)
    {
		// Aloca memória para o objeto atual.
		if ((oCta = (CContaPontuacao*) realloc((void *)oCta, (sizeof(CContaPontuacao) * iNroObjCta))) != NULL)
        {
			// Coloca os dados do objeto atual.
            p = tuxHelper.walkTree(pNoConta, "SALDO", 0);

            oCta[iNroObjCta-1].setSaldo(p?p:"0");
            XMLString::release(&p);

            p = tuxHelper.walkTree(pNoConta, "NUM_CONTA", 0);
            oCta[iNroObjCta-1].setNrConta(p?p:"");
            XMLString::release(&p);

            /* Procura o no Linha */
            for(; (pNoLinha = tuxHelperI.walkDOMImpl(pNoConta, "LINHA", &iSub2no, iNroObjLin-1)) ;iSub2no=0,iNroObjLin++)
            {
		        // Aloca memória para o objeto atual.
		        if ((oLin = (CLinhaPonto*) realloc((void *)oLin, (sizeof(CLinhaPonto) * iNroObjLin))) != NULL)
                {
                    p = tuxHelper.walkTree(pNoLinha, "AREA", 0);
                    oLin[iNroObjLin-1].setNrCodArea(p?p:""); 
                    XMLString::release(&p);

                    p = tuxHelper.walkTree(pNoLinha, "NUM_LINHA", 0);
                    oLin[iNroObjLin-1].setNrLinha(p?p:""); 
                    XMLString::release(&p);
                }
                else
                {
                    if (oLin)
                        free(oLin);

                    if (oCta)
                        free(oCta);

				    ULOG_END("CContaPontuacao::RecuperarTodos()");
				    throw  TuxBasicSvcException(NRO_ERR_MEMORIA,MSG_ERR_MEMORIA);
                }
            } 
            oCta[iNroObjCta-1].setLinhaPontos(oLin);
            oCta[iNroObjCta-1].setQtdLinhaPontos((long)(iNroObjLin-1));
        }            
        else
        {
            if (oLin)
                free(oLin);

            if (oCta)
                free(oCta);

			ULOG_END("CContaPontuacao::RecuperarTodos()");
			throw  TuxBasicSvcException(NRO_ERR_MEMORIA,MSG_ERR_MEMORIA);
        }
    }

	if ( iNroObjCta >= 1)
        if ( iNroObjetos )
		    *iNroObjetos = iNroObjCta - 1;

	ULOG_END("CContaPontuacao::RecuperarTodos()");
	return oCta;
}
