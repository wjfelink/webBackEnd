#!/bin/sh
# ----------------------------------------------------------------------------
#  Copyright 2001-2006 The Apache Software Foundation.
#
#  Licensed under the Apache License, Version 2.0 (the "License");
#  you may not use this file except in compliance with the License.
#  You may obtain a copy of the License at
#
#       http://www.apache.org/licenses/LICENSE-2.0
#
#  Unless required by applicable law or agreed to in writing, software
#  distributed under the License is distributed on an "AS IS" BASIS,
#  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
#  See the License for the specific language governing permissions and
#  limitations under the License.
# ----------------------------------------------------------------------------
#
#   Copyright (c) 2001-2006 The Apache Software Foundation.  All rights
#   reserved.


# resolve links - $0 may be a softlink
PRG="$0"

while [ -h "$PRG" ]; do
  ls=`ls -ld "$PRG"`
  link=`expr "$ls" : '.*-> \(.*\)$'`
  if expr "$link" : '/.*' > /dev/null; then
    PRG="$link"
  else
    PRG=`dirname "$PRG"`/"$link"
  fi
done

PRGDIR=`dirname "$PRG"`
BASEDIR=`cd "$PRGDIR/.." >/dev/null; pwd`



# OS specific support.  $var _must_ be set to either true or false.
cygwin=false;
darwin=false;
case "`uname`" in
  CYGWIN*) cygwin=true ;;
  Darwin*) darwin=true
           if [ -z "$JAVA_VERSION" ] ; then
             JAVA_VERSION="CurrentJDK"
           else
             echo "Using Java version: $JAVA_VERSION"
           fi
           if [ -z "$JAVA_HOME" ] ; then
             JAVA_HOME=/System/Library/Frameworks/JavaVM.framework/Versions/${JAVA_VERSION}/Home
           fi
           ;;
esac

if [ -z "$JAVA_HOME" ] ; then
  if [ -r /etc/gentoo-release ] ; then
    JAVA_HOME=`java-config --jre-home`
  fi
fi

# For Cygwin, ensure paths are in UNIX format before anything is touched
if $cygwin ; then
  [ -n "$JAVA_HOME" ] && JAVA_HOME=`cygpath --unix "$JAVA_HOME"`
  [ -n "$CLASSPATH" ] && CLASSPATH=`cygpath --path --unix "$CLASSPATH"`
fi

# If a specific java binary isn't specified search for the standard 'java' binary
if [ -z "$JAVACMD" ] ; then
  if [ -n "$JAVA_HOME"  ] ; then
    if [ -x "$JAVA_HOME/jre/sh/java" ] ; then
      # IBM's JDK on AIX uses strange locations for the executables
      JAVACMD="$JAVA_HOME/jre/sh/java"
    else
      JAVACMD="$JAVA_HOME/bin/java"
    fi
  else
    JAVACMD=`which java`
  fi
fi

if [ ! -x "$JAVACMD" ] ; then
  echo "Error: JAVA_HOME is not defined correctly." 1>&2
  echo "  We cannot execute $JAVACMD" 1>&2
  exit 1
fi

if [ -z "$REPO" ]
then
  REPO="$BASEDIR"/lib
fi

