package cheese.soft.saleshelper.presentation.warehouse;

import android.app.DialogFragment;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cheese.soft.saleshelper.R;
import cheese.soft.saleshelper.TwoButtonDialog;
import cheese.soft.saleshelper.data.db.BDHandlerContract;
import cheese.soft.saleshelper.data.db.DBHandler;
import cheese.soft.saleshelper.data.db.DBTables;
import cheese.soft.saleshelper.data.db.MyCursorLoader;
import cheese.soft.saleshelper.data.model.Goods;

public class WarehousePresenter implements WarehouseContract {

    private final String LOG_TAG = "myLogs";
    private final String DIALOG_DELETE_GOODS = "DeleteGoods";
    private final String DIALOG_EDIT_GOODS = "EditGoods";

    private final int GROUPS_LOADER = 1;
    private final int GOODS_LOADER = 2;

    private static long DB_RECORD_ID = 0;

    private final Context context;
    private WarehouseActivity activity;

    private BDHandlerContract db;
    private SimpleCursorAdapter lvAdapter;
    private SimpleCursorAdapter spnAdapter;

    private ListView lv;

    private Map<String, DialogFragment> dialogs;

    public WarehousePresenter(Context context) {
        this.context = context;
        this.activity = (WarehouseActivity) context;

        db = new DBHandler(context);
        db.openDBConnection();

        dialogs = new HashMap<>();

        String[] from = new String[]{DBTables.GOODS_NAME, DBTables.GOODS_QUANTITY, DBTables.GOODS_PURCHASE_PRICE, DBTables.GOODS_PRICE, DBTables.GOODS_INNER_CODE};
        int[] to = new int[]{R.id.a_w_item_tv_goods_name, R.id.a_w_item_tv_goods_qty, R.id.a_w_item_tv_goods_purchase_price, R.id.a_w_item_tv_goods_price, R.id.a_w_item_tv_goods_inner_code};

        Spinner spn = activity.findViewById(R.id.a_warehouse_spn);
        spnAdapter = new SimpleCursorAdapter(context, android.R.layout.simple_spinner_item, null,
                new String[]{DBTables.GROUP_NAME}, new int[]{android.R.id.text1}, GROUPS_LOADER);
        spn.setAdapter(spnAdapter);
        //spn.setPrompt("Title");

        lv = activity.findViewById(R.id.a_warehouse_lv);
        lvAdapter = new SimpleCursorAdapter(context, R.layout.activity_warehouse_item, null, from, to, GOODS_LOADER);
        lv.setAdapter(lvAdapter);
    }

    @Override
    public void initLoaders() {
        Log.d(LOG_TAG, "initLoaders()");
        activity.getSupportLoaderManager().initLoader(GROUPS_LOADER, null, activity);
        activity.getSupportLoaderManager().initLoader(GOODS_LOADER, null, activity);
    }

    @Override
    public void activityDestroy() {
        db.closeDBConnection();
    }

    public void loadGoods() {
        Goods goods;
        List<Goods> goodsList = null;

        Cursor cur = db.getAllTableDataCursor(DBTables.TABLE_GOODS);

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

                Log.d(LOG_TAG, goods.getId() + " . " + goods.getInner_code() + " . " + goods.getName());

                goodsList.add(goods);

            } while (cur.moveToNext());

