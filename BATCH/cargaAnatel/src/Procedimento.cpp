#include "../include/Procedimento.hpp"


Procedimento::Procedimento() {
	log.setNivel(2);
	log.logDebug("inicializando o Procedimento Anatel");	
	this->historico = NULL;
}

Procedimento::~Procedimento() {
	log.logDebug("finalizando o Procedimento Anatel");
	atendimentoAnatel.disconnect();
	if (this->historico != NULL) {
		log.logError("arquivo de historico de log removido");
		delete this->historico;
	}	
	// desconectar do tuxedo
	log.logDebug("Desconectar tuxedo");
	tpterm(); 
}

void Procedimento::processar() {
	log.logDebug("iniciando o processo de carga anatel");
	
	list<Arquivo> arquivosZip;
	
	// carregar arquivo de configuração
	if ( !this->loadConfig() ) {
		log.logError("erro ao carregar arquivo de configuração");
		return;
	}
	
	// iniciar tuxedo
	if ( this->tuxInit() ) {
		log.logError("erro ao iniciar tuxedo");
		return;
	}
	
	// vamos procurar no diretório principal os arquivos zips para processar
	this->listarArquivos(this->inputDiretorio, arquivosZip, ".zip");
	
	// se lista for vazia vamos retornar erro
	if (arquivosZip.size() == 0) {
		log.logError("Não existe arquivos zip para processar");
		return;
	}
	
	// connectar ao oracle
	if (atendimentoAnatel.connect(this->userDatabase, this->passwordDatabase, this->oracleSID)) {
		log.logError("erro ao conectar ao oracle");
		return;
	}
	
	// carregar parametros da APOIO.PARAMETRO
	atendimentoAnatel.setIdAnatelSistemaOrigem(atendimentoAnatel.getParametro("ANATEL_SISTEMA_ORIGEM"));
	atendimentoAnatel.setIdAnatelGrupoAbertura(atendimentoAnatel.getParametro("GRUPO_ABERTURA_ANATEL"));
	atendimentoAnatel.setIdAnatelResponsavelAbertura(atendimentoAnatel.getParametro("ANATEL_RESPONSAVEL_ABERTURA"));
	atendimentoAnatel.setIdAnatelObservacao(atendimentoAnatel.getParametro("ANATEL_OBSERVACAO"));
	atendimentoAnatel.setIdAnatelTipoOperacao(atendimentoAnatel.getParametro("ANATEL_TIPO_OPERACAO"));
	atendimentoAnatel.setIdAnatelProcedencia(atendimentoAnatel.getParametro("ANATEL_PROCEDENCIA"));
	atendimentoAnatel.setIdAnatelCanal(atendimentoAnatel.getParametro("ANATEL_CANAL"));
	atendimentoAnatel.setIdAnatelUsuario(atendimentoAnatel.getParametro("ANATEL_USUARIO"));
	
	log.logDebug("=========== parametros APOIO.PARAMETRO =============");
	log.log("ANATEL_SISTEMA_ORIGEM = [%s]", atendimentoAnatel.getIdAnatelSistemaOrigem());
	log.log("GRUPO_ABERTURA_ANATEL = [%s]",atendimentoAnatel.getIdAnatelGrupoAbertura());
	log.log("ANATEL_RESPONSAVEL_ABERTURA = [%s]",atendimentoAnatel.getIdAnatelResponsavelAbertura());
	log.log("ANATEL_OBSERVACAO = [%s]",atendimentoAnatel.getIdAnatelObservacao());
	log.log("ANATEL_TIPO_OPERACAO = [%s]",atendimentoAnatel.getIdAnatelTipoOperacao());
	log.log("ANATEL_PROCEDENCIA = [%s]",atendimentoAnatel.getIdAnatelProcedencia());
	log.log("ANATEL_CANAL = [%s]",atendimentoAnatel.getIdAnatelCanal());
	log.log("ANATEL_USUARIO = [%s]",atendimentoAnatel.getIdAnatelUsuario());
		
	// percorrer a lista de arquivos zips e processar a carga
	while ( 0 < arquivosZip.size() ) {
		Arquivo arquivo = arquivosZip.front();
				
		// descompactar o arquivo zip				
		this->descompactar(arquivo);
		
		// processar os registros deste arquivo
		this->processarArquivo(arquivo);
		
		// ler dados gravados para abertura de processo de atendimento no vivonet
		this->registrarAtendimento(arquivo);
		
		
		// remove da lista
		arquivosZip.pop_front();
	}
		
	log.logDebug("finalizando o processo de carga anatel");
}

