/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  
 * @version $Revision: 1.1.6.3 $
 * @CVS     $Author: a5114878 $ - $Date: 2012/08/27 19:27:30 $
 **/
 
#include "../include/cLstGrupoAssoc.h"
#include "../../../commons/msgPadrao.h"

//
// Prototipos
//
extern void proCListaGrupoAssoc(unsigned long idContato, unsigned long idPessoaUsuario,XMLGen * Saida);
extern void proCListaGrupoAssocTodos( unsigned long idContatoPrm, XMLGen * Saida );


//
//------------------------------------------------------------------------
cLstGrupoAssoc::cLstGrupoAssoc( unsigned long idUsuario, DOMNode * dnode, XMLGen * xml_g )
{
    ULOG_START("cLstGrupoAssoc::cLstGrupoAssoc()");
    entrada = dnode;
	saida   = xml_g;
    idUser = idUsuario;

    carregaDados();
    ULOG_END("cLstGrupoAssoc::cLstGrupoAssoc()");
}




void cLstGrupoAssoc::Processa( void )
{
    ListaGrupoHabilitado();
}




void cLstGrupoAssoc::ListaGrupoHabilitado( void )
{
    ULOG_START("cLstGrupoAssoc::ListaGrupoHabilitado()");
    saida->createTag( "PerfilVariaveisVO" );
    saida->addProp( "xmlns","admsistemas.fo.vivo.com.br/vo" );
    saida->addProp( "xmlns:ns2","cliente.fo.vivo.com.br/vo" );
    saida->addProp( "xmlns:ns3","workflow.fo.vivo.com.br/vo" );
    ULOG("=== STATUS ===");
	 ULOG("idUser  =  [%d]",idUser );
	 ULOG("idContato =  [%l]", idContato);

    if( inPerfil )
        proCListaGrupoAssocTodos( idContato,saida );
	 else
        proCListaGrupoAssoc( idContato,idUser,saida );
        
    saida->closeTag();
    ULOG_END("cLstGrupoAssoc::ListaGrupoHabilitado()");
}




void cLstGrupoAssoc::carregaDados( void )
{
    ULOG_START("cLstGrupoAssoc::carregaDados()");
    char * p;
    inPerfil = 0;

    if (p = tx.walkTree( entrada, "idContato", 0 ), p ) 
    {
        idContato = strtoul( p,0,10 );
        XMLString::release(&p);
    }
    //ESta variavei indica que a chamada se originou da tela arvore de contato > Editar Workflow > Perfil CRI
    if (p = tx.walkTree( entrada, "inPerfil", 0 ), p ) 
    {
        inPerfil = atoi(p);
        XMLString::release(&p);
    }
    
  	ULOG("=== STATUS ===");
	ULOG("inPerfil  =  [%d]",inPerfil );
	ULOG("idContato =  [%l]", idContato);

    ULOG_END("cLstGrupoAssoc::carregaDados()");
    
}
