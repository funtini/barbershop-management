import React, { Component } from 'react';
import { Field, Form, reduxForm } from 'redux-form'

// components
import CheckBox from 'shared/components/form/redux-form/CheckBox';
import Input from 'shared/components/form/redux-form/Input';
import Button from 'shared/components/buttons/button/Button';

// utils
import joinClassNames from 'shared/utils/joinClassNames';
// style
import styles from './LoginForm.css';

let LoginForm = ( props ) => {
    const { handleSubmit, rememberMe, onToggleCheckBox } = props;

    return (
        <form onSubmit={ handleSubmit }>
            <div className={ styles.formGroup }>
                <Field name={'Email'} type={'email'} placeholder={'Email'} component={ Input } wide/>
            </div>
            <div className={ styles.formGroup }>
                <Field name={'Password'} type={'password'} placeholder={'Password'} component={ Input } wide/>
            </div>
            <div className={ styles.formGroup }>
                <Field label={'Remember me'} name={'rememberMe'} component={ CheckBox }/>
                {/*<CheckBox id={'rememberMe'} label={'remember me'} labelPosition={'after'} checked={ rememberMe } onChange={ onToggleCheckBox }/>*/}
            </div>
            <Button role={'primary'} wide modifier={'outlineButton'} type={'submit'}>Submit</Button>
            <button className={ joinClassNames(styles.actionButton,styles.formControl) } type='submit'>Sign In</button>
            <div className={styles.buttonFill}>SKEW FILL</div>
        </form>
    )
};

LoginForm = reduxForm({
    form: 'loginForm'
})(LoginForm);

export default LoginForm;
