package cheese.soft.dialogfragment;

import android.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    DialogFragment dlg1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dlg1 = new Dialog1();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDlg1:
                dlg1.show(getFragmentManager(), "dlg1");
                break;
            default:
                break;
        }

    }
}
