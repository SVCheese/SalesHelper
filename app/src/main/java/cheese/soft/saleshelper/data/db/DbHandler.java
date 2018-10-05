package cheese.soft.saleshelper.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBHandler {
    private final Context context;
    private DBHelper dbHelper;
    private SQLiteDatabase db;

    public DBHandler(Context context) {
        this.context = context;
        dbHelper = new DBHelper(context);
    }

    //private DbHelper dbHelper;
    /*Context context;

    public DbHandler(Context mContext){
        this.context = mContext;
        dbHelper = new DbHelper(context);
    }*/

    // открыть подключение
    public void open() {
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    // закрыть подключение
    public void close() {
        if (dbHelper != null) dbHelper.close();
    }

    /*// получить все данные из таблицы DB_TABLE
    public Cursor getAllData() {
        return db.query(DB_TABLE, null, null, null, null, null, null);
    }

    // добавить запись в DB_TABLE
    public void addRec(String txt, int img) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TXT, txt);
        cv.put(COLUMN_IMG, img);
        db.insert(DB_TABLE, null, cv);
    }

    // удалить запись из DB_TABLE
    public void delRec(long id) {
        db.delete(DB_TABLE, COLUMN_ID + " = " + id, null);
    }*/
}
