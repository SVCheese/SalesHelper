package cheese.soft.saleshelper.presentation.warehouse;

import android.database.Cursor;
import android.view.MenuItem;
import android.view.View;

public interface WarehouseContract {
    void buttonClick(int buttonId);
    boolean menuClick(int itemId, long dbRecordId);
    void dialogClick(String dialogTag, boolean result);
    //void buttonClick(View v);
    //boolean menuClick(MenuItem item);
    WarehousePresenter.MyCursorLoader GetMyCursorLoader();
    void swapCursor(Cursor cursor);
    void activityDestroy();
}
