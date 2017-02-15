#include <string.h>
#include <tuxfw.h>
#include "../include/svcmain.h"

DECLARE_TUXEDO_SERVICE(READRETORNO);

void implREADRETORNO::Execute( DOMNode * XMLIn, XMLGen * XMLOut )
{
   ULOG_START("implREADRETORNO::Execute()");
   char * parm = walkTree( XMLIn, "idContato", 0 );
   char * pRetorno = walkTree( XMLIn, "idTipoRetornoAtivo",0 );

   if ( !parm )
        throw new TuxBasicSvcException( "09E0001", "Código idContato Invalido" );

   if ( atol(parm) == 0 )
        throw new TuxBasicSvcException( "09E0050", "Código idContato não pode ser ZERO" );

   XMLOut->createTag("AdmRetornoContainerVO");
   XMLOut->addProp( "xmlns","admsistemas.fo.vivo.com.br/vo" );

   XMLOut->createTag( "listaTipoRetorno" );
  

      if ( strcmp(pRetorno, "") == 0)
      {
         if( !ConsultaRetorno(parm,XMLOut) )
         {
            XMLString::release(&parm);
            XMLString::release(&pRetorno);
            throw new TuxBasicSvcException("09E0002","=> CONSULTA: TIPO RETORNO");
         }

         XMLOut->closeTag();  //--------->  listaTipoRetorno <----------
         XMLOut->closeTag(); //------>  AdmRetornoContainerVO

         XMLString::release(&parm);
         XMLString::release(&pRetorno);

         setStatusCode("09I0000","Execucao Bem Sucedida");
         return;
      }

   XMLOut->closeTag();

   
      //--------> Existentes
      XMLOut->createTag( "existente" );
      //------------------------------------
   
      if( !ConsultaPessoa(parm,pRetorno,XMLOut) )
      {
         XMLString::release(&parm);
         XMLString::release(&pRetorno);
         throw new TuxBasicSvcException("09E0003","=> CONSULTA: ADMPESSOA");
      }
   
      if( !ConsultaTipoLinha(parm,pRetorno,XMLOut) )
      {
         XMLString::release(&parm);
         XMLString::release(&pRetorno);
         throw new TuxBasicSvcException("09E0004","=> CONSULTA: TIPO LINHA");
      }
   
      if( !ConsultaCanalEntrada(parm,pRetorno,XMLOut) )
      {
         XMLString::release(&parm);
         XMLString::release(&pRetorno);
         throw new TuxBasicSvcException("09E0005","=> CONSULTA: CANAL ENTRADA");
      }
   
      if( !ConsultaProcedencia(parm,pRetorno,XMLOut) )
      {
         XMLString::release(&parm);
         XMLString::release(&pRetorno);
         throw new TuxBasicSvcException("09E0006","=> CONSULTA: PROCEDENCIA");
      }
   
      if( !ConsultaRelacionamento(parm,pRetorno,XMLOut) )
      {
         XMLString::release(&parm);
         XMLString::release(&pRetorno);
         throw new TuxBasicSvcException("09E0007","=> CONSULTA: TIPO CLIENTE");
      }
   
      if( !ConsultaGrupo(parm,pRetorno,XMLOut) )
      {
         XMLString::release(&parm);
         XMLString::release(&pRetorno);
         throw new TuxBasicSvcException("09E0008","=> CONSULTA: GRUPO");
      }
   
      if( !ConsultaSegmentacao(parm,pRetorno,XMLOut) )
      {
         XMLString::release(&parm);
         XMLString::release(&pRetorno);
         throw new TuxBasicSvcException("09E0008","=> CONSULTA: SEGMENTACAO");
      }

      if( !ConsultaTipoCarteira(parm,pRetorno,XMLOut) )
      {
         XMLString::release(&parm);
         XMLString::release(&pRetorno);
         throw new TuxBasicSvcException("09E0008","=> CONSULTA: CARTEIRIZACAO");
      }

      if( !ConsultaTpUfOperadora(parm,pRetorno,XMLOut) )
      {
         XMLString::release(&parm);
         XMLString::release(&pRetorno);
         throw new TuxBasicSvcException("09E0008","=> CONSULTA: UFOPERADORA");
      }



      XMLOut->closeTag();

      //--------> Existentes ---------------
      XMLOut->createTag( "associado" );
      //------------------------------------
   

      if( !ConsultaPessoaSel(parm,pRetorno,XMLOut) )
      {
         XMLString::release(&parm);
         XMLString::release(&pRetorno);
         throw new TuxBasicSvcException("09E0003","=> CONSULTA: ADMPESSOA SEL");
      }

      if( !ConsultaTpLinhaSel(parm,pRetorno,XMLOut) )
      {
         XMLString::release(&parm);
         XMLString::release(&pRetorno);
         throw new TuxBasicSvcException("09E0004","=> CONSULTA: TIPO LINHA SEL");
      }


      if( !ConsultaCanalEntradaSel(parm,pRetorno,XMLOut) )
      {
         XMLString::release(&parm);
         XMLString::release(&pRetorno);
         throw new TuxBasicSvcException("09E0005","=> CONSULTA: CANAL ENTRADA SEL");
      }
 
      if( !ConsultaProcedenciaSel(parm,pRetorno,XMLOut) )
      {
         XMLString::release(&parm);
         XMLString::release(&pRetorno);
         throw new TuxBasicSvcException("09E0006","=> CONSULTA: PROCEDENCIA SEL");
      }
 
      if( !ConsultaRelacionamentoSel(parm,pRetorno,XMLOut) )
      {
         XMLString::release(&parm);
         XMLString::release(&pRetorno);
         throw new TuxBasicSvcException("09E0007","=> CONSULTA: TIPO CLIENTE SEL");
      }

      if( !ConsultaGrupoSel(parm,pRetorno,XMLOut) )
      {
         XMLString::release(&parm);
         XMLString::release(&pRetorno);
         throw new TuxBasicSvcException("09E0008","=> CONSULTA: GRUPO SEL");
      }
 
      if( !ConsultaSegmentacaoSel(parm,pRetorno,XMLOut) )
      {
         XMLString::release(&parm);
         XMLString::release(&pRetorno);
         throw new TuxBasicSvcException("09E0008","=> CONSULTA: SEGMENTACAO SEL");
      }
 
      if( !ConsultaTpCarteiraSel(parm,pRetorno,XMLOut) )
      {
         XMLString::release(&parm);
         XMLString::release(&pRetorno);
         throw new TuxBasicSvcException("09E0008","=> CONSULTA: CARTEIRIZACAO SEL");
      }

      if( !ConsultaTpUfOperadoraSel(parm,pRetorno,XMLOut) )
      {
         XMLString::release(&parm);
         XMLString::release(&pRetorno);
         throw new TuxBasicSvcException("09E0008","=> CONSULTA: CARTEIRIZACAO SEL");
      }


      XMLString::release(&parm);

      XMLString::release(&pRetorno);
   
      XMLOut->closeTag();

      XMLOut->closeTag(); //------>  AdmRetornoContainerVO

      setStatusCode("09I0000","Execucao Bem Sucedida");
      
      ULOG_END("implREADRETORNO::Execute()");
      
      return;
}

