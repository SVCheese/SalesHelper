package cheese.soft.saleshelper.data.db;

import android.content.ContentValues;
import android.database.Cursor;

public interface BDHandlerContract {
    void openDBConnection();
    void closeDBConnection();
    Cursor getAllTableDataCursor(String tableName);
    Cursor getGoodsCursor(String tableName, long id);
    long insertRecord(String tableName, ContentValues cv);
    int updateRecordById(String tableName, ContentValues cv, String id);
    int deleteRecordById(String tableName, long id);
}