void Procedimento::registrarAtendimento(Arquivo &arquivo) {
	log.logDebug("iniciando a leitura dos dados gravados na base do vivonet");
	
	list<Atendimento> atendimentos;
	CContato contato;
	char idContatoPai[53];
	
	// inicializar idContatoPai
	memset(idContatoPai, 0, sizeof(idContatoPai));
	
	try {
	
		// procurar a raiz principal
		contato.getContatoByPath( "VIVO/ANATEL FOCUS", idContatoPai );
		
		log.log("idcontato raiz anatel focus = %s", idContatoPai);		
		
	} catch(TuxBasicOraException tboe) {
		log.logError("Erro ao consultar path de VIVO/ANATEL FOCUS");
		return;
	}
	
	// recuperar as solicitações
	atendimentoAnatel.selectAtendimentoAnatel( atendimentos, arquivo.getNome());
	
	// percorrer os atendimentos para criar contato e abrir protocolo
	while ( 0 < atendimentos.size() ) {
			
		char idContato[53];
		int contatoNovo = 0;
	
		// inicializar idContato		
		memset(idContato, 0, sizeof(idContato));
		
		Atendimento atendimento = atendimentos.front();
		
		log.log("idAtendimentoAnatel = %s", atendimento.getIdAtendimentoAnatel());
		log.log("nrSolicitacao = %s", atendimento.getNrSolicitacao());
		log.log("dsTipoAtendimentoAnatel = %s", atendimento.getDsTipoAtendimentoAnatel());
		log.log("dsServico = %s", atendimento.getDsServico());
		log.log("dsModalidade = %s", atendimento.getDsModalidade());
		log.log("dsMotivo = %s", atendimento.getDsMotivo());
		log.log("dsSubMotivo = %s", atendimento.getDsSubMotivo());
		log.log("dsCategoria = %s", atendimento.getDsCategoria());
		log.log("dsSubCategoria = %s", atendimento.getDsSubCategoria());
		log.log("nrTelefoneProblema = %s", atendimento.getNrTelefoneProblema());
		
		// path da árvore
		int length = strlen(atendimento.getDsMotivo()) +  strlen(atendimento.getDsSubMotivo()) + 
						 strlen(atendimento.getDsCategoria()) + strlen(atendimento.getDsSubCategoria()) + 25;
		char *path = (char*) malloc(length);
		memset(path, 0, length);
				
		// cadastrar o motivo
		sprintf(path, "VIVO/ANATEL FOCUS/%s", atendimento.getDsMotivo());
		this->incluirContato(path, atendimento.getDsMotivo(), "0", "1", idContatoPai, idContato);
		
		// cadastrar submotivo
		memset(path, 0, length);
		sprintf(path, "VIVO/ANATEL FOCUS/%s/%s", atendimento.getDsMotivo(), atendimento.getDsSubMotivo());
		this->incluirContato(path, atendimento.getDsSubMotivo(), "0", "1", idContatoPai, idContato);
		
		// cadastrar dsCategoria
		memset(path, 0, length);
		sprintf(path, "VIVO/ANATEL FOCUS/%s/%s/%s", atendimento.getDsMotivo(), atendimento.getDsSubMotivo(), atendimento.getDsCategoria());
		this->incluirContato(path, atendimento.getDsCategoria(), "0", "1", idContatoPai, idContato);
		
		// cadastrar dsSubCategoria
		memset(path, 0, length);
		sprintf(path, "VIVO/ANATEL FOCUS/%s/%s/%s/%s", atendimento.getDsMotivo(), atendimento.getDsSubMotivo(), atendimento.getDsCategoria(), atendimento.getDsSubCategoria());
		contatoNovo = this->incluirContato(path, atendimento.getDsSubCategoria(), "1", "1", idContatoPai, idContato);
		if ( contatoNovo == 0 ) {
			// recuperar contato genérico 
			if (!atendimentoAnatel.getContatoFuncionalidade("PROCESSO_ANATEL_FOCUS", idContato) ) {
				log.logDebug("erro ao recuperar idContato genérico");		
			} 
		}
		
		// liberando o path
		free(path);
		log.logDebug("liberando o path da árvore");	
		
		// vamos chamar o REGCONTATO para abertura de protocolo
		this->abrirProcessoAtendimento(atendimento, idContato);
		
		if (contatoNovo == 0) {
			// contabilizar relatorio de contatos genericos
			atendimentoAnatel.relatorioContatoGenerico(atendimento.getIdAtendimentoAnatelArquivo());
		} else {
			// contabilizar relatorio de contatos novos
			atendimentoAnatel.relatorioContatoNovo(atendimento.getIdAtendimentoAnatelArquivo());
		}
		
		atendimentos.pop_front();
	}
	
	// atualizar status de processamento para 1 - terminou de abrir a palitagem para todas as solicitações
	atendimentoAnatel.atualizarStatusArquivo(arquivo.getNome(), 1);
		

	log.logDebug("finalizando a leitura dos dados gravados na base do vivonet");
}


