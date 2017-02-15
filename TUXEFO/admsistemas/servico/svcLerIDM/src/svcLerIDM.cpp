
#include <tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CPerfilIDM.h"
DECLARE_TUXEDO_SERVICE( LERIDM );

void implLERIDM::Execute(DOMNode*dnode,XMLGen*xml_g)
{
    ULOG_START( "implLERIDM::Execute()" );
    DOMNode * subnode;
	CSafePointer   oSafePointer;
    CPerfilIDM     oPerfilIDM;
    char   sOperacao[32];     sOperacao[0] = 0x0;
    char   idPerfilIDM[64];   idPerfilIDM[0] = 0x0;
    char   nmPerfilIDM[256];  nmPerfilIDM[0] = 0x0;
    int   i,j;
    char * buffer;

    buffer = oSafePointer.getTag(dnode,"idPerfil",0);
    if ( buffer[0] != 0x0)
       strcpy(idPerfilIDM,buffer);
    
    buffer = oSafePointer.getTag(dnode,"nmPerfil",0);
    if ( buffer[0] != 0x0)
       strcpy(nmPerfilIDM,buffer);

    
    ULOG( "*** idPerfilIDM [%s]",idPerfilIDM );
    ULOG( "*** nmPerfilIDM [%s]",nmPerfilIDM );
    
    xml_g->createTag("IDMPerfilVO");
    xml_g->addProp("xmlns", "admsistemas.fo.vivo.com.br/vo" );
    
    if ( idPerfilIDM[0] != 0x0 )
       xml_g->addItem("idPerfil", idPerfilIDM );
    
    xml_g->createTag("ListasVO");
    xml_g->createTag("Lista");
    j=0;
    while ( subnode = walkDOM(dnode,"Combos",j++) )
    {
        for (i=0;;i++)
        {
            /*  ---   nmSelect possiveis ---
                PerfilIDM
                Operadora
                Grupo
                ItemMenu
                Perfil
            */
            buffer = oSafePointer.getTag(subnode,"nmSelect",i);
            if ( buffer[0] == 0x0 )
                break;

            ULOG( ">>> Executando consultas..." );
                if ( !strcmp("PerfilIDM",buffer) )
                {
                    xml_g->addItem("nmSelect", "PerfilIDM" );
                    oPerfilIDM.ListaPerfil(xml_g);
                }
                if ( !strcmp("Operadora",buffer) )
                {
                    xml_g->addItem("nmSelect", "Operadora" );
                    oPerfilIDM.OperadoraPerfil(idPerfilIDM,xml_g);
                }
                if ( !strcmp("Grupo",buffer) )
                {
                    xml_g->addItem("nmSelect", "Grupo" );
                    oPerfilIDM.GrupoPerfil(idPerfilIDM,xml_g);
                }
                if ( !strcmp("ItemMenu",buffer) )
                {
                    xml_g->addItem("nmSelect", "ItemMenu" );
                    oPerfilIDM.ItemMenuPerfil(idPerfilIDM,xml_g);
                }
                if ( !strcmp("Perfil",buffer) )
                {
                    xml_g->addItem("nmSelect", "Perfil" );
                    oPerfilIDM.PerfilFO(idPerfilIDM,xml_g);
                }
        }
    }
    xml_g->closeTag();
    xml_g->closeTag();
    xml_g->addItem("codError", "000" );
    xml_g->addItem("msgError", "Operacao concluida com sucesso." );
    xml_g->closeTag();



	setStatusCode("14I0000","Operacao concluida com sucesso.");
	ULOG_END("implLERIDM::Execute()");
}
