package cheese.soft.saleshelper.presentation.warehouse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cheese.soft.saleshelper.R;

public class AddGoodsActivity extends AppCompatActivity implements View.OnClickListener {

    AddGoodsContract addGoodsContract;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_goods);

        addGoodsContract = new AddGoodsPresenter(this);

        Button a_add_goods_btn_save = findViewById(R.id.a_add_goods_btn_save);
        a_add_goods_btn_save.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        addGoodsContract.ButtonClick(v);
    }
}
