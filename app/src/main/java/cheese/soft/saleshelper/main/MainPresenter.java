package cheese.soft.saleshelper.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import cheese.soft.saleshelper.R;
import cheese.soft.saleshelper.presentation.cashbox.CashBoxActivity;
import cheese.soft.saleshelper.presentation.report.ReportActivity;
import cheese.soft.saleshelper.presentation.warehouse.WarehouseActivity;

public class MainPresenter implements MainContract {

    AppCompatActivity activity;

    public MainPresenter(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Override
    public void ButtonClick(View v) {
        Intent intent = null;

        switch (v.getId()) {
            case R.id.open_warehouse_btn:
                intent = new Intent(activity, WarehouseActivity.class);
                break;
            case R.id.open_cashbox_btn:
                intent = new Intent(activity, CashBoxActivity.class);
                break;
            case R.id.show_reports_btn:
                intent = new Intent(activity, ReportActivity.class);
                break;
        }

        if (intent == null) {
            Toast.makeText(activity, R.string.select_another_action, Toast.LENGTH_SHORT).show();
            return;
        }

        activity.startActivity(intent);
    }

}
