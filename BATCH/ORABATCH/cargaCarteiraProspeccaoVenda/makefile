
APPNAME=cargaCarteiraProspeccaoVenda

BATCH_DIR=$(HOME)/BATCH/ORABATCH

COMPILE_DIR=$(BATCH_DIR)/$(APPNAME)
COMPILE_CFG=$(COMPILE_DIR)/cfg

DEPLOY_DIR=$(HOME)/deploy
PROCESS_DIR=$(DEPLOY_DIR)/processos/ORABATCH

EXEC_DIR=$(PROCESS_DIR)/$(APPNAME)
EXEC_RCV=$(EXEC_DIR)/received
EXEC_PCD=$(EXEC_DIR)/processed
EXEC_LOG=$(EXEC_DIR)/log
EXEC_RJT=$(EXEC_DIR)/rejected

all: build

build: 
	@echo

clean:
	rm -f $(EXEC_DIR)/$(APPNAME).sh
	rm -f $(EXEC_DIR)/$(APPNAME)Parcial.sh
	rm -f $(EXEC_DIR)/$(APPNAME).cfg
	rm -f $(EXEC_DIR)/*.sql

install:
	umask 077

	# cria estrutura inicial
	if [ ! -d $(DEPLOY_DIR) ] ; then mkdir $(DEPLOY_DIR); fi
	if [ ! -d $(PROCESS_DIR) ] ; then mkdir $(PROCESS_DIR); fi

	# cria diretorios de aplicacao
	if [ ! -d $(EXEC_DIR) ] ; then mkdir $(EXEC_DIR); fi
	if [ ! -d $(EXEC_RCV) ] ; then mkdir $(EXEC_RCV); fi
	if [ ! -d $(EXEC_PCD) ] ; then mkdir $(EXEC_PCD); fi 
	if [ ! -d $(EXEC_LOG) ] ; then mkdir $(EXEC_LOG); fi
	if [ ! -d $(EXEC_RJT) ] ; then mkdir $(EXEC_RJT); fi
	
	chmod 0777 $(EXEC_RCV) $(EXEC_PCD) $(EXEC_LOG) $(EXEC_RJT) 

	# copia arquivo de configuracao da aplicacao
	cp -pf $(COMPILE_CFG)/$(APPNAME).`hostname`.`whoami`.cfg $(EXEC_DIR)/$(APPNAME).cfg

	# copia .sh
	cp -pf $(COMPILE_DIR)/$(APPNAME).sh $(EXEC_DIR)
	cp -pf $(COMPILE_DIR)/$(APPNAME)Parcial.sh $(EXEC_DIR)
	
	# copia .ctl
	cp -pf $(COMPILE_DIR)/cargaCarteiraProspeccaoVenda.ctl $(EXEC_DIR)

	# copia .sql
	cp -pf $(COMPILE_DIR)/manterCargaProspeccaoVenda.sql $(EXEC_DIR)
	cp -pf $(COMPILE_DIR)/rejectedsCargaProspeccaoVenda.sql $(EXEC_DIR)
	cp -pf $(COMPILE_DIR)/truncCargaProspeccaoVenda.sql $(EXEC_DIR)
	
	# seta permissoes
	chmod 0755 $(EXEC_DIR)
	chmod 0744 $(EXEC_DIR)/$(APPNAME).sh
	chmod 0744 $(EXEC_DIR)/$(APPNAME)Parcial.sh
	chmod 0664 $(EXEC_DIR)/$(APPNAME).cfg
	chmod 0777 $(EXEC_RCV) $(EXEC_PCD) $(EXEC_LOG) $(EXEC_RJT)

uninstall:
	rm -r -f $(EXEC_DIR)

