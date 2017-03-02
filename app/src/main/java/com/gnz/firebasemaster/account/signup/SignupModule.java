package com.gnz.firebasemaster.account.signup;

import com.gnz.firebasemaster.application.Constants;
import com.gnz.firebasemaster.utils.DataValidator;

import co.netguru.android.commons.di.FragmentScope;
import dagger.Module;
import dagger.Provides;

@Module
public class SignupModule {

    @FragmentScope
    @Provides
    DataValidator provideDataValidator() {
        return new DataValidator(Constants.PASSWORD_LENGHT);
    }

}
