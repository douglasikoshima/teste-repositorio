//////////////////////////////////////////////////////////////////////
// CApoioRet.h: interface for the CRelatorioGerenciamento class.
//////////////////////////////////////////////////////////////////////

#ifndef CApoioRetHH
#define CApoioRetHH


#include "utilretencao.h"




class CApoioRet 
{
	public:
	//int Inserir(DOMNode *,char *,char*);
	void ConcluiAnalise(char *pidRetencaoOld);
	void GetContatoTipoEncerramento(int idtipoencerramento,char *pidmatriz,char *pidcontato,
											   char *psgOferta, char* pidConta,char *pidTipoCarteira, char *pidTipoLinha,
											   char *pidpessoadepara, char* pidlinhatelefonica);
	int  ValidaTipoEncerramento(char *pidTipoEncerramento);
	bool VerificaContatoCliente( int * idPessoaDeParaPrm,char *pidGrupo, char *pidTipoEncerramento,
								 char *idMatrizOferta,char* sContatoPrm );
	bool ObtemVlrParametro( char *cdParametro,char *vlParametro );

	void AtualizaNRProtocolo(char* pidRetencao,char *pnrProtocolo);

	void GravaLinhasAssociadasCancelamento(DOMNode *pdnode);

	int InserirDadosAtivacao(DOMNode *pdnode,char *pidRetencao,char *pIduser,int vOferta);

	int ValidaPortout(DOMNode *pdnode,char *pnrprotocolo);

	private:
		bool   m_bAtualizaContato;


};

#endif



