#ifndef CGravaLegadoItrH
#define CGravaLegadoItrH

#include <stdlib.h>
#include <string.h>

#define TAMANHO_TIPO_TEXTO     255
#define TAMANHO_TIPO_NUMERICO   21

struct STGravaLegadoRegistro
{
	char pzcIdPessoa[TAMANHO_TIPO_TEXTO+1];
	char pzcNrLinha[TAMANHO_TIPO_TEXTO+1];
	char pzcIdGrupo[TAMANHO_TIPO_TEXTO+1];
	char pzcTipoCliente[TAMANHO_TIPO_TEXTO+1];
	char pzcNome[TAMANHO_TIPO_TEXTO+1];
	char pzcDataNascimento[TAMANHO_TIPO_TEXTO+1];
	char pzcEstadoCivil[TAMANHO_TIPO_TEXTO+1];
	char pzcCodSexo[TAMANHO_TIPO_TEXTO+1];
	char pzcDsRazaoSocial[TAMANHO_TIPO_TEXTO+1];
	char pzcIE[TAMANHO_TIPO_TEXTO+1];
	char pzcCPF[TAMANHO_TIPO_TEXTO+1];
	char pzcRG[TAMANHO_TIPO_TEXTO+1];
	char pzcDataExpiracao[TAMANHO_TIPO_TEXTO+1];
	char pzcOrgaoExpeditor[TAMANHO_TIPO_TEXTO+1];
	char pzcEstadoExpedicao[TAMANHO_TIPO_TEXTO+1];
	char pzcPassaporte[TAMANHO_TIPO_TEXTO+1];
	char pzcCNPJ[TAMANHO_TIPO_TEXTO+1];
	char pzcCNAE[TAMANHO_TIPO_TEXTO+1];
	char pzcTelefone[TAMANHO_TIPO_TEXTO+1];
	char pzcFax[TAMANHO_TIPO_TEXTO+1];
	char pzcEMail[TAMANHO_TIPO_TEXTO+1];
	char pzcNumDepend[TAMANHO_TIPO_TEXTO+1];
	char pzcNomeContato[TAMANHO_TIPO_TEXTO+1];
	char pzcLogradouro[TAMANHO_TIPO_TEXTO+1];
	char pzcEndereco[TAMANHO_TIPO_TEXTO+1];
	char pzcComplemento[TAMANHO_TIPO_TEXTO+1];
	char pzcBairro[TAMANHO_TIPO_TEXTO+1];
	char pzcCEP[TAMANHO_TIPO_TEXTO+1];
	char pzcCidade[TAMANHO_TIPO_TEXTO+1];
	char pzcEstado[TAMANHO_TIPO_TEXTO+1];
	char pzcNumero[TAMANHO_TIPO_TEXTO+1];
};

class CGravaLegadoItr
{
public:
	CGravaLegadoItr();
	~CGravaLegadoItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STGravaLegadoRegistro* Registro( void );
	STGravaLegadoRegistro* Registro(int nPosicao);

private:
	STGravaLegadoRegistro* stcrGravaLegado;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add(	char* pzcIdPessoa,
				char* pzcNrLinha,
				char* pzcIdGrupo,
				char* pzcTipoCliente,
				char* pzcNome,
				char* pzcDataNascimento,
				char* pzcEstadoCivil,
				char* pzcCodSexo,
				char* pzcDsRazaoSocial,
				char* pzcIE,
				char* pzcCPF,
				char* pzcRG,
				char* pzcDataExpiracao,
				char* pzcOrgaoExpeditor,
				char* pzcEstadoExpedicao,
				char* pzcPassaporte,
				char* pzcCNPJ,
				char* pzcCNAE,
				char* pzcTelefone,
				char* pzcFax,
				char* pzcEMail,
				char* pzcNumDepend,
				char* pzcNomeContato,
				char* pzcLogradouro,
				char* pzcEndereco,
				char* pzcComplemento,
				char* pzcBairro,
				char* pzcCEP,
				char* pzcCidade,
				char* pzcEstado,
				char* pzcNumero
			);

	void Zera( void );

};

#endif
