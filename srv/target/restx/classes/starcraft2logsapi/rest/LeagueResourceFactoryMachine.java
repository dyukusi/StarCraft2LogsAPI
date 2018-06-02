package starcraft2logsapi.rest;

import com.google.common.collect.ImmutableSet;
import restx.factory.*;
import starcraft2logsapi.rest.LeagueResource;

@Machine
public class LeagueResourceFactoryMachine extends SingleNameFactoryMachine<starcraft2logsapi.rest.LeagueResource> {
    public static final Name<starcraft2logsapi.rest.LeagueResource> NAME = Name.of(starcraft2logsapi.rest.LeagueResource.class, "LeagueResource");

    public LeagueResourceFactoryMachine() {
        super(0, new StdMachineEngine<starcraft2logsapi.rest.LeagueResource>(NAME, 0, BoundlessComponentBox.FACTORY) {


            @Override
            public BillOfMaterials getBillOfMaterial() {
                return new BillOfMaterials(ImmutableSet.<Factory.Query<?>>of(

                ));
            }

            @Override
            protected starcraft2logsapi.rest.LeagueResource doNewComponent(SatisfiedBOM satisfiedBOM) {
                return new LeagueResource(

                );
            }
        });
    }

}
