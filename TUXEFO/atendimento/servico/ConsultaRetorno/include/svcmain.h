#ifndef SVCMAIN
#define SVCMAIN

#include <tuxfw.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

//
// Prototipos
//
int ConsultaCanalEntrada( const char *idContatoParam , const char *idRetParam, XMLGen *Saida );
int ConsultaCanalEntradaSel( const char *idContatoParam , const char *idRetParam, XMLGen *Saida );
int ConsultaGrupo( const char *idContatoParam , const char *idRetParam, XMLGen *Saida );
int ConsultaGrupoSel( const char *idContatoParam , const char *idRetParam, XMLGen *Saida );
int ConsultaPessoa( const char *idContatoParam , const char *idRetParam, XMLGen *Saida );
int ConsultaPessoaSel( const char *idContatoParam , const char *idRetParam, XMLGen *Saida );
int ConsultaProcedencia( const char *idContatoParam , const char *idRetParam, XMLGen *Saida );
int ConsultaProcedenciaSel( const char *idContatoParam , const char *idRetParam, XMLGen *Saida );
int ConsultaRelacionamento( const char *idContatoParam , const char *idRetParam, XMLGen *Saida );
int ConsultaRelacionamentoSel( const char *idContatoParam , const char *idRetParam, XMLGen *Saida );
int ConsultaSegmentacao( const char *idContatoParam , const char *idRetParam, XMLGen *Saida );
int ConsultaSegmentacaoSel( const char *idContatoParam , const char *idRetParam, XMLGen *Saida );
int ConsultaTipoCarteira( const char *idContatoParam , const char *idRetParam, XMLGen *Saida );
int ConsultaTpCarteiraSel( const char *idContatoParam , const char *idRetParam, XMLGen *Saida );
int ConsultaTipoLinha( const char *idContatoParam , const char *idRetParam, XMLGen *Saida );
int ConsultaTpLinhaSel( const char *idContatoParam , const char *idRetParam, XMLGen *Saida );
int ConsultaRetorno( const char *idContatoParam , XMLGen *Saida );
int ConsultaTpUfOperadora( const char *idContatoParam , const char *idRetParam, XMLGen *Saida );
int ConsultaTpUfOperadoraSel( const char *idContatoParam , const char *idRetParam, XMLGen *Saida );

#endif
