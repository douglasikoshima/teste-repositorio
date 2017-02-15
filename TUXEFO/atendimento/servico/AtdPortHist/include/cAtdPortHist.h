


#ifndef __CATDPORTHIST_H__
#define __CATDPORTHIST_H__

#include <string>
using namespace std;

#include <tuxfw.h>


#ifdef WIN32
#pragma warning(disable:4786)
#endif
#include <stdio.h>
#include <stdlib.h>
#include <tuxfw.h>




class cAtdPortHist : public TuxBasicSvc
{
    public:
        DOMNode * entrada;
        XMLGen  * saida;

    public:
        string codUsuario;
        char s_cdArea[5];
        char s_nrLinha[16];

        cAtdPortHist( DOMNode * dnode, XMLGen * xml_g, char * user )
        {
            saida = xml_g;
            entrada = dnode;
            codUsuario = user?user:"";
            nomeServico = "ATDPORTHIST";
            descricaoServico = "Consulta do Historico de Portabilidade";
        }

        ~cAtdPortHist() {}

    public:
        void Executar();

    private:
        XMLGen pXMLGen;

        string nomeServico;
        string descricaoServico;

        void CarregarDados();
        void ListaHistPortabilidade();

        inline char *getNomeServico() { return (char*)nomeServico.c_str(); }
        inline char *getDescricaoServico() { return (char*)descricaoServico.c_str(); }

};

#endif