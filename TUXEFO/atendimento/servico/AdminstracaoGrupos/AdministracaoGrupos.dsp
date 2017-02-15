# Microsoft Developer Studio Project File - Name="AdministracaoGrupos" - Package Owner=<4>
# Microsoft Developer Studio Generated Build File, Format Version 6.00
# ** DO NOT EDIT **

# TARGTYPE "Win32 (x86) Console Application" 0x0103

CFG=AdministracaoGrupos - Win32 Debug
!MESSAGE This is not a valid makefile. To build this project using NMAKE,
!MESSAGE use the Export Makefile command and run
!MESSAGE 
!MESSAGE NMAKE /f "AdministracaoGrupos.mak".
!MESSAGE 
!MESSAGE You can specify a configuration when running NMAKE
!MESSAGE by defining the macro CFG on the command line. For example:
!MESSAGE 
!MESSAGE NMAKE /f "AdministracaoGrupos.mak" CFG="AdministracaoGrupos - Win32 Debug"
!MESSAGE 
!MESSAGE Possible choices for configuration are:
!MESSAGE 
!MESSAGE "AdministracaoGrupos - Win32 Release" (based on "Win32 (x86) Console Application")
!MESSAGE "AdministracaoGrupos - Win32 Debug" (based on "Win32 (x86) Console Application")
!MESSAGE 

# Begin Project
# PROP AllowPerConfigDependencies 0
# PROP Scc_ProjName ""
# PROP Scc_LocalPath ""
CPP=cl.exe
RSC=rc.exe

!IF  "$(CFG)" == "AdministracaoGrupos - Win32 Release"

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
# ADD BASE LINK32 kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib /nologo /subsystem:console /machine:I386
# ADD LINK32 kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib /nologo /subsystem:console /machine:I386

!ELSEIF  "$(CFG)" == "AdministracaoGrupos - Win32 Debug"

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
# ADD CPP /nologo /W2 /Gm /GX /ZI /Od /D "WIN32" /D "_DEBUG" /D "_CONSOLE" /D "_MBCS" /FR /YX /FD /GZ /c
# ADD BASE RSC /l 0x416 /d "_DEBUG"
# ADD RSC /l 0x416 /d "_DEBUG"
BSC32=bscmake.exe
# ADD BASE BSC32 /nologo
# ADD BSC32 /nologo
LINK32=link.exe
# ADD BASE LINK32 kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib /nologo /subsystem:console /debug /machine:I386 /pdbtype:sept
# ADD LINK32 kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib /nologo /subsystem:console /debug /machine:I386 /pdbtype:sept

!ENDIF 

# Begin Target

# Name "AdministracaoGrupos - Win32 Release"
# Name "AdministracaoGrupos - Win32 Debug"
# Begin Group "src"

# PROP Default_Filter ""
# Begin Source File

SOURCE=.\src\CGrupos.cpp
# End Source File
# Begin Source File

SOURCE=.\src\CGruposLst.cpp
# End Source File
# Begin Source File

SOURCE=.\src\insertGrupos.cpp
# End Source File
# Begin Source File

SOURCE=.\src\insertNivel.cpp
# End Source File
# Begin Source File

SOURCE=.\src\selectData.cpp
# End Source File
# Begin Source File

SOURCE=.\src\selectGrupos.cpp
# End Source File
# Begin Source File

SOURCE=.\src\selectGruposContato.cpp
# End Source File
# Begin Source File

SOURCE=.\src\selectNivel.cpp
# End Source File
# Begin Source File

SOURCE=.\src\selectTypes.cpp
# End Source File
# Begin Source File

SOURCE=.\src\svcAdministracaoGrupos.cpp
# End Source File
# End Group
# Begin Group "include"

# PROP Default_Filter ""
# Begin Source File

SOURCE=.\include\CGrupos.h
# End Source File
# Begin Source File

SOURCE=.\include\CGruposLst.h
# End Source File
# Begin Source File

SOURCE=.\include\selectData.h
# End Source File
# End Group
# Begin Group "Pro*C"

