# Microsoft Developer Studio Project File - Name="WF_ENCACAO" - Package Owner=<4>
# Microsoft Developer Studio Generated Build File, Format Version 6.00
# ** DO NOT EDIT **

# TARGTYPE "Win32 (x86) Console Application" 0x0103

CFG=WF_ENCACAO - Win32 Debug
!MESSAGE This is not a valid makefile. To build this project using NMAKE,
!MESSAGE use the Export Makefile command and run
!MESSAGE 
!MESSAGE NMAKE /f "WF_ENCACAO.mak".
!MESSAGE 
!MESSAGE You can specify a configuration when running NMAKE
!MESSAGE by defining the macro CFG on the command line. For example:
!MESSAGE 
!MESSAGE NMAKE /f "WF_ENCACAO.mak" CFG="WF_ENCACAO - Win32 Debug"
!MESSAGE 
!MESSAGE Possible choices for configuration are:
!MESSAGE 
!MESSAGE "WF_ENCACAO - Win32 Release" (based on "Win32 (x86) Console Application")
!MESSAGE "WF_ENCACAO - Win32 Debug" (based on "Win32 (x86) Console Application")
!MESSAGE 

# Begin Project
# PROP AllowPerConfigDependencies 0
# PROP Scc_ProjName ""
# PROP Scc_LocalPath ""
CPP=cl.exe
RSC=rc.exe

!IF  "$(CFG)" == "WF_ENCACAO - Win32 Release"

# PROP BASE Use_MFC 0
# PROP BASE Use_Debug_Libraries 0
# PROP BASE Output_Dir "Release"
# PROP BASE Intermediate_Dir "Release"
# PROP BASE Target_Dir ""
# PROP Use_MFC 0
# PROP Use_Debug_Libraries 0
# PROP Output_Dir "Release"
# PROP Intermediate_Dir "Release"
# PROP Target_Dir ""
# ADD BASE CPP /nologo /W3 /GX /O2 /D "WIN32" /D "NDEBUG" /D "_CONSOLE" /D "_MBCS" /YX /FD /c
# ADD CPP /nologo /W3 /GX /O2 /D "WIN32" /D "NDEBUG" /D "_CONSOLE" /D "_MBCS" /YX /FD /c
# ADD BASE RSC /l 0x416 /d "NDEBUG"
# ADD RSC /l 0x416 /d "NDEBUG"
BSC32=bscmake.exe
# ADD BASE BSC32 /nologo
# ADD BSC32 /nologo
LINK32=link.exe
# ADD BASE LINK32 kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib /nologo /subsystem:console /machine:I386
# ADD LINK32 kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib /nologo /subsystem:console /machine:I386

!ELSEIF  "$(CFG)" == "WF_ENCACAO - Win32 Debug"

# PROP BASE Use_MFC 0
# PROP BASE Use_Debug_Libraries 1
# PROP BASE Output_Dir "Debug"
# PROP BASE Intermediate_Dir "Debug"
# PROP BASE Target_Dir ""
# PROP Use_MFC 0
# PROP Use_Debug_Libraries 1
# PROP Output_Dir "Debug"
# PROP Intermediate_Dir "Debug"
# PROP Target_Dir ""
# ADD BASE CPP /nologo /W3 /Gm /GX /ZI /Od /D "WIN32" /D "_DEBUG" /D "_CONSOLE" /D "_MBCS" /YX /FD /GZ /c
# ADD CPP /nologo /W3 /Gm /GX /ZI /Od /D "WIN32" /D "_DEBUG" /D "_CONSOLE" /D "_MBCS" /FR /YX /FD /GZ /c
# ADD BASE RSC /l 0x416 /d "_DEBUG"
# ADD RSC /l 0x416 /d "_DEBUG"
BSC32=bscmake.exe
# ADD BASE BSC32 /nologo
# ADD BSC32 /nologo
LINK32=link.exe
# ADD BASE LINK32 kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib /nologo /subsystem:console /debug /machine:I386 /pdbtype:sept
# ADD LINK32 kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib /nologo /subsystem:console /debug /machine:I386 /pdbtype:sept

