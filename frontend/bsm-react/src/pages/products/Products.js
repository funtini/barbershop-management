import React, { Component } from 'react'
import { withTranslation } from 'react-i18next';

//styles
import styles from './Products.css';

class Products extends Component {
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
                <p>PRODUCTS</p>
                <p>
                    TEST TEEST TEST
                </p>
            </div>
        )}
}

export default withTranslation()(Products);
