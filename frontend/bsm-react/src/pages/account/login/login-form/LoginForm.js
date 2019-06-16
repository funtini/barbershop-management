import React from 'react';
import { Field, reduxForm } from 'redux-form'

// components
import CheckBox from 'shared/components/form/redux-form/CheckBox';
import Input from 'shared/components/form/redux-form/Input';
import SignInButton from 'shared/components/buttons/sign-in-button/SignInButton';

// style
import styles from './LoginForm.css';

let LoginForm = ( props ) => {
    const { handleSubmit, rememberMe, onToggleCheckBox } = props;

    return (
        <form onSubmit={ (event) => { handleSubmit(); event.preventDefault()} }>
            <div className={ styles.formGroup }>
                <Field name={'usernameOrEmail'} type={'email'} placeholder={'Email'} component={ Input } wide/>
            </div>
            <div className={ styles.formGroup }>
                <Field name={'password'} type={'password'} placeholder={'Password'} component={ Input } wide/>
            </div>
            <div className={ styles.formGroup }>
                <Field label={'Remember me'} name={'rememberMe'} component={ CheckBox }/>
                {/*<CheckBox id={'rememberMe'} label={'remember me'} labelPosition={'after'} checked={ rememberMe } onChange={ onToggleCheckBox }/>*/}
            </div>
            <SignInButton className={ styles.button } isLoading={ props.isLoading } loaderType={'dual-ring'} type={'submit'}>Sign in</SignInButton>
        </form>
    )
};

LoginForm = reduxForm({
    form: 'loginForm'
})(LoginForm);

export default LoginForm;
