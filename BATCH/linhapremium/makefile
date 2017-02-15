APPNAME=linhapremium

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
	rm -f -r $(EXEC_BIN)
	rm -f -r $(EXEC_CFG)
	rm -f $(EXEC_DIR)/*.sh


build:	linhapremium $(APPNAME)

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
	cp -f $(COMPILE_SRC)/preload_linhapremium.sh $(EXEC_BIN)/preload_linhapremium.sh
	cp -f $(COMPILE_CFG)/load_linhapremium.ctl $(EXEC_BIN)/load_linhapremium.ctl
	cp -f $(COMPILE_CFG)/load_linhapremium.par $(EXEC_BIN)/load_linhapremium.par
	cp -f $(COMPILE_DIR)/src/preload.sql $(EXEC_BIN)/preload.sql	
	cp -f $(COMPILE_DIR)/src/load_linhapremium.sql $(EXEC_BIN)/load_linhapremium.sql	
	#cp -f $(COMPILE_BIN)/VerificaLinhaPremium $(EXEC_BIN)/VerificaLinhaPremium
	cp -f linhapremium.sh $(EXEC_DIR)/linhapremium.sh
	chmod 0754 $(EXEC_BIN)/load_$(APPNAME).sh
	chmod 0754 $(EXEC_BIN)/preload_linhapremium.sh
	chmod 0754 $(EXEC_DIR)/linhapremium.sh

linhapremium:
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
	#cp -f $(COMPILE_SRC)/cfg/VerificaLinhaPremium.`hostname`.`whoami`.cfg $(EXEC_CFG)/VerificaLinhaPremium.cfg
	cp -f $(COMPILE_DIR)/src/preload_linhapremium.sh $(EXEC_BIN)/preload_linhapremium.sh
	cp -f linhapremium.sh $(EXEC_DIR)/linhapremium.sh
	cp -f $(COMPILE_DIR)/cfg/load_linhapremium.ctl $(EXEC_BIN)/load_linhapremium.ctl
	cp -f $(COMPILE_DIR)/cfg/load_linhapremium.par $(EXEC_BIN)/load_linhapremium.par
	cp -f $(COMPILE_DIR)/src/load_linhapremium.sql $(EXEC_BIN)/load_linhapremium.sql	
	cp -f $(COMPILE_DIR)/src/preload.sql $(EXEC_BIN)/preload.sql	
	chmod 0754 $(EXEC_BIN)/load_$(APPNAME).sh
	chmod 0754 $(EXEC_BIN)/preload_linhapremium.sh
	chmod 0754 $(EXEC_DIR)/linhapremium.sh
	