#ifndef _ST_ATIVIDADE_MOTIVO_H
#define _ST_ATIVIDADE_MOTIVO_H

struct st_AtividadeMotivo
{
	int idAtividadeMotivo;
	int idAtividade;
	int idMotivo;
	unsigned long idUsuarioAlteracao;
	char dtUltimaAlteracao[24];
};

struct st_vlAtividadeMotivo
{
	short idAtividadeMotivo;
	short idAtividade;
	short idMotivo;
	short idUsuarioAlteracao;
	short dtUltimaAlteracao;
};

#endif

