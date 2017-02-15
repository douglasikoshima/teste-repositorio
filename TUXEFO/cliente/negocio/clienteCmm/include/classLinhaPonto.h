//-----------------------------------------------------------------------------------------
//* 
//* Classe: CLinhaPonto
//* 
//* Purpose: Classe para receber dados do servico Atyls
//*
//* Relacao com outra(s) classe(s):
//*
//* Instrucoes de uso: Os atributos desta classe sao do char*.
//*
//* Public part:
//*     Metodos:
//*             void setNrCodArea(char*);
//*             void setNrLinha(char*);
//*             char* getNrCodArea(void);
//*             char* getNrLinha(void);
//*
//* Protegida part : nenhuma
//*
//* Private part:
//*             char sNrCodArea[LEN_NUMBER + LEN_EOS];
//*             char sNrLinha[LEN_NUMBER + LEN_EOS];
//-----------------------------------------------------------------------------------------
#ifndef CLASSLINHAPONTO
#define CLASSLINHAPONTO

#include "Global.h"

class CLinhaPonto{
public:
    /* Construtor/Destrutor */
    CLinhaPonto();
    virtual ~CLinhaPonto();

    //Metodos de acessos aos atributos
    //Setters
    void setNrCodArea(char*);
    void setNrLinha(char*);
    //Getters
    char* getNrCodArea(void);
    char* getNrLinha(void);

private:
    char sNrCodArea[LEN_NUMBER + LEN_EOS];
    char sNrLinha[LEN_NUMBER + LEN_EOS];
};

#endif
