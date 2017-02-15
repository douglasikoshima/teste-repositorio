#ifndef STPALITAGEM
    #define STPALITAGEM

typedef struct
{
    int idUFOperadora;
    int idContato;
    short idContatoFlag;
    long idPessoaUsuarioAbertura;
    short idPessoaUsuarioAberturaFlag;
    int idGrupoAbertura;
    short idGrupoAberturaFlag;
    int idUsuarioAlteracao;
    short idUsuarioAlteracaoFlag;
} TDadosQuery;

#endif
