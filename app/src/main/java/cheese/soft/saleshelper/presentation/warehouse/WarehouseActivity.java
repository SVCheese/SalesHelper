package cheese.soft.saleshelper.presentation.warehouse;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import cheese.soft.saleshelper.R;
import cheese.soft.saleshelper.data.db.DBHelper;

//public class WarehouseActivity extends AppCompatActivity implements View.OnClickListener, LoaderManager.LoaderCallbacks<String> {
public class WarehouseActivity extends AppCompatActivity implements View.OnClickListener {

    //final String LOG_TAG = "myLogs";
    WarehouseContract warehouseContract;

    //DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warehouse);

        Button add_goods_btn = findViewById(R.id.add_goods_btn);
        add_goods_btn.setOnClickListener(this);
        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(this);

        warehouseContract = new WarehousePresenter(this);

        /*Loader<String> loader = getSupportLoaderManager().initLoader(0, null, this);
          warehouseContract.LoadDataFromDB(this, loader); */

        //dbHelper = new DBHelper(this);

    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        warehouseContract.activityDestroy();
    }

    @Override
    public void onClick(View v) {

        // подключаемся к БД
        //SQLiteDatabase db = dbHelper.getWritableDatabase();

        switch (v.getId()) {
            case R.id.button1:
                warehouseContract.buttonClick(v);
                break;

            case R.id.add_goods_btn:
                warehouseContract.buttonClick(v);
                break;
        }

        // закрываем подключение к БД
        //dbHelper.close();

        //warehouseContract.ButtonClick(v);
    }


    /*@NonNull
    @Override
    public Loader<String> onCreateLoader(int i, @Nullable Bundle bundle) {
        return null;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String s) {

    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }*/
}
