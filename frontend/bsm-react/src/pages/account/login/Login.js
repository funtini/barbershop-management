import React, { Component } from 'react';
import { connect } from 'react-redux';
import { compose } from 'redux';
import PropTypes from 'prop-types';
import { withRouter } from 'react-router';
import { withTranslation } from 'react-i18next';

// State
import { login, loadUserProfile, getErrors, getUser, getUserDetails, isUserAuthenticated, isLoading } from 'shared/state/account';
import { istBreakpointLessThan } from 'shared/state/viewport/selectors';

// Components
import LoginForm from './login-form/LoginForm';
import LinkButton from 'shared/components/buttons/link-button';
import Notification from 'shared/components/notification/Notification';

// Util
import joinClassNames from 'shared/utils/joinClassNames';

// Style
import styles from './Login.css';

class Login extends Component {
    constructor(props) {
        super(props);
        this.state = {
            rememberMe: false,
            openNotification: false,
        };

        this._handleSubmit = this._handleSubmit.bind(this);
        this._handleToggleCheckBox = this._handleToggleCheckBox.bind(this);
    }

    render() {
        const { t, isLoading, error } = this.props;

        return (
            <div className={ styles.wrapper }>
                <div className={ styles.loginBox }>
                    <div className={ styles.header }>
                        <b>{ t('brand.name') }</b> { t('brand.suffix') }
                    </div>
                    {
                        error &&
                      <Notification
                          className={ styles.notify }
                          type={'danger'}
                          shouldOpen={ this.state.openNotification }
                          onClose={ this._handleCloseNotification }>
                            { t('validation:login.error') }
                        </Notification>
                    }
                    <div className={ styles.body }>
                        <div className={ styles.title }>
                            { t('account:login.sign-in-title') }
                        </div>
                        <LoginForm err={ this.state.error } isLoading={ isLoading } onSubmit={ this._handleSubmit } rememberMe={ this.state.rememberMe } onToggleCheckBox={ this._handleToggleCheckBox }/>
                        <LinkButton className={ styles.forgotPassword }>
                            { t('account:login.forgot-password') }
                        </LinkButton>
                    </div>
                </div>
            </div>
        );
    }

    _handleCloseNotification = () => {
        this.setState({
            openNotification: false,
            }
        )
    };

    _handleSubmit = (values) => {
        this.props.login(values);
        this.setState({
                openNotification: true,
            }
        );
        this.props.history.push('/')
    };

    _handleToggleCheckBox = () => {
        this.setState(prevState => ({
            rememberMe: !prevState.rememberMe
        }))
    }
}

Login.propTypes = {

};

const mapStateToProps = (state) => ({
    user: getUser(state),
    details: getUserDetails(state),
    isLoading: isLoading(state),
    error: getErrors(state),
    isAuthenticated: isUserAuthenticated(state),
    isMobile: istBreakpointLessThan(state,'md'),
    // viewport: ge
});

const mapDispatchToProps = (dispatch) => ({
    login: (values) => dispatch(login(values)),
    loadUser: () => dispatch(loadUserProfile())
});

export default compose(withRouter,withTranslation(['header','validation','account']),connect(mapStateToProps,mapDispatchToProps))(Login);

