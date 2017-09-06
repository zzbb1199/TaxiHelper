/*
 * Copyright (c) 2016 咖枯 <kaku201313@163.com | 3772304@qq.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.example.taxihelper.dagger.component;

import android.app.Activity;
import android.content.Context;

import com.example.taxihelper.dagger.module.ActivityModule;
import com.example.taxihelper.dagger.scope.ContextLife;
import com.example.taxihelper.dagger.scope.PerActivity;
import com.example.taxihelper.mvp.ui.activities.ChargeActivity;
import com.example.taxihelper.mvp.ui.activities.CityChooseActivity;
import com.example.taxihelper.mvp.ui.activities.LoginActivity;
import com.example.taxihelper.mvp.ui.activities.ShenZhouTaxiActivity;
import com.example.taxihelper.mvp.ui.activities.WaitingDriveAcceptActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    @ContextLife("Activity")
    Context getActivityContext();

    @ContextLife("Application")
    Context getApplicationContext();

    Activity getActivity();
    
    void inject(LoginActivity loginActivity);
    void inject(ShenZhouTaxiActivity shenZhouTaxiActivity);
    void inject(CityChooseActivity cityChooseActivity);

    void inject(ChargeActivity chargeActivity);

    void inject(WaitingDriveAcceptActivity waitingDriveAcceptActivity);
    //    void inject(ChargeActivity chargeActivity);


}