CLASSPATH=$CLASSPATH_PREFIX:"$BASEDIR"/conf:"$REPO"/dom4j-1.6.1.jar:"$REPO"/xml-apis-1.0.b2.jar:"$REPO"/jaxen-1.1.4.jar:"$REPO"/commons-email-1.3.1.jar:"$REPO"/mail-1.4.5.jar:"$REPO"/activation-1.1.1.jar:"$REPO"/mybatis-3.1.1.jar:"$REPO"/mybatis-spring-1.1.1.jar:"$REPO"/spring-core-3.1.2.RELEASE.jar:"$REPO"/spring-asm-3.1.2.RELEASE.jar:"$REPO"/spring-beans-3.1.2.RELEASE.jar:"$REPO"/spring-context-3.1.2.RELEASE.jar:"$REPO"/spring-expression-3.1.2.RELEASE.jar:"$REPO"/spring-context-support-3.1.2.RELEASE.jar:"$REPO"/spring-aop-3.1.2.RELEASE.jar:"$REPO"/aopalliance-1.0.jar:"$REPO"/spring-tx-3.1.2.RELEASE.jar:"$REPO"/spring-jdbc-3.1.2.RELEASE.jar:"$REPO"/spring-orm-3.1.2.RELEASE.jar:"$REPO"/spring-web-3.1.2.RELEASE.jar:"$REPO"/spring-webmvc-3.1.2.RELEASE.jar:"$REPO"/aspectjrt-1.6.10.jar:"$REPO"/aspectjweaver-1.6.10.jar:"$REPO"/cglib-nodep-3.1.jar:"$REPO"/quartz-1.8.5.jar:"$REPO"/jta-1.1.jar:"$REPO"/jackson-core-asl-1.9.9.jar:"$REPO"/jackson-mapper-asl-1.9.9.jar:"$REPO"/jackson-jaxrs-1.9.9.jar:"$REPO"/jetty-server-8.1.7.v20120910.jar:"$REPO"/javax.servlet-3.0.0.v201112011016.jar:"$REPO"/jetty-continuation-8.1.7.v20120910.jar:"$REPO"/jetty-http-8.1.7.v20120910.jar:"$REPO"/jetty-servlets-8.1.7.v20120910.jar:"$REPO"/jetty-client-8.1.7.v20120910.jar:"$REPO"/jetty-util-8.1.7.v20120910.jar:"$REPO"/jetty-servlet-8.1.7.v20120910.jar:"$REPO"/jetty-security-8.1.7.v20120910.jar:"$REPO"/jetty-webapp-8.1.7.v20120910.jar:"$REPO"/jetty-xml-8.1.7.v20120910.jar:"$REPO"/amqp-client-3.3.5.jar:"$REPO"/cxf-rt-frontend-jaxws-2.7.5.jar:"$REPO"/xml-resolver-1.2.jar:"$REPO"/asm-3.3.1.jar:"$REPO"/cxf-api-2.7.5.jar:"$REPO"/cxf-rt-core-2.7.5.jar:"$REPO"/cxf-rt-bindings-soap-2.7.5.jar:"$REPO"/cxf-rt-databinding-jaxb-2.7.5.jar:"$REPO"/cxf-rt-bindings-xml-2.7.5.jar:"$REPO"/cxf-rt-frontend-simple-2.7.5.jar:"$REPO"/cxf-rt-ws-addr-2.7.5.jar:"$REPO"/cxf-rt-transports-http-2.7.5.jar:"$REPO"/cxf-rt-transports-http-jetty-2.7.5.jar:"$REPO"/geronimo-servlet_3.0_spec-1.0.jar:"$REPO"/cxf-rt-ws-security-2.7.5.jar:"$REPO"/ehcache-core-2.5.1.jar:"$REPO"/wss4j-1.6.10.jar:"$REPO"/xmlsec-1.5.4.jar:"$REPO"/opensaml-2.5.1-1.jar:"$REPO"/openws-1.4.2-1.jar:"$REPO"/xmltooling-1.3.2-1.jar:"$REPO"/commons-logging-1.1.1.jar:"$REPO"/cxf-rt-ws-policy-2.7.5.jar:"$REPO"/neethi-3.0.2.jar:"$REPO"/cxf-bundle-jaxrs-2.7.5.jar:"$REPO"/woodstox-core-asl-4.2.0.jar:"$REPO"/stax2-api-3.1.1.jar:"$REPO"/xmlschema-core-2.0.3.jar:"$REPO"/geronimo-javamail_1.4_spec-1.7.1.jar:"$REPO"/wsdl4j-1.6.3.jar:"$REPO"/jaxb-impl-2.1.13.jar:"$REPO"/jetty-io-8.1.7.v20120910.jar:"$REPO"/javax.ws.rs-api-2.0-m10.jar:"$REPO"/jsr311-api-1.1.1.jar:"$REPO"/slf4j-api-1.7.5.jar:"$REPO"/log4j-over-slf4j-1.7.5.jar:"$REPO"/jcl-over-slf4j-1.7.5.jar:"$REPO"/logback-core-1.0.11.jar:"$REPO"/logback-classic-1.0.11.jar:"$REPO"/log4jdbc3-1.2.1.jar:"$REPO"/fastjson-1.1.23.jar:"$REPO"/commons-lang3-3.1.jar:"$REPO"/commons-codec-1.6.jar:"$REPO"/commons-dbcp-1.4.jar:"$REPO"/commons-pool-1.5.4.jar:"$REPO"/commons-fileupload-1.2.1.jar:"$REPO"/commons-configuration-1.9.jar:"$REPO"/commons-lang-2.6.jar:"$REPO"/commons-beanutils-1.8.3.jar:"$REPO"/commons-collections-3.2.1.jar:"$REPO"/commons-validator-1.4.0.jar:"$REPO"/commons-digester-1.8.jar:"$REPO"/servlet-api-3.0-alpha-1.jar:"$REPO"/httpclient-4.2.1.jar:"$REPO"/httpcore-4.2.1.jar:"$REPO"/mysql-connector-java-5.1.21.jar:"$REPO"/druid-0.2.9.jar:"$REPO"/c3p0-0.9.1.2.jar:"$REPO"/guava-13.0.1.jar:"$REPO"/reflections-0.9.8.jar:"$REPO"/javassist-3.12.1.GA.jar:"$REPO"/janino-2.6.1.jar:"$REPO"/commons-compiler-2.6.1.jar:"$REPO"/xstream-1.4.4.jar:"$REPO"/xmlpull-1.1.3.1.jar:"$REPO"/xpp3_min-1.1.4c.jar:"$REPO"/joda-time-2.3.jar:"$REPO"/base-1.3.jar:"$REPO"/activiti-engine-5.14.jar:"$REPO"/activiti-bpmn-converter-5.14.jar:"$REPO"/activiti-bpmn-model-5.14.jar:"$REPO"/activiti-spring-5.14.jar:"$REPO"/standDemo-1.0.0.jar
EXTRA_JVM_ARGUMENTS="-Xms128m"

# For Cygwin, switch paths to Windows format before running java
if $cygwin; then
  [ -n "$CLASSPATH" ] && CLASSPATH=`cygpath --path --windows "$CLASSPATH"`
  [ -n "$JAVA_HOME" ] && JAVA_HOME=`cygpath --path --windows "$JAVA_HOME"`
  [ -n "$HOME" ] && HOME=`cygpath --path --windows "$HOME"`
  [ -n "$BASEDIR" ] && BASEDIR=`cygpath --path --windows "$BASEDIR"`
  [ -n "$REPO" ] && REPO=`cygpath --path --windows "$REPO"`
fi

exec "$JAVACMD" $JAVA_OPTS \
  $EXTRA_JVM_ARGUMENTS \
  -classpath "$CLASSPATH" \
  -Dapp.name="Main" \
  -Dapp.pid="$$" \
  -Dapp.repo="$REPO" \
  -Dapp.home="$BASEDIR" \
  -Dbasedir="$BASEDIR" \
  cn.ctyun.start.App \
  "$@"
