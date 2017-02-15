/**
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.6 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:15 $
 **/

#ifndef __C_ENC_CONTATO_H__
#define __C_ENC_CONTATO_H__

#include <tuxfw.h>

// Classes de apoio.
#include "../../../commons/Collection/include/Collection.h"

#include "../../AgrupamentoEstadoTpProc/include/cWFAgrupamentoEstadoTpProc.h"
#include "../../AgrupamentoEstadoTpProc/include/stWFAgrupamentoEstadoTpProc.h"

#include "../../Usuario/include/stUsuario.h"

extern bool proCPesquisaGrupoFaseVariables(st_VariaveisUsuario* _dados,int &idGrupoDestino);
extern bool proCPesquisaGrupoFase(st_VariaveisUsuario* _dados,int &idGrupoDestino);

// função adicionada para atender a incidência de WR 3346
extern bool proCObtemUFOperadoraAtendimentoNaoCliente(long  _idAtendimento, int *_idUFOperadoraNaoCliente);

// Classes usadas no negócio.
#include "../../Andamento/include/cWFAndamento.h"
#include "../../AndamentoMotivo/include/cWFAndamentoMotivo.h"
#include "../../AndamentoObservacao/include/cWFAndamentoObservacao.h"
#include "../../Atendimento/include/cWFAtendimento.h"
#include "../../AtendimentoAndamentoAtual/include/cWFAtendimentoAndamentoAtual.h"
#include "../../AtendimentoGerarXMLDPR/include/stWFAtendimentoGerarXMLDPR.h"
#include "../../AtendimentoUsuarioAtual/include/cWFAtendimentoUsuarioAtual.h"
#include "../../AtendimentoGrupoAtual/include/cWFAtendimentoGrupoAtual.h"
#include "../../AtendimentoNivel/include/cWFAtendimentoNivel.h"
#include "../../AtdEncaminhadoIncorreto/include/cWFAtdEncaminhadoIncorreto.h"

// Declarações do próprio negócio.
#include "cEncContatoPC.h"
#include "stEncContato.h"
#include "cMsgErro.h"
#include "../../../commons/msgPadrao.h"


class cEncContato
{
	public:
		st_EncContato* dados;
        st_CRI   * dadosCRI;
        st_EncContatoPreValidacao *dadosValidacao;
        XMLDPR *xmlDpr;

	public:
		cEncContato(st_EncContato * entrada,XMLDPR *xmldpr);
        cEncContato(st_EncContato * origem,st_CRI *pCri,XMLDPR *xmldpr);
        cEncContato(st_EncContato * origem,st_CRI *pCri,st_EncContatoPreValidacao *pEncContatoValida,XMLDPR *xmldpr);

		bool registra();

	private:
        char dataAndamento[64];
	    cEncContatoPC ecpc;

		// Funções principais da classe.
		long gravaAndamento(char* dataAtual,int idAtividade,int idAgrupamentoEstadoTpProcFt,int idGrupoAtual);
		void gravaAndamentoObservacao(char* dataAtual, long idAndamento);
		void gravaAndamentoMotivo(char* dataAtual, long idAndamento);
		void gravaAtendimentoAndamentoAtual(char* dataAtual, long idAndamento);
		void gravaAtendimentoNivel(char* dataAtual,int idFase,int idGrupo,int idAtividade);
        void gravaAtendimentoGrpUsuDevolucao(long  _idAtendimento,int _idGrupoAtual,int _idPessoaUsuario);
        void gravaAtendimentoUsuarioAtual(long  _idAtendimento,int _idPessoaUsuario,const char *_dataAtual);
        void gravaAtendimentoEncIncorreto();
        void gravaAtendimentoGrupoAtual(const char* _dataAtual,const char *_dataEntrada
                                       ,long  _idAtendimento,int _idGrupo
                                       ,int _idSequencia,int _idUsuarioBKO);
        int ProcessaRelacionamentoCRI( const char * _dataAtual,const char * _dtEntrada,unsigned long &idGrupoCRIAssocUser);
        void AlterarTipoRetornoContatoCRI(long  _idAtendimento,int _idPessoaUsuario,const char *_dataAtual);
};

#endif