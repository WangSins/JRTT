package jrtt.imust.com.jrtt.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import jrtt.imust.com.jrtt.CollectActivity;
import jrtt.imust.com.jrtt.R;

/*
用户页面，
    1，收藏按钮
        打开一个Activity页面
                ListView
                    数据库保存的新闻记录
                        收藏的新闻
 */
public class UserFragment extends BaseFragment {
    @BindView(R.id.button)
    Button button;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_user, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.button)
    public void onViewClicked() {
        Intent intent =  new Intent(getContext(),CollectActivity.class);
        startActivity(intent);
    }
}
