package cheese.soft.saleshelper;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class TwoButtonDialog extends DialogFragment implements OnClickListener {

    private final String LOG_TAG = "myLogs";
    private static String TITLE = "title";
    private static String BODY = "body";
    private static String YES_BUTTON = "yesButtonText";
    private static String NO_BUTTON = "noButtonText";

    NoticeDialogListener ndListener;

    public interface NoticeDialogListener {
        void onDialogClick(String dialogTag, boolean result);
    }

    public static TwoButtonDialog newInstance(String title, String body, String yesButtonText, String noButtonText) {
        TwoButtonDialog twoButtonDialog = new TwoButtonDialog();
        Bundle args = new Bundle();
        args.putString(TITLE, title);
        args.putString(BODY, body);
        args.putString(YES_BUTTON, yesButtonText);
        args.putString(NO_BUTTON, noButtonText);
        twoButtonDialog.setArguments(args);
        return twoButtonDialog;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        getDialog().setTitle(getArguments().getString(TITLE, "Title!"));

        View v = inflater.inflate(R.layout.dialog_two_button, null);
        TextView tv = v.findViewById(R.id.dialog_two_button_tv);
        tv.setText(getArguments().getString(BODY, ""));

        Button yesBtn = v.findViewById(R.id.dialog_two_button_btnYes);
        yesBtn.setText(getArguments().getString(YES_BUTTON, "Да"));
        yesBtn.setOnClickListener(this);

        Button noBtn = v.findViewById(R.id.dialog_two_button_btnNo);
        noBtn.setText(getArguments().getString(NO_BUTTON, "Нет"));
        noBtn.setOnClickListener(this);

        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            ndListener = (NoticeDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }

    @Override
    public void onClick(View v) {
        ndListener.onDialogClick(getTag(), v.getId() == R.id.dialog_two_button_btnYes);
        dismiss();
    }

    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        Log.d(LOG_TAG, "Dialog: " + getTag() + " onDismiss");
    }

    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        Log.d(LOG_TAG, "Dialog 1: onCancel");
    }
}