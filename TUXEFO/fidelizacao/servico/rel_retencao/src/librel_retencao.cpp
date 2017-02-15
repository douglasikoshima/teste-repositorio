//
// $Id: librel_retencao.cpp,v 1.1 2009/07/31 15:33:36 a5110702 Exp $
//


#include "../../negocio/fidutil/include/retencao.hpp"
#include "../include/rel_retencao.h"



//montar dinamicamente o filtro para o relatorio
int gerarWhere(Filtro stf,char*pWhere, int idRelatorio)
{

	char szWhere[4000];
//Campo Datainicial e final são sempre obrigatórios
	sprintf(szWhere,"WHERE DATA >=  TO_DATE('%s' || ' 00:00:00','DD/MM/YYYY HH24:MI:SS')"
	              	" AND   DATA <= TO_DATE('%s' || ' 23:59:59','DD/MM/YYYY HH24:MI:SS')",stf.szDataIni,stf.szDataFim);


		if( idRelatorio==REL_MOV_DIARIAS || idRelatorio==REL_NOTES_LOJA )
		{
			//Esses relatorios levam em consideração a tabela relmovdiariaV01
			if(strlen(stf.szClassificacao))
			{
				strcat(szWhere," AND SEGMENTACAO= '");
				strcat(szWhere,stf.szClassificacao);
				strcat(szWhere,"' ");
				strcat(szWhere,"\n");
			}
		}else{
				if(strlen(stf.szClassificacao))
				{
					strcat(szWhere," AND IDSEGMENTACAO=");
					strcat(szWhere,stf.szClassificacao);
					strcat(szWhere,"\n");
				}
		}


		if(strlen(stf.szLogin)) 
		{
			strcat(szWhere," AND NMLOGINUSUARIO= '");
			strcat(szWhere,stf.szLogin);
			strcat(szWhere,"' ");
			strcat(szWhere,"\n");
		}

		if(strlen(stf.szGrupo)) 
		{
			strcat(szWhere," AND IDGRUPO=");
			strcat(szWhere,stf.szGrupo);
			strcat(szWhere,"\n");
		}

		if(strlen(stf.szTipoCliente))
		{
			strcat(szWhere," AND IDTIPOPESSOA=");
			strcat(szWhere,stf.szTipoCliente);
			strcat(szWhere,"\n");
		}

		if(strlen(stf.szOperadora)) 
		{
			strcat(szWhere," AND IDUFOPERADORA in (");
			strcat(szWhere,stf.szOperadora);
			strcat(szWhere,")");
			strcat(szWhere,"\n");
		}
	
	   
		if(strlen(stf.szOferta))
		{
			strcat(szWhere," AND OFERTA = ");
			strcat(szWhere,stf.szOferta);
		}

	//	strcat(szWhere,";");

		strcpy(pWhere,szWhere);

	return 1;

};

//Relatório de Retencao
extern int rel_retencao(char *stf, XMLGen*xml, const Filtro &stFiltro);

//Relatório de Retencao com Distinção ==>Ultima ligação é válida
//extern int rel_retencaoDistincao(char *stf, XMLGen*xml);
extern int rel_retencaoDistincao(char *stf, XMLGen*xml, const Filtro &stFiltro);

//Relatório de Controle de Crédito
extern int rel_ctrlcredito(char *stf, XMLGen*xml);

//Relatório de Ofertas
extern int rel_ofertas(char *stf, XMLGen*xml, const Filtro &stFiltro);

//Relatório de Operador
//Precisamos passar os dados de filtro para montar a query no serviço
extern int rel_operador(char *stf, XMLGen*xml, const Filtro &stFiltro);

//Relatório de Retencao por Ofertas
extern int rel_retoferta(char *stf, XMLGen*xml, const Filtro &stFiltro);

//Relatório de Planos
extern int rel_planos(char *stf, XMLGen*xml, const Filtro &stFiltro);

//Relatório de Planos com Distinção ==>Ultima ligação é válida
extern int rel_planosdistincao(char *stf, XMLGen*xml, const Filtro &stFiltro);;//(szFiltro, xml_g, stFiltro)

//Relatório de Resultado Destino
extern int rel_resdestino(char *stf, XMLGen*xml, const Filtro &stFiltro);

//Relatório de Resultado Destino com Distinção ==>Ultima ligação é válida
extern int rel_resdestinodistincao(char *stf, XMLGen*xml, const Filtro &stFiltro);

//Relatório de Movimentação Diária
extern int rel_movdiaria(char *stf, XMLGen*xml);

//Relatório de Ligações de Retencao
extern int rel_ligretencao(char *stf, XMLGen*xml);

//Relatório de Ofertas tais mensais
extern int rel_ofertatotalmensal(char *stf, XMLGen*xml);

extern int rel_notesloja( char *stf, XMLGen*xml);




DECLARE_TUXEDO_SERVICE(RELRETENCAO);

