package cheese.soft.saleshelper.presentation.warehouse;

import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import cheese.soft.saleshelper.data.db.MyCursorLoader;

public interface WarehouseContract {
    void buttonClick(int buttonId);
    boolean menuClick(int itemId, long dbRecordId);
    void dialogClick(String dialogTag, boolean result);
    void onItemSelected(long dbRecordId);
    //void buttonClick(View v);
    //boolean menuClick(MenuItem item);
    void initLoaders();
    //WarehousePresenter.MyCursorLoader GetMyCursorLoader(int loaderId);
    MyCursorLoader GetMyCursorLoader(int loaderId, Bundle bundle);
    void swapCursor(int loaderId, Cursor cursor);
    void activityDestroy();
}
