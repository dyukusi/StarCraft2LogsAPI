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

public class ProfileResourceRouter extends RestxRouter {

    public ProfileResourceRouter(
                    final ProfileResource resource,
                    final EntityRequestBodyReaderRegistry readerRegistry,
                    final EntityResponseWriterRegistry writerRegistry,
                    final MainStringConverter converter,
                    final PermissionFactory pf,
                    final Optional<Validator> validator,
                    final RestxSecurityManager securityManager,
                    final EndpointParameterMapperRegistry paramMapperRegistry) {
        super(
            "default", "ProfileResourceRouter", new RestxRoute[] {
        new StdEntityRoute<Void, java.lang.String>("default#ProfileResource#search",
                readerRegistry.<Void>build(Void.class, Optional.<String>absent()),
                writerRegistry.<java.lang.String>build(java.lang.String.class, Optional.<String>absent()),
                Endpoint.of("GET", "/profiles/{region}/{name}/{race}"),
                HttpStatus.OK, RestxLogLevel.DEFAULT, pf,
                paramMapperRegistry, new ParamDef[]{
                    ParamDef.of(new TypeReference<java.lang.String>(){}, "region"),
                    ParamDef.of(new TypeReference<java.lang.String>(){}, "name"),
                    ParamDef.of(new TypeReference<java.lang.String>(){}, "race"),
                    ParamDef.of(new TypeReference<java.lang.Integer>(){}, "rating")
                }) {
            @Override
            protected Optional<java.lang.String> doRoute(RestxRequest request, RestxResponse response, RestxRequestMatch match, Void body) throws IOException {
                securityManager.check(request, match, open());
                try {
                    return Optional.of(resource.search(
                        /* [PATH] region */ checkValid(validator, checkNotNull(mapQueryObjectFromRequest(java.lang.String.class, "region", request, match, EndpointParameterKind.PATH), "PATH param <region> is required")),
                        /* [PATH] name */ checkValid(validator, checkNotNull(mapQueryObjectFromRequest(java.lang.String.class, "name", request, match, EndpointParameterKind.PATH), "PATH param <name> is required")),
                        /* [PATH] race */ checkValid(validator, checkNotNull(mapQueryObjectFromRequest(java.lang.String.class, "race", request, match, EndpointParameterKind.PATH), "PATH param <race> is required")),
                        /* [QUERY] rating */ java.util.Optional.ofNullable(checkValid(validator, mapQueryObjectFromRequest(java.lang.Integer.class, "rating", request, match, EndpointParameterKind.QUERY)))
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

                OperationParameterDescription name = new OperationParameterDescription();
                name.name = "name";
                name.paramType = OperationParameterDescription.ParamType.path;
                name.dataType = "string";
                name.schemaKey = "";
                name.required = true;
                operation.parameters.add(name);

                OperationParameterDescription race = new OperationParameterDescription();
                race.name = "race";
                race.paramType = OperationParameterDescription.ParamType.path;
                race.dataType = "string";
                race.schemaKey = "";
                race.required = true;
                operation.parameters.add(race);

                OperationParameterDescription rating = new OperationParameterDescription();
                rating.name = "rating";
                rating.paramType = OperationParameterDescription.ParamType.query;
                rating.dataType = "int";
                rating.schemaKey = "";
                rating.required = false;
                operation.parameters.add(rating);


                operation.responseClass = "string";
                operation.inEntitySchemaKey = "";
                operation.inEntityType = Void.class;
                operation.outEntitySchemaKey = "";
                operation.outEntityType = java.lang.String.class;
                operation.sourceLocation = "starcraft2logsapi.rest.ProfileResource#search(java.lang.String,java.lang.String,java.lang.String,java.util.Optional<java.lang.Integer>)";
                operation.annotations = ImmutableList.<java.lang.annotation.Annotation>builder()
                    .add(new restx.annotations.GET() {
                        public Class<restx.annotations.GET> annotationType() { return restx.annotations.GET.class; }
                        public java.lang.String value() { return "/profiles/{region}/{name}/{race}"; }
                    })
                    .add(new restx.security.PermitAll() {
                        public Class<restx.security.PermitAll> annotationType() { return restx.security.PermitAll.class; }
                    })
                    .build();
            }
        },
        new StdEntityRoute<Void, java.lang.String>("default#ProfileResource#update",
                readerRegistry.<Void>build(Void.class, Optional.<String>absent()),
                writerRegistry.<java.lang.String>build(java.lang.String.class, Optional.<String>absent()),
                Endpoint.of("GET", "/profiles/update"),
                HttpStatus.OK, RestxLogLevel.DEFAULT, pf,
                paramMapperRegistry, new ParamDef[]{
                    ParamDef.of(new TypeReference<java.lang.String>(){}, "region"),
                    ParamDef.of(int.class, "profileId"),
                    ParamDef.of(new TypeReference<java.lang.String>(){}, "race")
                }) {
            @Override
            protected Optional<java.lang.String> doRoute(RestxRequest request, RestxResponse response, RestxRequestMatch match, Void body) throws IOException {
                securityManager.check(request, match, open());
                try {
                    return Optional.of(resource.update(
                        /* [QUERY] region */ checkValid(validator, checkNotNull(mapQueryObjectFromRequest(java.lang.String.class, "region", request, match, EndpointParameterKind.QUERY), "QUERY param <region> is required")),
                        /* [QUERY] profileId */ checkValid(validator, checkNotNull(mapQueryObjectFromRequest(int.class, "profileId", request, match, EndpointParameterKind.QUERY), "QUERY param <profileId> is required")),
                        /* [QUERY] race */ checkValid(validator, checkNotNull(mapQueryObjectFromRequest(java.lang.String.class, "race", request, match, EndpointParameterKind.QUERY), "QUERY param <race> is required"))
                    ));
                } catch(RuntimeException e) { throw e; }
                  catch(Exception e) { throw new WrappedCheckedException(e); }
            }

            @Override
            protected void describeOperation(OperationDescription operation) {
                super.describeOperation(operation);
                OperationParameterDescription region = new OperationParameterDescription();
                region.name = "region";
                region.paramType = OperationParameterDescription.ParamType.query;
                region.dataType = "string";
                region.schemaKey = "";
                region.required = true;
                operation.parameters.add(region);

                OperationParameterDescription profileId = new OperationParameterDescription();
                profileId.name = "profileId";
                profileId.paramType = OperationParameterDescription.ParamType.query;
                profileId.dataType = "int";
                profileId.schemaKey = "int";
                profileId.required = true;
                operation.parameters.add(profileId);

                OperationParameterDescription race = new OperationParameterDescription();
                race.name = "race";
                race.paramType = OperationParameterDescription.ParamType.query;
                race.dataType = "string";
                race.schemaKey = "";
                race.required = true;
                operation.parameters.add(race);


                operation.responseClass = "string";
                operation.inEntitySchemaKey = "";
                operation.inEntityType = Void.class;
                operation.outEntitySchemaKey = "";
                operation.outEntityType = java.lang.String.class;
                operation.sourceLocation = "starcraft2logsapi.rest.ProfileResource#update(java.lang.String,int,java.lang.String)";
                operation.annotations = ImmutableList.<java.lang.annotation.Annotation>builder()
                    .add(new restx.annotations.GET() {
                        public Class<restx.annotations.GET> annotationType() { return restx.annotations.GET.class; }
                        public java.lang.String value() { return "/profiles/update"; }
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
