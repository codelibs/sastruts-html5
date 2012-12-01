sastruts-html5
=============

Overview
-------------

sastruts-html5 provides HTML5 supports for SAStruts.

Getting Started
-------------

### Add sastruts-html5

Add the following dependency into your pom.xml.

    <repositories>
      <repository>
        <id>fess.sourceforge.jp</id>
        <name>Fess Repository</name>
        <url>http://fess.sourceforge.jp/repository/</url>
      </repository>
    </repositories>
    ...
    <dependency>
      <groupId>org.codelibs.sastruts</groupId>
      <artifactId>sastruts-html5</artifactId>
      <version>0.1.0-SNAPSHOT</version>
    </dependency>

### Replace taglib definition

In common.jsp, replace
    <%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
with
    <%@taglib prefix="html" uri="http://struts.codelibs.org/tags-html"%>

That's it!
