/**
 * @author  David Ramos Dominguez
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:17 $
 **/

#ifndef STWF_CERRAMERET
	#define STWF_CERRAMERET

//#include"../../../commons/Collection/include/Collection.h"

struct st_WF_CERRAMERET
{
	int  UsuarioAtual;
	long idAtendimento;
	int  idAgrEstTPrFt;
	int  idAgrEstTPrAt;
	int  idAtividade;
	int  idTipoPessoa;
	int  idTipoLinea;
	int  idFase;
	int  idSegmentacao;
	int  idTipoCarteira;
	int  idProcedencia;
	int  idContato;
	int  idGrupoAbertura;
	int  idCanal;
	int  idTipoRel;

	char urlDestino [256];

	DOMNode* domTratWf;
	DOMNode* domWorkflow;
	DOMNode* domArvEnc;
	DOMNode* domEncerrar;
	DOMNode* domFormulario;
};


#endif

