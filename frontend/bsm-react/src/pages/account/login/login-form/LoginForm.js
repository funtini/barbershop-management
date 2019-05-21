import React, { Component } from 'react';
import { Field, Form, reduxForm } from 'redux-form'

// components
import CheckBox from 'shared/components/form/redux-form/CheckBox';
import Input from 'shared/components/form/redux-form/Input';
import Button from 'shared/components/buttons/button/Button';
import LoaderButton from 'shared/components/buttons/loader-button/LoaderButton'

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
            <Button className={ styles.button } role={'primary'} wide modifier={'outlineButton'} type={'submit'}>Submit</Button>
            <Button className={ styles.button } role={'primary'} wide modifier={'buttonFill'} type={'submit'}>Submit</Button>
            <Button className={ styles.button } role={'primary'} wide gradient modifier={'buttonUnFill'} type={'submit'}>Submit</Button>
            <Button className={ styles.button } role={'unboxed'} wide type={'submit'}>Submit</Button>
            <Button className={ styles.button } role={'primary'} wide type={'submit'}>Submit</Button>
            <LoaderButton className={ styles.button } filled role={'primary'} wide type={'submit'} isLoading={true}>Submit</LoaderButton>
            <button className={ joinClassNames(styles.actionButton,styles.formControl) } type='submit'>Sign In</button>
        </form>
    )
};

LoginForm = reduxForm({
    form: 'loginForm'
})(LoginForm);

export default LoginForm;
