package cheese.soft.saleshelper.data.db;

import android.content.ContentValues;
import android.database.Cursor;

public interface BDHandlerContract {
    void openDBConnection();
    void closeDBConnection();
    Cursor getAllTableDataCursor(String tableName);
    void deleteRecordById(long id, String tableName);
    long insertRecord(String tableName, ContentValues cv);
}
