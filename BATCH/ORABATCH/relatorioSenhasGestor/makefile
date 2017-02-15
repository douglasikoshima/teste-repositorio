
APPNAME=relatorioSenhasGestor

BATCH_DIR=$(HOME)/BATCH/ORABATCH

COMPILE_DIR=$(BATCH_DIR)/$(APPNAME)
COMPILE_CFG=$(COMPILE_DIR)/cfg
COMPILE_MAIL=$(COMPILE_DIR)/mail

DEPLOY_DIR=$(HOME)/deploy
PROCESSOS_DIR=$(DEPLOY_DIR)/processos/ORABATCH

EXEC_DIR=$(PROCESSOS_DIR)/$(APPNAME)
EXEC_LOG=$(EXEC_DIR)/log
EXEC_DAT=$(EXEC_DIR)/data
EXEC_ENV=$(EXEC_DIR)/enviado
EXEC_MAIL=$(EXEC_DIR)/mail

all: build

build: 
	@echo

clean:
	rm -f $(EXEC_DIR)/*.sh
	rm -f $(EXEC_DIR)/*.sql
	rm -f $(EXEC_DIR)/$(APPNAME).cfg


install:
	umask 077

	# cria estrutura inicial
	if [ ! -d $(DEPLOY_DIR) ] ; then mkdir $(DEPLOY_DIR); fi
	if [ ! -d $(PROCESSOS_DIR) ] ; then mkdir $(PROCESSOS_DIR); fi

	# cria diretorios de aplicacao
	if [ ! -d $(EXEC_DIR) ] ; then mkdir $(EXEC_DIR); fi
	if [ ! -d $(EXEC_LOG) ] ; then mkdir $(EXEC_LOG); fi
	if [ ! -d $(EXEC_DAT) ] ; then mkdir $(EXEC_DAT); fi 
	if [ ! -d $(EXEC_ENV) ] ; then mkdir $(EXEC_ENV); fi
	if [ ! -d $(EXEC_MAIL) ] ; then mkdir $(EXEC_MAIL); fi
	
	chmod 0777 $(EXEC_LOG) $(EXEC_ERR) $(EXEC_DAT) $(EXEC_MAIL) 

	# copia arquivo de configuracao da aplicacao
	cp -pf $(COMPILE_CFG)/$(APPNAME).`hostname`.`whoami`.cfg $(EXEC_DIR)/$(APPNAME).cfg

	# copia .sh
	cp -pf $(COMPILE_DIR)/$(APPNAME).sh $(EXEC_DIR)

	# copia .sql
	cp -pf $(COMPILE_DIR)/relGestorAltSenhaSemanal.sql $(EXEC_DIR)
	cp -pf $(COMPILE_DIR)/relGestorNAltSenhaSemanal.sql $(EXEC_DIR)
	cp -pf $(COMPILE_DIR)/relGestorAltSenhaMensal.sql $(EXEC_DIR)
	cp -pf $(COMPILE_DIR)/relGestorNAltSenhaMensal.sql $(EXEC_DIR)
	cp -pf $(COMPILE_DIR)/validaPiloto.sql $(EXEC_DIR)
	cp -pf $(COMPILE_DIR)/emailEnvio.sql $(EXEC_DIR)
	
	# copia .txt
	cp -pf $(COMPILE_MAIL)/conteudoMailAlt.txt $(EXEC_MAIL)
	cp -pf $(COMPILE_MAIL)/conteudoMailNAlt.txt $(EXEC_MAIL)
	cp -pf $(COMPILE_MAIL)/conteudoMailAltAnexo.txt $(EXEC_MAIL)
	cp -pf $(COMPILE_MAIL)/conteudoMailNAltAnexo.txt $(EXEC_MAIL)
	
	# seta permissoes
	chmod 0755 $(EXEC_DIR)
	chmod 0744 $(EXEC_DIR)/$(APPNAME).sh
	chmod 0664 $(EXEC_DIR)/$(APPNAME).cfg
	chmod 0777 $(EXEC_LOG) $(EXEC_DAT) $(EXEC_ENV) $(EXEC_MAIL)

uninstall:
	rm -r -f $(EXEC_DIR)

