
#include "tuxfw.h"
#include "../../negocio/admatdCmm/include/cFiltroArvContato.h"

DECLARE_TUXEDO_SERVICE( FILTROARVCNTO );

st_FiltroArvContato pRegistro;

void implFILTROARVCNTO::Execute( DOMNode *dnode ,XMLGen *xml_g )
{
    ULOG_START( "Execute()" );

    int iOperacao = 0;
    char *p = 0x0;
    
    
    try
    {
        CFiltroArvContato oFiltro;
        memset( &pRegistro,0x0,sizeof(pRegistro) );
        char * user = getUser();
        char idUser[65];
        sprintf( idUser,"%s",user );
        
        ULOG( "Obteve Usuario [%s]",idUser );
        
        if ( p = oFiltro.tx.walkTree( dnode, "inOperacao", 0 ), p ) 
        {
            iOperacao = atoi( p );
            XMLString::release( &p );
            ULOG( "... Solicitando operacao [%d]",iOperacao );
#ifdef WIN32
            printf( "\n... Solicitando operacao [%d]\n",iOperacao );
#endif
        }

        switch ( iOperacao )
        {

            case   0  : oFiltro.SelecaoArquivos( idUser, dnode ,&pRegistro ,xml_g );
                        break;

            case   1  : oFiltro.Insere( idUser, dnode,&pRegistro ,xml_g );
                        break;

            case   2  : oFiltro.Selecao( idUser, dnode,&pRegistro ,xml_g );
                        break;
        }

	 	setStatusCode("14I0000","Sucesso no processamento"); 

        ULOG_END( "Execute()" );
    }
    catch ( ... ) 
    {
	 	setStatusCode("14E0000","ERRO, VERIFIQUE PROCESSAMENTO"); 
        ULOG_END( "Execute()" );
    }
}