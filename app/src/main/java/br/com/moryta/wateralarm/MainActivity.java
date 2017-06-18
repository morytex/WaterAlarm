package br.com.moryta.wateralarm;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.etTimer)
    EditText etTimer;

    @BindView(R.id.tvCountDown)
    TextView tvCountDown;

    @BindView(R.id.btStart)
    Button btStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.btStart)
    public void onClick(View view) {
        btStart.setEnabled(false);
        long time = Long.valueOf(etTimer.getText().toString());
        new CountDownTimer(time * 1000, 1000) {

            public void onTick(long millisUntilFinished) {
                tvCountDown.setText(String.valueOf(millisUntilFinished / 1000));
            }

            public void onFinish() {
                tvCountDown.setText("0");

                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this);
                builder.setContentTitle("Water alarm");
                builder.setContentText("Drink water now!");
                builder.setTicker("New message alert!");
                builder.setSmallIcon(R.mipmap.ic_launcher_round);

                NotificationManager notificationManager;
                notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                notificationManager.notify(1, builder.build());

                btStart.setEnabled(true);
            }
        }.start();
    }
}
