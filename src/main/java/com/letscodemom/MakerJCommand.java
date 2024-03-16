package com.letscodemom;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.stereotype.Component;
import org.springframework.context.ConfigurableApplicationContext;

import javax.management.Attribute;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

@Component
public class MakerJCommand implements SpringApplicationRunListener {

    public void started(ConfigurableApplicationContext context, Duration timeTaken) {
        createEntity();
    }

    public void createEntity() {
        // Parse command-line arguments (replace with actual parsing logic)
        String entityName = "Product"; // Replace with parsed value

        // Prompt user for attributes (replace with interactive prompts)
        /* la clase Attribute se utiliza para representar un atributo o propiedad de un objeto.
        Es una clase abstracta que define una interfaz básica para acceder y modificar
        el valor de un atributo. */

        List<Attribute> attributes = new ArrayList<>();
        attributes.add(new Attribute("name", "String"));
        attributes.add(new Attribute("price", "Integer"));

        // Generate class code using FreeMarker (replace with actual logic)
        generateClass(entityName, attributes);
    }

    private void generateClass(String entityName, List<Attribute> attributes) {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_31);
        configuration.setClassForTemplateLoading(MakerJCommand.class, "/templates"); // Directorio donde se encuentran las plantillas

        try {
            Template template = configuration.getTemplate("entity.ftl"); // Nombre de la plantilla
            Map<String, Object> dataModel = new HashMap<>();
            dataModel.put("entityName", entityName);
            dataModel.put("attributes", attributes);

            Writer fileWriter = new FileWriter("GeneratedClass.java"); // Nombre del archivo generado
            template.process(dataModel, fileWriter);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}