int Procedimento::abrirProcessoAtendimento(Atendimento &atendimento, char *idContato) {
	log.logDebug("inicio: abertura de processo");
	
	char xmlIn[4000 + 1];
	char xmlOut[2000 + 1];
	int errorNumber;
	char idTipoAberturaProtocolo[8];
	char *nrProtocolo = NULL;
	char *idAtendimento = NULL;
	char idLinhaTelefonica[256];
	char idTipoLinha[256];
	char idPessoa[256];
	char sgTipoPessoa[256];
	char *nrTelefoneProblema = atendimento.getNrTelefoneProblema();
	char nrLinha[16];
	char ddd[2+1];	
	char idConta[256];
	char idUfOperadora[256];	
	char idPessoadepara[256];
	
	// inicializar variaveis
	memset(nrLinha,0,sizeof(nrLinha));
	memset(ddd,0,sizeof(ddd));
	memset(idTipoAberturaProtocolo,0,sizeof(idTipoAberturaProtocolo));
	memset(idLinhaTelefonica,0,sizeof(idLinhaTelefonica));
	memset(idTipoLinha,0,sizeof(idTipoLinha));
	memset(idPessoa,0,sizeof(idPessoa));
	memset(sgTipoPessoa,0,sizeof(sgTipoPessoa));	
	memset(idConta,0,sizeof(idConta));
	memset(idUfOperadora,0,sizeof(idUfOperadora));	
	memset(idPessoadepara,0,sizeof(idPessoadepara));
	
    sprintf( ddd, "%.2s", nrTelefoneProblema);
    sprintf( nrLinha, "%s", (char*)&nrTelefoneProblema[2]);
	
	// se a linha existir o tipo de protocolo é igual a 3
	if ( atendimentoAnatel.verificaLinhaExistente(nrLinha, ddd, idLinhaTelefonica, 
				idTipoLinha, idPessoa, sgTipoPessoa, idConta, idPessoadepara) ) {
		log.logDebug("tipo de protocolo igual a 3");
		strcpy(idTipoAberturaProtocolo,"3");
		
		// atualizar relatório de protocolos abertos por cliente
		atendimentoAnatel.relatorioProtocoloCliente(atendimento.getIdAtendimentoAnatelArquivo());
		
	} else if ( atendimentoAnatel.verificaDocumentoExistente(atendimento, idPessoa) ) {
		log.logDebug("tipo de protocolo igual a 3");
		strcpy(idTipoAberturaProtocolo,"3");
		
		// atualizar relatório de protocolos abertos por cliente
		atendimentoAnatel.relatorioProtocoloCliente(atendimento.getIdAtendimentoAnatelArquivo());
		
	} else {
		log.logDebug("tipo de protocolo igual a 2");
		strcpy(idTipoAberturaProtocolo,"2");
		
		// atualizar relatório de protocolos abertos por não cliente
		atendimentoAnatel.relatorioProtocoloNaoCliente(atendimento.getIdAtendimentoAnatelArquivo());
	}
	
	// operadora
	if ( !atendimentoAnatel.getUFOperadora(idLinhaTelefonica, idPessoa, idUfOperadora)) {
		log.logDebug("erro ao recuperar idUfOperadora genérico");
	}
		
	// montar XML de entrada
	string xml = "<?xml version='1.0' encoding='ISO-8859-1'?>";
	       xml += "<msg><msgHdr><user>";
		   xml += atendimentoAnatel.getIdAnatelUsuario();
		   xml += "</user><service>REGCONTATO</service></msgHdr><msgBody>";
		   xml += "<DadosProtocoloVO>";
		   xml += "<idTipoAberturaProtocolo>";
		   xml += idTipoAberturaProtocolo;
		   xml += "</idTipoAberturaProtocolo><idSistemaOrigemProtocolo>";
		   xml += atendimentoAnatel.getIdAnatelSistemaOrigem();
		   xml += "</idSistemaOrigemProtocolo>";
		   xml += "<dddSMSProtocolo>";
		   xml += ddd;
		   xml += "</dddSMSProtocolo><nrLinhaSMSProtocolo>";
		   xml += nrLinha;
		   xml += "</nrLinhaSMSProtocolo></DadosProtocoloVO><idChamadaTelefonica>0</idChamadaTelefonica>";
		   xml += "<inAnatel>1</inAnatel><idGrupoAbertura>";
		   xml += atendimentoAnatel.getIdAnatelGrupoAbertura();
		   xml += "</idGrupoAbertura>";
		   xml += "<inResponsavelAbertura>";
		   xml += atendimentoAnatel.getIdAnatelResponsavelAbertura();
		   xml += "</inResponsavelAbertura>";
		   xml += "<nmContato></nmContato><observacao>";
		   xml += atendimentoAnatel.getIdAnatelObservacao();
		   xml += "</observacao>";
		   xml += "<nrTelefone>";
		   xml += nrTelefoneProblema;
		   xml += "</nrTelefone>";
		   xml += "<tpOperacao>";
		   xml += atendimentoAnatel.getIdAnatelTipoOperacao();
		   xml += "</tpOperacao>";
		   xml += "<idTipoLinha>";
		   xml += idTipoLinha;
		   xml += "</idTipoLinha>";
		   xml += "<inTipoPessoa>";
		   xml += sgTipoPessoa;
		   xml += "</inTipoPessoa>";
		   xml += "<idUfOperadora>";
		   xml += idUfOperadora;
		   xml += "</idUfOperadora>";
		   xml += "<ProcedenciaVO><idProcedencia>";
		   xml += atendimentoAnatel.getIdAnatelProcedencia();
		   xml += "</idProcedencia></ProcedenciaVO>";
		   xml += "<CanalVO><idCanal>";
		   xml += atendimentoAnatel.getIdAnatelCanal();
		   xml += "</idCanal></CanalVO>";
		   
		   if (strlen(idPessoadepara) > 0) {
			   xml += "<PessoaVO>";
			   xml += "<idPessoa>";
			   xml += idPessoadepara;
			   xml += "</idPessoa></PessoaVO>";	
		   }		   
		   
		   if (strlen(idConta) > 0) {
			   xml += "<Contas>";
			   xml += "<ContaVO>";
			   xml += "<idConta>";
			   xml += idConta;
			   xml += "</idConta>";
			   xml += "<LinhaVO>";
			   xml += "<idPessoaLinhaHistorico>";
			   xml += idLinhaTelefonica;
			   xml += "</idPessoaLinhaHistorico>";
			   xml += "</LinhaVO></ContaVO></Contas>";		
		   }
			
		   xml += "<ArvoreAtendimentoVO>";
		   xml += "<idContato>";
		   xml += idContato;
		   xml += "</idContato>";
		   xml += "<CarterizacaoVO>";
		   xml += "<idTipoCarteira>13";
		   xml += "</idTipoCarteira>";
		   xml += "</CarterizacaoVO>";
		   xml += "<SegmentacaoVO><idSegmentacao>1</idSegmentacao>";
		   xml += "</SegmentacaoVO>";
		   xml += "</ArvoreAtendimentoVO>";
	
		   xml += "</msgBody></msg>";

	// inicilizar
	memset(xmlIn, 0, sizeof(xmlIn));
	memset(xmlOut, 0, sizeof(xmlOut));
	strcpy( xmlIn, xml.c_str());
	
	log.log("XML de Entrada [%s]", xmlIn);
		   
	// vamos chamar o serviço REGCONTATO
	log.logDebug("vai chamar servico REGCONTATO");
	if ( !this->sendXML(xmlIn, xmlOut, &errorNumber) ) {
	
		log.logDebug("sucesso na chamada do serviço REGCONTATO");
		
		log.log("XML de Saida [%s]", xmlOut);
		
		XMLManager xmlManager;
		if (xmlManager.parseString(xmlOut) == 1) {
			TiXmlElement *element = xmlManager.getRootElement();			
			TiXmlNode *msgBody  = element->FirstChild("msgBody");
			TiXmlNode *atendimentoVO = msgBody->FirstChild("AtendimentoVO");
			idAtendimento = (char*) xmlReader.getNodeValue(atendimentoVO, "idAtendimento");
			nrProtocolo = (char*) xmlReader.getNodeValue(atendimentoVO, "nrProtocolo");
			log.log("idAtendimento = [%s]", idAtendimento);
			log.log("nrProtocolo = [%s]", nrProtocolo);	
			if (idAtendimento == NULL) {
				log.logDebug("erro ao abrir protocolo");
				atendimentoAnatel.gravarLog("erro ao abrir protocolo", atendimento);
			} else if ( atendimentoAnatel.atualizarAtendimento(atendimento.getIdAtendimentoAnatel(), idAtendimento) ) {
				log.logDebug("idatendimento atualizado com sucesso");
			} 
		}
		
	} else {
		log.logError("erro ao chamar serviço REGCONTATO");
	}
	
	log.logDebug("fim: abertura de processo");
	
	return 1;
}

