package cheese.soft.saleshelper.presentation.warehouse;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import cheese.soft.saleshelper.R;
import cheese.soft.saleshelper.TwoButtonDialog;

public class WarehouseActivity extends AppCompatActivity implements View.OnClickListener, LoaderManager.LoaderCallbacks<Cursor>,
        TwoButtonDialog.NoticeDialogListener, AdapterView.OnItemSelectedListener {

    WarehouseContract warehouseContract;

    final String LOG_TAG = "myLogs";

    ListView lv;
    Spinner spn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warehouse);

        Button add_goods_btn = findViewById(R.id.add_goods_btn);
        add_goods_btn.setOnClickListener(this);
        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(this);

        spn = findViewById(R.id.a_warehouse_spn);
        spn.setOnItemSelectedListener(this);

        lv = findViewById(R.id.a_warehouse_lv);
        registerForContextMenu(lv);

        warehouseContract = new WarehousePresenter(this);
        warehouseContract.initLoaders();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        warehouseContract.activityDestroy();
    }

    @Override
    public void onClick(View v) {
        warehouseContract.buttonClick(v.getId());
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        warehouseContract.onItemSelected(id);
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
    }

    @Override
    public void onDialogClick(String dialogTag, boolean result) {
        warehouseContract.dialogClick(dialogTag, result);
        //Log.d(LOG_TAG, "Dialog tag: " + dialogTag + " click");
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        getMenuInflater().inflate(R.menu.activity_warehouse_mnu_lv, menu);

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        if (warehouseContract.menuClick(item.getItemId(), acmi.id)) {
            return true;
        }

        return super.onContextItemSelected(item);
    }

    // -----------------------------------------------------------------------------------------------

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int i, @Nullable Bundle bundle) {
        Log.d(LOG_TAG, "onCreateLoader(" + i + ", " + bundle + ");");
        return warehouseContract.GetMyCursorLoader(i, bundle);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {
        Log.d(LOG_TAG, "onLoadFinished(" + loader.getId() + ", " + cursor.toString().replace("android.database.sqlite.", "") + ");");
        warehouseContract.swapCursor(loader.getId(), cursor);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        Log.d(LOG_TAG, "onLoaderReset(" + loader.getId() + ");");
    }
}
