#include "../include/svcmain.h"

DECLARE_TUXEDO_SERVICE(readRegEnc);

void implreadRegEnc::Execute( DOMNode * XMLIn, XMLGen * XMLOut )
{
   ULOG_START("implreadRegEnc::Execute()");
   char * parm = walkTree( XMLIn, "codGrupo", 0 );
   
   if ( !parm )
   {
      throw new TuxBasicSvcException( "09E0001", "Codigo de Grupo Invalido" );
   }
    
   XMLOut->createTag("RegrasEncaminhamentoVO");
   XMLOut->addProp( "xmlns","admsistemas.fo.vivo.com.br/vo" );
   XMLOut->createTag( "RegrasEncaminhamentoDisponivelVO" );
   
   if( !ConsultaCarterizacao(parm,XMLOut) )
   {
      XMLString::release(&parm);
      throw new TuxBasicSvcException("09E0002","=> CONSULTA: CARTEIRIZACAO");
   }

   if( !ConsultaProcedencia(parm,XMLOut) )
   {
      XMLString::release(&parm);
      throw new TuxBasicSvcException("09E0003","=> CONSULTA: PROCEDENCIA");
   }

   if( !ConsultaSegmentacao(parm,XMLOut) )
   {
      XMLString::release(&parm);
      throw new TuxBasicSvcException("09E0004","=> CONSULTA: SEGMENTACAO");
   }

   if( !ConsultaTipoCliente(parm,XMLOut) )
   {
      XMLString::release(&parm);
      throw new TuxBasicSvcException("09E0005","=> CONSULTA: TIPOCLIENTE");
   }

   if( !ConsultaTipoPessoa(parm,XMLOut) )
   {
      XMLString::release(&parm);
      throw new TuxBasicSvcException("09E0006","=> CONSULTA: TIPOPESSOA");
   }

   if( !ConsultaTipoRelacionamento(parm,XMLOut) )
   {
      XMLString::release(&parm);
      throw new TuxBasicSvcException("09E0007","=> CONSULTA: TIPORELACIONAMENTO");
   }

   if( !ConsultaGrupoAberturaGrupo(parm,XMLOut) )
   {
      XMLString::release(&parm);
      throw new TuxBasicSvcException("09E0008","=> CONSULTA: GRUPOABERTURAGRUPO");
   }

   if( !ConsultaCanalGrupo(parm,XMLOut) )
   {
      XMLString::release(&parm);
      throw new TuxBasicSvcException("09E0009","=> CONSULTA: CANALGRUPO");
   }

   if( !ConsultaRegionalGrupo(parm,XMLOut) )
   {
      XMLString::release(&parm);
      throw new TuxBasicSvcException("09E0010","=> CONSULTA: UFOPERADORAGRUPO");
   }

   XMLOut->closeTag();
   
   XMLOut->createTag( "RegrasEncaminhamentoSelecionadoVO" );

   
   if( !ConsultaCarterizacaoSel(parm,XMLOut) )
   {
      XMLString::release(&parm);
      throw new TuxBasicSvcException("09E0009","=> CONSULTA: CARTEIRIZACAO SEL");
   }

   if( !ConsultaProcedenciaSel(parm,XMLOut) )
   {
      XMLString::release(&parm);
      throw new TuxBasicSvcException("09E0010","=> CONSULTA: PROCEDENCIA SEL");
   }

   if( !ConsultaSegmentacaoSel(parm,XMLOut) )
   {
      XMLString::release(&parm);
      throw new TuxBasicSvcException("09E0011","=> CONSULTA: SEGMENTACAO SEL");
   }

   if( !ConsultaTipoClienteSel(parm,XMLOut) )
   {
      XMLString::release(&parm);
      throw new TuxBasicSvcException("09E0012","=> CONSULTA: TIPOCLIENTE SEL");
   }

   if( !ConsultaTipoPessoaSel(parm,XMLOut) )
   {
      XMLString::release(&parm);
      throw new TuxBasicSvcException("09E0013","=> CONSULTA: TIPOPESSOA SEL");
   }

   if( !ConsultaTipoRelacionamentoSel(parm,XMLOut) )
   {
      XMLString::release(&parm);
      throw new TuxBasicSvcException("09E0014","=> CONSULTA: TIPORELACIONAMENTO SEL");
   }

   if( !ConsultaGrupoAberturaGrupoSel( parm,XMLOut ))
   {
      XMLString::release(&parm);
      throw new TuxBasicSvcException("09E0015","=> CONSULTA: GRUPOABERTURAGRUPO SEL");
   }

   if( !ConsultaCanalGrupoSel(parm,XMLOut) )
   {
      XMLString::release(&parm);
      throw new TuxBasicSvcException("09E0016","=> CONSULTA: CANALGRUPOSEL");
   }

   if( !ConsultaRegionalGrupoSel(parm,XMLOut) )
   {
      XMLString::release(&parm);
      throw new TuxBasicSvcException("09E0010","=> CONSULTA: UFOPERADORAGRUPOSEL");
   }

   XMLOut->closeTag();

   XMLString::release(&parm);

   setStatusCode("09I0000","Execucao Bem Sucedida");
   ULOG_END("implreadRegEnc::Execute()");
   return;
}

