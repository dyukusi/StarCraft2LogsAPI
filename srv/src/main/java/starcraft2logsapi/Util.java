package starcraft2logsapi;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class Util {
    static public DB createSC2LogsDatabaseObject() throws FileNotFoundException {
        Map y = (Map) new Yaml().load(new FileInputStream(new File("/etc/sc2logs.yaml")));
        Map dbSetting = (Map) y.get("database");
        return new DB(
                (String) dbSetting.get("hostname"),
                (String) dbSetting.get("database"),
                (String) dbSetting.get("user"),
                (String) dbSetting.get("password")
        );
    }

    static public String createResponseJSONString(ResultSet result) throws SQLException, IOException {
        ResultSetMetaData meta = result.getMetaData();
        ArrayList<Column> columns = new ArrayList<>();
        for (int i = 1; i <= meta.getColumnCount(); i++) {
            columns.add(new Column(meta.getColumnName(i), meta.getColumnTypeName(i)));
        }

        JsonFactory f = new JsonFactory();
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        JsonGenerator g = f.createGenerator(b, JsonEncoding.UTF8);

        g.writeStartArray();

        while (result.next()) {
            g.writeStartObject();
            for (Column column : columns) {
                g.writeFieldName(column.getColumnName());

                switch (column.getTypeName()) {
                    case "INT":
                        g.writeNumber(result.getInt(column.getColumnName()));
                        break;
                    case "VARCHAR":
                    case "TEXT":
                        String str = result.getString(column.getColumnName());

                        if (str == null) {
                            g.writeNull();
                        } else {
                            g.writeString(result.getString(column.getColumnName()));
                        }
                        break;
                    case "DATETIME":
                        g.writeNumber(result.getTimestamp(column.getColumnName()).getTime() / 1000);
                        break;
                    default:
                        break;
                }
            }
            g.writeEndObject();
        }

        g.writeEndArray();
        g.flush();

        return b.toString();
    }

}
