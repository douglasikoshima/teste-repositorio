/*****************************************************************************
 *
 * Modulo:    BXAATIVARUP
 * Arquivo:   BXAATIVARUP.cpp
 * Proposito: servico do framework tuxedo que aciona servico pro*c
 * Historico:
 * Data        Autor                 Descricao
 * ----------  --------------------  -----------------------------------------
 * 17/06/2004  C_RECOliveira         Criacao
 * 17/06/2004  C_EDMartins           Criacao
 *
 ****************************************************************************/
/**************************************************************************
 * Notas:
 *
 **************************************************************************/

//Definicao Global
#define BXAATIVARUPCPP

//Header de Srv de Infra-Estrutura
#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CBxa.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(BXAATIVARUP);

/**************************************************************************
 *  Funcao de Negocios:  BXAATIVARUP
 *
 *  Descricao: chama funcao de negocios
 *
 *  Parametros de Entrada e Saida:
 *  DOMNode		*dnode    Nodos da Arvore DOM XML
 *  XMLGen		*xml_g    XML de entrada
 *
 *  Parametros de Saida:
 *  DOMNode		*dnode    Nodos da Arvore DOM XML
 *  XMLGen		*xml_g    XML de saida
 *
 *************************************************************************/
void implBXAATIVARUP::Execute(DOMNode *dnode,XMLGen *xml_g)
{
   ULOG_START("implBXAATIVARUP::Execute()");

	/* Chamada de Funcao de Negocios */
	CSafePointer oSafePointer;
	CBaixa oBaixa;
    int iCount;
    char szPesquisa[15000 + 1];

    char* pidBaixa=NULL;
    char* pinHabilitado=NULL;

    pinHabilitado = oSafePointer.getTag( dnode, "inHabilitado", 0);
    ULOG("pinHabilitado[%s]", pinHabilitado?pinHabilitado:"...NULL...");
	if( strlennull( pinHabilitado ) <= 0 )
	{
		setStatusCode("14E0001","inHabilitado está nulo");
		ULOG_END("implBXAATIVARUP::Execute()");
		return;
	}

    memset(szPesquisa, 0x00, sizeof(szPesquisa));
    strcpy(szPesquisa, "(");
    for(iCount=0; (pidBaixa = oSafePointer.getTag( dnode, "idBaixa", iCount)) != NULL; iCount++) {

        ULOG("pidBaixa[%s]", pidBaixa?pidBaixa:"...NULL...");

        strcat(szPesquisa, pidBaixa);
        strcat(szPesquisa, ", ");
        ULOG("szPesquisa[%s]iCount(%d)", szPesquisa, iCount);
    }

    szPesquisa[strlen(szPesquisa) - 2]=0x00;
    strcat(szPesquisa, ")");
    ULOG("Ultima ->szPesquisa[%s]", szPesquisa);


    ULOG("b.iCount(%d)", iCount);
	if( iCount == 0 )
	{
		setStatusCode("14E0001","idBaixa está nulo");
		ULOG_END("implBXAATIVARUP::Execute()");
		return;
	}
	switch( oBaixa.ativaInativaArvoreUP2(szPesquisa, pinHabilitado))
	{
		case 0: setStatusCode("14I0000", "Operação realizada com sucesso" );break;
		case 1: setStatusCode("14E0002", "idBaixa está nulo" );break;
		case 2: setStatusCode("14E0003", "Baixa não foi achada, idBaixa está errada" );break;
		case 3: setStatusCode("14W0001", "A raiz não pode ser habilitada nem desabilitada" );break;
		case 4: setStatusCode("14W0002", "Esta operação não foi realizada, pois existem contatos associdados a esta baixa." );break;
		case 5: setStatusCode("14W0003", "Uma baixa com pai desabilitado não pode ser habilitada" );break;
		default: setStatusCode("14E9999", "Erro não listado" );break;
	}//switch
	ULOG_END("implBXAATIVARUP::Execute()");
}
