#ifndef STVARIAVEISOBTFRMDIN
	#define STVARIAVEISOBTFRMDIN

struct st_VariaveisObtFrmDin
{
   char idAtendimento[23];
   char idFaseProcesso[23];
   char prefixo[23];
   char idContato[23];
   char idFormaRetorno[23];
   char dsTipoComunicacao[256];
   char nrTelefoneContato[16];
   char idTipoLinha[23];
   char idContatoFolhaCampo[23];
   char idCampo[23];
   char nmCampo[2001];
   char inObrigatorio[256];
   char inPesquisa[256];
   char nmTipoDadoCampo[256];
   char sgLayoutApresentacaoCampo[256];
   char sgMascaraApresentacaoCampo[256];
   char nrTamanho[256];
   char flgFrmValor[2];    // 0 - Nao tem valor de st_VariaveisFrmValor
} ;                        // 1 - st_VariaveisFrmValor possui valor

struct st_VariaveisFrmValor
{
   char idFormularioCampoValor[23];
   char valor[256];
} ;


struct st_FormularioDinamico
{
	int idContato;
	char nrTelefone[256];
	int idTipoLinha;
	int idFaseProcesso;
} ;

#endif
