#ifndef SELECT_DATA
#define SELECT_DATA

#include <tuxfw.h>
#include "CGruposLst.h"

#include <string>
using namespace std;

#define TO_VARCHAR(VAR,VAL)	{strcpy((char*)VAR.arr,VAL);VAR.len=strlen(VAL);}	
#define INITIALIZE_SQL		struct sqlca sqlca;sqlca.sqlcode=0
#define END_ARRAY -1

class GruposBase
{
protected:
	int GetGrupoType(char*);
};

class GruposRequest: public GruposBase
{
public:
	GruposRequest( XMLGen* );
	~GruposRequest();
	int SetQueryType( char );
	int QueryData( char*,int );
    
    int QueryFluxosAtendimento();
    int ConsultaGruposAbertura(long idContato) const;
    int ConsultaGruposDisponiveis(long idAtividadePrm,long idContatoPrm);
    int ConsultaGruposAssociados(long idAtividadePrm,long idContatoPrm);    
    int ConsultaTipoCarteira(long idContatoPrm,long idGrupoPrm,long idTpFechamento) const;
    int ConsultaTipoCarteiraAssoc(long idContatoPrm,long idGrupoPrm,long idTpFechamento) const;
    int ConsultaGruposTpFechamento(long idContatoPrm,long idTpFechamento) const;
    int ConsultaGruposTpFechamentoAssoc(long idContatoPrm,long idTpFechamento) const;
    int ConsultaTipoLinha(long idContatoPrm,long idGrupoPrm,long idTpFechamentoPrm) const;
    int ConsultaTipoLinhaAssoc(long idContatoPrm,long idGrupoPrm,long idTpFechamentoPrm) const;
    int ConsultaTipoPessoa(long idContatoPrm,long idGrupoPrm,long idTpFechamentoPrm) const;
    int ConsultaTipoPessoaAssoc(long idContatoPrm,long idGrupoPrm,long idTpFechamentoPrm) const;
    int ConsultaSegmentacao(long idContatoPrm,long idGrupoPrm,long idTpFechamentoPrm) const;
    int ConsultaSegmentacaoAssoc(long idContatoPrm,long idGrupoPrm,long idTpFechamentoPrm) const;
    int ConsultaTipoRelacion(long idContatoPrm,long idGrupoPrm,long idTpFechamentoPrm) const;
    int ConsultaTipoRelacionAssoc(long idContatoPrm,long idGrupoPrm,long idTpFechamentoPrm) const;
    long TotalRegistros(long idContatoPrm,long idGrupoPrm,long idTipoFechamentoPrm,
                        long *idTipoLinhaPrm,long *idSegmentacaoPrm,long *idTipoCarteiraPrm,
                        long *idTipoPessoaPrm,long *idTipoRelacionamentoPrm) const;
    long RegistroAtual(long idContatoPrm,long idGrupoPrm,long idTipoFechamentoPrm) const;
    bool RegistrosInconsistentes(long idContatoPrm,long idGrupoPrm,long idTipoFechamentoPrm,
                                 long *idTipoLinhaPrm,long *idSegmentacaoPrm,
                                 long *idTipoCarteiraPrm,long *idTipoPessoaPrm,
                                 long *idTipoRelacionamentoPrm) const;

private:
	long QueryTipoRetornoContato();
	int QueryGrupos();
	int QueryGruposContato();
	int QueryInContato();
	int QueryTypes( char,char* );
	XMLGen*pxml;
	char ctype,*pnode,xtra;
};

class GruposWriter: public TuxHelper, public GruposBase
{
public:
	GruposWriter( char* );
	~GruposWriter();
	long QueryFaseFechamento( 
        long idContato, 
        string idGruposAbertura );
	int CleanPreviousRetornoSeq( string & ) const;
	int CleanPreviousNivelSeq( string & ) const;
	int CleanPreviousData( string & ) const;
	int CleanPreviousNiveis( string & ) const;
	int CleanPreviousSequencia( string & ) const;
	int CleanPreviousFluxoFase( string & ) const;
	int CleanFaseGrupoFechamento( long * ) const;
    int RemoveTpRetornoSeq( int * );
    int RemoveNivelSeq( int * );
    int RemoveNiveis( int * );

	int TemAtendimentosAbertos(
								long idContatoParam,
								long idGrupoParam
							  );

	int TemNivelSequencia( long idSequenciaParam );
	
    int WorkWithGrupo( DOMNode*, 
        long, 
        long, 
        CGruposLst & );
	
    int WriteFluxoAtendimento(long sIdContato
                             ,const char *sgFluxoAtendimento);

    int WriteGrupos( DOMNode*, 
        long, 
        long, 
        CGruposLst & 
        );
	
    int BatchWriter( DOMNode* );
    int LimpaFluxoFaseGrupo( 
                                long idContatoParam, 
                                long idAtividadeParam, 
                                long idGrupoParam 
                           ) const;
    int AtualizaFluxoFaseGrupo( 
                                int idContatoPrm, 
                                int idGrupoPrm, 
                                int idTipoFechamentoPrm, 
                                int idTipoLinhaPrm, 
                                int idSegmentacaoPrm, 
                                int idTipoCarteiraPrm, 
                                int idTipoPessoaPrm, 
                                int idTipoRelacionamentoPrm 
                              );

    int OrganizaFluxoFaseGrupo( 
        long idFaseFechamentoPrm
        ) const;

    int InsereFluxoFaseGrupo( 
        long  idContatoPrm, 
        long  idGrupoPrm,
        long  idTipoFechamentoPrm,
        long *idFaseGrupoFechamentoPrm
        ) const;

    bool FechamentoTipoLinha( 
        long  idFaseGrpFechamParam, 
        long *idTipoLinhaParam 
        ) const;

    bool FechamentoSegmentacao( 
        long idFaseGrpFechamParam, 
        long *idSegmentacaoParam 
        ) const;

    bool FechamentoTipoCarteira( 
         long  idFaseGrpFechamParam, 
         long *idTipoCarteiraParam 
         ) const;

    bool FechamentoTipoPessoa( 
        long idFaseGrpFechamParam, 
        long *idTipoPessoaParam 
        ) const;

    bool FechamentoTipoRelac( 
        long idFaseGrpFechamParam, 
        long *idTipoRelacParam 
        ) const;
        
	int CleanSkill( long idContatoParam, long *idTipoSequenciaArrParam, long *idGrupoArrParam ) const;

private:
	char * idUsr;
};

class NiveisRequest
{
public:
	NiveisRequest( XMLGen* );
	~NiveisRequest();
	int QueryData( char* );
private:
	int QueryLevels();
	int QuerySpecificLevel( long );
	XMLGen*pxml;
	char*pnode;
};

class NiveisWriter: public TuxHelper
{
public:
	NiveisWriter( char* );
	~NiveisWriter();
	int CleanPreviousData( long ) const;
	int BatchWriter( DOMNode* );
	int WorkWithNivel( DOMNode*, long );
private:
	char * idUsr;
};

#endif
