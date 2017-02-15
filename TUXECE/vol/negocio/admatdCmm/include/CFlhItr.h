/* charls */
#ifndef CContatoFolhaItrH
#define CContatoFolhaItrH

#include <stdlib.h>
#include <string.h>


struct STContatoFolhaRegistro
{
	char cidContato[21+1];
	char cdsContato[255+1];
	char cidPagina[21+1];
	char cdsPagina[255+1];
	char cidTipoRetorno[21+1];
	char cdsTipoRetorno[255+1];
	char cidTipoFechamento[21+1];
	char cdsTipoFechamento[255+1];
	char cidTipoProcesso[21+1];
	char cdsTipoProcesso[255+1];
	char cqtHorasPrazo[21+1];
	char cinFechamentoImediato[21+1];
	char cvlPeso[21+1];
	char cinProcessoTecnico[21+1];
	char cidMensagem[21+1];
	char cnmMensagem[255+1];
    char cqtHorasPrazoAnatel[21+1];
    char cInSms[1+1];
    char cDsSms[50+1];
    char cInRelacionamento[1+1];
    char cInProtocolo[1+1];
    char cDsContatoCanais[50+1];
    char cDsMsgExcecao[60+1];
	char cInCancelamento[1+1];
	char cInexibeProtocolo[1+1];
    char cIdClassificacaoSms[40+1];
    char cInAberturaContato[1+1];
    char cSgRegraEncaminhamento[3+1];
    char cSgFluxoAtendimento[3+1];
};

class CContatoFolhaItr
{
public:
	CContatoFolhaItr();
	~CContatoFolhaItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STContatoFolhaRegistro* Registro( void );
	STContatoFolhaRegistro* Registro(int nPosicao);

private:
	STContatoFolhaRegistro* stcrContatoFolha;
	int _iQuantidade;
	int _iPosicao;
    const tamBlocoCCItr;
protected:

	void Add( 
	          char* cidContato
	         ,char* cdsContato
	         ,char* cidPagina
	         ,char* cdsPagina
	         ,char* cidTipoRetorno
	         ,char* cdsTipoRetorno
	         ,char* cidTipoFechamento
	         ,char* cdsTipoFechamento
	         ,char* cidTipoProcesso
	         ,char* cdsTipoProcesso
	         ,char* cqtHorasPrazo
	         ,char* cinFechamentoImediato
	         ,char* cvlPeso
	         ,char* cinProcessoTecnico
	         ,char* cidMensagem
	         ,char* cnmMensagem
             ,char* cqtHorasPrazoAnatel
             ,char* cInSms
             ,char* cDsSms
             ,char* cInRelacionamento
             ,char* cInProtocolo
             ,char* cInexibeProtocolo
             ,char* cDsContatoCanais
             ,char* cDsMsgExcecao
			 ,char* cInCancelamento
             ,char* cIdClassificacaoSms
             ,char* cInAberturaContato
             ,char* cSgRegraEncaminhamento
             ,char* cSgFluxoAtendimento
            );

	void ZeraContatoFolha( void );

};

#endif
