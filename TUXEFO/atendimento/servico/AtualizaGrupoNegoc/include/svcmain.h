#ifndef SVCMAIN
#define SVCMAIN

#include<tuxfw.h>

extern int RemoveSegmentacao( const char * );
extern int RemoveProcedencia( const char * );
extern int RemoveTpCarteira( const char * );
extern int RemoveTipoLinha( const char * );
extern int RemoveGrupoAberturaGrupo( const char * );
extern int RemoveTipoRelacionamento( const char * );
extern int RemoveTipoPessoa( const char * );
extern int RemoveCanalGrupo( const char * );
extern int RemoveRegionalGrupo( const char * );

extern int InsereProcedencia( const char *,DOMNode * );
extern int InsereSegmentacao( const char *,DOMNode * );
extern int InsereTipoCarteira( const char *,DOMNode * );
extern int InsereTipoLinha( const char *,DOMNode * );
extern int InsereGrupoAberturaGrupo( const char *,DOMNode * );
extern int InsereTipoPessoa( const char *,DOMNode * );
extern int InsereTipoRelacionamento( const char *,DOMNode * );
extern int InsereCanalGrupo( const char *,DOMNode * );
extern int InsereRegionalGrupo( const char *,DOMNode * );

extern int RemoveSkillSegmentacao( const char * );
extern int RemoveSkillProcedencia( const char * );
extern int RemoveSkillTpCarteira( const char * );
extern int RemoveSkillTipoLinha( const char * );
extern int RemoveSkillGrupoAberturaGrupo( const char * );
extern int RemoveSkillTipoRelacionamento( const char * );
extern int RemoveSkillTipoPessoa( const char * );
extern int RemoveSkillCanalGrupo( const char * );
extern int RemoveSkillRegionalGrupo( const char * );

extern int RemoveSkillParametros( const char *pzcIdGrupoSkill );

//valida dependencias usuario e skill
extern int ValidaDependencias( const char *,int);
extern int RemoveUsuarioSkill(const char*);

//Verifica se houve ou nao exclusao de variaveis
extern int SomenteInclusaoVariaveis( char* idGupoPar, DOMNode* XMLIn );



#endif
