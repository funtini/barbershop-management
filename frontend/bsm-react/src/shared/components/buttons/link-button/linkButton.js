import React from 'react'

//utils
import joinClassNames from '../../../utils/joinClassNames';

// styles
import styles from './linkButton.css';

const linkButton = ( props ) =>{
    const { className, ...rest } = props;
    return (<button className={ joinClassNames(className, styles.linkButton) } { ...rest } >
        {props.children}
    </button>
)};

export default linkButton;