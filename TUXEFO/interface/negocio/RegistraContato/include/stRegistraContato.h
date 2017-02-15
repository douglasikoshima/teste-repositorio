/* $Id: stRegistraContato.h,v 1.1 2009/07/31 15:33:47 a5110702 Exp $ */

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
