package com.example.taxihelper.mvp.ui.fragments.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.taxihelper.App;
import com.example.taxihelper.dagger.component.DaggerFragmentComponent;
import com.example.taxihelper.dagger.component.FragmentComponent;
import com.example.taxihelper.dagger.module.FragmentModule;

import butterknife.ButterKnife;
import rx.Subscription;



public abstract class BaseFragment extends Fragment {

    protected FragmentComponent mFragmentComponent;
    protected Subscription mSubsription;
    protected String TAG;

    public FragmentComponent getFragmentComponent() {
        return mFragmentComponent;
    }

    public abstract void initInjector();

    public abstract void initViews();

    public abstract int getLayoutId();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = this.getClass().getSimpleName();
        initFragmentComponent();
        initInjector();
    }

    private void initFragmentComponent() {
        mFragmentComponent = DaggerFragmentComponent.builder()
                .applicationComponent(((App) getActivity().getApplication()).getmApplicationComponent())
                .fragmentModule(new FragmentModule(this))
                .build();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = inflater.inflate(getLayoutId(),container,false);
        ButterKnife.inject(this, rootView);
        initViews();

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mSubsription != null && mSubsription.isUnsubscribed()) {
            mSubsription.unsubscribe();
        }
    }
}
