#include "../include/cCfgPerfilGruCon.h"
#include "../include/cCfgPerfilGruConImpl.h"

//
// Prototipos
//
extern short proCAtualizaContatoGrupoPerfil
( 
 unsigned long idUser, 
 unsigned long idContatoPrm, 
 unsigned long idPerfilPrm, 
 unsigned long idGrupoPrm,
 unsigned long sqOrdemPrm,
 unsigned long idGrupoPerfilPrm
);

extern short proCApagaContatoGrupoPerfil
( 
    unsigned long idUser, 
    unsigned long idContatoPrm,
    unsigned long idGrupoPerfilPrm
);

//
//------------------------------------------------------------------------
cCfgPerfilGruCon::cCfgPerfilGruCon( unsigned long idUsuario, DOMNode * dnode, XMLGen * xml_g )
{
	entrada = dnode;
	saida   = xml_g;
    idUser = idUsuario;

    carregaDados();
}




void cCfgPerfilGruCon::Processa( void )
{
    AtualizaGrupoPerfil();
}




void cCfgPerfilGruCon::AtualizaGrupoPerfil( void )
{
    int i;
    char msg[ 129 ];
    

    if(bFlagDeleta == true)
    {
        //A exclusao apaga um por um
        if( proCApagaContatoGrupoPerfil( idUser,idContato,pGrupoPerfil.at(0) )  == -1 ) {
    	    strcpy( msg,"Esta relação não pode ser apagada, pois tem processo em aberto" );
    
    	    saida->createTag( "WFAcaoRetornoVO" );
    	    saida->addProp( "xmlns","workflow.fo.vivo.com.br/vo" );
    
    	    saida->addItem( "acaoExecucao","S" );
    	    saida->addItem( "mensagem",msg );
    
    	    saida->closeTag();
        }
        else
        {
            strcpy( msg,"Exclusão efetuada com sucesso" );
            
            saida->createTag( "WFAcaoRetornoVO" );
            saida->addProp( "xmlns","workflow.fo.vivo.com.br/vo" );
            
            saida->addItem( "acaoExecucao","S" );
            saida->addItem( "mensagem",msg );
            
            saida->closeTag();
        }
    }
    else
    {
        for ( i=0; i < pPerfil.size() ; i++ )
        {
            proCAtualizaContatoGrupoPerfil( 
                idUser, 
                idContato, 
                pPerfil.at(i), 
                pGrupo.at(i),
                i,
                pGrupoPerfil.at(i));
        }

        strcpy( msg,"Associação efetuada com sucesso" );
        
        saida->createTag( "WFAcaoRetornoVO" );
        saida->addProp( "xmlns","workflow.fo.vivo.com.br/vo" );
        
        saida->addItem( "acaoExecucao","S" );
        saida->addItem( "mensagem",msg );
        
        saida->closeTag();
    }
}




void cCfgPerfilGruCon::carregaDados( void )
{
    DOMNode       * pDOM;
    TuxHelperImpl   txhlpIMPL;
    int             i;
    int             iRec = 0;
	char          * p;


    p = tx.walkTree( entrada,"operacao",0 );
    if ( p != NULL )
    {
        if(!memcmp(p, "I", 1))
            bFlagDeleta=false;
        else
            bFlagDeleta=true;

        XMLString::release(&p);
    }
    

    p = tx.walkTree( entrada,"contato",0 );
    if ( p != NULL )
    {
        idContato = strtoul( p,0,10 );
        XMLString::release(&p);
    }

    for ( i=0;;i++ )  
    {
        p = tx.walkTree( entrada,"idGrupoPerfil",i );
        if( p == NULL )
        {
            break;
        }
        pGrupoPerfil.push_back( strtoul(p,0,10) );
        XMLString::release(&p);
    }

    for ( i=0;;i++ )  
    {
        iRec = 0;
        pDOM = txhlpIMPL.walkDOMImpl( entrada,"PerfilGrupoVO",&iRec,i );
        if ( pDOM == NULL )
        {
            break;
        }
        p = tx.walkTree( pDOM,"idPerfil",0 );
        if( p != NULL )
        {
            pPerfil.push_back( strtoul( p,0,10 ) );
            XMLString::release(&p);
        }

        p = tx.walkTree( pDOM,"idGrupo",0 );
        if( p != NULL )
        {
            pGrupo.push_back( strtoul( p,0,10 ) );
            XMLString::release(&p);
        }

    }


}
