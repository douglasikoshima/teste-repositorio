//////////////////////////////////////////////////////////////////////
// CRelatorioMotivos.h: interface for the CRelatorioMotivos class.
//////////////////////////////////////////////////////////////////////

#ifndef SRelArqRespListaHH
#define SRelArqRespListaHH

struct RelArqRespLista
{
	char dtatendimento			[255+1];
	char nrtelefone				[255+1];
	char idsubcampanhahistorico	[255+1];
	char dstipostatuscampanha	[255+1];
	char nmloginusuario			[255+1];
	char dstiposubmotivocampanha[255+1];
	char dstipomotivocampanha	[255+1];
	char dscampanha				[255+1];
	char nmcanal				[255+1];
	char nmsubcampanha			[255+1];
	char idatendimentocmp		[255+1]; 
};

struct RelRespostaLista
{
	char idpergunta				[255+1];
	char dsresposta				[2000+1];
	char dspergunta				[2000+1];
};

struct RelPerguntaLista
{
	char idpergunta				[255+1];
	char dspergunta				[2000+1];
};


#endif