!ENDIF 

# Begin Target

# Name "WF_ENCACAO - Win32 Release"
# Name "WF_ENCACAO - Win32 Debug"
# Begin Group "Source Files"

# PROP Default_Filter "cpp;c;cxx;rc;def;r;odl;idl;hpj;bat"
# Begin Source File

SOURCE=..\..\..\commons\emailUtil\src\cEmailUtil.cpp
# End Source File
# Begin Source File

SOURCE=..\ENCCONTATO\src\cEncContato.cpp
# End Source File
# Begin Source File

SOURCE=..\ENCCONTATO\src\cEncContatoPC.cpp
# End Source File
# Begin Source File

SOURCE=..\..\..\workflow\servico\IBWFPSUsuario\src\cIBWFPSUsuario.cpp
# End Source File
# Begin Source File

SOURCE=..\..\..\commons\Collection\src\Collection.cpp
# End Source File
# Begin Source File

SOURCE=..\..\..\commons\smsUtil\src\cSMSUtil.cpp
# End Source File
# Begin Source File

SOURCE=..\WF_ACAO\src\cWF_AcaoPC.cpp
# End Source File
# Begin Source File

SOURCE=..\..\..\workflow\servico\AgrupamentoEstadoTpProc\src\cWFAgrupamentoEstadoTpProc.cpp
# End Source File
# Begin Source File

SOURCE=..\..\..\workflow\servico\AgrupamentoEstadoTpProc\src\cWFAgrupamentoEstadoTpProcPC.cpp
# End Source File
# Begin Source File

SOURCE=..\..\..\atendimento\servico\Andamento\src\cWFAndamento.cpp
# End Source File
# Begin Source File

SOURCE=..\AndamentoMotivo\src\cWFAndamentoMotivo.cpp
# End Source File
# Begin Source File

SOURCE=..\AndamentoMotivo\src\cWFAndamentoMotivoPC.cpp
# End Source File
# Begin Source File

SOURCE=..\..\..\atendimento\servico\AndamentoObservacao\src\cWFAndamentoObservacao.cpp
# End Source File
# Begin Source File

SOURCE=..\..\..\atendimento\servico\AndamentoObservacao\src\cWFAndamentoObservacaoPC.cpp
# End Source File
# Begin Source File

SOURCE=..\..\..\atendimento\servico\Andamento\src\cWFAndamentoPC.cpp
# End Source File
# Begin Source File

SOURCE=..\AndamentoProcessoFODPR\src\cWFAndamentoProcessoFODPR.cpp
# End Source File
# Begin Source File

SOURCE=..\AndamentoProcessoFODPR\src\cWFAndamentoProcessoFODPRPC.cpp
# End Source File
# Begin Source File

SOURCE=..\AndamentoTransferencia\src\cWFAndamentoTransferencia.cpp
# End Source File
# Begin Source File

SOURCE=..\AndamentoTransferencia\src\cWFAndamentoTransferenciaPC.cpp
# End Source File
# Begin Source File

SOURCE=..\AtdEncaminhadoIncorreto\src\cWFAtdEncaminhadoIncorreto.cpp
# End Source File
# Begin Source File

SOURCE=..\AtdEncaminhadoIncorreto\src\cWFAtdEncaminhadoIncorretoPC.cpp
# End Source File
# Begin Source File

SOURCE=..\AtdMensagemCri\src\cWFAtdMsgCri.cpp
# End Source File
# Begin Source File

SOURCE=..\AtdMensagemCri\src\cWFAtdMsgCriPC.cpp
# End Source File
# Begin Source File

SOURCE=..\AtdMensagemRC\src\cWFAtdMsgRC.cpp
# End Source File
# Begin Source File

