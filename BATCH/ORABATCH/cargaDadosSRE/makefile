
APPNAME=cargaDadosSRE

BATCH_DIR=$(HOME)/BATCH/ORABATCH

COMPILE_DIR=$(BATCH_DIR)/$(APPNAME)
COMPILE_CFG=$(COMPILE_DIR)/cfg
COMPILE_PRL=$(BATCH_DIR)/../commons/perl64

DEPLOY_DIR=$(HOME)/deploy
PROCESSOS_DIR=$(DEPLOY_DIR)/processos/ORABATCH

EXEC_DIR=$(PROCESSOS_DIR)/$(APPNAME)
EXEC_CFG=$(EXEC_DIR)
EXEC_LOG=$(EXEC_DIR)/log
EXEC_LOG_CAD_MANUAL=$(EXEC_DIR)/logCadManual
EXEC_LOG_REL_CARGA=$(EXEC_DIR)/logRelCarga
EXEC_DNE=$(EXEC_DIR)/DNE
EXEC_RELATORIO_CAD_MANUAL=$(EXEC_DIR)/RelatoriosCadManual
EXEC_RELATORIO_CARGA_CD_DNE=$(EXEC_DIR)/RelatoriosCargaCdDNE
EXEC_PRL=$(PROCESSOS_DIR)/../perl64

build: 
	# copia libperl
	if [ ! -d $(EXEC_PRL) ] ; then cp -prf $(COMPILE_PRL) $(EXEC_PRL); fi

clean:
	rm -fr $(EXEC_DIR)/*.sh
	rm -fr $(EXEC_DIR)/*.pl 
	rm -fr $(EXEC_DIR)/*.pm
	rm -fr $(EXEC_DIR)/*.sql
	rm -fr $(EXEC_DIR)/*.txt

install:
	umask 077

	# cria estrutura inicial
	if [ ! -d $(DEPLOY_DIR) ] ; then mkdir $(DEPLOY_DIR); fi
	if [ ! -d $(PROCESSOS_DIR) ] ; then mkdir $(PROCESSOS_DIR); fi

	# cria diretorios de aplicacao
	if [ ! -d $(EXEC_DIR) ] 					; then mkdir $(EXEC_DIR); fi
	if [ ! -d $(EXEC_CFG) ] 					; then mkdir $(EXEC_CFG); fi
	if [ ! -d $(EXEC_LOG) ] 					; then mkdir $(EXEC_LOG); fi
	if [ ! -d $(EXEC_LOG_CAD_MANUAL) ] 			; then mkdir $(EXEC_LOG_CAD_MANUAL); fi
	if [ ! -d $(EXEC_LOG_REL_CARGA) ] 			; then mkdir $(EXEC_LOG_REL_CARGA); fi
	if [ ! -d $(EXEC_DNE) ] 					; then mkdir $(EXEC_DNE); fi
	if [ ! -d $(EXEC_RELATORIO_CAD_MANUAL) ] 	; then mkdir $(EXEC_RELATORIO_CAD_MANUAL); fi
	if [ ! -d $(EXEC_RELATORIO_CARGA_CD_DNE) ] 	; then mkdir $(EXEC_RELATORIO_CARGA_CD_DNE); fi
	

	# copia arquivo de configuracao da aplicacao
	cp -pf $(COMPILE_CFG)/$(APPNAME).`hostname`.`whoami`.cfg $(EXEC_CFG)/$(APPNAME).cfg

	# copia arquivos
	cp -pf $(COMPILE_DIR)/*.sh $(EXEC_DIR)
	cp -pf $(COMPILE_DIR)/*.pl $(EXEC_DIR)
	cp -pf $(COMPILE_DIR)/*.pm $(EXEC_DIR)
	cp -pf $(COMPILE_DIR)/*.sql $(EXEC_DIR)
	cp -pf $(COMPILE_DIR)/*.txt $(EXEC_DIR)

	# seta permissoes
	chmod ug=rwx,o=rwx $(EXEC_DIR)/*.sh
	chmod ug=rwx,o=rwx $(EXEC_DIR)/*.pl
	chmod ug=rwx,o=rwx $(EXEC_DIR)/*.pm
	chmod ug=rw,o=rw $(EXEC_DIR)/*.sql
	chmod ug=rw,o=rw $(EXEC_DIR)/*.txt
	chmod ug=rw,o=rw $(EXEC_CFG)/$(APPNAME).cfg
	chmod ug=rwx,o=rwx $(EXEC_LOG) $(EXEC_DNE)

uninstall:
	rm -r -f $(EXEC_DIR)


