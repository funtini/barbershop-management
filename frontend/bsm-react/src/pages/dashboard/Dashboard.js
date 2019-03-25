import React, { Component } from 'react';
import { withTranslation } from 'react-i18next';
import changeTheme from 'shared/utils/changeTheme';
import { themeColors } from 'shared/utils/changeTheme';

//styles
import styles from './Dashboard.css';
import Layout from "../../App";



class Dashboard extends Component {
    constructor(props) {
        super(props);
        this.state = {
            isLoading: false,
        }
            //state data
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
        changeTheme(themeColors.BLACK_WHITE);

    }
}

export default withTranslation()(Dashboard);
