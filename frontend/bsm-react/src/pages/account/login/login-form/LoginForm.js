import React, { Component } from 'react';
import { Field, Form, reduxForm } from 'redux-form'
// import CheckBox from 'shared/components/form/checkbox/Checkbox'
import CheckBox from 'shared/components/form/redux-form/CheckBox'
import styles from './LoginForm.css';
import joinClassNames from 'shared/utils/joinClassNames';
import Button from 'shared/components/buttons/button/Button';

let LoginForm = ( props ) => {
    const { handleSubmit, rememberMe, onToggleCheckBox } = props;

    return (
        <form onSubmit={ handleSubmit }>
            <div className={ styles.formGroup }>
                <Field className={ styles.formControl } name={'Email'} component={'input'} type={'email'} placeholder={'Email'}/>
            </div>
            <div className={ styles.formGroup }>
                <Field className={ styles.formControl } type={'password'} placeholder={'Password'} name={'Password'} component={'input'}/>
            </div>
            <div className={ styles.formGroup }>
                <Field label={'Remember me'} name={'rememberMe'} component={ CheckBox }/>
                {/*<CheckBox id={'rememberMe'} label={'remember me'} labelPosition={'after'} checked={ rememberMe } onChange={ onToggleCheckBox }/>*/}
            </div>
            <Button role={'primary'} wide modifier={'outlineButton'} type={'submit'}>Submit</Button>
            <button className={ joinClassNames(styles.actionButton,styles.formControl) } type='submit'>Sign In</button>
        </form>
    )
};

LoginForm = reduxForm({
    form: 'loginForm'
})(LoginForm);

export default LoginForm;
