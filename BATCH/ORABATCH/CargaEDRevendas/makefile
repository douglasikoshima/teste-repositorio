
APPNAME=CargaEDRevendas

BATCH_DIR=$(HOME)/BATCH/ORABATCH

COMPILE_DIR=$(BATCH_DIR)/$(APPNAME)
COMPILE_CFG=$(COMPILE_DIR)/cfg
COMPILE_LOG=$(COMPILE_DIR)/Log
COMPILE_ERR=$(COMPILE_DIR)/Error
COMPILE_DAT=$(COMPILE_DIR)/Data

DEPLOY_DIR=$(HOME)/deploy
PROCESSOS_DIR=$(DEPLOY_DIR)/processos/ORABATCH

EXEC_DIR=$(PROCESSOS_DIR)/$(APPNAME)
EXEC_CFG=$(EXEC_DIR)/
EXEC_LOG=$(EXEC_DIR)/Log
EXEC_ERR=$(EXEC_DIR)/Error
EXEC_DAT=$(EXEC_DIR)/Data

all: build

build: 
	@echo

clean:
	rm -f $(EXEC_DIR)/$(APPNAME).sh
	rm -f $(EXEC_DIR)/$(APPNAME).cfg
	rm -f $(EXEC_DIR)/*.txt
	rm -f $(EXEC_DIR)/*.ctl
	rm -f $(EXEC_DIR)/*.sql

install:
	umask 077

	# cria estrutura inicial
	if [ ! -d $(DEPLOY_DIR) ] ; then mkdir $(DEPLOY_DIR); fi
	if [ ! -d $(PROCESSOS_DIR) ] ; then mkdir $(PROCESSOS_DIR); fi

	# cria diretorios de aplicacao
	if [ ! -d $(EXEC_DIR) ] ; then mkdir $(EXEC_DIR); fi
	if [ ! -d $(EXEC_CFG) ] ; then mkdir $(EXEC_CFG); fi
	if [ ! -d $(EXEC_LOG) ] ; then mkdir $(EXEC_LOG); fi
	if [ ! -d $(EXEC_ERR) ] ; then mkdir $(EXEC_ERR); fi
	if [ ! -d $(EXEC_DAT) ] ; then mkdir $(EXEC_DAT); fi
	
	chmod 0777 $(EXEC_LOG) $(EXEC_ERR) $(EXEC_DAT)

	# copia arquivo de configuracao da aplicacao
	cp -pf $(COMPILE_CFG)/$(APPNAME).`hostname`.`whoami`.cfg $(EXEC_CFG)/$(APPNAME).cfg

	# copia .sh
	cp -pf $(COMPILE_DIR)/$(APPNAME).sh $(EXEC_DIR)

	# copia .ctl
	cp -pf $(COMPILE_DIR)/equaliza.sql $(EXEC_DIR)
	cp -pf $(COMPILE_DIR)/sapoio_classificacao.ctl $(EXEC_DIR)
	cp -pf $(COMPILE_DIR)/sapoio_parceiro.ctl $(EXEC_DIR)
	cp -pf $(COMPILE_DIR)/sapoio_enderecoparceiro.ctl $(EXEC_DIR)
	cp -pf $(COMPILE_DIR)/sapoio_linhaparceiro.ctl $(EXEC_DIR)
	cp -pf $(COMPILE_DIR)/sapoio_linhainterceptacao.ctl $(EXEC_DIR)
	cp -pf $(COMPILE_DIR)/sapoio_histlinhaparceiro.ctl $(EXEC_DIR)
	cp -pf $(COMPILE_DIR)/sapoio_histlinhainterceptacao.ctl $(EXEC_DIR)
	
	# copia .sql
	cp -pf $(COMPILE_DIR)/truncSapoio.sql $(EXEC_DIR)
	cp -pf $(COMPILE_DIR)/equaliza.sql $(EXEC_DIR)
	
	# seta permissoes
	chmod 0755 $(EXEC_DIR)
	chmod 0744 $(EXEC_DIR)/$(APPNAME).sh
	chmod 0664 $(EXEC_CFG)/$(APPNAME).cfg
	chmod 0777 $(EXEC_LOG) $(EXEC_ERR) $(EXEC_DAT)

uninstall:
	rm -r -f $(EXEC_DIR)

