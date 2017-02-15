#ifndef CUsrItrH
#define CUsrItrH

#include <stdlib.h>
#include <string.h>
#include<tuxfw.h>

struct STUsuarioRegistro
{
	char cidUsuario[21+1];
	char cnome[255+1];
	char csobrenome[255+1];
	char cemail[255+1];
	char cidDDD[21+1];
	char cdsDDD[255+1];
	char cTelefone[255+1];
	char clogin[255+1];
	char cloginCti[255+1];
	char cidStatusAtual[21+1];
	char csiglaStatusAtual[255+1];
	char cdescricaoStatusAtual[255+1];
	char cidMotivo[21+1];
	char cdsMotivo[255+1];
	char cdtInicio[255+1];
	char cdtRetorno[255+1];
	char cidCargoAtual[21+1];
	char cdescricaoCargoAtual[255+1];
	char cloginChefe[255+1];
	char cnomeChefe[255+1];
	char cidUF[21+1];
	char cnmUF[255+1];
	char cdtInclusao[255+1];
	char cdtExclusao[255+1];
	char cinConsultor[21+1];
	char cidUFOperadora[21+1];
	char cdsSessaoUsuario[500+1];
	char cidPerfilConsultorAtd[10+1];
	char cidFornecedorConsultorAtd[10+1];
	char cidSiteConsultorAtd[10+1];
    char cdsLoginRoteamento[10+1];
    char cdsFornecedorConsultorAtd[255+1];
    char cdsSiteConsultorAtd[255+1];
};

class CUsrItr
{
public:
	CUsrItr();
	~CUsrItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STUsuarioRegistro* Registro( void );
	STUsuarioRegistro* Registro(int nPosicao);

private:
	STUsuarioRegistro* stcrUsuario;
	int _iQuantidade;
	int _iPosicao;
	int _tamBloco;

protected:
	void Add( char* cidUsuario
			 ,char* cnome
			 ,char* csobrenome
			 ,char* cemail
			 ,char* cidDDD
			 ,char* cdsDDD
			 ,char* cTelefone
			 ,char* clogin
			 ,char* cloginCti
			 ,char* cidStatusAtual
			 ,char* cdescricaoStatusAtual
			 ,char* cidMotivo
			 ,char* cdsMotivo
			 ,char* cdtInicio
			 ,char* cdtRetorno
			 ,char* cidCargoAtual
			 ,char* cdescricaoCargoAtual
			 ,char* cloginChefe
			 ,char* cnomeChefe
			 ,char* cidUF
			 ,char* cnmUF
			 ,char* cdtInclusao
			 ,char* cdtExclusao 
			 ,char* cinConsultor
			 ,char* cidUFOperadora
			 ,char* cidPerfilConsultorAtd
			 ,char* cidFornecedorConsultorAtd
			 ,char* cidSiteConsultorAtd
             ,char* cdsLoginRoteamento
			);

	void Add( char* cidUsuario
			 ,char* cnome
			 ,char* clogin
			 ,char* cloginCti
			 ,char* cloginChefe
			 ,char* cinConsultor
			 ,char* cidStatusAtual
			 ,char* csiglaStatusAtual
			 ,char* cdescricaoStatusAtual
			 ,char* cidPerfilConsultorAtd
			 ,char* cidFornecedorConsultorAtd
			 ,char* cidSiteConsultorAtd
             ,char* cdsLoginRoteamento
             ,char* cdsFornecedorConsultorAtd
             ,char* cdsSiteConsultorAtd
			);

	void Add( char* cidUsuario
			 ,char* cnome
			 ,char* clogin
			 ,char* cloginCti
			 ,char* cloginChefe
			 ,char* cinConsultor
			 ,char* cidStatusAtual
			 ,char* csiglaStatusAtual
			 ,char* cdescricaoStatusAtual
			 ,char* cdsSessaoUsuario
			);

	void ZeraUsuario( void );

};

#endif