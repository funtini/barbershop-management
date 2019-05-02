import React, { Component } from 'react';
import { withTranslation } from 'react-i18next';
import { compose } from 'redux';
import { connect } from 'react-redux';
import { listUsers, ACCESS_TOKEN } from 'shared/utils/apiClient';
import { getCookie } from 'shared/utils/cookieUtils';

// actions
import { switchTheme } from 'shared/state/layout';
import { login } from 'shared/state/account';

// styles
import styles from './Dashboard.css';

const values = {
    usernameOrEmail:'admin@bsm.com',
    password:'12345'
};

const loginRequest = Object.assign({}, values);

class Dashboard extends Component {
    constructor(props) {
        super(props);
        this.state = {
            isLoading: false,
            users: [],
        }
            //state data
        this._handleClick = this._handleClick.bind(this);
        }

    componentWillMount() {
        const { login } = this.props;

        // login({...loginRequest})
    }



    render () {
        const { t } = this.props;
console.log(this.state.users);
        return (
            <div className={ styles.dashboardWrapper }>
                <p>DASHBOARD</p>
                <p>
                    {t('pages:dashboard.title')}
                    {t('pages:dashboard.subtitle')}
                </p>
                { this.state.users && this.state.users.map( user => <p key={user.name}>{ user.name }</p>) }
                <br/>
                <button onClick={this._handleClick}> Change Theme </button>
            </div>
        )}

    _handleClick() {
        console.log('clicked me');
        const { changeTheme } = this.props;
        changeTheme('BLACK_WHITE');
        console.log(changeTheme)
        const user = getCookie('username');
        console.log('COOKIE NAME', user)

        listUsers().then(resp => this.setState({ users: resp.data }));

    }
}

const mapDispatchToProps = (dispatch) => ({
    changeTheme: (theme) => dispatch(switchTheme(theme)),
    login: (values) => dispatch(login(values))
});

export default compose(withTranslation(),connect(null,mapDispatchToProps))(Dashboard);
