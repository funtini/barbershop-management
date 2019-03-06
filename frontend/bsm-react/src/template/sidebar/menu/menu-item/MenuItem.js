import React from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

//styles
import styles from './MenuItem.css';

//TODO: make active feature for submenus from path compare with exact path or something like that
const MenuItem = ({ path, icon, label, children, selected, onClick }) => (
    <li>
        <a href={ path } className={ selected ? styles.active : undefined } onClick={() =>  onClick && _handleClick(label, onClick) }>
                <FontAwesomeIcon icon={ icon } className={ styles.icon }/>
            <span className={ styles.label }>
                { label }
            </span>
            <span className={ styles.notification }>
                { children }
            </span>
        </a>
    </li>
);

const _handleClick = (label, onClick) => {
    onClick(label)
};

export default MenuItem;