SOURCE=..\AtdMensagemRC\src\cWFAtdMsgRCPC.cpp
# End Source File
# Begin Source File

SOURCE=..\AtdTratamentoCri\src\cWFAtdTratamentoCri.cpp
# End Source File
# Begin Source File

SOURCE=..\AtdTratamentoCri\src\cWFAtdTratamentoCriPC.cpp
# End Source File
# Begin Source File

SOURCE=..\Atendimento\src\cWFAtendimento.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoAgendamento\src\cWFAtendimentoAgendamento.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoAgendamento\src\cWFAtendimentoAgendamentoPC.cpp
# End Source File
# Begin Source File

SOURCE=..\..\..\atendimento\servico\AtendimentoAndamentoAtual\src\cWFAtendimentoAndamentoAtual.cpp
# End Source File
# Begin Source File

SOURCE=..\..\..\atendimento\servico\AtendimentoAndamentoAtual\src\cWFAtendimentoAndamentoAtualPC.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoBaixaAtual\src\cWFAtendimentoBaixaAtual.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoBaixaAtual\src\cWFAtendimentoBaixaAtualPC.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoBaixaHistorico\src\cWFAtendimentoBaixaHistorico.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoBaixaHistorico\src\cWFAtendimentoBaixaHistoricoPC.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoContato\src\cWFAtendimentoContato.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoContato\src\cWFAtendimentoContatoPC.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoCPFila\src\cWFAtendimentoCPFila.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoCPFila\src\cWFAtendimentoCPFilaPC.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoFechamento\src\cWFAtendimentoFechamento.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoFechamento\src\cWFAtendimentoFechamentoPC.cpp
# End Source File
# Begin Source File

SOURCE=..\Atendimento\src\cWFAtendimentoFilaBKO.cpp
# End Source File
# Begin Source File

SOURCE=..\Atendimento\src\cWFAtendimentoFilaBKOPC.cpp
# End Source File
# Begin Source File

SOURCE=..\Atendimento\src\cWFAtendimentoFilaCRI.cpp
# End Source File
# Begin Source File

SOURCE=..\Atendimento\src\cWFAtendimentoFilaCRIPC.cpp
# End Source File
# Begin Source File

SOURCE=..\Atendimento\src\cWFAtendimentoFilaRC.cpp
# End Source File
# Begin Source File

SOURCE=..\Atendimento\src\cWFAtendimentoFilaRCPC.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoFrm\src\cWFAtendimentoFrm.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoFrmCampo\src\cWFAtendimentoFrmCampo.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoFrmCampo\src\cWFAtendimentoFrmCampoPC.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoFrm\src\cWFAtendimentoFrmPC.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoGerarXMLDPR\src\cWFAtendimentoGerarXMLDPR.cpp
# End Source File
# Begin Source File

SOURCE=..\..\..\atendimento\servico\AtendimentoGrupoAtual\src\cWFAtendimentoGrupoAtual.cpp
# End Source File
# Begin Source File

SOURCE=..\..\..\atendimento\servico\AtendimentoGrupoAtual\src\cWFAtendimentoGrupoAtualPC.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoGrupoDevolucao\src\cWFAtendimentoGrupoDevolucao.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoGrupoDevolucao\src\cWFAtendimentoGrupoDevolucaoPC.cpp
# End Source File
# Begin Source File

SOURCE=..\Atendimento\src\cWFAtendimentoInBoxBKO.cpp
# End Source File
# Begin Source File

SOURCE=..\Atendimento\src\cWFAtendimentoInBoxBKOPC.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoLinha\src\cWFAtendimentoLinha.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoLinha\src\cWFAtendimentoLinhaPC.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoMensagem\src\cWFAtendimentoMensagem.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoMensagem\src\cWFAtendimentoMensagemPC.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoNivel\src\cWFAtendimentoNivel.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoNivel\src\cWFAtendimentoNivelPC.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoPausa\src\cWFAtendimentoPausa.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoPausa\src\cWFAtendimentoPausaPC.cpp
# End Source File
# Begin Source File

