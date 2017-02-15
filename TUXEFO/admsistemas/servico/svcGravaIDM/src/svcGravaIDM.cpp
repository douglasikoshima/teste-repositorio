
#include <tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CPerfilIDM.h"
DECLARE_TUXEDO_SERVICE( GRVIDM );

void implGRVIDM::Execute(DOMNode*dnode,XMLGen*xml_g)
{
    ULOG_START( "implGRVIDM::Execute()" );
    DOMNode * subnode;
	CSafePointer   oSafePointer;
    CPerfilIDM     oPerfilIDM;
    char   sOperacao[32];     sOperacao[0] = 0x0;
    char   idPerfilIDM[64];   idPerfilIDM[0] = 0x0;
    char   nmPerfilIDM[256];  nmPerfilIDM[0] = 0x0;
    char   idUsuarioNovo[64]; idUsuarioNovo[0] = 0x0;
    char   tpOperacao[32];    tpOperacao[0] = 0x0;
    int   i,j;
    char * buffer;
    char * cidUser = getUser();

    buffer = oSafePointer.getTag(dnode,"idPerfil",0);
    if ( buffer[0] != 0x0)
       strcpy(idPerfilIDM,buffer);
    
    buffer = oSafePointer.getTag(dnode,"nmPerfil",0);
    if ( buffer[0] != 0x0)
       strcpy(nmPerfilIDM,buffer);

    buffer = oSafePointer.getTag(dnode,"idUsuario",0);
    if ( buffer[0] != 0x0)
       strcpy( idUsuarioNovo,buffer );
   
    buffer = oSafePointer.getTag(dnode,"tpOperacao",0);
    if ( buffer[0] != 0x0)
       strcpy( tpOperacao,buffer );

    ULOG( "*** idPerfilIDM   [%s]",idPerfilIDM );
    ULOG( "*** nmPerfilIDM   [%s]",nmPerfilIDM );
    ULOG( "*** idUsuarioNovo [%s]",idUsuarioNovo );
    ULOG( "*** tpOperacao    [%s]",tpOperacao );
    
    int ctRegistros = 0;
    if ( nmPerfilIDM[0] != 0x0 && idPerfilIDM[0] == 0x0 )
    {
        oPerfilIDM.InserePerfilIDM( cidUser,idPerfilIDM,nmPerfilIDM,&ctRegistros );
    }
    
    xml_g->createTag("IDMPerfilVO");
    xml_g->addProp("xmlns", "admsistemas.fo.vivo.com.br/vo" );
    
    if ( ctRegistros > 0 )
    {
        xml_g->addItem("idPerfil", "" );
        xml_g->addItem("codError", "001" );
        xml_g->addItem("msgError", "Nome de Perfil já cadastrado." );
        //xml_g->createTag("ListasVO");
        //xml_g->createTag("Lista");
        //xml_g->closeTag();
        //xml_g->closeTag();
        xml_g->closeTag();
        setStatusCode("14I0000","Operacao concluida com sucesso.");
        ULOG_END("implGRVIDM::Execute()");
        return;
    }

    if ( idPerfilIDM[0] != 0x0 )
       xml_g->addItem("idPerfil", idPerfilIDM );

    //xml_g->createTag("ListasVO");
    //xml_g->createTag("Lista");
    j=0;
    while ( subnode = walkDOM(dnode,"Lista",j++) )
    {
        /*  ---   nmSelect possiveis ---
            PerfilIDM
            Operadora
            Grupo
            ItemMenu
            Perfil
        */
        buffer = oSafePointer.getTag(subnode,"nmSelect",0);
        if ( buffer[0] == 0x0 )
            continue;
        if ( !strcmp("Operadora",buffer) )
        {
            ULOG( "Gravando Operadora" );
            oPerfilIDM.InsereOperadoraPerfil(cidUser,idUsuarioNovo,dnode,xml_g);
        }
        if ( !strcmp("Grupo",buffer) )
        {
            ULOG( "Gravando Grupo" );
            oPerfilIDM.InsereGrupoPerfil(cidUser,idUsuarioNovo,dnode,xml_g);
        }
        if ( !strcmp("ItemMenu",buffer) )
        {
            ULOG( "Gravando ItemMenu" );
            oPerfilIDM.InsereItemMenuPerfil(cidUser,idUsuarioNovo,dnode,xml_g);
        }
        if ( !strcmp("Perfil",buffer) )
        {
            ULOG( "Gravando Perfil" );
            oPerfilIDM.RelacionaPerfil(cidUser,idUsuarioNovo,dnode,xml_g);
        }
    }
    //xml_g->closeTag();
    //xml_g->closeTag();
    xml_g->addItem("codError", "000" );
    xml_g->addItem("msgError", "Operacao concluida com sucesso." );
    xml_g->closeTag();



	setStatusCode("14I0000","Operacao concluida com sucesso.");
	ULOG_END("implGRVIDM::Execute()");
}
