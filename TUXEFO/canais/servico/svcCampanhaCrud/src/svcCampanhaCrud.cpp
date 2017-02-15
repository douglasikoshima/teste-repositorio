
#include <stdio.h>
#include <tuxfw.h>
#include "../../negocio/TuxHelperClever/include/TuxHelperClever/TuxHelperClever.h"
#include "../../negocio/UtilCanais/include/UtilCanais/UtilCanais.hpp"

#undef SQLCA
#define SQLCA_NONE
#include <sqlca.h>
#include <sqlda.h>

extern void proCInsereCampanha( int idUsuarioPrm, DOMNode* dnode  );
extern void proCAlteraCampanha( int idUsuarioPrm, int idCampanhaPrm, DOMNode* dnode );
extern void proCExcluirCampanha( int idCampanhaPrm );


DECLARE_TUXEDO_SERVICE(CAMPANHCRUD);

void implCAMPANHCRUD::Execute( DOMNode* dnode, XMLGen* xml_g ) 
{   
   CTuxHelperClever helper;
   CStatusCode      statusCode;
   
   char* p0 = NULL;	
   int	id = 0;
   int   idUsuario;
   int   idCampanha;
   char *user = getUser();
   
   idUsuario = atoi( user );
   
   p0 = helper.walkTree( dnode, "idCampanha", 0 );
   if ( p0 != NULL )
   {
      idCampanha = atoi( p0 );
   }

   p0 = helper.walkTree( dnode, "dsOperacao", 0 );
   if ( p0 != NULL )
   {
      if ( !strcmp(p0,"INCLUSAO") )
      {
         try
         {
            proCInsereCampanha( idUsuario, dnode );
            statusCode.setCod("13I0000");
            statusCode.setMsg("Sucesso");
         }
         catch ( TuxBasicOraException *ex )
         {
            char codErro[25];
            char msg[1024];
            sprintf(codErro,"04E%04d",ex->eCode<0 ?ex->eCode*(-1):ex->eCode);
            strcpy(msg,ex->pMsg);
            tuxfw_getlogger()->debug("Falha: [%s]\n", msg );
            if ( !memcmp(msg,"ORA-00001: unique constraint (VOL.CAMPANHACEAK1)",48) )
            {
			    statusCode.setCod("13W0001");				
   			    statusCode.setMsg("O código SGP informado já está cadastrado!");
            }
            else
            {
                if ( !memcmp(msg,"ORA-00001: unique constraint (APOIO.PARAMETROAK2)",49) )
                {
    			    statusCode.setCod("13W0001");				
    			    statusCode.setMsg("Este nome de histórico de relacionamento já existe para outra campanha!");
                }
                else
                {
    			    statusCode.setCod("13E0002");				
    			    statusCode.setMsg(msg);
                }
            }

            delete ex;
         }
         catch ( ... )
         {
		    statusCode.setCod("13E9999");				
		    statusCode.setMsg("Erro desconhecido");
         }
      }
      if ( !strcmp(p0,"ALTERACAO") )
      {
         try
         {
             proCAlteraCampanha( idUsuario, idCampanha, dnode );
    		 statusCode.setCod("13I0000");
    		 statusCode.setMsg("Sucesso");
		 }
         catch ( TuxBasicOraException *ex )
         {
            char codErro[25];
            char msg[1024];
            sprintf(codErro,"04E%04d",ex->eCode<0 ?ex->eCode*(-1):ex->eCode);
            strcpy(msg,ex->pMsg);
            tuxfw_getlogger()->debug("Falha: [%s]\n", msg );
		    statusCode.setCod("13E0002");				
		    statusCode.setMsg(msg);
            delete ex;
         }
         catch ( ... )
         {
		    statusCode.setCod("13E9999");				
		    statusCode.setMsg("Erro desconhecido");
         }
      }
      
      if ( !strcmp(p0,"EXCLUSAO") )
      {
         try
         {
             proCExcluirCampanha( idCampanha );
    		 statusCode.setCod("13I0000");
    		 statusCode.setMsg("Sucesso");
		 }
         catch ( TuxBasicOraException *ex )
         {
            char codErro[25];
            char msg[1024];
            sprintf(codErro,"04E%04d",ex->eCode<0 ?ex->eCode*(-1):ex->eCode);
            strcpy(msg,ex->pMsg);
            tuxfw_getlogger()->debug("Falha: [%s]\n", msg );
		    statusCode.setCod("13E0002");				
		    statusCode.setMsg(msg);

            delete ex;
         }
         catch ( ... )
         {
		    statusCode.setCod("13E9999");				
		    statusCode.setMsg("Erro desconhecido");
         }
		 
      }

   }
   
   // seta mensagem de retorno - header
   setStatusCode(statusCode.getCod(), statusCode.getMsg());
      
}
