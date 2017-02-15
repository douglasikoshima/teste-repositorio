// Assunto.hpp: interface for the CAssunto class.
//
//////////////////////////////////////////////////////////////////////

#ifndef __C_ASSUNTO__
#define __C_ASSUNTO__

#include <list>
using namespace std;

#define XML_OUT_ASSUNTO						"Assuntos"
#define XML_OUT_ID_ASSUNTO					"idAssunto"
#define XML_OUT_DS_ASSUNTO					"dsAssunto"

#define XML_OUT_SUB_ASSUNTO					"SubAssuntos"
#define XML_OUT_ID_SUB_ASSUNTO				"idSubAssunto"
#define XML_OUT_DS_SUB_ASSUNTO				"dsSubAssunto"
#define XML_OUT_SQ_APRES_SUB_ASSUNTO		"sqApresentacao"

#define XML_OUT_ATRIBUTO					"Atributos"
#define XML_OUT_ID_ATRIBUTO					"idAtributo"
#define XML_OUT_DS_ATRIBUTO					"dsAtributo"
#define XML_OUT_ID_TP_APRES_ATRIBUTO		"idTipoApresentacao"
#define XML_OUT_SQ_APRES_ATRIBUTO			"sqApresentacao"
#define XML_OUT_ID_VAL_SEL_ATRIBUTO			"idValorSelecionado"

#define XML_OUT_VALOR_POSSIVEL				"ValoresPossiveis"
#define XML_OUT_ID_VALOR_POSSIVEL			"idValorPossivel"
#define XML_OUT_DS_VALOR_POSSIVEL			"dsValorPossivel"
#define XML_OUT_IN_SEL_VALOR_POSSIVEL		"inSelecionado"
#define XML_OUT_STATUS_VALOR_POSSIVEL		"status"

#define XML_OUT_VALOR_LIVRE					"ValorLivre"
#define XML_OUT_ID_VALOR_LIVRE				"idValorLivre"
#define XML_OUT_DS_VALOR_LIVRE				"dsValorLivre"

struct			S_PROFILE;
typedef struct	S_PROFILE TP_ARVORE_PROFILE_SQL;

class CAssunto
{

public:
	CAssunto();
	virtual ~CAssunto();

	void montaArvorePessoaCanal(int idPessoa, int idCanal, XMLGen* xml);

private:

	void consultaArvore(int idCanal, list<TP_ARVORE_PROFILE_SQL> &lstProfile);

	void consultaArvorePessoaCanalValPosDB(int idPessoa, 
										   int idCanal,
										   list <TP_ARVORE_PROFILE_SQL> &lstProfile);

	void consultaArvorePessoaCanalValLivreDB(int idPessoa, 
											 int idCanal, 
											 list <TP_ARVORE_PROFILE_SQL> &lstProfile);

	void montaArvorePessoaCanalDB(int idPessoa, int idCanal, XMLGen* xml);
};

#endif // __C_ASSUNTO__