var mensagens = new Mensagens();
var staticMaps = new Map();

function Mensagens(){};
Mensagens.prototype.Alteracoessalvasucesso = null;
Mensagens.prototype.Alterar = null;
Mensagens.prototype.AlterarnovoGrupoServicos = null;
Mensagens.prototype.Anterior = null;
Mensagens.prototype.AssociarModelo = null;
Mensagens.prototype.ate = null;
Mensagens.prototype.Ativo = null;
Mensagens.prototype.AUFsselecionadas = null;
Mensagens.prototype.Avulso = null;
Mensagens.prototype.Classificacao = null;
Mensagens.prototype.Codigo = null;
Mensagens.prototype.Cor = null;
Mensagens.prototype.Dependente = null;
Mensagens.prototype.DescProduto = null;
Mensagens.prototype.DescProdutoCatalogo = null;
Mensagens.prototype.DescricaoProdutoCatalogo = null;
Mensagens.prototype.Detalhes = null;
Mensagens.prototype.Disp = null;
Mensagens.prototype.Disponivel = null;
Mensagens.prototype.de = null;
Mensagens.prototype.Excluir = null;
Mensagens.prototype.Fabricante = null;
Mensagens.prototype.GrupoServicos = null;
Mensagens.prototype.Inativo = null;
Mensagens.prototype.Incluir = null;
Mensagens.prototype.Indisponivel = null;
Mensagens.prototype.LimiteparaAtivacoes = null;
Mensagens.prototype.Limitemaximodependentes=null;
Mensagens.prototype.Maximo = null;
Mensagens.prototype.Modelo = null;
Mensagens.prototype.Mostrandode = null;
Mensagens.prototype.Nao = null;
Mensagens.prototype.NaoAUFsselecionadas = null;
Mensagens.prototype.Naoforamencontradosregistros = null;
Mensagens.prototype.Erro01 = null;
Mensagens.prototype.Erro07 = null;
Mensagens.prototype.NomeComercial = null;
Mensagens.prototype.NomeTecnico = null;
Mensagens.prototype.Padrao = null;
Mensagens.prototype.RegionaisUFs = null;
Mensagens.prototype.Registros=null;
Mensagens.prototype.Proximo=null;
Mensagens.prototype.Processando=null;
Mensagens.prototype.Sim = null;
Mensagens.prototype.Tecnologia = null;
Mensagens.prototype.Titular = null;
Mensagens.prototype.TipoProduto = null;
Mensagens.prototype.Todos = null;
Mensagens.prototype.UF=null;
Mensagens.prototype.Ultimo=null;
Mensagens.prototype.WiFiAtifivo=null;



// ERROS

Mensagens.prototype.E01=null;//Campo Obrigatório
Mensagens.prototype.E02=null;//Campo e-mail invalido
Mensagens.prototype.E03=null;//Data invalida
Mensagens.prototype.E04=null;//valor maximo excedido
Mensagens.prototype.E05=null;//valor minimo insuficiente
Mensagens.prototype.E06=null;//valor A nao deve ser Menor que B

function Map(){}

Map.prototype.grupoServicos = null;
Map.prototype.RegionaisUFs = new Array();
Map.prototype.UFsSelecionadas = new Array();
staticMaps.camposValidado = true;

function RegionaisSel(){}

RegionaisSel.prototype.nmRegional = null;
RegionaisSel.prototype.idRegional = null;
RegionaisSel.prototype.UFs = new Array();

function UF(){}

UF.prototype.nmUf = null;
UF.prototype.idUf = null;