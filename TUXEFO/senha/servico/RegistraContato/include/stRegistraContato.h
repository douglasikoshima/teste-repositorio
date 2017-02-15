/* $Id: stRegistraContato.h,v 1.1.2.2 2010/07/14 23:24:25 a5114878 Exp $ */

#ifndef STREGISTROCONTATO
	#define STREGISTROCONTATO

struct stRegistraContato
{
    int          idHistoricoSenha;
    int          idTipoHistoricoSenha;
    int          idSenha;
    int          idTipoSenha;
    int          idTipoStatusSenha;
    int          idCanal;
    int          idPessoaUsuario;
    int          idTipoSistema;
    char         cdSenha[256];
    char         dtExpiracao[9];
    char         dtRegistroHistorico[9];
    char         obsRegistro[256];
    char         nmRepresentante[256];
    long         idPessoaLinha;
    int          iIdTerminal;
} ;


#endif
