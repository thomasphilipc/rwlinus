package com.roamworks.apps.rwlinus.rwlinus;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.telephony.gsm.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public  void button_on_click(View v)
    {
        Button button =  (Button) v;
        ((Button) v).setText("clicked");
        send_sms();
        send_sms_intent();
    }


    public boolean isSimExists() {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        int SIM_STATE = telephonyManager.getSimState();

        if (SIM_STATE == TelephonyManager.SIM_STATE_READY)
            return true;
        else {
            // we can inform user about sim state
            switch (SIM_STATE) {
                case TelephonyManager.SIM_STATE_ABSENT:
                case TelephonyManager.SIM_STATE_NETWORK_LOCKED:
                case TelephonyManager.SIM_STATE_PIN_REQUIRED:
                case TelephonyManager.SIM_STATE_PUK_REQUIRED:
                case TelephonyManager.SIM_STATE_UNKNOWN:
                    break;
            }
            return false;
        }
    }
    private void send_sms()
    {

        try {
            SmsManager smgr = SmsManager.getDefault();
            smgr.sendTextMessage("+971529210255", null, "sms message", null, null);

            Toast.makeText(MainActivity.this, "SMS Sent Successfully", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Toast.makeText(MainActivity.this, "SMS Failed to Send, Please try again", Toast.LENGTH_SHORT).show();
        }



    }

    private void send_sms_intent()
    {
        String phone = "++971529210255";
        String message = "Hello";


        String SMS_SENT = "SMS_SENT";
        String SMS_DELIVERED = "SMS_DELIVERED";



// unregisterReceiver()

// get the default instance of SmsManager
        SmsManager smsManager = SmsManager.getDefault();

        PendingIntent sentPendingIntent = PendingIntent.getBroadcast(this, 0, new Intent(SMS_SENT), 0);
        PendingIntent deliveredPendingIntent = PendingIntent.getBroadcast(this, 0, new Intent(SMS_DELIVERED), 0);


        Intent sendIntent = new Intent(Intent.ACTION_VIEW);
        sendIntent.putExtra("address", phone);
        sendIntent.putExtra("sms_body", message);
        sendIntent.setType("vnd.android-dir/mms-sms");
        startActivity(sendIntent);



    }


}
