/***************************************************************************************************
 *--Uteis-Commons--SmallString.h--Novembro/2004--Cassio
 *--Versão reduzida de uma classe de manipulação de strings C.
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:20 $
 **/

/***************************************************************************************************
 *  SmallString não foi feita com a intenção de substituir o template 'string' do C++.
 *  A razão da sua criação se deveu ao fato de que na data da sua criação havia a 
 *  necessidade de alguma coisa que pudesse nos ajudar com a manipulação de strings
 *  C e não poderíamos usar templates por regra de arquitetura. Assim SmallString foi 
 *  criada como uma pequena classe de objetos com algumas funções básicas para uso
 *  diário e simples. Construções do tipo:
 *
 *  { 
 *      SmallString ss;
 *  
 *      for(in x=0;x<1000000;x++)
 *      {
 *          ss += "aaa";
 *      }
 *  }
 *  
 *  deveriam ser evitadas, pois apesar de funcionar sem problemas, o tempo de
 *  processamento se tornará impraticável. A iteração de concatenação de áreas a uma string
 *  existente pode ser usada mas com bom senso e é impossível dizer qual o limite máximo
 *  para isto; cada um deve balancear no momento o que é mais importante: performance ou 
 *  produtividade somada a segurança.
 *  O ideal é a criação de objetos "quase" estáticos, do ponto de vista onde poucas 
 *  concatenações são feitas para evitar problemas de performance.
 *
 * */

#ifndef __SMALLSTRING_H__
#define __SMALLSTRING_H__

#include "msgPadrao.h"

struct SmallString
{
    char *valor;
    int tamAlocado;
    int size;

    SmallString() 
    {
        this->size=0;
        this->valor = 0;
        this->tamAlocado = 0; 
    }

    SmallString(char *x) { this->valor=0;operator=((const char *)x); }
    SmallString(const char *x) { this->valor=0;operator=(x); }
    ~SmallString() { delete this->valor; }

    int Size() { return this->size; }
    void Clean() { delete this->valor; this->valor=0; this->size=this->tamAlocado=0; }

    char *c_str() { return this->valor; }

    SmallString& operator=(const char *x)
    {
        if ( !x || !*x ) return *this;

        delete this->valor;

        this->tamAlocado = strlen(x) + 1;

        this->valor = new char [this->tamAlocado];

        //if ( !this->valor ) throw new TuxBasicSvcException("00E0000",erroFalhaAlocacaoMemoria());

        strcpy(this->valor,x);

        this->size = strlen(this->valor);

        return *this;
    }

    SmallString& operator+(const char *x)
    {
        if ( !x || !*x ) return *this;

        char *temp = 0;
        register int tamDestino = this->valor ? strlen(this->valor) : 0;
        register int tamBloco = strlen(x);

        // Se tamanho alocado em destino é insuficiente, aloca mais espaço
        if ( tamDestino + tamBloco >= this->tamAlocado )
        {
            if ( tamDestino )
            {
                temp = new char [tamDestino + 1];
                //if ( !temp ) throw new TuxBasicSvcException("00E0000",erroFalhaAlocacaoMemoria());
                strcpy(temp,this->valor);
                delete this->valor;
            }

            this->tamAlocado = tamDestino + tamBloco + 256;
            this->valor = new char [ this->tamAlocado ];
            //if ( !this->valor ) throw new TuxBasicSvcException("00E0000",erroFalhaAlocacaoMemoria());
            *this->valor = 0;

            if ( temp )
            {
                strcpy(this->valor,temp);
                delete temp;
            }
        }

        strcat(this->valor,x);

        this->size = strlen(this->valor);

        return *this;
    }

    SmallString& operator+=(const char *x)
    {
        operator+(x);
        return *this;
    }

    SmallString& operator+=(int x)
    {
        char temp[32];
        sprintf(temp,"%d",x);
        operator+(temp);
        return *this;
    }

    SmallString& operator+(int x)
    {
        char temp[32];
        sprintf(temp,"%d",x);
        operator+(temp);
        return *this;
    }

    SmallString& operator=(int x)
    {
        char temp[32];
        sprintf(temp,"%d",x);
        operator=(temp);
        return *this;
    }

    SmallString& operator+=(unsigned long x)
    {
        char temp[32];
        sprintf(temp,"%lu",x);
        operator+(temp);
        return *this;
    }

    SmallString& operator+(unsigned long x)
    {
        char temp[32];
        sprintf(temp,"%lu",x);
        operator+(temp);
        return *this;
    }

    SmallString& operator=(unsigned long x)
    {
        char temp[32];
        sprintf(temp,"%lu",x);
        operator=(temp);
        return *this;
    }

    SmallString& operator+=(char x)
    {
        char temp[2] = { x,0 };
        operator+(temp);
        return *this;
    }

    SmallString& operator+(char x)
    {
        char temp[2] = { x,0 };
        operator+(temp);
        return *this;
    }

    SmallString& operator=(char x)
    {
        char temp[2] = { x,0 };
        operator=(temp);
        return *this;
    }

    SmallString& operator=(SmallString *x)
    {
        operator=(x->valor);
        return *this;
    }

    SmallString& operator=(SmallString &x)
    {
        operator=(x.valor);
        return *this;
    }

    SmallString& operator+(SmallString &x)
    {
        operator+(x.valor);
        return *this;
    }

    SmallString& operator+=(SmallString &x)
    {
        operator+(x.valor);
        return *this;
    }

    operator const char*() { return this->valor; }
    operator char*() { return this->valor; }
};

#endif
