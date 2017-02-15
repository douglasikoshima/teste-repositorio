#ifndef ATENDIMENTO_H
#define ATENDIMENTO_H
#include "../../commons/Log/include/Log.h"
#include <stdlib.h>
#include <string>

using namespace std;

class Atendimento {
	private:
		char idAtendimentoAnatel[53];
		char nrSolicitacao[53];
		char dsTipoAtendimentoAnatel[256];
		char dsServico[256];
		char dsModalidade[256];
		char dsMotivo[256];
		char dsSubMotivo[256];
		char dsCategoria[256];
		char dsSubCategoria[256];
		char nrTelefoneProblema[256];
		char idAtendimentoAnatelArquivo[256];
		char nrCpfCnpjAssinante[256];
		char nmAssinante[256];
		
		Log log;
	public:
		Atendimento();
		~Atendimento();
		
		char* getIdAtendimentoAnatel();
		char* getNrSolicitacao();
		char* getDsTipoAtendimentoAnatel();
		char* getDsServico();
		char* getDsModalidade();
		char* getDsMotivo();
		char* getDsSubMotivo();
		char* getDsCategoria();
		char* getDsSubCategoria();
		char* getNrTelefoneProblema();
		char* getIdAtendimentoAnatelArquivo();
		char* getNrCpfCnpjAssinante();
		char* getNmAssinante();
		
		void setIdAtendimentoAnatel(char *idAtendimentoAnatel);
		void setNrSolicitacao(char *nrSolicitacao);
		void setDsTipoAtendimentoAnatel(char *dsTipoAtendimentoAnatel);
		void setDsServico(char *dsServico);
		void setDsModalidade(char *dsModalidade);
		void setDsMotivo(char *dsMotivo);
		void setDsSubMotivo(char *dsSubMotivo);
		void setDsCategoria(char *dsCategoria);
		void setDsSubCategoria(char *dsSubCategoria);
		void setNrTelefoneProblema(char *nrTelefoneProblema);
		void setIdAtendimentoAnatelArquivo(char*);
		void setNrCpfCnpjAssinante(char *nrCpfCnpjAssinante);
		void setNmAssinante(char *nmAssinante);
		
};

#endif