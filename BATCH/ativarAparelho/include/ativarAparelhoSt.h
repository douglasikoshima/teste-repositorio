

#ifndef ATIVARAPARELHOST
#define ATIVARAPARELHOST

struct ParametrosCOMSAP
{
    char idPessoa[64];
    char idGrupo[64];
    char nmCliente[256];
    char nrLinha[32];
    char sgDocumento[7];
    char nrDocumento[32];
    char idSegmentacao[64];
    char nmPessoaReceb[256];
    char nmRuaCliente[256];
    char nmBairroCliente[256];
    char nmCidadeCliente[256];
    char nrCEPCliente[16];
    char sgEstadoCliente[3];
    char codPaisCliente[32];
    char nmRuaEntrega[256];
    char nmBairroEntrega[256];
    char nmCidadeEntrega[256];
    char nrCEPEntrega[16];
    char sgEstadoEntrega[3];
    char codPaisEntrega[32];
    char idAparelho[64];
    char cdPagto[7];
    char cdMaterial[32];
    char qtdVendida[12];
    char dtRemessa[32];
    char vlPedido[64];
    char cdMeioPagto[12];
    char dsObservacao[256];
    char nrLinhaRecado[32];
};

#endif
