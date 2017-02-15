APPNAME=expurgoAtdProtoServico

BATCH_DIR=$(HOME)/BATCH

COMPILE_DIR=$(BATCH_DIR)/$(APPNAME)
COMPILE_SRC=$(COMPILE_DIR)/src
COMPILE_BIN=$(COMPILE_DIR)/bin

DEPLOY_DIR=$(HOME)/deploy
PROCESSOS_DIR=$(DEPLOY_DIR)/processos

EXEC_DIR=$(PROCESSOS_DIR)/$(APPNAME)
EXEC_BIN=$(EXEC_DIR)/bin
EXEC_LOG=$(EXEC_DIR)/log

all:  build

clean:
	rm -r -f $(EXEC_DIR)

build:	expurgoAtdProtoServico $(APPNAME)

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
	cp -f $(COMPILE_DIR)/$(APPNAME).sh $(EXEC_DIR)/$(APPNAME).sh
	cp -f $(COMPILE_SRC)/exec_$(APPNAME).`hostname`.`whoami`.sh $(EXEC_BIN)/exec_$(APPNAME).sh
	cp -f $(COMPILE_DIR)/src/expurgoAtdProtoServico.sql $(EXEC_BIN)/expurgoAtdProtoServico.sql	
	chmod 0754 $(EXEC_DIR)/$(APPNAME).sh
	chmod 0754 $(EXEC_BIN)/exec_$(APPNAME).sh

expurgoAtdProtoServico:
	umask 077
	# cria estrutura inicial
	if [ ! -d $(DEPLOY_DIR) ] ; then mkdir $(DEPLOY_DIR); fi
	if [ ! -d $(PROCESSOS_DIR) ] ; then mkdir $(PROCESSOS_DIR); fi
	# cria diretorios de aplicacao
	if [ ! -d $(EXEC_DIR) ] ; then mkdir $(EXEC_DIR); fi
	if [ ! -d $(EXEC_BIN) ] ; then mkdir $(EXEC_BIN); fi
	if [ ! -d $(EXEC_LOG) ] ; then mkdir $(EXEC_LOG); fi
	cp -f $(COMPILE_DIR)/$(APPNAME).sh $(EXEC_DIR)/$(APPNAME).sh
	cp -f $(COMPILE_SRC)/exec_$(APPNAME).`hostname`.`whoami`.sh $(EXEC_BIN)/exec_$(APPNAME).sh
	cp -f $(COMPILE_DIR)/src/expurgoAtdProtoServico.sql $(EXEC_BIN)/expurgoAtdProtoServico.sql	
	chmod 0754 $(EXEC_DIR)/$(APPNAME).sh
	chmod 0754 $(EXEC_BIN)/exec_$(APPNAME).sh

