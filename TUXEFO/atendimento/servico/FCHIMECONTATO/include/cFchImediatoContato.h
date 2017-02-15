/**
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.6 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:15 $
 **/

#include <tuxfw.h>

// Classes de apoio.
#include "../../AgrupamentoEstadoTpProc/include/cWFAgrupamentoEstadoTpProc.h"
#include "../../AgrupamentoEstadoTpProc/include/stWFAgrupamentoEstadoTpProc.h"

// Classes usadas no negócio.
#include "../../Atendimento/include/cWFAtendimento.h"
#include "../../AtendimentoBaixaHistorico/include/cWFAtendimentoBaixaHistorico.h"
#include "../../AtendimentoBaixaAtual/include/cWFAtendimentoBaixaAtual.h"
#include "../../Andamento/include/cWFAndamento.h"
#include "../../AndamentoObservacao/include/cWFAndamentoObservacao.h"
#include "../../AtendimentoFechamento/include/cWFAtendimentoFechamento.h"
#include "../../AtendimentoAndamentoAtual/include/cWFAtendimentoAndamentoAtual.h"
#include "../../AtendimentoNivel/include/cWFAtendimentoNivel.h"
#include "../../AtendimentoGrupoAtual/include/cWFAtendimentoGrupoAtual.h"

// Declarações das estruturas usadas.
#include "../../AtendimentoBaixaHistorico/include/stWFAtendimentoBaixaHistorico.h"
#include "../../AtendimentoBaixaAtual/include/stWFAtendimentoBaixaAtual.h"
#include "../../Andamento/include/stWFAndamento.h"
#include "../../AndamentoObservacao/include/stWFAndamentoObservacao.h"
#include "../../AtendimentoFechamento/include/stWFAtendimentoFechamento.h"
#include "../../AtendimentoAndamentoAtual/include/stWFAtendimentoAndamentoAtual.h"
#include "../../AtendimentoNivel/include/stWFAtendimentoNivel.h"

// Declarações do próprio negócio.
#include "cFchImediatoContatoPC.h"
#include "stFchImediatoContato.h"
#include "cMsgErro.h"

class cFchImediatoContato
{
	public:
		st_FchImediatoContato* dados;
        int proximoAgrupamento;

		cFchImediatoContato(st_FchImediatoContato* entrada,XMLDPR *xmldpr);
		bool registra();

	private:
        XMLDPR *xmlDpr;

	private:
		// Funções principais da classe.
		int  gravaAtendimentoBaixaHistorico(char* dataAtual, long idAndamento);
		void gravaMensagemBaixa(char* dataAtual, int* idBaixaHistorico);
		void gravaBaixaAtual(char* dataAtual, int* idBaixaHistorico);
		
        long gravaAndamento(char* dataAtual);
		
        void gravaAndamentoObservacao(char* dataAtual, long idAndamento);
		void gravaAtendimentoFechamento(char* dataAtual, long idAndamento);
		void gravaAtendimentoAndamentoAtual(char* dataAtual, long idAndamento);
		void gravaAtendimentoNivel(char* dataAtual);
        void alterarAtendimento(long  _idAtendimento,int _idUsuarioBKO);
        void gravaAtendimentoGrupoAtual(const char* _dataAtual,const char *_dataEntrada
                                       ,long  _idAtendimento,int _idGrupo
                                       ,int _idSequencia,int _idUsuarioBKO);

};
