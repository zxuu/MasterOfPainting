package com.zxu.masterofpainting.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.zxu.masterofpainting.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class LookFragment extends Fragment {
    private Button starturl;
    private ImageView resultimg;
    public LookFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_look,container,false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        starturl = (Button) view.findViewById(R.id.starurl);
        resultimg = (ImageView) view.findViewById(R.id.res_picture);
        starturl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonStart();
            }
        });
    }

    private void buttonStart() {
        shellExec();
    }

    public void shellExec() {
        Runtime mRuntime = Runtime.getRuntime();
        try {
            //Process中封装了返回的结果和执行错误的结果
            Process mProcess = mRuntime.exec("adb shell");
            BufferedReader mReader = new BufferedReader(new InputStreamReader(mProcess.getInputStream()));
            StringBuffer mRespBuff = new StringBuffer();
            char[] buff = new char[1024];
            int ch = 0;
            while ((ch = mReader.read(buff)) != -1) {
                mRespBuff.append(buff, 0, ch);
            }
            mReader.close();
            Log.d(TAG, "shellExec: " + mRespBuff.toString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
