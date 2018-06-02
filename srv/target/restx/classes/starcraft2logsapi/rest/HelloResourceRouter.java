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

public class HelloResourceRouter extends RestxRouter {

    public HelloResourceRouter(
                    final HelloResource resource,
                    final EntityRequestBodyReaderRegistry readerRegistry,
                    final EntityResponseWriterRegistry writerRegistry,
                    final MainStringConverter converter,
                    final PermissionFactory pf,
                    final Optional<Validator> validator,
                    final RestxSecurityManager securityManager,
                    final EndpointParameterMapperRegistry paramMapperRegistry) {
        super(
            "default", "HelloResourceRouter", new RestxRoute[] {
        new StdEntityRoute<Void, starcraft2logsapi.domain.Message>("default#HelloResource#sayHello",
                readerRegistry.<Void>build(Void.class, Optional.<String>absent()),
                writerRegistry.<starcraft2logsapi.domain.Message>build(starcraft2logsapi.domain.Message.class, Optional.<String>absent()),
                Endpoint.of("GET", "/message"),
                HttpStatus.OK, RestxLogLevel.DEFAULT, pf,
                paramMapperRegistry, new ParamDef[]{

                }) {
            @Override
            protected Optional<starcraft2logsapi.domain.Message> doRoute(RestxRequest request, RestxResponse response, RestxRequestMatch match, Void body) throws IOException {
                securityManager.check(request, match, hasRole("hello"));
                try {
                    return Optional.of(resource.sayHello(
                        
                    ));
                } catch(RuntimeException e) { throw e; }
                  catch(Exception e) { throw new WrappedCheckedException(e); }
            }

            @Override
            protected void describeOperation(OperationDescription operation) {
                super.describeOperation(operation);


                operation.responseClass = "Message";
                operation.inEntitySchemaKey = "";
                operation.inEntityType = Void.class;
                operation.outEntitySchemaKey = "starcraft2logsapi.domain.Message";
                operation.outEntityType = starcraft2logsapi.domain.Message.class;
                operation.sourceLocation = "starcraft2logsapi.rest.HelloResource#sayHello()";
                operation.annotations = ImmutableList.<java.lang.annotation.Annotation>builder()
                    .add(new restx.annotations.GET() {
                        public Class<restx.annotations.GET> annotationType() { return restx.annotations.GET.class; }
                        public java.lang.String value() { return "/message"; }
                    })
                    .add(new restx.security.RolesAllowed() {
                        public Class<restx.security.RolesAllowed> annotationType() { return restx.security.RolesAllowed.class; }
                        public java.lang.String[] value() { return new java.lang.String[]{ "hello" }; }
                    })
                    .build();
            }
        },
        new StdEntityRoute<Void, starcraft2logsapi.domain.Message>("default#HelloResource#helloPublic",
                readerRegistry.<Void>build(Void.class, Optional.<String>absent()),
                writerRegistry.<starcraft2logsapi.domain.Message>build(starcraft2logsapi.domain.Message.class, Optional.<String>absent()),
                Endpoint.of("GET", "/hello"),
                HttpStatus.OK, RestxLogLevel.DEFAULT, pf,
                paramMapperRegistry, new ParamDef[]{
                    ParamDef.of(new TypeReference<java.lang.String>(){}, "who")
                }) {
            @Override
            protected Optional<starcraft2logsapi.domain.Message> doRoute(RestxRequest request, RestxResponse response, RestxRequestMatch match, Void body) throws IOException {
                securityManager.check(request, match, open());
                try {
                    return Optional.of(resource.helloPublic(
                        /* [QUERY] who */ checkValid(validator, checkNotNull(mapQueryObjectFromRequest(java.lang.String.class, "who", request, match, EndpointParameterKind.QUERY), "QUERY param <who> is required"))
                    ));
                } catch(RuntimeException e) { throw e; }
                  catch(Exception e) { throw new WrappedCheckedException(e); }
            }

            @Override
            protected void describeOperation(OperationDescription operation) {
                super.describeOperation(operation);
                OperationParameterDescription who = new OperationParameterDescription();
                who.name = "who";
                who.paramType = OperationParameterDescription.ParamType.query;
                who.dataType = "string";
                who.schemaKey = "";
                who.required = true;
                operation.parameters.add(who);


                operation.responseClass = "Message";
                operation.inEntitySchemaKey = "";
                operation.inEntityType = Void.class;
                operation.outEntitySchemaKey = "starcraft2logsapi.domain.Message";
                operation.outEntityType = starcraft2logsapi.domain.Message.class;
                operation.sourceLocation = "starcraft2logsapi.rest.HelloResource#helloPublic(java.lang.String)";
                operation.annotations = ImmutableList.<java.lang.annotation.Annotation>builder()
                    .add(new restx.annotations.GET() {
                        public Class<restx.annotations.GET> annotationType() { return restx.annotations.GET.class; }
                        public java.lang.String value() { return "/hello"; }
                    })
                    .add(new restx.security.PermitAll() {
                        public Class<restx.security.PermitAll> annotationType() { return restx.security.PermitAll.class; }
                    })
                    .build();
            }
        },
        new StdEntityRoute<starcraft2logsapi.rest.HelloResource.MyPOJO, starcraft2logsapi.rest.HelloResource.MyPOJO>("default#HelloResource#helloPojo",
                readerRegistry.<starcraft2logsapi.rest.HelloResource.MyPOJO>build(starcraft2logsapi.rest.HelloResource.MyPOJO.class, Optional.<String>absent()),
                writerRegistry.<starcraft2logsapi.rest.HelloResource.MyPOJO>build(starcraft2logsapi.rest.HelloResource.MyPOJO.class, Optional.<String>absent()),
                Endpoint.of("POST", "/mypojo"),
                HttpStatus.OK, RestxLogLevel.DEFAULT, pf,
                paramMapperRegistry, new ParamDef[]{

                }) {
            @Override
            protected Optional<starcraft2logsapi.rest.HelloResource.MyPOJO> doRoute(RestxRequest request, RestxResponse response, RestxRequestMatch match, starcraft2logsapi.rest.HelloResource.MyPOJO body) throws IOException {
                securityManager.check(request, match, open());
                try {
                    return Optional.of(resource.helloPojo(
                        /* [BODY] pojo */ checkValid(validator, checkNotNull(body, "body param <pojo> is required"))
                    ));
                } catch(RuntimeException e) { throw e; }
                  catch(Exception e) { throw new WrappedCheckedException(e); }
            }

            @Override
            protected void describeOperation(OperationDescription operation) {
                super.describeOperation(operation);
                OperationParameterDescription pojo = new OperationParameterDescription();
                pojo.name = "pojo";
                pojo.paramType = OperationParameterDescription.ParamType.body;
                pojo.dataType = "MyPOJO";
                pojo.schemaKey = "starcraft2logsapi.rest.HelloResource.MyPOJO";
                pojo.required = true;
                operation.parameters.add(pojo);


                operation.responseClass = "MyPOJO";
                operation.inEntitySchemaKey = "starcraft2logsapi.rest.HelloResource.MyPOJO";
                operation.inEntityType = starcraft2logsapi.rest.HelloResource.MyPOJO.class;
                operation.outEntitySchemaKey = "starcraft2logsapi.rest.HelloResource.MyPOJO";
                operation.outEntityType = starcraft2logsapi.rest.HelloResource.MyPOJO.class;
                operation.sourceLocation = "starcraft2logsapi.rest.HelloResource#helloPojo(starcraft2logsapi.rest.HelloResource.MyPOJO)";
                operation.annotations = ImmutableList.<java.lang.annotation.Annotation>builder()
                    .add(new restx.annotations.POST() {
                        public Class<restx.annotations.POST> annotationType() { return restx.annotations.POST.class; }
                        public java.lang.String value() { return "/mypojo"; }
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
