package starcraft2logsapi.rest;


import restx.annotations.GET;
import restx.annotations.RestxResource;
import restx.factory.Component;
import restx.security.PermitAll;
import starcraft2logsapi.DB;
import starcraft2logsapi.Race;
import starcraft2logsapi.Region;
import starcraft2logsapi.Util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Component @RestxResource
public class LeagueResource {
    @GET("/leagues/{region}")
    @PermitAll
    public String leagues(String region) throws SQLException, IOException {
        DB db = Util.createSC2LogsDatabaseObject();
        Connection con = db.connect();

        int regionId = Region.valueOf(region).getId();

        PreparedStatement selectCurrentSeasonPS = con.prepareStatement(
                "SELECT * FROM season WHERE region_id = ? ORDER BY id DESC, number DESC"
        );
        selectCurrentSeasonPS.setInt(1, regionId);

        ResultSet seasonResult = selectCurrentSeasonPS.executeQuery();
        if (!seasonResult.next()) return "season not found";
        int currentSeasonId = seasonResult.getInt("id");

        PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM league WHERE region_id = ? AND season_id = ?;"
        );
        ps.setInt(1, regionId);
        ps.setInt(2, currentSeasonId);

        ResultSet result = ps.executeQuery();
        String ret = Util.createResponseJSONString(result);

        ps.close();
        con.close();

        return ret;
    }
}
