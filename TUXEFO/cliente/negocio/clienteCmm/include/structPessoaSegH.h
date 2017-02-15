#ifndef STRUCTPESSOASEGHH
#define STRUCTPESSOASEGHH

struct stPessoaSegmentacaoHistorico 
{
	int		idpessoasegmentacao;
	int		idpessoadepara;
	int		idsegmentacao;
	int		vlrentabilidade;
	int		invip;
	char	dtsegmentacao[12+1];
};

#endif
