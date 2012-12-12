moskito-jboss
=============

Integrating MoSKito DevOps monitoring tool into JBoss AS 7 Java EE 6 application.

Runs with:
* MoSKito 2.1.2 (See changelog https://confluence.opensource.anotheria.net/display/MSK/03+Change+Log)
* Java EE 6 Stack, including CDI, EJB 3.1 and Seam 3.1 extension
* JBoss AS 7.1.1

Getting started 
===============

**1)** Git clone or download source code, config settings.xml: JBoss Nexus repository needs to be added in order to resolve Seam dependencies
```XML
<repository>
    <id>jboss-public-repository-group</id>
    <name>JBoss Public Maven Repository Group</name>
    <url>http://repository.jboss.org/nexus/content/repositories/releases</url>
</repository>
```

**2)** Setup build.properties, use provided build.properties.template as template. This is mainly useful for both Maven build and starting JBoss from IDE.

**3)** Build project with Maven: mvn clean install -U

**4)** Download JBoss AS 7: http://www.jboss.org/jbossas/downloads/

**5)** Setup DataSource: for demo application we are using InProcess HSQL Database, define within //JBOSS_HOME/standalone/configuration/standalone.xml
```XML
<subsystem xmlns="urn:jboss:domain:datasources:1.0">
      <datasources>
          <datasource jta="true" jndi-name="java:/DefaultDS" pool-name="H2DS" enabled="true" use-java-context="true">
              <connection-url>jdbc:h2:mem:test;DB_CLOSE_DELAY=-1</connection-url>
              <driver>h2</driver>
              <security>
                  <user-name>sa</user-name>
                  <password>sa</password>
              <security>
          </datasource>
      </datasources>
</subsystem>
```

**6)** Start JBoss and deploy artifact //PROJECT-HOME//moskitojboss-web/target/moskitojboss.war to //JBOSS_HOME/standalone/deployments

**7)** Start application in Browser: http://localhost:8080/moskitojboss/

**8)** Login with initial user or create your own

**9)** Play around, add some tasks and check MoSKito Producers, Accumulators and Thresholds localhost:8080/moskitojboss/mui/

**10)** Give us feedback and/or contribute in any ways. :-)
