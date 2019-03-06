import React from 'react';

//styles
import styles from './MenuHeader.css';

const MenuHeader = ( props ) => (
    <li className={ styles.header }>
        { props.title }
    </li>
);

export default MenuHeader;
