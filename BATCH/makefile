#SUBDIRS=`ls -l | grep ^d | awk '{print $$9}'`

SUBDIRS=criaCad blindagemCliente enviaProtocoloSms removeProtocoloSms blackList blackListRel atualizaStatus atualizaContatos enviaCad \
        encaminhaContatoPrevio pontocontato linhapremium listaCampanha linhaPME linhaIphone linhaOferta priorizacaoAtendimento \
        enviaSMS enviaLegado enviaVIP statusUsuario resetaIndisponibilidadeUsuario relatorioArvore atendimentoVOLE \
        desativarComprovante CnpjSemCarteira expurgoAtendimento priorizacaoAtendimentoPortout enviaNGIN2 cancelamentoProdutoPF cancelamentoProdutoPJ \
        procMatrizesRetencao relatorioIMEI protocoloNaoSincronizado cartaoVivoItauCard relatorioURA mailing relatorioMVE \
        enviaInterceptacao enviaLegado2 finalizaCadastroPortIn relatorioGestor relatorioSP copiaArvore pupProcon enviaNGIN notasURA \
        movimentacaoPUP cargaCatalogo cargaConsultor cargaGrupoContato relatorioPP removerStatusPP cargaRecontato cargaGestores vinculoRecargaSMS \
        vinculoRecargaPendente cargaAnatel extratorDB fechaSolicitacaoBloqueioPR relatorioNMMDP cancelarReservaAparelho relatorioMCMDP \
        cargaPlanoDados enviaPP enviaPPSeg LoaderCargaPP linhaParceiro enviaPPPre cargaBancosPR relatorioMCMDP_DT enviarEmailProtocolo expurgoAtdProtoServico expurgoTmpPessoaLinha \
        manutencaoLogin manutencaoAcesso CadUsuario sittel blacklistcredito mensagemblacklist CadUsuarioMassivo ValidarLinhaCPF blacklistdados \
        expurgoRegistrosPreAtivacao importFileDadosAjato relatorioDescontoConvergente ORABATCH SINCRONISMO cargaLinhasParceiros planoservicolinha_tmp CancelAutomatico sincronismoPP

        #
        # Batchs inativados.
        #
        # notificaStatusAtendimento apiLegadoExec
        #
all:
	for dir in $(SUBDIRS); do \
		cd $$dir; \
		make; \
		cd ..; \
	done

clean:
	for dir in $(SUBDIRS); do \
		cd $$dir; \
		make clean; \
		cd ..; \
	done

install:
	echo Ajustando permissoes
	find . -name '*.sh' -exec chmod 700 {} \;
	echo Instalando aplicativos
	for dir in $(SUBDIRS); do \
		cd $$dir; \
		make install; \
		cd ..; \
	done 

uninstall:
	for dir in $(SUBDIRS); do \
		cd $$dir; \
		make uninstall; \
		cd ..; \
	done 

build:
	for dir in $(SUBDIRS); do \
		cd $$dir; \
		make build; \
		cd ..; \
	done
