#ifndef SVCMAIN
#define SVCMAIN

#include<tuxfw.h>

extern int ObtemContatoRetorno( const char * idParam, const char * pRetorno );
int RegistrosInconsistentes(
                            const char*  idContatoPrm, const char*  idTipoRetornoPrm,
                            long *idTipoLinhaPrm,
                            long *idSegmentacaoPrm,
                            long *idTipoCarteiraPrm,
                            long *idTipoPessoaPrm,
                            long *idTipoRelacionamentoPrm,
                            long *idGrupoPrm,
                            long *idCanalPrm,
                            long *idProcedenciaPrm,
                            long *idUfOperadoraPra
                            );

extern int RemoveCanalEntrada( int pRetorno );
extern int RemoveGrupo( int pRetorno );
extern int RemovePessoa( int pRetorno );
extern int RemoveProcedencia( int pRetorno );
extern int RemoveRelacionamento( int pRetorno );
extern int RemoveSegmentacao( int pRetorno );
extern int RemoveTipoCarteira( int pRetorno );
extern int RemoveTipoLinha( int pRetorno );
extern int RemoveContatoTipoRetorno( int pRetorno );
extern int RemoveTipoRetornoUfOperadora( int idContatoParam );

extern unsigned long InsereRetornoTipoContato( const char * idParam, const char * pRetorno );
extern int InsereCanalEntrada( long pRetorno, long *idCanalPrm );
extern int InsereGrupo( long pRetorno, long *idGrupoPrm );
extern int InserePessoa( long pRetorno, long *idTipoPessoaPrm );
extern int InsereProcedencia( long pRetorno, long *idProcedenciaPrm );
extern int InsereRelacionamento( long pRetorno, long *idTipoRelacionamentoPrm );
extern int InsereSegmentacao( long pRetorno, long *idSegmentacaoPrm );
extern int InsereTipoCarteira( long pRetorno, long *idTipoCarteiraPrm );
extern int InsereTipoLinha( long pRetorno, long *idTipoLinhaPrm );
extern int InsereTipoRetornoUfOperadora( long  idRetParam, long * idUfOperadoraPra );


#endif
