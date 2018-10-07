package cheese.soft.saleshelper.presentation.warehouse;

import android.app.Dialog;
import android.app.DialogFragment;
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

import cheese.soft.saleshelper.TwoButtonDialog;
import cheese.soft.saleshelper.R;

public class WarehouseActivity extends AppCompatActivity implements View.OnClickListener, LoaderManager.LoaderCallbacks<Cursor>,
        TwoButtonDialog.NoticeDialogListener {

    WarehouseContract warehouseContract;

    ListView lv;

    //final int DIALOG_DELETE_RECORD = 1;
    final String LOG_TAG = "myLogs";

    //DialogFragment dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warehouse);

        Button add_goods_btn = findViewById(R.id.add_goods_btn);
        add_goods_btn.setOnClickListener(this);
        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(this);

        lv = (ListView) findViewById(R.id.a_warehouse_lv);
        registerForContextMenu(lv);

        warehouseContract = new WarehousePresenter(this);

        //dialog = TwoButtonDialog.newInstance("Удаление товара", "Удалить выбранный товар?", null, null);

        getSupportLoaderManager().initLoader(0, null, this);
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        getMenuInflater().inflate(R.menu.activity_warehouse_mnu_lv, menu);

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        if (warehouseContract.menuClick(item.getItemId(), acmi.id)) {
            getSupportLoaderManager().getLoader(0).forceLoad();
            return true;
        }

        return super.onContextItemSelected(item);
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
    public void onDialogClick(String dialogTag, boolean result) {
        warehouseContract.dialogClick(dialogTag, result);
        //Log.d(LOG_TAG, "Dialog tag: " + dialogTag + " click");
    }

    // -----------------------------------------------------------------------------------------------

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int i, @Nullable Bundle bundle) {
        return warehouseContract.GetMyCursorLoader();
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {
        warehouseContract.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
    }
}
