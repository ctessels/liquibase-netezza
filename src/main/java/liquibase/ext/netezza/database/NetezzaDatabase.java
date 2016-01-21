package liquibase.ext.netezza.database;

import liquibase.database.AbstractJdbcDatabase;
import liquibase.database.DatabaseConnection;
import liquibase.exception.DatabaseException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class NetezzaDatabase extends AbstractJdbcDatabase {
	public static final String PRODUCT_NAME = "Netezza NPS";
	private Set<String> reservedWords = new HashSet<String>();
	private Set<String> systemTablesAndViews = new HashSet<String>();

	public String getShortName() {
		return PRODUCT_NAME;
	}

	public NetezzaDatabase() {
		super.setCurrentDateTimeFunction("CURRENT_TIMESTAMP");

		reservedWords.addAll(Arrays.asList("ABORT", "DEC", "LEADING", "RESET", "ADMIN", "DECIMAL", "LEFT", "REUSE", "AGGREGATE",
				"DECODE", "LIKE", "RIGHT", "ALIGN", "DEFAULT", "LIMIT", "ROWS", "ALL", "DEFERRABLE", "LISTEN", "ROWSETLIMIT",
				"ALLOCATE", "DESC", "LOAD", "RULE", "ANALYSE", "DISTINCT", "LOCAL", "SEARCH", "ANALYZE", "DISTRIBUTE", "LOCK",
				"SELECT", "AND", "DO", "MATERIALIZED", "SEQUENCE", "ANY", "ELSE", "MINUS", "SESSION_USER", "AS", "END", "MOVE",
				"SETOF", "ASC", "EXCEPT", "NATURAL", "SHOW", "BETWEEN", "EXCLUDE", "NCHAR", "SOME", "BINARY", "EXISTS", "NEW",
				"SUBSTRING", "BIT", "EXPLAIN", "NOT", "SYSTEM", "BOTH", "EXPRESS", "NOTNULL", "TABLE", "CASE", "EXTEND", "NULL",
				"THEN", "CAST", "EXTERNAL", "NULLIF", "TIES", "CHAR", "EXTRACT", "NULLS", "TIME", "CHARACTER", "FALSE", "NUMERIC",
				"TIMESTAMP", "CHECK", "FIRST", "NVL", "TO", "CLUSTER", "FLOAT", "NVL2", "TRAILING", "COALESCE", "FOLLOWING",
				"OFF", "TRANSACTION", "COLLATE", "FOR", "OFFSET", "TRIGGER", "COLLATION", "FOREIGN", "OLD", "TRIM", "COLUMN",
				"FROM", "ON", "TRUE", "CONSTRAINT", "FULL", "ONLINE", "UNBOUNDED", "COPY", "FUNCTION", "ONLY", "UNION", "CROSS",
				"GENSTATS", "OR", "UNIQUE", "CURRENT", "GLOBAL", "ORDER", "USER", "CURRENT_CATALOG", "GROUP", "OTHERS", "USING",
				"CURRENT_DATE", "HAVING", "OUT", "VACUUM", "CURRENT_DB", "IDENTIFIER_CASE", "OUTER", "VARCHAR", "CURRENT_SCHEMA",
				"ILIKE", "OVER", "VERBOSE", "CURRENT_SID", "IN", "OVERLAPS", "VERSION", "CURRENT_TIME", "INDEX", "PARTITION",
				"VIEW", "CURRENT_TIMESTAMP", "INITIALLY", "POSITION", "WHEN", "CURRENT_USER", "INNER", "PRECEDING", "WHERE",
				"CURRENT_USERID", "INOUT", "PRECISION", "WITH", "CURRENT_USEROID", "INTERSECT", "PRESERVE", "WRITE", "DEALLOCATE",
				"INTERVAL", "PRIMARY", "RESET", "INTO", "REUSE", "CTID", "OID", "XMIN", "CMIN", "XMAX", "CMAX", "TABLEOID",
				"ROWID", "DATASLICEID", "CREATEXID", "DELETEXID"));

		systemTablesAndViews.addAll(Arrays.asList("_v_procedure", "_v_relation_column", "_v_relation_column_def", "_v_sequence",
				"_v_session", "_v_table", "_v_table_dist_map", "_v_table_index", "_v_user", "_v_usergroups", "_v_view",
				"_v_sys_group_priv", "_v_sys_index", "_v_sys_priv", "_v_sys_table", "_v_sys_user_priv", "_v_sys_view"));
	}

	public int getPriority() {
		return PRIORITY_DEFAULT;
	}

	@Override
	protected String getDefaultDatabaseProductName() {
		return PRODUCT_NAME;
	}

	public Integer getDefaultPort() {
		return 5480;
	}

	public boolean supportsInitiallyDeferrableColumns() {
		return true;
	}

	public boolean isCorrectDatabaseImplementation(DatabaseConnection conn) throws DatabaseException {
		String databaseProductName = conn.getDatabaseProductName();
		return PRODUCT_NAME.equalsIgnoreCase(databaseProductName);
	}

	public String getDefaultDriver(String url) {
		if (url.startsWith("jdbc:netezza:")) {
			return "org.netezza.Driver";
		}
		return null;
	}

	public boolean supportsTablespaces() {
		return false;
	}

	@Override
	public Set<String> getSystemViews() {
		return systemTablesAndViews;
	}

	@Override
	public boolean supportsSequences() {
		return true;
	}

	@Override
	public boolean supportsRestrictForeignKeys() {
		return false;
	}

	@Override
	public boolean supportsDropTableCascadeConstraints() {
		return false;
	}

	@Override
	public boolean supportsAutoIncrement() {
		return false;
	}

	@Override
	public boolean isCaseSensitive() {
		return false;
	}

	@Override
	public boolean supportsSchemas() {
		return false;
	}

	@Override
	public String getDefaultSchemaName() {
		return "ADMIN";
	}
}