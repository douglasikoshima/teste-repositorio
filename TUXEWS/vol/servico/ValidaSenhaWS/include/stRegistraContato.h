/* $Id: stRegistraContato.h,v 1.1 2007/07/04 13:40:06 akurak Exp $ */

#ifndef STREGISTROCONTATO
	#define STREGISTROCONTATO

struct stRegistraContato
{
	int		idHistoricoSenha;
	int		idTipoHistoricoSenha;
	int		idSenha;
	int		idTipoSenha;
	int		idTipoStatusSenha;
	int		idCanal;
	int		idPessoaUsuario;
	int		idTipoSistema;
	char	cdSenha[256];
	char	dtExpiracao[9];
	char	dtRegistroHistorico[9];
	char	obsRegistro[256];
	char	nmRepresentante[256];
	int		idPessoaLinha;
	int		iIdTerminal;
} ;


#endif
