package com.diegogin.motorcyclesapi.documentation;

import jakarta.servlet.http.HttpServletRequest;
import org.springdoc.core.providers.ObjectMapperProvider;
import org.springdoc.core.properties.SwaggerUiConfigProperties;
import org.springdoc.core.properties.SwaggerUiOAuthProperties;
import org.springdoc.webmvc.ui.SwaggerIndexPageTransformer;
import org.springdoc.webmvc.ui.SwaggerWelcomeCommon;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.resource.ResourceTransformerChain;

import org.springframework.core.io.ByteArrayResource;

import java.nio.charset.StandardCharsets;

import java.io.IOException;

public class SwaggerIndexTransformer extends SwaggerIndexPageTransformer {

    public SwaggerIndexTransformer(
            SwaggerUiConfigProperties swaggerUiConfig,
            SwaggerUiOAuthProperties swaggerUiOAuthProperties,
            SwaggerWelcomeCommon swaggerWelcomeCommon,
            ObjectMapperProvider objectMapperProvider) {

        super(
                swaggerUiConfig,
                swaggerUiOAuthProperties,
                swaggerWelcomeCommon,
                objectMapperProvider
        );
    }

    @Override
    public Resource transform(
            HttpServletRequest request,
            Resource resource,
            ResourceTransformerChain transformer
    ) throws IOException {

        Resource transformedResource =
                super.transform(request, resource, transformer);

        if (!"swagger-initializer.js".equals(resource.getFilename())) {
            return transformedResource;
        }

        String content = transformedResource.getContentAsString(
                StandardCharsets.UTF_8
        );

        String operationsSorter = """
            operationsSorter: (a, b) => {
                const order = {
                    getAuthStatus: 1,
                    getAllMotorcycles: 2,
                    getMotorcycleById: 3,
                    createMotorcycle: 4,
                    updateMotorcycle: 5,
                    deleteMotorcycle: 6
                };

                const aId = a.get("operation").get("operationId");
                const bId = b.get("operation").get("operationId");

                return (order[aId] ?? 99) - (order[bId] ?? 99);
            },
            """;

        content = content.replace(
                "SwaggerUIBundle({",
                "SwaggerUIBundle({\n" + operationsSorter
        );

        return new ByteArrayResource(
                content.getBytes(StandardCharsets.UTF_8)
        ) {
            @Override
            public String getFilename() {
                return resource.getFilename();
            }
        };
    }

}