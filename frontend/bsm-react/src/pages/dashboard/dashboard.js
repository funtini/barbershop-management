import React, { Component } from 'react'

//styles
import styles from './dashboard.css';


class Dashboard extends Component {
    constructor(props) {
        super(props);
        this.state = {
            isLoading: false,
        }
            //state data
        }
        render () {

    return (
        <div className={ styles.dashboardWrapper }>
            <p>DASHBOARD</p>
        </div>
    )}
}

export default Dashboard;