int Procedimento::incluirContato(char *path, char *nmContato, char *inFolha, 
									char *idTipoArvore, char *idContatoPai, char *idContato) {
	log.logDebug("iniciando o cadastro de contato");
	
	CContato contato;
	char *cidNomeContato = NULL;
	char *idUser = "1";
	int iWarningNome = 0;
	int contatoRetorno = 0;
	int inserirContato = 1;
	
	try {
		// inicializar contato
		memset(idContato, 0, sizeof(idContato));
	
		// procurar o path
		if ( contato.getContatoByPath( path, idContato ) ) {
			log.log("achou o idcontato = %s", idContato);	
			strcpy(idContatoPai, idContato);
			log.log("idContatoPai = %s", idContatoPai);	
			return 0;
		}
				
	} catch(TuxBasicOraException tboe) {
		log.log("Erro ao consultar path %s", path);		
		return 0;
	}		
	
	
	log.log("vamos procurar o nome do contato %s", nmContato);
	
	try {
		contatoRetorno = contato.ProcuraNome( nmContato );
	} catch(TuxBasicOraException tboe) {
		log.log("Erro procurar nome de contato %s", nmContato);			
	}
	
	switch( contatoRetorno )
	{
		case 0://Nao achou o nome
			break;
		case 1://Achou o nome, mas eh diferente (maisculas e minisculas)
			iWarningNome++;
		case 2://Achou o nome exatamente igual (maisculas e minisculas)
			cidNomeContato = contato.getIdNome();
			break;
		default:
			break;
	}	
	
	try {
		inserirContato = contato.Insert( idContatoPai, cidNomeContato, nmContato, 
					"1", inFolha, idUser, idTipoArvore, idContato);
		if (inserirContato == 1) {
			if (strcmp(inFolha,"1") == 0) {
				inserirContato = contato.InsertContatoFolha(idContato, "0", "0", "1", "1", "120", "0", "1", "0"
			         ,"1", "120" ,"0" ,"" ,"0" ,"0" ,"1" ,"" ,"" ,"0" ,"1" ,"1" ,"MC" ,"MC3"
			      );
			}
			contato.commit();
		} else {
			contato.rollback();
		}

		
	} catch(TuxBasicOraException tboe) {
		log.logDebug("Erro ao inserir contato");	
		contato.rollback();
	}
	 
	strcpy(idContatoPai, idContato);
	log.log("idContatoPai = %s", idContatoPai);	
	
	log.logDebug("finalizado o cadastro de contato");
	
	return inserirContato;
}

