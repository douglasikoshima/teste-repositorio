/**
 * @author  David Ramos Dominguez
 * @version $Revision: 1.1.2.3 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:17 $
 **/

#include "../../../commons/msgPadrao.h"
#include "../include/cWF_CERRAMERET.h"
#include "../include/cWF_CERRAMERETPC.h"

// Classes de apoio.
#include"../../../commons/Collection/include/Collection.h"

#include"../../Andamento/include/cWFAndamento.h"
#include"../../AndamentoObservacao/include/cWFAndamentoObservacao.h"
#include"../../AndamentoTransferencia/include/cWFAndamentoTransferencia.h"

#include"../../Atendimento/include/cWFAtendimento.h"
#include"../../AtendimentoAndamentoAtual/include/cWFAtendimentoAndamentoAtual.h"
#include"../../AtendimentoBaixaAtual/include/cWFAtendimentoBaixaAtual.h"
#include"../../AtendimentoBaixaHistorico/include/cWFAtendimentoBaixaHistorico.h"
#include"../../AtendimentoFechamento/include/cWFAtendimentoFechamento.h"
#include"../../AtendimentoFrm/include/cWFAtendimentoFrm.h"
#include"../../AtendimentoFrmCampo/include/cWFAtendimentoFrmCampo.h"
#include"../../AtendimentoGrupoAtual/include/cWFAtendimentoGrupoAtual.h"
#include"../../AtendimentoNivel/include/cWFAtendimentoNivel.h"
#include"../../AtendimentoLinha/include/cWFAtendimentoLinha.h"
#include"../../AtendimentoPessoa/include/cWFAtendimentoPessoa.h"
#include"../../AtendimentoSuspeito/include/cWFAtendimentoSuspeito.h"
#include"../../AtendimentoUsuarioAtual/include/cWFAtendimentoUsuarioAtual.h"
#include"../../AtendimentoUsuarioDevolucao/include/cWFAtendUsuarioDevolucao.h"
#include"../../AtendimentoGrupoDevolucao/include/cWFAtendimentoGrupoDevolucao.h"
#include"../../CancelamentoSolicitado/include/cWFCancelamentoSolicitado.h"

#include "../../IBWFPSUsuario/include/cWFIBWFPSUsuario.h"

extern bool proCPesquisaGrupoFaseRetorno(st_VariaveisUsuario* _dados, Collection* _grupos)
extern bool proCPesquisaGrupoFase(st_VariaveisUsuario* _dados, Collection** _grupos);

cWF_CERRAMERET::cWF_CERRAMERET(XMLGen*xml_g)
{
	saida = xml_g;
}

