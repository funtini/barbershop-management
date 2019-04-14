import React from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

//utils
import joinClassNames from 'shared/utils/joinClassNames';

//styles
import styles from './MenuItem.css';

//TODO: make active feature for submenus from path compare with exact path or something like that
const MenuItem = ({ path, icon, label, children, selected, onClick, isCollapsed }) => (
    <li>
        <a href={ path }
           className={ joinClassNames(selected && styles.active, isCollapsed && styles.menu) }
           onClick={ () =>  onClick && _handleClick(label, onClick) }>
            <FontAwesomeIcon icon={ icon } className={ styles.icon }/>
            <span className={ joinClassNames(styles.label, isCollapsed && styles['label-collapsed']) }>
                { label }
            </span>

            <span className={ styles.notification }>
                { children }
            </span>
        </a>
    </li>
);

const _handleClick = (label, onClick) => {
    onClick(label, false)
};

export default MenuItem;