void implRELRETENCAO::Execute(DOMNode*dnode,XMLGen*xml_g)
{
  ULOG_START("RELRETENCAO");

  char parm[10]; parm[0] = '\0';
  int idUsr= get_idUsuario(getUser());;
  int idRelatorio=0;
  char szFiltro[4000]; szFiltro[0] = '\0';
  struct Filtro stFiltro;		//Variaveis para o filtro
  
  memset(&stFiltro,0,sizeof(Filtro));

  /*<DataInicio xmlns:vo="fidelizacao.fo.vivo.com.br/vo">01/08/2005</DataInicio>
  <DataFim xmlns:vo="fidelizacao.fo.vivo.com.br/vo">13/08/2005</DataFim>
  <Classificacao xmlns:vo="fidelizacao.fo.vivo.com.br/vo">1</Classificacao>
  <Grupo xsi:nil="true" xmlns:vo="fidelizacao.fo.vivo.com.br/vo" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"/>
  <CustoPonto xmlns:vo="fidelizacao.fo.vivo.com.br/vo"/>
  <Oferta xmlns:vo="fidelizacao.fo.vivo.com.br/vo">100002155</Oferta>
  <TipoCliente xmlns:vo="fidelizacao.fo.vivo.com.br/vo">1</TipoCliente>
  <Operadora xmlns:vo="fidelizacao.fo.vivo.com.br/vo">11</Operadora>
  <Plano xmlns:vo="fidelizacao.fo.vivo.com.br/vo"/>
  <IdRelatorio xmlns:vo="fidelizacao.fo.vivo.com.br/vo">1</IdRelatorio>
  */
	
  	get_tag(parm,dnode,"IdRelatorio",0,0);
	idRelatorio=atoi(parm);

    get_tag(stFiltro.szDataIni,dnode,"DataInicio",0,0);

	get_tag(stFiltro.szDataFim,dnode,"DataFim",0,0);

	get_tag(stFiltro.szClassificacao,dnode,"Classificacao",0,0);

	get_tag(stFiltro.szGrupo,dnode,"Grupo",0,0);

	get_tag(stFiltro.szCustoPonto,dnode,"CustoPonto",0,0);

	get_tag(stFiltro.szTipoCliente,dnode,"TipoCliente",0,0);

	get_tag(stFiltro.szPlano,dnode,"Plano",0,0);

	get_tag(stFiltro.szOperadora,dnode,"idsOperadoras",0,0);

	get_tag(stFiltro.szLogin,dnode,"Operador",0,0);

	if(idRelatorio==5)
	{
		get_tag(stFiltro.szOferta,dnode,"Oferta",0,0);
	}

	gerarWhere(stFiltro,szFiltro,idRelatorio);

		
	
	
 switch(idRelatorio) 
 {
	case REL_RETENCAO:
		rel_retencao(szFiltro,xml_g, stFiltro);
	break;

	case REL_RET_OFERTA:
		rel_retoferta(szFiltro,xml_g, stFiltro);
	break;

	case REL_MOV_DIARIAS:
		rel_movdiaria(szFiltro, xml_g);
	break;

	case REL_LIG_RETENCAO:
		rel_ligretencao(szFiltro, xml_g);
	break;

	case REL_OPERADOR:
		rel_operador(szFiltro, xml_g, stFiltro);
	break;

	case REL_RETENCAODISTINCAO:
		rel_retencaoDistincao(szFiltro,xml_g, stFiltro);
	break;

	case REL_TODAS_OFERTAS:
		rel_ofertas(szFiltro, xml_g, stFiltro);
	break;

	case REL_RES_DESTINO:
		rel_resdestino(szFiltro,xml_g, stFiltro);
	break;

	case REL_RES_DEST_DISTINCAO:
		rel_resdestinodistincao(szFiltro, xml_g, stFiltro);
	break;

	case REL_CTRL_CREDITO:
		rel_ctrlcredito(szFiltro, xml_g);
	break;

	case REL_RET_PLANOS:
		ULOG("RelFidFactory - GetRelatorio REL_RET_PLANOS");
		rel_planos(szFiltro, xml_g, stFiltro);	
	break;

	case REL_RET_PLAN_DISTINCAO:
		rel_planosdistincao(szFiltro, xml_g, stFiltro);
	break;
	case REL_OFERTAS_TOT_MENSAL:
		rel_ofertatotalmensal(szFiltro, xml_g);
	break;
	case REL_OFERTAS_DIA_PONTOS:
		rel_retencao(szFiltro,xml_g, stFiltro);
	break;
	case REL_NOTES_LOJA:
		rel_notesloja(szFiltro, xml_g);
	break;
	default:
		ULOG("Nenhum relatorio selecionado...Erro Fatal");
		throw new TuxBasicSvcException(NOKFID,"Nenhum relatorio selecionado...Erro Fatal!");
	
	}

  setStatusCode(OKFID,OKMSG);
  ULOG("RELRETENCAO");
  

}