/**
	Registra todos os dados do atendimento.
*/
void cWF_CERRAMERET::Execute()
{
    ULOG_START("cWF_CERRAMERET::Execute()");

	char dataAtual[64];
	char dataAndamento[64];
	char* p;
	int discador;
	int idGrupoAtd = 0;
	dataAtual[0] = 0;
	dataAndamento[0]=0;
	cWF_CERRAMERETPC rcpc;

	int idTipoPessoa;

	ULOG("Atendimento: %d", dados.idAtendimento);
	ULOG("idFase: %d", dados.idFase);
	ULOG("Id Atividade: %d", dados.idAtividade);

	rcpc.dataAtual(dataAtual);
	rcpc.dataAndamento(dataAndamento);

	discador=1;

	// obtener grupo
	st_AtendimentoGrupoAtual m_stDadosGrup;
	st_vlAtendimentoGrupoAtual m_vlDadosGrup;
	XMLGen xmlObtemAtGrAt;

	memset(&m_stDadosGrup, 0, sizeof(m_stDadosGrup));
	memset(&m_vlDadosGrup,-1, sizeof(m_vlDadosGrup));
	m_stDadosGrup.idAtendimento=dados.idAtendimento;
	m_vlDadosGrup.idAtendimento=1;

	cWFAtendimentoGrupoAtual AtendimentoGrupoAtual(&m_stDadosGrup, &m_vlDadosGrup, &xmlObtemAtGrAt);

	AtendimentoGrupoAtual.ObtemGrAt();

	if (wta.walkTreeXMLGen(&xmlObtemAtGrAt, "idGrupo", &p, 0)==0)
	{
		idGrupoAtd= atoi(p);
		XMLString::release(&p);			
	}	

	idFaseSig = dados.idFase + 1;

	ULOG("Atendimento: %d", dados.idAtendimento);
	ULOG("idFase: %d", dados.idFase);

	//Obtener fase atual
	st_Atendimento m_stDadosAtendimento;
	st_vlAtendimento m_vlDadosAtendimento;
	XMLGen xmlAtendimento;

	memset(&m_stDadosAtendimento, 0, sizeof(m_stDadosAtendimento));
	memset(&m_vlDadosAtendimento,-1, sizeof(m_vlDadosAtendimento));
	m_stDadosAtendimento.idAtendimento=dados.idAtendimento;
	m_vlDadosAtendimento.idAtendimento=1;

	cWFAtendimento Atendimento;

	Atendimento.ObtemDetalheAtend(dados.idAtendimento, &xmlAtendimento);
	if (wta.walkTreeXMLGen(&xmlAtendimento, "idSegmentacao", &p, 0)==0)
	{
		dados.idSegmentacao= atoi(p);
		XMLString::release(&p);			
	}
	if (wta.walkTreeXMLGen(&xmlAtendimento, "idTipoCarteira", &p, 0)==0)
	{
		dados.idTipoCarteira= atoi(p);
		XMLString::release(&p);			
	}
	ULOG("idTipoCarteira: %d", dados.idTipoCarteira);
	if (wta.walkTreeXMLGen(&xmlAtendimento, "idProcedencia", &p, 0)==0)
	{
		dados.idProcedencia= atoi(p);
		XMLString::release(&p);			
	}
	ULOG("idProcedencia: %d", dados.idProcedencia);
	if (wta.walkTreeXMLGen(&xmlAtendimento, "idContato", &p, 0)==0)
	{
		dados.idContato= atoi(p);
		XMLString::release(&p);			
	}
	if (wta.walkTreeXMLGen(&xmlAtendimento, "idGrupoAbertura", &p, 0)==0)
	{
		dados.idGrupoAbertura= atoi(p);
		XMLString::release(&p);			
	}
	if (wta.walkTreeXMLGen(&xmlAtendimento, "idCanal", &p, 0)==0)
	{
		dados.idCanal= atoi(p);
		XMLString::release(&p);			
	}

	// Obteniendo Atendimento Pessoa
	st_AtendimentoPessoa m_stDadosAtendimentoPessoa;
	st_vlAtendimentoPessoa m_vlDadosAtendimentoPessoa;
	XMLGen xmlAtendimentoPessoa;

	memset(&m_stDadosAtendimentoPessoa, 0, sizeof(m_stDadosAtendimentoPessoa));
	memset(&m_vlDadosAtendimentoPessoa,-1, sizeof(m_vlDadosAtendimentoPessoa));
	m_stDadosAtendimentoPessoa.idAtendimento=dados.idAtendimento;
	m_vlDadosAtendimentoPessoa.idAtendimento=1;

	cWFAtendimentoPessoa AtendimentoPessoa(&m_stDadosAtendimentoPessoa, &m_vlDadosAtendimentoPessoa, &xmlAtendimentoPessoa);

	AtendimentoPessoa.ObtemAtendPessoa();

	if (wta.walkTreeXMLGen(&xmlAtendimentoPessoa, "idTipoPessoa", &p, 0)==0)
	{
		idTipoPessoa = atoi(p);		
		dados.idTipoPessoa= atoi(p);		
		XMLString::release(&p);			
	}
	else
		dados.idTipoPessoa= 1;

	if (wta.walkTreeXMLGen(&xmlAtendimentoPessoa, "inRspAbertura", &p, 0)==0)
	{
		dados.idTipoRel= atoi(p);		
		XMLString::release(&p);			
	}
	else
		m_stDadosAtendimentoPessoa.idTipoRelacionamento= 1;

	m_vlDadosAtendimentoPessoa.idTipoRelacionamento= 1;
	ULOG("idTipoPessoa: %d", dados.idTipoPessoa);
	ULOG("idTipoRel: %d", dados.idTipoRel);

	st_AtendimentoLinha m_stDadosAtendimentoLinha;
	st_vlAtendimentoLinha m_vlDadosAtendimentoLinha;
	XMLGen xmlAtendimentoLinha;

	memset(&m_stDadosAtendimentoLinha, 0, sizeof(m_stDadosAtendimentoLinha));
	memset(&m_vlDadosAtendimentoLinha,-1, sizeof(m_vlDadosAtendimentoLinha));
	m_stDadosAtendimentoLinha.idAtendimento=dados.idAtendimento;
	m_vlDadosAtendimentoLinha.idAtendimento=1;

	cWFAtendimentoLinha AtendimentoLinha(&m_stDadosAtendimentoLinha, &m_vlDadosAtendimentoLinha, &xmlAtendimentoLinha);

	AtendimentoLinha.ObtemDetalheAtend();

	if (wta.walkTreeXMLGen(&xmlAtendimentoLinha, "idPessoaLinhaHistorico", &p, 0)==0)
	{
		m_stDadosAtendimentoLinha.idPessoaLinhaHistorico= atol(p);
		m_vlDadosAtendimentoLinha.idPessoaLinhaHistorico= 0;
		XMLString::release(&p);			
	}
	ULOG("idPessoaLinhaHistorico: %ld", m_stDadosAtendimentoLinha.idPessoaLinhaHistorico);

	if (m_stDadosAtendimentoLinha.idPessoaLinhaHistorico > 0)
		dados.idTipoLinea=rcpc.inclusaoBaixaHistorico(m_stDadosAtendimentoLinha.idPessoaLinhaHistorico);

	if (discador==1)
	{
		// Recuperando siguiente grupo para el proceso

		st_VariaveisUsuario dadosUsuario;
		Collection *resultadoUsuario;
		
		memset(&dadosUsuario, 0, sizeof(dadosUsuario));
		dadosUsuario.idContato = dados.idContato;
		//Se o Tipo de retorno for 1 (Com Retorno Grupo BKO) entao usa os grupos da fase de tratamento (idFase = 2)
		//Se o Tipo de retorno for 2 (Com Retorno Grupo de Retorno) entao usa os grupos da fase de tratamento (idFase = 3)
		if( idTipoRetornoContato > 0 )
			dadosUsuario.idFase = idTipoRetornoContato == 1 ? 2 : 3;
		else //Se nao tem tipo de retorno entao nao muda a fase
			dadosUsuario.idFase = idFaseSig;
		dadosUsuario.idSegmentacao=dados.idSegmentacao;
		dadosUsuario.idTipoCarteira=dados.idTipoCarteira;
		dadosUsuario.idProcedencia=dados.idProcedencia;
		dadosUsuario.idGrupoAbertura=dados.idGrupoAbertura;
		dadosUsuario.idTipoPessoa=dados.idTipoPessoa;
		dadosUsuario.idTipoLinha=dados.idTipoLinea;
		dadosUsuario.idTipoRelacionamento=dados.idTipoRel,
		dadosUsuario.idCanal=dados.idCanal;
			
		ULOG("idTipoRetornoContato: %d", dados.idTipoRetornoContato);
		ULOG("idContato: %d", dadosUsuario.idContato);
		ULOG("idFase: %d", dadosUsuario.idFase);
		ULOG("idSegmentacao: %d", dadosUsuario.idSegmentacao);
		ULOG("idTipoCarteira: %d", dadosUsuario.idTipoCarteira);
		ULOG("idProcedencia: %d", dadosUsuario.idProcedencia);
		ULOG("idGrupoAbertura: %d", dadosUsuario.idGrupoAbertura);
		ULOG("idTipoPessoa: %d", dadosUsuario.idTipoPessoa);
		ULOG("idTipoLinha: %d", dadosUsuario.idTipoLinha);
		ULOG("idTipoRelacionamento: %d", dadosUsuario.idTipoRelacionamento);
		ULOG("idCanal: %d", dadosUsuario.idCanal);

		proCPesquisaGrupoFaseRetorno(&dadosUsuario, &resultadoUsuario);
	
		char *idGrupoFase;
		idGrupoFase = (char*) resultadoUsuario->GetItem(0);
		
		if (strcmp(idGrupoFase, "") == 0)
		{
			ULOG("Grupo valido para as variaveis: %s", idGrupoFase);
			ULOG("Grupo valido para as variaveis vacio");

			proCPesquisaGrupoFase(&dadosUsuario, &resultadoUsuario);

			idGrupoFase = (char*) resultadoUsuario->GetItem(0);
			
			if (strcmp(idGrupoFase, "") == 0)
			{
				ULOG("Grupo valido para a fase vacio");
				saida->createTag("WFAcaoRetornoVO");
				saida->addProp("xmlns","workflow.fo.vivo.com.br/vo");
				saida->addItem("acaoExecucao", "M");
				saida->addItem("mensagem", "\nProcesso(s) aberto(s) para 'Retorno por Grupo de Retorno'. Favor informar ao administrador para cadastrar o Grupo de Retorno: ");
				saida->addItem("urlDestino", dados.urlDestino);
				saida->closeTag();
				return;
			}
			ULOG("Grupo valido para a fase: %s", idGrupoFase);
		}
		m_stDadosGrup.idGrupo=atoi(idGrupoFase);
		m_vlDadosGrup.idGrupo=1;
		m_stDadosGrup.idUsuarioAlteracao=dados.UsuarioAtual;
		m_vlDadosGrup.idUsuarioAlteracao=1;
		strcpy(m_stDadosGrup.dtUltimaAlteracao, dataAtual);
		m_vlDadosGrup.dtUltimaAlteracao=1;

		cWFAtendimentoGrupoAtual AtdGrupoAtual(&m_stDadosGrup, &m_vlDadosGrup, &xmlObtemAtGrAt);

		AtdGrupoAtual.alterar();
		//delete idGrupo;
		//delete idGrupoFase;

		// obtener usuario
		st_AtendimentoUsuarioAtual m_stDadosUsr;
		st_vlAtendimentoUsuarioAtual m_vlDadosUsr;
		XMLGen xmlObtemAtUsrAt;

		memset(&m_stDadosUsr, 0, sizeof(m_stDadosUsr));
		memset(&m_vlDadosUsr,-1, sizeof(m_vlDadosUsr));
		m_stDadosUsr.idAtendimento=dados.idAtendimento;
		m_vlDadosUsr.idAtendimento=1;
		m_vlDadosUsr.idPessoaUsuario=-1;

		cWFAtendimentoUsuarioAtual AtendimentoUsuarioAtual(&m_stDadosUsr, &m_vlDadosUsr, &xmlObtemAtUsrAt);

		AtendimentoUsuarioAtual.consultar();

		if (wta.walkTreeXMLGen(&xmlObtemAtUsrAt, "idPessoaUsuario", &p, 0)==0)
		{
			m_stDadosUsr.idPessoaUsuario= atoi(p);
			m_vlDadosUsr.idPessoaUsuario= 1;
			XMLString::release(&p);			
		}	

		ULOG("Obtemos idPessoaUsuario: %d", m_stDadosUsr.idPessoaUsuario);
		if (m_vlDadosUsr.idPessoaUsuario != -1)
		{
			ULOG("Excluimos Usuario Atual");
			AtendimentoUsuarioAtual.excluir();		

			st_AndamentoTransferencia m_stDadosTran;
			st_vlAndamentoTransferencia m_vlDadosTran;
			XMLGen xmlUltAndTrf;

			memset(&m_stDadosTran, 0, sizeof(m_stDadosTran));
			memset(&m_vlDadosGrup,-1, sizeof(m_vlDadosGrup));
			m_stDadosTran.idAtendimento=dados.idAtendimento;
			m_vlDadosTran.idAtendimento=1;
			m_vlDadosTran.idAndamento=-1;
			m_vlDadosTran.dtFinTransferencia=-1;

			cWFAndamentoTransferencia AndamentoTransferencia(&m_stDadosTran, &m_vlDadosTran, &xmlUltAndTrf);

			
			ULOG("Obtemos ultimo Andamanto Transferencia");

			AndamentoTransferencia.obtemTrf();

			if (wta.walkTreeXMLGen(&xmlUltAndTrf, "idAndamento", &p, 0 )==0)
			{
				m_stDadosTran.idAndamento=atol(p);
				m_vlDadosTran.idAndamento=1;
				XMLString::release(&p);			
			}

			ULOG("IdAndamento: %ld", m_stDadosTran.idAndamento);
			if (m_vlDadosTran.idAndamento != -1)
			{
				strcpy(m_stDadosTran.dtFinTransferencia,dataAtual);
				m_vlDadosTran.dtFinTransferencia=1;
				m_vlDadosTran.dtInicioTransferencia=-1;
				m_vlDadosTran.idPessoaUsuario=-1;

				cWFAndamentoTransferencia AndamentoTransferencia(&m_stDadosTran, &m_vlDadosTran, &xmlUltAndTrf);

				ULOG("Alteramos ultimo Andamanto Transferencia");
				AndamentoTransferencia.alterar();
			}
		}
	}

	//************* Inclusao do Andamento ********************;

	st_Andamento m_stDadosAndamento;
	st_vlAndamento m_vlDadosAndamento;
	XMLGen AndamentoIns;

	memset(&m_stDadosAndamento, 0, sizeof(m_stDadosAndamento));
	memset(&m_vlDadosAndamento,-1, sizeof(m_vlDadosAndamento));
	m_stDadosAndamento.idAtividade=dados.idAtividade;
	m_vlDadosAndamento.idAtividade=1;
	m_stDadosAndamento.idAgrupamentoEstadoTpProc=dados.idAgrEstTPrFt;
	m_vlDadosAndamento.idAgrupamentoEstadoTpProc=1;
	m_stDadosAndamento.idAtendimento=dados.idAtendimento;
	m_vlDadosAndamento.idAtendimento=1;
	m_stDadosAndamento.idGrupo=idGrupoAtd;
	m_vlDadosAndamento.idGrupo=1;
	m_stDadosAndamento.idPessoaUsuario=dados.UsuarioAtual;
	m_vlDadosAndamento.idPessoaUsuario=1;
	strcpy(m_stDadosAndamento.dtAndamento,dataAndamento);
	m_vlDadosAndamento.dtAndamento=1;
	m_stDadosAndamento.idUsuarioAlteracao=dados.UsuarioAtual;
	m_vlDadosAndamento.idUsuarioAlteracao=1;
	strcpy(m_stDadosAndamento.dtUltimaAlteracao,dataAtual);
	m_vlDadosAndamento.dtUltimaAlteracao=1;

	cWFAndamento Andamento(&m_stDadosAndamento, &m_vlDadosAndamento, &AndamentoIns);

	Andamento.incluir();

	if (wta.walkTreeXMLGen(&AndamentoIns, "idAndamento", &p, 0)==0)
	{
		m_stDadosAndamento.idAndamento = atol(p);
		m_vlDadosAndamento.idAndamento = 1;
		XMLString::release(&p);			
	}
	stBaixaHistorico datosBaixaHistorico;

	if (p=tx.walkTree(dados.domEncerrar, "idBaixa", 0 ),p)
	{
		datosBaixaHistorico.idBxa=atoi(p);
		XMLString::release(&p);
	}

	if ( datosBaixaHistorico.idBxa > 0 )
	{
		datosBaixaHistorico.idAtendimento=dados.idAtendimento;

		st_AtendimentoBaixaHistorico m_stDadosHist;
		st_vlAtendimentoBaixaHistorico m_vlDadosHist;
		XMLGen baixaHistorico;

		memset(&m_stDadosHist, 0, sizeof(m_stDadosHist));
		memset(&m_vlDadosHist,-1, sizeof(m_vlDadosHist));
		m_stDadosHist.idAtendimento=dados.idAtendimento;
		m_vlDadosHist.idAtendimento=1;
		m_stDadosHist.idBaixa= datosBaixaHistorico.idBxa;
		m_vlDadosHist.idBaixa=1;
		m_stDadosHist.idFase=dados.idFase;
		m_vlDadosHist.idFase=1;
		m_stDadosHist.idPessoaUsuario=dados.UsuarioAtual;
		m_vlDadosHist.idPessoaUsuario=1;
        m_stDadosHist.idAndamento=m_stDadosAndamento.idAndamento;
        m_vlDadosHist.idAndamento=1;
		m_stDadosHist.idUsuarioAlteracao=dados.UsuarioAtual;
		m_vlDadosHist.idUsuarioAlteracao=1;
		strcpy(m_stDadosHist.dtUltimaAlteracao, dataAtual);
		m_vlDadosHist.dtUltimaAlteracao=1;
		strcpy(m_stDadosHist.dtBaixa, dataAtual);
		m_vlDadosHist.dtBaixa=1;

		stMensagemBaixa datosMensagemBaixa;

		if (p=tx.walkTree(dados.domEncerrar, "idBaixaMensagem", 0 ),p)
		{
			datosMensagemBaixa.idMsgBxa=atoi(p);
			XMLString::release(&p);
		}

	ULOG("Dads a inserir como Baixa Historico");
	ULOG("idAtendimento: %d", m_stDadosHist.idAtendimento);
	ULOG("idBaixa: %d", m_stDadosHist.idBaixa);
	ULOG("idFase: %d", m_stDadosHist.idFase);
	ULOG("idPessoaUsuario: %d", m_stDadosHist.idPessoaUsuario);
		cWFAtendimentoBaixaHistorico AtendimentoBaixaHistorico(&m_stDadosHist, &m_vlDadosHist, &baixaHistorico );
		AtendimentoBaixaHistorico.incluir();
		
		//********************** Inclusão do Mensagem de Baixa ********************** 


 		if (wta.walkTreeXMLGen(&baixaHistorico, "idAtendimentoBaixaHistorico", &p, 0 )==0)
		{
			m_stDadosHist.idAtendimentoBaixaHistorico= atol(p);
			m_vlDadosHist.idAtendimentoBaixaHistorico=1;
			XMLString::release(&p);			
		}

		ULOG("idAtendimentoBaixaHistorico: %d", m_stDadosHist.idAtendimentoBaixaHistorico);
		ULOG("Baixa Mensagem a Incluir: %d",  datosMensagemBaixa.idMsgBxa);
		if (datosMensagemBaixa.idMsgBxa>0)
		{
			datosMensagemBaixa.idBxa=datosBaixaHistorico.idBxa;
			rcpc.sacaridBaixaHistorico(&datosMensagemBaixa);

			m_stDadosHist.idBaixaMensagem=datosMensagemBaixa.idBaixaMensagem;
			m_vlDadosHist.idBaixaMensagem=1;

			cWFAtendimentoBaixaHistorico AtendimentoBaixaHistorico(&m_stDadosHist, &m_vlDadosHist, &baixaHistorico );

			ULOG("Incluimos AtendimentoBaixaMensagem");
			AtendimentoBaixaHistorico.incluirBaixaMensagem();
		}

		int total = rcpc.TotalAtendimentoBaixaActual(m_stDadosHist.idAtendimento);

		st_AtendimentoBaixaAtual m_stDadosBaixaAtual;
		st_vlAtendimentoBaixaAtual m_vlDadosBaixaAtual;
		XMLGen baixa;

		memset(&m_stDadosBaixaAtual, 0, sizeof(m_stDadosBaixaAtual));
		memset(&m_vlDadosBaixaAtual,-1, sizeof(m_vlDadosBaixaAtual));
		m_stDadosBaixaAtual.idAtendimento=dados.idAtendimento;
		m_vlDadosBaixaAtual.idAtendimento=1;
		strcpy(m_stDadosBaixaAtual.dtUltimaAlteracao, dataAtual);
		m_vlDadosBaixaAtual.dtUltimaAlteracao=1;
		m_stDadosBaixaAtual.idAtendimentoBaixaHistorico=m_stDadosHist.idAtendimentoBaixaHistorico;
		m_vlDadosBaixaAtual.idAtendimentoBaixaHistorico=1;
		m_stDadosBaixaAtual.idUsuarioAlteracao=dados.UsuarioAtual;
		m_vlDadosBaixaAtual.idUsuarioAlteracao=1;

		cWFAtendimentoBaixaAtual AtendimentoBaixaAtual(&m_stDadosBaixaAtual, &m_vlDadosBaixaAtual, &baixa);

		if (total==0)
        {
 			ULOG("Incluimos AtendimentoBaixaAtual");
			AtendimentoBaixaAtual.incluir();
        }
		else
        {
 			ULOG("Alteramos AtendimentoBaixaAtual");
			AtendimentoBaixaAtual.alterar();
        }
	}
	st_AtendimentoSuspeito m_stDadosAtenSuspeito;
	st_vlAtendimentoSuspeito m_vlDadosAtenSuspeito;
	XMLGen ExcSusp;

	memset(&m_stDadosAtenSuspeito, 0, sizeof(m_stDadosAtenSuspeito));
	memset(&m_vlDadosAtenSuspeito,-1, sizeof(m_vlDadosAtenSuspeito));
	m_stDadosAtenSuspeito.idAtendimento=dados.idAtendimento;
	m_vlDadosAtenSuspeito.idAtendimento=1;

	cWFAtendimentoSuspeito AtendimentoSuspeito(&m_stDadosAtenSuspeito, &m_vlDadosAtenSuspeito, &ExcSusp);
	AtendimentoSuspeito.excluir();

	st_CancelamentoSolicitado m_stDadosCancelamentoSolicitado;
	st_vlCancelamentoSolicitado m_vlDadosCancelamentoSolicitado;
	XMLGen xmlCancela;

	memset(&m_stDadosCancelamentoSolicitado, 0, sizeof(m_stDadosCancelamentoSolicitado));
	memset(&m_vlDadosCancelamentoSolicitado,-1, sizeof(m_vlDadosCancelamentoSolicitado));
	m_stDadosCancelamentoSolicitado.idAtendimento=dados.idAtendimento;
	m_vlDadosCancelamentoSolicitado.idAtendimento=1;

	cWFCancelamentoSolicitado CancelamentoSolicitado(&m_stDadosCancelamentoSolicitado, &m_vlDadosCancelamentoSolicitado, &xmlCancela);
	CancelamentoSolicitado.excluir();

	st_AtendimentoNivel m_stDadosAtendimentoNivel;
	st_vlAtendimentoNivel m_vlDadosAtendimentoNivel;
	XMLGen AtdNivel;

	memset(&m_stDadosAtendimentoNivel, 0, sizeof(m_stDadosAtendimentoNivel));
	memset(&m_vlDadosAtendimentoNivel,-1, sizeof(m_stDadosAtendimentoNivel));
	m_stDadosAtendimentoNivel.idAtendimento=dados.idAtendimento;
	m_vlDadosAtendimentoNivel.idAtendimento=1;
	m_stDadosAtendimentoNivel.idGrupo=idGrupoAtd;
	m_vlDadosAtendimentoNivel.idGrupo=1;
	m_stDadosAtendimentoNivel.idFase=dados.idFase;
	m_vlDadosAtendimentoNivel.idFase=1;
	m_stDadosAtendimentoNivel.idAtividade=dados.idAtividade;
	m_vlDadosAtendimentoNivel.idAtividade=1;
	strcpy(m_stDadosAtendimentoNivel.dtNivel, dataAndamento);
	m_vlDadosAtendimentoNivel.dtNivel=1;
	m_stDadosAtendimentoNivel.idUsuarioAlteracao=dados.UsuarioAtual;
	m_vlDadosAtendimentoNivel.idUsuarioAlteracao=1;
	strcpy(m_stDadosAtendimentoNivel.dtUltimaAlteracao, dataAtual);
	m_vlDadosAtendimentoNivel.dtUltimaAlteracao=1;

	cWFAtendimentoNivel AtendimentoNivel(&m_stDadosAtendimentoNivel, &m_vlDadosAtendimentoNivel, &AtdNivel);
	
	AtendimentoNivel.incluir();

	// Inclui o Atendimento Grupo Receptor;  
	st_AtendimentoGrupoDevolucao m_stDadosGrupoDevolucao;
	st_vlAtendimentoGrupoDevolucao m_vlDadosGrupoDevolucao;
	XMLGen AtdGrpRecep;

	memset(&m_stDadosGrupoDevolucao, 0, sizeof(m_stDadosGrupoDevolucao));
	memset(&m_vlDadosGrupoDevolucao,-1, sizeof(m_vlDadosGrupoDevolucao));
	m_stDadosGrupoDevolucao.idAtendimento=dados.idAtendimento;
	m_vlDadosGrupoDevolucao.idAtendimento=1;
	m_stDadosGrupoDevolucao.idGrupo=idGrupoAtd;
	m_vlDadosGrupoDevolucao.idGrupo=1;

	cWFAtendimentoGrupoDevolucao AtendimentoGrupoDevolucao(&m_stDadosGrupoDevolucao, &m_vlDadosGrupoDevolucao, &AtdGrpRecep);

	AtendimentoGrupoDevolucao.incluir();

	st_AtendimentoUsuarioDevolucao m_stDadosUsuarioDevolucao;
	st_vlAtendimentoUsuarioDevolucao m_vlDadosUsuarioDevolucao;
	XMLGen AtdUsrRecep;

	memset(&m_stDadosUsuarioDevolucao, 0, sizeof(m_stDadosUsuarioDevolucao));
	memset(&m_vlDadosUsuarioDevolucao,-1, sizeof(m_vlDadosUsuarioDevolucao));
	m_stDadosUsuarioDevolucao.idAtendimento=dados.idAtendimento;
	m_vlDadosUsuarioDevolucao.idAtendimento=1;
	m_stDadosUsuarioDevolucao.idPessoaUsuario=dados.UsuarioAtual;
	m_vlDadosUsuarioDevolucao.idPessoaUsuario=1;

	cWFAtendimentoUsuarioDevolucao AtendimentoUsuarioDevolucao(&m_stDadosUsuarioDevolucao, &m_vlDadosUsuarioDevolucao, &AtdUsrRecep);

	AtendimentoUsuarioDevolucao.incluir();

	memset(&m_stDadosAtendimento, 0, sizeof(m_stDadosAtendimento));
	memset(&m_vlDadosAtendimento,-1, sizeof(m_vlDadosAtendimento));
	m_stDadosAtendimento.idAtendimento=dados.idAtendimento;
	m_vlDadosAtendimento.idAtendimento=1;
	m_stDadosAtendimento.idFase=idFaseSig;
	m_vlDadosAtendimento.idFase=1;
	m_stDadosAtendimento.nrNivel=0;
	m_vlDadosAtendimento.nrNivel=1;
	m_stDadosAtendimento.idUsuarioAlteracao=dados.UsuarioAtual;
	m_vlDadosAtendimento.idUsuarioAlteracao=1;

	cWFAtendimento Atd(&m_stDadosAtendimento, &m_vlDadosAtendimento, &xmlAtendimento);

	Atd.alterar();


	//************* Atualiza Andamento Atual ********************;

	st_AtendimentoAndamentoAtual m_stDadosAndamentoAtual;
	st_vlAtendimentoAndamentoAtual m_vlDadosAndamentoAtual;
	XMLGen AndAtual;

	memset(&m_stDadosAndamentoAtual, 0, sizeof(m_stDadosAndamentoAtual));
	memset(&m_vlDadosAndamentoAtual,-1, sizeof(m_vlDadosAndamentoAtual));
	m_stDadosAndamentoAtual.idAtendimento=dados.idAtendimento;
	m_vlDadosAndamentoAtual.idAtendimento=1;
	m_stDadosAndamentoAtual.idAndamento=m_stDadosAndamento.idAndamento;
	m_vlDadosAndamentoAtual.idAndamento=1;
	m_stDadosAndamentoAtual.idUsuarioAlteracao=dados.UsuarioAtual;
	m_vlDadosAndamentoAtual.idUsuarioAlteracao=1;
	strcpy(m_stDadosAndamentoAtual.dtUltimaAlteracao,dataAtual);
	m_vlDadosAndamentoAtual.dtUltimaAlteracao=1;

	cWFAtendimentoAndamentoAtual AtendimentoAndamentoAtual(&m_stDadosAndamentoAtual, &m_vlDadosAndamentoAtual, &AndAtual);

	AtendimentoAndamentoAtual.alterar();

	//********** Inclusao do Andamento Observacao *************

	st_AndamentoObservacao m_stDadosAndamentoObservacao;
	st_vlAndamentoObservacao m_vlDadosAndamentoObservacao;
	XMLGen Observacao;

	memset(&m_stDadosAndamentoObservacao, 0, sizeof(m_stDadosAndamentoObservacao));
	memset(&m_vlDadosAndamentoObservacao,-1, sizeof(m_vlDadosAndamentoObservacao));
	m_stDadosAndamentoObservacao.idAndamento=m_stDadosAndamento.idAndamento;
	m_vlDadosAndamentoObservacao.idAndamento=1;

	if (p=tx.walkTree(dados.domEncerrar, "dsComentario", 0 ),p)
	{
		strcpy(m_stDadosAndamentoObservacao.dsAndamentoObservacao, p);
		m_vlDadosAndamentoObservacao.dsAndamentoObservacao=1;
		XMLString::release(&p);
	}

	m_stDadosAndamentoObservacao.idUsuarioAlteracao=dados.UsuarioAtual;
	m_vlDadosAndamentoObservacao.idUsuarioAlteracao=1;
	strcpy(m_stDadosAndamentoObservacao.dtUltimaAlteracao, dataAtual);
	m_vlDadosAndamentoObservacao.dtUltimaAlteracao=1;

	cWFAndamentoObservacao AndamentoObservacao(&m_stDadosAndamentoObservacao, &m_vlDadosAndamentoObservacao, &Observacao);

	AndamentoObservacao.incluir();

	//********************** Inclusão Dos Campos e dos Valores dos Campos do Formulário ********************** 

	int contador=0;
	int contador2=0;
	int aux=0;	
	char* valor;

	long idAtendimentoFrm;

	DOMNode* domAux;
	DOMNode* domAux2;

	st_AtendimentoFrm m_stDadosAtendimentoFrm;
	st_vlAtendimentoFrm m_vlDadosAtendimentoFrm;
	XMLGen atdFrm;

	st_AtendimentoFrmCampo m_stDadosAtendimentoFrmCampo;
	st_vlAtendimentoFrmCampo m_vlDadosAtendimentoFrmCampo;
	XMLGen atdFrmValor;

    ULOG("Procedemos a recorrer el formulario");
	while (domAux= tx.walkDOM( dados.domFormulario, "FormularioCampoVO", contador++))
	{
        ULOG("Obtenemos el idContatoFolhaCampo");
		if (valor=tx.walkTree(domAux, "idContatoFolhaCampo", 0 ),valor)
		{
			if ( (atoi(valor)) > 0 )
			{			
				ULOG("Obtenemos el idCampo");
				memset(&m_stDadosAtendimentoFrm, 0, sizeof(m_stDadosAtendimentoFrm));
				memset(&m_vlDadosAtendimentoFrm,-1, sizeof(m_vlDadosAtendimentoFrm));
				m_stDadosAtendimentoFrm.idAtendimento=dados.idAtendimento;
				m_vlDadosAtendimentoFrm.idAtendimento=1;
				if (p=tx.walkTree(domAux, "idCampo", 0 ),p)
				{
					m_stDadosAtendimentoFrm.idCampo=atoi(p);
					m_vlDadosAtendimentoFrm.idCampo=1;
					XMLString::release(&p);
				}
				m_stDadosAtendimentoFrm.idUsuarioAlteracao=dados.UsuarioAtual;
				m_vlDadosAtendimentoFrm.idUsuarioAlteracao=1;
				strcpy(m_stDadosAtendimentoFrm.dtUltimaAlteracao, dataAtual);
				m_vlDadosAtendimentoFrm.dtUltimaAlteracao=1;

				cWFAtendimentoFrm AtendimentoFrm(&m_stDadosAtendimentoFrm, &m_vlDadosAtendimentoFrm, &atdFrm);

				ULOG("Incluimos el Formulario");
				idAtendimentoFrm = AtendimentoFrm.incluir();
				ULOG("Formulario incluso: [%d]", idAtendimentoFrm);
				
				ULOG("Obtenemos Formulario Campo Valor");
				while (domAux2= tx.walkDOM( domAux, "FormularioCampoValorVO", contador2++))
				{
					memset(&m_stDadosAtendimentoFrmCampo, 0, sizeof(m_stDadosAtendimentoFrmCampo));
					memset(&m_vlDadosAtendimentoFrmCampo,-1, sizeof(m_vlDadosAtendimentoFrmCampo));
					ULOG("Obtenemos Id de Formulario para el Atendimento");

				    m_stDadosAtendimentoFrmCampo.idAtendimentoFrm = idAtendimentoFrm;
					m_vlDadosAtendimentoFrmCampo.idAtendimentoFrm = 1;
					m_vlDadosAtendimentoFrmCampo.idDominio=-1;
					ULOG("Obtenemos Id Dominio");
					if (p=tx.walkTree(domAux2,"idFormularioCampoValor",0),p)
                    {
							m_stDadosAtendimentoFrmCampo.idDominio=atoi(p);
							m_vlDadosAtendimentoFrmCampo.idDominio=1;
							XMLString::release(&p);
					}
	
					if ( m_vlDadosAtendimentoFrmCampo.idDominio != 1 )
					{
						ULOG("Obtenemos Valor");
						if (p=tx.walkTree(domAux2,"valor",0),p)
						{
							strcpy(m_stDadosAtendimentoFrmCampo.dsValor,p);
							m_vlDadosAtendimentoFrmCampo.dsValor=1;
							XMLString::release(&p);
						}				
					}

					m_stDadosAtendimentoFrmCampo.idUsuarioAlteracao=dados.UsuarioAtual;
					m_vlDadosAtendimentoFrmCampo.idUsuarioAlteracao=1;
					strcpy(m_stDadosAtendimentoFrmCampo.dtUltimaAlteracao, dataAtual);
					m_vlDadosAtendimentoFrmCampo.dtUltimaAlteracao=1;

					ULOG("Incluimos el campo con los valores: idAtendimentoFrm: [%d] idDominio: [%d] valor: [%s] ", m_stDadosAtendimentoFrmCampo.idAtendimentoFrm, m_stDadosAtendimentoFrmCampo.idDominio, m_stDadosAtendimentoFrmCampo.dsValor);
					cWFAtendimentoFrmCampo AtendimentoFrmCampo(&m_stDadosAtendimentoFrmCampo, &m_vlDadosAtendimentoFrmCampo, &atdFrmValor);
	
					AtendimentoFrmCampo.incluir();
				}
				contador2 = 0;
			}
			XMLString::release(&valor);
		}
	}
	
    ULOG("URL de Salida: %s", dados.urlDestino);
	saida->createTag("WFAcaoRetornoVO");
	saida->addProp("xmlns","workflow.fo.vivo.com.br/vo");
	saida->addItem("acaoExecucao", "S");
	saida->addItem("mensagem", "Encerramento Concluido");
	saida->addItem("urlDestino", dados.urlDestino);
	saida->closeTag();

    ULOG_START("cWF_CERRAMERET::Execute()");
}
