import React, { Component } from 'react';
import { compose } from 'redux';
import { Field, reduxForm, formValues } from 'redux-form'
import PropTypes from 'prop-types';
import { withRouter } from 'react-router';

// Components
import LoginForm from './login-form/LoginForm';

// Util
import joinClassNames from 'shared/utils/joinClassNames';

// Style
import styles from './Login.css';

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
                        <LoginForm handleSubmit={ this._handleSubmit }/>
                        <a>Forgot Password</a>
                    </div>
                </div>
            </div>
        );
    }

    _handleSubmit = (values) => {
        console.log(values.target)
        this.props.history.push('/')
    }
}

Login.propTypes = {

};



export default withRouter(Login);

