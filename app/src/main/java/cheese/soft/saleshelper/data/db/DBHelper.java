package cheese.soft.saleshelper.data.db;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import cheese.soft.saleshelper.R;
import cheese.soft.saleshelper.data.model.Goods;

public class DBHelper extends SQLiteOpenHelper {
    final String LOG_TAG = "myLogs";

    public static final String DATABASE_NAME = "sales_helper.db";
    public static final int DATABASE_VERSION = 1;

    private Context context;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DBTables.DB_TABLE_GROUPS);

        ContentValues cv = new ContentValues();
        String[] groups = context.getResources().getStringArray(R.array.groups);

        for (String item : groups) {
            cv.put(DBTables.GROUP_NAME, item);
            db.insert(DBTables.TABLE_GROUPS, null, cv);
            cv.clear();
        }

        cv.put(DBTables.GROUP_NAME, context.getResources().getString(R.string.start_group1));
        long startGroup1Id = db.insert(DBTables.TABLE_GROUPS, null, cv);
        cv.clear();
        cv.put(DBTables.GROUP_NAME, context.getResources().getString(R.string.start_group2));
        long startGroup2Id = db.insert(DBTables.TABLE_GROUPS, null, cv);
        cv.clear();

        db.execSQL(DBTables.DB_TABLE_GOODS);

        int count = 1;
        List<Goods> goodsList = readGoodsFromFile();

        for (Goods goods : goodsList) {
            cv.put(DBTables.GOODS_INNER_CODE, goods.getInner_code());
            cv.put(DBTables.GOODS_GROUP_ID, count <= 5 ? startGroup1Id : startGroup2Id);
            cv.put(DBTables.GOODS_NAME, goods.getName());
            cv.put(DBTables.GOODS_QUANTITY, goods.getQuantity());
            cv.put(DBTables.GOODS_PURCHASE_PRICE, goods.getPurchase_price());
            cv.put(DBTables.GOODS_PRICE, goods.getPrice());

            db.insert(DBTables.TABLE_GOODS, null, cv);

            cv.clear();
            count++;
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.beginTransaction();

        try {
            db.execSQL(DBTables.DB_TABLE_GROUPS);

            long startGroupId = 0;
            ContentValues cv = new ContentValues();
            String[] groups = context.getResources().getStringArray(R.array.groups);
            String noGroup = context.getResources().getString(R.string.no_group);

            for (String item : groups) {
                cv.put(DBTables.GROUP_NAME, item);
                long id = db.insert(DBTables.TABLE_GROUPS, null, cv);
                if (item.equalsIgnoreCase(noGroup)) startGroupId = id;
                cv.clear();
            }

            db.execSQL("alter table " + DBTables.TABLE_GOODS + " add column " + DBTables.GOODS_GROUP_ID + " integer;");
            db.execSQL("update " + DBTables.TABLE_GOODS + " set " + DBTables.GOODS_GROUP_ID + "=" + startGroupId + ";");

            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    public List<Goods> readGoodsFromFile() {
        Gson gson = new Gson();
        List<Goods> goodsList = new ArrayList<Goods>();
        InputStream inputStream = context.getResources().openRawResource(R.raw.goods);

        try {
            JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
            reader.beginArray();

            while (reader.hasNext()) {
                Goods goods = gson.fromJson(reader, Goods.class);
                goodsList.add(goods);
            }

            reader.endArray();
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            Log.d(LOG_TAG, "Ex: " + ex.getMessage());
        }
        return goodsList;
    }
}
