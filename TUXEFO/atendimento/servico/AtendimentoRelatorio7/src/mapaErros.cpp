#ifdef WIN32
#pragma warning(disable:4514 4786)
#endif

#include "../include/MapErrDefs.h"
#include "../include/MapaErros.h"

//=============================================================================
///////////////////////////////////////////////////////////////////////////////
// Inicializa��es
//=============================================================================
bool MapaErros::mapaErrosInicializado = false;
MAP_ERROS MapaErros::mapErros;

// Cuidado: Precisa instanciar DEPOIS da declara��o dos mapas utilizada logo acima
MapaErros mapaErros;

//=============================================================================
///////////////////////////////////////////////////////////////////////////////
// Implementa��o dos m�todos da classe
//=============================================================================

MapaErros::MapaErros()
   {
   inicializarMapaErros();
   }

const char *MapaErros::obterMensagemErro(long codErro)
   {
   const char *descricaoErro = "Erro nao mapeado ocorrido";

   MAP_ERROS::iterator nn_i = mapErros.find(codErro);

   if ( nn_i != mapErros.end() )
      {
      return nn_i->second;
      }

   return descricaoErro;
   }

void MapaErros::inicializarMapaErros()
   {

   if ( MapaErros::mapaErrosInicializado )
      {
      return;
      }

   //=============================================================================
   // Erros comuns a todas as interfaces de todos os componentes
   //=============================================================================
   mapErros[ERRO_CARGA_CONTATOS] = "Arvore de contatos n�o carregada";
   mapErros[ERRO_CARGA_PALITAGEM] = "Folhas de contatos n�o carregadas";
   mapErros[ERRO_DATA_INICIO] = "'Data1' n�o informada";
   mapErros[ERRO_DATA_FIM] = "'Data2' n�o informada";
   mapErros[ERRO_NIVEL] = "'Nivel' n�o informado";
   mapErros[ERRO_REL_NIVEL_0] = "Falha na execu��o do relat�rio n�vel zero";
   mapErros[ERRO_ARVORE] = "N� n�o encontrado na arvore de contatos";
   mapErros[ERRO_AGRUPAMENTO] = "Tamanho de buffer incoerente com a operacao sendo executada";
   mapErros[ERRO_DATA_INVALIDA] = "'Data1','Data2' ou 'DataHoje' n�o compat�veis com conte�do da massa de pesquisa";
   mapErros[ERRO_TOTAL] = "Provavel erro de l�gica no programa: Linha de total ainda n�o gerada";
   mapErros[ERRO_CHAVE_INVALIDA] = "Chave de pesquisa n�o encontrada";
   mapErros[ERRO_FALTA_SG_UF] = "'sgUF' n�o informada";
   mapErros[ERRO_INIT_DATA] = "Inicializa��o de datas falhou";
   mapErros[ERRO_FALTA_CONTATO] = "'idContatoPai' n�o informado";
   mapErros[ERRO_INDEFINIDO] = "Erro nao identificado";

   MapaErros::mapaErrosInicializado = true;

   }