void Procedimento::processarArquivo(Arquivo &arquivo) {
	log.logDebug("iniciando o processo de arquivo anatel");
	
	string arquivoXML;
	XMLManager xmlManager;
	
	// procurar xml principal
	findFileType(arquivo.getPathDescompactado(), arquivoXML, ".xml");
	
	char *xmlFile = (char*) arquivoXML.c_str();
	
	// percorrer XML de entrada e gravar registros
	if (xmlManager.parse(xmlFile) == 1) {
		log.logDebug("vamos ler as tags do XML");
		
		// node princial header e solicitacoes
		TiXmlElement *element = xmlManager.getRootElement();
		TiXmlNode *header = element->FirstChild("infoEnvio");
		TiXmlNode *solicitacoes = element->FirstChild("solicitacoes");
		char idAtendimentoAnatelArquivo[53]; // chave primária da tabela atendimento.atendimentoanatelarquivo
		memset(idAtendimentoAnatelArquivo, 0, sizeof(idAtendimentoAnatelArquivo));
		
		// gravar o arquivo
		if ( !atendimentoAnatel.inserirAtendimentoArquivo( header, idAtendimentoAnatelArquivo, arquivo.getNome()) ) {
			log.logError("Erro ao gravar arquivo anatel");
			return;
		} else {
			log.logError("criando arquivo de log de historico");
			
			// vamos deletar o historico se já existir
			if (this->historico != NULL) {
				log.logError("arquivo de log removido para recriar");
				delete this->historico;
			}
			
			// criar um Histórico novo para o arquivo zip
			this->historico = new Historico();			
			
			// montar o path do histórico
			string arquivoLog = this->outputDiretorio;
			arquivoLog += "/";
			arquivoLog += this->historico->getNome();
			log.log("arquivo de log %s", (char*) arquivoLog.c_str());						
			this->historico->setPath((char*) arquivoLog.c_str());
			
			// atualizar nome do arquivo gerado na tabela atendimento.atendimentoanatelarquivo
			atendimentoAnatel.atualizarArquivoLog((char*) arquivoLog.c_str(), idAtendimentoAnatelArquivo);
			
			// abrir o arquvo como gravação e append
			if ( !this->historico->open("w+") ) {
				log.log("erro ao abrir arquivo de log %s", (char*) arquivoLog.c_str());
				return;
			}
			this->historico->append("Data| idtSolicitacao| Data registro| telefone problema| inGravado| descrição\n");
		}
		
		// percorrendo a tag solicitacao
		TiXmlNode *node = NULL;
		while ( node =  solicitacoes->IterateChildren(node)) {
			 int type = node->Type();
			 // se for um elemento
			 if (type == TiXmlNode::ELEMENT) {		
								
				// gravar solicitação
				this->salvarNovaSolicitacao(node, arquivo, idAtendimentoAnatelArquivo);				
				
			 }
		}
		
	} else {
		log.logError("Erro no parse do arquivo XML");
	}
	
	log.logDebug("finalizando o processo de arquivo anatel");
}

