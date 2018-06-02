package starcraft2logsapi.rest;

import com.google.common.collect.ImmutableSet;
import restx.factory.*;
import starcraft2logsapi.rest.HelloResource;

@Machine
public class HelloResourceFactoryMachine extends SingleNameFactoryMachine<starcraft2logsapi.rest.HelloResource> {
    public static final Name<starcraft2logsapi.rest.HelloResource> NAME = Name.of(starcraft2logsapi.rest.HelloResource.class, "HelloResource");

    public HelloResourceFactoryMachine() {
        super(0, new StdMachineEngine<starcraft2logsapi.rest.HelloResource>(NAME, 0, BoundlessComponentBox.FACTORY) {


            @Override
            public BillOfMaterials getBillOfMaterial() {
                return new BillOfMaterials(ImmutableSet.<Factory.Query<?>>of(

                ));
            }

            @Override
            protected starcraft2logsapi.rest.HelloResource doNewComponent(SatisfiedBOM satisfiedBOM) {
                return new HelloResource(

                );
            }
        });
    }

}
