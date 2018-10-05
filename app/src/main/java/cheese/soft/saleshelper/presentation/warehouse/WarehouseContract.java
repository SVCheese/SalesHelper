package cheese.soft.saleshelper.presentation.warehouse;

import android.content.Context;
import android.support.v4.content.Loader;
import android.view.View;

public interface WarehouseContract {
    void buttonClick(View v);
    void activityDestroy();
    //void LoadDataFromDB(Context context, Loader<String> loader);
    //void loadGoods();
}