void Procedimento::salvarNovaSolicitacao(TiXmlNode *node, Arquivo &arquivo, char *idAtendimentoAnatelArquivo) {
	log.logDebug("salvar nova solicitação");
	
	// sequence da tabela atendimento.atendimentoanatel
	char idAtendimentoAnatel[53];
	list<Arquivo> anexos;
	char *idtSolicitacao = (char*) xmlReader.getNodeValue(node,"idtSolicitacao");
	char *dataRegistro = (char*) xmlReader.getNodeValue(node,"dataRegistro");
	char *telefoneProblema = (char*) xmlReader.getNodeValue(node,"telefoneProblema");	
		
	atendimentoAnatel.setHistorico(this->historico);
	atendimentoAnatel.setIdtSolicitacao(idtSolicitacao);
	atendimentoAnatel.setDataRegistro(dataRegistro);
	atendimentoAnatel.setTelefoneProblema(telefoneProblema);
	
	// verificar se o campo obrigatório idtSolicitacao existe
	if (idtSolicitacao == NULL) {
		log.logError("campo obrigatório idtSolicitacao não encontrado, registro será rejeitado");
		
		// atualizar tabela atendimento.atendimentoanatel para relatório
		atendimentoAnatel.relatorioSolicitacaoRejeitada(idAtendimentoAnatelArquivo);
		
		// gravar no arquivo de log para download
		this->historico->appendText("%s|%s|%s|%d|%s", idtSolicitacao, dataRegistro, 
		telefoneProblema, 0, "código idtSolicitacao não encontrado");
		return;
	}
	
	// verificar se o registro pode ser gravado ou se vai ser rejeitado
	if (atendimentoAnatel.verificaSolicitacaoDuplicada(node)) {
	
		log.logError("solicitação duplicada");
		
		// atualizar tabela atendimento.atendimentoanatel para relatório
		atendimentoAnatel.relatorioSolicitacaoRejeitada(idAtendimentoAnatelArquivo);
		
		// gravar no arquivo de log para download
		this->historico->appendText("%s|%s|%s|%d|%s", idtSolicitacao, dataRegistro, 
		telefoneProblema, 0, "código idtSolicitacao duplicado");
		
		return;
	}	
	
	// gravar cada solicitacao
	if ( atendimentoAnatel.inserirAtendimento(node, idAtendimentoAnatel, idAtendimentoAnatelArquivo)) {
				
		TiXmlNode *dadosComplementares = node->FirstChild("dadosComplementares");		
		TiXmlNode *historicos = node->FirstChild("historicos");
		TiXmlNode *atividades = node->FirstChild("atividades");
		
		// contabilizar total de registros para relatório
		atendimentoAnatel.relatorioTotalRegistros(idAtendimentoAnatelArquivo);
		
		// vamos gravar os dados complementares		
		TiXmlNode *dadoComplementar = NULL;
		while ( dadoComplementar =  dadosComplementares->IterateChildren(dadoComplementar)) {
			 int type = dadoComplementar->Type();
			 // se for um elemento
			 if (type == TiXmlNode::ELEMENT) {		
								
				// gravar dados complementares
				if ( !atendimentoAnatel.inserirDadoComplementar(idAtendimentoAnatel, dadoComplementar) ) {
					// gravar log de erro caso não consiga gravar dados complementares
					this->historico->appendText("%s|%s|%s|%d|%s", idtSolicitacao, dataRegistro, 
					telefoneProblema, 1, "erro ao gravar dados complementares");
				}								
			 }
		}	

		// vamos gravar historico
		TiXmlNode *historico = NULL;
		while ( historico =  historicos->IterateChildren(historico)) {
			 int type = historico->Type();
			 // se for um elemento
			 if (type == TiXmlNode::ELEMENT) {		
								
				// gravar histórico
				if ( !atendimentoAnatel.inserirHistorico(idAtendimentoAnatel, historico) ) {
					// gravar log de erro caso não consiga gravar histórico
					this->historico->appendText("%s|%s|%s|%d|%s", idtSolicitacao, dataRegistro, 
					telefoneProblema, 1, "erro ao gravar dados de histórico");
				}								
			 }
		}	

		// gravar anexos
		string path = arquivo.getPathDescompactado();
		path += "/";
		path +=  xmlReader.getNodeValue(node,"idtSolicitacao");
		char *pathSolicitacao = (char*) path.c_str();		
		
		// vamos exibir o log do caminho
		log.logDebug( pathSolicitacao );
		
		// listar os anexos pdf
		this->listarAnexos( pathSolicitacao, anexos);
		
		// percorrer a lista de anexos e gravar no banco
		while( 0 < anexos.size() ) {
			Arquivo anexo = anexos.front();
			
			// inserir anexo
			if ( !atendimentoAnatel.inserirAnexo(idAtendimentoAnatel, anexo.getNome()) ) {
				// gravar log de erro
				log.logError("erro ao gravar anexo de atividade");
				this->historico->appendText("%s|%s|%s|%d|%s%s", idtSolicitacao, dataRegistro, 
				telefoneProblema, 1, "erro ao gravar anexo ", anexo.getNome());
			}
			
			anexos.pop_front();
		}
		
		// vamos gravar os dados de atividades	
		TiXmlNode *atividade = NULL;
		while ( atividade =  atividades->IterateChildren(atividade)) {
		
			 // todos os anexos
			 list<Arquivo> atividadeAnexos;
			 int type = atividade->Type();
			 // se for um elemento
			 if (type == TiXmlNode::ELEMENT) {	
				
				// sequence da tabela atendimento.atendimentoanatelatividade
				char idAtendimentoAnatelAtividade[53];
				
				// inicializar variavel
				memset(idAtendimentoAnatelAtividade, 0, sizeof(idAtendimentoAnatelAtividade));
				
				// inserir a atividade
				if ( atendimentoAnatel.inserirAtividade(atividade, idAtendimentoAnatelAtividade, idAtendimentoAnatel) ) {
				
					// número de atividade corresponde ao nome do diretório com os anexos
					const char *numAtividade = xmlReader.getNodeValue(atividade,"numAtividade");
					
					// montar o path de cada atividade
					string pathAtividade = pathSolicitacao;
					pathAtividade += "/";
					pathAtividade += numAtividade;
					
					// log de path da atividade
					log.logDebug( (char*) pathAtividade.c_str() );
					
					// listar os anexos da cada atividade pdf
					this->listarAnexos( (char*) pathAtividade.c_str(), atividadeAnexos);
					
					// percorrer a lista de anexos da atividade e gravar no banco
					while( 0 < atividadeAnexos.size() ) {
						Arquivo anexo = atividadeAnexos.front();
						
						// inserir anexo de atividade
						if ( !atendimentoAnatel.inserirAtividadeAnexo(idAtendimentoAnatelAtividade, anexo.getNome()) ) {
							// gravar log de erro
							log.logError("erro ao gravar anexo de atividade");
							this->historico->appendText("%s|%s|%s|%d|%s%s", idtSolicitacao, dataRegistro, 
							telefoneProblema, 1, "erro ao gravar anexo de atividade ", anexo.getNome());
						}
						
						atividadeAnexos.pop_front();
					}					
				} else {
				
					// exibir log de erro de atividade
					log.logError("Erro ao gravar atividade");
					this->historico->appendText("%s|%s|%s|%d|%s", idtSolicitacao, dataRegistro, 
					telefoneProblema, 1, "erro ao gravar dados de atividade");
				}
											
			 }
		}			
				
		
	} else {		
		log.logError("Erro ao gravar solicitação");
		// atualizar tabela atendimento.atendimentoanatel para relatório
		atendimentoAnatel.relatorioSolicitacaoRejeitada(idtSolicitacao);
		this->historico->appendText("%s|%s|%s|%d|%s", idtSolicitacao, dataRegistro, telefoneProblema, 0, "erro ao gravar solicitação");
	}
}

