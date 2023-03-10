package cloud.intermission.quarkus.config;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class TemplateEngineProducer {
    @Produces
    @ApplicationScoped
    public TemplateEngine createTemplateEngine() {
        TemplateEngine templateEngine = new TemplateEngine();

        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setPrefix("templates/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode("HTML");
        resolver.setOrder(1);
        resolver.setCacheable(true);

        templateEngine.setTemplateResolver(resolver);

        return templateEngine;
    }
}
