import React from 'react'

//utils
import joinClassNames from 'shared/utils/joinClassNames';

// styles
import styles from './LinkButton.css';

const LinkButton = ( { children, className, ...rest } ) => (
    <button className={ joinClassNames(className, styles.linkButton) } { ...rest } >
        { children }
    </button>
);

export default LinkButton;