/*****************************************************************************
 *
 * Modulo:    BxaListar
 * Arquivo:   BxaListar.cpp
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
#define BxaListarCPP

//Header de Srv de Infra-Estrutura
#include<tuxfw.h>

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(BxaListar);

/**************************************************************************
 *  Funcao de Negocios:  BxaListar
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

extern int  proCObtemWFHierarquiaBaixa(XMLGen* saida);
extern bool proCObtemWFBaixaLista(int sIdBaixa, int idTipoComunicacao, XMLGen* saida);
extern bool proCObtemWFBaixaContato(int sIdBaixa, int sIdContato, int idTipoComunicacao, XMLGen* saida);

void implBxaListar::Execute(DOMNode*dnode,XMLGen*xml_g)
{
    ULOG_START("implBxaListar::Execute()");
	 /* Chamada de Funcao de Negocios */
	 char* pBuffer;
    char idBaixaWrk[ 23 ];

	 int    iNivel;
	 int    iContTag;
	 int    idTpComunicAux = -1;
    int    idContatoAux = -1;
    int    idBaixaAux = -1;

	 bool filhos = false;

    pBuffer = walkTree( dnode, "idBaixa", 0 );
    if ( pBuffer )
    {
        idBaixaAux = atoi(pBuffer);
        XMLString::release(&pBuffer);
    }
	 else
        idBaixaAux = 0;


    pBuffer = walkTree( dnode, "idContato", 0 );
    if ( pBuffer )
    {
        idContatoAux = atoi(pBuffer);
        XMLString::release(&pBuffer);
    }
	 else
        idContatoAux = 0;


    pBuffer = walkTree( dnode, "idTipoComunicacao", 0 );
    
    if ( pBuffer )
    {
       idTpComunicAux = atoi(pBuffer);
       XMLString::release(&pBuffer);
    }
	 else
        idTpComunicAux = -1;

    xml_g->createTag("AdmFolhaBaixaVO");
	 xml_g->addProp("xmlns", "admsistemas.fo.vivo.com.br/vo" );

	 if ( idBaixaAux == 0 )
	      idBaixaAux = proCObtemWFHierarquiaBaixa(xml_g);

	 if ( idContatoAux == 0 )
	 {
 	   filhos = proCObtemWFBaixaLista(idBaixaAux, idTpComunicAux, xml_g);
		ULOGI("ListChildrenSemContat");
 	 }
	 else
	 {
	   filhos = proCObtemWFBaixaContato(idBaixaAux, idContatoAux, idTpComunicAux, xml_g);
		ULOGI("ListBxaComContatos");
 	 }

	 xml_g->closeTag();
	 setStatusCode("14I0000","Operacao concluida com sucesso!");
	
	 ULOG_START("implBxaListar::Execute()");
}
