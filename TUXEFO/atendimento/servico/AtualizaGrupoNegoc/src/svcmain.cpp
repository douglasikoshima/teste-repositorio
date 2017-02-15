#include "../include/svcmain.h"

DECLARE_TUXEDO_SERVICE(writeRegEnc);


// a função ValidaDependencias(parm,idret) 
//e RemoveUsuarioSkill(parm)
//estao no  source ==>grp_neg_ins_tplinha.pcpp <==
//para evitar alteração
//de makefiles em horário crítico
//ficando pendente altera-lo depois como melhoria


void implwriteRegEnc::Execute( DOMNode*XMLIn , XMLGen*XMLOut )
{
   ULOG_START("implwriteRegEnc::Execute()");

	char *parm = walkTree( XMLIn, "codigoGrupo",0 );
	char msgRet[255];
	char msgJavaScript[2];
	//controle de validação de dependencias
	char *ctrValidaDependencia = walkTree( XMLIn, "Valida",0 );
	int   ctrValida=0;
	int	  ctrApaga=1;
 	ULOG( "==========inicio de writeRegEnc ==============================");
    if ( !parm )
    {
		throw new TuxBasicSvcException("09E0001","Falta Tag de Dependencia");
    }
	else
    {
		ctrValida=atoi(ctrValidaDependencia);
    }

    if ( !parm )
   {
		throw new TuxBasicSvcException("09E0001","Nao Encontrou codigoGrupo");
   }
   
	if( atoi(parm) == 0 )
   {
		throw new TuxBasicSvcException("09E0001","Nao Encontrou codigoGrupo");
   }
   
	if(ctrValida)
   {
		ULOG( "Verificando se alguma variavel foi excluida" );
		if( !SomenteInclusaoVariaveis( parm, XMLIn ) )
   {
	 		ULOG( "Variaveis foram removidas, verificando skill e usuários associados");
			//Conta quanto usuarios e skills serão afetados
			int ctrDependenciasSkill	=	ValidaDependencias(parm,1); //Skill
			int ctrDependenciasUsuario	=	ValidaDependencias(parm,2); //Usr
   
			if(ctrDependenciasSkill || ctrDependenciasUsuario)
   {
				ctrApaga=0;
				sprintf(msgRet,"Com essa alteração, podem ser desassociados %d skills e %d usuários. Deseja continuar?",
								ctrDependenciasSkill,ctrDependenciasUsuario);
				sprintf(msgJavaScript,"S");
   }
   }
   }
   
	 ULOG( "if(ctrApaga[%d])", ctrApaga );
	if(ctrApaga)
   {
		sprintf(msgRet,"Regras de Encaminhamento Gravadas! As novas regras só terão validade para atendimento abertos após esta modificação");
		sprintf(msgJavaScript,"N");
	   /*
		*  Remove dados nas tabelas a serem atualizadas
		*/
	   	RemoveProcedencia(parm);
		RemoveSegmentacao(parm);
		RemoveTpCarteira(parm);
		RemoveTipoLinha(parm);
		RemoveTipoPessoa(parm);
		RemoveTipoRelacionamento(parm);
		RemoveGrupoAberturaGrupo(parm);
		RemoveCanalGrupo(parm);
		RemoveRegionalGrupo(parm);

      /*
    *  Atualiza os dados nas tabelas 
    */
   if ( !InsereProcedencia( parm, XMLIn ) )
   {
      XMLString::release(&parm);
      throw new TuxBasicSvcException("09E0006","INSERT: PROCEDENCIAGRUPO");
   }
   if ( !InsereSegmentacao( parm, XMLIn ) )
   {
      XMLString::release(&parm);
      throw new TuxBasicSvcException("09E0007","INSERT: SEGMENTACAO");
   }
   if ( !InsereTipoCarteira( parm, XMLIn ) )
   {
      XMLString::release(&parm);
      throw new TuxBasicSvcException("09E0008","INSERT: TIPOLINHAGRUPO");
   }
   if ( !InsereTipoLinha( parm, XMLIn ) )
   {
      XMLString::release(&parm);
      throw new TuxBasicSvcException("09E0009","INSERT: TIPOCARTEIRAGRUPO");
   }
   if ( !InsereTipoPessoa( parm, XMLIn ) )
   {
      XMLString::release(&parm);
      throw new TuxBasicSvcException("09E0009","INSERT: TIPOPESSOA");
   }
   if ( !InsereTipoRelacionamento( parm, XMLIn ) )
   {
      XMLString::release(&parm);
      throw new TuxBasicSvcException("09E0009","INSERT: TIPORELACIONAMENTO");
   }
   if ( !InsereGrupoAberturaGrupo( parm, XMLIn ) )
   {
      XMLString::release(&parm);
      throw new TuxBasicSvcException("09E0009","INSERT: GRUPOABERTURAGRUPO");
   }
   if ( !InsereCanalGrupo( parm, XMLIn ) )
   {
      XMLString::release(&parm);
      throw new TuxBasicSvcException("09E0009","INSERT: CANALGRUPO");
   }
   if ( !InsereRegionalGrupo( parm, XMLIn ) )
   {
      XMLString::release(&parm);
      throw new TuxBasicSvcException("09E0010","INSERT: REGIONALGRUPO");
   }

		//Remove os Skills que utilizam variaveis que os grupos perderam
		//No caso de adicionar variveis a grupo não afeta os Skill
		ULOG( "> RemoveSkillProcedencia(%s)", parm);
	   	RemoveSkillProcedencia(parm);
		ULOG( "> RemoveSkillSegmentacao(%s)", parm);
		RemoveSkillSegmentacao(parm);
		ULOG( "> RemoveSkillTpCarteira(%s)", parm);
		RemoveSkillTpCarteira(parm);
		ULOG( "> RemoveSkillTipoLinha(%s)", parm);
		RemoveSkillTipoLinha(parm);
		ULOG( "> RemoveSkillTipoPessoa(%s)", parm);
		RemoveSkillTipoPessoa(parm);
		ULOG( "> RemoveSkillTipoRelacionamento(%s)", parm);
		RemoveSkillTipoRelacionamento(parm);
		ULOG( "> RemoveSkillGrupoAberturaGrupo(%s)", parm);
		RemoveSkillGrupoAberturaGrupo(parm);
		ULOG( "> RemoveSkillCanalGrupo(%s)", parm);
		RemoveSkillCanalGrupo(parm);
		ULOG( "> RemoveSkillRegionalGrupo(%s)", parm);
		RemoveSkillRegionalGrupo(parm);
		
	   	XMLString::release(&parm);
	}//if(ctrApaga)

	ULOG( "=============monta xml=====================================");

			   XMLOut->createTag("WFAcaoRetornoVO");
			   XMLOut->addProp("xmlns","workflow.fo.vivo.com.br/vo");
				 XMLOut->addItem("acaoExecucao",msgJavaScript);  
				 XMLOut->addItem("mensagem",msgRet);  
  			   XMLOut->closeTag();
              
	ULOG( "=============ifsetStatuscode=====================================");

   setStatusCode("09I0000","Sucesso Na Execucao");
   ULOG("implwriteRegEnc::Execute()");   
}


