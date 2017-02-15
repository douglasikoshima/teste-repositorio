
#include "../include/cDomCampoDinPC.h"

DECLARE_TUXEDO_SERVICE(WFDOMCAMPODIN);

void implWFDOMCAMPODIN::Execute( DOMNode*dnode, XMLGen*xml_g )
{
   register int indx;
   Collection resultado;
   cDomCampoDinPC rc;
   st_VariaveisDomCampoDin dados;
   st_VariaveisDomCampoDin * pdadosResult;
   char *p;

   if ( p=walkTree( dnode, "idCampo", 0 ),p )
   {
     strcpy( dados.idCampo, p);
     XMLString::release(&p);
   }

   if ( p=walkTree( dnode, "idUfOperadora", 0 ),p )
   {
     strcpy( dados.idUFOperadora, p);
     XMLString::release(&p);
   }

   if ( p=walkTree( dnode, "idTipoLinha", 0 ),p )
   {
     strcpy( dados.idTipoLinha, p);
     XMLString::release(&p);
   }

   rc.pesquisaDominioCampoDinamicoPC( &dados,&resultado );

   xml_g->createTag("FormularioVO");
   xml_g->addProp("xmlns","admsistemas.fo.vivo.com.br/vo");

   int getCount = resultado.GetCount();

   for ( indx=0;indx < getCount;indx++ )
   {
       if ( pdadosResult = (st_VariaveisDomCampoDin *)resultado.GetItem(indx),pdadosResult )
       {
           if ( indx == 0 )
           {
               xml_g->createTag("FormularioCampoVO");
               xml_g->addProp("xmlns","admsistemas.fo.vivo.com.br/vo");
               xml_g->addItem("idContatoFolhaCampo", pdadosResult->idTabelaDominio );
               xml_g->addItem("idCampo", pdadosResult->idCampo );
               xml_g->addItem("nmCampo", pdadosResult->nmCampo );
           }

           xml_g->createTag("FormularioCampoValorVO");
           xml_g->addItem("idFormularioCampoValor", pdadosResult->idDominio );
           xml_g->addItem("valor", pdadosResult->nmDominio );
           xml_g->closeTag();
       }
   }

   xml_g->closeTag();
   xml_g->closeTag();

   setStatusCode( "04I0000","Obtencao de Dominio do Campo Dinamico Concluido." );

}
