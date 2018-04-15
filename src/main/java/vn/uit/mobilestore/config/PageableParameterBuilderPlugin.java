package vn.uit.mobilestore.config;

import com.fasterxml.classmate.TypeResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.ResolvedMethodParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.ParameterBuilderPlugin;
import springfox.documentation.spi.service.contexts.ParameterContext;
import springfox.documentation.swagger.common.SwaggerPluginSupport;

import java.util.ArrayList;
import java.util.List;

@Component
@Order(SwaggerPluginSupport.SWAGGER_PLUGIN_ORDER)
public class PageableParameterBuilderPlugin implements ParameterBuilderPlugin {

    @Autowired
    private TypeResolver resolver;

    @Override
    public  boolean supports(DocumentationType documentationType) {
        return DocumentationType.SWAGGER_2.equals(documentationType);
    }

    @Override
    public void apply(ParameterContext context) {
        ResolvedMethodParameter resolvedMethodParameter = context.resolvedMethodParameter();
        if (Pageable.class.isAssignableFrom(resolvedMethodParameter.getParameterType().getErasedType())) {
            List<Parameter> parameters = new ArrayList();
            parameters.add(context.parameterBuilder()
                    .parameterType("query").name("page")
                    .type(resolver.resolve(Integer.class))
                    .modelRef(new ModelRef("int"))
                    .description("Page number of the requested page")
                    .build());
            parameters.add(context.parameterBuilder()
                    .parameterType("query").name("size")
                    .type(resolver.resolve(Integer.class))
                    .modelRef(new ModelRef("int"))
                    .description("Size of a page")
                    .build()
            );
            parameters.add(context.parameterBuilder()
                    .parameterType("query").name("sort")
                    .type(resolver.resolve(String.class))
                    .modelRef(new ModelRef("string"))
                    .allowMultiple(true)
                    .description("Sorting criteria in the format: property(,asc|desc). "
                            + "Default sort order is ascending. "
                            + "Multiple sort criteria are supported.")
                    .build());

            context.getOperationContext().operationBuilder().parameters(parameters);
        }
    }

}