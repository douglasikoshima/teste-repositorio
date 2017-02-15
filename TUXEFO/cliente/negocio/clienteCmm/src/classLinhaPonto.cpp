//---------------------------------------------------------------------
//*
//* Class: classLinhaPonto
//---------------------------------------------------------------------
//* Purpose:
//*
//* Utilizada pela classe ContaPontuacao. 
//* Obtem todas as linha pontos de uma determinada ContaPontuacao.
//* 
//* Review:
//*
//* Task force to seek potential memory leaks and exception errors - March,2005 - Cassio
//---------------------------------------------------------------------

#include <tuxfw.h>
#include "../include/Exception.h"

#include "../include/Global.h"
#include "../include/classLinhaPonto.h"

//
// Construtor e Destrutor
CLinhaPonto::CLinhaPonto()
{
    memset(sNrCodArea, 0x00, sizeof(sNrCodArea));
    memset(sNrLinha,   0x00, sizeof(sNrLinha));
}

CLinhaPonto::~CLinhaPonto(){}

//
// Metodos getter
char* CLinhaPonto::getNrCodArea(void)
{
    return ((char*)sNrCodArea);
}
char* CLinhaPonto::getNrLinha(void)
{
    return ((char*)sNrLinha);
}

//
// Metodos setter
void CLinhaPonto::setNrCodArea(char* pDado)
{
    strcpy(sNrCodArea, pDado?pDado:"");
}
void CLinhaPonto::setNrLinha(char* pDado)
{
    strcpy(sNrLinha, pDado?pDado:"");
}

