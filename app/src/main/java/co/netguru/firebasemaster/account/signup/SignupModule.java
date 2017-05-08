package co.netguru.firebasemaster.account.signup;

import co.netguru.firebasemaster.application.Constants;
import co.netguru.firebasemaster.utils.DataValidator;

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
