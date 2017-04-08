package com.github.watanabear.learnandroidanimation;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.github.watanabear.learnandroidanimation.databinding.ActivityMainBinding;
import com.github.watanabear.learnandroidanimation.wavecanvas.WaveCanvasActivity;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.actList.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Arrays.asList(ActItem.values())));
        mBinding.actList.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        if (adapterView.getId() == R.id.act_list) {
            ActItem item = (ActItem) adapterView.getItemAtPosition(position);
            startActivity(new Intent(this, item.mActivityClass));
        }
    }

    public enum ActItem {
        WAVE_CANVAS_ACTIVITY("WaveCanvasActivity", WaveCanvasActivity.class),

        ;
        final String mLabel;
        final Class<? extends Activity> mActivityClass;

        ActItem(String label, Class<? extends Activity> activityClass) {
            mLabel = label;
            mActivityClass = activityClass;
        }

        @Override
        public String toString() {
            return mLabel;
        }
    }
}