            cur.close();
        }


        //String[] names = { "Иван", "Марья", "Петр", "Антон", "Даша", "Борис", "Костя", "Игорь", "Анна", "Денис", "Андрей" };

        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, R.layout.activity_warehouse_item, names);

        lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        //ArrayAdapter<Goods> adapter = new ArrayAdapter<Goods>(context, R.layout.activity_warehouse_item, goodsList);
        ArrayAdapter<Goods> adapter = new ArrayAdapter<Goods>(context, R.layout.activity_warehouse_simple_item, goodsList);

        lv.setAdapter(adapter);

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

        long rowID = db.insertRecord(DBTables.TABLE_GOODS, cv);

        activity.getSupportLoaderManager().getLoader(GOODS_LOADER).forceLoad();

        Log.d(LOG_TAG, "row inserted, ID = " + rowID);
    }

    @Override
    public void buttonClick(int buttonId) {
        //Intent intent = null;

        switch (buttonId) {
            case R.id.add_goods_btn:
                //intent = new Intent(activity, AddGoodsActivity.class);

                //insertGoods();
                break;

            case R.id.button1:



                //intent = new Intent(activity, AddGoodsActivity.class);
                //loadGoods();
                break;
        }

        /*if (intent == null) {
            Toast.makeText(activity, R.string.select_another_action, Toast.LENGTH_SHORT).show();
            return;
        }

        activity.startActivity(intent);*/
    }

    @Override
    public void onItemSelected(long dbRecordId) {
        /*if (dbRecordId == context.getResources().getInteger(R.integer.all_group_id)) {
            //activity.getSupportLoaderManager().initLoader(GOODS_LOADER, null, activity);
            //activity.getSupportLoaderManager().getLoader(GOODS_LOADER).forceLoad();
            return;
        }*/

        /*Log.d(LOG_TAG, "onItemSelected(" + dbRecordId + ");");

        if (dbRecordId == context.getResources().getInteger(R.integer.all_group_id)) {
            return;
        }

        Bundle b = new Bundle();
        b.putLong(DBTables.GOODS_GROUP_ID, dbRecordId);
        //activity.getSupportLoaderManager().restartLoader(GOODS_LOADER, b, activity);
        activity.getSupportLoaderManager().initLoader(GOODS_LOADER, b, activity);*/
    }

    @Override
    public boolean menuClick(int itemId, long dbRecordId) {

        DB_RECORD_ID = dbRecordId;

        switch (itemId) {
            case R.id.activity_warehouse_mnu_lv_add:
                // TODO Add goods
                Log.d(LOG_TAG, "Add goods, ID = " + dbRecordId);
                break;

            case R.id.activity_warehouse_mnu_lv_qty:
                // TODO Change goods amount
                Log.d(LOG_TAG, "Change goods amount, ID = " + dbRecordId);
                break;

            case R.id.activity_warehouse_mnu_lv_edit:
                if (!dialogs.containsKey(DIALOG_EDIT_GOODS)) {
                    TwoButtonDialog dialog = TwoButtonDialog.newInstance("Редактирование товара", "Редактировать выбранный товар?", null, null);
                    dialogs.put(DIALOG_EDIT_GOODS, dialog);
                }

                dialogs.get(DIALOG_EDIT_GOODS).show(activity.getFragmentManager(), DIALOG_EDIT_GOODS);

                //Toast.makeText(activity, "EditGoods", Toast.LENGTH_SHORT).show();
                Log.d(LOG_TAG, "Edit goods, ID: " + dbRecordId + " Hash: " + dialogs.get(DIALOG_EDIT_GOODS).hashCode());
                break;

            case R.id.activity_warehouse_mnu_lv_delete:
                if (!dialogs.containsKey(DIALOG_DELETE_GOODS)) {
                    TwoButtonDialog dialog = TwoButtonDialog.newInstance("Удаление товара", "Удалить выбранный товар?", null, null);
                    dialogs.put(DIALOG_DELETE_GOODS, dialog);
                }

                dialogs.get(DIALOG_DELETE_GOODS).show(activity.getFragmentManager(), DIALOG_DELETE_GOODS);

                //Toast.makeText(activity, "DeleteGoods", Toast.LENGTH_SHORT).show();
                Log.d(LOG_TAG, "Delete goods, ID: " + dbRecordId + " Hash: " + dialogs.get(DIALOG_DELETE_GOODS).hashCode());
                break;
        }

        return true;
    }

    @Override
    public void dialogClick(String dialogTag, boolean result) {
        switch (dialogTag) {
            case DIALOG_EDIT_GOODS:
                // TODO Edit goods
                Toast.makeText(activity, "EditGoods: " + DB_RECORD_ID + " res: " + result, Toast.LENGTH_SHORT).show();
                Log.d(LOG_TAG, "Edit goods, ID: " + DB_RECORD_ID + " res: " + result);
                break;

            case DIALOG_DELETE_GOODS:

                if (result) db.deleteRecordById(DBTables.TABLE_GOODS, DB_RECORD_ID);

                activity.getSupportLoaderManager().getLoader(GOODS_LOADER).forceLoad();
                Toast.makeText(activity, "DeleteGoods: " + DB_RECORD_ID + " res: " + result, Toast.LENGTH_SHORT).show();
                Log.d(LOG_TAG, "Delete goods, ID: " + DB_RECORD_ID + " res: " + result);
                break;
        }
    }

    public MyCursorLoader GetMyCursorLoader(int loaderId, Bundle bundle) {

        if (bundle == null) {
            switch (loaderId) {
                case GROUPS_LOADER:
                    Log.d(LOG_TAG, "GetMyCursorLoader(" + loaderId + ", " + bundle + ");");
                    return new MyCursorLoader(context, db, DBTables.TABLE_GROUPS);
                case GOODS_LOADER:
                    Log.d(LOG_TAG, "GetMyCursorLoader(" + loaderId + ", " + bundle + ");");
                    return new MyCursorLoader(context, db, DBTables.TABLE_GOODS);
            }
        } else {
            Log.d(LOG_TAG, "GetMyCursorLoader(" + loaderId + ", " + bundle + ");");
            return new MyCursorLoader(context, db, DBTables.TABLE_GOODS, bundle.getLong(DBTables.GOODS_GROUP_ID));
        }

        return null;
    }

    public void swapCursor(int loaderId, Cursor cursor) {
        switch (loaderId) {
            case GOODS_LOADER:
                Log.d(LOG_TAG, "swapCursor(" + loaderId + ", " + cursor.toString().replace("android.database.sqlite.", "") + ");");
                lvAdapter.swapCursor(cursor);
                break;
            case GROUPS_LOADER:
                Log.d(LOG_TAG, "swapCursor(" + loaderId + ", " + cursor.toString().replace("android.database.sqlite.", "") + ");");
                spnAdapter.swapCursor(cursor);
                break;
        }
    }
}
