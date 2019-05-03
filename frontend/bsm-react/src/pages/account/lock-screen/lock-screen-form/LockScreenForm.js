import React from 'react';
import { Field, Form, reduxForm } from 'redux-form'

// Styles
import styles from './LockScreenForm.css';
import joinClassNames from "../../../../shared/utils/joinClassNames";

let LockScreenForm = ( props ) => {
    const { handleSubmit } = props;

    return (
        <form onSubmit={ handleSubmit }>
            <div className={ styles.formGroup }>
                <Field className={ styles.formControl }
                       name={'Password'}
                       component={'input'}
                       type={'password'}
                       placeholder={'Password'}/>
                <button className={ joinClassNames(styles.action,styles.formControl) } type='submit'>Sign In</button>
            </div>
        </form>
    )
}
