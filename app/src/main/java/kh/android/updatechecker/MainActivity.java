package kh.android.updatechecker;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import kh.android.updatecheckerlib.UpdateChecker;

public class MainActivity extends AppCompatActivity{
    EditText mEditTextPackageName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditTextPackageName = (EditText)findViewById(R.id.editText_packagename);
        findViewById(R.id.button_coolapk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check(UpdateChecker.Market.MARKET_COOLAPK, mEditTextPackageName.getText().toString());
            }
        });
        findViewById(R.id.button_googleplay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check(UpdateChecker.Market.MARKET_GOOGLEPLAY, mEditTextPackageName.getText().toString());
            }
        });
        findViewById(R.id.button_wandoujia).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check(UpdateChecker.Market.MARKET_WANDOUJIA, mEditTextPackageName.getText().toString());
            }
        });
    }

    private void check (final UpdateChecker.Market market, final String pkg) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final UpdateChecker.UpdateInfo info = UpdateChecker.check(market, pkg);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                            builder.setTitle("检查结果")
                                    .setMessage(info.getVersionName() + "\n" + info.getChangeLog())
                                    .show();
                        }
                    });
                } catch (final Exception e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                            builder.setTitle("检查结果")
                                    .setMessage(e.getMessage())
                                    .show();
                        }
                    });
                }
            }
        }).start();
    }
}
