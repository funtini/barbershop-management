import React from 'react';

//util
import joinClassNames from "shared/utils/joinClassNames";

// styles
import styles from './Badge.css';


const Badge = ( { children, className, ...rest } ) => (
        <span className={ joinClassNames(className, styles.badge) } { ...rest } >
            { children }
        </span>
);

export default Badge;