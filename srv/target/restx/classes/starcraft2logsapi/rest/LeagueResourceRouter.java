package starcraft2logsapi.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.common.base.Optional;
import com.google.common.base.Suppliers;
import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableList;
import restx.common.Types;
import restx.common.TypeReference;
import restx.*;
import restx.entity.*;
import restx.http.*;
import restx.endpoint.*;
import restx.exceptions.WrappedCheckedException;
import restx.factory.*;
import restx.security.*;
import restx.security.PermissionFactory;
import restx.description.*;
import restx.converters.MainStringConverter;
import static restx.common.MorePreconditions.checkPresent;

import javax.validation.Validator;
import static restx.validation.Validations.checkValid;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.Arrays;


@Component(priority = 0)

public class LeagueResourceRouter extends RestxRouter {

    public LeagueResourceRouter(
                    final LeagueResource resource,
                    final EntityRequestBodyReaderRegistry readerRegistry,
                    final EntityResponseWriterRegistry writerRegistry,
                    final MainStringConverter converter,
                    final PermissionFactory pf,
                    final Optional<Validator> validator,
                    final RestxSecurityManager securityManager,
                    final EndpointParameterMapperRegistry paramMapperRegistry) {
        super(
            "default", "LeagueResourceRouter", new RestxRoute[] {
        new StdEntityRoute<Void, java.lang.String>("default#LeagueResource#leagues",
                readerRegistry.<Void>build(Void.class, Optional.<String>absent()),
                writerRegistry.<java.lang.String>build(java.lang.String.class, Optional.<String>absent()),
                Endpoint.of("GET", "/leagues/{region}"),
                HttpStatus.OK, RestxLogLevel.DEFAULT, pf,
                paramMapperRegistry, new ParamDef[]{
                    ParamDef.of(new TypeReference<java.lang.String>(){}, "region")
                }) {
            @Override
            protected Optional<java.lang.String> doRoute(RestxRequest request, RestxResponse response, RestxRequestMatch match, Void body) throws IOException {
                securityManager.check(request, match, open());
                try {
                    return Optional.of(resource.leagues(
                        /* [PATH] region */ checkValid(validator, checkNotNull(mapQueryObjectFromRequest(java.lang.String.class, "region", request, match, EndpointParameterKind.PATH), "PATH param <region> is required"))
                    ));
                } catch(RuntimeException e) { throw e; }
                  catch(IOException e) { throw e; }
                  catch(Exception e) { throw new WrappedCheckedException(e); }
            }

            @Override
            protected void describeOperation(OperationDescription operation) {
                super.describeOperation(operation);
                OperationParameterDescription region = new OperationParameterDescription();
                region.name = "region";
                region.paramType = OperationParameterDescription.ParamType.path;
                region.dataType = "string";
                region.schemaKey = "";
                region.required = true;
                operation.parameters.add(region);


                operation.responseClass = "string";
                operation.inEntitySchemaKey = "";
                operation.inEntityType = Void.class;
                operation.outEntitySchemaKey = "";
                operation.outEntityType = java.lang.String.class;
                operation.sourceLocation = "starcraft2logsapi.rest.LeagueResource#leagues(java.lang.String)";
                operation.annotations = ImmutableList.<java.lang.annotation.Annotation>builder()
                    .add(new restx.annotations.GET() {
                        public Class<restx.annotations.GET> annotationType() { return restx.annotations.GET.class; }
                        public java.lang.String value() { return "/leagues/{region}"; }
                    })
                    .add(new restx.security.PermitAll() {
                        public Class<restx.security.PermitAll> annotationType() { return restx.security.PermitAll.class; }
                    })
                    .build();
            }
        },
        });
    }

}
