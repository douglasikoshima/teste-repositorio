
APPNAME=relatorioPreCadastro

BATCH_DIR=$(HOME)/BATCH/ORABATCH

COMPILE_DIR=$(BATCH_DIR)/$(APPNAME)
COMPILE_CFG=$(COMPILE_DIR)/cfg

DEPLOY_DIR=$(HOME)/deploy
PROCESSOS_DIR=$(DEPLOY_DIR)/processos/ORABATCH

EXEC_DIR=$(PROCESSOS_DIR)/$(APPNAME)
EXEC_LOG=$(EXEC_DIR)/log
EXEC_DAT=$(EXEC_DIR)/data

all: build

build: 
	@echo

clean:
	rm -f $(EXEC_DIR)/$(APPNAME).sh
	rm -f $(EXEC_DIR)/$(APPNAME).cfg
	rm -f $(EXEC_DIR)/$(APPNAME).sql

install:
	umask 077

	# cria estrutura inicial
	if [ ! -d $(DEPLOY_DIR) ] ; then mkdir $(DEPLOY_DIR); fi
	if [ ! -d $(PROCESSOS_DIR) ] ; then mkdir $(PROCESSOS_DIR); fi

	# cria diretorios de aplicacao
	if [ ! -d $(EXEC_DIR) ] ; then mkdir $(EXEC_DIR); fi
	if [ ! -d $(EXEC_LOG) ] ; then mkdir $(EXEC_LOG); fi
	if [ ! -d $(EXEC_DAT) ] ; then mkdir $(EXEC_DAT); fi 
	
	chmod 0777 $(EXEC_LOG) $(EXEC_ERR) $(EXEC_DAT)

	# copia arquivo de configuracao da aplicacao
	cp -pf $(COMPILE_CFG)/$(APPNAME).`hostname`.`whoami`.cfg $(EXEC_DIR)/$(APPNAME).cfg

	# copia .sh
	cp -pf $(COMPILE_DIR)/$(APPNAME).sh $(EXEC_DIR)

	# copia .sql
	cp -pf $(COMPILE_DIR)/relatorioPreCadastro.sql $(EXEC_DIR)
	
	
	# seta permissoes
	chmod 0755 $(EXEC_DIR)
	chmod 0744 $(EXEC_DIR)/$(APPNAME).sh
	chmod 0664 $(EXEC_DIR)/$(APPNAME).cfg
	chmod 0777 $(EXEC_LOG) $(EXEC_DAT) $(EXEC_ENV)

uninstall:
	rm -r -f $(EXEC_DIR)

