
#include "../include/cWFGrvCTIRetPC.h"


class TuxHelperImpl:public TuxHelper
{
public:
	DOMNode * walkDOMImpl( DOMNode*a , char*b , int*c , int d )
	{
		return walkDOM( a , b , c , d );
	}
};


DECLARE_TUXEDO_SERVICE(WFGRVCTIRET);

void implWFGRVCTIRET::Execute( DOMNode*dnode,XMLGen*xml_g )
{
   ULOG_START("implWFGRVCTIRET::Execute()");
   
	int SubnoAtual = 0;
	int Subno = 0;
	Collection  resultado;
	char        *pBuffer;
	char        Msg[4096];
	Msg[0] = 0;

	cWFGrvCTIPC  rc;
	stVariaveisGrvCTI dados;
	DOMNode        * pNoCodigo;
	TuxHelperImpl   tuxhelperIMPL;

   pBuffer = walkTree( dnode, "operacao", 0 );
   if ( pBuffer == NULL )
      dados.operacao[0] = 0x0;
   else
   {
      strcpy( dados.operacao,pBuffer );
      XMLString::release(&pBuffer);
   }
   
   pBuffer = walkTree( dnode, "sgRetornoWFCTI", 0 );
   if ( pBuffer == NULL )
      dados.sgretornowfcti[0] = 0x0;
   else
   {
      strcpy( dados.sgretornowfcti,pBuffer );
      XMLString::release(&pBuffer);
   }

   pBuffer = walkTree( dnode, "dsRetornoWFCTI", 0 );
   if ( pBuffer == NULL )
      dados.dsretornowfcti[0] = 0x0;
   else
   {
      strcpy( dados.dsretornowfcti,pBuffer );
      XMLString::release(&pBuffer);
   }

   pBuffer = walkTree( dnode, "sgStatus", 0 );
   if ( pBuffer == NULL )
      dados.sgstatus[0] = 0x0;
   else
   {
      strcpy( dados.sgstatus,pBuffer );
      XMLString::release(&pBuffer);
   }

   pBuffer = walkTree( dnode, "inPadrao", 0 );
   if ( pBuffer == NULL )
      dados.inpadrao[0] = 0x0;
   else
   {
      strcpy( dados.inpadrao,pBuffer );
      XMLString::release(&pBuffer);
   }

   pBuffer = walkTree( dnode, "idRetornoWFCTI", 0 );
   if ( pBuffer == NULL )
      rc.rwfcti.idretornowfcti = 0;
   else
   {
      rc.rwfcti.idretornowfcti = atoi(pBuffer);
      XMLString::release(&pBuffer);
   }

   POPULATE_INT(dnode,IDRETORNOWFCTI,rc.rwfcti.idretornowfcti);
	POPULATE_STR(dnode,SGRETORNOWFCTI,rc.rwfcti.sgretornowfcti,255);
	POPULATE_STR(dnode,DSRETORNOWFCTI,rc.rwfcti.dsretornowfcti,255);
	POPULATE_STR(dnode,SGSTATUS,rc.rwfcti.sgstatus,1);
	POPULATE_INT(dnode,INPADRAO,rc.rwfcti.inpadrao);
   
   pBuffer = getUser();
   if ( pBuffer == NULL )
      rc.rwfcti.idusuarioalteracao = 0;
   else
   {
      rc.rwfcti.idusuarioalteracao = atoi(pBuffer);
   }
   
   pBuffer = getUUID();
   if ( pBuffer == NULL )
      rc.rwfcti.idusuariogrupo = 0;
   else
   {
      rc.rwfcti.idusuariogrupo = atoi(pBuffer);
      XMLString::release(&pBuffer);
   }

   if ( atoi(dados.operacao) == 0 )
   {
      if ( rc.InsertWFRet(Msg) != true )
      {
         setStatusCode( "09W4096",Msg );
         return;
      }

      rc.DeleteByIDWFGRet();

      for ( ;; )  
      {
         Subno = 0;
         pNoCodigo = tuxhelperIMPL.walkDOMImpl( dnode,"WFGrupoSelecionadoVO",&Subno,SubnoAtual++ );
         if ( pNoCodigo == NULL )
            break;

         pBuffer = walkTree( pNoCodigo,"idGrupo",0 );
         if ( pBuffer == NULL )
            break;
         else
            rc.rwfcti.idgrupo = atoi(pBuffer);

         rc.InsertWFGRet();

      }

   }
   else
   {
      if( atoi(dados.operacao) == 1 )
      {
         
         if ( rc.Update( Msg ) != true )
         {
            setStatusCode( "09W4096",Msg );
            return;
         }
         
         sprintf( dados.idretornowfcti,"%d",rc.rwfcti.idretornowfcti );

         rc.DeleteByIDWFGRet();

         for ( ;; )  
         {
            Subno = 0;
            pNoCodigo = tuxhelperIMPL.walkDOMImpl( dnode,"WFGrupoSelecionadoVO",&Subno,SubnoAtual++ );
            if ( pNoCodigo == NULL )
               break;

            pBuffer = walkTree( pNoCodigo,"idGrupo",0 );
            if ( pBuffer == NULL )
               break;
            else
            {
               rc.rwfcti.idgrupo = atoi(pBuffer);
               XMLString::release(&pBuffer);
            }

            rc.InsertWFGRet();

         }

      }
      else
      {
         rc.DeleteByIDWFGRet();

         rc.DeleteWFRet();

      }

   }

	setStatusCode("09I0000","Gravação Retorno CTI Concluida.");
	
	ULOG_END("implWFGRVCTIRET::Execute()");

}
