import React, { Component } from 'react';
import { connect } from 'react-redux';
import { compose } from 'redux';
import { Field, reduxForm, formValues, getFormValues } from 'redux-form'
import PropTypes from 'prop-types';
import { withRouter } from 'react-router';

// Components
import LoginForm from './login-form/LoginForm';

// Util
import joinClassNames from 'shared/utils/joinClassNames';
import { setCookie } from 'shared/utils/cookieUtils';
import { ACCESS_TOKEN } from 'shared/utils/apiClient';

// Style
import styles from './Login.css';

const sessionTime = 0.15;
const sessionCookieKey = 'sessionTime'

class Login extends Component {
    constructor(props) {
        super(props);

        this._handleSubmit = this._handleSubmit.bind(this);
    }

    render() {
        return (
            <div className={ styles.wrapper }>
                <div className={ styles.loginBox }>
                    <div className={ styles.header }>
                        <h2> BSManagement</h2>
                    </div>
                    <div className={ styles.body }>
                        <div className={ styles.title }>
                            Sign in to start your session
                        </div>
                        <LoginForm onSubmit={ this._handleSubmit }/>
                        <a>Forgot Password</a>
                    </div>
                </div>
            </div>
        );
    }

    _handleSubmit = (values) => {
        console.log(values)
        setCookie(ACCESS_TOKEN,'true', 0.015);
        this.props.history.push('/')
    }
}

Login.propTypes = {

};



export default withRouter(Login);

