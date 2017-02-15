#ifndef SVCMAIN
#define SVCMAIN

#include <tuxfw.h>
int ConsultaCarterizacao( const char *,XMLGen * );
int ConsultaProcedencia( const char *,XMLGen * );
int ConsultaSegmentacao( const char *,XMLGen * );
int ConsultaTipoCliente( const char *,XMLGen * );
int ConsultaTipoPessoa( const char *, XMLGen * );
int ConsultaTipoRelacionamento( const char *, XMLGen * );
int ConsultaGrupoAberturaGrupo( const char *, XMLGen * );
int ConsultaCarterizacaoSel( const char *,XMLGen * );
int ConsultaProcedenciaSel( const char *,XMLGen * );
int ConsultaSegmentacaoSel( const char *,XMLGen * );
int ConsultaTipoClienteSel( const char *,XMLGen * );
int ConsultaTipoPessoaSel( const char *, XMLGen * );
int ConsultaTipoRelacionamentoSel( const char *, XMLGen * );
int ConsultaGrupoAberturaGrupoSel( const char *, XMLGen * );
int ConsultaCanalGrupo( const char *, XMLGen * );
int ConsultaCanalGrupoSel( const char *, XMLGen * );
int ConsultaRegionalGrupo( const char *, XMLGen * );
int ConsultaRegionalGrupoSel( const char *, XMLGen * );

#endif