void Procedimento::findFileType( char *path, string &arquivo, char *type)
{	
	log.logDebug("localizar arquivo");
    DIR *dirp;
    struct dirent *dp;
	char nameLog[1024];
	
	// inicializando nameLog
	memset(nameLog,0,sizeof(nameLog));
	
	// copiando o path
	arquivo = path;

	// vai abrir o diretório
    if ((dirp = opendir(path)) == NULL) {
        log.logError("Erro ao abrir diretório");
        return;
    }

    do {
        if ((dp = readdir(dirp)) != NULL) {			
            sprintf(nameLog,"encontrado: %s size = %d\n", dp->d_name, sizeof(dp->d_name));     
			log.logDebug(nameLog);
			 if((!memcmp(dp->d_name + strlen(dp->d_name)-4, type, 4))) {
				arquivo += "/";
				arquivo += dp->d_name;
				closedir(dirp);
				return;
			 }			
        }
    } while (dp != NULL);

    closedir(dirp);
}

void Procedimento::listarArquivos( char *path, list<Arquivo> &arquivos, char *type)
{
    DIR *dirp;
    struct dirent *dp;	
	Arquivo arquivo;
	
    if ((dirp = opendir(path)) == NULL) {
        log.logError("Erro ao abrir diretório");
        return;
    }

    do {
        if ((dp = readdir(dirp)) != NULL) {
			if((!memcmp(dp->d_name + strlen(dp->d_name)-4, type, 4))) {						
				arquivo.setArquivo(dp->d_name, path);				
				arquivos.push_back(arquivo);							
			 }	
            // printf("encontrado %s size = %d\n", dp->d_name, sizeof(dp->d_name));            
        }
    } while (dp != NULL);

    closedir(dirp);
}

void Procedimento::listarAnexos( char *path, list<Arquivo> &arquivos)
{
    DIR *dirp;
    struct dirent *dp;	
	Arquivo arquivo;
	
    if ((dirp = opendir(path)) == NULL) {
        log.logError("Erro ao abrir diretório");
        return;
    }

    do {
        if ((dp = readdir(dirp)) != NULL) {
			if((!memcmp(dp->d_name + strlen(dp->d_name)-4, ".pdf", 4)) ||
			    (!memcmp(dp->d_name + strlen(dp->d_name)-4, ".PDF", 4))) {						
				arquivo.setArquivo(dp->d_name, path);				
				arquivos.push_back(arquivo);							
			 }	
            // printf("encontrado %s size = %d\n", dp->d_name, sizeof(dp->d_name));            
        }
    } while (dp != NULL);

    closedir(dirp);
}

int Procedimento::loadConfig() {
	log.logDebug("inicio vai abrir o arquivo de configuração");
	
	int retorno = 0;
		
	// fazer parse do arquivo de configuração
	if ( managerConfig.parse("cargaAnatel.xml") ) {
		TiXmlElement *element = managerConfig.getRootElement();
		
		if (element->FirstChild("userDatabase") == NULL) {
			log.logError("campo userDatabase do arquivo de configuração, não encontrado.");
			return retorno;
		} else if (element->FirstChild("passwordDatabase") == NULL) {
			log.logError("campo passwordDatabase do arquivo de configuração, não encontrado.");
			return retorno;
		} else if (element->FirstChild("oracleSID") == NULL) {
			log.logError("campo oracleSID do arquivo de configuração, não encontrado.");
			return retorno;
		} else if (element->FirstChild("userTuxedo") == NULL) {
			log.logError("campo userTuxedo do arquivo de configuração, não encontrado.");
			return retorno;
		} else if (element->FirstChild("passwordTuxedo") == NULL) {
			log.logError("campo passwordTuxedo do arquivo de configuração, não encontrado.");
			return retorno;
		} else if (element->FirstChild("passwordTuxedoGen") == NULL) {
			log.logError("campo passwordTuxedoGen do arquivo de configuração, não encontrado.");
			return retorno;
		} else if (element->FirstChild("inputDiretorio") == NULL) {
			log.logError("campo inputDiretorio do arquivo de configuração, não encontrado.");
			return retorno;
		} else if (element->FirstChild("outputDiretorio") == NULL) {
			log.logError("campo outputDiretorio do arquivo de configuração, não encontrado.");
			return retorno;
		} else if (element->FirstChild("executeDiretorio") == NULL) {
			log.logError("campo executeDiretorio do arquivo de configuração, não encontrado.");
			return retorno;
		} else if (element->FirstChild("cltTux") == NULL) {
			log.logError("campo cltTux do arquivo de configuração, não encontrado.");
			return retorno;
		} 
		
		this->userDatabase = (char*) element->FirstChild("userDatabase")->ToElement()->GetText(); 
		log.log("userDatabase = [%s]",(char*)this->userDatabase);
		this->passwordDatabase = (char*) element->FirstChild("passwordDatabase")->ToElement()->GetText();
		this->oracleSID = (char*) element->FirstChild("oracleSID")->ToElement()->GetText(); 
		log.log("oracleSID = [%s]", (char*)this->oracleSID);
		this->userTuxedo = (char*) element->FirstChild("userTuxedo")->ToElement()->GetText(); 
		log.log("userTuxedo = [%s]", (char*)this->userTuxedo);
		this->passwordTuxedo = (char*) element->FirstChild("passwordTuxedo")->ToElement()->GetText(); 
		log.log("passwordTuxedo = [%s]", (char*)this->passwordTuxedo);
		this->passwordTuxedoGen = (char*) element->FirstChild("passwordTuxedoGen")->ToElement()->GetText(); 
		log.log("passwordTuxedoGen = [%s]", (char*)this->passwordTuxedoGen);
		this->inputDiretorio = (char*) element->FirstChild("inputDiretorio")->ToElement()->GetText(); 
		log.log("inputDiretorio = [%s]", (char*)this->inputDiretorio);
		this->outputDiretorio = (char*) element->FirstChild("outputDiretorio")->ToElement()->GetText(); 
		log.log("outputDiretorio = [%s]", (char*)this->outputDiretorio);
		this->executeDiretorio = (char*) element->FirstChild("executeDiretorio")->ToElement()->GetText(); 
		log.log("executeDiretorio = [%s]", (char*)this->executeDiretorio);
		this->cltTux = (char*) element->FirstChild("cltTux")->ToElement()->GetText(); 
		log.log("cltTux = [%s]", (char*)this->cltTux);
		
		retorno = 1;
		log.logDebug("sucesso ao abrir arquivo de configuração");
	}
	
	log.logDebug("fim vai abrir o arquivo de configuração");
	
	return retorno;
}

