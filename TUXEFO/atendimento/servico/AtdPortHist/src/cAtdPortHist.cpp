

#include "../include/cAtdPortHist.h"

extern bool proCConsultaHistPortabilidade( char * s_cdAreaPrm, char * s_nrLinhaPrm, XMLGen * saida );

void cAtdPortHist::Executar()
{
    ULOG_START("cAtdPortHist::Executar()");


    CarregarDados();
    ListaHistPortabilidade();


    ULOG_END("cAtdPortHist::Executar()");
}



void cAtdPortHist::CarregarDados()
{
    ULOG_START("cAtdPortHist::CarregarDados()");

    char *p;

    if (p=walkTree(entrada, "cdAreaRegistro", 0 ),p)
    {
        strcpy( s_cdArea,p );
        ULOG("cdArea = [%s]",s_cdArea );

        XMLString::release(&p);
    }

    if (p=walkTree(entrada, "nrLinha", 0 ),p)
    {
        strcpy( s_nrLinha,p );
        ULOG("nrLinha = [%s]", s_nrLinha );

        XMLString::release(&p);
    }

    ULOG_END("cAtdPortHist::CarregarDados()");
}



void cAtdPortHist::ListaHistPortabilidade()
{
    ULOG_START("cAtdPortHist::ListaHistPortabilidade()");


    proCConsultaHistPortabilidade( s_cdArea, s_nrLinha, saida );


    ULOG_END("cAtdPortHist::ListaHistPortabilidade()");
}
