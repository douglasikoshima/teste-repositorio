/***************************************************************************************************
 *--Uteis-Commons--SmallString.h--Novembro/2004--Cassio
 *--Vers�o reduzida de uma classe de manipula��o de strings C.
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:20 $
 **/

/***************************************************************************************************
 *  SmallString n�o foi feita com a inten��o de substituir o template 'string' do C++.
 *  A raz�o da sua cria��o se deveu ao fato de que na data da sua cria��o havia a 
 *  necessidade de alguma coisa que pudesse nos ajudar com a manipula��o de strings
 *  C e n�o poder�amos usar templates por regra de arquitetura. Assim SmallString foi 
 *  criada como uma pequena classe de objetos com algumas fun��es b�sicas para uso
 *  di�rio e simples. Constru��es do tipo:
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
 *  processamento se tornar� impratic�vel. A itera��o de concatena��o de �reas a uma string
 *  existente pode ser usada mas com bom senso e � imposs�vel dizer qual o limite m�ximo
 *  para isto; cada um deve balancear no momento o que � mais importante: performance ou 
 *  produtividade somada a seguran�a.
 *  O ideal � a cria��o de objetos "quase" est�ticos, do ponto de vista onde poucas 
 *  concatena��es s�o feitas para evitar problemas de performance.
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

        // Se tamanho alocado em destino � insuficiente, aloca mais espa�o
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
