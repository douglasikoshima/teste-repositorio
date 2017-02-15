/***************************************************************************************************
 *--Uteis-Commons--SmallString.h--Novembro/2004--Cassio
 *--Versão reduzida de uma classe de manipulação de strings C.
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.3 $
 * @CVS     $Author: rteixeir $ - $Date: 2005/03/24 20:19:17 $
 **/

#ifndef __SMALLSTRING_H__
#define __SMALLSTRING_H__

#include <msgPadrao.h>

struct SmallString
{
    char *valor;
    int tamAlocado;
    int size;

    SmallString() { size=0; valor = 0; tamAlocado = 0; }
    SmallString(char *x) { operator=(x); }
    SmallString(const char *x) { operator=(x); }
    ~SmallString() { if ( this->valor ) delete this->valor; }

    int Size() { return this->size; }
    void Clean() { if ( this->valor ) { delete this->valor; this->valor=0; this->size=this->tamAlocado=0; } }

    char *c_str() { return this->valor; }

    SmallString& operator=(const char *x)
    {
        if ( !x || !*x ) return *this;

        if ( this->valor ) delete this->valor;

        this->tamAlocado = strlen(x) + 1;

        this->valor = new char [this->tamAlocado];

        if ( !this->valor ) throw;

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
                if ( !temp ) throw;
                strcpy(temp,this->valor);
                delete this->valor;
            }

            this->tamAlocado = tamDestino + tamBloco + 256;
            this->valor = new char [ this->tamAlocado ];
            if ( !this->valor ) throw;
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
