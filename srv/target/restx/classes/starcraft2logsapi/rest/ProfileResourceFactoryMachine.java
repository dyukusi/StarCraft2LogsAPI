package starcraft2logsapi.rest;

import com.google.common.collect.ImmutableSet;
import restx.factory.*;
import starcraft2logsapi.rest.ProfileResource;

@Machine
public class ProfileResourceFactoryMachine extends SingleNameFactoryMachine<starcraft2logsapi.rest.ProfileResource> {
    public static final Name<starcraft2logsapi.rest.ProfileResource> NAME = Name.of(starcraft2logsapi.rest.ProfileResource.class, "ProfileResource");

    public ProfileResourceFactoryMachine() {
        super(0, new StdMachineEngine<starcraft2logsapi.rest.ProfileResource>(NAME, 0, BoundlessComponentBox.FACTORY) {


            @Override
            public BillOfMaterials getBillOfMaterial() {
                return new BillOfMaterials(ImmutableSet.<Factory.Query<?>>of(

                ));
            }

            @Override
            protected starcraft2logsapi.rest.ProfileResource doNewComponent(SatisfiedBOM satisfiedBOM) {
                return new ProfileResource(

                );
            }
        });
    }

}
