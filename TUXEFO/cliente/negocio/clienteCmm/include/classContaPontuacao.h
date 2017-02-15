#ifndef CLASSCONTAPONTUACAO
#define CLASSCONTAPONTUACAO

#include <tuxfw.h>
#include "Global.h"
#include "classLinhaPonto.h"

#define MSG_ERR_MEMORIA		"Erro de alocação de memória"
#define NRO_ERR_MEMORIA		"24E0359"

class CContaPontuacao{
public:
       /* Construtor/Destrutor */
    CContaPontuacao();
    virtual ~CContaPontuacao();

    static CContaPontuacao *RecuperarTodos(int*, char*, char*);

    //Metodos de acessos aos atributos
    //Setters
    void setSaldo(char*);
    void setNrConta(char*);
    void setLinhaPontos(CLinhaPonto*);
    void setQtdLinhaPontos(long);
    //Getters
    char* getSaldo(void);
    char* getNrConta(void);
    long  getQtdLinhaPontos(void);
    CLinhaPonto* getLinhaPontos(void);

private:
    long lQtdLinhaPontos;
    char sSaldo[LEN_NUMBER + LEN_EOS];
    char sNrConta[LEN_NRCONTA + LEN_EOS];
    CLinhaPonto* pObjLinhas;
};

#endif

