import React from 'react'

//styles
import styles from './headerQuickActions.css';

const headerQuickActions = ( props ) => (
        <ul className={ styles.quickActions }>
            <li>NEW SALE</li>
            <li>NEW CUSTOMER</li>
            <li>NEW BOOKING</li>
        </ul>
);

export default headerQuickActions;