@REM ----------------------------------------------------------------------------
@REM  Copyright 2001-2006 The Apache Software Foundation.
@REM
@REM  Licensed under the Apache License, Version 2.0 (the "License");
@REM  you may not use this file except in compliance with the License.
@REM  You may obtain a copy of the License at
@REM
@REM       http://www.apache.org/licenses/LICENSE-2.0
@REM
@REM  Unless required by applicable law or agreed to in writing, software
@REM  distributed under the License is distributed on an "AS IS" BASIS,
@REM  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@REM  See the License for the specific language governing permissions and
@REM  limitations under the License.
@REM ----------------------------------------------------------------------------
@REM
@REM   Copyright (c) 2001-2006 The Apache Software Foundation.  All rights
@REM   reserved.

@echo off

set ERROR_CODE=0

:init
@REM Decide how to startup depending on the version of windows

@REM -- Win98ME
if NOT "%OS%"=="Windows_NT" goto Win9xArg

@REM set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" @setlocal

@REM -- 4NT shell
if "%eval[2+2]" == "4" goto 4NTArgs

@REM -- Regular WinNT shell
set CMD_LINE_ARGS=%*
goto WinNTGetScriptDir

@REM The 4NT Shell from jp software
:4NTArgs
set CMD_LINE_ARGS=%$
goto WinNTGetScriptDir

:Win9xArg
@REM Slurp the command line arguments.  This loop allows for an unlimited number
@REM of arguments (up to the command line limit, anyway).
set CMD_LINE_ARGS=
:Win9xApp
if %1a==a goto Win9xGetScriptDir
set CMD_LINE_ARGS=%CMD_LINE_ARGS% %1
shift
goto Win9xApp

:Win9xGetScriptDir
set SAVEDIR=%CD%
%0\
cd %0\..\.. 
set BASEDIR=%CD%
cd %SAVEDIR%
set SAVE_DIR=
goto repoSetup

:WinNTGetScriptDir
set BASEDIR=%~dp0\..

:repoSetup


if "%JAVACMD%"=="" set JAVACMD=java

if "%REPO%"=="" set REPO=%BASEDIR%\lib

