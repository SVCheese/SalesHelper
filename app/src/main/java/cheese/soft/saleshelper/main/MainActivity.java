package cheese.soft.saleshelper.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cheese.soft.saleshelper.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    MainContract mainContract;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainContract = new MainPresenter(this);

        Button add_goods_btn = findViewById(R.id.open_warehouse_btn);
        add_goods_btn.setOnClickListener(this);
        Button open_cashbox_btn = findViewById(R.id.open_cashbox_btn);
        open_cashbox_btn.setOnClickListener(this);
        Button show_reports_btn = findViewById(R.id.show_reports_btn);
        show_reports_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        mainContract.ButtonClick(v);
    }
}
