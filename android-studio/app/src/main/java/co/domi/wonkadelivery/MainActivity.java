package co.domi.wonkadelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageView gasI;
    private ImageView juiI;
    private ImageView hamI;
    private ImageView fryI;
    private int menuType;

    private Communication com;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gasI = findViewById(R.id.gasBtn);
        juiI = findViewById(R.id.juiBtn);
        hamI = findViewById(R.id.hamBtn);
        fryI = findViewById(R.id.fryBtn);

        com = new Communication();
        com.start();
        com.setMainA(this);

        gasI.setOnClickListener(
                (view)->{
                    menuType = 1;
                    com.sendMessage(menuType+"");
                    Toast.makeText(this, "1 sended", Toast.LENGTH_SHORT).show();
                }
        );

        juiI.setOnClickListener(
                (view)->{
                    menuType = 3;
                    com.sendMessage(menuType+"");
                    Toast.makeText(this, "2 sended", Toast.LENGTH_SHORT).show();
                }
        );

        hamI.setOnClickListener(
                (view)->{
                    menuType = 2;
                    com.sendMessage(menuType+"");
                    Toast.makeText(this, "3 sended", Toast.LENGTH_SHORT).show();
                }
        );

        fryI.setOnClickListener(
                (view)->{
                    menuType = 4;
                    com.sendMessage(menuType+"");
                    Toast.makeText(this, "4 sended", Toast.LENGTH_SHORT).show();
                }
        );
    }

    private void onMessage(){
        Toast.makeText(this, "order completed", Toast.LENGTH_SHORT).show();
    }

    public void launchIt(String message, MainActivity mainA) {
        runOnUiThread(new Runnable(){
            @Override
            public void run() {
                Toast.makeText(mainA, "Order #"+message+ " ready", Toast.LENGTH_SHORT).show();

            }
        });
    }
}