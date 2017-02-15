#ifndef STVARIAVEISTPCOMUNIC
	#define STVARIAVEISTPCOMUNIC

struct st_VariaveisMigracaoACS
{
   char idAtendimento[23];
   char idSistemaOrigem[23];
   char idProcesso[23];
   char idTipoAtendimentoMigracao[23];
   char nmUsuarioGrupoBKO[256];
   char nmUsuarioBKO[256];
   char inProcessoTecnico[23];
   char obsAtendimento[256];
   char nmClienteUsuario[256];
   char inClienteVIVO[23];
   char nmTipoCliente[256];
   char nmCPF_CNPJ[256];
   char nmContato[256];
   char nmTelefoneContato[256];
   char nmEmailContato[256];
   char nmTipoCarteira[256];
   char nmSegmentacao[256];
   char idLinhaTelefonica[23];
   char nrLinha[23];
   char nmTipoServico[256];
   char nmProcesso[256];
   char dtTratamentoBKO[20];
   char dtAtualizacao[20];
   char dtImportacao[20];
   char obsHistorico[4001];
   char idPessoaDePara[23];
   char idTipoAtendimento[23];
   char idAndamento[39];
   char idAtividade[23];
   char idContatoFolhaBaixa[23];
   char dtAndamento[20];
   char nmUsuarioGrupo[256];
   char nmUsuario[256];
   char idComentario[23];
   char nmUsuarioGrupoMigracao[256];
   char nmLoginUsuario[256];
   char dtAbertura[20];
   char Observacao[4001];
   char dsTipoAtendimento[256];
} ;

#endif
