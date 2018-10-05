package cheese.soft.saleshelper.presentation.warehouse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import cheese.soft.saleshelper.R;

public class AddGoodsPresenter implements AddGoodsContract {

    AppCompatActivity activity;

    public AddGoodsPresenter(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Override
    public void ButtonClick(View v) {
        Intent intent = null;

        switch (v.getId()) {
            case R.id.a_add_goods_btn_save:
                Toast.makeText(activity, "Add to DB", Toast.LENGTH_SHORT).show();
                activity.finish();
                break;
        }
        /*if (intent == null) {
            Toast.makeText(activity, R.string.select_another_action, Toast.LENGTH_SHORT).show();
            return;
        }*/

        //activity.startActivity(intent);
    }
}
