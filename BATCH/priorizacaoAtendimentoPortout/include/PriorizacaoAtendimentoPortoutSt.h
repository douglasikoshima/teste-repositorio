/**
 * 
 * @modulo  Batch
 * @usecase Batch
 * @author  Cassio M Garcia
 * @version $Revision: 1.1.4.2 $
 * @CVS     $Author: cmgarcia $ - $Date: 2008/08/12 15:31:04 $
 **/

#ifndef PRIORIZACAOATENDIMENTOPORTOUTST
#define PRIORIZACAOATENDIMENTOPORTOUTST

struct ParametrosCalculo
{
    int horaCorteCrit1;
    int horaCorteCrit2;
    
    int idAlertaAmarela;
    int qtTempoDecorridoCorAmarela;
    char nmCorAlertaAmarela[256];

    int idAlertaLaranja;
    int qtTempoDecorridoCorLaranja;
    char nmCorAlertaLaranja[256];

    int idAlertaVermelho;
    int qtTempoDecorridoCorVermelho;
    char nmCorAlertaVermelho[256];

    double ntContato;
    double ntProcedencia;
    double ntCarteira;
    double ntSegmentacao;
};

struct ParametrosCalculoStatus
{
    short horaCorteCrit1;
    short horaCorteCrit2;
    
    short idAlertaAmarela;
    short qtTempoDecorridoCorAmarela;
    short nmCorAlertaAmarela;

    short idAlertaLaranja;
    short qtTempoDecorridoCorLaranja;
    short nmCorAlertaLaranja;

    short idAlertaVermelho;
    short qtTempoDecorridoCorVermelho;
    short nmCorAlertaVermelho;

    short ntContato;
    short ntProcedencia;
    short ntCarteira;
    short ntSegmentacao;
};
 
#endif
