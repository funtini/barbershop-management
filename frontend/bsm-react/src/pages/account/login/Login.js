import React, { Component } from 'react';
import { connect } from 'react-redux';
import { compose } from 'redux';
import { Field, reduxForm, formValues, getFormValues } from 'redux-form'
import PropTypes from 'prop-types';
import { withRouter } from 'react-router';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { withTranslation } from 'react-i18next';

// Components
import LoginForm from './login-form/LoginForm';
import LinkButton from 'shared/components/buttons/link-button';

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
        const { t } = this.props;

        return (
            <div className={ styles.wrapper }>
                <div className={ styles.loginBox }>
                    <div className={ styles.header }>
                        <b>{ t('brand.name') }</b> { t('brand.suffix') }
                    </div>
                    <div className={ styles.body }>
                        <div className={ styles.title }>
                            Sign in to start your session
                        </div>
                        <LoginForm onSubmit={ this._handleSubmit }/>
                        <LinkButton className={ styles.forgotPassword }>I forgot my password</LinkButton>
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



export default withRouter(withTranslation(['header'])(Login));

