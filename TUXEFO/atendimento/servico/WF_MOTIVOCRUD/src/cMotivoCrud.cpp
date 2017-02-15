#include "../include/cMotivoCrud.h"

//
// Prototipos
//
extern int proCInsereMotivo( const char * sMotivo );
extern int proCRemoveMotivo( const unsigned long idMotivo );
extern int proCConsultaMotivos( const char * sMotivoPrm, XMLGen * Saida );
extern int proCAlteraMotivo( const unsigned long idMotivoPrm, const char * sMotivoPrm );

//
//------------------------------------------------------------------------
cMotivoCrud::cMotivoCrud( DOMNode * dnode, XMLGen * xml_g )
{
	entrada = dnode;
	saida   = xml_g;

	carregaDados();
}



//
// iOperacao == 0 - Insere
// iOperacao == 1 - Alteracao
// iOperacao == 2 - Remover
// iOperacao == 3 - Consulta
int cMotivoCrud::Processa()
{
    int retorno;

    switch (iOperacao)
    {
    case 0:
        retorno = InsereMotivo();
        break;

    case 1:
        retorno = AlteraMotivo();
        break;

    case 2:
        retorno = RemoveMotivo();
        break;

    case 3:
        retorno = ConsultaMotivo();
        break;

    default:
        retorno = -3; // Operação inválida
        break;
    };

    return retorno;
}




int cMotivoCrud::InsereMotivo( )
{
    return proCInsereMotivo( sMotivo );
}




int cMotivoCrud::AlteraMotivo( )
{
    return proCAlteraMotivo( idMotivo,sMotivo );
}




int cMotivoCrud::RemoveMotivo( )
{
    return proCRemoveMotivo( idMotivo );
}




int cMotivoCrud::ConsultaMotivo( )
{
    saida->createTag( "WFManterMotivosVO" );
    saida->addProp( "xmlns","workflow.fo.vivo.com.br/vo" );
        int retorno = proCConsultaMotivos( sMotivo,saida );
    saida->closeTag();

    return retorno;
}




void cMotivoCrud::carregaDados( )
{
	char *p;

    if (p = tx.walkTree( entrada, "idMotivo", 0 ), p ) 
    {
        idMotivo = strtoul( p,0,10 );
        XMLString::release(&p);
    }

    if (p = tx.walkTree( entrada, "dsMotivo", 0 ), p ) 
    {
        sprintf(sMotivo,"%.*s",sizeof(sMotivo)-1,p);
        XMLString::release(&p);
    }

    if (p = tx.walkTree( entrada, "operacao", 0 ), p ) 
    {
        iOperacao = atoi( p );
        XMLString::release(&p);
    }

}

