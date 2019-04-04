package cheese.soft.saleshelper.data.db;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.content.CursorLoader;
import android.util.Log;

public class MyCursorLoader extends CursorLoader {

    private BDHandlerContract db;
    private final String LOG_TAG = "myLogs";
    private String tableName;
    private long groupId;

    public MyCursorLoader(Context context, BDHandlerContract db, String tableName) {
        super(context);
        this.db = db;
        this.tableName = tableName;
        Log.d(LOG_TAG, "MyCursorLoader(" + db.toString().replace("cheese.soft.saleshelper.data.db.", "") + ", " + tableName + ");");
    }

    public MyCursorLoader(Context context, BDHandlerContract db, String tableName, long groupId) {
        this(context, db, tableName);
        this.groupId = groupId;
        Log.d(LOG_TAG, "MyCursorLoader(" + db.toString().replace("cheese.soft.saleshelper.data.db.", "") + ", " + tableName + ", " + groupId + ");");
    }

    @Override
    public Cursor loadInBackground() {
        Log.d(LOG_TAG, "loadInBackground(): " + tableName + ", " + groupId);

        Cursor cursor = groupId == 0 ? db.getAllTableDataCursor(tableName) : db.getGoodsCursor(tableName, groupId);

        Log.d(LOG_TAG, "loadInBackground(): " + cursor.toString().replace("android.database.sqlite.", ""));
        return cursor;
    }
}