package cheese.soft.saleshelper.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBHandler implements BDHandlerContract {
    private final String LOG_TAG = "myLogs";
    private final Context context;

    private DBHelper dbHelper;
    private SQLiteDatabase db;

    public DBHandler(Context context) {
        this.context = context;
        dbHelper = new DBHelper(context);
    }

    public void openDBConnection() {
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public void closeDBConnection() {
        if (dbHelper != null) dbHelper.close();
    }

    @Override
    public Cursor getAllTableDataCursor(String tableName) {
        Log.d(LOG_TAG, "getAllTableDataCursor(" + tableName + ");");
        return db.query(tableName, null, null, null, null, null, null);
    }

    @Override
    public Cursor getGoodsCursor(String tableName, long id) {
        Log.d(LOG_TAG, "getGoodsCursor(" + tableName + ", " + id + ");");
        return db.query(tableName, null, DBTables.GOODS_GROUP_ID + " = ?", new String[] { Long.toString(id) }, null, null, null);
    }

    @Override
    public long insertRecord(String tableName, ContentValues cv) {
        return db.insert(tableName, null, cv);
    }

    @Override
    public int updateRecordById(String tableName, ContentValues cv, String id) {
        return db.update(tableName, cv, "_id = ?", new String[] { id });
    }

    @Override
    public int deleteRecordById(String tableName, long id) {
        return db.delete(tableName,  "_id = " + id, null);
    }
}
