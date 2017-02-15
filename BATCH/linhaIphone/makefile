APPNAME=linhaIphone

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
	rm -f $(EXEC_BIN)/load_$(APPNAME).sh
	rm -f $(EXEC_BIN)/linhaIphone.sh
	rm -f $(EXEC_BIN)/load_linhaIphone.ctl
	rm -f $(EXEC_BIN)/load_linhaIphone.par
	rm -f $(EXEC_BIN)/preload.sql
	rm -f $(EXEC_BIN)/load_linhaIphone.sql
	rm -f $(EXEC_DIR)/executa.sh
	rm -f $(EXEC_BIN)/preload_linhaIphone.sh

build:	linhaIphone $(APPNAME)

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
	cp -f $(COMPILE_SRC)/load_$(APPNAME).`hostname`.`whoami`.sh $(EXEC_BIN)/load_$(APPNAME).sh
	cp -f $(COMPILE_SRC)/preload_linhaIphone.sh $(EXEC_BIN)/preload_linhaIphone.sh
	cp -f $(COMPILE_CFG)/load_linhaIphone.ctl $(EXEC_BIN)/load_linhaIphone.ctl
	cp -f $(COMPILE_CFG)/load_linhaIphone.par $(EXEC_BIN)/load_linhaIphone.par
	cp -f $(COMPILE_DIR)/src/preload.sql $(EXEC_BIN)/preload.sql	
	cp -f $(COMPILE_DIR)/src/load_linhaIphone.sql $(EXEC_BIN)/load_linhaIphone.sql
	cp -f executa.sh $(EXEC_DIR)/executa.sh
	chmod 0754 $(EXEC_BIN)/load_$(APPNAME).sh
	chmod 0754 $(EXEC_BIN)/preload_linhaIphone.sh
	chmod 0754 $(EXEC_DIR)/executa.sh

linhaIphone:
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
	cp -f $(COMPILE_SRC)/load_$(APPNAME).`hostname`.`whoami`.sh $(EXEC_BIN)/load_$(APPNAME).sh
	cp -f $(COMPILE_DIR)/src/preload_linhaIphone.sh $(EXEC_BIN)/preload_linhaIphone.sh
	cp -f executa.sh $(EXEC_DIR)/executa.sh
	cp -f $(COMPILE_DIR)/cfg/load_linhaIphone.ctl $(EXEC_BIN)/load_linhaIphone.ctl
	cp -f $(COMPILE_DIR)/cfg/load_linhaIphone.par $(EXEC_BIN)/load_linhaIphone.par
	cp -f $(COMPILE_DIR)/src/load_linhaIphone.sql $(EXEC_BIN)/load_linhaIphone.sql	
	cp -f $(COMPILE_DIR)/src/preload.sql $(EXEC_BIN)/preload.sql	
	chmod 0754 $(EXEC_BIN)/load_$(APPNAME).sh
	chmod 0754 $(EXEC_BIN)/preload_linhaIphone.sh
	chmod 0754 $(EXEC_DIR)/executa.sh