package com.example.taxihelper.mvp.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.eugeneek.smilebar.SmileBar;
import com.example.taxihelper.R;
import com.example.taxihelper.constant.Constant;
import com.example.taxihelper.mvp.contract.CommentContract;
import com.example.taxihelper.mvp.presenter.CommentPresenterImpl;
import com.example.taxihelper.mvp.ui.fragments.base.BaseFragment;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by 张兴锐 on 2017/9/8.
 */

public class CommentFragment extends BaseFragment implements CommentContract.View {
    String orderId;
    @InjectView(R.id.starBar)
    SmileBar starBar;
    @InjectView(R.id.remark_input)
    EditText remarkInput;

    int score = 5;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        orderId = getArguments().getString(Constant.ORDER_ID);
    }
    
    @Inject
    CommentPresenterImpl presenter;

    @Override
    public void initInjector() {
        mFragmentComponent.inject(this);
        presenter.injectView(this);
    }

    @Override
    public void initViews() {
        starBar.setRating(score);
        starBar.setOnRatingSliderChangeListener(new SmileBar.OnRatingSliderChangeListener() {
            @Override
            public void onPendingRating(int i) {
                
            }

            @Override
            public void onFinalRating(int i) {
                score = i;
            }

            @Override
            public void onCancelRating() {

            }
        });
    }

    
    
    @Override
    public int getLayoutId() {
        return R.layout.fragment_comment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);
        return rootView;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void showMsg(String msg) {
        super.showMsg(msg);
        getActivity().finish();
    }

    @OnClick(R.id.comment_finish)
    public void onViewClicked() {
        presenter.commentOrder(orderId,score,remarkInput.getText().toString());
    }
}
