#ifdef WIN32
#pragma warning(disable:4514 4786)
#endif

#include "../include/MapErrDefs.h"
#include "../include/MapaErros.h"

//=============================================================================
///////////////////////////////////////////////////////////////////////////////
// Inicializações
//=============================================================================
bool MapaErros::mapaErrosInicializado = false;
MAP_ERROS MapaErros::mapErros;

// Cuidado: Precisa instanciar DEPOIS da declaração dos mapas utilizada logo acima
MapaErros mapaErros;

//=============================================================================
///////////////////////////////////////////////////////////////////////////////
// Implementação dos métodos da classe
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
   mapErros[ERRO_CARGA_CONTATOS] = "Arvore de contatos não carregada";
   mapErros[ERRO_CARGA_PALITAGEM] = "Folhas de contatos não carregadas";
   mapErros[ERRO_DATA_INICIO] = "'Data1' não informada";
   mapErros[ERRO_DATA_FIM] = "'Data2' não informada";
   mapErros[ERRO_NIVEL] = "'Nivel' não informado";
   mapErros[ERRO_REL_NIVEL_0] = "Falha na execução do relatório nível zero";
   mapErros[ERRO_ARVORE] = "Nó não encontrado na arvore de contatos";
   mapErros[ERRO_AGRUPAMENTO] = "Tamanho de buffer incoerente com a operacao sendo executada";
   mapErros[ERRO_DATA_INVALIDA] = "'Data1','Data2' ou 'DataHoje' não compatíveis com conteúdo da massa de pesquisa";
   mapErros[ERRO_TOTAL] = "Provavel erro de lógica no programa: Linha de total ainda não gerada";
   mapErros[ERRO_CHAVE_INVALIDA] = "Chave de pesquisa não encontrada";
   mapErros[ERRO_FALTA_SG_UF] = "'sgUF' não informada";
   mapErros[ERRO_INIT_DATA] = "Inicialização de datas falhou";
   mapErros[ERRO_FALTA_CONTATO] = "'idContatoPai' não informado";
   mapErros[ERRO_INDEFINIDO] = "Erro nao identificado";

   MapaErros::mapaErrosInicializado = true;

   }
