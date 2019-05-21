import React, { Component } from 'react';
import { withTranslation } from 'react-i18next';
import { compose } from 'redux';
import { connect } from 'react-redux';

// components
import FadeTransition from 'shared/components/animations/fade-transition/FadeTransition';
import CSSLoaderOverlay from 'shared/components/loaders/css-loader-overlay/CSSLoaderOverlay';
import Loader from 'shared/components/loaders/loader/Loader';

// actions
import { switchTheme } from 'shared/state/layout';
import { login } from 'shared/state/account';

// utils
import { listUsers, ACCESS_TOKEN } from 'shared/utils/apiClient';
import { getCookie } from 'shared/utils/cookieUtils';


// styles
import './Dashboard.css';
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
            showList: false,
            highlightedHobby: false,
        }
            //state data
        this._handleClick = this._handleClick.bind(this);
        this._handleSwitch = this._handleSwitch.bind(this);
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
                <Loader/>
                <Loader type={'dual-ring'}/>
                <Loader type={'bars'}/>
                <CSSLoaderOverlay isLoading={true} withLoader={true}>
                <p>DASHBOARD</p>

                <p>
                    {t('pages:dashboard.title')}
                    {t('pages:dashboard.subtitle')}
                </p>
                </CSSLoaderOverlay>
                <div className={ styles.container }>
                    <button className={ styles.container } onClick={this._handleSwitch}>
                        Test Transition
                    </button>
                        <FadeTransition show={ this.state.showList } timeout={ 300 } fadeInSlideOut>
                            <div className={ styles["list-body"] }>
                                <ul className={ styles.list }>
                                    <li className={ styles["list-item"] }>Feed the dog</li>
                                    <li className={ styles["list-item"] }>Cut hair</li>
                                    <li className={ styles["list-item"] }>Do the dishs</li>
                                    <li className={ styles["list-item"] }>Buy grossries</li>
                                </ul>
                            </div>
                        </FadeTransition>

                </div>
                { this.state.users && this.state.users.map( user => <p key={user.name}>{ user.name }</p>) }
                <br/>
                <br/>
                <br/>
                <br/>
                <br/>
                <br/>
                <br/>
                <br/>
                <br/>
                <br/>
                <br/>
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

    _handleSwitch() {
        this.setState(prevState => ({
            showList: !prevState.showList
        }))
    }

    _handleListSwitch() {
        this.setState(state =>({
            highlightedHobby: !state.highlightedHobby
        }))
    }
}

const mapDispatchToProps = (dispatch) => ({
    changeTheme: (theme) => dispatch(switchTheme(theme)),
    login: (values) => dispatch(login(values))
});

export default compose(withTranslation(),connect(null,mapDispatchToProps))(Dashboard);
