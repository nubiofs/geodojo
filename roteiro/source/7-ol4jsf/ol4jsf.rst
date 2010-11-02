:Author: Rafael Soto
:Author: Robert Anderson
:Version: |release|
:License: Create Commons with attribution

********************************************************************
Disponibilizando os mapas de forma rica na web - OpenLayers & Ol4JSF
********************************************************************

.. contents::

Nesse capítulo vamos entender qual a relação existente entre o OpenLayers e o OL4JSF. Aprenderemos como instalar, configurar e utilizar os recursos disponíveis no OL4JSF em nossas aplicações JEE.

###################
OpenLayers & OL4JSF
###################

O OpenLayers é uma biblioteca javascript, opensource, que possui uma grande variedade de recursos para plotagem de mapas nos principais navegadores do mercado. Se ela é tão boa assim, então qual o problema?

Um dos grandes problemas não é a API em si, mas o javascript. Por ser uma linguagem dinâmica, as IDEs não conseguem ajudar muito e os desenvolvedores acabam sempre tendo que recorrer para a documentação para fazer as mínimas coisas. Em qual pacote está a classe responsável por exibir o mapa? Quais são parâmetros para instanciação? Enfim, cabe ao desenvolvedor conhecer cada detalhe da API através da documentação.

É nesse cenário que entra o OL4JSF. Ele encapsula boa parte da lógica responsável pela instanciação dos objetos OpenLayers e ainda, pelo fato de ser um conjunto de componentes JSF, integra-se perfeitamente aos ambientes de desenvolvimento JEE atuais: auto completar, ajuda contextual, janela de propriedades, palheta de componentes, etc.

Mas agora vem a melhor parte. O OL4JSF já foi concebido para facilitar o desenvolvimento, porém sem "engessá-lo". Como assim? Desenvolvedores experientes com OpenLayers na maioria das vezes vão querer ter a possibilidade de acessar os recursos da API diretamente. Além disso, nem todas as funcionalidades existentes no OpenLayers estarão disponíveis no OL4JSF, ou pelo menos, poderão levar um certo tempo para serem implementadas. Sendo assim, os componentes OL4JSF podem ser disponibilizados para o contexto javascript na forma de variáveis globais e serem acessados normalmente em qualquer código javascript da página. Adicionalmente, o OL4JSF já disponibiliza um componente *<m:script />* que possibilita a inserção de código javascript diretamente na elaboração do mapa. Então, que tal visualizarmos e isso e muito mais na prática?

####################
Instalação do OL4JSF
####################

Vamos instalar o OL4JSF via Maven. Para isso basta colocar a seguintes entradas no pom.xml:

.. code-block:: xml

	<repositories>
	<!-- Outros repositórios omitidos -->
	<repository>
		<id>java.net2</id>
		<name>Repository hosting the jee6 artifacts</name>
		<url>http://download.java.net/maven/2</url>
	</repository>
	</repositories>
	<dependencies>
		<!-- Outras dependencias omitidas -->
	        <dependency>
	            <groupId>org.ol4jsf</groupId>
	            <artifactId>ol4jsf-core</artifactId>
	            <version>2.0-SNAPSHOT</version>
	            <scope>compile</scope>
	        </dependency>
	</dependencies>

Nesse altura do campeonato, o nosso pom deve estar assim:

