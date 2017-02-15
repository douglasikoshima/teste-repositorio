#ifndef ATENDIMENTOANATEL_H
#define ATENDIMENTOANATEL_H
#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include <iostream.h>
#include <unistd.h>
#include <list>
#include <unistd.h>
#include <ctype.h>
#include <dirent.h>
#include "../../commons/Log/include/Log.h"
#include "../../commons/tinyxml/include/tinyxml.h"
#include "../include/XMLReader.hpp"
#include "../include/Atendimento.hpp"
#include "../include/Historico.hpp"

#ifndef _MAX_PATH
#define _MAX_PATH 1024
#endif

#define TAM_BD_PASSWD 32
#define TAM_BD_USRNAME 32
#define TAM_BD_INST 32
#define TAM_TX_PASSWD TAM_BD_PASSWD
#define TAM_TX_USRNAME TAM_BD_USRNAME
#define TAM_TX_CLT 32
#define TAM_TX_GEN 32

#define STATUS_ERRO     0
#define STATUS_SUCESSO  1
#define STATUS_VAZIO    2

using namespace std;


/*******************************************************************************
 * Macros para manipulacao de dados ProC/C++
 *******************************************************************************/
#define endOraStr(varstr)      varstr.arr[varstr.len]= '\0'
#define oraToStr(bstr,vchar)   if(!bstr) strncpy(bstr,vchar.arr,vchar.len)
#define strToOra(vchar,bstr)   vchar.len = strlen(bstr);strncpy((char *)vchar.arr,bstr,vchar.len);vchar.arr[vchar.len] = 0
#define strconv(buffer,fonte)  sprintf(buffer,"%d",fonte)

class AtendimentoAnatel {
	private:
	    XMLReader xmlReader;
		Log log;
		char szTempTrace[2048];
		Historico *historico;
		char idtSolicitacao[256];
		char dataRegistro[256];
		char telefoneProblema[256];	
		char dsValorParametro[256];
		char idAnatelSistemaOrigem[256];
		char idAnatelGrupoAbertura[256];
		char idAnatelResponsavelAbertura[256];
		char idAnatelObservacao[256];
		char idAnatelTipoOperacao[256];
		char idAnatelProcedencia[256];
		char idAnatelCanal[256];
		char idAnatelUsuario[256];
				
	public:
		AtendimentoAnatel();
		~AtendimentoAnatel();
		
		// métodos para acessar propriedades
		void setHistorico(Historico *historico);
		void setIdtSolicitacao(char *idtSolicitacao);
		void setDataRegistro(char *dataRegistro);
		void setTelefoneProblema(char *telefoneProblema);
		Historico* getHistorico();
		char* getIdtSolicitacao();
		char* getDataRegistro();
		char* getTelefoneProblema();		
		char* getIdAnatelSistemaOrigem();
		char* getIdAnatelGrupoAbertura();
		char* getIdAnatelResponsavelAbertura();
		char* getIdAnatelObservacao();
		char* getIdAnatelTipoOperacao();
		char* getIdAnatelProcedencia();
		char* getIdAnatelCanal();
		char* getIdAnatelUsuario();
		void setIdAnatelSistemaOrigem(char *idAnatelSistemaOrigem);
		void setIdAnatelGrupoAbertura(char *idAnatelGrupoAbertura);
		void setIdAnatelResponsavelAbertura(char *idAnatelResponsavelAbertura);
		void setIdAnatelObservacao(char *idAnatelObservacao);
		void setIdAnatelTipoOperacao(char *idAnatelTipoOperacao);
		void setIdAnatelProcedencia(char *idAnatelProcedencia);
		void setIdAnatelCanal(char *idAnatelCanal);
		void setIdAnatelUsuario(char *idAnatelUsuario);
		
		// métodos para recuperar informações
		int selectAtendimentoAnatel( list<Atendimento> &atendimentos, char *nmArquivo);
		
		// métodos de gravação de dados
		int inserirAtendimentoArquivo(TiXmlNode *header, char *idAtendimentoAnatelArquivo, char *nmArquivo);
		int inserirAtendimento(TiXmlNode *node, char*, char*);
		int inserirDadoComplementar(char*, TiXmlNode *node);
		int inserirHistorico(char*, TiXmlNode *node);
		int inserirAnexo(char *pIdAtendimentoAnatel, char *pDsAnexo);
		int inserirAtividade(TiXmlNode *node, char *pIdAtendimentoAnatelAtividade, char *idAtendimentoAnatel);
		int inserirAtividadeAnexo(char *pIdAtendimentoAnatelAtividade, char *pDsAnexo);
		int verificaSolicitacaoDuplicada(TiXmlNode *node);
		int verificaLinhaExistente(char *nrLinha, char *ddd, char *pidLinhaTelefonica, char *pidTipoLinha, 
							char *pidPessoa, char *psgTipoPessoa, char *pidConta, char *pidPessoaDepara);
		int verificaDocumentoExistente(Atendimento &atendimento, char *pidPessoa);		
		int atualizarAtendimento(char *pIdAtendimentoAnatel, char *pIdAtendimento);
		int relatorioSolicitacaoRejeitada(char *idAtendimentoAnatelArquivo);
		int relatorioProtocoloCliente(char *idAtendimentoAnatelArquivo);
		int relatorioProtocoloNaoCliente(char *idAtendimentoAnatelArquivo);
		int relatorioTotalRegistros(char *idAtendimentoAnatelArquivo);
		int relatorioContatoNovo(char *idAtendimentoAnatelArquivo);
		int relatorioContatoGenerico(char *idAtendimentoAnatelArquivo);
		int atualizarArquivoLog(char *nome, char*idAtendimentoAnatelArquivo);
		int atualizarStatusArquivo(char*nome, int status);
		char* getParametro(char *parametro);
		int getContatoFuncionalidade(char *cdFuncionalidade, char *idContato);
		int getUFOperadora(char *idLinhaTelefonica, char *idPessoa, char *idUfOperadora);
		
		// métodos utilitarios
		void gravarLog(char *mensagem, long sqlcode, char *sqlerrmc, int inGravado);
		void gravarLog(char *mensagem, Atendimento &atendimento);
		
		// conexão
		int connect(char *user, char *password, char *sid);
		void disconnect();
};


#endif
