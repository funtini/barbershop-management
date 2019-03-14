import React, { Component } from 'react'
// import i18n from "i18next";
import { withTranslation } from 'react-i18next';

//styles
import styles from './Dashboard.css';

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
            </div>
        )}
}

export default withTranslation()(Dashboard);