set CLASSPATH="%BASEDIR%"\conf;"%REPO%"\dom4j-1.6.1.jar;"%REPO%"\xml-apis-1.0.b2.jar;"%REPO%"\jaxen-1.1.4.jar;"%REPO%"\commons-email-1.3.1.jar;"%REPO%"\mail-1.4.5.jar;"%REPO%"\activation-1.1.1.jar;"%REPO%"\mybatis-3.1.1.jar;"%REPO%"\mybatis-spring-1.1.1.jar;"%REPO%"\spring-core-3.1.2.RELEASE.jar;"%REPO%"\spring-asm-3.1.2.RELEASE.jar;"%REPO%"\spring-beans-3.1.2.RELEASE.jar;"%REPO%"\spring-context-3.1.2.RELEASE.jar;"%REPO%"\spring-expression-3.1.2.RELEASE.jar;"%REPO%"\spring-context-support-3.1.2.RELEASE.jar;"%REPO%"\spring-aop-3.1.2.RELEASE.jar;"%REPO%"\aopalliance-1.0.jar;"%REPO%"\spring-tx-3.1.2.RELEASE.jar;"%REPO%"\spring-jdbc-3.1.2.RELEASE.jar;"%REPO%"\spring-orm-3.1.2.RELEASE.jar;"%REPO%"\spring-web-3.1.2.RELEASE.jar;"%REPO%"\spring-webmvc-3.1.2.RELEASE.jar;"%REPO%"\aspectjrt-1.6.10.jar;"%REPO%"\aspectjweaver-1.6.10.jar;"%REPO%"\cglib-nodep-3.1.jar;"%REPO%"\quartz-1.8.5.jar;"%REPO%"\jta-1.1.jar;"%REPO%"\jackson-core-asl-1.9.9.jar;"%REPO%"\jackson-mapper-asl-1.9.9.jar;"%REPO%"\jackson-jaxrs-1.9.9.jar;"%REPO%"\jetty-server-8.1.7.v20120910.jar;"%REPO%"\javax.servlet-3.0.0.v201112011016.jar;"%REPO%"\jetty-continuation-8.1.7.v20120910.jar;"%REPO%"\jetty-http-8.1.7.v20120910.jar;"%REPO%"\jetty-servlets-8.1.7.v20120910.jar;"%REPO%"\jetty-client-8.1.7.v20120910.jar;"%REPO%"\jetty-util-8.1.7.v20120910.jar;"%REPO%"\jetty-servlet-8.1.7.v20120910.jar;"%REPO%"\jetty-security-8.1.7.v20120910.jar;"%REPO%"\jetty-webapp-8.1.7.v20120910.jar;"%REPO%"\jetty-xml-8.1.7.v20120910.jar;"%REPO%"\amqp-client-3.3.5.jar;"%REPO%"\cxf-rt-frontend-jaxws-2.7.5.jar;"%REPO%"\xml-resolver-1.2.jar;"%REPO%"\asm-3.3.1.jar;"%REPO%"\cxf-api-2.7.5.jar;"%REPO%"\cxf-rt-core-2.7.5.jar;"%REPO%"\cxf-rt-bindings-soap-2.7.5.jar;"%REPO%"\cxf-rt-databinding-jaxb-2.7.5.jar;"%REPO%"\cxf-rt-bindings-xml-2.7.5.jar;"%REPO%"\cxf-rt-frontend-simple-2.7.5.jar;"%REPO%"\cxf-rt-ws-addr-2.7.5.jar;"%REPO%"\cxf-rt-transports-http-2.7.5.jar;"%REPO%"\cxf-rt-transports-http-jetty-2.7.5.jar;"%REPO%"\geronimo-servlet_3.0_spec-1.0.jar;"%REPO%"\cxf-rt-ws-security-2.7.5.jar;"%REPO%"\ehcache-core-2.5.1.jar;"%REPO%"\wss4j-1.6.10.jar;"%REPO%"\xmlsec-1.5.4.jar;"%REPO%"\opensaml-2.5.1-1.jar;"%REPO%"\openws-1.4.2-1.jar;"%REPO%"\xmltooling-1.3.2-1.jar;"%REPO%"\commons-logging-1.1.1.jar;"%REPO%"\cxf-rt-ws-policy-2.7.5.jar;"%REPO%"\neethi-3.0.2.jar;"%REPO%"\cxf-bundle-jaxrs-2.7.5.jar;"%REPO%"\woodstox-core-asl-4.2.0.jar;"%REPO%"\stax2-api-3.1.1.jar;"%REPO%"\xmlschema-core-2.0.3.jar;"%REPO%"\geronimo-javamail_1.4_spec-1.7.1.jar;"%REPO%"\wsdl4j-1.6.3.jar;"%REPO%"\jaxb-impl-2.1.13.jar;"%REPO%"\jetty-io-8.1.7.v20120910.jar;"%REPO%"\javax.ws.rs-api-2.0-m10.jar;"%REPO%"\jsr311-api-1.1.1.jar;"%REPO%"\slf4j-api-1.7.5.jar;"%REPO%"\log4j-over-slf4j-1.7.5.jar;"%REPO%"\jcl-over-slf4j-1.7.5.jar;"%REPO%"\logback-core-1.0.11.jar;"%REPO%"\logback-classic-1.0.11.jar;"%REPO%"\log4jdbc3-1.2.1.jar;"%REPO%"\fastjson-1.1.23.jar;"%REPO%"\commons-lang3-3.1.jar;"%REPO%"\commons-codec-1.6.jar;"%REPO%"\commons-dbcp-1.4.jar;"%REPO%"\commons-pool-1.5.4.jar;"%REPO%"\commons-fileupload-1.2.1.jar;"%REPO%"\commons-configuration-1.9.jar;"%REPO%"\commons-lang-2.6.jar;"%REPO%"\commons-beanutils-1.8.3.jar;"%REPO%"\commons-collections-3.2.1.jar;"%REPO%"\commons-validator-1.4.0.jar;"%REPO%"\commons-digester-1.8.jar;"%REPO%"\servlet-api-3.0-alpha-1.jar;"%REPO%"\httpclient-4.2.1.jar;"%REPO%"\httpcore-4.2.1.jar;"%REPO%"\mysql-connector-java-5.1.21.jar;"%REPO%"\druid-0.2.9.jar;"%REPO%"\c3p0-0.9.1.2.jar;"%REPO%"\guava-13.0.1.jar;"%REPO%"\reflections-0.9.8.jar;"%REPO%"\javassist-3.12.1.GA.jar;"%REPO%"\janino-2.6.1.jar;"%REPO%"\commons-compiler-2.6.1.jar;"%REPO%"\xstream-1.4.4.jar;"%REPO%"\xmlpull-1.1.3.1.jar;"%REPO%"\xpp3_min-1.1.4c.jar;"%REPO%"\joda-time-2.3.jar;"%REPO%"\base-1.3.jar;"%REPO%"\activiti-engine-5.14.jar;"%REPO%"\activiti-bpmn-converter-5.14.jar;"%REPO%"\activiti-bpmn-model-5.14.jar;"%REPO%"\activiti-spring-5.14.jar;"%REPO%"\standDemo-1.0.0.jar
set EXTRA_JVM_ARGUMENTS=-Xms128m
goto endInit

@REM Reaching here means variables are defined and arguments have been captured
:endInit

%JAVACMD% %JAVA_OPTS% %EXTRA_JVM_ARGUMENTS% -classpath %CLASSPATH_PREFIX%;%CLASSPATH% -Dapp.name="Main" -Dapp.repo="%REPO%" -Dbasedir="%BASEDIR%" cn.ctyun.start.App %CMD_LINE_ARGS%
if ERRORLEVEL 1 goto error
goto end

:error
if "%OS%"=="Windows_NT" @endlocal
set ERROR_CODE=%ERRORLEVEL%

:end
@REM set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" goto endNT

@REM For old DOS remove the set variables from ENV - we assume they were not set
@REM before we started - at least we don't leave any baggage around
set CMD_LINE_ARGS=
goto postExec

:endNT
@REM If error code is set to 1 then the endlocal was done already in :error.
if %ERROR_CODE% EQU 0 @endlocal


:postExec

if "%FORCE_EXIT_ON_ERROR%" == "on" (
  if %ERROR_CODE% NEQ 0 exit %ERROR_CODE%
)

exit /B %ERROR_CODE%
