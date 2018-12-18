package com.coffe.shentao.mvp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.coffe.shentao.mvp.R;
import com.coffe.shentao.mvp.presenter.MainPresenter;

public class MainActivity extends AppCompatActivity implements  IMainView{

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    TextView tv ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        tv = (TextView) findViewById(R.id.sample_text);

        MainPresenter mainPresenter=new MainPresenter(this);
        mainPresenter.fetch();
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    @Override
    public void showLoading() {
        Toast.makeText(this, "正在拼命加载中。。。", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showData(String data) {
        tv.setText(data);
    }
}
