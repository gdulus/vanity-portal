grails.servlet.version = "3.0" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.target.level = 1.6
grails.project.source.level = 1.6
//grails.project.war.file = "target/${appName}-${appVersion}.war"

// uncomment (and adjust settings) to fork the JVM to isolate classpaths
//grails.project.fork = [
//   run: [maxMemory:1024, minMemory:64, debug:false, maxPerm:256]
//]

grails.project.dependency.resolution = {

    inherits("global") {}
    log "error"          // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    checksums true       // Whether to verify checksums on resolve
    legacyResolve false
    // whether to do a secondary resolve on plugin installation, not advised and here for backwards compatibility

    repositories {
        inherits true    // Whether to inherit repository definitions from plugins
        grailsPlugins()
        grailsHome()
        grailsCentral()
        mavenLocal()
        mavenCentral()
        mavenRepo 'http://maven.restlet.org'
    }

    dependencies {
        compile('postgresql:postgresql:9.1-901.jdbc4')
        compile('commons-lang:commons-lang:2.6')
    }

    plugins {
        build ":tomcat:7.0.52.1"
        runtime ":jquery:1.8.3"
        runtime ":database-migration:1.2.1"
        runtime ":hibernate:3.6.10.10"
        compile ":cache:1.1.1"
        compile ":asset-pipeline:1.7.1"
        compile ":less-asset-pipeline:1.7.0"
        compile ":mail:1.0.4"
    }
}

grails.plugin.location.'vanity-core' = '../vanity-core'
grails.plugin.location.'vanity-search' = '../vanity-search'