SOURCE=..\Atendimento\src\cWFAtendimentoPC.cpp
# End Source File
# Begin Source File

SOURCE=..\Atendimento\src\cWFAtendimentoPesquisas.cpp
# End Source File
# Begin Source File

SOURCE=..\Atendimento\src\cWFAtendimentoPesquisasPC.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoPessoa\src\cWFAtendimentoPessoa.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoPessoa\src\cWFAtendimentoPessoaPC.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoPriorizacao\src\cWFAtendimentoPriorizacao.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoPriorizacao\src\cWFAtendimentoPriorizacaoPC.cpp
# End Source File
# Begin Source File

SOURCE=..\Atendimento\src\cWFAtendimentoSkillPC.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoSuspeito\src\cWFAtendimentoSuspeito.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoSuspeito\src\cWFAtendimentoSuspeitoPC.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoTeste\src\cWFAtendimentoTeste.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoTeste\src\cWFAtendimentoTestePC.cpp
# End Source File
# Begin Source File

SOURCE=..\..\..\atendimento\servico\AtendimentoUsuarioAtual\src\cWFAtendimentoUsuarioAtual.cpp
# End Source File
# Begin Source File

SOURCE=..\..\..\atendimento\servico\AtendimentoUsuarioAtual\src\cWFAtendimentoUsuarioAtualPC.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoUsuarioDevolucao\src\cWFAtendimentoUsuarioDevolucao.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoUsuarioDevolucao\src\cWFAtendimentoUsuarioDevolucaoPC.cpp
# End Source File
# Begin Source File

SOURCE=..\CancelamentoSolicitado\src\cWFCancelamentoSolicitado.cpp
# End Source File
# Begin Source File

SOURCE=..\CancelamentoSolicitado\src\cWFCancelamentoSolicitadoPC.cpp
# End Source File
# Begin Source File

SOURCE=..\..\..\workflow\servico\IBWFPSUsuario\src\cWFIBWFPSUsuarioPC.cpp
# End Source File
# Begin Source File

SOURCE=..\Motivo\src\cWFMotivo.cpp
# End Source File
# Begin Source File

SOURCE=..\Motivo\src\cWFMotivoPC.cpp
# End Source File
# Begin Source File

SOURCE=..\PPRelatorios\src\cWFPPRelatorios.cpp
# End Source File
# Begin Source File

SOURCE=..\PPRelatorios\src\cWFPPRelatoriosPC.cpp
# End Source File
# Begin Source File

SOURCE=..\..\..\workflow\servico\Usuario\src\cWFUsuario.cpp
# End Source File
# Begin Source File

SOURCE=..\..\..\workflow\servico\Usuario\src\cWFUsuarioPC.cpp
# End Source File
# Begin Source File

SOURCE=.\src\svcWF_ENCACAO.cpp
# End Source File
# Begin Source File

SOURCE=..\WF_ACAO\src\WF_Acao.cpp
# End Source File
# End Group
# Begin Group "Header Files"

# PROP Default_Filter "h;hpp;hxx;hm;inl"
# Begin Source File

SOURCE=.\include\cWF_ENCACAOPC.h
# End Source File
# Begin Source File

SOURCE=.\include\TString.h
# End Source File
# End Group
# Begin Group "Resource Files"

# PROP Default_Filter "ico;cur;bmp;dlg;rc2;rct;bin;rgs;gif;jpg;jpeg;jpe"
# Begin Source File

SOURCE=.\makefile
# End Source File
# End Group
# Begin Group "PRO*C"

# PROP Default_Filter ""
# End Group
# Begin Group "XML"

# PROP Default_Filter ""
# Begin Source File

SOURCE=.\xml\consulta.xml
# End Source File
# Begin Source File

SOURCE=.\xml\Script288.txt
# End Source File
# End Group
# End Target
# End Project
