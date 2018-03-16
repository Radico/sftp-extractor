import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import com.amazon.redshift.jdbc42.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class RedshiftClient extends AbstractSQLClient {

    private final Logger logger = LoggerFactory.getLogger(SQLServerClient.class);

    private final SQLParams params;

    public RedshiftClient(SQLParams params) {
        this.params = params;
    }

    @Override
    public List<Map<String, Object>> query(String queryText) {
        logger.info("Querying for: " + queryText);
        try {
            DbUtils.loadDriver("com.amazon.redshift.jdbc42.Driver");
            DataSource ds = new DataSource();
            ds.setUserID(params.getUser());
            ds.setPassword(params.getPassword());
            ds.setURL(params.getRedshiftURL());
            QueryRunner queryRunner = new QueryRunner(ds);
            MapListHandler handler = new MapListHandler();
            return queryRunner.query(queryText, handler);
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}