.. code-block:: xml
	:linenos:

	<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	    <modelVersion>4.0.0</modelVersion>
	
	    <groupId>org.latinoware.geodojo</groupId>
	    <artifactId>geodojo</artifactId>
	    <version>1.0-SNAPSHOT</version>
	    <name>Geodojo Project</name>
	    <packaging>war</packaging>
	
	    <repositories>
	        <repository>
	            <id>java.net2</id>
	            <name>Repository hosting the jee6 artifacts</name>
	            <url>http://download.java.net/maven/2</url>
	        </repository>
	        <repository>
	            <id>EclipseLink Repo</id>
	            <url>http://www.eclipse.org/downloads/download.php?r=1&amp;nf=1&amp;file=/rt/eclipselink/maven.repo</url>
	        </repository>
	        <repository>
	            <id>OSGEO GeoTools repo</id>
	            <url>http://download.osgeo.org/webdav/geotools</url>
	        </repository>
	        <repository>
	            <id>Hibernate Spatial repo</id>
	            <url>http://www.hibernatespatial.org/repository</url>
	        </repository>
	        <!-- add JBOSS repository for easy access to Hibernate libraries -->
	        <repository>
	            <id>JBOSS</id>
	            <url>http://repository.jboss.com/maven2</url>
	        </repository>
			      
	    </repositories>
	
	    <properties>
	        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
	    </properties>
	
	    <build>
	        <plugins>
	            <plugin>
	                <groupId>org.apache.maven.plugins</groupId>
	                <artifactId>maven-compiler-plugin</artifactId>
	                <configuration>
	                    <source>1.6</source>
	                    <target>1.6</target>
	                </configuration>
	            </plugin>
	        </plugins>
	    </build>
	
	    <dependencies>
	        <dependency>
	            <groupId>javax.servlet</groupId>
	            <artifactId>jstl</artifactId>
	            <version>1.2</version>
	        </dependency>
	        <dependency>
	            <groupId>javax.servlet</groupId>
	            <artifactId>servlet-api</artifactId>
	            <version>2.5</version>
	        </dependency>
	        <dependency>
	            <groupId>hsqldb</groupId>
	            <artifactId>hsqldb</artifactId>
	            <version>1.8.0.10</version>
	            <scope>compile</scope>
	        </dependency>
	        <dependency>
	            <groupId>javax.enterprise</groupId>
	            <artifactId>cdi-api</artifactId>
	            <version>1.0-SP1</version>
	            <scope>compile</scope>
	        </dependency>
	        <dependency>
	            <groupId>org.jboss.weld</groupId>
	            <artifactId>weld-api</artifactId>
	            <version>1.0-SP1</version>
	            <scope>runtime</scope>
	        </dependency>
	        <dependency>
	            <groupId>org.jboss.weld.servlet</groupId>
	            <artifactId>weld-servlet</artifactId>
	            <version>1.0.1-Final</version>
	            <scope>runtime</scope>
	        </dependency>
	        <dependency>
	            <groupId>com.sun.faces</groupId>
	            <artifactId>jsf-api</artifactId>
	            <version>2.0.3</version>
	            <scope>compile</scope>
	        </dependency>
	        <dependency>
	            <groupId>com.sun.faces</groupId>
	            <artifactId>jsf-impl</artifactId>
	            <version>2.0.3</version>
	            <scope>runtime</scope>
	        </dependency>
	        <dependency>
	            <groupId>hibernate</groupId>
	            <artifactId>hibernate-entitymanager</artifactId>
	            <version>3.4.0.GA</version>
	        </dependency>	        	        
	        
	        <!-- the Postgis JDBC driver -->
	        <dependency>
	            <groupId>org.postgis</groupId>
	            <artifactId>postgis-jdbc</artifactId>
	            <version>1.3.3</version>
	        </dependency>
	
	        <!-- the postgresql driver -->
	        <dependency>
	            <groupId>postgresql</groupId>
	            <artifactId>postgresql</artifactId>
	            <!-- scope>provided</scope>  -->
	            <version>8.4-701.jdbc3</version>
	        </dependency>
	
	        <!-- Hibernate uses slf4j for logging, for our purposes here use the simple backend -->
	        <dependency>
	            <groupId>org.slf4j</groupId>
	            <artifactId>slf4j-simple</artifactId>
	            <version>1.5.6</version>
	        </dependency>
	               
	        <dependency>
	            <groupId>org.slf4j</groupId>
	            <artifactId>slf4j-api</artifactId>
	            <version>1.5.6</version>
	        </dependency>	        
	       
	        <!-- GIS DEPENDENCIES -->
	        <dependency>
	            <groupId>org.ol4jsf</groupId>
	            <artifactId>ol4jsf-core</artifactId>
	            <version>2.0-SNAPSHOT</version>
	            <scope>compile</scope>
	        </dependency>
	        <dependency>
	            <groupId>org.hibernatespatial</groupId>
	            <artifactId>hibernate-spatial-postgis</artifactId>
	            <version>1.0</version>
	        </dependency>	
	    </dependencies>		
	</project>
