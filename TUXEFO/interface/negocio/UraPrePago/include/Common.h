#ifndef COMMON_H
#define COMMON_H
#include<string.h>
#include<tuxfw.h>
#include"../../commons/include/vectorlist.h"

struct stParametrosUren{
	char cdDDD[2+1];
	char cdNumTelefone[16];
	char nomeCliente[256];
	char sexo[1+1];
	char dtNascimento[8+1];
	char endereco[256];
	char nrEndereco[256];
	char complemento[256];
	char bairro[256];
	char municipio[256];
	char cep[10+1];
	char cidade[256];
	char uf[256];
	char cpf[11+1];
	char cdControle[256];
	char telefoneContato[256];
};

struct stPessoaLinha{
	char sgTipoLinha[256+1];
	char idPessoaLinha[21+1];
	char idPessoa[21+1];
	char idTipoRelacionamento[21+1];
	char estadoLinha[256];
	int dtCadastro;
	int qtdeLinhasPre;
	int qtdeLinhasPos;
};

struct stPessoaFisica{
	char idPessoa[21];
	char nmPessoa[256];
	char idSexo[21];
	char dtNascimento[15];
	char idEstadoCivil[21];
	char idEscolaridade[21];
	char idOcupacao[21];
	char dsOcupacao[256];
};

struct stLinha{
	char cdAreaRegistro[3];
	char nrLinha[16];
	char idLinha[21+1];
	char sgTipoLinha[256+1];
};



typedef CVectorList<stPessoaLinha> ListPessoaLinha;
typedef CVectorList<stPessoaFisica> ListPessoaFisica;


#endif