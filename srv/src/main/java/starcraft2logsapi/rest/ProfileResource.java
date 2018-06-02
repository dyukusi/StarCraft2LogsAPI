package starcraft2logsapi.rest;


import github.dyukusi.API;
import restx.annotations.GET;
import restx.annotations.RestxResource;
import restx.factory.Component;
import restx.security.PermitAll;
import starcraft2logsapi.DB;
import starcraft2logsapi.Race;
import starcraft2logsapi.Region;
import starcraft2logsapi.Util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Component @RestxResource
public class ProfileResource {
    @GET("/profiles/{region}/{name}/{race}")
    @PermitAll
    public String search(String region, String name, String race, Optional<Integer> rating) throws SQLException, IOException {
        DB db = Util.createSC2LogsDatabaseObject();
        Connection con = db.connect();
        StringBuilder sql = new StringBuilder();

        if (rating.isPresent()) {
            sql.append("SELECT DISTINCT * FROM profile_log WHERE region_id = ? AND name = ? AND race_id IN (?, ?) ORDER BY ABS(rating - ?) ASC, last_played_at DESC, created_at DESC LIMIT 10;");
        } else {
            sql.append("SELECT DISTINCT * FROM profile_log WHERE region_id = ? AND name = ? AND race_id IN (?, ?) ORDER BY last_played_at DESC, created_at DESC LIMIT 10;");
        }

        PreparedStatement ps = con.prepareStatement(sql.toString());
        ps.setInt(1, Region.valueOf(region).getId());
        ps.setString(2, name);
        ps.setInt(3, Race.valueOf(race).getId());
        ps.setInt(4, Race.Null.getId());

        if (rating.isPresent()) {
            ps.setInt(5, rating.get());
        }

        ResultSet result = ps.executeQuery();
        String ret = Util.createResponseJSONString(result);

        ps.close();
        con.close();

        return ret;
    }

    @GET("/profiles/update")
    @PermitAll
    public String update(String region, int profileId, String race) throws FileNotFoundException {
        int regionId = Region.valueOf(region).getId();
        int raceId = Race.valueOf(race).getId();

        try {
            DB db = Util.createSC2LogsDatabaseObject();
            Connection con = db.connect();

            PreparedStatement ps = con.prepareStatement("SELECT * FROM profile_log WHERE region_id = ? AND id = ? AND race_id = ? ORDER BY created_at DESC");
            ps.setInt(1, regionId);
            ps.setInt(2, profileId);
            ps.setInt(3, raceId);

            ResultSet result = ps.executeQuery();
            if (!result.next()) {
                return "profile not found";
            }

            int ladderId = result.getInt("ladder_id");
            ps.close();
            con.close();

            // update
            API.updateProfile(regionId, ladderId, profileId);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return String.format("update process finished. regionId: %d, profileId: %d, raceId %d",
                regionId,
                profileId,
                raceId
        );
    }
}
