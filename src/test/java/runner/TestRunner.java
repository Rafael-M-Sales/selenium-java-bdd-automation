package runner;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.*;

/**
 * TestRunner: Ponto de entrada para execução dos testes BDD
 * Projeto: selenium-java-bdd
 *
 * Para executar: mvn test
 * Para executar por tag: mvn test -Dcucumber.filter.tags="@login"
 */
@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty, " +
        "html:target/cucumber-reports/cucumber-report.html, " +
        "json:target/cucumber-reports/cucumber-report.json, " +
        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "steps,hooks")
@ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME, value = "not @ignore")
@ConfigurationParameter(key = FEATURES_PROPERTY_NAME, value = "src/test/resources/features")
@ConfigurationParameter(key = SNIPPET_TYPE_PROPERTY_NAME, value = "camelcase")
public class TestRunner {
    // Classe de configuração — não adicionar lógica aqui
}
