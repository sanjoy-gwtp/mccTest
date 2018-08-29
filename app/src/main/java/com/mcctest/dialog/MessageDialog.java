package com.mcctest.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mcctest.R;

/**
 * Created by sanjoy on 6/7/17.
 */

public class MessageDialog extends AlertDialog {

    private String message;
    private boolean success;

    private Button okButton;

    public MessageDialog(Context context, String message, boolean success) {
        super(context);
        this.message=message;
        this.success=success;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setInverseBackgroundForced(true);

        setContentView(R.layout.dialog_message);
        setCanceledOnTouchOutside(false);
        setCancelable(false);

        TextView alertMsg=(TextView)findViewById(R.id.alert_msg);
        alertMsg.setText(message);

        if(success){
            ImageView icon = (ImageView)findViewById(R.id.iconImage);
            icon.setImageResource(R.mipmap.ic_done_white_24dp);
        }

        okButton=(Button) findViewById(R.id.okButton);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}
