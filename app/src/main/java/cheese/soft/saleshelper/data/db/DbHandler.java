package cheese.soft.saleshelper.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBHandler implements BDHandlerContract {
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

    public Cursor getAllTableDataCursor(String tableName) {
        return db.query(tableName, null, null, null, null, null, null);
    }

    public long insertRecord(String tableName, ContentValues cv) {
        return db.insert(tableName, null, cv);
    }

    public void deleteRecordById(long id, String tableName) {
        db.delete(tableName,  "_id = " + id, null);
    }
}
