/* $Id: stAlteraSenha.h,v 1.1.118.1 2012/07/26 14:53:22 a5114878 Exp $ */

#ifndef STALTERASENHA
#define STALTERASENHA

struct stAlteraSenha
{

    int   idPessoa;
    int   titularidadeSenha;
    char  tipoTitularidade[2];
    char  telefone[16];
    int   idTipoHistoricoSenha;
    int   idCanal;
    int   idTipoSistema;
    char  nmRepresentante[256];
    char  nrTerminal[256];
    char  obsRegistro[256];
    char  cdSenha[256];
    int   registraHistorico;
    int   idPessoaUsuario;
    int   inTrocaSenha;
    char  dsLembreteSenha[256];
    int   idPessoaLinha;
	int   iIdTerminal;
} ;


#endif
