APPNAME=planoservicolinha_tmp

BATCH_DIR=$(HOME)/BATCH

COMPILE_DIR=$(BATCH_DIR)/$(APPNAME)
COMPILE_SRC=$(COMPILE_DIR)/src
COMPILE_BIN=$(COMPILE_DIR)/bin
COMPILE_CFG=$(COMPILE_DIR)/cfg

DEPLOY_DIR=$(HOME)/deploy
PROCESSOS_DIR=$(DEPLOY_DIR)/processos

EXEC_DIR=$(PROCESSOS_DIR)/$(APPNAME)
EXEC_BIN=$(EXEC_DIR)/bin
EXEC_CFG=$(EXEC_DIR)/cfg
EXEC_LOG=$(EXEC_DIR)/log
DATA_DIR=$(EXEC_DIR)/data

all:  build

clean:
	rm -r -f $(EXEC_DIR)

build:	$(APPNAME)

uninstall:
	rm -r -f $(EXEC_DIR)

install:
	umask 077
	# cria estrutura inicial
	if [ ! -d $(DEPLOY_DIR) ] ; then mkdir $(DEPLOY_DIR); fi
	if [ ! -d $(PROCESSOS_DIR) ] ; then mkdir $(PROCESSOS_DIR); fi
	# cria diretorios de aplicacao
	if [ ! -d $(EXEC_DIR) ] ; then mkdir $(EXEC_DIR); fi
	if [ ! -d $(EXEC_BIN) ] ; then mkdir $(EXEC_BIN); fi
	if [ ! -d $(EXEC_LOG) ] ; then mkdir $(EXEC_LOG); fi
	if [ ! -d $(DATA_DIR) ] ; then mkdir $(DATA_DIR); fi
	cp -f $(COMPILE_SRC)/planoservicolinha.`hostname`.`whoami`.sh $(EXEC_BIN)/planoservicolinha.sh
	cp -f $(COMPILE_SRC)/updt_planoservicoLinha.sh $(EXEC_BIN)/updt_planoservicoLinha.sh
	cp -f $(COMPILE_CFG)/planoservicolinha_tmp.ctl $(EXEC_BIN)/planoservicolinha_tmp.ctl
	cp -f $(COMPILE_CFG)/planoservicolinha_tmp.par $(EXEC_BIN)/planoservicolinha_tmp.par
	cp -f $(COMPILE_DIR)/src/trunc_planoservicolinha_tmp.sql $(EXEC_BIN)/trunc_planoservicolinha_tmp.sql	
	cp -f $(COMPILE_DIR)/src/planoservicolinha_tmp.sql $(EXEC_BIN)/planoservicolinha_tmp.sql
	cp -f $(COMPILE_DIR)/src/descarte.sql $(EXEC_BIN)/descarte.sql
	cp -f executa.sh $(EXEC_DIR)/executa.sh
	chmod 0777 $(DATA_DIR)
	chmod 0754 $(EXEC_BIN)/planoservicolinha.sh
	chmod 0754 $(EXEC_BIN)/updt_planoservicoLinha.sh
	chmod 0754 $(EXEC_DIR)/executa.sh

planoservicolinha_tmp:
	umask 077
	# cria estrutura inicial
	if [ ! -d $(DEPLOY_DIR) ] ; then mkdir $(DEPLOY_DIR); fi
	if [ ! -d $(PROCESSOS_DIR) ] ; then mkdir $(PROCESSOS_DIR); fi
	# cria diretorios de aplicacao
	if [ ! -d $(EXEC_DIR) ] ; then mkdir $(EXEC_DIR); fi
	if [ ! -d $(EXEC_BIN) ] ; then mkdir $(EXEC_BIN); fi
	if [ ! -d $(EXEC_LOG) ] ; then mkdir $(EXEC_LOG); fi
	if [ ! -d $(DATA_DIR) ] ; then mkdir $(DATA_DIR); fi
	cp -f $(COMPILE_SRC)/planoservicolinha.`hostname`.`whoami`.sh $(EXEC_BIN)/planoservicolinha.sh
	cp -f $(COMPILE_SRC)/updt_planoservicoLinha.sh $(EXEC_BIN)/updt_planoservicoLinha.sh
	cp -f $(COMPILE_CFG)/planoservicolinha_tmp.ctl $(EXEC_BIN)/planoservicolinha_tmp.ctl
	cp -f $(COMPILE_CFG)/planoservicolinha_tmp.par $(EXEC_BIN)/planoservicolinha_tmp.par
	cp -f $(COMPILE_DIR)/src/trunc_planoservicolinha_tmp.sql $(EXEC_BIN)/trunc_planoservicolinha_tmp.sql	
	cp -f $(COMPILE_DIR)/src/planoservicolinha_tmp.sql $(EXEC_BIN)/planoservicolinha_tmp.sql
	cp -f $(COMPILE_DIR)/src/descarte.sql $(EXEC_BIN)/descarte.sql
	cp -f executa.sh $(EXEC_DIR)/executa.sh
	chmod 0777 $(DATA_DIR)
	chmod 0754 $(EXEC_BIN)/planoservicolinha.sh
	chmod 0754 $(EXEC_BIN)/updt_planoservicoLinha.sh
	chmod 0754 $(EXEC_DIR)/executa.sh
