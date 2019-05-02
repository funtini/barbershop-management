import React, { Component } from 'react';
import { Field, Form, reduxForm } from 'redux-form'
import styles from './LoginForm.css';
import joinClassNames from 'shared/utils/joinClassNames';

let LoginForm = ( props ) => {
    const { handleSubmit } = props;

    return (
        <form onSubmit={ handleSubmit }>
            <div className={ styles.formGroup }>
                <Field className={ styles.formControl } name={'Email'} component={'input'} type={'email'} placeholder={'Email'}/>
                {/*<input className={ styles.formControl } type={'email'} placeholder={'Email'}/>*/}
            </div>
            <div className={ styles.formGroup }>
                <Field className={ styles.formControl } type={'password'} placeholder={'Password'} name={'Password'} component={'input'}/>
            </div>
            {/*<div className={ joinClassNames(styles.formGroup, styles.formActions) }>*/}
                {/*<div className={ joinClassNames(styles.action, styles.rememberMe) }>*/}
                    {/*<input className={ styles.checkBox } type={'checkbox'}/>*/}
                    {/*/!*<label>remember me</label>*!/*/}
                    {/*<span> remember me</span>*/}
                {/*</div>*/}

            {/*</div>*/}
            <button className={ joinClassNames(styles.action,styles.formControl) } type='submit'>Sign In</button>
        </form>
    )
};

LoginForm = reduxForm({
    form: 'loginForm'
})(LoginForm);

export default LoginForm;
