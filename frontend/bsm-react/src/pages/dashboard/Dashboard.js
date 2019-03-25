import React, { Component } from 'react';
import { withTranslation } from 'react-i18next';
import { compose } from 'redux';
import { connect } from 'react-redux';

// actions
import { switchTheme } from 'shared/state/layout';

// styles
import styles from './Dashboard.css';



class Dashboard extends Component {
    constructor(props) {
        super(props);
        this.state = {
            isLoading: false,
        }
            //state data
        this._handleClick = this._handleClick.bind(this);
        }



    render () {
        const { t } = this.props;

        return (
            <div className={ styles.dashboardWrapper }>
                <p>DASHBOARD</p>
                <p>
                    {t('pages:dashboard.title')}
                    {t('pages:dashboard.subtitle')}
                </p>
                <br/>
                <button onClick={this._handleClick}> Change Theme </button>
            </div>
        )}

    _handleClick() {
        console.log('clicked me');
        const { changeTheme } = this.props;
        changeTheme('BLACK_WHITE');
        console.log(changeTheme)

    }
}

const mapDispatchToProps = (dispatch) => ({
    changeTheme: (theme) => dispatch(switchTheme(theme))
});

export default compose(withTranslation(),connect(null,mapDispatchToProps))(Dashboard);