int Procedimento::descompactar(Arquivo &arquivo) {
	log.logDebug("inicio descompactar arquivo");
	
	// montar commando unzip
	char *unzip = (char*) malloc(strlen(arquivo.getArquivo()) + strlen(this->executeDiretorio) + 11);
	memset(unzip, 0, sizeof(unzip));
	
	sprintf(unzip, "unzip -o -d %s %s", (char*)this->executeDiretorio, arquivo.getArquivo());
	log.logDebug(unzip);
	
	// copiar o caminho do arquivo descompactado
	arquivo.setPathDescompactado(this->executeDiretorio);
	
	// vamos descompactar o arquivo usando o gzip
	log.logDebug("começa a descompactar");
	system(unzip);
	free(unzip);
	log.logDebug("arquivo descompactado");
	
	
	log.logDebug("fim descompactar arquivo");
	
	return 1;
}

int Procedimento::tuxInit()
{
    log.logDebug("inicio: Procedimento::TuxedoInit");
	
	TPINIT *tpInitInfo;	
    int i;
	
	log.logDebug("alocanto TPINIT");
    if((tpInitInfo = (TPINIT *)tpalloc("TPINIT", (char *)NULL, TPINITNEED(strlen("vivo") - 1) )) == (TPINIT *)NULL)
		return tperrno;

    strcpy(tpInitInfo->usrname, this->userTuxedo);
    strcpy(tpInitInfo->cltname, this->cltTux);
    strcpy(tpInitInfo->passwd, this->passwordTuxedo);
    strcpy((char *)&tpInitInfo->data, this->passwordTuxedoGen);

	log.logDebug("iniciando tuxedo");
    i = tpinit(tpInitInfo);
	
	log.log("TPINIT: %i, TPERRNO: %i", i, tperrno);

    log.logDebug("ComprovanteServico::TuxedoInit fim");

    if (i == -1)
        return 1;
    else  
        return 0;
}

int Procedimento::sendXML(char *pXmlSaida, char *pRetMsg, int *pErrorNumber) 
{

	log.logDebug("enviando XML de envio");
    char *sendbuf, *rcvbuf;
    long sendlen, rcvlen;

    sendlen = strlen(pXmlSaida);
    
	log.log("tamanho da msg = (%d)", sendlen);

    if((sendbuf = (char *) tpalloc("STRING", NULL, sendlen+1)) == NULL) {
        log.logError("Error allocating send buffer");
        tpterm();

        return -1;
    }

    if((rcvbuf = (char *) tpalloc("STRING", NULL, sendlen+1)) == NULL) {
        log.logError("Error allocating receive buffer");
        tpfree(sendbuf);
        tpterm();

        return -1;
    }

    strcpy(sendbuf, pXmlSaida);

    if(tpcall("TuxConnector", (char *)sendbuf, sendlen, (char **)&rcvbuf, &rcvlen, (long)0) == -1) {
				
		log.log("Tperrno = %d", tperrno);
		log.log("rcvbuf[%s]", rcvbuf);
		log.log("Erro[%s]", tpstrerror(tperrno));

        *pErrorNumber=tperrno;
        strcpy(pRetMsg, rcvbuf);

        tpfree(sendbuf);
        tpfree(rcvbuf);

		switch(*pErrorNumber)
		{
			case TPEINVAL: log.logError(CONST_TPEINVAL); break;
			case TPENOENT: log.logError(CONST_TPENOENT); break;
			case TPEITYPE: log.logError(CONST_TPEITYPE); break;
			case TPEOTYPE: log.logError(CONST_TPEOTYPE); break;
			case TPETRAN: log.logError(CONST_TPETRAN); break;
			case TPETIME: log.logError(CONST_TPETIME); break;
			case TPESVCFAIL: log.logError(CONST_TPESVCFAIL); break;
			case TPESVCERR: log.logError(CONST_TPESVCERR); break;
			case TPEBLOCK: log.logError(CONST_TPEBLOCK); break;
			case TPGOTSIG: log.logError(CONST_TPGOTSIG); break;
			case TPEPROTO: log.logError(CONST_TPEPROTO); break;
			case TPESYSTEM: log.logError(CONST_TPESYSTEM); break;
			case TPEOS: log.logError(CONST_TPEOS); break;
		}

        return 1;
    }

    *pErrorNumber=0;
    strcpy(pRetMsg, rcvbuf);

    tpfree(sendbuf);
    tpfree(rcvbuf);

    return 0;
}