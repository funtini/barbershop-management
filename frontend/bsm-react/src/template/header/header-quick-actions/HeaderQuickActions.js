import React from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

//styles
import styles from './HeaderQuickActions.css';

const HeaderQuickActions = ( props ) => (
    props.isMobile ?
        <ul className={ styles.quickActions }>
            <li><FontAwesomeIcon icon={ 'hand-holding-usd' }/></li>
            <li><FontAwesomeIcon icon={ 'user-plus' }/></li>
            <li><FontAwesomeIcon icon={ 'calendar-plus' }/></li>
        </ul> :
        <ul className={ styles.quickActions }>
            <li>NEW SALE</li>
            <li>NEW CUSTOMER</li>
            <li>NEW BOOKING</li>
        </ul>

);

export default HeaderQuickActions;
