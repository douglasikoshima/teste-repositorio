#ifndef TXPB_UTIL_H
#define TXPB_UTIL_H 1

#define PESSOA_USUARIO 1
#define PESSOA_CLIENTE 2

#define ESTADO_ATLYS_ATIVO     1
#define ESTADO_PPS_ATIVO       6
#define ESTADO_NGIN_ATIVO      12

//ProC
#define NO_DATA_FOUND		   1403

#include <string>
#include <sstream>

using namespace std;

typedef enum TipoValidacao
{
	VALIDA_TABELA_DDD = 1,
	VALIDA_TABELA_REGIONAL = 2
}TIPOVALIDACAO;


class Util
{
public:
	static char* upper(char *);
	static char* rtrim(char *);
	static char* ltrim(char *);
	static char* trim(char *);
	static void  trim(string& strValue);
	static unsigned long HexaToLong(char*);
	static string intToStr(int iNumber) ;
	static long pot(int, int);
	static int IsNumeric(char*);
	static bool cmp(char*, char*);
	static bool isNum(char*);
	//static char* convertDate(char*, char*, char*);
	static char* formateDate(char*, char*, char*, char *);
	static char* getIdContaSistemaOrigem(char*, char*);
	static int isValidLine(char*);
	static int getQtFavoritos(char*);
	static int getDigitoLinha(char*);
	static char* rand(char*);
	static char* DataDiaAnterior(char* chrData);
	static void DtAtual(char *chrDtAtual, char *chrFormato);
	static void SubDiasData(char* chrDtInicial, char* chrDtFinal, int intSubtraiDias, char* chrFormato);
	static bool IsValidDate(char*dtDiaVencimento);
	static int getDadosPessoa(char*nomePessoa,char*cpf,char*linha,char*ddd);
	
	static int logarChamadaNGIN(string &snrLinha, string &sdsMetodo_NGIN, string &sdsObservacao, string &sdsXML_IN, string &sdsXML_OUT );
	static int VerificarlinhaFavoritaValida(string &spc_ddd, TIPOVALIDACAO eTipoValidacao /*1=DDD 2=REGIONAL*/);
	static int lerApoioParametro(string &sCdParametro, string &sDsValorParametro);
};


#endif