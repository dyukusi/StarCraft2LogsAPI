package starcraft2logsapi.rest;

import com.google.common.collect.ImmutableSet;
import restx.factory.*;
import starcraft2logsapi.rest.ProfileResourceRouter;

@Machine
public class ProfileResourceRouterFactoryMachine extends SingleNameFactoryMachine<starcraft2logsapi.rest.ProfileResourceRouter> {
    public static final Name<starcraft2logsapi.rest.ProfileResourceRouter> NAME = Name.of(starcraft2logsapi.rest.ProfileResourceRouter.class, "ProfileResourceRouter");

    public ProfileResourceRouterFactoryMachine() {
        super(0, new StdMachineEngine<starcraft2logsapi.rest.ProfileResourceRouter>(NAME, 0, BoundlessComponentBox.FACTORY) {
private final Factory.Query<starcraft2logsapi.rest.ProfileResource> resource = Factory.Query.byClass(starcraft2logsapi.rest.ProfileResource.class).mandatory();
private final Factory.Query<restx.entity.EntityRequestBodyReaderRegistry> readerRegistry = Factory.Query.byClass(restx.entity.EntityRequestBodyReaderRegistry.class).mandatory();
private final Factory.Query<restx.entity.EntityResponseWriterRegistry> writerRegistry = Factory.Query.byClass(restx.entity.EntityResponseWriterRegistry.class).mandatory();
private final Factory.Query<restx.converters.MainStringConverter> converter = Factory.Query.byClass(restx.converters.MainStringConverter.class).mandatory();
private final Factory.Query<restx.security.PermissionFactory> pf = Factory.Query.byClass(restx.security.PermissionFactory.class).mandatory();
private final Factory.Query<javax.validation.Validator> validator = Factory.Query.byClass(javax.validation.Validator.class).optional();
private final Factory.Query<restx.security.RestxSecurityManager> securityManager = Factory.Query.byClass(restx.security.RestxSecurityManager.class).mandatory();
private final Factory.Query<restx.endpoint.EndpointParameterMapperRegistry> paramMapperRegistry = Factory.Query.byClass(restx.endpoint.EndpointParameterMapperRegistry.class).mandatory();

            @Override
            public BillOfMaterials getBillOfMaterial() {
                return new BillOfMaterials(ImmutableSet.<Factory.Query<?>>of(
resource,
readerRegistry,
writerRegistry,
converter,
pf,
validator,
securityManager,
paramMapperRegistry
                ));
            }

            @Override
            protected starcraft2logsapi.rest.ProfileResourceRouter doNewComponent(SatisfiedBOM satisfiedBOM) {
                return new ProfileResourceRouter(
satisfiedBOM.getOne(resource).get().getComponent(),
satisfiedBOM.getOne(readerRegistry).get().getComponent(),
satisfiedBOM.getOne(writerRegistry).get().getComponent(),
satisfiedBOM.getOne(converter).get().getComponent(),
satisfiedBOM.getOne(pf).get().getComponent(),
satisfiedBOM.getOneAsComponent(validator),
satisfiedBOM.getOne(securityManager).get().getComponent(),
satisfiedBOM.getOne(paramMapperRegistry).get().getComponent()
                );
            }
        });
    }

}
