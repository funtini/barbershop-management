import React, { Component } from 'react';
import { connect } from 'react-redux';
import { compose } from 'redux';
import { Field, reduxForm, formValues, getFormValues } from 'redux-form'
import PropTypes from 'prop-types';
import { withRouter } from 'react-router';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { withTranslation } from 'react-i18next';

// State
import { login } from 'shared/state/account';

// Components
import LoginForm from './login-form/LoginForm';
import LinkButton from 'shared/components/buttons/link-button';
import Notification from 'shared/components/notification/Notification';

// Util
import joinClassNames from 'shared/utils/joinClassNames';
import { setCookie } from 'shared/utils/cookieUtils';
import { ACCESS_TOKEN } from 'shared/utils/apiClient';

// Style
import styles from './Login.css';


const sessionTime = 0.15;
const sessionCookieKey = 'sessionTime';

class Login extends Component {
    constructor(props) {
        super(props);
        this.state = {
            isLoading: false,
            rememberMe: false,
            error: undefined,
        }

        this._handleSubmit = this._handleSubmit.bind(this);
        this._handleToggleCheckBox = this._handleToggleCheckBox.bind(this);
    }

    render() {
        const { t } = this.props;

        return (
            <div className={ styles.wrapper }>
                <div className={ styles.loginBox }>
                    <div className={ styles.header }>
                        <b>{ t('brand.name') }</b> { t('brand.suffix') }
                    </div>
                    <Notification className={ styles.notify } type={'danger'}/>
                    <div className={ styles.body }>
                        <div className={ styles.title }>
                            Sign in to start your session
                        </div>
                        <LoginForm err={ this.state.error } isLoading={ this.state.isLoading } onSubmit={ this._handleSubmit } rememberMe={ this.state.rememberMe } onToggleCheckBox={ this._handleToggleCheckBox }/>
                        <LinkButton className={ styles.forgotPassword }>I forgot my password</LinkButton>
                    </div>
                </div>
            </div>
        );
    }

    _handleSubmit = (values) => {
        console.log(values);
        // setCookie(ACCESS_TOKEN,'true', 0.015);
        // this.props.history.push('/')
        this.props.login(values);
        this.setState(prevState => ({
            isLoading: !prevState.isLoading,
        }))

        this.setState({
            error: 'OLA'
        })
    }

    _handleToggleCheckBox = () => {
        this.setState(prevState => ({
            rememberMe: !prevState.rememberMe
        }))
    }
}

Login.propTypes = {

};

const mapDispatchToProps = (dispatch) => ({
    login: (values) => dispatch(login(values))
});

export default compose(withRouter,withTranslation(['header']),connect(null,mapDispatchToProps))(Login);

