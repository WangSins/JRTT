package jrtt.imust.com.jrtt.fragment.sub;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Administrator on 2018-6-28.
 */

public class SubFagment extends Fragment {
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView textView = new TextView(getContext());
        textView.setTextColor(Color.RED);
        textView.setTextSize(22);
        textView.setBackgroundColor(Color.GRAY);
        textView.setGravity(Gravity.CENTER);
        textView.setText(title);
        return textView;
    }
}
