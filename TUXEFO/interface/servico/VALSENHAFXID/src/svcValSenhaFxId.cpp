
/**
 * Serviço de validação de senha e solicitação de segmetação de cliente, ativado via URA - Linhas Fixas
 **/

#include "ValSenhaSolicSegmentacaoFxId.hpp"
#include "../../../negocio/commons/include/Commom.hpp"
#include "../../../negocio/commons/include/stLinha.hpp"
#include <tuxfw.h>
#include "time.h"

extern void TipoConta( char * idLinhaTelefonicaPrm, char * sTipoContaPrm );
char * alltrim( char * pStr );

DECLARE_TUXEDO_SERVICE ( VALSENHAFXID );

int getTimeNow()
{
   srand ( time(NULL));
   struct tm *data;
   time_t ltime;
   time( &ltime );
   data = localtime ( &ltime );
   char dataInicio[50];
   memset(&dataInicio,0,50);

   int mes = data->tm_mon;
   int ano = data->tm_year;
   int dia = data->tm_mday;
   int hora = data->tm_hour;
   int minuto = data->tm_min;
   int segundo = data->tm_sec;
   char cano[4+1];
   char cmes[2+1];
   char cdia[2+1];
   char chora[2+1];
   char cminuto[2+1];
   char csegundo[2+1];


   if(mes+1 < 10)
      sprintf(cmes,"0%d",mes+1);
   else
      sprintf(cmes,"%d",mes+1);

   sprintf(cano,"%d",(ano+1900));

   if(dia < 10)
      sprintf(cdia,"0%d",dia);
   else
      sprintf(cdia,"%d",dia);

   sprintf(dataInicio,"%s%s%s",cano,cmes,cdia);

   int timenow = atoi(dataInicio);
   timenow += (hora*60*60+minuto*60+segundo);

   return timenow;

}

    

void implVALSENHAFXID::Execute(DOMNode* dnode, XMLGen* xml_g) {

   char *pcDDD = NULL;
   char *pcFone = NULL;
   char szCodRetorno[3]; // Codigo de retorno
   struct stStatuslinha stLinha;
   struct stVlStatuslinha stVlLinha;
   memset( &stLinha, 0, sizeof( stLinha ) );
   memset( &stVlLinha, 0, sizeof( stVlLinha ) );
   int timeInicial = getTimeNow();

   // Busca pelas informações necessárias para execução do serviço
   // DDD
   pcDDD = walkTree(dnode, TAG_DDD, 0);
   // Fone
   pcFone = walkTree(dnode, TAG_FONE, 0);

   if ((!pcDDD) || (!pcFone) || (!*pcDDD) || (!*pcFone) || (!IsNumeric(pcDDD)) || (!IsNumeric(pcFone)))
   {
      stLinha.ntStatus = ERR_IN_PARAM;
   }

   if ( stLinha.ntStatus == RET_OK )
   {
      stLinha.ntLinha = atoi( pcFone );
      stLinha.ntArea  = atoi( pcDDD  );
      strcpy( stLinha.chrTelefone, pcDDD );
      strcat( stLinha.chrTelefone, pcFone );
      stVlLinha.i_chrSenhacliente = -1;
      stVlLinha.i_chrSenhausuario = -1;
      stVlLinha.i_dblIdpessoalinha = -1;
      
      RecuperarDados( &stLinha, &stVlLinha );
      tuxfw_getlogger()->information("Dados Recuperados");
   }

   // Seta flag de retorno
   if (stLinha.ntStatus == RET_OK)
   {
      setStatusCode(COD_I_0000, MSG_I_0000);
      strncpy(szCodRetorno,&COD_I_0000[5],2);
   }
   else
   {
      switch( stLinha.ntStatus )
      {
      case ERR_NOT_FOUND_OR_DISABLED:
         {
            setStatusCode( COD_E_0001, MSG_E_0001 );
            strncpy(szCodRetorno,&COD_E_0001[5],2);
            break;
         }
      case ERR_IN_PARAM:
         {
            setStatusCode( COD_E_0002, MSG_E_0002 );
            strncpy(szCodRetorno,&COD_E_0002[5],2);
            break;
         }
      case ERR_DATABASE:
         {
            setStatusCode( COD_E_0003, MSG_E_0003 );
            strncpy(szCodRetorno,&COD_E_0003[5],2);
            break;
         }
      case ERR_SUBROUTINES:
         {
            setStatusCode( COD_E_0004, MSG_E_0004 );
            strncpy(szCodRetorno,&COD_E_0004[5],2);
            break;
         }
      case ERR_NAO_ATENDIDO_FO:
         {
            setStatusCode( COD_E_0006, MSG_E_0006 );
            strncpy(szCodRetorno,&COD_E_0006[5],2);
            break;
         }
      default:
         {
            setStatusCode( COD_E_0005,MSG_E_0005 );
            strncpy(szCodRetorno,&COD_E_0005[5],2);
            break;
         }
      }
   }
   szCodRetorno[2]=0;

   // Testa o codigo de retorno, se diferente de 0 nao monta a TAG de resposta.
   xml_g->addItem(TAG_STATCOM, 1);
   xml_g->addItem(TAG_CODIGORETORNO,szCodRetorno);
      
   char sTipoConta[256];
   char idLinhaTelefonica[256];
   memset( sTipoConta       , 0x0, sizeof(sTipoConta) );
   memset( idLinhaTelefonica, 0x0, sizeof(idLinhaTelefonica) );
   sprintf( idLinhaTelefonica, "%.0f", stLinha.dblIdlinhatelefonica );
   TipoConta( idLinhaTelefonica, sTipoConta );
   xml_g->addItem( "CODCATG", sTipoConta );
      
   if(szCodRetorno[1]=='0' || szCodRetorno[1]=='7')
      {

         xml_g->addItem(TAG_SEGMENTO,stLinha.ntSegmento);

      tuxfw_getlogger()->information("Tipo Pessoa: %s", stLinha.chrTipopessoa);

      // formata cep
      char cepCliente[9+1];
      int k=0;
      for(int i=0;i<strlen(stLinha.chrCEPcliente);i++)
      {
         if(stLinha.chrCEPcliente[i] != '-')
            cepCliente[k++] = stLinha.chrCEPcliente[i];
      }
      cepCliente[k] = 0;
      strcpy(stLinha.chrCEPcliente,cepCliente);

      xml_g->addItem(TAG_CEP_CLIENTE,stLinha.chrCEPcliente);

      // nova tag de cluster
      if(stLinha.idClusterLinha == 0)
         xml_g->addItem(TAG_CLUSTERLINHA,"");
      else
         xml_g->addItem(TAG_CLUSTERLINHA,stLinha.idClusterLinha);
      
      // cdCodigoSubSegmentoCliente
      alltrim( stLinha.cdCodigoSubSegmentoCliente );   // Correcao INC000001062343
      xml_g->addItem(TAG_CODSUBSEGMENTOCLIENTE, stLinha.cdCodigoSubSegmentoCliente);

   }
   XMLString::release(&pcDDD);
   XMLString::release(&pcFone);
	
   tuxfw_getlogger()->debug("TEMPO URIN = %d",(getTimeNow()-timeInicial));

}



char * alltrim( char * pStr )   // Correcao INC000001062343
{
    int rInd;

    if (pStr == NULL)
        return NULL;
    rInd = strlen(pStr) - 1;

    if (!isspace(pStr[rInd]))
        return pStr;

    while ( isspace(pStr[rInd]) && rInd >= 0)
        rInd--;
    pStr[rInd + 1] = '\0';
    return pStr;
}
