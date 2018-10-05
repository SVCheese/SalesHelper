package cheese.soft.saleshelper.presentation.warehouse;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.List;

import cheese.soft.saleshelper.R;
import cheese.soft.saleshelper.data.db.DBHelper;
import cheese.soft.saleshelper.data.db.DBTables;
import cheese.soft.saleshelper.data.model.Goods;

public class WarehousePresenter implements WarehouseContract {

    final String LOG_TAG = "myLogs";
    AppCompatActivity activity;
    //DBHandler db;
    //Loader<String> loader;

    Cursor cursor;
    SimpleCursorAdapter scAdapter;

    ListView lvMain;

    private final Context context;
    private DBHelper dbHelper;
    private SQLiteDatabase db;

    public WarehousePresenter(Context context) {
        this.context = context;
        this.activity = (AppCompatActivity) context;

        lvMain = (ListView) activity.findViewById(R.id.a_warehouse_lv);

        openDBConnection();

        cursor = getAllData();
        activity.startManagingCursor(cursor);

        // формируем столбцы сопоставления
        String[] from = new String[] { DBTables.GOODS_INNER_CODE, DBTables.GOODS_NAME, DBTables.GOODS_PURCHASE_PRICE };
        int[] to = new int[] { R.id.textView1, R.id.textView2, R.id.textView3 };

        // создаем адаптер и настраиваем список
        scAdapter = new SimpleCursorAdapter(context, R.layout.item, cursor, from, to);

        lvMain.setAdapter(scAdapter);

        // добавляем контекстное меню к списку
        activity.registerForContextMenu(lvMain);

        //loadGoods();
    }

    @Override
    public void activityDestroy() {
        closeDBConnection();
    }

    public void openDBConnection() {
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public void closeDBConnection() {
        if (dbHelper != null) dbHelper.close();
    }

    // получить все данные из таблицы DB_TABLE
    public Cursor getAllData() {
        return db.query(DBTables.TABLE_GOODS, null, null, null, null, null, null);
    }

    public void loadGoods() {
        Goods goods;
        List<Goods> goodsList = null;

        Cursor cur = db.query(DBTables.TABLE_GOODS, null, null, null, null, null, null);

        if (cur != null && cur.moveToFirst()) {

            int id = cur.getColumnIndex(DBTables.GOODS_ID);
            int inner_code = cur.getColumnIndex(DBTables.GOODS_INNER_CODE);
            int name = cur.getColumnIndex(DBTables.GOODS_NAME);
            int quantity = cur.getColumnIndex(DBTables.GOODS_QUANTITY);
            int purchase_price = cur.getColumnIndex(DBTables.GOODS_PURCHASE_PRICE);
            int price = cur.getColumnIndex(DBTables.GOODS_PRICE);

            goodsList = new ArrayList<>();

            do {
                goods = new Goods();
                goods.setId(cur.getInt(id));
                goods.setInner_code(cur.getInt(inner_code));
                goods.setName(cur.getString(name));
                goods.setQuantity(cur.getInt(quantity));
                goods.setPurchase_price(cur.getDouble(purchase_price));
                goods.setPrice(cur.getDouble(price));

                Log.d(LOG_TAG,goods.getId() + " . " + goods.getInner_code() + " . " + goods.getName());

                goodsList.add(goods);

            } while (cur.moveToNext());

            cur.close();
        }


        //String[] names = { "Иван", "Марья", "Петр", "Антон", "Даша", "Борис", "Костя", "Игорь", "Анна", "Денис", "Андрей" };

        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, R.layout.activity_warehouse_item, names);

        lvMain.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        //ArrayAdapter<Goods> adapter = new ArrayAdapter<Goods>(context, R.layout.activity_warehouse_item, goodsList);
        ArrayAdapter<Goods> adapter = new ArrayAdapter<Goods>(context, R.layout.activity_warehouse_simple_item, goodsList);

        lvMain.setAdapter(adapter);

        /*public void onClick(View arg0) {
            // пишем в лог выделенные элементы
            Log.d(LOG_TAG, "checked: ");
            SparseBooleanArray sbArray = lvMain.getCheckedItemPositions();
            for (int i = 0; i < sbArray.size(); i++) {
                int key = sbArray.keyAt(i);
                if (sbArray.get(key))
                    Log.d(LOG_TAG, names[key]);
            }
        }*/
    }

    public void insertGoods() {
        Goods goods = new Goods(0, 100001, 0, "Апельсин", 5, 100f, 150f);

        ContentValues cv = new ContentValues();
        cv.put(DBTables.GOODS_INNER_CODE, goods.getInner_code());
        cv.put(DBTables.GOODS_NAME, goods.getName());
        cv.put(DBTables.GOODS_QUANTITY, goods.getQuantity());
        cv.put(DBTables.GOODS_PURCHASE_PRICE, goods.getPurchase_price());
        cv.put(DBTables.GOODS_PRICE, goods.getPrice());

        long rowID = db.insert(DBTables.TABLE_GOODS, null, cv);

        Log.d(LOG_TAG, "row inserted, ID = " + rowID);
    }

    @Override
    public void buttonClick(View v) {
        //Intent intent = null;

        switch (v.getId()) {
            case R.id.add_goods_btn:
                //intent = new Intent(activity, AddGoodsActivity.class);

                insertGoods();
                break;

            case R.id.button1:
                //intent = new Intent(activity, AddGoodsActivity.class);

                loadGoods();
                break;
        }

        /*if (intent == null) {
            Toast.makeText(activity, R.string.select_another_action, Toast.LENGTH_SHORT).show();
            return;
        }

        activity.startActivity(intent);*/
    }

    /*@Override
    public void LoadDataFromDB(Context context, Loader<String> loader) {
*/
        /*// открываем подключение к БД
        db = new DBHandler(context);
        db.open();

        // формируем столбцы сопоставления
        String[] from = new String[] { DB.COLUMN_IMG, DB.COLUMN_TXT };
        int[] to = new int[] { R.id.ivImg, R.id.tvText };

        // создаем адаптер и настраиваем список
        scAdapter = new SimpleCursorAdapter(context, R.layout.activity_warehouse_item, null, from, to, 0);

        ListView lvData = (ListView) activity.findViewById(R.id.a_warehouse_lv);
        lvData.setAdapter(scAdapter);*/
    //}


    /*static class MyCursorLoader extends CursorLoader {

        DBHandler db;

        public MyCursorLoader(Context context, DBHandler db) {
            super(context);
            this.db = db;
        }

        @Override
        public Cursor loadInBackground() {
            *//*Cursor cursor = db.getAllData();
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return cursor;*//*
            return null;
        }
    }*/
}
