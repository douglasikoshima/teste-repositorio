APPNAME=linhaParceiro

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
PROC_DIR=$(EXEC_DIR)/proc

all:  build

clean:
	rm -r -f $(EXEC_DIR)

build:	linhaParceiro $(APPNAME)

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
	if [ ! -d $(PROC_DIR) ] ; then mkdir $(PROC_DIR); fi
	cp -f $(COMPILE_SRC)/load_$(APPNAME).`hostname`.`whoami`.sh $(EXEC_BIN)/load_$(APPNAME).sh
	cp -f $(COMPILE_SRC)/preload_linhaParceiro.sh $(EXEC_BIN)/preload_linhaParceiro.sh
	cp -f $(COMPILE_CFG)/linhaParceiro.ctl $(EXEC_BIN)/linhaParceiro.ctl
	cp -f $(COMPILE_CFG)/linhaParceiro.par $(EXEC_BIN)/linhaParceiro.par
	cp -f executa.sh $(EXEC_DIR)/linhaParceiro.sh
	chmod 0754 $(EXEC_BIN)/load_$(APPNAME).sh
	chmod 0754 $(EXEC_BIN)/preload_linhaParceiro.sh
	chmod 0754 $(EXEC_DIR)/linhaParceiro.sh

linhaParceiro:
	umask 077
	# cria estrutura inicial
	if [ ! -d $(DEPLOY_DIR) ] ; then mkdir $(DEPLOY_DIR); fi
	if [ ! -d $(PROCESSOS_DIR) ] ; then mkdir $(PROCESSOS_DIR); fi
	# cria diretorios de aplicacao
	if [ ! -d $(EXEC_DIR) ] ; then mkdir $(EXEC_DIR); fi
	if [ ! -d $(EXEC_BIN) ] ; then mkdir $(EXEC_BIN); fi
	if [ ! -d $(EXEC_CFG) ] ; then mkdir $(EXEC_CFG); fi
	if [ ! -d $(EXEC_LOG) ] ; then mkdir $(EXEC_LOG); fi
	if [ ! -d $(DATA_DIR) ] ; then mkdir $(DATA_DIR); fi
	if [ ! -d $(PROC_DIR) ] ; then mkdir $(PROC_DIR); fi
	cp -f $(COMPILE_SRC)/load_$(APPNAME).`hostname`.`whoami`.sh $(EXEC_BIN)/load_$(APPNAME).sh
	cp -f $(COMPILE_DIR)/src/preload_linhaParceiro.sh $(EXEC_BIN)/preload_linhaParceiro.sh
	cp -f executa.sh $(EXEC_DIR)/linhaParceiro.sh
	cp -f $(COMPILE_DIR)/cfg/linhaParceiro.ctl $(EXEC_BIN)/linhaParceiro.ctl
	cp -f $(COMPILE_DIR)/cfg/linhaParceiro.par $(EXEC_BIN)/linhaParceiro.par
	chmod 0754 $(EXEC_BIN)/load_$(APPNAME).sh
	chmod 0754 $(EXEC_BIN)/preload_linhaParceiro.sh
	chmod 0754 $(EXEC_DIR)/linhaParceiro.sh