import React from 'react'

//styles
import styles from './headerNavigation.css';

const headerQuickActions = ( props ) => (
    <ul className={ styles.accountMenu }>
        <li>NEW SALE</li>
        <li>NEW CUSTOMER</li>
        <li>NEW BOOKING</li>
    </ul>
);

export default headerQuickActions;