# PROP Default_Filter ""
# Begin Source File

SOURCE=.\src\insertGrupos.pcpp

!IF  "$(CFG)" == "AdministracaoGrupos - Win32 Release"

!ELSEIF  "$(CFG)" == "AdministracaoGrupos - Win32 Debug"

# Begin Custom Build
InputPath=.\src\insertGrupos.pcpp

"insertGrupos.cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc             parse=none             cpp_suffix=cpp             code=cpp             $(InputPath)             include=D:\Projeto\FrontOffice\TUXEFO\atendimento\servico\Atendimento\include\ 

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=.\src\insertNivel.pcpp

!IF  "$(CFG)" == "AdministracaoGrupos - Win32 Release"

!ELSEIF  "$(CFG)" == "AdministracaoGrupos - Win32 Debug"

# Begin Custom Build
InputPath=.\src\insertNivel.pcpp

"insertNivel.cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc             parse=none             cpp_suffix=cpp             code=cpp             $(InputPath)             include=D:\Projeto\FrontOffice\TUXEFO\atendimento\servico\Atendimento\include\ 

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=.\src\selectGrupos.pcpp

!IF  "$(CFG)" == "AdministracaoGrupos - Win32 Release"

!ELSEIF  "$(CFG)" == "AdministracaoGrupos - Win32 Debug"

# Begin Custom Build
InputPath=.\src\selectGrupos.pcpp

"selectGrupos.cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc             parse=none             cpp_suffix=cpp             code=cpp             $(InputPath)             include=D:\Projeto\FrontOffice\TUXEFO\atendimento\servico\Atendimento\include\ 

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=.\src\selectGruposContato.pcpp

!IF  "$(CFG)" == "AdministracaoGrupos - Win32 Release"

!ELSEIF  "$(CFG)" == "AdministracaoGrupos - Win32 Debug"

# Begin Custom Build
InputPath=.\src\selectGruposContato.pcpp

"selectGruposContato.cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc             parse=none             cpp_suffix=cpp             code=cpp             $(InputPath)             include=D:\Projeto\FrontOffice\TUXEFO\atendimento\servico\Atendimento\include\ 

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=.\src\selectNivel.pcpp

!IF  "$(CFG)" == "AdministracaoGrupos - Win32 Release"

!ELSEIF  "$(CFG)" == "AdministracaoGrupos - Win32 Debug"

# Begin Custom Build
InputPath=.\src\selectNivel.pcpp

"selectNivel.cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc             parse=none             cpp_suffix=cpp             code=cpp             $(InputPath)             include=D:\Projeto\FrontOffice\TUXEFO\atendimento\servico\Atendimento\include\ 

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=.\src\selectTypes.pcpp

!IF  "$(CFG)" == "AdministracaoGrupos - Win32 Release"

!ELSEIF  "$(CFG)" == "AdministracaoGrupos - Win32 Debug"

# Begin Custom Build
InputPath=.\src\selectTypes.pcpp

"selectTypes.cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc             parse=none             cpp_suffix=cpp             code=cpp             $(InputPath)             include=D:\Projeto\FrontOffice\TUXEFO\atendimento\servico\Atendimento\include\ 

# End Custom Build

!ENDIF 

# End Source File
# End Group
# Begin Group "XML"

# PROP Default_Filter ""
# Begin Source File

SOURCE=.\xml\consulta.xml
# End Source File
# Begin Source File

SOURCE=.\xml\Insert.xml
# End Source File
# Begin Source File

SOURCE=.\xml\TestDriver_Insert.xml
# End Source File
# Begin Source File

SOURCE=.\xml\TestDriver_InsertLevel.xml
# End Source File
# Begin Source File

SOURCE=.\xml\TestDriver_Select.xml
# End Source File
# Begin Source File

SOURCE=.\xml\TestDriver_SelectLevel.xml
# End Source File
# Begin Source File

SOURCE=.\xml\teste.xml
# End Source File
# Begin Source File

SOURCE=.\xml\writegrpsproc.xml
# End Source File
# End Group
# End Target
# End Project
