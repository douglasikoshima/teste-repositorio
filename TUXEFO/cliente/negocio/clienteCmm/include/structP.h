#ifndef STRUCTPH
#define STRUCTPH

struct stPessoa 
{
	int		idpessoa;
	int		idsistemaorigem;
	char	idpessoasistemaorigem[255+1];
	char	nmpessoa[255+1];
	char	nmnome[255+1];
	char	nmnomemeio[255+1];
	char	nmsobrenome[255+1];
	char	cdchurnprobabilidade;
	int		vlrchurnprobabilidade;
	char	dtchurn[12+1];		// field date
	char	dtcadastro[12+1];	// field date
	int		infalecimentoinformado;
	char	dtfalecimento[13];
	int		idtipopessoa;
	int		tssincronismo;
	int		sqsincronismo;
	int		idtipocarteira;
	int		iduf;
};

#endif
